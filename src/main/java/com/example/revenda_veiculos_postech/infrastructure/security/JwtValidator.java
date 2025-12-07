package com.example.revenda_veiculos_postech.infrastructure.security;

import java.net.URL;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

@Component
public class JwtValidator {

    @Value("${aws.cognito.issuer}")
    private String issuer;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.jwksUrl}")
    private String jwksUrl;

    private ConfigurableJWTProcessor<SecurityContext> jwtProcessor;
    private Instant jwksCacheExpiry;
    private final Map<String, JWTClaimsSet> tokenCache = new ConcurrentHashMap<>();

    public JWTClaimsSet validateToken(String token) throws ParseException, BadJOSEException, JOSEException {
        if (tokenCache.containsKey(token)) {
            JWTClaimsSet cached = tokenCache.get(token);
            if (cached.getExpirationTime().after(new Date())) {
                return cached;
            }
            tokenCache.remove(token);
        }

        if (jwtProcessor == null || jwksCacheExpiry == null || Instant.now().isAfter(jwksCacheExpiry)) {
            initializeJwtProcessor();
        }

        JWTClaimsSet claims = jwtProcessor.process(token, null);

        if (!issuer.equals(claims.getIssuer())) {
            throw new BadJOSEException("Invalid issuer");
        }

        String audience = claims.getAudience() != null && !claims.getAudience().isEmpty() 
            ? claims.getAudience().get(0) 
            : (String) claims.getClaim("client_id");
        
        if (!clientId.equals(audience)) {
            throw new BadJOSEException("Invalid audience/client_id");
        }

        Date now = new Date();
        if (claims.getExpirationTime() == null || claims.getExpirationTime().before(now)) {
            throw new BadJOSEException("Token expired");
        }
        if (claims.getIssueTime() == null || claims.getIssueTime().after(now)) {
            throw new BadJOSEException("Invalid issue time");
        }

        tokenCache.put(token, claims);

        return claims;
    }

    public String extractSubject(JWTClaimsSet claims) {
        return claims.getSubject();
    }

    public String extractEmail(JWTClaimsSet claims) {
        return (String) claims.getClaim("email");
    }

    public String extractUsername(JWTClaimsSet claims) {
        return (String) claims.getClaim("username");
    }

    private void initializeJwtProcessor() {
        try {
            JWKSource<SecurityContext> keySource = new RemoteJWKSet<>(new URL(jwksUrl));

            ConfigurableJWTProcessor<SecurityContext> processor = new DefaultJWTProcessor<>();
            JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(
                JWSAlgorithm.RS256,
                keySource
            );
            processor.setJWSKeySelector(keySelector);

            this.jwtProcessor = processor;
            this.jwksCacheExpiry = Instant.now().plusSeconds(86400);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize JWT processor", e);
        }
    }
}
