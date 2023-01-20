select *
from v_enchere_status
where idenchere in
      (
          select idenchere from historique_enchere where idclient=1
      )


-- TODO Should be used when getting the historique so that there is more about the client who made the bidding
SELECT historique_enchere.*,
       c.nom,
       c.prenom
FROM historique_enchere
         join client c on historique_enchere.idclient = c.idclient
order by price desc