package com.amz.altlearner.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class IdTokenVerifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdTokenVerifier.class);
    private static final String GOOGLE_CLIENT_ID = "28523494863-6fpqh3i0171mrm3105ga6po9errrmrqb.apps.googleusercontent.com";

    public boolean verify(final String provider, final String token, final String email) {
        try {
            if ("GOOGLE".equalsIgnoreCase(provider)) {
                return verifyGoogleToken(token, email);
            }
        } catch (final Exception e) {
            LOGGER.error("Error verifying user", e);
        }
        return false;
    }

    private boolean verifyGoogleToken(final String token, final String email) throws Exception {
        final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();
        return verifier.verify(token).getPayload().getEmail().equalsIgnoreCase(email);
    }


}
