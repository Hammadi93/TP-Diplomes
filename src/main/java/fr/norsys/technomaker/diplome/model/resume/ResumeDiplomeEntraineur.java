package fr.norsys.technomaker.diplome.model.resume;

import java.util.ArrayList;
import java.util.List;

public class ResumeDiplomeEntraineur {

    private final String nomEntraineur;

    private final String prenomEntraineur;

    private final ArrayList<ResumeDiplome> diplomes;

    public ResumeDiplomeEntraineur(String nomEntraineur, String prenomEntraineur) {
	this.nomEntraineur = nomEntraineur;
	this.prenomEntraineur = prenomEntraineur;
	this.diplomes = new ArrayList<ResumeDiplome>();
    }

    public String getNomEntraineur() {
	return nomEntraineur;
    }

    public String getPrenomEntraineur() {
	return prenomEntraineur;
    }

    public List<ResumeDiplome> getDiplomes() {
	return diplomes;
    }

}
