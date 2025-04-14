package geoanalytique.graphique;

import java.awt.Color;
import java.awt.Graphics;

/**
 * La classe GLigne représente une ligne graphique tracée sur le canevas.
 * Elle permet de définir une ligne à partir de deux points (x1, y1) et (x2, y2),
 * avec une couleur optionnelle.
 * 
 * Cette classe hérite de la classe abstraite {@link Graphique}.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class GLigne extends Graphique {

    /**
     * La coordonnée X du premier point de la ligne.
     */
    private int x1;

    /**
     * La coordonnée Y du premier point de la ligne.
     */
    private int y1;

    /**
     * La coordonnée X du second point de la ligne.
     */
    private int x2;

    /**
     * La coordonnée Y du second point de la ligne.
     */
    private int y2;

    /**
     * Constructeur de la classe GLigne.
     * Initialise une ligne avec deux points (x1, y1) et (x2, y2).
     * La couleur par défaut est utilisée.
     * 
     * @param x1 La coordonnée X du premier point.
     * @param y1 La coordonnée Y du premier point.
     * @param x2 La coordonnée X du second point.
     * @param y2 La coordonnée Y du second point.
     */
    public GLigne(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Constructeur de la classe GLigne.
     * Initialise une ligne avec deux points (x1, y1) et (x2, y2), et une couleur spécifiée.
     * 
     * @param x1    La coordonnée X du premier point.
     * @param y1    La coordonnée Y du premier point.
     * @param x2    La coordonnée X du second point.
     * @param y2    La coordonnée Y du second point.
     * @param color La couleur de la ligne.
     */
    public GLigne(int x1, int y1, int x2, int y2, Color color) {
        this(x1, y1, x2, y2);
        this.color = color;
    }

    /**
     * Vérifie si un autre objet est égal à cette instance de GLigne.
     * Deux lignes sont considérées comme égales si leurs points de départ et d'arrivée
     * sont identiques.
     * 
     * @param o L'objet à comparer.
     * @return {@code true} si les lignes sont égales, {@code false} sinon.
     */
    @Override
    public boolean equals(Object o) {
        return o.getClass() == this.getClass() &&
               ((GLigne) o).x1 == this.x1 &&
               ((GLigne) o).y1 == this.y1 &&
               ((GLigne) o).x2 == this.x2 &&
               ((GLigne) o).y2 == this.y2;
    }

    /**
     * Définit la couleur de la ligne.
     * 
     * @param c La nouvelle couleur de la ligne.
     */
    @Override
    public void setCouleur(Color c) {
        this.color = c;
    }

    /**
     * Dessine la ligne sur le canevas.
     * 
     * @param g L'objet {@link Graphics} utilisé pour dessiner la ligne.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
    }

    /**
     * Retourne une représentation textuelle de la ligne.
     * 
     * @return Une chaîne de caractères décrivant la ligne avec ses coordonnées.
     */
    @Override
    public String toString() {
        return "Ligne de (" + this.x1 + ", " + this.y1 + ") à (" + this.x2 + ", " + this.y2 + ")";
    }
}