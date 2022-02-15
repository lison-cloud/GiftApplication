package by.bsuir.system.service;

import java.util.List;
import java.util.Optional;

import by.bsuir.system.dto.Tag;
import by.bsuir.system.dto.TagRequest;
import by.bsuir.system.service.exception.TagServiceException;

public interface TagService {

    List<Tag> getAll() throws TagServiceException;

    List<Tag> getByGcId(long gcId) throws TagServiceException;

    Optional<Tag> get(long id) throws TagServiceException;

    Optional<Tag> getByName(String name) throws TagServiceException;
    
    Optional<Tag> save(TagRequest tag) throws TagServiceException;

    List<Tag> saveList(List<TagRequest> tag) throws TagServiceException; 

    void delete(long id) throws TagServiceException;

    void deleteByName(String name) throws TagServiceException;

    void addTagToGc(long tagId, long gcId);

    void addAllTagToGc(List<TagRequest> tag, long gcId);

    void removeAllTagFromGc(long gcId);

    void removeTagFromGc(long tagId, long gcId);

}
