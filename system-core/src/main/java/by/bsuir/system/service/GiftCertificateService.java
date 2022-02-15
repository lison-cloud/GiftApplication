package by.bsuir.system.service;

import java.util.List;
import java.util.Optional;

import by.bsuir.system.dto.GiftCertificate;
import by.bsuir.system.dto.GiftCertificateRequest;

public interface GiftCertificateService {

    List<GiftCertificate> getAll();
 
    Optional<GiftCertificate> get(long id);

    Optional<GiftCertificate> getByName(String name);
    
    Optional<GiftCertificate> save(GiftCertificateRequest gc);

    void delete(long id);

    Optional<GiftCertificate> update(GiftCertificateRequest gc, long id);
}
