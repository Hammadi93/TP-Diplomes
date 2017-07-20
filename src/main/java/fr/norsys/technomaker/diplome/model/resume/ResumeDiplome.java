package fr.norsys.technomaker.diplome.model.resume;

import java.time.LocalDate;

import fr.norsys.technomaker.diplome.model.Diplome;

public class ResumeDiplome {

    private Diplome diplome;

    private LocalDate dateObtention;

    private LocalDate dateRevision;

    public ResumeDiplome() {
    }

    public Diplome getDiplome() {
	return diplome;
    }

    public void setDiplome(Diplome diplome) {
	this.diplome = diplome;
    }

    public LocalDate getDateObtention() {
	return dateObtention;
    }

    public void setDateObtention(LocalDate dateObtention) {
	this.dateObtention = dateObtention;
    }

    public LocalDate getDateRevision() {
	return dateRevision;
    }

    public void setDateRevision(LocalDate dateRevision) {
	this.dateRevision = dateRevision;
    }

}
