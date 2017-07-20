package fr.norsys.technomaker.diplome.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.norsys.technomaker.diplome.model.Diplome;
import fr.norsys.technomaker.diplome.model.DiplomeEntraineur;
import fr.norsys.technomaker.diplome.model.Entraineur;
import fr.norsys.technomaker.diplome.model.TypeDiplome;
import fr.norsys.technomaker.diplome.model.resume.ResumeDiplome;
import fr.norsys.technomaker.diplome.model.resume.ResumeDiplomeEntraineur;

public class DiplomeServiceTest {

    private DiplomeService diplomeService;

    private final List<Entraineur> entraineurs = new ArrayList<>();

    @Before
    public void setUp() {

	this.diplomeService = new DiplomeService();

	final Entraineur entraineur1 = new Entraineur("Mourinho", "Jose");
	entraineur1
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_1, TypeDiplome.OBTENTION, LocalDate.of(1995, 1, 25)));
	entraineur1
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_2, TypeDiplome.OBTENTION, LocalDate.of(1996, 9, 13)));
	entraineur1
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_3, TypeDiplome.OBTENTION, LocalDate.of(2000, 6, 5)));
	entraineur1
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_1, TypeDiplome.REVISION, LocalDate.of(1998, 2, 18)));
	entraineur1
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_2, TypeDiplome.REVISION, LocalDate.of(1999, 12, 12)));
	entraineur1
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_2, TypeDiplome.REVISION, LocalDate.of(2002, 4, 10)));
	entraineur1
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_3, TypeDiplome.REVISION, LocalDate.of(2005, 3, 22)));

	final Entraineur entraineur2 = new Entraineur("Noah", "Yannick");
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_1, TypeDiplome.OBTENTION, LocalDate.of(1987, 10, 14)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_2, TypeDiplome.OBTENTION, LocalDate.of(1988, 11, 2)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_3, TypeDiplome.OBTENTION, LocalDate.of(1990, 7, 31)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_1, TypeDiplome.REVISION, LocalDate.of(1991, 3, 15)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_1, TypeDiplome.REVISION, LocalDate.of(1993, 8, 26)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_2, TypeDiplome.REVISION, LocalDate.of(1992, 5, 17)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_2, TypeDiplome.REVISION, LocalDate.of(1995, 3, 12)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_3, TypeDiplome.REVISION, LocalDate.of(1996, 10, 16)));
	entraineur2
		.addDiplomes(new DiplomeEntraineur(Diplome.DEGRE_3, TypeDiplome.REVISION, LocalDate.of(2001, 12, 4)));

	this.entraineurs.add(entraineur1);
	this.entraineurs.add(entraineur2);
    }

    @Test
    public void shouldGetDateObtentionDiplomesJava7() throws Exception {

	final List<ResumeDiplomeEntraineur> diplomeEntraineurs = this.diplomeService
		.resumerDiplomeEntraineursJava7(this.entraineurs);

	this.verifierResume(diplomeEntraineurs);
    }

    @Test
    public void shouldGetDateObtentionDiplomesLambda() throws Exception {

	final List<ResumeDiplomeEntraineur> diplomeEntraineurs = this.diplomeService
		.resumerDiplomeEntraineursLambda(this.entraineurs);

	this.verifierResume(diplomeEntraineurs);
    }

    @Test
    public void shouldGetDateObtentionDiplomesCollector() throws Exception {

	final List<ResumeDiplomeEntraineur> diplomeEntraineurs = this.diplomeService
		.resumerDiplomeEntraineursCollector(this.entraineurs);

	this.verifierResume(diplomeEntraineurs);
    }

    private void verifierResume(List<ResumeDiplomeEntraineur> resumeDiplomeEntraineurs) {
	assertThat(resumeDiplomeEntraineurs).isNotNull();
	assertThat(resumeDiplomeEntraineurs.size()).isEqualTo(2);

	final ResumeDiplomeEntraineur resumeDiplomeEntraineur1 = resumeDiplomeEntraineurs.get(0);
	assertThat(resumeDiplomeEntraineur1).isNotNull();
	assertThat(resumeDiplomeEntraineur1.getNomEntraineur()).isEqualTo("Mourinho");
	assertThat(resumeDiplomeEntraineur1.getPrenomEntraineur()).isEqualTo("Jose");

	final List<ResumeDiplome> diplomes1 = resumeDiplomeEntraineur1.getDiplomes();
	assertThat(diplomes1).isNotNull();
	assertThat(diplomes1.size()).isEqualTo(3);
	assertThat(diplomes1.get(0).getDiplome()).isEqualTo(Diplome.DEGRE_1);
	assertThat(diplomes1.get(0).getDateObtention()).isEqualTo(LocalDate.of(1995, 1, 25));
	assertThat(diplomes1.get(0).getDateRevision()).isEqualTo(LocalDate.of(1998, 2, 18));

	assertThat(diplomes1.get(1).getDiplome()).isEqualTo(Diplome.DEGRE_2);
	assertThat(diplomes1.get(1).getDateObtention()).isEqualTo(LocalDate.of(1996, 9, 13));
	assertThat(diplomes1.get(1).getDateRevision()).isEqualTo(LocalDate.of(2002, 4, 10));

	assertThat(diplomes1.get(2).getDiplome()).isEqualTo(Diplome.DEGRE_3);
	assertThat(diplomes1.get(2).getDateObtention()).isEqualTo(LocalDate.of(2000, 6, 5));
	assertThat(diplomes1.get(2).getDateRevision()).isEqualTo(LocalDate.of(2005, 3, 22));

	final ResumeDiplomeEntraineur resumeDiplomeEntraineur2 = resumeDiplomeEntraineurs.get(1);
	assertThat(resumeDiplomeEntraineur2).isNotNull();
	assertThat(resumeDiplomeEntraineur2.getNomEntraineur()).isEqualTo("Noah");
	assertThat(resumeDiplomeEntraineur2.getPrenomEntraineur()).isEqualTo("Yannick");

	final List<ResumeDiplome> diplomes2 = resumeDiplomeEntraineur2.getDiplomes();
	assertThat(diplomes2).isNotNull();
	assertThat(diplomes2.size()).isEqualTo(3);
	assertThat(diplomes2.get(0).getDiplome()).isEqualTo(Diplome.DEGRE_1);
	assertThat(diplomes2.get(0).getDateObtention()).isEqualTo(LocalDate.of(1987, 10, 14));
	assertThat(diplomes2.get(0).getDateRevision()).isEqualTo(LocalDate.of(1993, 8, 26));

	assertThat(diplomes2.get(1).getDiplome()).isEqualTo(Diplome.DEGRE_2);
	assertThat(diplomes2.get(1).getDateObtention()).isEqualTo(LocalDate.of(1988, 11, 2));
	assertThat(diplomes2.get(1).getDateRevision()).isEqualTo(LocalDate.of(1995, 3, 12));

	assertThat(diplomes2.get(2).getDiplome()).isEqualTo(Diplome.DEGRE_3);
	assertThat(diplomes2.get(2).getDateObtention()).isEqualTo(LocalDate.of(1990, 7, 31));
	assertThat(diplomes2.get(2).getDateRevision()).isEqualTo(LocalDate.of(2001, 12, 4));
    }

}
