package geoanalytique.gui;

import geoanalytique.view.GeoAnalytiqueView;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JPanel;

/**
 * Classe représentant l'interface graphique principale de l'application GeoAnalytique.
 * Cette classe configure la disposition de l'interface utilisateur et intègre la zone de dessin
 * (grille) ainsi que des panneaux pour les éléments, les identifiants et les opérations.
 * 
 * Elle hérite de {@link JPanel} et utilise un objet {@link GeoAnalytiqueView} pour gérer la zone de dessin.
 * 
 * <b>Note :</b> Certaines méthodes sont encore à compléter.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class GeoAnalytiqueGUI extends JPanel {

    /**
     * La grille principale utilisée pour dessiner les objets géométriques.
     */
    private GeoAnalytiqueView grille;

    /**
     * Constructeur de la classe GeoAnalytiqueGUI.
     * Initialise la grille de dessin et configure la disposition de l'interface graphique.
     */
    public GeoAnalytiqueGUI() {
        grille = new GeoAnalytiqueView();

        // Configuration de la disposition
        this.setLayout(new BorderLayout());
        this.add(grille);
    }

    /**
     * Retourne la grille de dessin utilisée pour afficher les objets géométriques.
     * 
     * @return L'objet {@link GeoAnalytiqueView} représentant la grille de dessin.
     */
    public GeoAnalytiqueView getCanvas() {
        return grille;
    }

    /**
     * Retourne le panneau contenant les éléments de l'interface utilisateur.
     * 
     * <b>Note :</b> Cette méthode est à compléter.
     * 
     * @return Un objet {@link Container} représentant le panneau des éléments, ou {@code null}.
     */
    public Container getPanelElements() {
        // TODO: À compléter
        return null;
    }

    /**
     * Retourne le panneau contenant les identifiants de l'interface utilisateur.
     * 
     * <b>Note :</b> Cette méthode est à compléter.
     * 
     * @return Un objet {@link Container} représentant le panneau des identifiants, ou {@code null}.
     */
    public Container getPanelIDs() {
        // TODO: À compléter
        return null;
    }

    /**
     * Retourne le panneau contenant les opérations disponibles dans l'interface utilisateur.
     * 
     * <b>Note :</b> Cette méthode est à compléter.
     * 
     * @return Un objet {@link Container} représentant le panneau des opérations, ou {@code null}.
     */
    public Container getPanelOperations() {
        // TODO: À compléter
        return null;
    }
}