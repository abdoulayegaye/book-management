package com.xoslu.tech.bookmanagment.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.xoslu.tech.bookmanagment.entity.AuthTokenRequest;
import com.xoslu.tech.bookmanagment.entity.AuthTokenResponse;
import com.xoslu.tech.bookmanagment.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakAuthService {

    private final Logger log = LoggerFactory.getLogger(KeycloakAuthService.class);
    private final String oidcIssuer = "http://localhost:8888/realms/book-realm";
    private final RestClient restClient;

    public KeycloakAuthService(RestClient.Builder clientBuilder) {
        this.restClient = clientBuilder.baseUrl(oidcIssuer).build();
    }

    public AuthTokenResponse getToken(AuthTokenRequest authTokenRequest) {
        System.out.println("Base url : " + oidcIssuer);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", authTokenRequest.getGrantType());
        formData.add("client_id", authTokenRequest.getClientId());
        formData.add("client_secret", authTokenRequest.getClientSecret());
        formData.add("username", authTokenRequest.getUsername());
        formData.add("password", authTokenRequest.getPassword());
        System.out.println("Form data map " + formData);
        try {
            ResponseEntity<AuthTokenResponse> response = this.restClient.post()
                    .uri("/protocol/openid-connect/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .exchange((req, res) -> {
                        if (res.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                            throw new UnauthorizedException("Invalid credentials");
                        }
                        return ResponseEntity.status(res.getStatusCode())
                                .body(res.bodyTo(AuthTokenResponse.class));
                    });
            assert response != null;
            return response.getBody();
        } catch (UnauthorizedException e) {
            throw e;
        } catch (Exception e) {
            log.info("Authentication failed: {}", e.getMessage());
            throw new UnauthorizedException("Authentication failed" + e.getMessage());
        }
    }

    public String extractEmailFromToken(String accessToken) {

        if (accessToken == null || accessToken.isBlank()) {
            log.warn("Le token est null ou vide !");
            return "Email non disponible";
        }

        try {
            SignedJWT signedJWT = SignedJWT.parse(accessToken);
            var claims = signedJWT.getJWTClaimsSet();

            String email = claims.getStringClaim("email");
            if (email != null && !email.isBlank()) {
                return email.trim();
            }

            String username = claims.getStringClaim("preferred_username");
            if (username != null && !username.isBlank()) {
                return username.trim();
            }

            return "Email non disponible";
        } catch (Exception e) {
            log.error("Erreur lors de l'extraction de l'email du token", e);
            return "Email non disponible";
        }
    }

    public List<String> getRolesFromToken(String accessToken) throws ParseException {
        List<String> roles = new ArrayList<>();

        // Parse le token JWT
        SignedJWT signedJWT = SignedJWT.parse(accessToken);
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

        // 1️⃣ Rôles realm
        Map<String, Object> realmAccess = (Map<String, Object>) claims.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") instanceof List<?> realmRoles) {
            realmRoles.forEach(role -> roles.add(role.toString()));
        }

        // 2️⃣ Rôles client
        Map<String, Object> resourceAccess = (Map<String, Object>) claims.getClaim("resource_access");
        if (resourceAccess != null) {
            resourceAccess.forEach((client, value) -> {
                if (value instanceof Map<?, ?> clientMap && clientMap.get("roles") instanceof List<?> clientRoles) {
                    clientRoles.forEach(r -> roles.add(r.toString()));
                }
            });
        }

        return roles;
    }
}
