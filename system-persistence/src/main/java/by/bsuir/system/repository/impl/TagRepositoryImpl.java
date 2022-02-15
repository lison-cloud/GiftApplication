package by.bsuir.system.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import by.bsuir.system.dto.Tag;
import by.bsuir.system.repository.TagRepository;

@Repository
public class TagRepositoryImpl
        implements TagRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(Tag t) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tag")
                .usingGeneratedKeyColumns("t_id");
        Map<String, Object> map = new HashMap<>();
        map.put("t_name", t.getName());
        return simpleJdbcInsert.executeAndReturnKey(map).longValue();
    }

    @Override
    public Optional<Tag> get(Long u) {
        String sql = "SELECT * FROM tag WHERE t_id=?;";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, this::mapRowToGs, u));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tag> getByName(String name) {
        String sql = "SELECT * FROM tag WHERE t_name=?;";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, this::mapRowToGs, name));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Long u) {
        String sql = "DELETE FROM tag WHERE t_id=?;";
        try {
            return this.jdbcTemplate.update(sql, u) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean update(Tag t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tag> getAll() {
        String sql = "SELECT * FROM tag;";
        return this.jdbcTemplate.query(sql, this::mapRowToGs);
    }

    private Tag mapRowToGs(ResultSet rs, int rn) throws SQLException {
        return Tag.builder()
                .id(rs.getLong("t_id"))
                .name(rs.getString("t_name"))
                .build();
    }

    @Override
    public boolean deleteByName(String name) {
        String sql = "DELETE FROM tag WHERE t_name=?;";
        try {
            return this.jdbcTemplate.update(sql, name) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public List<Tag> getByGcId(long gcId) {
        String sql = "SELECT t_id,t_name FROM tag AS t JOIN giftcertificate_tag AS gct ON gct.gct_t_id=t.t_id WHERE gct_gc_id=?;";
        return this.jdbcTemplate.query(sql, this::mapRowToGs, gcId);
    }

    @Override
    public boolean addTagTOGc(long tagId, long gcId) {
        String sql = "INSERT INTO giftcertificate_tag(gct_t_id, gct_gc_id) VALUES (?,?);";
        try {
            return this.jdbcTemplate.update(sql, tagId, gcId) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean removeAllTagFromGc(long gcId) {
        String sql = "DELETE FROM giftcertificate_tag WHERE gct_gc_id=?;";
        try {
            return this.jdbcTemplate.update(sql, gcId) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean removeTagFromGc(long tagId, long gcId) {
        String sql = "DELETE FROM giftcertificate_tag WHERE gct_t_id=? AND gct_gc_id=?;";
        try {
            return this.jdbcTemplate.update(sql, tagId, gcId) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

}
