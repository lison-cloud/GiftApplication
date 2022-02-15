INSERT INTO tag(t_name) VALUES ('a');
INSERT INTO tag(t_name) VALUES ('b');
INSERT INTO tag(t_name) VALUES ('c');
INSERT INTO tag(t_name) VALUES ('d');
INSERT INTO tag(t_name) VALUES ('e');
INSERT INTO tag(t_name) VALUES ('f');
INSERT INTO tag(t_name) VALUES ('g');
INSERT INTO tag(t_name) VALUES ('k');
INSERT INTO tag(t_name) VALUES ('l');
INSERT INTO tag(t_name) VALUES ('m');


INSERT INTO gift_certificate(gc_name,gc_description,gc_price,
        gc_duration,gc_createAt,gc_last_updateAt)
 VALUES ('walmart','for food',5.0, 10,'2022-01-29 10:00:00',null);
INSERT INTO gift_certificate(gc_name,gc_description,gc_price,
        gc_duration,gc_createAt,gc_last_updateAt)
 VALUES ('adidas','for sneakers',100.0,5,'2022-01-10 10:00:00','2022-01-11 15:00:00');
INSERT INTO gift_certificate(gc_name,gc_description,gc_price,
        gc_duration,gc_createAt,gc_last_updateAt)
 VALUES ('nike','same as adidas',50.0, 3,'2022-01-31 10:00:00','2022-01-31 15:15:50');
INSERT INTO gift_certificate(gc_name,gc_description,gc_price,
        gc_duration,gc_createAt,gc_last_updateAt)
 VALUES ('electronic','for smartphones',99.0, 10,'2022-01-31 10:00:00',null);
INSERT INTO gift_certificate(gc_name,gc_description,gc_price,
        gc_duration,gc_createAt,gc_last_updateAt)
 VALUES ('5elemet','for laptops',200.0,9,'2022-01-29 16:38:37',null);
INSERT INTO gift_certificate(gc_name,gc_description,gc_price,
        gc_duration,gc_createAt,gc_last_updateAt)
 VALUES ('pro climbing',null,34.0,15,'2022-01-28 10:00:00','2022-01-30 13:38:37');

INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (1,1);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (1,2);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (1,3);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (2,1);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (3,1);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (4,1);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (4,9);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (4,8);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (5,10);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (6,1);
INSERT INTO giftcertificate_tag(gct_gc_id, gct_t_id) VALUES (6,2);