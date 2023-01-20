package group.enchere.controller;

import group.enchere.model.*;
import group.enchere.modelRepository.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admins")
public class Admincontroller {
    //    @RequestMapping("/admin")
    RefillPendingRepository rpPendingRepo;
    TransactionCompteRepository tRepo;
    AdminRepository adminRepository;
    AuthTokenRepository authTokenRepository;
    CategorieRepository repoCatego;
    EnchereRepository repoEnchere;

    public Admincontroller(EnchereRepository repo3,CategorieRepository categorieRepository,AdminRepository repo4, RefillPendingRepository repo, TransactionCompteRepository repo2, AuthTokenRepository repoToken) {
        tRepo = repo2;
        rpPendingRepo = repo;
        adminRepository = repo4;
        authTokenRepository = repoToken;
        repoCatego = categorieRepository;
        repoEnchere = repo3;
    }

    @GetMapping
    public List<RefillPending> getAllPending() {
        return rpPendingRepo.findAllNotAccepted();
    }

    @GetMapping("/choice/{choice}")
    public void decision(@PathVariable int choice, @RequestParam int idRefill) {
//        1 refus , 2 accept
        RefillPending rp = rpPendingRepo.findById(idRefill).orElse(null);
        rp.setValide(choice);
        rpPendingRepo.save(rp);

        TransactionCompte t = new TransactionCompte();
        t.setIdClient(rp.getIdClient());
        t.setAmount(rp.getVola());
        t.setType_transaction(1);
        tRepo.save(t);
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestBody Map<String, Object> payload) throws NoResultException {
        List<Admin> all = adminRepository.findAll();
        String identifiant = (String) payload.get("email");
        String mdp = (String) payload.get("password");
        for (Admin administrateur : all) {
            System.out.println(administrateur.getEmail()+administrateur.getPassword());
            if (administrateur.getEmail().equals(identifiant) && administrateur.getPassword().equals(mdp)) {
                // Creating the token
                AuthToken authToken = new AuthToken();
                authToken.generateToken(identifiant, mdp);

                // saving in its database
                authTokenRepository.save(authToken);

                // sending the token to the navigator in its header
//                HttpHeaders headers = new HttpHeaders();
//                headers.set("Bearer",authToken.getToken());

//                return new ResponseEntity<String>("Identifiants corrects",headers, HttpStatus.CREATED);
                return authToken.getToken();
            }
        }

        throw new NoResultException("Pas de client trouvé pour cet identifiant");
    }
@GetMapping("/categorisation")
    public EnchereDetails[] categorisationEnchere(){
        List<Enchere> enchereList = repoEnchere.getAllNotDone();
        EnchereDetails[] allEncheresNotDone = new EnchereDetails[enchereList.size()];
        for (int i=0;i<enchereList.size();i++)
            allEncheresNotDone[i] = repoEnchere.getDetails(enchereList.get(i).getIdEnchere());

        return allEncheresNotDone;
    }

}
