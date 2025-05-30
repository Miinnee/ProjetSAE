package factoryCIUP.structures.structuresTypes;

import factoryCIUP.menu.*;
import factoryCIUP.menu.Menu;
import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.Structure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RestoU extends Structure {

    List<Menu> carteResto;

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

        if (carteResto != null) {
            carteResto.forEach(menu -> {
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


            carteResto.forEach(menu -> {
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

                for (Menu menu : carteResto) {
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
                for (Menu menu : carteResto) {
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
    public RestoU(Nationnalite nationnalite, String nomMaison, double nGPS, double eGPS) {
        super(nationnalite, nomMaison, nGPS, eGPS);
        this.carteResto = new LinkedList<>();
    }

    public void carteDuJour(){
        List<Nourriture> menu1Nourriture = new ArrayList<>();

        menu1Nourriture.add(new Salee("Velouté de potimarron et lait de coco", TypeNourriture.ENTREE, TypePlat.VEGAN,3.50));
        menu1Nourriture.add(new Salee("Curry de pois chiches et légumes avec du riz basmati",TypeNourriture.PLAT,TypePlat.VEGAN,7.50));
        menu1Nourriture.add(new Sucree("Mousse au chocolat à l’aquafaba", TypeNourriture.DESSERT, TypePlat.VEGAN,3.00));

        List<Nourriture> menu2Nourriture = new ArrayList<>();

        menu2Nourriture.add(new Salee("Salade caprese (tomates, mozzarella, basilic)",TypeNourriture.ENTREE,TypePlat.VEGETARIEN,4.00));
        menu2Nourriture.add(new Salee("Lasagnes aux épinards et ricotta",TypeNourriture.PLAT,TypePlat.VEGETARIEN,8.00));
        menu2Nourriture.add(new Sucree("Tarte aux pommes maison", TypeNourriture.DESSERT, TypePlat.VEGETARIEN,3.50));

        List<Nourriture> menu3Nourriture = new ArrayList<>();

        menu3Nourriture.add(new Salee("Carpaccio de bœuf avec roquette et copeaux de parmesan",TypeNourriture.ENTREE,TypePlat.NEUTRE,5.00));
        menu3Nourriture.add(new Salee("Magret de canard sauce miel et purée de patates douces",TypeNourriture.PLAT,TypePlat.NEUTRE,12.00));
        menu3Nourriture.add(new Sucree("Fondant au chocolat avec une boule de glace vanille", TypeNourriture.DESSERT, TypePlat.NEUTRE ,4.50));

        List<Nourriture> menu4Nourriture = new ArrayList<>();
        menu4Nourriture.add(new Salee("Soupe miso aux algues et tofu", TypeNourriture.ENTREE, TypePlat.VEGAN, 3.20));
        menu4Nourriture.add(new Salee("Ramen aux légumes et champignons shiitake", TypeNourriture.PLAT, TypePlat.VEGAN, 8.50));
        menu4Nourriture.add(new Sucree("Perles de coco à la vapeur", TypeNourriture.DESSERT, TypePlat.VEGAN, 3.00));

        List<Nourriture> menu5Nourriture = new ArrayList<>();
        menu5Nourriture.add(new Salee("Oeuf mimosa", TypeNourriture.ENTREE, TypePlat.VEGETARIEN, 3.80));
        menu5Nourriture.add(new Salee("Risotto aux champignons", TypeNourriture.PLAT, TypePlat.VEGETARIEN, 7.90));
        menu5Nourriture.add(new Sucree("Crème brûlée", TypeNourriture.DESSERT, TypePlat.VEGETARIEN, 4.00));

        List<Nourriture> menu6Nourriture = new ArrayList<>();
        menu6Nourriture.add(new Salee("Saumon mariné façon gravlax", TypeNourriture.ENTREE, TypePlat.NEUTRE, 5.50));
        menu6Nourriture.add(new Salee("Filet de cabillaud au citron et légumes vapeur", TypeNourriture.PLAT, TypePlat.NEUTRE, 10.50));
        menu6Nourriture.add(new Sucree("Tarte au citron meringuée", TypeNourriture.DESSERT, TypePlat.NEUTRE, 4.20));

        List<Nourriture> menu7Nourriture = new ArrayList<>();
        menu7Nourriture.add(new Salee("Bruschetta à la tomate et au basilic", TypeNourriture.ENTREE, TypePlat.VEGETARIEN, 3.00));
        menu7Nourriture.add(new Salee("Pizza Margherita", TypeNourriture.PLAT, TypePlat.VEGETARIEN, 8.00));
        menu7Nourriture.add(new Sucree("Panna cotta au coulis de fruits rouges", TypeNourriture.DESSERT, TypePlat.VEGETARIEN, 3.80));

        List<Nourriture> menu8Nourriture = new ArrayList<>();
        menu8Nourriture.add(new Salee("Taboulé libanais", TypeNourriture.ENTREE, TypePlat.VEGAN, 3.30));
        menu8Nourriture.add(new Salee("Falafels avec houmous et pain pita", TypeNourriture.PLAT, TypePlat.VEGAN, 7.20));
        menu8Nourriture.add(new Sucree("Baklava aux noix", TypeNourriture.DESSERT, TypePlat.VEGAN, 3.50));

        List<Nourriture> menu9Nourriture = new ArrayList<>();
        menu9Nourriture.add(new Salee("Tartare de saumon à l’avocat et citron vert", TypeNourriture.ENTREE, TypePlat.NEUTRE, 5.80));
        menu9Nourriture.add(new Salee("Pavé de veau sauce aux morilles, gratin dauphinois", TypeNourriture.PLAT, TypePlat.NEUTRE, 13.00));
        menu9Nourriture.add(new Sucree("Macaron framboise-lychee façon Ispahan", TypeNourriture.DESSERT, TypePlat.NEUTRE, 4.70));

        carteResto.add(new Menu("Vegan",menu1Nourriture ));
        carteResto.add(new Menu("Vegetarien",menu2Nourriture ));
        carteResto.add(new Menu("Neutre",menu3Nourriture ));
    }
}
