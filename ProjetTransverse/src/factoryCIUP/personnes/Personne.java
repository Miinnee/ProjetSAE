package factoryCIUP.personnes;

import factoryCIUP.structures.structuresTypes.Maison;

public abstract class Personne {
    String nom;
    String prenom;
    String anneeEtude;
    Maison maison;


    //Etudiant
    public Personne(String nom, String prenom, String anneeEtude) {
        this.nom = nom;
        this.prenom = prenom;
        this.anneeEtude = anneeEtude;
        this.maison = null;
    }

    //Directeur
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.anneeEtude = null;
        this.maison = null;
    }



    public void attributionMaison(Maison maison) {
        this.maison = maison;
    }
}
