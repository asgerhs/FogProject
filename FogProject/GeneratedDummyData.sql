-- Woods
INSERT INTO stock VALUES ('1000', '25x200 mm. trykimp. Br�dt', 3600, 1, 'stk', 17983);
INSERT INTO stock VALUES ('1001', '25x200 mm. trykimp. Br�dt', 5400, 1, 'stk', 26973);
INSERT INTO stock VALUES ('1002', '25x125mm. trykimp. Br�dt', 3600, 1, 'stk', 10783);
INSERT INTO stock VALUES ('1003', '25x125mm. trykimp. Br�dt', 5400, 1, 'stk', 16173);
INSERT INTO stock VALUES ('1004', '38x73 mm. L�gte ubh.', 4200, 1, 'stk', 2499);
INSERT INTO stock VALUES ('1005', '45x95 mm. Reglar ub.', 2400, 1, 'stk', 3108);
INSERT INTO stock VALUES ('1006', '45x95 mm. Reglar ub.', 2700, 1, 'stk', 3496);
INSERT INTO stock VALUES ('1007', '45x195 mm. sp�rtr� ubh.', 4800, 1, 'stk', 23016);
INSERT INTO stock VALUES ('1008', '45x195 mm. sp�rtr� ubh.', 6000, 1, 'stk', 28770);
INSERT INTO stock VALUES ('1009', '97x97 mm. trykimp. Stolpe', 3000, 1, 'stk', 11385);
INSERT INTO stock VALUES ('1010', '19x100 mm. trykimp. Br�dt', 2100, 1, 'stk', 1250);
INSERT INTO stock VALUES ('1011', '19x100 mm. trykimp. Br�dt', 3600, 1, 'stk', 2143);
INSERT INTO stock VALUES ('1012', '19x100 mm. trykimp. Br�dt', 5400, 1, 'stk', 3213);
INSERT INTO stock VALUES ('1013', 'Plastmo Ecolite bl�tonet', 3600, 1, 'stk', 11900);
INSERT INTO stock VALUES ('1014', 'Plastmo Ecolite bl�tonet', 6000, 1, 'stk', 23000);
INSERT INTO stock VALUES ('1015', '38x73 mm. tagl�gte T1', 5400, 1, 'stk', 9153);
INSERT INTO stock VALUES ('1016', '38x73 mm. tagl�gte T1', 4200, 1, 'stk', 7119);
INSERT INTO stock VALUES ('1030', 'Plastmo bundskruer 200 stk.', 0, 200, 'pakke', 39500);
INSERT INTO stock VALUES ('1031', 'Hulb�nd 1x20 mm. 10 mtr.', 1000, 1, 'rulle', 20900);
INSERT INTO stock VALUES ('1032-r', 'Universal 190 mm h�jre', 190, 1, 'stk', 1590);
INSERT INTO stock VALUES ('1032-l', 'Universal 190 mm venstre', 190, 1, 'stk', 1590);
INSERT INTO stock VALUES ('1034', '4,5x60 mm. skruer 200 stk.', 60, 200, 'pakke', 15900);
INSERT INTO stock VALUES ('1035', '4,0x50 mm. skruer 250 stk.', 50, 250, 'pakke', 22900);
INSERT INTO stock VALUES ('1036', 'Br�ddebolt 10x120 mm.', 120, 1, 'stk', 1953);
INSERT INTO stock VALUES ('1037', 'Firkantskiver 40x40x11mm', 11, 1, 'stk', 1011);
INSERT INTO stock VALUES ('1038', '4,5x70 mm. Skruer 400 stk.', 70, 400, 'pakke', 19900);
INSERT INTO stock VALUES ('1039', '4,5x50 mm. Skruer 300 stk.', 50, 300, 'pakke', 9595);
INSERT INTO stock VALUES ('1040', 'Staldd�rsgreb 50x75 mm', 75, 1, 's�t', 14320);
INSERT INTO stock VALUES ('1041', 'T h�ngsel 390 mm', 390, 1, 'stk', 6396);
INSERT INTO stock VALUES ('1042', 'Vinkelbeslag 35 mm', 35, 1, 'stk', 2495);
INSERT INTO stock VALUES ('1043', '5,0 x 40 mm. beslagskruer 250 stk.', 40, 250, 'pakke', 21000);
INSERT INTO stock VALUES ('1044', '5,0 x 100 mm. skruer 100 stk', 100, 100, 'pakke', 18900);
INSERT INTO stock VALUES ('1060', 'B & C Dobbelt -s sort', 0, 300, 'stk', 589);
INSERT INTO stock VALUES ('1061', 'B & C Rygsten sort', 0, 1, 'stk', 4500);
INSERT INTO stock VALUES ('1062', 'B & C Topl�gte holder', 0, 1, 'stk', 1995);
INSERT INTO stock VALUES ('1063', 'B & C Rygstensbeslag', 0, 1, 'stk', 1010);
INSERT INTO stock VALUES ('1064', 'B & C Tagstens bindere & nakkekroge', 0, 1, 'pakke', 81900);


-- Categories
INSERT INTO categories(name) VALUES ('Understernbr�dder');
INSERT INTO categories(name) VALUES ('Oversternbr�dder');
INSERT INTO categories(name) VALUES ('L�gter');
INSERT INTO categories(name) VALUES ('Reglar');
INSERT INTO categories(name) VALUES ('Sp�rtr�');
INSERT INTO categories(name) VALUES ('Rem');
INSERT INTO categories(name) VALUES ('Stolpe');
INSERT INTO categories(name) VALUES ('Br�dt');
INSERT INTO categories(name) VALUES ('Vandbr�t');
INSERT INTO categories(name) VALUES ('Tagplader');
INSERT INTO categories(name) VALUES ('Skruer');
INSERT INTO categories(name) VALUES ('Hulb�nd');
INSERT INTO categories(name) VALUES ('Beslag');
INSERT INTO categories(name) VALUES ('Bolt');
INSERT INTO categories(name) VALUES ('Skiver');
INSERT INTO categories(name) VALUES ('ShedMisc');
INSERT INTO categories(name) VALUES ('Tagpakke');


-- Categories - Stock link
INSERT INTO stockToCategory VALUES ('1000',1);
INSERT INTO stockToCategory VALUES ('1001',1);
INSERT INTO stockToCategory VALUES ('1002',2);
INSERT INTO stockToCategory VALUES ('1003',2);
INSERT INTO stockToCategory VALUES ('1004',3);
INSERT INTO stockToCategory VALUES ('1005',4);
INSERT INTO stockToCategory VALUES ('1006',4);
INSERT INTO stockToCategory VALUES ('1007',5);
INSERT INTO stockToCategory VALUES ('1008',5);
INSERT INTO stockToCategory VALUES ('1007',6);
INSERT INTO stockToCategory VALUES ('1008',6);
INSERT INTO stockToCategory VALUES ('1009',7);
INSERT INTO stockToCategory VALUES ('1010',8);
INSERT INTO stockToCategory VALUES ('1011',8);
INSERT INTO stockToCategory VALUES ('1012',8);
INSERT INTO stockToCategory VALUES ('1010',9);
INSERT INTO stockToCategory VALUES ('1011',9);
INSERT INTO stockToCategory VALUES ('1012',9);
INSERT INTO stockToCategory VALUES ('1013',10);
INSERT INTO stockToCategory VALUES ('1014',10);
INSERT INTO stockToCategory VALUES ('1015',3);
INSERT INTO stockToCategory VALUES ('1016',3);
INSERT INTO stockToCategory VALUES ('1030',11);
INSERT INTO stockToCategory VALUES ('1034',11);
INSERT INTO stockToCategory VALUES ('1035',11);
INSERT INTO stockToCategory VALUES ('1038',11);
INSERT INTO stockToCategory VALUES ('1039',11);
INSERT INTO stockToCategory VALUES ('1031',12);
INSERT INTO stockToCategory VALUES ('1032-r',13);
INSERT INTO stockToCategory VALUES ('1032-l',13);
INSERT INTO stockToCategory VALUES ('1042',13);
INSERT INTO stockToCategory VALUES ('1036',14);
INSERT INTO stockToCategory VALUES ('1037',15);
INSERT INTO stockToCategory VALUES ('1040',16);
INSERT INTO stockToCategory VALUES ('1041',16);
INSERT INTO stockToCategory VALUES ('1004',16);
INSERT INTO stockToCategory VALUES ('1043',11);
INSERT INTO stockToCategory VALUES ('1044',11);
INSERT INTO stockToCategory VALUES ('1060',17);
INSERT INTO stockToCategory VALUES ('1061',17);
INSERT INTO stockToCategory VALUES ('1062',17);
INSERT INTO stockToCategory VALUES ('1063',17);
INSERT INTO stockToCategory VALUES ('1064',17);


-- Users
INSERT INTO users VALUES ('Leon', 'Leon@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Jesus', 'Jesus@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Mistie', 'Mistie@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Vernon', 'Vernon@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Theresa', 'Theresa@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Ruthie', 'Ruthie@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Joesph', 'Joesph@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Dalton', 'Dalton@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Keena', 'Keena@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Edie', 'Edie@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Joe', 'Joe@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Margareta', 'Margareta@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Lynetta', 'Lynetta@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Vincent', 'Vincent@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Dorthy', 'Dorthy@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Danial', 'Danial@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Son', 'Son@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Arden', 'Arden@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Dorine', 'Dorine@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Harriett', 'Harriett@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Roy', 'Roy@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Noble', 'Noble@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Cary', 'Cary@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Refugio', 'Refugio@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Isidro', 'Isidro@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Floria', 'Floria@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Renay', 'Renay@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Darron', 'Darron@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Adelina', 'Adelina@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Chia', 'Chia@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Bobby', 'Bobby@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Elois', 'Elois@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Pete', 'Pete@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Frederick', 'Frederick@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Daniel', 'Daniel@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Criselda', 'Criselda@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Alycia', 'Alycia@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Wilber', 'Wilber@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Audra', 'Audra@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Barton', 'Barton@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Eli', 'Eli@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Duane', 'Duane@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Tracey', 'Tracey@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Rima', 'Rima@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Tennille', 'Tennille@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Lelah', 'Lelah@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Tyrone', 'Tyrone@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Lizeth', 'Lizeth@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Nicolas', 'Nicolas@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Monroe', 'Monroe@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Emmitt', 'Emmitt@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Erich', 'Erich@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Elijah', 'Elijah@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Lamont', 'Lamont@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Marcel', 'Marcel@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Kim', 'Kim@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Kiana', 'Kiana@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Daron', 'Daron@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Jutta', 'Jutta@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Gary', 'Gary@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Mariette', 'Mariette@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Leland', 'Leland@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Stacey', 'Stacey@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Yvonne', 'Yvonne@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Carly', 'Carly@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Sharyl', 'Sharyl@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Janessa', 'Janessa@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Jamel', 'Jamel@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Marlen', 'Marlen@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Gus', 'Gus@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Ross', 'Ross@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Chung', 'Chung@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Timmy', 'Timmy@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Lilli', 'Lilli@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Aron', 'Aron@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Qiana', 'Qiana@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Brenna', 'Brenna@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Madge', 'Madge@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Clotilde', 'Clotilde@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Kit', 'Kit@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Vickey', 'Vickey@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Jaunita', 'Jaunita@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Enoch', 'Enoch@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Brain', 'Brain@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Kermit', 'Kermit@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Boyce', 'Boyce@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Krishna', 'Krishna@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Mitchell', 'Mitchell@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Yetta', 'Yetta@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Lucila', 'Lucila@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Gearldine', 'Gearldine@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Fredricka', 'Fredricka@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Carey', 'Carey@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Joi', 'Joi@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Tyree', 'Tyree@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Vanessa', 'Vanessa@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Iona', 'Iona@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Traci', 'Traci@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Hunter', 'Hunter@somewhere.dk', '1234', 'SALESMAN');
INSERT INTO users VALUES ('Bronwyn', 'Bronwyn@somewhere.dk', '1234', 'SALESMAN');


-- Requests
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (733, 733, 130, 130, 'not flat', 30, 'Cordell', '080 Fabian Port', 'Cordells Zip', '018.496.1273', 'Cordell@somewhere.com', 'This is a test for Cordell');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (650, 650, 173, 173, 'not flat', 30, 'Lakisha', '6518 Hilpert Lodge', 'Lakishas Zip', '(004) 658-6143', 'Lakisha@somewhere.com', 'This is a test for Lakisha');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (695, 695, 172, 172, 'not flat', 30, 'Orville', '2300 Nitzsche Stream', 'Orvilles Zip', '984-520-2334', 'Orville@somewhere.com', 'This is a test for Orville');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (754, 754, 106, 106, 'not flat', 30, 'Rolland', '651 Shirely Mall', 'Rollands Zip', '586-674-4780', 'Rolland@somewhere.com', 'This is a test for Rolland');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (536, 536, 102, 102, 'not flat', 30, 'Gregory', '92420 Lebsack Crossing', 'Gregorys Zip', '951.584.7307', 'Gregory@somewhere.com', 'This is a test for Gregory');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (563, 563, 101, 101, 'not flat', 30, 'Jacqulyn', '9946 Jaimee Trafficway', 'Jacqulyns Zip', '1-807-792-0010', 'Jacqulyn@somewhere.com', 'This is a test for Jacqulyn');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (717, 717, 128, 128, 'not flat', 30, 'Pauline', '74246 Jose Stravenue', 'Paulines Zip', '(091) 760-0946', 'Pauline@somewhere.com', 'This is a test for Pauline');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (678, 678, 183, 183, 'not flat', 30, 'Elias', '74857 Lesch Well', 'Eliass Zip', '552-966-9969', 'Elias@somewhere.com', 'This is a test for Elias');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (619, 619, 108, 108, 'not flat', 30, 'Tona', '25918 Serina Light', 'Tonas Zip', '639-341-9356', 'Tona@somewhere.com', 'This is a test for Tona');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (726, 726, 118, 118, 'not flat', 30, 'Noel', '77262 Odis Vista', 'Noels Zip', '933-504-1802', 'Noel@somewhere.com', 'This is a test for Noel');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (598, 598, 121, 121, 'not flat', 30, 'Amos', '4258 Coral Fort', 'Amoss Zip', '1-862-823-3067', 'Amos@somewhere.com', 'This is a test for Amos');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (727, 727, 138, 138, 'not flat', 30, 'Meryl', '943 Helen Roads', 'Meryls Zip', '175.320.0001', 'Meryl@somewhere.com', 'This is a test for Meryl');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (615, 615, 166, 166, 'not flat', 30, 'Ling', '6463 Schuster Pike', 'Lings Zip', '1-649-637-3048', 'Ling@somewhere.com', 'This is a test for Ling');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (621, 621, 108, 108, 'not flat', 30, 'Maxine', '26637 Williamson Shoal', 'Maxines Zip', '1-522-978-4991', 'Maxine@somewhere.com', 'This is a test for Maxine');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (653, 653, 174, 174, 'not flat', 30, 'Tonie', '23349 Mikel Mountain', 'Tonies Zip', '740.930.6809', 'Tonie@somewhere.com', 'This is a test for Tonie');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (565, 565, 192, 192, 'not flat', 30, 'Cristopher', '544 Dalton Viaduct', 'Cristophers Zip', '(129) 358-7451', 'Cristopher@somewhere.com', 'This is a test for Cristopher');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (511, 511, 111, 111, 'not flat', 30, 'Reyes', '21752 Yong Loaf', 'Reyess Zip', '516.526.7699', 'Reyes@somewhere.com', 'This is a test for Reyes');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (745, 745, 162, 162, 'not flat', 30, 'Cleta', '291 Jamison Junction', 'Cletas Zip', '608.311.5564', 'Cleta@somewhere.com', 'This is a test for Cleta');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (737, 737, 127, 127, 'not flat', 30, 'Ceola', '1574 Marks Crossing', 'Ceolas Zip', '247-720-6496', 'Ceola@somewhere.com', 'This is a test for Ceola');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (751, 751, 201, 201, 'not flat', 30, 'Oswaldo', '5964 King Row', 'Oswaldos Zip', '1-805-348-1104', 'Oswaldo@somewhere.com', 'This is a test for Oswaldo');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (588, 588, 112, 112, 'not flat', 30, 'Cristy', '152 Maggio Mountain', 'Cristys Zip', '421.913.2047', 'Cristy@somewhere.com', 'This is a test for Cristy');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (722, 722, 157, 157, 'not flat', 30, 'Elida', '9798 Murphy Well', 'Elidas Zip', '(303) 489-0550', 'Elida@somewhere.com', 'This is a test for Elida');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (542, 542, 118, 118, 'not flat', 30, 'Verna', '4821 Roob Ridge', 'Vernas Zip', '516.505.6402', 'Verna@somewhere.com', 'This is a test for Verna');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (583, 583, 194, 194, 'not flat', 30, 'Zachariah', '1158 Boyle Spring', 'Zachariahs Zip', '(369) 658-1171', 'Zachariah@somewhere.com', 'This is a test for Zachariah');
INSERT INTO requests(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (627, 627, 176, 176, 'not flat', 30, 'Jewell', '9994 Wendell Camp', 'Jewells Zip', '769.966.4190', 'Jewell@somewhere.com', 'This is a test for Jewell');
