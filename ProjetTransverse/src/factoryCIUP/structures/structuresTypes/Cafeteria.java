package factoryCIUP.structures.structuresTypes;

import factoryCIUP.menu.*;
import factoryCIUP.menu.Menu;
import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.Structure;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Cafeteria extends Structure {

    String nomCafet;
    Nationnalite nationnalite;
    double nGPS,eGPS;
    List<Menu> carteCafet;

    @Override
    public JPanel panel(JPanel parentPanel) {
        JPanel mainContainer = new JPanel(new BorderLayout()); // Conteneur principal


        JComboBox comboBoxPlat = new JComboBox(TypePlat.values());
        JComboBox comboBoxNourriture = new JComboBox(TypeNourriture.values());
        String[] stringsType = {"MENU", "INDIVIDUEL"};
        JComboBox comboBoxType = new JComboBox(stringsType);
        JButton validationButton = new JButton("Valider Filtre");
        JButton parDefautButton = new JButton("Defaut Filtre");

        JPanel menuFiltre = new JPanel(new BorderLayout());
        menuFiltre.setLayout(new BoxLayout(menuFiltre, BoxLayout.X_AXIS));

        menuFiltre.add(comboBoxPlat, BorderLayout.EAST);
        menuFiltre.add(comboBoxNourriture, BorderLayout.EAST);
        menuFiltre.add(comboBoxType, BorderLayout.EAST);

        menuFiltre.add(validationButton, BorderLayout.EAST);
        menuFiltre.add(parDefautButton, BorderLayout.EAST);

        mainContainer.add(menuFiltre, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Organisation verticale
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marge autour

        if (carteCafet != null) {
            carteCafet.forEach(menu -> {
                // Panel pour chaque menu avec bordure
                JPanel menuCard = new JPanel();
                menuCard.setLayout(new BoxLayout(menuCard, BoxLayout.Y_AXIS));
                menuCard.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(menu.getNomMenu()),
                        BorderFactory.createEtchedBorder(Color.BLACK,Color.BLACK)
                ));

                // Prix en gras
                JLabel labelPrix = new JLabel("Prix total: " + menu.getPrixTotal() + " €");
                labelPrix.setFont(labelPrix.getFont().deriveFont(Font.BOLD));
                menuCard.add(labelPrix);

                // Séparateur
                menuCard.add(new JSeparator(JSeparator.HORIZONTAL));

                // Panel pour les items de nourriture
                JPanel foodItemsPanel = new JPanel();
                foodItemsPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Une colonne, espacement de 5px

                menu.getNourritures().forEach(nourriture -> {
                    JPanel foodCard = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    foodCard.setBorder(BorderFactory.createEtchedBorder());

                    JLabel nomNourriture = new JLabel(nourriture.getNomNourriture());
                    nomNourriture.setFont(nomNourriture.getFont().deriveFont(Font.PLAIN, 12));

                    JLabel typeNourriture = new JLabel("(" + nourriture.getTypeNourriture().name() + ")");
                    JLabel typePlat = new JLabel("[" + nourriture.getTypePlat().name() + "]");
                    JLabel price = new JLabel(" | " + nourriture.getPrix() + " €");

                    foodCard.add(nomNourriture);
                    foodCard.add(typeNourriture);
                    foodCard.add(typePlat);
                    foodCard.add(price);


                    foodItemsPanel.add(foodCard);
                });

                menuCard.add(foodItemsPanel);
                menuPanel.add(menuCard);
                menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace entre les menus
            });

            // Ajout dans un JScrollPane pour le défilement si nécessaire
            JScrollPane scrollPane = new JScrollPane(menuPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            mainContainer.add(scrollPane, BorderLayout.CENTER);
        }

        parDefautButton.addActionListener(e -> {

            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();


                carteCafet.forEach(menu -> {
                    // Panel pour chaque menu avec bordure
                    JPanel menuCard = new JPanel();
                    menuCard.setLayout(new BoxLayout(menuCard, BoxLayout.Y_AXIS));
                    menuCard.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder(menu.getNomMenu()),
                            BorderFactory.createEtchedBorder(Color.BLACK,Color.BLACK)
                    ));

                    // Prix en gras
                    JLabel labelPrix = new JLabel("Prix total: " + menu.getPrixTotal() + " €");
                    labelPrix.setFont(labelPrix.getFont().deriveFont(Font.BOLD));
                    menuCard.add(labelPrix);

                    // Séparateur
                    menuCard.add(new JSeparator(JSeparator.HORIZONTAL));

                    // Panel pour les items de nourriture
                    JPanel foodItemsPanel = new JPanel();
                    foodItemsPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Une colonne, espacement de 5px

                    menu.getNourritures().forEach(nourriture -> {
                        JPanel foodCard = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        foodCard.setBorder(BorderFactory.createEtchedBorder());

                        JLabel nomNourriture = new JLabel(nourriture.getNomNourriture());
                        nomNourriture.setFont(nomNourriture.getFont().deriveFont(Font.PLAIN, 12));

                        JLabel typeNourriture = new JLabel("(" + nourriture.getTypeNourriture().name() + ")");
                        JLabel typePlat = new JLabel("[" + nourriture.getTypePlat().name() + "]");
                        JLabel price = new JLabel(" | " + nourriture.getPrix() + " €");

                        foodCard.add(nomNourriture);
                        foodCard.add(typeNourriture);
                        foodCard.add(typePlat);
                        foodCard.add(price);


                        foodItemsPanel.add(foodCard);
                    });

                    menuCard.add(foodItemsPanel);
                    menuPanel.add(menuCard);
                    menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace entre les menus
                });

                menuPanel.revalidate();
                menuPanel.repaint();
        });


        validationButton.addActionListener(e -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();

            TypePlat selectedPlat = (TypePlat) comboBoxPlat.getSelectedItem();
            TypeNourriture selectedNourriture = (TypeNourriture) comboBoxNourriture.getSelectedItem();
            String comboTypeVal = (String) comboBoxType.getSelectedItem();

            if(Objects.equals(comboTypeVal, "INDIVIDUEL")){
                List<Nourriture> nourritures = new ArrayList<>();

                for (Menu menu : carteCafet) {
                    for (Nourriture nourriture : menu.getNourritures()) {
                        if(nourriture.getTypeNourriture() == selectedNourriture &&
                                nourriture.getTypePlat() == selectedPlat){
                            nourritures.add(nourriture);
                        }
                    }
                }

                for (Nourriture nourriture : nourritures) {
                    JPanel foodCard = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    foodCard.setBorder(BorderFactory.createEtchedBorder());

                    JLabel nomNourriture = new JLabel(nourriture.getNomNourriture());
                    nomNourriture.setFont(nomNourriture.getFont().deriveFont(Font.PLAIN, 12));

                    JLabel typeNourriture = new JLabel("(" + nourriture.getTypeNourriture().name() + ")");
                    JLabel typePlat = new JLabel("[" + nourriture.getTypePlat().name() + "]");
                    JLabel price = new JLabel(" | " + nourriture.getPrix() + " €");

                    foodCard.add(nomNourriture);
                    foodCard.add(typeNourriture);
                    foodCard.add(typePlat);
                    foodCard.add(price);

                    menuPanel.add(foodCard);

                }



            }else{
                List<Menu> menus = new ArrayList<>();
                for (Menu menu : carteCafet) {
                    for (Nourriture nourriture : menu.getNourritures()) {
                        if(nourriture.getTypeNourriture() == selectedNourriture &&
                                nourriture.getTypePlat() == selectedPlat){
                            menus.add(menu);
                            break;
                        }
                    }
                }
                for (Menu menu : menus) {
                    // Panel pour chaque menu avec bordure
                    JPanel menuCard = new JPanel();
                    menuCard.setLayout(new BoxLayout(menuCard, BoxLayout.Y_AXIS));
                    menuCard.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder(menu.getNomMenu()),
                            BorderFactory.createEtchedBorder(Color.BLACK,Color.BLACK)
                    ));

                    // Prix en gras
                    JLabel labelPrix = new JLabel("Prix total: " + menu.getPrixTotal() + " €");
                    labelPrix.setFont(labelPrix.getFont().deriveFont(Font.BOLD));
                    menuCard.add(labelPrix);

                    // Séparateur
                    menuCard.add(new JSeparator(JSeparator.HORIZONTAL));

                    // Panel pour les items de nourriture
                    JPanel foodItemsPanel = new JPanel();
                    foodItemsPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Une colonne, espacement de 5px

                    menu.getNourritures().forEach(nourriture -> {
                        JPanel foodCard = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        foodCard.setBorder(BorderFactory.createEtchedBorder());

                        JLabel nomNourriture = new JLabel(nourriture.getNomNourriture());
                        nomNourriture.setFont(nomNourriture.getFont().deriveFont(Font.PLAIN, 12));

                        JLabel typeNourriture = new JLabel("(" + nourriture.getTypeNourriture().name() + ")");
                        JLabel typePlat = new JLabel("[" + nourriture.getTypePlat().name() + "]");
                        JLabel price = new JLabel(" | " + nourriture.getPrix() + " €");

                        foodCard.add(nomNourriture);
                        foodCard.add(typeNourriture);
                        foodCard.add(typePlat);
                        foodCard.add(price);


                        foodItemsPanel.add(foodCard);
                    });

                    menuCard.add(foodItemsPanel);
                    menuPanel.add(menuCard);
                    menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace entre les menus
                }

            }

            menuPanel.revalidate();
            menuPanel.repaint();

        });



        return mainContainer;
    }


    public Cafeteria(Nationnalite nationnalite, String nomMaison, double nGPS, double eGPS) {
        super(nationnalite, nomMaison, nGPS, eGPS);
        this.nomCafet=nomMaison;
        this.nationnalite=nationnalite;
        this.eGPS=eGPS;
        this.nGPS=nGPS;
        carteCafet = new LinkedList<>();
    }

    public void carteDuJour(){
        List<Nourriture> menu1Nourriture = new ArrayList<>();

        menu1Nourriture.add(new Salee("Salade de quinoa, avocat et légumes croquants",TypeNourriture.ENTREE,TypePlat.VEGAN,3.50));
        menu1Nourriture.add(new Salee("Chili sin carne aux haricots rouges avec du riz",TypeNourriture.PLAT,TypePlat.VEGAN,7.50));
        menu1Nourriture.add(new Sucree("Compote de pommes et cannelle maison", TypeNourriture.DESSERT, TypePlat.VEGAN,2.50));

        List<Nourriture> menu2Nourriture = new ArrayList<>();

        menu2Nourriture.add(new Salee("Velouté de légumes de saison",TypeNourriture.ENTREE,TypePlat.VEGETARIEN,3.00));
        menu2Nourriture.add(new Salee("Gratin de pâtes aux légumes et fromage",TypeNourriture.PLAT,TypePlat.VEGETARIEN,7.00));
        menu2Nourriture.add(new Sucree("Fromage blanc avec coulis de fruits rouges ", TypeNourriture.DESSERT, TypePlat.VEGETARIEN,2.50));

        List<Nourriture> menu3Nourriture = new ArrayList<>();

        menu3Nourriture.add(new Salee("Salade de crudités (carottes, concombre, maïs)",TypeNourriture.ENTREE,TypePlat.NEUTRE,3.00));
        menu3Nourriture.add(new Salee("Poulet rôti avec pommes de terre sautées",TypeNourriture.PLAT,TypePlat.VIANDE,8.00));
        menu3Nourriture.add(new Sucree("Tarte aux poires", TypeNourriture.DESSERT, TypePlat.NEUTRE ,3.00));

        List<Nourriture> menu4Nourriture = new ArrayList<>();

        menu4Nourriture.add(new Salee("Carottes râpées à la vinaigrette", TypeNourriture.ENTREE, TypePlat.VEGAN, 2.80));
        menu4Nourriture.add(new Salee("Couscous végétarien aux légumes et pois chiches", TypeNourriture.PLAT, TypePlat.VEGAN, 7.20));
        menu4Nourriture.add(new Sucree("Fruit frais de saison (pomme ou banane)", TypeNourriture.DESSERT, TypePlat.NEUTRE, 1.80));

        List<Nourriture> menu5Nourriture = new ArrayList<>();

        menu5Nourriture.add(new Salee("Betteraves en dés à l’échalote", TypeNourriture.ENTREE, TypePlat.VEGETARIEN, 2.50));
        menu5Nourriture.add(new Salee("Omelette nature avec frites", TypeNourriture.PLAT, TypePlat.VEGETARIEN, 6.80));
        menu5Nourriture.add(new Sucree("Yaourt nature sucré", TypeNourriture.DESSERT, TypePlat.VEGETARIEN, 2.00));

        List<Nourriture> menu6Nourriture = new ArrayList<>();

        menu6Nourriture.add(new Salee("Taboulé maison", TypeNourriture.ENTREE, TypePlat.NEUTRE, 3.00));
        menu6Nourriture.add(new Salee("Sauté de dinde aux petits légumes et semoule", TypeNourriture.PLAT, TypePlat.VIANDE, 8.20));
        menu6Nourriture.add(new Sucree("Gâteau au yaourt", TypeNourriture.DESSERT, TypePlat.NEUTRE, 2.80));

        List<Nourriture> menu7Nourriture = new ArrayList<>();

        menu7Nourriture.add(new Salee("Œufs durs mayonnaise", TypeNourriture.ENTREE, TypePlat.NEUTRE, 3.00));
        menu7Nourriture.add(new Salee("Steak haché avec haricots verts et purée", TypeNourriture.PLAT, TypePlat.VIANDE, 8.50));
        menu7Nourriture.add(new Sucree("Flan caramel", TypeNourriture.DESSERT, TypePlat.NEUTRE, 2.70));

        List<Nourriture> menu8Nourriture = new ArrayList<>();

        menu8Nourriture.add(new Salee("Macédoine de légumes", TypeNourriture.ENTREE, TypePlat.VEGETARIEN, 2.60));
        menu8Nourriture.add(new Salee("Gratin dauphinois avec brocolis", TypeNourriture.PLAT, TypePlat.VEGETARIEN, 6.90));
        menu8Nourriture.add(new Sucree("Riz au lait", TypeNourriture.DESSERT, TypePlat.VEGETARIEN, 2.50));

        List<Nourriture> menu9Nourriture = new ArrayList<>();

        menu9Nourriture.add(new Salee("Salade de pâtes au thon et crudités", TypeNourriture.ENTREE, TypePlat.NEUTRE, 3.20));
        menu9Nourriture.add(new Salee("Poisson pané avec riz et ratatouille", TypeNourriture.PLAT, TypePlat.POISSON, 8.00));
        menu9Nourriture.add(new Sucree("Moelleux à la vanille", TypeNourriture.DESSERT, TypePlat.NEUTRE, 3.00));

        carteCafet.add(new Menu("Vegan",menu1Nourriture ));
        carteCafet.add(new Menu("Vegetarien",menu2Nourriture ));
        carteCafet.add(new Menu("Neutre",menu3Nourriture ));

        carteCafet.add(new Menu("Vegan",menu4Nourriture ));
        carteCafet.add(new Menu("Vegetarien",menu5Nourriture ));
        carteCafet.add(new Menu("Neutre",menu6Nourriture ));

        carteCafet.add(new Menu("Vegan",menu7Nourriture ));
        carteCafet.add(new Menu("Vegetarien",menu8Nourriture ));
        carteCafet.add(new Menu("Neutre",menu9Nourriture ));

        List<Nourriture> menu1Nourriture1 = new ArrayList<>();
        menu1Nourriture1.add(new Salee("Salade César au poulet grillé", TypeNourriture.ENTREE, TypePlat.NEUTRE, 4.20));
        menu1Nourriture1.add(new Salee("Pavé de saumon sauce citronnée", TypeNourriture.PLAT, TypePlat.POISSON, 12.50));
        menu1Nourriture1.add(new Sucree("Profiteroles au chocolat", TypeNourriture.DESSERT, TypePlat.NEUTRE, 5.00));

        List<Nourriture> menu2Nourriture1 = new ArrayList<>();
        menu2Nourriture1.add(new Salee("Velouté de champignons", TypeNourriture.ENTREE, TypePlat.VEGETARIEN, 3.80));
        menu2Nourriture1.add(new Salee("Risotto aux asperges", TypeNourriture.PLAT, TypePlat.VEGETARIEN, 9.80));
        menu2Nourriture1.add(new Sucree("Tarte tatin", TypeNourriture.DESSERT, TypePlat.VEGETARIEN, 4.50));

        List<Nourriture> menu3Nourriture1 = new ArrayList<>();
        menu3Nourriture1.add(new Salee("Carpaccio de bœuf", TypeNourriture.ENTREE, TypePlat.VIANDE, 6.50));
        menu3Nourriture1.add(new Salee("Tagliatelles carbonara", TypeNourriture.PLAT, TypePlat.NEUTRE, 10.50));
        menu3Nourriture1.add(new Sucree("Crème caramel", TypeNourriture.DESSERT, TypePlat.NEUTRE, 4.00));

        List<Nourriture> menu4Nourriture1 = new ArrayList<>();
        menu4Nourriture1.add(new Salee("Tartare d'algues", TypeNourriture.ENTREE, TypePlat.VEGAN, 4.50));
        menu4Nourriture1.add(new Salee("Curry de lentilles corail", TypeNourriture.PLAT, TypePlat.VEGAN, 8.50));
        menu4Nourriture1.add(new Sucree("Smoothie bowl tropical", TypeNourriture.DESSERT, TypePlat.VEGAN, 5.50));

        List<Nourriture> menu5Nourriture1 = new ArrayList<>();
        menu5Nourriture1.add(new Salee("Feuilleté aux escargots", TypeNourriture.ENTREE, TypePlat.VIANDE, 5.80));
        menu5Nourriture1.add(new Salee("Blanquette de veau", TypeNourriture.PLAT, TypePlat.VIANDE, 13.50));
        menu5Nourriture1.add(new Sucree("Île flottante", TypeNourriture.DESSERT, TypePlat.NEUTRE, 4.80));

        List<Nourriture> menu6Nourriture1 = new ArrayList<>();
        menu6Nourriture1.add(new Salee("Salade niçoise", TypeNourriture.ENTREE, TypePlat.NEUTRE, 5.20));
        menu6Nourriture1.add(new Salee("Bouillabaisse", TypeNourriture.PLAT, TypePlat.POISSON, 14.00));
        menu6Nourriture1.add(new Sucree("Sorbet citron vert", TypeNourriture.DESSERT, TypePlat.VEGAN, 4.00));

        carteCafet.add(new Menu("Menu Viande Premium", menu1Nourriture1));
        carteCafet.add(new Menu("Menu Végétarien Gourmet", menu2Nourriture1));
        carteCafet.add(new Menu("Menu Italien", menu3Nourriture1));
        carteCafet.add(new Menu("Menu Vegan Bio", menu4Nourriture1));
        carteCafet.add(new Menu("Menu Tradition Française", menu5Nourriture1));
        carteCafet.add(new Menu("Menu Méditerranéen", menu6Nourriture1));

    }

}