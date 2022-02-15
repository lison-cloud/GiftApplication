package by.bsuir.system.repository;

import java.util.Optional;

import by.bsuir.system.entities.GiftCertificateEntity;

public interface GiftCertificateRepository
        extends BaseCrudRepository<GiftCertificateEntity, Long> {
    
    Optional<GiftCertificateEntity> getByName(String name);
}
