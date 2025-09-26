package com.xoslu.tech.bookmanagment.controller;

import com.xoslu.tech.bookmanagment.entity.AuthInfo;
import com.xoslu.tech.bookmanagment.entity.AuthTokenRequest;
import com.xoslu.tech.bookmanagment.entity.UserInfosResponse;
import com.xoslu.tech.bookmanagment.exception.UnauthorizedException;
import com.xoslu.tech.bookmanagment.exception.error.ErrorResponse;
import com.xoslu.tech.bookmanagment.service.impl.KeycloakAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Authentification", description = "Gestion de l'authentification")
public class AuthController {

    private final KeycloakAuthService keycloakAuthService;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    String clientSecret;

    public AuthController(KeycloakAuthService keycloakAuthService) {
        this.keycloakAuthService = keycloakAuthService;
    }

    @PostMapping("/login")
    @Operation(summary = "Authentification", description = "Donne username et password et retourne un token")
    public ResponseEntity<Object> getAuthToken(@RequestBody AuthInfo request) {
        log.info("Log request : {}", request);
        try {
            AuthTokenRequest authTokenRequest = new AuthTokenRequest(
                    request.getUsername(),
                    request.getPassword(),
                    (request.getGrantType()) == null ? "password" : request.getGrantType()
            );
            authTokenRequest.setClientId(clientId);
            authTokenRequest.setClientSecret(clientSecret);
            var token = keycloakAuthService.getToken(authTokenRequest);
            log.info("Token : {}", token.getAccessToken());
            //String email = keycloakAuthService.extractEmailFromToken(token.getAccessToken());

            return ResponseEntity.ok(new UserInfosResponse(
                            request.getUsername(),
                            null,
                            //keycloakAuthService.getRolesFromToken(token.getAccessToken()),
                            token.getAccessToken(),
                            token.getRefreshToken(),
                            null
                    )
            );
        } catch (UnauthorizedException e) {
            ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials !");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        } catch (Exception e) {
            log.error("Erreur lors de l'authentification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
