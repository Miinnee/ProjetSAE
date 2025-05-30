package factoryCIUP;

import factoryCIUP.managers.FenetreManager;
import factoryCIUP.personnes.Directeur;
import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.structuresTypes.Maison;
import factoryCIUP.structures.structuresTypes.MaisonInternationnal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FactoryCIUP {
    static MaisonInternationnal maisonInternationnal;


    public static void main(String[] args) {
        //48.70973793841098, 2.1549976721050284
        maisonInternationnal = new MaisonInternationnal(48.70973793841098, 2.1549976721050284, new Directeur("Gerard", "Boulet"), Nationnalite.NEUTRE, MaisonInternationnal.class.getName(), new ArrayList<>());
    
        new FenetreManager();
        //TODO : Pour construction pour les prochaines maisons

        /* Maison maison = new Maison("Maison de Test", Nationnalite.JAPONAIS, "Directeur de Maisons", 550, 551, 6, "EtudiantTest", "EtudiantTest2");
        maison.ajoutMaison();*/

        maisonInternationnal.getStructureList().stream().filter(structure -> structure instanceof Maison).forEach(structure -> {
            System.out.println(" ");
            System.out.println(structure.getNomMaison());
            System.out.println(structure.getDirecteur().getPrenom() + " " + structure.getDirecteur().getNom());
            structure.getEtudiantLogeant().forEach(etudiant -> {
                System.out.println(etudiant.getPrenom() + " " + etudiant.getNom() + " -> " + etudiant.getFormation());
            });
        });
    }

    public static MaisonInternationnal getMaisonInternationnal() {
        return maisonInternationnal;
    }

}
