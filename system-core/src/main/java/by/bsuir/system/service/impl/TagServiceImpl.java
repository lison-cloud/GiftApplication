package by.bsuir.system.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsuir.system.dto.Tag;
import by.bsuir.system.dto.TagRequest;
import by.bsuir.system.repository.TagRepository;
import by.bsuir.system.service.TagService;
import by.bsuir.system.service.exception.TagServiceException;

@Service
public class TagServiceImpl
        implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Optional<Tag> get(long id) throws TagServiceException {
        return this.tagRepository.get(id);
    }

    @Override
    public Optional<Tag> save(TagRequest tagRequest) throws TagServiceException {
        Optional<Tag> t = this.tagRepository.getByName(tagRequest.getName());
        if (t.isPresent())
            return t;
        Tag tag = this.convertToTag(tagRequest);
        tag.setId(this.tagRepository.add(tag));
        return Optional.of(tag);
    }

    private Tag convertToTag(TagRequest tagRequest) {
        return Tag.builder()
                .name(tagRequest.getName())
                .build();
    }

    @Override
    public void delete(long id) throws TagServiceException {
        if (!this.tagRepository.delete(id))
            throw new TagServiceException();
    }

    @Override
    public void deleteByName(String name) throws TagServiceException {
        this.tagRepository.deleteByName(name);
    }

    @Override
    public List<Tag> getAll() throws TagServiceException {
        return this.tagRepository.getAll();
    }

    @Override
    public Optional<Tag> getByName(String name) throws TagServiceException {
        return this.tagRepository.getByName(name);
    }

    @Override
    public List<Tag> getByGcId(long gcId) throws TagServiceException {
        return this.tagRepository.getByGcId(gcId);
    }

    @Override
    public List<Tag> saveList(List<TagRequest> tag) throws TagServiceException {
        return tag.stream().map(t -> this.save(t).orElseThrow(TagServiceException::new)).collect(Collectors.toList());
    }

    @Override
    public void addTagToGc(long tagId, long gcId) {
        this.tagRepository.addTagTOGc(tagId, gcId);
    }

    @Override
    public void removeAllTagFromGc(long gcId) {
        if(!this.tagRepository.removeAllTagFromGc(gcId))
            throw new TagServiceException();
    }

    @Override
    public void removeTagFromGc(long tagId, long gcId) {
        this.tagRepository.removeTagFromGc(tagId, gcId);
    }

    @Override
    public void addAllTagToGc(List<TagRequest> tag, long gcId) {
        tag.stream().map(t -> this.save(t).orElseThrow(TagServiceException::new))
                .forEach(t -> this.addTagToGc(t.getId(), gcId));
    }

}
