package geoanalytique.model;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.exception.VisiteurException;
import geoanalytique.util.GeoObjectVisitor;

/** 
 * Modele mathematique pour les ellipses
 * 
 */
public class Ellipse extends Surface {

    private final Point centre;
    private double rayonX;
    private double rayonY;
    private GeoAnalytiqueControleur controleur;

   public  Ellipse(Point centre, double rayonX, double rayonY,GeoAnalytiqueControleur controleur) {
        super(controleur); //centre.getNom()
        this.centre = centre;
        this.rayonX = rayonX;
        this.rayonY = rayonY;
        this.controleur = controleur;
    }

    @Override
    public double calculerAire() {
        // TODO: a completer
        throw new UnsupportedOperationException("Not supported yet.");
        
    }

    
    @Override
    public boolean equals(Object o) {
        // TODO: a completer
        return false;
    }
    
	@Override
	public boolean contient(Point p) {
        // TODO: a completer d
        double a = this.rayonX ;
        double b = this.rayonY;
        double dx = p.getX() - this.getCentre().getX();
        double dy = p.getY() - this.getCentre().getY();

        return (dx * dx) / (a * a) + (dy * dy) / (b * b)<=1;
	}
    
    public Point getCentre() {
        return new Point(centre.getX()+this.rayonX, centre.getY()+this.rayonY, this.controleur);
    }
    public double getRayonX() {
        return rayonX;
    }
    public double getRayonY() {
        return rayonY;
    }
    
    @Override
	public <T> T visitor(GeoObjectVisitor<T> obj) throws VisiteurException {
            // TODO: a completer d

          return obj.visitEllipse(this); 
	}

    @Override
    public Point calculerCentreGravite() {
        // TODO: a completer d
        return new Point(this.centre.getX()+this.rayonX, this.centre.getY()+this.rayonY, this.controleur);
    }
}