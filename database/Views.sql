CREATE OR REPLACE VIEW V_ENCHERE_NOT_DONE as
SELECT *
FROM ENCHERE
WHERE CURRENT_TIMESTAMP < timingStart+ interval '1 hour'*duration;

CREATE OR REPLACE VIEW V_WINNING_PRICE AS
select enchere.*,
       case
           when winnerPrice is null then enchere.startprice
           else winnerPrice
           end as price
from enchere
         left join (
    select idenchere,
           max(price) as winnerPrice
    from historique_enchere
    group by idenchere
) as t1 on enchere.idenchere = t1.idenchere;

-- allow us to get the winner per enchere
-- possible diso le second condition de join since the winner price is not unique
-- en réalité efa assurer any amin ny métier fa tokony unique io
CREATE OR REPLACE  VIEW V_WINNER_PER_ENCHERE AS
select idClient,wp.idenchere,wp.price
from historique_enchere
         join V_WINNING_PRICE wp on wp.idenchere = historique_enchere.idenchere and historique_enchere.price = wp.price;




CREATE OR REPLACE  VIEW V_MONEY_SPENT_PER_CLIENT as
select idClient,
       sum(price) as totalSpent
    from V_WINNER_PER_ENCHERE
group by idClient;


CREATE OR REPLACE VIEW V_SOLDE_PER_CLIENT as
select t1.idClient,
    t1.solde as totalIncome,
       t2.totalSpent,
       t1.solde-t2.totalSpent as solde
from
    (
        select idClient,
               sum(amount) as solde
        from transaction_compte
        where type_transaction=1
        group by idClient
    ) as t1
join V_MONEY_SPENT_PER_CLIENT t2 on t2.idClient = t1.idclient;


CREATE OR REPLACE VIEW V_ENCHERE_DETAILS as
select enchere.*,
       c.nom,
       c.prenom,
       c2.nomcategorie
from enchere
join client c on enchere.idlauncher = c.idclient
join categorie c2 on enchere.idcategorie = c2.idcategorie;


-- longest discussion , le plus populaire
CREATE OR REPLACE VIEW V_CATEGORIE_LONGEST_DISCUSSION as
select categorie.*,
       t1.stat
from categorie
         join
     (
         select idCategorie,
                count(idHistorique) as stat
         from enchere
                  join historique_enchere he on enchere.idenchere = he.idenchere
         group by idCategorie
     ) as t1 on t1.idCategorie = categorie.idcategorie
order by stat desc;

-- highest discussion
CREATE OR REPLACE VIEW V_CATEGORIE_HIGHEST_DISCUSSION as
select categorie.*,
       t1.stat
from categorie
         join
     (
         select  idCategorie ,
                 max(price) as stat
         from enchere
                  join historique_enchere he on enchere.idenchere = he.idenchere
         where CURRENT_TIMESTAMP >= timingStart + interval '1 hours'*duration
         group by idCategorie
     ) as t1 on t1.idCategorie = categorie.idcategorie
order by stat desc;


CREATE OR REPLACE VIEW V_CATEGORIE_POURCENTAGE as
select categorie.*,
       t1.stat
from categorie
         join
     (
         select enchere.idcategorie,
                (100.0*count(enchere.idcategorie))/(select count(idenchere) from enchere) as stat
         from enchere
         group by idcategorie
     ) as t1 on t1.idCategorie = categorie.idcategorie
order by stat desc;


CREATE OR REPLACE VIEW V_ENCHERE_STATUS AS
select enchere.*,
       case
           when enchere.idenchere in (select idenchere from v_enchere_not_done) then 0
            else 1
        end status,
        vwp.price
from enchere
    join v_winning_price vwp on enchere.idenchere = vwp.idenchere;