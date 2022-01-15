INSERT INTO vacancies(vacancy_id, title, description, enabled)
VALUES
(1, 'Testautomatiseringsspecialist', 'Als testautomatiseringsspecialist ben je er op gebrand om onze klanten te helpen met het automatiseren van hun tests. We zoeken mensen die goed zelfstandig kunnen werken en hun hart volgen. ', true),
(2, 'Ontwikkelaar', 'Of je nou gelukkig wordt van mooie paginas in elkaar knutselen of liever in databases rond snuffelt, wij zijn op zoek naar jou! De wensen van onze klanten zijn zo divers dat we altijd iets passends voor je kunnen vinden', true),
(3, 'Open sollicitatie', 'Staat de vacature van je dromen er niet bij? Geen probleem, ook open sollicitaties zijn bij ons welkom. Geef op het sollicitatieformulier duidelijk aan wat je zoekt zodat we met jou mee kunnen denken', true);

INSERT INTO functions(function_id, name, basic_salary)
VALUES
(1, 'junior testautomatiseringsspecialist', 2000),
(2, 'medior testautomatiseringsspecialist', 2500),
(3, 'senior testautomatiseringsspecialist', 2750),
(4, 'junior ontwikkelaar', 2250),
(5, 'medior ontwikkelaar', 2750),
(6, 'senior ontwikkelaar', 3000);

INSERT INTO users(user_id, username, password, telephone_number, name)
VALUES
(1, 'danielleoker@gmail.com', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , '0639861015' , 'Danielle Admin');

INSERT INTO authorities (user_id, authority)
VALUES
(1, 'admin', 'ROLE_USER'),
(1, 'admin', 'ROLE_ADMIN'),
(2, 'ROLE_USER');