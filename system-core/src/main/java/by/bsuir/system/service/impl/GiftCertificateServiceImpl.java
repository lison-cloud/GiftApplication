package by.bsuir.system.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.system.dto.GiftCertificate;
import by.bsuir.system.dto.GiftCertificateRequest;
import by.bsuir.system.dto.Tag;
import by.bsuir.system.entities.GiftCertificateEntity;
import by.bsuir.system.repository.GiftCertificateRepository;
import by.bsuir.system.service.GiftCertificateService;
import by.bsuir.system.service.TagService;
import by.bsuir.system.service.exception.GiftCertificateServiceException;

@Service
public class GiftCertificateServiceImpl
        implements GiftCertificateService {

    private GiftCertificateRepository gcRepository;
    private TagService tagService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository gcRepository, TagService tagService) {
        this.gcRepository = gcRepository;
        this.tagService = tagService;
    }

    @Override
    public Optional<GiftCertificate> get(long id) {
        GiftCertificateEntity entity = this.gcRepository.get(id).orElseThrow(GiftCertificateServiceException::new);
        return Optional.of(this.convertToGc(entity));
    }

    private GiftCertificate convertToGc(GiftCertificateEntity entity) {
        return GiftCertificate.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .duration(entity.getDuration())
                .createDate(entity.getCreateDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .tags(this.tagService.getByGcId(entity.getId()))
                .build();
    }

    private GiftCertificateEntity convertToGcEntity(GiftCertificateRequest gc) {
        return GiftCertificateEntity.builder()
                .name(gc.getName())
                .description(gc.getDescription())
                .price(gc.getPrice())
                .duration(gc.getDuration())
                .build();
    }

    @Override
    public Optional<GiftCertificate> save(GiftCertificateRequest gc) {
        GiftCertificateEntity entity = this.convertToGcEntity(gc);
        entity.setCreateDate(new Date(Instant.now().toEpochMilli()));
        entity.setId(this.gcRepository.add(entity));
        this.tagService.addAllTagToGc(gc.getTags(), entity.getId());
        return Optional.of(this.convertToGc(entity));
    }

    @Override
    public void delete(long id) {
        this.tagService.removeAllTagFromGc(id);
        if (!this.gcRepository.delete(id))
            throw new GiftCertificateServiceException("some error accrued when deleting gc: " + id);
    }

    @Override
    public Optional<GiftCertificate> update(GiftCertificateRequest gc, long id) {
        GiftCertificateEntity entity = this.gcRepository.get(id).orElseThrow(GiftCertificateServiceException::new);
        GiftCertificateEntity updatedEntity = this.convertToGcEntity(gc);

        Optional.ofNullable(updatedEntity.getName()).ifPresent(entity::setName);
        Optional.ofNullable(updatedEntity.getDescription()).ifPresent(entity::setDescription);
        
        if(updatedEntity.getPrice()!=0)
            entity.setPrice(updatedEntity.getPrice());
        if(updatedEntity.getDuration()!=0)
            entity.setDuration(updatedEntity.getDuration());

        entity.setLastUpdateDate(new Date(Instant.now().toEpochMilli()));

        if (!this.gcRepository.update(entity))
            throw new GiftCertificateServiceException("can not update entity with id: " + id);

        this.tagService.addAllTagToGc(gc.getTags(), id);

        return Optional.of(this.convertToGc(entity));
    }

    @Override
    public List<GiftCertificate> getAll() {
        return this.gcRepository.getAll().stream()
                .map(this::convertToGc)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GiftCertificate> getByName(String name) {
        GiftCertificateEntity entity = this.gcRepository.getByName(name)
                .orElseThrow(GiftCertificateServiceException::new);
        return Optional.of(this.convertToGc(entity));
    }

}
