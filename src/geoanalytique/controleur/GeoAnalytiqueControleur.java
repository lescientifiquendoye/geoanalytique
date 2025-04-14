package geoanalytique.controleur;
 
import geoanalytique.exception.ArgumentOperationException;
import geoanalytique.exception.IncorrectTypeOperationException;
import geoanalytique.exception.VisiteurException;
import geoanalytique.graphique.Graphique;
import geoanalytique.gui.GeoAnalytiqueGUI;
import geoanalytique.model.GeoObject;
import geoanalytique.model.Point;
import geoanalytique.model.Segment;
import geoanalytique.model.ViewPort;
import geoanalytique.util.Dessinateur;
import geoanalytique.util.Operation;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * La classe GeoAnalytiqueControleur est le contrôleur principal de l'application GeoAnalytique.
 * Elle gère les interactions entre la vue (interface graphique) et le modèle (objets géométriques).
 * Tous les événements importants provenant de l'interface graphique passent par cette classe.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class GeoAnalytiqueControleur implements ActionListener, MouseListener, HierarchyBoundsListener {

    /**
     * Liste des objets géométriques gérés par le contrôleur.
     */
    private ArrayList<GeoObject> objs;

    /**
     * La zone d'affichage (viewport) utilisée pour convertir les coordonnées entre le modèle et la vue.
     */
    private ViewPort viewport;

    /**
     * La vue principale de l'application GeoAnalytique.
     */
    private GeoAnalytiqueGUI view;

    /**
     * L'objet géométrique actuellement sélectionné.
     */
    private transient GeoObject select;

    /**
     * Constructeur de la classe GeoAnalytiqueControleur.
     * Initialise le contrôleur et établit le lien avec la vue.
     * 
     * @param view La vue principale de l'application.
     */
    public GeoAnalytiqueControleur(GeoAnalytiqueGUI view) {
		objs = new ArrayList<GeoObject>();
        this.view = view;
		viewport = new ViewPort(view.getCanvas().getWidth(),view.getCanvas().getWidth());
		// TODO: A completer avec vos modifications
    }

    /**
     * Ajoute un nouvel objet géométrique au système.
     * Met à jour la vue et la liste des opérations réalisables.
     * 
     * @param obj L'objet géométrique à ajouter.
     */
    public void addObjet(GeoObject obj) {
        objs.add(obj);
        recalculPoints();
            // TODO: a completer
	}
	

    /**
     * Met à jour les informations de la vue en fonction d'un objet géométrique modifié.
     * 
     * @param object L'objet géométrique ayant subi une modification.
     */
    public void update(GeoObject object) {
        recalculPoints();
    }

    /**
     * Gère les événements d'action provenant de l'interface graphique.
     * 
     * @param e L'événement d'action.
     */
    public void actionPerformed(ActionEvent e) {
        // TODO: à compléter
    }

	public void mouseClicked(MouseEvent e) {
            // a priori inutile
            // mais customisable si necessaire
	}
	

	public void mouseReleased(MouseEvent e) {
            // a priori inutile
            // mais customisable si necessaire
	}

	public void mouseEntered(MouseEvent e) {
            // a priori inutile
            // mais customisable si necessaire
	}

	public void mouseExited(MouseEvent e) {
            // a priori inutile
            // mais customisable si necessaire
	}





    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Convertir les coordonnées du clic en coordonnées du modèle
        x = (int) ((x - viewport.getCentreX()) / viewport.getScale());
        y = (int) ((y - viewport.getCentreY()) / viewport.getScale());

        System.out.println("Clic détecté à (" + x + ", " + -y + ")");

        // Chercher l'objet géométrique sous les coordonnées du clic
        GeoObject clickedObject = touverObjectAt(x, y);

        if (clickedObject != null) {
            selectionner(clickedObject);
            System.out.println("Objet sélectionné : " + clickedObject.getClass());
        } else {
            deselectionner();
        }
    }

    /**
     * Recherche un objet géométrique à une position donnée.
     * 
     * @param x La coordonnée X.
     * @param y La coordonnée Y.
     * @return L'objet géométrique trouvé, ou {@code null} si aucun objet n'est trouvé.
     */
    private GeoObject touverObjectAt(int x, int y) {
		// Parcourir la liste des objets géométriques pour trouver celui qui contient le point (x, y)

		for (int i = 2; i < objs.size(); i++) {
			
			if (objs.get(i).contient(new Point(x, -y,null))) { 
				return objs.get(i);
            }
        }
        return null;
    }

    /**
     * Sélectionne un objet géométrique dans la vue.
     * Met en évidence l'objet sélectionné et met à jour la vue.
     * 
     * @param o L'objet géométrique à sélectionner.
     */
    private void selectionner(GeoObject o) {
			// Si un objet est déjà sélectionné, on le désélectionne d'abord
        if (select != null) {
            deselectionner();
        }

			// Marquer l'objet comme sélectionné
        select = o;
			
			// Appliquer des modifications visuelles à l'objet (par exemple, changer sa couleur)
			// Vous pouvez ajouter une méthode dans GeoObject pour gérer cela, par exemple :
			//o.setSelected(true);
			
			if (select instanceof Point p) {
				Selectionner s = new Selectionner(this, p, viewport);
				s.operationSurPoint(p);
			} else if(select instanceof Segment seg){
                Selectionner s = new Selectionner(this, seg, viewport);
                s.operationSurSegment(seg);
            } else{
				System.out.println("Selected object is not a Point.");
			}

        try {
            Graphique graphique = o.visitor(new Dessinateur(viewport));
            graphique.setCouleur(Color.YELLOW);
            view.getCanvas().addGraphique(graphique);
        } catch (VisiteurException e) {
            e.printStackTrace();
        }

        recalculPoints();
    }

    /**
     * Désélectionne l'objet géométrique actuellement sélectionné.
     */
    private void deselectionner() {
        select = null;
    }

    /**
     * Prépare les événements nécessaires pour l'interface graphique.
     * Ajoute des listeners pour le canevas et d'autres composants.
     */
    public void prepareTout() {
            // Preparation des evenements du canevas
        view.getCanvas().addMouseListener(this);
        view.getCanvas().addHierarchyBoundsListener(this);
            
            // TODO: a completer si necessaire
            
	}

	public void ancestorMoved(HierarchyEvent e) {
            // a priori inutile
            // mais customisable si necessaire
	}

	public void ancestorResized(HierarchyEvent e) {
	    // TODO: a completer si le canevas est redimentionnable
	}

        /**
         * Cette fonction est la fonction permettant de caracteriser le presenteur.
         * Elle realise la presentation des donnees en indiquant a la vue les
         * element graphique devant etre affiche en fonction de la zone d'affichage
         * (Viewport).
     */
    private void recalculPoints() {
        view.getCanvas().clear();

        Dessinateur d = new Dessinateur(viewport);
        for (GeoObject o : objs) {
            try {
                Graphique c = o.visitor(d);
                view.getCanvas().addGraphique(c);
            } catch (VisiteurException e) {
                e.printStackTrace();
            }
        }

        view.getCanvas().repaint();
    }

    /**
     * Lance une opération sur un objet géométrique.
     * 
     * @param object L'objet géométrique sur lequel l'opération est réalisée.
     * @param ope    L'opération à effectuer.
     */
    public void lanceOperation(GeoObject object, Operation ope) {
            // TODO: a modifier si vous avez compris comment la fonction
            // procedais. Sinon laissez telle quel
		for(int i=0; i < ope.getArite();i++) {
			try {
				String res = JOptionPane.showInputDialog(view, ope.getDescriptionArgument(i), ope.getTitle(),JOptionPane.QUESTION_MESSAGE);
				if(res == null)
					return;
				if(ope.getClassArgument(i) == Double.class) {
					ope.setArgument(i, Double.valueOf(res));
				}
				else if(ope.getClassArgument(i) == Integer.class) {
					ope.setArgument(i, Integer.valueOf(res));
				}
				else if(ope.getClassArgument(i) == Character.class) {
					ope.setArgument(i, new Character(res.charAt(0)));
				}
				else if(ope.getClassArgument(i) == String.class) {
					ope.setArgument(i, new String(res));
				}
				else if(GeoObject.class.isAssignableFrom(ope.getClassArgument(i))) {
					ope.setArgument(i, searchObject(res));
				}
				else {
                    JOptionPane.showMessageDialog(view, "Classe de l'argument non supporte", "Erreur dans le lancement de l'operation", JOptionPane.ERROR_MESSAGE);
       				    return;
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (ArgumentOperationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IncorrectTypeOperationException e) {
				e.printStackTrace();
			}
		}
                // Dans notre application on autorise un resultat, que nous devons
                // interprété. Pas de resultat correspond au pointeur null
		Object o = ope.calculer();
		if(o != null) {
                       // on a bien trouve un resultat. Mais on ne connait pas
                       // sa nature on va donc le tester
			if(GeoObject.class.isAssignableFrom(o.getClass())) {
                            // c'est un objet analytique on l'ajoute dans notre systeme
				addObjet((GeoObject) o);
			}
			else {
                            // on ne connait pas le type, donc on l'avise a l'utilisateur
				JOptionPane.showConfirmDialog(view, o, ope.getTitle(),JOptionPane.OK_OPTION);
			}
                        // TODO BONUS: proposer et modifier le traitement du resultat
                        // pour pouvoir renvoyer plusieurs chose en meme temps
		}
		recalculPoints();
	}
	
        /**
         * Cette fonction permet de retrouver un objet dans la liste des objets
         * geometrique a partir de son nom (que l'on supposera unique). Si le nom
         * de l'objet est un introuvable on leve l'exception ArgumentOperationException.
         * Cette fonction est utilisee dans le calcul d'une operation.
         * @param x nom de l'objet a rechercher
         * @return Renvoie l'objet ayant pour nom x, sinon leve une exception
         * @throws geoanalytique.exception.ArgumentOperationException
     */
    private Object searchObject(String x) throws ArgumentOperationException {
        for (GeoObject o : objs) {
            if (o.getName().equals(x)) {
                return o;
            }
        }
        throw new ArgumentOperationException("Nom de l'objet introuvable");
    }

    public void effacerTout() {
        for (int i = 2; i < objs.size(); i++) {
			
            objs.remove(i);
        }
        recalculPoints();
        select = null;
    }


}