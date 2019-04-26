-- Træ og tagplader
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (0, '25x200 mm. trykimp. Brædt', 	360,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (1, '25x200 mm. trykimp. Brædt', 	540,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (2, '25x125mm. trykimp. Brædt', 	360,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (3, '25x125mm. trykimp. Brædt', 	540,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (4, '38x73 mm. Lægte ubh.', 		420,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (5, '45x95 mm. Reglar	ub.', 		270,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (6, '45x95 mm. Reglar	ub.', 		240,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (7, '45x195 mm. spærtræ ubh.', 	    600,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (8, '45x195 mm. spærtræ ubh.', 	    480,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (9, '45x195 mm. spærtræ ubh.', 	    600,   'Stk', 1);	
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (10, '97x97 mm. trykimp. Stolpe', 	300,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (11, '19x100 mm. trykimp. Brædt', 	210,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (12, '19x100 mm. trykimp. Brædt', 	540,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (13, '19x100 mm. trykimp. Brædt', 	360,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (14, 'Plastmo Ecolite blåtonet', 	600,   'Stk', 1);
INSERT INTO stock(ref, name, length, unit, amount  ) VALUES (15, 'Plastmo Ecolite blåtonet', 	360,   'Stk', 1);

-- Beslag og s
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (16, 'plastmo bundskruer 200 stk.', 		    1, 'pakke',    200);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (17, 'hulbånd 1x20 mm. 10 mtr.', 			1000, 'rulle',    1);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (18, 'universal 190 mm højre', 			    190, 'Stk',      1);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (19, 'universal 190 mm venstre', 			190, 'Stk',      1);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (20, '4,5 x 60 mm. skruer 200 stk.', 		60, 'pakke',    200);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (21, '4,0 x 50 mm. beslagskruer 250 stk', 	50, 'pakke',    250);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (22, 'bræddebolt 10 x 120 mm.', 			    120, 'Stk',      1);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (23, 'firkantskiver 40x40x11 mm.', 		    11, 'Stk',      1);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (24, '4,5 x 70 mm. Skruer 400 stk', 		    70, 'pakke',    400);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (25, '4,5 x 50 mm. Skruer 300 stk.', 		50, 'pakke',    300);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (26, 'stalddørsgreb 50x75', 				    75, 'sæt',      1);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (27, 't hængsel 390 mm', 					390, 'Stk',      1);
INSERT INTO stock(ref, name, length, unit, amount ) VALUES (28, 'vinkelbeslag 35', 					    35, 'Stk',      1);

-- User dummy
INSERT INTO users VALUES ('Gaspard Augé', 'mester@fog.dk', 'adgang', 'ADMIN');
INSERT INTO users VALUES ('Xavier de Rosnay', 'elev@fog.dk', 'adgang', 'SALESMAN');
select * from stock;
select * from users;