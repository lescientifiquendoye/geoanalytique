package geoanalytique.model;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import java.util.Arrays;

/**
 * La classe Rectangle représente un rectangle géométrique défini par un point d'origine,
 * une longueur et une largeur. Elle hérite de la classe {@link Polygone}.
 * 
 * Cette classe permet de calculer l'aire, le périmètre et d'obtenir des informations
 * sur les segments et le centre de gravité du rectangle.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class Rectangle extends Polygone {

    /**
     * La longueur du rectangle.
     */
    private double longueur;

    /**
     * La largeur du rectangle.
     */
    private double largeur;

    private String nom;

    private GeoAnalytiqueControleur controleur;

    /**
     * Constructeur de la classe Rectangle.
     * Initialise un rectangle à partir d'un point d'origine, d'une longueur et d'une largeur.
     * Les autres sommets du rectangle sont calculés automatiquement.
     * 
     * @param a           Le point d'origine du rectangle (coin supérieur gauche).
     * @param longueur    La longueur du rectangle.
     * @param largeur     La largeur du rectangle.
     * @param controleur  Le contrôleur principal de l'application.
     */
    public Rectangle(String nom,Point a, double longueur, double largeur, GeoAnalytiqueControleur controleur) {
        super(nom, Arrays.asList(a,
                        new Point(a.getX() + longueur, a.getY(), controleur),
                        new Point(a.getX() + longueur, a.getY() + largeur, controleur),
                        new Point(a.getX(), a.getY() + largeur, controleur)),
                controleur);
        this.longueur = longueur;
        this.largeur = largeur;
        this.controleur = controleur;
        this.nom = nom;
    }

    /**
     * Retourne la longueur du rectangle.
     * 
     * @return La longueur du rectangle.
     */
    public double getLongueur() {
        return longueur;
    }

    /**
     * Retourne la largeur du rectangle.
     * 
     * @return La largeur du rectangle.
     */
    public double getLargeur() {
        return largeur;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Calcule l'aire du rectangle.
     * 
     * @return L'aire du rectangle.
     */
    public double calculerAire() {
        return longueur * largeur;
    }

    /**
     * Calcule le périmètre du rectangle.
     * 
     * @return Le périmètre du rectangle.
     */
    public double calculerPerimetre() {
        return 2 * (longueur + largeur);
    }

    /**
     * Calcule le centre de gravité du rectangle.
     * 
     * <b>Note :</b> Cette méthode est à implémenter.
     * 
     * @return Un objet {@link Point} représentant le centre de gravité du rectangle, ou {@code null}.
     */
    @Override
    public Point calculerCentreGravite() {
        return null; // TODO: Implémenter la logique pour calculer le centre de gravité.
    }

    /**
     * Retourne un segment du rectangle en fonction de son index.
     * 
     * <b>Note :</b> Cette méthode est à implémenter.
     * 
     * @param i L'index du segment (0 à 3 pour un rectangle).
     * @return Un objet {@link Segment} représentant le segment correspondant, ou {@code null}.
     */
    @Override
    public Segment getSegment(int i) {
        // TODO: Implémenter la logique pour retourner un segment du rectangle.
        return null;
    }
}