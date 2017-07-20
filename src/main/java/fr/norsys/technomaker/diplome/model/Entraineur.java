package fr.norsys.technomaker.diplome.model;

import java.util.ArrayList;
import java.util.List;

public class Entraineur {

    private final String nom;

    private final String prenom;

    private final List<DiplomeEntraineur> diplomes;

    public Entraineur(String nom, String prenom) {
	this.nom = nom;
	this.prenom = prenom;
	this.diplomes = new ArrayList<DiplomeEntraineur>();
    }

    public String getNom() {
	return this.nom;
    }

    public String getPrenom() {
	return this.prenom;
    }

    public List<DiplomeEntraineur> getDiplomes() {
	return this.diplomes;
    }

    public void addDiplomes(DiplomeEntraineur diplome) {
	this.diplomes.add(diplome);
    }

}
