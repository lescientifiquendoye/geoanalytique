package geoanalytique.controleur;

import geoanalytique.model.GeoObject;
import geoanalytique.model.Point;
import geoanalytique.model.ViewPort;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import geoanalytique.model.Segment;
/**
 * La classe Selectionner permet de gérer les opérations sur un objet géométrique sélectionné.
 * Elle offre des fonctionnalités pour effectuer des actions spécifiques sur des points, 
 * comme le déplacement ou le calcul de distance.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class Selectionner {

    /**
     * L'objet géométrique actuellement sélectionné.
     */
    GeoObject selectedObject;

    /**
     * Le contrôleur principal de l'application GeoAnalytique.
     */
    GeoAnalytiqueControleur controleur;

    /**
     * La zone d'affichage (viewport) utilisée pour convertir les coordonnées entre le modèle et la vue.
     */
    ViewPort viewport;

    /**
     * Constructeur de la classe Selectionner.
     * Initialise les informations nécessaires pour effectuer des opérations sur un objet sélectionné.
     * 
     * @param controleur      Le contrôleur principal de l'application.
     * @param selectedObject  L'objet géométrique actuellement sélectionné.
     * @param viewport        La zone d'affichage utilisée pour les conversions de coordonnées.
     */
    public Selectionner(GeoAnalytiqueControleur controleur, GeoObject selectedObject, ViewPort viewport) {
        this.controleur = controleur;
        this.selectedObject = selectedObject;
        this.viewport = viewport;
    }

    /**
     * Effectue une opération sur un point sélectionné.
     * Les opérations disponibles incluent le déplacement du point ou le calcul de la distance
     * entre le point et une autre position spécifiée par l'utilisateur.
     * 
     * @param point Le point sur lequel effectuer l'opération.
     */
    public void operationSurPoint(Point point) {
        String[] options = {"Déplacer", "Calculer distance", "Annuler"};
        int choix = JOptionPane.showOptionDialog(
            null,
            "Choisissez une opération sur le point \"" + point.getNom() + "\" :",
            "Opération sur Point",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choix == 0 || choix == 1) {
            JTextField xField = new JTextField();
            JTextField yField = new JTextField();

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("X :"));
            panel.add(xField);
            panel.add(new JLabel("Y :"));
            panel.add(yField);

            int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                choix == 0 ? "Déplacer le point" : "Calculer la distance",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double x = Double.parseDouble(xField.getText());
                    double y = Double.parseDouble(yField.getText());

                    if (choix == 0) {
                        // Déplacement du point
                        if (selectedObject instanceof Point p) {
                            p.deplacer(x, y);
                        }
                        JOptionPane.showMessageDialog(null, "✅ Point déplacé avec succès !");
                    } else {
                        // Calcul de distance
                        double dx = x - point.getX();
                        double dy = y - point.getY();
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        JOptionPane.showMessageDialog(null, String.format("📏 Distance = %.2f unités", distance));
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "❌ Coordonnées invalides !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

 

    public void operationSurSegment(Segment segment) {
    String[] options = {"📏 Longueur", "🎯 Centre", "❌ Annuler"};
    int choix = JOptionPane.showOptionDialog(
        null,
        "Choisissez une opération sur le segment \"" + segment.getName() + "\" :",
        "Opération sur Segment",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]
    );

    if (choix == 0) {
        // Calcul de la longueur
        double longueur = segment.getLongueur();
        JOptionPane.showMessageDialog(null, String.format("📏 Longueur du segment : %.2f unités", longueur));
    } else if (choix == 1) {
        // Calcul du centre (milieu)
        Point centre = segment.getCentre();
        JOptionPane.showMessageDialog(null, String.format(
            "🎯 Centre du segment : (%.2f, %.2f)",
            centre.getX(), centre.getY()
        ));
    }
}


}