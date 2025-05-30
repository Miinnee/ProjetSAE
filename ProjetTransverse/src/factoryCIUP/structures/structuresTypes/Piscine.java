package factoryCIUP.structures.structuresTypes;

import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.Structure;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Piscine extends Structure {

    int nbCasiers;
    String accessibilite;   // avec ou sans amenagements pr les handicapés
    int nbBassins;
    double profondeur;
    String[] horrairesOuverture;
    String ouvertureLundiVendredi;  // format "De HH:MM à HH:MM"
    String ouvertureWeekend;    // format "De HH:MM à HH:MM"

    public Piscine(Nationnalite nationnalite, String nomMaison, double nGPS, double eGPS, int nbCasiers, String accessibilite, int nbBassins, double profondeur, String ouvertureLundiVendredi, String ouvertureWeekend) {
        super(nationnalite, nomMaison, nGPS, eGPS);
        this.nbCasiers=nbCasiers;
        this.accessibilite=accessibilite;
        this.nbBassins=nbBassins;
        this.profondeur=profondeur;
        this.ouvertureLundiVendredi=ouvertureLundiVendredi;
        this.ouvertureWeekend=ouvertureWeekend;
        this.horrairesOuverture= new String[]{ouvertureWeekend, ouvertureLundiVendredi};
    }





    public JPanel panel(JPanel parentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel supportPanel = new JPanel(new BorderLayout());

        String[] entetes = {"Horaire", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        File fichier = new File("src/factoryCIUP/FichierDonnee/EdtPiscine.txt");

        // Vérification du fichier
        if (!fichier.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return panel;
        }

        ArrayList<String[]> lignes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichier));
            String ligne;
            while ((ligne = br.readLine()) != null) {
                System.out.println("Ligne brute lue : " + ligne); // affichage ligne lue

                String[] parties = ligne.split("\\|");
                System.out.println("Parties après split : " + Arrays.toString(parties));  // Vérification result split

                if (parties.length == 8) {
                    lignes.add(parties);
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas 8 parties : " + ligne);  // Ligne ignorée
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Vérification des ligne ajouter
        if (lignes.isEmpty()) {
            System.out.println("Aucune ligne valide n'a été lue.");
        } else {
            System.out.println("Total des lignes lues : " + lignes.size());
        }

        // Convertion en tableau 2D
        String[][] donnees = new String[lignes.size()][8];
        for (int i = 0; i < lignes.size(); i++) {
            donnees[i] = lignes.get(i);
        }



        JTable edt = new JTable(donnees, entetes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        edt.setRowHeight(75);
        edt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        edt.setFillsViewportHeight(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < edt.getColumnCount(); i++) {
            edt.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        TableColumnModel columnModel = edt.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);

        edt.setBackground(Color.white);
        edt.setForeground(Color.black);
        edt.setShowGrid(true);
        edt.setGridColor(Color.lightGray);
        Font edtFont = new Font("Segoe UI", Font.PLAIN, 14);
        edt.setFont(edtFont);

        JTableHeader header = edt.getTableHeader();
        header.setBackground(Color.decode("#ADD8E6"));
        header.setForeground(Color.white);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setFont(edtFont);


        JScrollPane scrollPane = new JScrollPane(edt);
        supportPanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(supportPanel, BorderLayout.CENTER);

        return panel;
    }





}
