package by.bsuir.system.repository;

import java.util.List;
import java.util.Optional;

import by.bsuir.system.dto.Tag;

public interface TagRepository
        extends BaseCrudRepository<Tag, Long> {

    List<Tag> getByGcId(long gcId);

    Optional<Tag> getByName(String name);

    boolean addTagTOGc(long tagId, long gcId);

    boolean removeAllTagFromGc(long gcId);

    boolean removeTagFromGc(long tagId, long gcId);

    boolean deleteByName(String name);
}
