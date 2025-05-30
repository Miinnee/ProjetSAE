package factoryCIUP.structures.structuresTypes;

import factoryCIUP.managers.FenetreManager;
import factoryCIUP.personnes.Directeur;
import factoryCIUP.personnes.Etudiant;
import factoryCIUP.personnes.Formation;
import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.Structure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MaisonInternationnal extends Structure {
    String nomMaison;
    Directeur directeur;
    Nationnalite nationnalite;

    double eGPS;
    double nGPS;

    List<Structure> structureList;

    @Override
    public JPanel panel(JPanel parentPanel) {
        JPanel panel = new JPanel(new CardLayout()); // Correction ici
        JLabel j = new JLabel("test");
        panel.add(j);

        return panel;
    }
    public MaisonInternationnal(double eGPS, double nGPS, Directeur directeur, Nationnalite nationnalite, String nomMaison, List<Structure> structuresList) {
        super(eGPS, nGPS, directeur, nationnalite, nomMaison, structuresList);
        this.structureList = structuresList;
        this.nomMaison = nomMaison;
        this.directeur = directeur;
        this.nationnalite = nationnalite;
        this.eGPS = eGPS;
        this.nGPS = nGPS;

        enregistrementMaisonInitial();

        RestoU restoU = new RestoU(Nationnalite.NEUTRE, "Resto Universitaire", 2.66666, 2.66666);
        structureList.add(restoU);
        restoU.carteDuJour();

        Cafeteria cafeteria = new Cafeteria(Nationnalite.NEUTRE, "Cafetearia des Mondes", 2.66, 2.66);
        structureList.add(cafeteria);
        cafeteria.carteDuJour();

        structureList.add(new Theatre(Nationnalite.NEUTRE, "Théâtre internationnal", 3.14, 6.30));
        structureList.add(new Piscine(Nationnalite.NEUTRE, "Piscine Internationnal", 0.88888,0.333333, 1000, "amenage pour les handicapes", 4, 2.70, "De 05:00 a 20:00", "De 08:00 a 18:00"));
    }

    public void enregistrementMaisonInitial(){


        structureList.add(new Maison("Maison France", Nationnalite.FRANCAIS, new Directeur("Jean", "Pascal"),48.70973793841098, 2.1549976721050284, 4,
                new Etudiant("Duchamp", "Eliot", Formation.STAPS, "2023"),
                new Etudiant("VivitLair", "Maxence", Formation.STAPS, "2023"),
                new Etudiant("Peneloppe", "Eliot", Formation.STAPS, "2023"),
                new Etudiant("Duchamp", "Valerie", Formation.STAPS, "2023")));


        structureList.add(new Maison(
                "Maison Allemande",
                Nationnalite.ALLEMAND,
                new Directeur("Pierre", "Dupont"),
                48.70973793841098,
                2.1549976721050284,
                4,
                new Etudiant("Martin", "Lucie", Formation.BUT, "2020"),
                new Etudiant("Bernard", "Julien", Formation.BUT, "2022")
        ));

        structureList.add(new Maison(
                "Maison Belge",
                Nationnalite.BELGE,
                new Directeur("Sophie", "Lemoine"),
                48.70973793841098,
                2.1549976721050284,
                4,
                new Etudiant("Girard", "Théo", Formation.BTS, "2025"),
                new Etudiant("Moreau", "Léa", Formation.BTS, "2024"),
                new Etudiant("Roux", "Nathan", Formation.BTS, "2022"),
                new Etudiant("Fontaine", "Chloé", Formation.BTS, "2021")
        ));


        structureList.add(new Maison(
                "Maison Japonais",
                Nationnalite.JAPONAIS,
                new Directeur("Antoine", "Blanc"),
                48.70973793841098,
                2.1549976721050284,
                4,
                new Etudiant("Lemoine", "Sarah", Formation.MASTER, "2021"),
                new Etudiant("Dubois", "Alexis", Formation.MASTER, "2020"),
                new Etudiant("Mercier", "Manon", Formation.MASTER, "2023"),
                new Etudiant("Garnier", "Hugo", Formation.MASTER, "2023")
        ));

        structureList.forEach(structure -> {
            if(structure instanceof Maison maison){
                Directeur directeurMaison = maison.getDirecteur();
                directeurMaison.attributionMaison(maison);

                List<Etudiant> etudiants = maison.getEtudiantLogeant();
                etudiants.forEach(etudiant -> {
                    etudiant.attributionMaison(maison);
                });
            }

        });
    }


    public void addMaisonToInternationnal(Structure maison){
        this.getStructureList().add(maison);
    }

    public String getNomMaison() {
        return nomMaison;
    }

    public void setNomMaison(String nomMaison) {
        this.nomMaison = nomMaison;
    }

    @Override
    public Directeur getDirecteur() {
        return directeur;
    }

    @Override
    public void setDirecteur(Directeur directeur) {
        this.directeur = directeur;
    }

    public Nationnalite getNationnalite() {
        return nationnalite;
    }

    public void setNationnalite(Nationnalite nationnalite) {
        this.nationnalite = nationnalite;
    }

    public double geteGPS() {
        return eGPS;
    }

    public void seteGPS(double eGPS) {
        this.eGPS = eGPS;
    }

    public double getnGPS() {
        return nGPS;
    }

    public void setnGPS(double nGPS) {
        this.nGPS = nGPS;
    }

    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }
}
