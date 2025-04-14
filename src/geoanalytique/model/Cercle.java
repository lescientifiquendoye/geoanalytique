package geoanalytique.model;
 
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.exception.VisiteurException;
import geoanalytique.util.GeoObjectVisitor;

/**
 * Modele mathematique pour les cercles.
 * 
 */
public class Cercle extends Ellipse {

        private Point centre;
        private double rayon;

        public Cercle(Point centre, double rayon,GeoAnalytiqueControleur controleur) {
            super(centre, rayon, rayon, controleur);
            this.centre = centre;
            this.rayon = rayon;
        }

        @Override
	public <T> T visitor(GeoObjectVisitor<T> obj) throws VisiteurException {
            // TODO: a completer d
                return obj.visitCercle(this);
	}

	@Override
	public boolean contient(Point p) {
            
            return super.contient(p);
	}
}
