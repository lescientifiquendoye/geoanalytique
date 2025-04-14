package geoanalytique.graphique;

import geoanalytique.model.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * La classe GPolygone représente un polygone graphique tracé sur le canevas.
 * Un polygone est défini par une liste de points connectés par des segments.
 * Cette classe permet de dessiner le polygone et de gérer ses propriétés graphiques, 
 * comme sa couleur.
 * 
 * Cette classe hérite de la classe abstraite {@link Graphique}.
 * 
 * <b>Note :</b> Certaines méthodes sont encore à compléter.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class GPolygone extends Graphique {

    /**
     * Liste des points définissant les sommets du polygone.
     */
    private List<Point> polyPoints;

    /**
     * Couleur utilisée pour dessiner le polygone.
     */
    private Color color;

    /**
     * Constructeur de la classe GPolygone.
     * Initialise un polygone avec une liste de points et utilise une couleur par défaut.
     * 
     * @param polyPoints La liste des points définissant les sommets du polygone.
     */
    public GPolygone(List<Point> polyPoints) {
        this.polyPoints = polyPoints;
    }

    /**
     * Constructeur de la classe GPolygone.
     * Initialise un polygone avec une liste de points et une couleur spécifiée.
     * 
     * @param polyPoints La liste des points définissant les sommets du polygone.
     * @param color      La couleur utilisée pour dessiner le polygone.
     */
    public GPolygone(List<Point> polyPoints, Color color) {
        this(polyPoints);
        this.color = color;
    }

    /**
     * Vérifie si un autre objet est égal à cette instance de GPolygone.
     * Deux polygones sont considérés comme égaux si leurs listes de points sont identiques.
     * 
     * @param o L'objet à comparer.
     * @return {@code true} si les polygones sont égaux, {@code false} sinon.
     */
    @Override
    public boolean equals(Object o) {
        // TODO: Implémenter la logique de comparaison
        return false;
    }

    /**
     * Définit la couleur du polygone.
     * 
     * @param c La nouvelle couleur du polygone.
     */
    @Override
    public void setCouleur(Color c) {
        this.color = c;
    }

    /**
     * Dessine le polygone sur le canevas.
     * Les segments reliant les points sont tracés pour former le polygone.
     * 
     * @param g L'objet {@link Graphics} utilisé pour dessiner le polygone.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);

        // Dessiner les segments reliant les points
        for (int i = 0; i < this.polyPoints.size() - 1; i++) {
            g.drawLine(
                (int) this.polyPoints.get(i).getX(),
                (int) this.polyPoints.get(i).getY(),
                (int) this.polyPoints.get(i + 1).getX(),
                (int) this.polyPoints.get(i + 1).getY()
            );
        }

        // Relier le dernier point au premier pour fermer le polygone
        g.drawLine(
            (int) this.polyPoints.get(this.polyPoints.size() - 1).getX(),
            (int) this.polyPoints.get(this.polyPoints.size() - 1).getY(),
            (int) this.polyPoints.get(0).getX(),
            (int) this.polyPoints.get(0).getY()
        );
    }

    /**
     * Retourne une représentation textuelle du polygone.
     * 
     * @return Une chaîne de caractères décrivant le polygone.
     */
    @Override
    public String toString() {
        // TODO: Implémenter une description textuelle du polygone
        return "";
    }
}