import java.util.List;
import java.util.Locale;

public class Agent {
    String nom;
    String prenom;
    String profession;
    String mdp;
    String unique;
    String IDimage;
    List<String> materiaux;

    public Agent(String nom, String prenom, String profession, String mdp, List<String> materiaux) {
        this.profession = profession;
        this.mdp = mdp;
        this.materiaux = materiaux;

        String maj = prenom.substring(0,1).toUpperCase(Locale.ROOT);
        this.prenom = maj + prenom.substring(1).toLowerCase();
        maj = nom.substring(0,1).toUpperCase(Locale.ROOT);
        this.nom = maj + nom.substring(1).toLowerCase();

        IDimage = this.nom + ".png";
        unique = this.prenom.substring(0,1).toLowerCase() + this.nom ;
    }
}
