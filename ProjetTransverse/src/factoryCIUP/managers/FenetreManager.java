package factoryCIUP.managers;

import factoryCIUP.FactoryCIUP;
import factoryCIUP.personnes.Directeur;
import factoryCIUP.personnes.Etudiant;
import factoryCIUP.personnes.Formation;
import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.Structure;
import factoryCIUP.structures.structuresTypes.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FenetreManager extends JFrame {

    public FenetreManager() {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Cite Universitaire");
            f.setSize(1280, 720);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Panel principal avec CardLayout
            JPanel mainPanel = new JPanel(new CardLayout());

            // Création des panneaux pour chaque page
            JPanel etudiantPanel = createPageEtudiant();
            JPanel maisonsPanel = createPageMaisons(mainPanel);
            JPanel servicesPanel = createServicePanel(mainPanel);

            // Ajout des panneaux au mainPanel avec des identifiants uniques
            mainPanel.add(etudiantPanel, "ETUDIANTS");
            mainPanel.add(maisonsPanel, "MAISONS");
            mainPanel.add(servicesPanel, "SERVICES");

            // Chargement de l'icône
            ImageIcon icon = loadImage("logo.png", 200, 100);
            JLabel iconLabel = new JLabel(icon);

            // Panel gauche pour l'icône
            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            leftPanel.add(iconLabel);

            // Panel central pour les boutons
            JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            centerPanel.add(leftPanel);

            // Création des boutons
            JButton buttonEtudiant = new JButton("Etudiants");
            JButton buttonMaisons = new JButton("Maisons");
            JButton buttonServices = new JButton("Services");

            // Ajout des boutons au panel central
            centerPanel.add(buttonEtudiant);
            centerPanel.add(buttonMaisons);
            centerPanel.add(buttonServices);

            // Ajout du panel central au panel principal
            f.add(centerPanel, BorderLayout.NORTH);
            f.add(mainPanel, BorderLayout.CENTER);

            // Gestion des clics sur les boutons
            buttonEtudiant.addActionListener(e -> showPanel(mainPanel, "ETUDIANTS"));
            buttonMaisons.addActionListener(e -> showPanel(mainPanel, "MAISONS"));
            buttonServices.addActionListener(e -> showPanel(mainPanel, "SERVICES"));

            // Affichage de la fenêtre
            f.setVisible(true);
        });
    }

    private void showPanel(JPanel mainPanel, String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }

    private JPanel createServicePanel(JPanel parentPanel) {
        // Panel principal qui contiendra soit la liste des services, soit le panel détaillé
        JPanel mainServicePanel = new JPanel(new BorderLayout());

        // Panel pour la liste des boutons de services
        JPanel servicesListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Exclure les services
        FactoryCIUP.getMaisonInternationnal()
                .getStructureList()
                .stream()
                .filter(structure -> !(structure instanceof Maison))
                .forEach(structure -> {

            JButton button = new JButton();
            JPanel structurePanel = structure.panel(parentPanel); // Panel spécifique de la structure
            button.setLayout(new BorderLayout());
            JLabel label = new JLabel(structure.getNomMaison());
                    button.add(label, BorderLayout.SOUTH);
                    button.setPreferredSize(new Dimension(500, 250));

                    if(structure instanceof Cafeteria){
                        button.setIcon(loadImage("Cafet.png", 200, 200));
                    }
                    if(structure instanceof RestoU){
                        button.setIcon(loadImage("RestoU.png", 150, 150));
                    }
                    if(structure instanceof Theatre){
                        button.setIcon(loadImage("Theatre.png",200,200));
                    }
                    if(structure instanceof Piscine){
                        button.setIcon(loadImage("Piscine.png",200,200));
                    }
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setForeground(Color.BLACK);
            label.setPreferredSize(new Dimension(250, 100));
            button.addActionListener(e -> {
                // Retirer tous les composants du panel principal
                mainServicePanel.removeAll();
                // Ajouter le panel de la structure
                mainServicePanel.add(structurePanel, BorderLayout.CENTER);

                // Ajouter un bouton "Retour"
                JButton backButton = new JButton("Retour à la liste");
                backButton.addActionListener(ev -> {
                    mainServicePanel.removeAll();
                    mainServicePanel.add(servicesListPanel, BorderLayout.CENTER);
                    mainServicePanel.revalidate();
                    mainServicePanel.repaint();
                });

                mainServicePanel.add(backButton, BorderLayout.SOUTH);

                // Rafraîchir l'affichage
                mainServicePanel.revalidate();
                mainServicePanel.repaint();
            });
            servicesListPanel.add(button);
        });

        // Ajouter initialement la liste des services au panel principal
        mainServicePanel.add(servicesListPanel, BorderLayout.CENTER);

        return mainServicePanel;
    }



    private JPanel createPage(String pageName) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(pageName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    JPanel mPanel ;

    private JPanel createPageMaisons(JPanel parentPanel) {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton buttonAjouterMaison = new JButton("Ajouter Maison");
        
        buttonPanel.add(buttonAjouterMaison);

        // Panel pour afficher les maisons
        JPanel maisonsPanel = new JPanel();
        maisonsPanel.setLayout(new BoxLayout(maisonsPanel, BoxLayout.Y_AXIS));
        mPanel = maisonsPanel;

        buttonAjouterMaison.addActionListener(e -> {

                JTextField nomField = new JTextField();

                JTextField nGPS = new JTextField();
                JTextField eGPS = new JTextField();

                JTextField nbChambre = new JTextField();

                JComboBox<Nationnalite> nationnaliteJComboBox = new JComboBox<>(Nationnalite.values());

                JTextField directeurPrenom = new JTextField();
                JTextField directeurNom = new JTextField();

                JPanel formPanel = new JPanel(new GridLayout(0, 1));
                formPanel.add(new JLabel("Nom de Maison :"));
                formPanel.add(nomField);

                formPanel.add(new JLabel("NGPS :"));
                formPanel.add(nGPS);

                formPanel.add(new JLabel("EGPS :"));
                formPanel.add(eGPS);

                formPanel.add(new JLabel("Nombre de Chambre :"));
                formPanel.add(nbChambre);

                formPanel.add(new JLabel("Nationnalité :"));
                formPanel.add(nationnaliteJComboBox);

                formPanel.add(new JLabel("Prenom directeur : "));
                formPanel.add(directeurPrenom);

                formPanel.add(new JLabel("Nom directeur : "));
                formPanel.add(directeurNom);

                int result = JOptionPane.showConfirmDialog(null, formPanel,
                        "Ajouter la Maison", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {

                    if(eGPS.getText() == null || nGPS.getText() == null || nbChambre.getText() == null || nationnaliteJComboBox.getSelectedItem() == Nationnalite.NEUTRE.name() ){
                        // Créer une boîte de dialogue
                        JFrame frame = new JFrame();
                        JDialog d = new JDialog(frame, "Erreur");
                        // Créer une étiquette
                        JLabel l = new JLabel("Tous les champs sont obligatoire");
                        // Ajouter l'étiquette à la boîte de dialogue
                        d.add(l);
                        // Définir la taille de la boîte de dialogue
                        d.setSize(200, 100);
                        // Définir la visibilité de la boîte de dialogue
                        d.setVisible(true);
                        return;
                    }


                    Directeur directeur = new Directeur(directeurNom.getText(), directeurPrenom.getText());


                    Maison maison = new Maison(nomField.getText(), Nationnalite.valueOf(nationnaliteJComboBox.getSelectedItem().toString()), directeur, Double.parseDouble(nGPS.getText()),  Double.parseDouble(eGPS.getText()), 5);

                    FactoryCIUP.getMaisonInternationnal().addMaisonToInternationnal(maison);
                    FactoryCIUP.getMaisonInternationnal().getStructureList().stream().filter(structure -> structure instanceof Maison).forEach(structure ->
                            System.out.println(structure.getNomMaison())
                    );


                    maisonsPanel.removeAll();
                    maisonsPanel.revalidate();
                    // TODO : sauvegarde des objet dans des fichiers pour reconstruire les maisons creer dans les dossiers
                    execute(maisonsPanel);
                }

        });

        // Parcourir la liste des structures pour afficher les maisons
        execute(maisonsPanel);

        // Ajouter un JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(maisonsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Ajouter les composants au panel principal
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void execute(JPanel maisonsPanel){
        FactoryCIUP.getMaisonInternationnal().getStructureList().stream()
                .filter(structure -> structure instanceof Maison)
                .forEach(structure -> {
                    Maison maison = (Maison) structure;

                    // Créer un sous-panneau pour chaque maison
                    JPanel maisonPanel = new JPanel(new BorderLayout());

                    // Ajouter le nom de la maison
                    JLabel nomLabel = new JLabel("Nom: " + maison.getNomMaison());
                    nomLabel.setFont(new Font("Arial", Font.BOLD, 16));

                    // Ajouter la nationalité de la maison
                    JLabel nationaliteLabel = new JLabel("Nationalité: " + maison.getNationnalite().name());
                    nationaliteLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                    JLabel directeurLabel = new JLabel("Directeur: " + maison.getDirecteur().getNom() + " " + maison.getDirecteur().getPrenom());
                    directeurLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                    ImageIcon icon = loadImage(maison.getNationnalite().getDrapeau() + ".png", 100, 50);
                    JLabel iconLabel = new JLabel(icon);

                    // Ajouter le nombre d'étudiants
                    JLabel etudiantsLabel = new JLabel("Étudiants: " + maison.getEtudiantLogeant().size());
                    etudiantsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                    // Ajouter les informations au sous-panneau
                    JPanel infoPanel = new JPanel(new GridLayout(3, 1));
                    infoPanel.add(nomLabel);
                    infoPanel.add(nationaliteLabel);
                    infoPanel.add(iconLabel);
                    infoPanel.add(etudiantsLabel);
                    infoPanel.add(directeurLabel);
                    maisonPanel.add(infoPanel, BorderLayout.CENTER);

                    JButton detailsButton = new JButton("Supprimer");
                    maisonPanel.add(detailsButton, BorderLayout.EAST);

                    detailsButton.addActionListener(e -> {
                        FactoryCIUP.getMaisonInternationnal().getStructureList().remove(maison);
                        // Mettre à jour l'affichage
                        maisonsPanel.remove(maisonPanel);
                        maisonsPanel.revalidate();
                        maisonsPanel.repaint();
                    });

                    // Ajouter le sous-panneau au panel des maisons
                    maisonsPanel.add(maisonPanel);
                    maisonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement entre les maisons
                });
    }


    private JPanel createPageEtudiant() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel etudiantPanel = new JPanel();
        etudiantPanel.setLayout(new BoxLayout(etudiantPanel, BoxLayout.Y_AXIS));

        JPanel menuetudiant = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JButton ajoutetudiant =new JButton("Ajouter un etudiant");
        menuetudiant.add(ajoutetudiant);



        FactoryCIUP.getMaisonInternationnal().getStructureList().stream()
                .filter(structure -> structure instanceof Maison)
                .forEach(structure -> {
                    Maison maison = (Maison) structure;
                    for (Etudiant etudiant : maison.getEtudiantLogeant()) {
                        JPanel panelEtudiant = new JPanel(new BorderLayout());

                        // Chargement de l'icône de l'étudiant
                        ImageIcon icon = loadImage("utilisateur.png", 100, 50);
                        JLabel iconLabel = new JLabel(icon);
                        panelEtudiant.add(iconLabel, BorderLayout.LINE_START);

                        // Panel pour les informations de l'étudiant
                        JPanel panelInfo = new JPanel(new GridLayout(4, 2));

                        JLabel labelNom = new JLabel("Nom: " + etudiant.getNom());
                        labelNom.setFont(new Font("Arial", Font.PLAIN, 16));

                        JLabel labelPrenom = new JLabel("Prénom: " + etudiant.getPrenom());
                        labelPrenom.setFont(new Font("Arial", Font.PLAIN, 16));

                        JLabel labelMaison = new JLabel("Maison: " + maison.getNomMaison());
                        labelMaison.setFont(new Font("Arial", Font.PLAIN, 16));

                        JLabel labelNatio = new JLabel("Nationalité: " + maison.getNationnalite().name());
                        labelNatio.setFont(new Font("Arial", Font.PLAIN, 16));

                        ImageIcon rightIcon = loadImage(structure.getNationnalite().getDrapeau() + ".png", 100, 50);
                        JLabel rightIconLabel = new JLabel(rightIcon);

                        panelEtudiant.add(rightIconLabel, BorderLayout.LINE_END);

                        panelInfo.add(labelNom);
                        panelInfo.add(labelPrenom);
                        panelInfo.add(labelMaison);
                        panelInfo.add(labelNatio);

                        panelEtudiant.add(panelInfo, BorderLayout.CENTER);
                        panelEtudiant.setBorder(new EtchedBorder());

                        // Ajout du panneau de l'étudiant au panneau principal
                        etudiantPanel.add(panelEtudiant);
                        etudiantPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                    }
                });



        panel.add(menuetudiant);
        panel.add(etudiantPanel);

        ajoutetudiant.addActionListener(e -> {

                JTextField nomField = new JTextField();

                JTextField prenom = new JTextField();

                JTextField annee = new JTextField();

            List<String> nomsMaisons = FactoryCIUP.getMaisonInternationnal()
                    .getStructureList()
                    .stream()
                    .filter(structure -> structure instanceof Maison)
                    .map(structure -> ((Maison) structure).getNomMaison()) // cast vers Maison, puis appel de getNomMaison()
                    .collect(Collectors.toList());

            JComboBox<String> maisonsComboBox = new JComboBox<>(nomsMaisons.toArray(new String[0]));


            JComboBox<Formation> Fcombo = new JComboBox<>(Formation.values());


            JPanel formPanel = new JPanel(new GridLayout(0, 1));
                formPanel.add(new JLabel("Nom :"));
                formPanel.add(nomField);

                formPanel.add(new JLabel("Prenom :"));
                formPanel.add(prenom);

                formPanel.add(new JLabel("Nombre d'annee :"));
                formPanel.add(annee);

                formPanel.add(new JLabel("Formation :"));
                formPanel.add(Fcombo);

                formPanel.add(new JLabel("Maison :"));
                formPanel.add(maisonsComboBox);

                int result = JOptionPane.showConfirmDialog(null, formPanel,
                        "Ajouter l'etudiant", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    Etudiant etud = new Etudiant(nomField.getText(), prenom.getText(), Formation.valueOf(Fcombo.getSelectedItem().toString()), annee.getText());
                    System.out.println(etud);
                    System.out.println(maisonsComboBox.getSelectedItem());

                    Optional<Structure> m= FactoryCIUP.getMaisonInternationnal().getStructureList()
                            .stream()
                            .filter(structure -> structure instanceof Maison && structure.getNomMaison() == maisonsComboBox.getSelectedItem())
                                    .findFirst();

                    if(m.isPresent()) {
                        System.out.println(m.get());
                        m.get().getEtudiantLogeant().add(etud);
                        execute(mPanel);

                    }


                    etudiantPanel.removeAll();
                    etudiantPanel.revalidate();


                    FactoryCIUP.getMaisonInternationnal().getStructureList().stream()
                            .filter(structure -> structure instanceof Maison)
                            .forEach(structure -> {
                                Maison maison = (Maison) structure;
                                for (Etudiant etudiant : maison.getEtudiantLogeant()) {
                                    JPanel panelEtudiant = new JPanel(new BorderLayout());

                                    // Chargement de l'icône de l'étudiant
                                    ImageIcon icon = loadImage("utilisateur.png", 100, 50);
                                    JLabel iconLabel = new JLabel(icon);
                                    panelEtudiant.add(iconLabel, BorderLayout.LINE_START);

                                    // Panel pour les informations de l'étudiant
                                    JPanel panelInfo = new JPanel(new GridLayout(4, 2));

                                    JLabel labelNom = new JLabel("Nom: " + etudiant.getNom());
                                    labelNom.setFont(new Font("Arial", Font.PLAIN, 16));

                                    JLabel labelPrenom = new JLabel("Prénom: " + etudiant.getPrenom());
                                    labelPrenom.setFont(new Font("Arial", Font.PLAIN, 16));

                                    JLabel labelMaison = new JLabel("Maison: " + maison.getNomMaison());
                                    labelMaison.setFont(new Font("Arial", Font.PLAIN, 16));

                                    JLabel labelNatio = new JLabel("Nationalité: " + maison.getNationnalite().name());
                                    labelNatio.setFont(new Font("Arial", Font.PLAIN, 16));

                                    ImageIcon rightIcon = loadImage(structure.getNationnalite().getDrapeau() + ".png", 100, 50);
                                    JLabel rightIconLabel = new JLabel(rightIcon);

                                    panelEtudiant.add(rightIconLabel, BorderLayout.LINE_END);

                                    panelInfo.add(labelNom);
                                    panelInfo.add(labelPrenom);
                                    panelInfo.add(labelMaison);
                                    panelInfo.add(labelNatio);

                                    panelEtudiant.add(panelInfo, BorderLayout.CENTER);
                                    panelEtudiant.setBorder(new EtchedBorder());

                                    // Ajout du panneau de l'étudiant au panneau principal
                                    etudiantPanel.add(panelEtudiant);
                                    etudiantPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                }
                            });
                }
        });


        // Ajout d'un scroll pane pour gérer un grand nombre d'étudiants
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(scrollPane, BorderLayout.CENTER);

        return containerPanel;
    }

    private ImageIcon loadImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }
}