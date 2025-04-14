package geoanalytique.graphique;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Graphique permettant d'afficher un texte dans une zone de dessin
 * 
 * @see Graphics#drawString(java.lang.String, int, int) 
 */
public class GTexte extends Graphique {
    private int x, y;
    private String txt;
    private Color color;
    
    public GTexte(int x, int y, String txt, Color color) {
        this(x, y, txt);
        this.color = color;
        // TODO: a completer d
    }
    
    public GTexte(int x, int y, String txt) {
        this.x = x;
        this.y = y;
        this.txt = txt;
        // TODO: a completer
    }
     
    
    /**
     * * @see Graphics#drawString(java.lang.String, int, int) 
     */
	@Override
	public void paint(Graphics g) {
        Color originalColor = g.getColor(); // On sauvegarde la couleur actuelle
        g.setColor(color);                  // On change la couleur
        g.drawString(txt, x, y);            // On dessine le texte
        g.setColor(originalColor);
		// TODO: a completer
	}
}
