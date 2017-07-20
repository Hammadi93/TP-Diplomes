package fr.norsys.technomaker.diplome.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.norsys.technomaker.diplome.model.Diplome;
import fr.norsys.technomaker.diplome.model.DiplomeEntraineur;
import fr.norsys.technomaker.diplome.model.Entraineur;
import fr.norsys.technomaker.diplome.model.TypeDiplome;
import fr.norsys.technomaker.diplome.model.resume.ResumeDiplome;
import fr.norsys.technomaker.diplome.model.resume.ResumeDiplomeEntraineur;

public class DiplomeService {

    public List<ResumeDiplomeEntraineur> resumerDiplomeEntraineursJava7(List<Entraineur> entraineurs) {

	List<ResumeDiplomeEntraineur> lstResumeDiplomesEntraineurs = new ArrayList<>();
	for (Entraineur entraineur : entraineurs) {

	    ResumeDiplomeEntraineur resumeDiplomeEntraineur = new ResumeDiplomeEntraineur(entraineur.getNom(),
		    entraineur.getPrenom());

	    List<ResumeDiplome> diplomes = new ArrayList<>();
	    for (DiplomeEntraineur diplomeEntraineur : entraineur.getDiplomes()) {

		ResumeDiplome resumeDiplome = new ResumeDiplome();
		resumeDiplome.setDiplome(diplomeEntraineur.getDiplome());

		if (diplomeEntraineur.getTypeDiplome().equals(TypeDiplome.OBTENTION)) {

		    resumeDiplome.setDateObtention(diplomeEntraineur.getDateObtention());
		    LocalDate dateDerniereRevision = diplomeEntraineur.getDateObtention();

		    for (DiplomeEntraineur diplomeEntr : entraineur.getDiplomes()) {

			dateDerniereRevision = verifierDernierRevision(diplomeEntraineur, dateDerniereRevision,
				diplomeEntr);
		    }
		    resumeDiplome.setDateRevision(dateDerniereRevision);
		    diplomes.add(resumeDiplome);
		}

	    }

	    resumeDiplomeEntraineur.getDiplomes().addAll(diplomes);
	    lstResumeDiplomesEntraineurs.add(resumeDiplomeEntraineur);
	}

	return lstResumeDiplomesEntraineurs;

    }

    public List<ResumeDiplomeEntraineur> resumerDiplomeEntraineursLambda(List<Entraineur> entraineurs) {

	return entraineurs.stream().map(entraineur -> {
	    ResumeDiplomeEntraineur resumeDiplomeEntraineur = new ResumeDiplomeEntraineur(entraineur.getNom(),
		    entraineur.getPrenom());
	    resumeDiplomeEntraineur.getDiplomes()
		    .addAll(this.getResumeDiplomeFromDiplomeEntaineur2(entraineur.getDiplomes()));
	    return resumeDiplomeEntraineur;
	}).collect(Collectors.toList());
    }

    public List<ResumeDiplome> getResumeDiplomeFromDiplomeEntaineur2(List<DiplomeEntraineur> diplomeEntraineurs) {

	return Arrays.stream(Diplome.values())
		.map(diplome -> diplomeEntraineurs.stream()
			.filter(diplomeEntraineur -> diplomeEntraineur.getDiplome().equals(diplome))
			.map(this::mapDiplomeEntraineurToResumeDiplome).reduce(this::reducreresumeDiplome).get())
		.collect(Collectors.toList());

    }

    private ResumeDiplome mapDiplomeEntraineurToResumeDiplome(DiplomeEntraineur diplomeEntraineur) {
	ResumeDiplome resumeDiplome = new ResumeDiplome();
	resumeDiplome.setDiplome(diplomeEntraineur.getDiplome());
	resumeDiplome.setDateObtention(diplomeEntraineur.getDateObtention());
	resumeDiplome.setDateRevision(diplomeEntraineur.getDateObtention());

	return resumeDiplome;
    }

    private ResumeDiplome reducreresumeDiplome(ResumeDiplome rd1, ResumeDiplome rd2) {
	if (rd1.getDateRevision().isBefore(rd2.getDateRevision())) {
	    rd1.setDateRevision(rd2.getDateRevision());
	}
	return rd1;
    }

    public List<ResumeDiplomeEntraineur> resumerDiplomeEntraineursCollector(List<Entraineur> entraineurs) {
	return entraineurs.stream().map(entraineur -> {
	    ResumeDiplomeEntraineur resumeDiplomeEntraineur = new ResumeDiplomeEntraineur(entraineur.getNom(),
		    entraineur.getPrenom());
	    resumeDiplomeEntraineur.getDiplomes().addAll(this.getResumerDiplomeCollector(entraineur.getDiplomes()));
	    return resumeDiplomeEntraineur;
	}).collect(Collectors.toList());
    }

    private List<ResumeDiplome> getResumerDiplomeCollector(List<DiplomeEntraineur> diplomeEntraineurs) {
	Map<Diplome, Optional<ResumeDiplome>> map = diplomeEntraineurs.stream()
		.collect(Collectors.groupingBy(DiplomeEntraineur::getDiplome, LinkedHashMap::new, Collectors.mapping(
			this::mapDiplomeEntraineurToResumeDiplome, Collectors.reducing(this::reducreresumeDiplome))));
	return map.values().stream().map(d -> d.get()).collect(Collectors.toList());
    }

    private LocalDate verifierDernierRevision(DiplomeEntraineur diplomeEntraineur, LocalDate dateDerniereRevision,
	    DiplomeEntraineur diplome) {
	if (diplome.getDateObtention().isAfter(dateDerniereRevision)
		&& diplome.getTypeDiplome().equals(TypeDiplome.REVISION)
		&& diplome.getDiplome().equals(diplomeEntraineur.getDiplome())) {
	    dateDerniereRevision = diplome.getDateObtention();
	}
	return dateDerniereRevision;
    }

}
