package factoryCIUP.structures.structuresTypes;

import factoryCIUP.structures.Nationnalite;
import factoryCIUP.structures.Structure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Theatre extends Structure {

    String nomTheatre;
    Nationnalite nationnalite;
    double nGPS,eGPS;
    ArrayList<Spectacle> spectacles;

    @Override
    public JPanel panel(JPanel parent) {
        JPanel Tpanel = new JPanel(new CardLayout());

        // page principale
        JPanel carte = new JPanel();
        carte.setLayout(new BoxLayout(carte, BoxLayout.Y_AXIS));
        carte.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Titre du theatre
        JLabel titreLabel = new JLabel(nomTheatre);
        titreLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        carte.add(titreLabel);
        carte.add(Box.createVerticalStrut(10));

        // Infos générales
        JLabel LBLNationalite = new JLabel("\uD83D\uDC64 Nationalité : " + nationnalite);
        LBLNationalite.setFont(new Font("SansSerif", Font.PLAIN, 14));
        LBLNationalite.setAlignmentX(Component.CENTER_ALIGNMENT);
        carte.add(LBLNationalite);

        JLabel LBLGps = new JLabel("\uD83D\uDD0D Coordonnées GPS : " + nGPS + " / " + eGPS);
        LBLGps.setFont(new Font("SansSerif", Font.PLAIN, 14));
        LBLGps.setAlignmentX(Component.CENTER_ALIGNMENT);
        carte.add(LBLGps);

        carte.add(Box.createVerticalStrut(15)); // Espace

        // Section spectacles
        JLabel LBLSpectacle = new JLabel("\uD83C\uDFAC Spectacles :");
        LBLSpectacle.setFont(new Font("SansSerif", Font.BOLD, 16));
        LBLSpectacle.setAlignmentX(Component.CENTER_ALIGNMENT);
        carte.add(LBLSpectacle);
        carte.add(Box.createVerticalStrut(8));

        if (spectacles == null || spectacles.isEmpty()) {
            JLabel LBLVidespec = new JLabel("Aucun spectacle pour le moment.");
            LBLVidespec.setFont(new Font("SansSerif", Font.ITALIC, 13));
            LBLVidespec.setForeground(Color.GRAY);
            LBLVidespec.setAlignmentX(Component.CENTER_ALIGNMENT);
            carte.add(LBLVidespec);
        } else {
            for (Spectacle s : new ArrayList<>(spectacles)) {
                JPanel AjtSptectacle = new JPanel();
                AjtSptectacle.setLayout(new BorderLayout());
                AjtSptectacle.setBackground(new Color(255, 255, 255));
                AjtSptectacle.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                        BorderFactory.createEtchedBorder(Color.BLACK,Color.BLACK)
                ));
                AjtSptectacle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

                JLabel LBLspec = new JLabel("<html><b>" + s.getNomSpectacle() + "</b> — "
                        + s.getDate() + "<br><i>Thème : " + s.getTheme().getNomTheme() + "</i></html>");
                LBLspec.setFont(new Font("SansSerif", Font.PLAIN, 13));

                JButton BTNSuprr = new JButton("❌");
                BTNSuprr.setFocusPainted(false);
                BTNSuprr.setFont(new Font("SansSerif", Font.PLAIN, 12));
                BTNSuprr.setBackground(new Color(255, 230, 230));
                BTNSuprr.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                BTNSuprr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                BTNSuprr.addActionListener(e -> {
                    spectacles.remove(s);
                    Tpanel.removeAll();
                    Tpanel.add(this.panel(parent));  // appel récursif avec parent
                    Tpanel.revalidate();
                    Tpanel.repaint();
                });

                AjtSptectacle.add(LBLspec, BorderLayout.CENTER);
                AjtSptectacle.add(BTNSuprr, BorderLayout.EAST);
                carte.add(AjtSptectacle);
                carte.add(Box.createVerticalStrut(8));
            }
        }

        JScrollPane scrollPane = new JScrollPane(carte);
        scrollPane.setBorder(null); // pour garder un style propre
        scrollPane.getVerticalScrollBar().setUnitIncrement(12); // vitesse de scroll
        Tpanel.add(scrollPane, "vuePrincipale");


        // Bouton ajout
        JButton ajouterBtn = new JButton("+ Ajouter un spectacle");
        ajouterBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        ajouterBtn.addActionListener(e -> {
            JTextField nomField = new JTextField();
            JTextField dateField = new JTextField();
            JCheckBox CBXsurprise = new JCheckBox("Surprise");
            JComboBox<THEME> themeCombo = new JComboBox<>(THEME.values());

            JPanel formPanel = new JPanel(new GridLayout(0, 1));
            formPanel.add(new JLabel("Nom du spectacle :"));
            formPanel.add(nomField);
            formPanel.add(new JLabel("Date (JJ/MM/AAAA : hh/mm) :"));
            formPanel.add(dateField);
            formPanel.add(CBXsurprise);
            formPanel.add(new JLabel("Thème :"));
            formPanel.add(themeCombo);

            int result = JOptionPane.showConfirmDialog(null, formPanel,
                    "Ajouter un spectacle", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String nom = nomField.getText().trim();
                String date = dateField.getText().trim();
                boolean surprise = CBXsurprise.isSelected();
                THEME theme = (THEME) themeCombo.getSelectedItem();

                Spectacle nouveau = new Spectacle(nom, date, surprise, theme);
                this.ajouterSpectacle(nouveau);

                Tpanel.removeAll();
                Tpanel.add(this.panel(parent));  // appel récursif avec parent
                Tpanel.revalidate();
                Tpanel.repaint();
            }
        });

        carte.add(Box.createVerticalStrut(15));
        carte.add(ajouterBtn);

        return Tpanel;
    }

    public Theatre(Nationnalite nationnalite, String nomMaison, double nGPS, double eGPS) {
        super(nationnalite, nomMaison, nGPS, eGPS);
        this.nomTheatre=nomMaison;
        this.nationnalite=nationnalite;
        this.eGPS=eGPS;
        this.nGPS=nGPS;
        this.spectacles=new ArrayList<>();
    }

    public String getNomTheatre() {
        return nomTheatre;
    }

    public void ajouterSpectacle(Spectacle spectacle) {
        this.spectacles.add(spectacle);
    }

    public enum THEME {
        LA_LIBERTE("La liberte"),
        LES_PRINCIPES("Les principes"),
        LES_SAISONS("Les saisons"),
        LE_TRAVAIL("Le travail");

        String nomTheme;

        THEME(String nomTheme) {
            this.nomTheme = nomTheme;
        }

        public String getNomTheme() {
            return nomTheme;
        }
    }

    public static THEME getRandomTheme() {
            THEME[] themes = THEME.values(); // tableau de toutes les valeurs de l'enum
            Random rand = new Random();
            return themes[rand.nextInt(themes.length)];
    }

    public static class Spectacle{
        String nomSpectacle;
        String Date; // format JJ/MM/AAAA : hh/mm
        THEME theme;
        String nomTheme;

        Boolean surprise;

        public Spectacle(String nom, String Date, Boolean surprise, THEME theme) {
            this.nomSpectacle=nom;
            this.Date=Date;
            this.surprise=surprise;
            if(surprise){
                this.theme=getRandomTheme();
            }else
                this.theme=theme;
            this.nomTheme= theme.getNomTheme();
        }

        public String getNomSpectacle() {
            return nomSpectacle;
        }
        public String getDate() {
            return Date;
        }
        public THEME getTheme() {
            return theme;
        }

    }

    public void setNomTheatre(String nomTheatre) {
        this.nomTheatre = nomTheatre;
    }

    @Override
    public Nationnalite getNationnalite() {
        return nationnalite;
    }

    @Override
    public void setNationnalite(Nationnalite nationnalite) {
        this.nationnalite = nationnalite;
    }

    @Override
    public double getnGPS() {
        return nGPS;
    }

    public void setnGPS(double nGPS){
        this.nGPS=nGPS;
    }

    @Override
    public double geteGPS() {
        return eGPS;
    }

    @Override
    public void seteGPS(double eGPS) {
        this.eGPS = eGPS;
    }

    public ArrayList<Spectacle> getSpectacles() {
        return spectacles;
    }

    public void setSpectacles(ArrayList<Spectacle> spectacles) {
        this.spectacles = spectacles;
    }
}
