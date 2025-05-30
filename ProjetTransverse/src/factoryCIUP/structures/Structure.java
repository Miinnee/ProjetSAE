package factoryCIUP.structures;

import factoryCIUP.FactoryCIUP;
import factoryCIUP.personnes.Directeur;
import factoryCIUP.personnes.Etudiant;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Structure {

    private String nomMaison;
    private Nationnalite nationnalite;

    private Directeur directeur;
    private List<Etudiant> etudiantLogeant;

    private List<Structure> structureList;

    private double nGPS;
    private double eGPS;

    private int nbChambre;

    JPanel jPanel ;

    public abstract JPanel panel(JPanel parentPanel);

    // constructeur pour batiment administratif
    public Structure(Nationnalite nationnalite, String nomMaison, double nGPS, double eGPS) {
        this.nationnalite = nationnalite;
        this.nomMaison = nomMaison;
        this.nGPS = nGPS;
        this.eGPS = eGPS;
        this.jPanel = panel(null);
    }

    //constructeur maisons, avec varargs
    public Structure(String nomMaison, Nationnalite nationnalite, Directeur directeur, double nGPS, double eGPS, int nbChambre, Etudiant... etudiants) {
        this.nomMaison = nomMaison;
        this.nationnalite = nationnalite;
        this.directeur = directeur;
        this.etudiantLogeant = new ArrayList<>();
        this.nGPS = nGPS;
        this.eGPS = eGPS;
        this.nbChambre = nbChambre;
        etudiantLogeant.addAll(Arrays.asList(etudiants));
    }

    //constructeur maisons, avec ajoutArrays
    public Structure(String nomMaison, Nationnalite nationnalite, Directeur directeur, double nGPS, double eGPS, int nbChambre, List<Etudiant> etudiants) {
        this.nomMaison = nomMaison;
        this.nationnalite = nationnalite;
        this.directeur = directeur;
        this.etudiantLogeant = new ArrayList<>();
        this.nGPS = nGPS;
        this.eGPS = eGPS;
        this.nbChambre = nbChambre;
        etudiantLogeant.addAll(etudiants);
    }


    //constructeur de la maisonInternationnal
    public Structure(double eGPS, double nGPS, Directeur directeur, Nationnalite nationnalite, String nomMaison, List<Structure> structuresList) {
        this.eGPS = eGPS;
        this.nGPS = nGPS;
        this.directeur = directeur;
        this.nationnalite = nationnalite;
        this.nomMaison = nomMaison;
        this.structureList = structuresList;
    }


    public void ajoutMaison(){
        FactoryCIUP.getMaisonInternationnal().addMaisonToInternationnal(this);
    }

    public void removeEtudiant(Etudiant etudiant){
        this.etudiantLogeant.remove(etudiant);
    }


    public String getNomMaison() {
        return nomMaison;
    }

    public void setNomMaison(String nomMaison) {
        this.nomMaison = nomMaison;
    }

    public Nationnalite getNationnalite() {
        return nationnalite;
    }

    public void setNationnalite(Nationnalite nationnalite) {
        this.nationnalite = nationnalite;
    }

    public Directeur getDirecteur() {
        return directeur;
    }

    public void setDirecteur(Directeur directeur) {
        this.directeur = directeur;
    }

    public List<Etudiant> getEtudiantLogeant() {
        return etudiantLogeant;
    }

    public void setEtudiantLogeant(List<Etudiant> etudiantLogeant) {
        this.etudiantLogeant = etudiantLogeant;
    }

    public List<Structure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<Structure> structureList) {
        this.structureList = structureList;
    }

    public double getnGPS() {
        return nGPS;
    }

    public void setnGPS(double nGPS) {
        this.nGPS = nGPS;
    }

    public double geteGPS() {
        return eGPS;
    }

    public void seteGPS(double eGPS) {
        this.eGPS = eGPS;
    }

    public int getNbChambre() {
        return nbChambre;
    }

    public void setNbChambre(int nbChambre) {
        this.nbChambre = nbChambre;
    }

}
