package Esamen20IKKEFERDIG;
/*
 *  Bruker - Brukerne av systemet kan poste innlegg og kommentere innlegg
    Innlegg - Forumet består av en rekke innlegg postet av brukere
    Kommentar - Hvert innlegg kan ha en rekke kommentarer

    Vi ønsker kun toveis forbindelse mellom innlegg og
    kommentarer (for enklere navigasjon fra Java), ellers skal det væ re enveis

 */
@Entity
public class Bruker {
    @id
    private String bruker_id;
    private String fornavn;
    private String etternavn;
    private int fodselsaar;
}
@Entity
public class Innlegg {
    @id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int innlegg_id;
    private LocalDateTime tidspunkt;
    private String tekst;

    @ManyToOne
    @JoinColumns(name="bruker_id", referencedColumnName="bruker_id")
    private Bruker bruker;

    @OneToMany(mappedBy="innlegg_id")
    private List<Kommentar> kommentar;
}
@Entity
public class Kommentar {
    @id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int kommentar_id;
    private LocalDateTime tidspunkt;
    private String tekst;

    @ManyToOne
    @JoinColumn(name="innlegg_id", referencedColumnName="innlegg_id")
    private Innlegg innlegg;

    @ManyToOne
    @JoinColumns(name="bruker_id", referncedColumnName="bruker_id")
    private Bruker bruker;
}

// b) 
/*
 *  Vi antar at vi har en hjelpeklasse ForumDAO. Her skal du lage en metode som henter
    ut ett enkelt innlegg med tilhørende kommentarer fra databasen. Metoden har
    innleggid som parameter. Hvis du legger til annoteringer i entitetsklasser for å få dette
    til, så forklar næ rmere i en kommentar.
*/

public class ForumDAO {

    // Antar at det finnes private hjelpemetoder her

    public String hentUt

}


