package geoanalytique;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.gui.GeoAnalytiqueGUI;
import geoanalytique.model.Point;
import geoanalytique.model.Segment;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Classe principale de l'application GeoAnalytique.
 * Cette classe sert de point d'entrée pour lancer l'application.
 * Elle initialise la fenêtre principale, le contrôleur, la barre de menus, et ajoute des exemples d'objets géométriques.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class Main {

    /**
     * Méthode principale de l'application.
     * Initialise l'interface graphique, le contrôleur, et ajoute des exemples d'objets géométriques.
     * 
     * @param args Les arguments de ligne de commande (non utilisés).
     */
    public static void main(String[] args) {

        // Initialisation de la vue principale
        GeoAnalytiqueGUI panel = new GeoAnalytiqueGUI();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3)); 
        panel.setBorder(BorderFactory.createTitledBorder("Zone de dessin"));

        JFrame frame = new JFrame("GeoAnalytique");

        // Ajout du panneau principal à la fenêtre
        frame.getContentPane().add(panel);

        // Configuration de la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        // Initialisation du contrôleur
        GeoAnalytiqueControleur controleur = new GeoAnalytiqueControleur(panel);
        controleur.prepareTout();

        // Création et ajout de la barre de menus
        JMenuBar menuBar = new Menu(controleur);
        frame.setJMenuBar(menuBar);

        // Bouton Effacer tout
       // Création du menu "Fichier"
JMenu menuFichier = new JMenu("Fichier");

// Ajout du menu à gauche
frame.add(menuFichier);

// Création du bouton rouge "Effacer tout"
JButton btnEffacerTout = new JButton("X Effacer tout");
btnEffacerTout.setForeground(Color.WHITE);
btnEffacerTout.setBackground(Color.RED);
btnEffacerTout.setFocusPainted(false);
btnEffacerTout.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

// Action du bouton
btnEffacerTout.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Voulez-vous vraiment effacer tous les objets ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            controleur.effacerTout();  // Méthode du contrôleur
        }
    }
});

// Espace "glue" pour pousser le bouton à droite
menuBar.add(Box.createHorizontalGlue());

// Ajout du bouton à la barre de menus (à droite)
menuBar.add(btnEffacerTout);


        // Ajout d'exemples d'objets géométriques
        controleur.addObjet(new Segment(new Point(-10, 0, controleur), new Point(10, 0, controleur), controleur));
        controleur.addObjet(new Segment(new Point(0, -10, controleur), new Point(0, 10, controleur), controleur));
        // Exemple commenté : ajout de points
        // controleur.addObjet(new Point("Ori", 0, 0, controleur));
        // controleur.addObjet(new Point("Ori2", 10, 0, controleur));

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}