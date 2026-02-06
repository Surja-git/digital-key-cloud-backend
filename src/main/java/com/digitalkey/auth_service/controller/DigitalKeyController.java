package com.digitalkey.auth_service.controller;

import com.digitalkey.auth_service.dto.CreateDigitalKeyRequest;
import com.digitalkey.auth_service.dto.DigitalKeyResponse;
import com.digitalkey.auth_service.entity.DigitalKey;
import com.digitalkey.auth_service.service.DigitalKeyService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digital-keys")
public class DigitalKeyController {

    private final DigitalKeyService digitalKeyService;

    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }

    // 🔹 CREATE DIGITAL KEY
    @PostMapping
    public ResponseEntity<DigitalKeyResponse> createKey(
            @RequestBody CreateDigitalKeyRequest request) {

        DigitalKey key = digitalKeyService.createKey(
                request.getUserId(),
                request.getVehicleId()
        );

        return new ResponseEntity<>(mapToResponse(key), HttpStatus.CREATED);
    }

    // 🔹 GET DIGITAL KEY
    @GetMapping("/{keyId}")
    public ResponseEntity<DigitalKeyResponse> getKey(@PathVariable String keyId) {

        DigitalKey key = digitalKeyService.getKeyById(keyId);
        return ResponseEntity.ok(mapToResponse(key));
    }

    // 🔹 REVOKE DIGITAL KEY
    @PutMapping("/{keyId}/revoke")
    public ResponseEntity<String> revokeKey(@PathVariable String keyId) {

        digitalKeyService.revokeKey(keyId);
        return ResponseEntity.ok("Digital key revoked successfully");
    }

    // 🔹 MAPPER (Entity → DTO)
    private DigitalKeyResponse mapToResponse(DigitalKey key) {

        DigitalKeyResponse response = new DigitalKeyResponse();
        response.setKeyId(key.getKeyId());
        response.setUserId(key.getUserId());
        response.setVehicleId(key.getVehicleId());
        response.setStatus(key.getStatus());
        response.setCreatedAt(key.getCreatedAt());

        return response;
    }
}
