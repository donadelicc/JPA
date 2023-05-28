// importer bilioteker

// a) 

import java.time.LocalDate;

@Entity
public class Fag {

    private String navn;
    private Integer stp;
    private String semester;

    @id
    private String kode;

    @ManyToOne
    @JoinColumn(name="ansvarlig_id", referencedColumnName = "id")
    private Person ansvarlig;

    //d
    @ManyToMany
    @JoinTable(
        name="undervisning",
        joinColumns =  @JoinColumn(name="fag_kode"),
        inverseJoinColumns = @JoinColumn(name="laerer_id")
    )
    private List<Person> laerer;
    
}

@Entity
public class Person {
    
    private String fornavn;
    private String etternavn;
    private LocalDate fodselsdato;

    @id
    @GeneratedValue(strategy=GenerationType.IDENTITY);

    @ManyToMany(mappedBy="ansvarlig")
    private List<Fag> fagMedAnsvarFor;

    // d)
    @ManyToMany(mappedBy="laerere")
    private List<Fag> fagUnderviser;

}

// b)

public class FagDAO {

    public void lagreNyttFag(String kode, String navn, Integer stp, String semester, Integer ansvarlig_id) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Person fagansvarlig = em.find(Person.class, ansvarlig_id);
            if(fagansvarlig == null) {
                // returner uten å gjøre noe
            } else {
                Fag nyttFag = new Fag(kode, navn, stp, semester, ansvarlig_id);
                // antar at leggTilFagansvarligFor allere finnes i FagDAO
                fagansvarlig.leggTilFagansvarligFor(nyttFag);

                Fag ingenKode = em.find(Fag.class, kode);
                if(ingenKode == null) {
                    // Opprette et nytt fag med nye data --> ?
                    em.persist(nyttFag);
                } else {
                    // Oppdaterer eksisterende fag med nye data
                    em.merge(nyttFag);
                }
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}

// c) Nå kan et fag ha flere lærere, og en lærer undervise i et fag. Utvid databasen

// Lage koblingstabell mellom lærer og fag. F.eks. undervisning

// d) 
