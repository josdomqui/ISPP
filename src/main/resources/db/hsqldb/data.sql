-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');

INSERT INTO tables(id, number, costumer) values (1, 1, 3);

INSERT INTO commands(id, name, price, mesa_id) values (1, 'usuario', 53.02, 1);

INSERT INTO plates(id, name, category, cost, image) values (1, 'Huevos Lartisan', 'Media-Racion', '11', '/resources/images/Huevos Lartisan.jpg');
INSERT INTO plates(id, name, category, cost, image) values (2, 'Solomillo de vaca', 'Racion', '18', '/resources/images/Solomillo de vaca.jpg');
INSERT INTO plates(id, name, category, cost, image) values (3, 'Chuleta de atun', 'Tapa', '55', '/resources/images/Chuleta de atun.jpg');
INSERT INTO plates(id, name, category, cost, image) values (4, 'Souffle de verduras con atun', 'Entrante', '17', '/resources/images/Souffle de verduras con atun.png');
INSERT INTO plates(id, name, category, cost, image) values (5, 'Cordero con yeso y tupinambo', 'Plato princiapl', '28', '/resources/images/Cordero con yeso y tupinambo.JPEG');
INSERT INTO plates(id, name, category, cost, image) values (6, 'Cromlech de Madioca y Huitlacoche', 'Plato princiapl', '60', '/resources/images/Cromlech de Madioca y Huitlacoche.jpg');
INSERT INTO plates(id, name, category, cost, image) values (7, 'Trufón de chocolate', 'Postre', '13', '/resources/images/Trufón de chocolate.jpg');
INSERT INTO plates(id, name, category, cost, image) values (8, 'Angulas a la vongole con navajas de playa', 'Entrante', '55', '/resources/images/Angulas a la vongole con navajas de playa.jpg');
INSERT INTO plates(id, name, category, cost, image) values (9, 'Ensalada líquida Andalusí', 'Entrante', '16', '/resources/images/Ensalada líquida Andalusí.jpg');
INSERT INTO plates(id, name, category, cost, image) values (10,'Chuleta de raya Nikkei con pure de maiz', 'Plato principal', '42', '/resources/images/Chuleta de raya Nikkei con pure de maiz.jpg');



