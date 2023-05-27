// imports

import java.lang.annotation.Inherited;
import java.util.List;

@Entity
public class Person() {

    private String fornavn;
    private String etternavn;

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer person_id;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @OneToMany(mappedBy = "person_id" fetch = FetchType.EAGER)
    private List<Telefon> telefoner;

}

@Entity
public class Adresse() {
    private String gateadresse;
    private Integer postnummer;

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adresse_id;

}

@Entity
public class Telefon() {
    private String alias;
    
    @id
    private Interger nummer;

    @ManyToOne
    @JoinColumn(mappedBy = "person_id")
    private Person person;
}


public Person hentUtPersonMedId(int id) {

    EntityManager em = emf.createEntityManager();
    try {
        Person person = em.find(Person.class, id);
        return person;
    } finally {
        em.close();
    }
}

public List<Person> hentUtAlleSomBorPaaAdresseId(int id) {
    EntityManager em = emf.createEntityManager();
    try{
        TypedQuery<Person> query = em.createQuery(
            "SELECT p FROM Person p WHERE p.adresse.adresse_id = :id", Person.class);
            query.setParameter("id", id);
            return query.getResultList();
    } finally {
        em.close();
    }
}

public void personFlytterTilAdresse(int personId, String gateadresse, int postnummer) {
    EntityManager em = emf.createEntityManager();
    EntiyTransaction tx = em.getTransaction();

    String adresse = hentUtAdresse(gateadresse, postnummer);

    try {
        if(adresse == null) {
        
        tx.begin()
        Adresse nyAdresse = new Adresse(gateadresse, postnummer);
        em.persist(nyAdresse);
      
        Person p = em.find(Person.class, personId);
        p.setAdresse(adresse);
        tx.commit();
    } catch (Exception e) {
        e.printStackTrace();
        tx.rollback();
    } finally {
        em.close();
    }


}

