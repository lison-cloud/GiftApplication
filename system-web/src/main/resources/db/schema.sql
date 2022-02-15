-- DROP DATABASE IF EXISTS system_service;

-- CREATE DATABASE system_service DEFAULT CHARACTER SET 'utf8'
--     DEFAULT COLLATE 'utf8_unicode_ci';

-- USE system_service;

CREATE TABLE gift_certificate (
    gc_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    gc_name VARCHAR(100) NOT NULL,
    gc_description TEXT NULL DEFAULT NULL,
    gc_price DOUBLE UNSIGNED NOT NULL,
    gc_duration INT UNSIGNED NOT NULL,
    gc_createAt DATETIME NOT NULL,
    gc_last_updateAt DATETIME NULL DEFAULT NULL
);

CREATE TABLE tag (
    t_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    t_name VARCHAR(100) NOT NULL
);

CREATE TABLE giftcertificate_tag (
    gct_t_id BIGINT UNSIGNED NOT NULL,
    gct_gc_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (gct_gc_id, gct_t_id),
    FOREIGN KEY (gct_gc_id) REFERENCES gift_certificate (gc_id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (gct_t_id) REFERENCES tag (t_id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);