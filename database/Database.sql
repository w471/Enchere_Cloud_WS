-- CREATE DATABASE ENCHERE;

-- client and admin extends the same class

CREATE SEQUENCE S_CLIENT_ID start with 1 increment 1;
CREATE TABLE CLIENT(
                       idClient integer primary key default nextVal('S_CLIENT_ID'),
                       nom varchar(25),
                       prenom varchar(25),
                       email varchar(25) unique not null,
                       password varchar(25) not null
);
INSERT INTO CLIENT VALUES
                       (default,'jean@gmail.com','mdp','jean','jean_prenom'),
                       (default,'rakoto@gmail.com','rakoto','rakoto','rakoto_prenom'),
                            (default,'rabe@gmail.com','rabe','rabe','rabe_prenom'),
                            (default,'rasoa@gmail.com','rasoa','rasoa','rasoa_prenom'),
                            (default,'ranja@gmail.com','ranja','ranja','ranja_prenom'),
                            (default,'doe@gmail.com','doe','doe','doe_prenom'),
                            (default,'beck@gmail.com','beck','beck','beck_prenom'),
                            (default,'maria@gmail.com','maria','maria','maria_prenom');


CREATE SEQUENCE S_ADMIN_ID start with 1 increment 1;
CREATE TABLE ADMIN(
                      idAdmin integer primary key default nextVal('S_ADMIN_ID'),
                      email varchar(25) unique not null,
                      password varchar(25) not null
);
INSERT INTO ADMIN VALUES (default,'admin@gmail.com','mdp');

CREATE SEQUENCE S_AUTH_TOKEN_ID start 1 increment 1;
CREATE TABLE AuthToken(
                          id integer primary key default nextVal('S_AUTH_TOKEN_ID'),
                          token varchar(200),
                          dateExpiration date
);

CREATE SEQUENCE S_CATEGORIE_ID start with 1 increment 1;
CREATE TABLE CATEGORIE(
                          idCategorie integer primary key default nextVal('S_CATEGORIE_ID'),
                          nomCategorie varchar(25)
);
INSERT INTO CATEGORIE VALUES
                          (default,'informatique'),
                          (default,'voiture'),
                          (default,'bijoux'),
                          (default,'tissu'),
                          (default,'immeuble'),
                          (default,'meuble');
;

CREATE SEQUENCE S_ENCHERE_ID start with 1 increment 1;
CREATE TABLE ENCHERE(
                        idEnchere integer primary key default nextVal('S_ENCHERE_ID'),
                        timingStart timestamp not null default now(),
                        idLauncher integer references CLIENT(idClient),
                        description text,
                        idCategorie integer references CATEGORIE(idCategorie) not null,
                        startPrice double precision not null check ( startPrice>=0 ) default 0,
                        duration integer not null check ( duration>=0 ) default 0,
                        commission double precision check ( commission>=0 ) default 0,
                        image text
);
INSERT INTO ENCHERE(timingStart,idLauncher,description,idCategorie,startPrice,duration,commission,image) VALUES
(default,1,'ordinateur en tres bon etat',1,2000,3,0.2,null),
(default,2,'Golf 5 GTI neuve',2,20000,5,0.4,null),
(default,3,'collier diamant',3,300000,3,0.1,null),
(default,4,'robe',4,1000,2,0.2,null),
(default,5,'Appartement  a andoharanofotsy',5,100000,8,0.1,null),
(default,6,'Canape',6,1500,6,0.5,null),
(default,7,'iphone 14',1,10000,1,0.3,null),
(default,8,'Table',6,2000,2,0.2,null);

-- CREATE SEQUENCE S_ENCHERE_DONE_ID start with 1 increment 1;
-- CREATE TABLE ENCHERE_DONE(
--     idEnchereDone integer primary key default nextVal('S_ENCHERE_DONE_ID'),
--     idEnchere integer references ENCHERE(idEnchere),
--     priceClose double precision not null check ( priceClose>=0 ),
--     idCloser integer references CLIENT(idClient)
-- );

CREATE SEQUENCE S_HISTORIQUE_ENCHERE_ID start with 1 increment 1;
CREATE TABLE HISTORIQUE_ENCHERE(
                                   idHistorique integer primary key default nextVal('S_HISTORIQUE_ENCHERE_ID'),
                                   idEnchere integer references ENCHERE(idEnchere),
                                   idClient integer,
                                   price double precision not null check ( price >= 0 ) default 0
);
INSERT INTO HISTORIQUE_ENCHERE(idEnchere,idClient,price) VALUES
                                                             (2,4,40000),
                                                             (5,1,200000);

CREATE SEQUENCE S_TRANSACTION_COMPTE_ID start with 1 increment 1;
-- there is 2 ways
-- this(typ 0 retrait , type 1 : income , type 2 : block )
CREATE TABLE TRANSACTION_COMPTE(
       idTransaction integer primary key default nextVal('S_TRANSACTION_COMPTE_ID'),
       idClient integer references  CLIENT(idClient),
       amount double precision not null check ( amount>0 ),
       type_transaction integer  not null
);
INSERT INTO TRANSACTION_COMPTE(idClient,amount,type_transaction) VALUES
                                                                     (4,40000,1),
                                                                     (1,200000,0);

--
CREATE SEQUENCE S_REFILL_PENDING_ID start with 1 increment 1;
CREATE TABLE REFILL_PENDING(
    idRefill integer primary key default nextVal('S_REFILL_PENDING_ID'),
    idClient integer references CLIENT(idClient),
    vola double precision not null check ( vola>=0 ),
--     0 pending , 1 refus , 2 accept
    valide integer not null default 0
);