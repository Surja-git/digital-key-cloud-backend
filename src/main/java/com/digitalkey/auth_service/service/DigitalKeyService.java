package com.digitalkey.auth_service.service;

import com.digitalkey.auth_service.entity.DigitalKey;
import com.digitalkey.auth_service.repository.DigitalKeyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DigitalKeyService {

    private final DigitalKeyRepository digitalKeyRepository;

    public DigitalKeyService(DigitalKeyRepository digitalKeyRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
    }

    // Create a new Digital Key
    public DigitalKey createKey(String userId, String vehicleId) {

        DigitalKey key = new DigitalKey();
        key.setKeyId(UUID.randomUUID().toString());
        key.setUserId(userId);
        key.setVehicleId(vehicleId);
        key.setStatus("ACTIVE");

        return digitalKeyRepository.save(key);
    }

    // Revoke an existing Digital Key
    public DigitalKey revokeKey(String keyId) {

        DigitalKey key = digitalKeyRepository.findByKeyId(keyId)
                .orElseThrow(() -> new RuntimeException("Digital Key not found"));

        key.setStatus("REVOKED");
        return digitalKeyRepository.save(key);
    }

    // Get Digital Key status
    public String getKeyStatus(String keyId) {

        Optional<DigitalKey> key = digitalKeyRepository.findByKeyId(keyId);
        return key.map(DigitalKey::getStatus).orElse("NOT_FOUND");
    }
    public DigitalKey getKeyById(String keyId) {
        return digitalKeyRepository.findByKeyId(keyId)
                .orElseThrow(() -> new RuntimeException("Digital Key not found"));
    }
}