package group.enchere.controller;

import group.enchere.model.*;
import group.enchere.modelRepository.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/clients")
public class Clientcontroler {
    RefillPendingRepository rpRepository;
    EnchereRepository enchereRepository;
    ClientRepository repoClient;
    AuthTokenRepository authTokenRepository;

    HistoriqueEnchereRepository hRepo;


    public Clientcontroler(HistoriqueEnchereRepository repoH,RefillPendingRepository repo, EnchereRepository repo1, ClientRepository repo2, AuthTokenRepository repo3) {
        rpRepository = repo;
        enchereRepository = repo1;
        repoClient = repo2;
        authTokenRepository = repo3;
        hRepo = repoH;
    }

    @GetMapping("/{idClient}")
    public Client getClient(@PathVariable("idClient") int idClient){
         return repoClient.findById(idClient).orElse(null);
    }

    @PostMapping("/refill")
    public void refill(@RequestBody RefillPending refil) {
        rpRepository.save(refil);
    }

    @GetMapping("/getSolde")
    public ClientFinanceStatus getSolde(@RequestParam int idPersonne){
        return repoClient.getSolde(idPersonne);
    }

    @PostMapping("/bid")
    public void bid(@RequestBody HistoriqueEnchere historiqueEnchere) throws Exception {
        ClientFinanceStatus clientSolde = repoClient.getSolde(historiqueEnchere.getIdClient());

//        what is the floor price
         Enchere e = enchereRepository.findById(historiqueEnchere.getIdEnchere()).orElse(null);
        if(historiqueEnchere.getPrice()<e.getStartPrice())
            throw new Exception("Prix inferieur au prix minimum");

//        do you even have the amount you are saying
        if(historiqueEnchere.getPrice()>clientSolde.getSolde())
            throw new Exception("Solde insuffisant pour l offre");

//        check = the money he is biding> previous
        List<HistoriqueEnchere> historiqueEncheres =  enchereRepository.getHistorique(historiqueEnchere.getIdEnchere());
//        if an enchere has no historique => useless to check
        if(historiqueEncheres.size()>0){
//            index 0 because the max is already on the first index
            if(historiqueEncheres.get(0).getPrice()>=historiqueEnchere.getPrice())
                throw new Exception("Offre inferieur a l offre actuelle");
        }


//        lock the money by inserting his offre as the current max
        hRepo.save(historiqueEnchere);
//        double compte = historiqueEnchere.get

    }

    @GetMapping("/getPossesed")
    public List<EnchereStatus> getPossessedEnchere(@RequestParam int idPersonne) {
        return enchereRepository.allPossesed(idPersonne);
    }

    @GetMapping("/getInvolved")
    public List<EnchereStatus> getInvolved(@RequestParam int idPersonne){
        return enchereRepository.getInvolved(idPersonne);
    }

//    allow us to get the list of auction not done yet + not owned => auction list on which he can bid
    @GetMapping("/list")
    public List<EnchereStatus> getEnchereBiddable(@RequestParam int idPersonne){
        return repoClient.getBiddable(idPersonne);
    }

    @GetMapping("/lists")
    public List<EnchereStatus> getEnchereBiddable(){
        return repoClient.getGeneralBiddable();
    }

    @PostMapping("/signIn")
    public void SignIn(@RequestBody Client client){
        repoClient.save(client);
    }

    @PostMapping("/checkLogin")
    public Object[] checkLogin(@RequestBody Map<String, Object> payload) throws NoResultException {
        Object[] result = new Object[2];
        List<Client> all = repoClient.findAll();
        String identifiant = (String) payload.get("email");
        String mdp = (String) payload.get("password");
        for (Client administrateur : all) {
            if (administrateur.getEmail().equals(identifiant) && administrateur.getPassword().equals(mdp)) {
                // Creating the token
                AuthToken authToken = new AuthToken();
                authToken.generateToken(identifiant, mdp);

                // saving in its database
                authTokenRepository.save(authToken);

                result[0] =authToken.getToken();
                result[1] = administrateur.getIdAdmin();
                return result;
            }
        }
        throw new NoResultException("Pas de client trouvé pour cet identifiant");
    }

    @PostMapping("/chargeAccount")
    public void chargeAccount(@RequestBody RefillPending pending){
        rpRepository.save(pending);
    }
}
