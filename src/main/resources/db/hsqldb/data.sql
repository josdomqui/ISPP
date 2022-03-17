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

INSERT INTO plates(id, name, category, cost) values (1, 'Huevos Lartisan', 'Media-Racion', '11');
INSERT INTO plates(id, name, category, cost) values (2, 'Solomillo de vaca', 'Racion', '18');
INSERT INTO plates(id, name, category, cost) values (3, 'Chuleta de atun', 'Tapa', '55');

INSERT INTO plates(id, name, category, cost) values (4, 'Souffle de verduras con atun', 'Entrante', '17');
INSERT INTO plates(id, name, category, cost) values (5, 'Cordero con yeso y tupinambo', 'Plato princiapl', '28');
INSERT INTO plates(id, name, category, cost) values (6, 'Cromlech de Madioca y Huitlacoche', 'Plato princiapl', '60');
INSERT INTO plates(id, name, category, cost) values (7, 'Trufón de chocolate', 'Postre', '13');
INSERT INTO plates(id, name, category, cost) values (8, 'Angulas a la vongole con navajas de playa', 'Entrante', '55');
INSERT INTO plates(id, name, category, cost) values (9, 'Ensalada líquida Andalusí', 'Entrante', '16');
INSERT INTO plates(id, name, category, cost) values (10,'Chuleta de raya Nikkei con pure de maiz', 'Plato principal', '42');

INSERT INTO restaurants(id, name, address, city, description, telephone, photo) VALUES (1, 'Gomez Martin SC', 'C/San juan del puerto a caceres, Nº 12', 'Huelva', 'Establecimiento de bebidas y cafetería', 959121521,'/resources/images/bar1.jpg');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo) VALUES (2, 'Tabernas Sevilla', 'C/Gamazo 6', 'Sevilla', 'Restaurante de cocina mediterránea con opciones vegetarianas', 954221128,'/resources/images/bar2.jpg');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo) VALUES (3, 'La mitad del cuarto', 'C/AV José Laguillo', 'Sevilla', 'Lugar idóneo para tapear', 954533124,'/resources/images/bar3.jpg');

