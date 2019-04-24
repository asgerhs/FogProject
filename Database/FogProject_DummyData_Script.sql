-- Træ og tagplader
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('25x200 mm. trykimp. Brædt', 360, 4, 'Stk', 'understernbrædder til for & bag ende');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('25x200 mm. trykimp. Brædt', 540, 4, 'Stk', 'understernbrædder til siderne');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('25x125mm. trykimp. Brædt', 360, 2, 'Stk', 'overstærnbrædder til forenden');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('25x125mm. trykimp. Brædt', 540, 4, 'Stk', 'overstærnbrædder til siderne');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('38x73 mm. Lægte ubh.', 420, 1, 'Stk', 'til z på bagside af dør');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('45x95 mm. Reglar	ub.', 270, 12, 'Stk','løsholter til skur gavle');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('45x95 mm. Reglar	ub.', 240, 4, 'Stk', 'løsholter til skur sider');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('45x195 mm. spærtræ ubh.', 600, 2, 'Stk', 'Remme i sider, sadles ned i stolper');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('45x195 mm. spærtræ ubh.', 480, 1, 'Stk', 'Remme i sider, sadles ned i stolper (skur del, deles)');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('45x195 mm. spærtræ ubh.', 600, 15, 'Stk', 'Spær, monteres på rem');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('97x97 mm. trykimp. Stolpe', 300, 11, 'Stk', 'Stolper nedgraves 90 cm. i jord');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('19x100 mm. trykimp. Brædt', 210, 200, 'Stk', 'til beklædning af skur 1 på 2');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('19x100 mm. trykimp. Brædt', 540, 4, 'Stk', 'vandbrædt på stærn i sider');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('19x100 mm. trykimp. Brædt', 360, 2, 'Stk', 'vandbrædt på stærn i forende');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('Plastmo Ecolite blåtonet', 600, 6, 'Stk', 'tagplader monteres på stærn');
INSERT INTO stock(name, length, qty, unit, `description`) VALUES ('Plastmo Ecolite blåtonet', 360, 6, 'Stk', 'tagplader monteres på stærn');

-- Beslag og skruer
INSERT INTO stock(name, qty, unit, `description`) VALUES ('plastmo bundskruer 200 stk.', 3, 'pakke', 'Skruer til tagplader');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('hulbånd 1x20 mm. 10 mtr.', 2, 'rulle', 'Til vindkyrds på stærn');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('universal 190 mm højre', 15, 'Stk', 'Til montering af spær på rem');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('universal 190 mm venstre', 15, 'Stk', 'Til montering af spær på rem');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('4,5 x 60 mm. skruer 200 stk.', 1, 'pakke', 'Til montering af stern & vandbrædt');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('4,0 x 50 mm. beslagskruer 250 stk', 3, 'pakke', 'Til montering af universalbeslag + hulbånd');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('bræddebolt 10 x 120 mm.', 18, 'Stk', 'Til montering af rem på stolper');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('firkantskiver 40x40x11 mm.', 12, 'Stk', 'Til montering af rem på stolper');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('4,5 x 70 mm. Skruer 400 stk', 2, 'pakke', 'Til montering af yderste beklædning');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('4,5 x 50 mm. Skruer 300 stk.', 2, 'pakke', 'Til montering af inderste beklædning');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('stalddørsgreb 50x75', 1, 'sæt', 'Til lås på dør i skur');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('t hængsel 390 mm', 2, 'Stk', 'Til skurdør');
INSERT INTO stock(name, qty, unit, `description`) VALUES ('vinkelbeslag 35', 32, 'Stk', 'Til montering af løsholter i skur');

-- User dummy
INSERT INTO users VALUES ('Gaspard Augé', 'mester@fog.dk', 'adgang', 'ADMIN');
INSERT INTO users VALUES ('Xavier de Rosnay', 'elev@fog.dk', 'adgang', 'SALESMAN');
select * from stock;
select * from users;