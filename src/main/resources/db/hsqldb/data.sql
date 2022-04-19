-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- Username and authority
INSERT INTO users(username,password,enabled) VALUES ('gomezmartin','Jnais872NS',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'gomezmartin','restaurant');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo, capacity, schedule, email, username, media) VALUES (1, 'Gomez Martin SC', 'Avenida la borbolla 3', 'Sevilla', 'Establecimiento de bebidas y cafetería', 959121521,'/resources/images/bar1.jpg', 12, 'Lunes/Viernes 10:00 - 20:00', 'gomezmartin@gmail.com', 'gomezmartin', null);
--
INSERT INTO users(username,password,enabled) VALUES ('tabernasevilla','Jnais872N2sv231S;;',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'tabernasevilla','restaurant');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo, capacity, schedule, email, username, media) VALUES (2, 'Tabernas Sevilla', 'C/Gamazo 6', 'Sevilla', 'Restaurante de cocina mediterránea con opciones vegetarianas', 954221128,'/resources/images/bar2.jpg', 20, 'Lunes/Viernes 10:00 - 22:00', 'tabernasevilla@gmail.com', 'tabernasevilla', 3.5);

INSERT INTO users(username,password,enabled) VALUES ('mitadcuarto','s872N2sv2Ssdik928231S#',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'mitadcuarto','restaurant');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo, capacity, schedule, email, username, media) VALUES (3, 'La mitad del cuarto', 'C/AV José Laguillo', 'Sevilla', 'Lugar idóneo para tapear', 954533124,'/resources/images/bar3.jpg', 20, 'Lunes/Viernes 11:00 - 20:00', 'mitadcuarto@gmail.com', 'mitadcuarto', 5.0);

INSERT INTO users(username,password,enabled) VALUES ('parada','parada',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'parada','restaurant');
INSERT INTO restaurants(id, name, address, city, description, telephone, photo, capacity, schedule, email, username, media) VALUES (4, 'Bar Parada', 'C/AV Sanchez Romero', 'Huelva', 'Lugar idóneo para tapear', 666666666,'/resources/images/bar1.jpg', 20, 'Lunes/Viernes 11:00 - 20:00', 'barparada@gmail.com', 'parada', 2.5);

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

INSERT INTO commands(id, costumers, price, mesa_id, restaurant_id) values (1, 4, 53.02, 1, 1);

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

INSERT INTO valoraciones(id, opinion, puntuacion, restaurant_id) values (1, 'El restaurante es muy recomendable', null, 1);
INSERT INTO valoraciones(id, opinion, puntuacion, restaurant_id) values (2, 'El restaurante es de muy alto nivel', null, 1);
INSERT INTO valoraciones(id, opinion, puntuacion, restaurant_id) values (3, 'Una experiencia maravillosa, el lugar perfecto', 5, 2);
INSERT INTO valoraciones(id, opinion, puntuacion, restaurant_id) values (4, 'Posiblemente fuera un mal día porque el sitio tiene buena pinta, pero un servicio muy mejorable y la comida no pasa de decente', 2, 2);
INSERT INTO valoraciones(id, opinion, puntuacion, restaurant_id) values (5, 'Inmejorable', 5, 3);
INSERT INTO valoraciones(id, opinion, puntuacion, restaurant_id) values (6, 'Todo correcto', 3, 4);
INSERT INTO valoraciones(id, opinion, puntuacion, restaurant_id) values (7, 'Un poco decepcionante, me esperaba un mejor servicio', 2, 4);