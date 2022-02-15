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

import by.bsuir.system.entities.GiftCertificateEntity;
import by.bsuir.system.repository.GiftCertificateRepository;

@Repository
public class GiftCertificateRepositoryImpl
        implements GiftCertificateRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(GiftCertificateEntity t) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("gift_certificate")
                .usingGeneratedKeyColumns("gc_id");
        Map<String, Object> map = new HashMap<>();
        map.put("gc_name", t.getName());
        map.put("gc_description", t.getDescription());
        map.put("gc_price", t.getPrice());
        map.put("gc_duration", t.getDuration());
        map.put("gc_createAt", t.getCreateDate());
        map.put("gc_last_updateAt", t.getLastUpdateDate());

        return simpleJdbcInsert.executeAndReturnKey(map).longValue();
    }

    @Override
    public Optional<GiftCertificateEntity> get(Long u) {
        String sql = "SELECT * FROM gift_certificate WHERE gc_id=?;";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, this::mapRowToGs, u));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Long u) {
        String sql = "DELETE FROM gift_certificate WHERE gc_id=?;";
        try {
            return this.jdbcTemplate.update(sql, u) > 0;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(GiftCertificateEntity t) {
        String sql = "UPDATE gift_certificate SET gc_name=?," +
                " gc_description=?, gc_price=?, gc_duration=?," +
                " gc_createAt=?, gc_last_updateAt=? WHERE gc_id=?;";
        try {
            return this.jdbcTemplate.update(sql, t.getName(), t.getDescription(),
                    t.getPrice(), t.getDuration(), t.getCreateDate(),
                    t.getLastUpdateDate(), t.getId()) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private GiftCertificateEntity mapRowToGs(ResultSet rs, int rn) throws SQLException {
        return GiftCertificateEntity.builder()
                .id(rs.getLong("gc_id"))
                .name(rs.getString("gc_name"))
                .description(rs.getString("gc_description"))
                .price(rs.getDouble("gc_price"))
                .duration(rs.getInt("gc_duration"))
                .createDate(rs.getTimestamp("gc_createAt"))
                .lastUpdateDate(rs.getTimestamp("gc_last_updateAt"))
                .build();
    }

    @Override
    public List<GiftCertificateEntity> getAll() {
        String sql = "SELECT * FROM gift_certificate;";
        try {
            return this.jdbcTemplate.query(sql, this::mapRowToGs);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<GiftCertificateEntity> getByName(String name) {
        String sql = "SELECT * FROM gift_certificate WHERE gc_name=?;";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, this::mapRowToGs, name));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

}
