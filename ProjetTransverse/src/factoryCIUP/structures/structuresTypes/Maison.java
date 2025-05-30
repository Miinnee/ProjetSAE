package factoryCIUP.structures.structuresTypes;

import factoryCIUP.FactoryCIUP;
import factoryCIUP.personnes.Directeur;
import factoryCIUP.personnes.Etudiant;
import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.Structure;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Maison extends Structure {

    List<Etudiant> etudiants;
    String descriptionMaison;

    public Maison(String nomMaison, Nationnalite nationnalite, Directeur directeur, double nGPS, double eGPS, int nbChambre, List<Etudiant> etudiants) {
        super(nomMaison, nationnalite, directeur, nGPS, eGPS, nbChambre, etudiants);
        this.etudiants = etudiants;

        enregistrementDesEtudiant();
    }

    @Override
    public JPanel panel(JPanel parentPanel) {
        JPanel panel = new JPanel(new CardLayout()); // Correction ici
        JLabel j = new JLabel("test");
        panel.add(j);

        return panel;
    }

    public Maison(String nomMaison, Nationnalite nationnalite, Directeur directeur, double nGPS, double eGPS, int nbChambre, Etudiant... etudiants) {
        super(nomMaison, nationnalite, directeur, nGPS, eGPS, nbChambre, etudiants);
        this.etudiants = List.of(etudiants);

        enregistrementDesEtudiant();
    }

    public void enregistrementDesEtudiant(){
        etudiants.forEach(etudiant -> etudiant.setMaison(this));
    }
}
