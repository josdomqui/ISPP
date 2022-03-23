-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO restaurants(id, name, address, city, description, telephone, photo, capacity, schedule, email) VALUES (1, 'Gomez Martin SC', 'Avenida la borbolla 3', 'Sevilla', 'Establecimiento de bebidas y cafetería', 959121521,'/resources/images/bar1.jpg', 12, 'Lunes/Viernes 10:00 - 20:00', 'gomezmartin@gmail.com');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo, capacity, schedule, email) VALUES (2, 'Tabernas Sevilla', 'C/Gamazo 6', 'Sevilla', 'Restaurante de cocina mediterránea con opciones vegetarianas', 954221128,'/resources/images/bar2.jpg', 20, 'Lunes/Viernes 10:00 - 22:00', 'tabernasevilla@gmail.com');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo, capacity, schedule, email) VALUES (3, 'La mitad del cuarto', 'C/AV José Laguillo', 'Sevilla', 'Lugar idóneo para tapear', 954533124,'/resources/images/bar3.jpg', 20, 'Lunes/Viernes 11:00 - 20:00', 'mitadcuarto@gmail.com');

INSERT INTO products(id, name, description, price, restaurant_id) VALUES (1, 'Ensalada gourmet', 'Lechuga Gourmet, Queso De Cabra, Cebolla Caramelizada, Nueces y Pasas',6.30, 1);
INSERT INTO products(id, name, price, restaurant_id) VALUES (2, 'Patatas bravas', 2.90, 1);
INSERT INTO products(id, name, price, restaurant_id) VALUES (3, 'Montadito Pollo', 2.60, 1);
INSERT INTO products(id, name, description, price, restaurant_id) VALUES (4, 'Tosta de la casa', 'Solomillo, Queso de Cabra, Cebolla Caramelizada y Salsa Pedro Ximénez', 3.60, 1);
INSERT INTO products(id, name, price, restaurant_id) VALUES (5, 'Solomillo al roque', 2.30, 1);

INSERT INTO products(id, name, description, price, restaurant_id) VALUES (6, 'Mini Hamburguesa de Buey', 'Hamburguesa con verdadera carne vacuno',4.50, 2);
INSERT INTO products(id, name, price, restaurant_id) VALUES (7, 'Patatas bravas', 2.90, 2);
INSERT INTO products(id, name, price, restaurant_id) VALUES (8, 'Semifrío Gourmet De Tarta De Queso', 3.60, 2);
INSERT INTO products(id, name, price, restaurant_id) VALUES (9, 'Solomillo al roque', 2.30, 2);

INSERT INTO products(id, name, description, price, restaurant_id) VALUES (10, 'Condado de Oriza', 'Verdadero vino de la Rioja',12.30, 3);
INSERT INTO products(id, name, description, price, restaurant_id) VALUES (11, 'Verdejo rueda','Verdejo rueda', 2.90, 3);
INSERT INTO products(id, name, price, restaurant_id) VALUES (12, 'Montadito Pollo', 2.60, 3);
INSERT INTO products(id, name, price, restaurant_id) VALUES (13, 'Solomillo al roque', 2.30, 3);

INSERT INTO restaurante_type(restaurante_id, type) VALUES (1,'DOS_TENEDORES');
INSERT INTO restaurante_type(restaurante_id, type) VALUES (1,'CERVECERIA');
INSERT INTO restaurante_type(restaurante_id, type) VALUES (2,'COMEDORES');
INSERT INTO restaurante_type(restaurante_id, type) VALUES (2,'EUROPA_DEL_ESTE');
INSERT INTO restaurante_type(restaurante_id, type) VALUES (3,'GOURMET');
INSERT INTO restaurante_type(restaurante_id, type) VALUES (3,'AUTOR');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');

INSERT INTO tables(id, number, costumer) values (1, 1, 3);
INSERT INTO tables(id, number, costumer) values (2, 2, 4);
INSERT INTO tables(id, number, costumer) values (3, 3, 5);
INSERT INTO tables(id, number, costumer) values (4, 4, 6);
INSERT INTO tables(id, number, costumer) values (5, 5, 7);

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
