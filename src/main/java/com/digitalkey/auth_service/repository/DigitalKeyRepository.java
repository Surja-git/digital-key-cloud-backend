package com.digitalkey.auth_service.repository;

import com.digitalkey.auth_service.entity.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    Optional<DigitalKey> findByKeyId(String keyId);
}
