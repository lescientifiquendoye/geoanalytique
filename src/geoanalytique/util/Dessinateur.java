package geoanalytique.util;
 
import geoanalytique.exception.VisiteurException;
import geoanalytique.graphique.GCoordonnee;
import geoanalytique.graphique.GLigne;
import geoanalytique.graphique.GOvale;
import geoanalytique.graphique.GPolygone;
import geoanalytique.graphique.Graphique;
import geoanalytique.model.Cercle;
import geoanalytique.model.Droite;
import geoanalytique.model.Ellipse;
import geoanalytique.model.Point;
import geoanalytique.model.Polygone;
import geoanalytique.model.Segment;
import geoanalytique.model.ViewPort;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette objet est utilise par le presenteur, pour 'convertir' les modeles
 * en objet graphique utilisable par la vue. Le dessinateur repose sur le design pattern
 * Visitor. 
 * 
 * 
 *
 */
public class Dessinateur implements GeoObjectVisitor<Graphique> {

    private ViewPort viewport;
    
	/**
	 * 
	 */
	public Dessinateur(ViewPort viewport) {
            this.viewport = viewport;
	}

	/**
	 * @see geoanalytique.model.GeoObjectVisitor#visitDroite(geoanalytique.model.Droite)
	 */
	@Override
	public Graphique visitDroite(Droite d) throws VisiteurException {
		 // On calcule 2 points sur la droite (ex : x = 0 et x = 1)
		 double x1 = 0;
		 double x2 = 1;

		 double y1 = d.getOrdonnée(x1);
		 double y2 = d.getOrdonnée(x2);
	 
		 GCoordonnee p1 = viewport.convert(x1, y1);
		 GCoordonnee p2 = viewport.convert(x2, y2);
		 return new GLigne(p1.getX(), p1.getY(), p2.getX(), p2.getY());

        // TODO: a completer d
	}

	/**
	 * @see geoanalytique.model.GeoObjectVisitor#visitEllipse(geoanalytique.model.Ellipse)
	 */
	@Override
	public Graphique visitEllipse(Ellipse e) throws VisiteurException {
		GCoordonnee centre = viewport.convert((double)e.getCentre().getX(), e.getCentre().getY());
    int rayonX = (int)(e.getRayonX() * viewport.getScale());
    int rayonY = (int)(e.getRayonY() * viewport.getScale());

    return new GOvale(centre.getX(), centre.getY(), rayonX, rayonY);
            // TODO: a completer d
	}

	@Override
	public Graphique visitCercle(Cercle e) throws VisiteurException {
		GCoordonnee centre = viewport.convert((double)e.getCentre().getX(), e.getCentre().getY());
    int rayonX = (int)(e.getRayonX() * viewport.getScale());
    int rayonY = (int)(e.getRayonY() * viewport.getScale());

    return new GOvale(centre.getX(), centre.getY(), rayonX, rayonY);
            // TODO: a completer d
	}

	/**
	 * @see geoanalytique.model.GeoObjectVisitor#visitPoint(geoanalytique.model.Point)
	 */
    @Override
	public Graphique visitPoint(Point o) throws VisiteurException {
            GCoordonnee c = viewport.convert((double)((Point)o).getX(),((Point)o).getY());
            return c;
	}

	/**
	 * @see geoanalytique.model.GeoObjectVisitor#visitPolygone(geoanalytique.model.Polygone)
	 */
	public Graphique visitPolygone(Polygone p) throws VisiteurException {

		 List<Point> convertedPoints = new ArrayList<>();

    for (Point pt : p.getPoints()) {
        GCoordonnee gc = viewport.convert((double)pt.getX(), pt.getY());
        convertedPoints.add(new Point((int) gc.getX(), (int) gc.getY(),pt.getControleur()));
    }
		return new GPolygone((List<Point>)convertedPoints);
		
		// TODO: a completer d
			
	}

	/**
	 * @see geoanalytique.model.GeoObjectVisitor#visitSegment(geoanalytique.model.Segment)
	 */
	public Graphique visitSegment(Segment s) throws VisiteurException {
		GCoordonnee p1 = viewport.convert((double)s.getP1().getX(), s.getP1().getY());
		GCoordonnee p2 = viewport.convert((double)s.getP2().getX(), s.getP2().getY());
		
		return new GLigne(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		
		// TODO: a completer d
	}
}
