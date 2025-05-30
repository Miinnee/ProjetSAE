package factoryCIUP.menu;

public abstract class Nourriture {

    String nomNourriture;
    TypeNourriture typeNourriture;
    TypePlat typePlat;
    Double prix;

    public Nourriture(String nomNourriture, TypeNourriture typeNourriture, TypePlat typePlat, Double prix) {
        this.nomNourriture = nomNourriture;
        this.typeNourriture = typeNourriture;
        this.typePlat = typePlat;
        this.prix = prix;
    }

    public String getNomNourriture() {
        return nomNourriture;
    }

    public void setNomNourriture(String nomNourriture) {
        this.nomNourriture = nomNourriture;
    }

    public TypeNourriture getTypeNourriture() {
        return typeNourriture;
    }

    public void setTypeNourriture(TypeNourriture typeNourriture) {
        this.typeNourriture = typeNourriture;
    }

    public TypePlat getTypePlat() {
        return typePlat;
    }

    public void setTypePlat(TypePlat typePlat) {
        this.typePlat = typePlat;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}
