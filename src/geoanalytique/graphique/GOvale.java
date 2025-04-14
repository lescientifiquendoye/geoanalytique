package geoanalytique.graphique;
 
import java.awt.Color;
import java.awt.Graphics;

/**
 * Objet graphique permettant de dessiner un ovale quelconque a la Java
 * (ellipse ou cercle).
 * 
 * 
 * @see java.awt.Graphics#drawOval(int, int, int, int) 
 */
public class GOvale extends Graphique {
	private  int x , y,w,h;Color color;

	public GOvale(int x,int y,int w,int h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;

            // TODO: a completer d
	}

	public GOvale(int x,int y,int w,int h, Color color) {
		this(x, y, w, h);
		this.color=color;

            // TODO: a completer d
	}

	/**
	 * @see geoanalytique.graphique.Graphique#paint(java.awt.Graphics)
         * @see java.awt.Graphics#drawOval(int, int, int, int) 
	 */
	@Override
	public void paint(Graphics g) {
		g.drawOval(x, y, w, h);
            // TODO: a completer d
	}

}
