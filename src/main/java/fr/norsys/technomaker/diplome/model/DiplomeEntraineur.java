package fr.norsys.technomaker.diplome.model;

import java.time.LocalDate;

public class DiplomeEntraineur {

    private final Diplome diplome;

    private final TypeDiplome typeDiplome;

    private final LocalDate date;

    public DiplomeEntraineur(Diplome diplome, TypeDiplome typeDiplome, LocalDate date) {
	this.diplome = diplome;
	this.typeDiplome = typeDiplome;
	this.date = date;
    }

    public Diplome getDiplome() {
	return this.diplome;
    }

    public TypeDiplome getTypeDiplome() {
	return this.typeDiplome;
    }

    public LocalDate getDateObtention() {
	return this.date;
    }

}
