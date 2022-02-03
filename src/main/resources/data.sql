INSERT INTO vacancies(vacancy_id, title, description, enabled)
VALUES
(1, 'Testautomatiseringsspecialist', 'Als testautomatiseringsspecialist ben je er op gebrand om onze klanten te helpen met het automatiseren van hun tests. We zoeken mensen die goed zelfstandig kunnen werken en hun hart volgen. ', true),
(2, 'Ontwikkelaar', 'Of je nou gelukkig wordt van mooie paginas in elkaar knutselen of liever in databases rond snuffelt, wij zijn op zoek naar jou! De wensen van onze klanten zijn zo divers dat we altijd iets passends voor je kunnen vinden', true),
(3, 'Open sollicitatie', 'Staat de vacature van je dromen er niet bij? Geen probleem, ook open sollicitaties zijn bij ons welkom. Geef op het sollicitatieformulier duidelijk aan wat je zoekt zodat we met jou mee kunnen denken', true);

INSERT INTO users(user_id, username, password, telephone_number, name, enabled)
VALUES
(1, 'danielleoker@gmail.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , '0639861015' , 'Danielle Admin', true),
(2, 'okerofferapplication@gmail.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , '0639861015' , 'Danielle OfferApplication', true),
(3, 'okervacancyapplication@gmail.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , '0639861015' , 'Danielle VacancyApplication', true);

INSERT INTO authorities (user_id, authority)
VALUES
(1, 'ROLE_USER'),
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_USER');


--INSERT INTO vacancy_applications(vacancy_application_id, user_id??, description, file, status)

--INSERT INTO offer_applications (offerApplication_id, user_id??, description, file, status)