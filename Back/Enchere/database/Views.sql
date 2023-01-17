CREATE OR REPLACE VIEW V_ENCHERE_NOT_DONE as
SELECT *
FROM ENCHERE
WHERE CURRENT_TIMESTAMP < timingStart+ interval '1 hour'*duration;


CREATE OR REPLACE VIEW V_ENCHERE_DETAILS as
select enchere.*,
       c.nom,
       c.prenom,
       c2.nomcategorie
from enchere
join client c on enchere.idlauncher = c.idclient
join categorie c2 on enchere.idcategorie = c2.idcategorie;


alter table client add column  prenom varchar(25)