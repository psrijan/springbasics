SET FOREIGN_KEY_CHECKS=0;

INSERT INTO user (id, email, first_name, last_name, password,  user_name ) VALUES ( 1, 'p.srijan08@gmail.com', 'Srijan', 'Pandey', '$2a$10$UEZf9T2BQQ94K6n6uC6tjOQWB8c4wB/XknVql0ux4tONzSdwiwkQa', 'psrijan');

INSERT INTO authority (id, `name`,group_name) VALUES (1, 'ROLE_SUPERADMIN','Super Admin');
INSERT INTO authority (id, `name`,group_name) VALUES (2, 'ROLE_ADMIN','Admin');
INSERT INTO authority (id, `name`,group_name) VALUES (3, 'ROLE_USER','User');
--
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

SET FOREIGN_KEY_CHECKS=1;