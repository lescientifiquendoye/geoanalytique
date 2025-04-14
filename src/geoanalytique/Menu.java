package geoanalytique;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.model.Carre;
import geoanalytique.model.Ellipse;
import geoanalytique.model.Point;
import geoanalytique.model.Polygone;
import geoanalytique.model.Rectangle;
import geoanalytique.model.Segment;
import geoanalytique.model.Triangle;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Menu extends JMenuBar {

    /**
     * Le contrôleur principal de l'application GeoAnalytique.
     */
    GeoAnalytiqueControleur controleur;

  public Menu(GeoAnalytiqueControleur controleur){  // Menu "Fichier"
        this.controleur = controleur;
        JMenu menuAjouter = new JMenu("+ Ajouter");
        menuAjouter.setForeground(Color.BLUE);

        // Liste des actions avec nom et méthode associée
        List<ActionFigure> actions = List.of(
            new ActionFigure("Point", this::addPoint),
            new ActionFigure("Segment", this::addSegment),
            new ActionFigure("Cercle", this::addCercle),
            new ActionFigure("Ellipse", () -> this.addEllipse(false)),
            new ActionFigure("Polygone", this::addPolygone),
            new ActionFigure("Rectangle", this::addRectangle),
            new ActionFigure("Carre", this::addCarre),
            new ActionFigure("Trangle", this::addTriangle)
        );

        // Ajoute chaque JMenuItem généré automatiquement
        for (ActionFigure actionFigure : actions) {
            menuAjouter.add(actionFigure.getMenuItem());
        }

        this.add(menuAjouter);

    
  }


  


   //classe interne représentant une action de menu
    private class ActionFigure {
        private final String nom;
        private final Runnable action;
        private final JMenuItem menuItem;

        /**
         * Constructeur de la classe ActionFigure.
         * 
         * @param nom    Le nom de l'action (affiché dans le menu).
         * @param action L'action à exécuter lorsque l'élément de menu est sélectionné.
         */
        public ActionFigure(String nom, Runnable action) {
            this.nom = nom;
            this.action = action;
            this.menuItem = new JMenuItem(nom);
            this.menuItem.addActionListener(e -> this.action.run());
        }

        /**
         * Retourne l'élément de menu associé à cette action.
         * 
         * @return L'objet JMenuItem correspondant.
         */
        public JMenuItem getMenuItem() {
            return this.menuItem;
        }
    }


    // ==== Méthodes des actions ====


/// Méthode pour vérifier si les coordonnées sont valides
    private boolean isCoordValide(double x, double y) {
        return x >= -10 && x <= 10 && y >= -10 && y <= 10;
    }


    private void addPoint() {
        JTextField nomField = new JTextField(15);
        JTextField xField = new JTextField(15);
        JTextField yField = new JTextField(15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // marges
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Nom du point :"), gbc);
        gbc.gridx = 1;
        panel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Coordonnée X :"), gbc);
        gbc.gridx = 1;
        panel.add(xField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Coordonnée Y :"), gbc);
        gbc.gridx = 1;
        panel.add(yField, gbc);

        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "➕ Ajouter un point",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

     

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nom = nomField.getText().trim();
                double x = Double.parseDouble(xField.getText().trim());
                double y = Double.parseDouble(yField.getText().trim());

                if (x < -10 || x > 10 || y < -10 || y > 10) {
                    throw new Exception("Les coordonnées doivent être comprises entre -10 et +10.");
                }

                controleur.addObjet(new Point(nom, x, y, controleur));
                JOptionPane.showMessageDialog(null, "✅ Point ajouté avec succès !");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                "❌ Coordonnées invalides !",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                "❌ " + e.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    
    }

    private void addSegment() {
    JTextField x1Field = new JTextField(10);
    JTextField y1Field = new JTextField(10);
    JTextField x2Field = new JTextField(10);
    JTextField y2Field = new JTextField(10);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(new JLabel("Coordonnées du point A :"));
    panel.add(new JLabel("X1 :"));
    panel.add(x1Field);
    panel.add(new JLabel("Y1 :"));
    panel.add(y1Field);

    panel.add(Box.createVerticalStrut(10)); // espace

    panel.add(new JLabel("Coordonnées du point B :"));
    panel.add(new JLabel("X2 :"));
    panel.add(x2Field);
    panel.add(new JLabel("Y2 :"));
    panel.add(y2Field);

    int result = JOptionPane.showConfirmDialog(
        null,
        panel,
        "Ajouter un segment",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            double x1 = Double.parseDouble(x1Field.getText().trim());
            double y1 = Double.parseDouble(y1Field.getText().trim());
            double x2 = Double.parseDouble(x2Field.getText().trim());
            double y2 = Double.parseDouble(y2Field.getText().trim());

            // Validation des coordonnées entre -10 et +10
            if (!isCoordValide(x1, y1) || !isCoordValide(x2, y2)) {
                throw new NumberFormatException("Toutes les coordonnées doivent être entre -10 et +10.");
            }

            Point a = new Point(x1, y1, controleur);
            Point b = new Point(x2, y2, controleur);
            controleur.addObjet(new Segment(a, b, controleur));

            JOptionPane.showMessageDialog(null, "✅ Segment ajouté avec succès !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

   private void addEllipse(boolean  estCercle) {
        JTextField xField = new JTextField(10);
        JTextField yField = new JTextField(10);
        JTextField aField = new JTextField(10); // Grand rayon
        JTextField bField = new JTextField(10); // Petit rayon

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        String type =  estCercle? "du cecle" : "de l'ellipse :";
        panel.add(new JLabel("Position "+ type));
        panel.add(new JLabel("X :"));
        panel.add(xField);
        panel.add(new JLabel("Y :"));
        panel.add(yField);

        panel.add(Box.createVerticalStrut(10)); // espace

        panel.add(new JLabel("Rayons "+ type));
        if (!estCercle) {
            panel.add(new JLabel("Grand rayon (a) :"));
        }
        panel.add(aField);
     
        if (!estCercle) {
            panel.add(new JLabel("Petit rayon (b) :"));
            panel.add(bField);
        } else {
            bField=aField;
        }
       

        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Ajouter une ellipse",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                double x = Double.parseDouble(xField.getText().trim());
                double y = Double.parseDouble(yField.getText().trim());
                double a = Double.parseDouble(aField.getText().trim());
                double b = Double.parseDouble(bField.getText().trim());

                // Vérification des coordonnées et des rayons
                if (!isCoordValide(x, y)) {
                    throw new NumberFormatException("Les coordonnées doivent être entre -10 et +10.");
                }
                if (a <= 0 || b <= 0) {
                    throw new NumberFormatException("Les rayons doivent être strictement positifs.");
                }

                Point centre = new Point(x, y, controleur);
                controleur.addObjet(new Ellipse(centre, a, b, controleur));

                JOptionPane.showMessageDialog(null, "✅ Ellipse ajoutée avec succès !");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "❌ " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

private void addCercle() {
        addEllipse(true);
    }

    /**
     * Ajoute un polygone à l'application après avoir demandé les coordonnées des points à l'utilisateur.
     */
private void addPolygone() {
    JTextField nomField = new JTextField("Polygone", 15);
    JPanel panelPoints = new JPanel();
    panelPoints.setLayout(new BoxLayout(panelPoints, BoxLayout.Y_AXIS));
    List<JTextField[]> pointFields = new ArrayList<>();

    // Fonction pour ajouter dynamiquement un champ de point
    Runnable addPointField = () -> {
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);
        JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligne.add(new JLabel("X:"));
        ligne.add(xField);
        ligne.add(new JLabel("Y:"));
        ligne.add(yField);
        panelPoints.add(ligne);
        pointFields.add(new JTextField[]{xField, yField});
        panelPoints.revalidate();
        panelPoints.repaint();
    };

    // Ajouter 3 points minimum par défaut
    for (int i = 0; i < 3; i++) addPointField.run();

    JButton btnAjouterPoint = new JButton("➕ Ajouter un point");
    btnAjouterPoint.addActionListener(e -> addPointField.run());

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(new JLabel("Nom du polygone :"));
    mainPanel.add(nomField);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(new JLabel("Coordonnées des points :"));
    mainPanel.add(new JScrollPane(panelPoints));
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(btnAjouterPoint);

    int result = JOptionPane.showConfirmDialog(
        null,
        mainPanel,
        "Ajouter un polygone",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            String nom = nomField.getText().trim();
            Collection<Point> points = new ArrayList<>();

            for (JTextField[] fields : pointFields) {
                double x = Double.parseDouble(fields[0].getText().trim());
                double y = Double.parseDouble(fields[1].getText().trim());

                if (!isCoordValide(x, y)) {
                    throw new NumberFormatException("Les coordonnées doivent être entre -10 et +10.");
                }

                points.add(new Point(x, y, controleur));
            }

            if (points.size() < 3) {
                JOptionPane.showMessageDialog(null, "❌ Au moins 3 points sont nécessaires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controleur.addObjet(new Polygone(nom, points, controleur) {
                @Override
                public Segment getSegment(int nb) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public double calculerAire() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public Point calculerCentreGravite() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });

            JOptionPane.showMessageDialog(null, "✅ Polygone ajouté avec succès !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void addRectangle() {
    JTextField nomField = new JTextField("Rectangle", 15);
    JTextField xField = new JTextField(5);
    JTextField yField = new JTextField(5);
    JTextField longueurField = new JTextField(5);
    JTextField largeurField = new JTextField(5);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;

    panel.add(new JLabel("Nom du rectangle :"), gbc);
    gbc.gridx = 1;
    panel.add(nomField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Coordonnée X du coin haut-gauche :"), gbc);
    gbc.gridx = 1;
    panel.add(xField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Coordonnée Y du coin haut-gauche :"), gbc);
    gbc.gridx = 1;
    panel.add(yField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Longueur :"), gbc);
    gbc.gridx = 1;
    panel.add(longueurField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Largeur :"), gbc);
    gbc.gridx = 1;
    panel.add(largeurField, gbc);

    int result = JOptionPane.showConfirmDialog(
        null,
        panel,
        "➕ Ajouter un rectangle",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            String nom = nomField.getText().trim();
            double x = Double.parseDouble(xField.getText().trim());
            double y = Double.parseDouble(yField.getText().trim());
            double longueur = Double.parseDouble(longueurField.getText().trim());
            double largeur = Double.parseDouble(largeurField.getText().trim());

            if (!isCoordValide(x, y) || !isCoordValide(x + longueur, y - largeur)) {
                throw new IllegalArgumentException("Les coordonnées des points du rectangle doivent être entre -10 et +10.");
            }

            List<Point> points = List.of(
                new Point(x, y, controleur),
                new Point(x + longueur, y, controleur),
                new Point(x + longueur, y - largeur, controleur),
                new Point(x, y - largeur, controleur)
            );

            Rectangle rectangle = new Rectangle(nom,points.get(0), longueur, largeur, controleur);
            controleur.addObjet(rectangle);

            JOptionPane.showMessageDialog(null, "✅ Rectangle ajouté avec succès !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Entrées invalides. Veuillez entrer des nombres valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void addTriangle() {
    JTextField nomField = new JTextField("Triangle", 15);
    JPanel panelPoints = new JPanel();
    panelPoints.setLayout(new BoxLayout(panelPoints, BoxLayout.Y_AXIS));
    List<JTextField[]> pointFields = new ArrayList<>();

    // Fonction pour créer un champ de coordonnées
    Runnable addPointField = () -> {
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);
        JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligne.add(new JLabel("X:"));
        ligne.add(xField);
        ligne.add(new JLabel("Y:"));
        ligne.add(yField);
        panelPoints.add(ligne);
        pointFields.add(new JTextField[]{xField, yField});
    };

    // Ajoute 3 champs de point (triangle = 3 sommets)
    for (int i = 0; i < 3; i++) addPointField.run();

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(new JLabel("Nom du triangle :"));
    mainPanel.add(nomField);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(new JLabel("Coordonnées des 3 sommets :"));
    mainPanel.add(panelPoints);

    int result = JOptionPane.showConfirmDialog(
        null,
        mainPanel,
        "Ajouter un triangle",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            String nom = nomField.getText().trim();
            List<Point> points = new ArrayList<>();

            for (JTextField[] fields : pointFields) {
                double x = Double.parseDouble(fields[0].getText().trim());
                double y = Double.parseDouble(fields[1].getText().trim());

                if (!isCoordValide(x, y)) {
                    throw new NumberFormatException("Les coordonnées doivent être entre -10 et +10.");
                }

                points.add(new Point(x, y, controleur));
            }

            if (points.size() != 3) {
                JOptionPane.showMessageDialog(null, "❌ Un triangle doit avoir exactement 3 points.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Création du triangle
            Triangle triangle = new Triangle(nom, points.get(0), points.get(1), points.get(2), controleur);
            controleur.addObjet(triangle);

            JOptionPane.showMessageDialog(null, "✅ Triangle ajouté avec succès !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}


private void addCarre() {
    JTextField nomField = new JTextField("Carre", 15);
    JTextField xField = new JTextField(5);
    JTextField yField = new JTextField(5);
    JTextField coteField = new JTextField(5);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;

    panel.add(new JLabel("Nom du carre :"), gbc);
    gbc.gridx = 1;
    panel.add(nomField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Coordonnée X du coin haut-gauche :"), gbc);
    gbc.gridx = 1;
    panel.add(xField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Coordonnée Y du coin haut-gauche :"), gbc);
    gbc.gridx = 1;
    panel.add(yField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("côté :"), gbc);
    gbc.gridx = 1;
    panel.add(coteField, gbc);

    int result = JOptionPane.showConfirmDialog(
        null,
        panel,
        "➕ Ajouter un Carre",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            String nom = nomField.getText().trim();
            double x = Double.parseDouble(xField.getText().trim());
            double y = Double.parseDouble(yField.getText().trim());
            double cote = Double.parseDouble(coteField.getText().trim());

            if (!isCoordValide(x, y) || !isCoordValide(x + cote, y - cote)) {
                throw new IllegalArgumentException("Les coordonnées des points du Carre doivent être entre -10 et +10.");
            }

            List<Point> points = List.of(
                new Point(x, y, controleur),
                new Point(x + cote, y, controleur),
                new Point(x + cote, y - cote, controleur),
                new Point(x, y - cote, controleur)
            );

            Carre rectangle = new Carre(nom,points.get(0), cote,controleur);
            controleur.addObjet(rectangle);

            JOptionPane.showMessageDialog(null, "✅ Carre ajouté avec succès !");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Entrées invalides. Veuillez entrer des nombres valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

}