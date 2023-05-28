// imports

// a) Lage klasser for tabellene land og by
public class Land {
    @id
    private String navn;
    private int areal_km2;
    private int folketall;

    @OneToOne
    @JoinColumn(name="hovedstad", referncedColumnName="hovedstad")
    private By by;

    // Antar at det finnes get - og set-metoder, konstruktør og toString-metode.
}

public class By {
    @id
    private String navn;
    private int år;
    private int folketall;

    @ManyToOne
    @JoinColumn(name="land", referncedColumnName="land")
    private Land land;

    // Antar at det finnes get - og set-metoder, kontruktør og toString-metode.

}

public class GeografiDAO {


// b) lag en metode som henter ut land med et gitt navn i databasen
    public Land hentUtLand(String navn) {
        EntityManager em = emf.createEntityManager();
        try {
        Land land = em.find(Land.class, navn);
        if(land == null) {}
            return land;
        } finally {
            em.close();
        }
    }

    // d) Skriv en metode som oppdaterer folketallet i en gitt by. 
    /*
     * @param by, folketall (nytt)
     * @rerun void
     */
    public void oppdaterFolktetall(String navn, int nyttFolketall) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            By by = em.find(By.class, navn);
            if(by== null) {
                return;
            } else {
                by.setFolketall(nyttFolketall);
                tx.commit();
            }
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }
}

// c) ) Skriv en main-metode som bruker metoden du laget i b) til å få
//      skrevet ut info om hovedstaden i Norge. Du kan anta at Land og By har get-, og
//      toString-metoder du kan bruke
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        GeografiDAO dao = new GeografiDAO();
        Land norge = dao.hentUtLand("Norge");
        String hovedstad = norge.getHovedstad();
        try {
            By hovedstad = norge.getBy();
            System.out.println(hovedstad.toString());
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
        }
    }
    




