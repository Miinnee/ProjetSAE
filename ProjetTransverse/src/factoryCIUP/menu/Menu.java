package factoryCIUP.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Menu {

    String nomMenu;
    Double prixTotal;
    List<Nourriture> nourritures;


    public Menu(String nomMenu, List<Nourriture> nourritures) {
        this.nomMenu= nomMenu;
        this.nourritures = nourritures;
        this.prixTotal= calculePrixTotal();
    }


    public Double calculePrixTotal(){
        double prix = 0.0;
        assert nourritures != null;
        for (Nourriture nourriture : nourritures) {
            prix = prix + nourriture.getPrix();
        }
        return prix;
    }

    public String getNomMenu() {
        return nomMenu;
    }

    public void setNomMenu(String nomMenu) {
        this.nomMenu = nomMenu;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public List<Nourriture> getNourritures() {
        return nourritures;
    }

    public void setNourritures(List<Nourriture> nourritures) {
        this.nourritures = nourritures;
    }
}
