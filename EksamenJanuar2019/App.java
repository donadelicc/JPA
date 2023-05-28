// a)
@Entity
public class Ansatt {
    @id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String ansattNr;
    private String fornavn;
    private String etternanv;
    private String tittel;

    @OneToOne
    @JoinColumn(name="romNr", referencedColumnName="romNr")
    private Rom kontorplass;
}

@Entity
public class Rom {
    @id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String romNr;
    private int kvadratmeter;

    @OneToOne
    @JoinColumn(name="ansattNr", referencedColumnName="ansattNr")
    private Ansatt ansatt;
}


// b) 

public void nyttKontor(int ansattNr, int romNr) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
        tx.begin();
        Rom rom = em.find(Rom.class, romNr);
        Ansatt ansatt = em.find(Ansatt.class, ansattNr);
        // hvis en ansatt har et rom
        if(ansatt.getRom() != null) {
            // frigjører rommet
            ansatt.setRom(rom) = null;
            // hvis rommet ikke er ledig
           if(rom.getAnsatt() != null) {
                throw new Exception(RoomNotAvailableExceptionz)
           } else {
            rom.set
           }
        } else {
            // frigjør rommet
        }
    }
}