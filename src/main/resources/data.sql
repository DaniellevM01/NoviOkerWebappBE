INSERT INTO vacancies(vacancy_id, title, description, enabled)
VALUES
(1, 'Testautomatiseringsspecialist', 'Als testautomatiseringsspecialist ben je er op gebrand om onze klanten te helpen met het automatiseren van hun tests. We zoeken mensen die goed zelfstandig kunnen werken en hun hart volgen. ', true),
(2, 'Ontwikkelaar', 'Of je nou gelukkig wordt van mooie paginas in elkaar knutselen of liever in databases rond snuffelt, wij zijn op zoek naar jou! De wensen van onze klanten zijn zo divers dat we altijd iets passends voor je kunnen vinden', true),
(3, 'Open sollicitatie', 'Staat de vacature van je dromen er niet bij? Geen probleem, ook open sollicitaties zijn bij ons welkom. Geef op het sollicitatieformulier duidelijk aan wat je zoekt zodat we met jou mee kunnen denken', true);


-- IVM GenerateValue van spring deze naar boven zodat ze geen conflict hebben
-- Prefereerbaar via postgres sequence
INSERT INTO users(user_id, username, password, telephone_number, name, enabled)
VALUES
(9991, 'danielleoker@gmail.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , '0639861015' , 'Danielle Admin', true),
(9992, 'okerofferapplication@gmail.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , '0639861015' , 'Danielle OfferApplication', true),
(9993, 'okervacancyapplication@gmail.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , '0639861015' , 'Danielle VacancyApplication', true);

INSERT INTO authorities (username, authority)
VALUES
('danielleoker@gmail.com', 'CUSTOMER'),
('danielleoker@gmail.com', 'SOLLICTOR'),
('danielleoker@gmail.com', 'ADMIN'),
('okerofferapplication@gmail.com', 'CUSTOMER'),
('okerofferapplication@gmail.com', 'USER'),
('okervacancyapplication@gmail.com', 'SOLLICTOR'),
('okervacancyapplication@gmail.com', 'USER');


--INSERT INTO vacancy_applications(vacancy_application_id, user_id??, description, file, status)
--(9991,9993, 'hier moet een beschrijving', file, 'Ingediend');

--INSERT INTO offer_applications (offerApplication_id, user_id??, description, file, status)
--(9992,9992, 'hier moet een beschrijving', file, 'Ingediend');