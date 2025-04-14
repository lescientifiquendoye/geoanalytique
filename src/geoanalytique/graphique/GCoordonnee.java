package geoanalytique.graphique;

import java.awt.Color;
import java.awt.Graphics;

/**
 * La classe GCoordonnee représente un point graphique sur la vue.
 * Pour des raisons de visibilité, un point est affiché comme un petit disque sur le canevas.
 * Cette classe hérite de la classe abstraite {@link Graphique}.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class GCoordonnee extends Graphique {

    /**
     * La coordonnée X du point.
     */
    private final int x;

    /**
     * La coordonnée Y du point.
     */
    private final int y;

    /**
     * Taille utilisée pour représenter un point sur le canevas.
     * Un point est affiché comme un cercle de diamètre {@code TAILLE_POINT}.
     */
    public static final int TAILLE_POINT = 9;

    /**
     * Constructeur de la classe GCoordonnee.
     * Initialise un point graphique avec des coordonnées X et Y, et une couleur par défaut (bleu).
     * 
     * @param x La coordonnée X du point.
     * @param y La coordonnée Y du point.
     */
    public GCoordonnee(int x, int y) {
        this.x = x;
        this.y = y;
        color = Color.BLUE;
    }

    /**
     * Constructeur de la classe GCoordonnee.
     * Initialise un point graphique avec des coordonnées X et Y, et une couleur spécifiée.
     * 
     * @param x     La coordonnée X du point.
     * @param y     La coordonnée Y du point.
     * @param color La couleur du point.
     */
    public GCoordonnee(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Retourne la coordonnée X du point.
     * 
     * @return La coordonnée X.
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnée Y du point.
     * 
     * @return La coordonnée Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Vérifie si un autre objet est égal à cette instance de GCoordonnee.
     * Deux objets GCoordonnee sont égaux s'ils ont les mêmes coordonnées X et Y.
     * 
     * @param o L'objet à comparer.
     * @return {@code true} si les objets sont égaux, {@code false} sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof GCoordonnee) {
            GCoordonnee co = (GCoordonnee) o;
            return co.getX() == x && co.getY() == y;
        }
        return false;
    }

    /**
     * Dessine le point sur le canevas.
     * Le point est représenté comme un cercle de diamètre {@code TAILLE_POINT}.
     * 
     * @param g L'objet {@link Graphics} utilisé pour dessiner le point.
     */
    @Override
    public void paint(Graphics g) {
        Color save = g.getColor();
        g.setColor(color);
        g.fillOval(x - (TAILLE_POINT / 2), y - (TAILLE_POINT / 2), TAILLE_POINT, TAILLE_POINT);
        g.setColor(save);
    }

    /**
     * Retourne une représentation textuelle de l'objet GCoordonnee.
     * 
     * @return Une chaîne de caractères contenant les coordonnées X et Y.
     */
    @Override
    public String toString() {
        return "Coordonnee: x=" + x + " y=" + y;
    }
}