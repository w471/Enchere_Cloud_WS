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
select c.idClient,
       case
        when sum(price) is null then 0
           else sum(price)
        end totalSpent
    from V_WINNER_PER_ENCHERE
right join client c on c.idclient = V_WINNER_PER_ENCHERE.idClient
group by c.idClient;

CREATE OR REPLACE VIEW V_INCOME_MONEY_PER_CLIENT as
SELECT t1.idclient,
       sum(amount) as solde
from (
    select c.idClient,
             case
                 when amount is null then 0
                 else amount
             end amount
      from (select * from transaction_compte where type_transaction = 1) as tt1
               right join client c on tt1.idclient = c.idclient
    ) as t1
group by idclient;

CREATE OR REPLACE VIEW V_SOLDE_PER_CLIENT as
select t1.idClient,
    t1.solde as totalIncome,
       t2.totalSpent,
       t1.solde-t2.totalSpent as solde
from V_INCOME_MONEY_PER_CLIENT as t1
join V_MONEY_SPENT_PER_CLIENT t2 on t2.idClient = t1.idclient;


CREATE OR REPLACE VIEW V_ENCHERE_DETAILS as
select enchere.*,
       c.nom,
       c.prenom,
       c2.nomcategorie
from enchere
join client c on enchere.idlauncher = c.idclient
join categorie c2 on enchere.idcategorie = c2.idcategorie;


CREATE OR REPLACE VIEW V_HISTORIQUE_ENCHERE_DETAILS AS
select historique_enchere.*,
       c.nom,
       c.prenom
    from historique_enchere
join client c on historique_enchere.idclient = c.idclient;

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
