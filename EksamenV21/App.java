// importer bilioteker

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
    
}

@Entity
public class Person {
    
    private String fornavn;
    private String etternavn;
    private String fodselsdato;

    @id
    @GeneratedValue(strategy=GenerationType.IDENTITY);

    @OneToMany(mappedBy="ansvarlig");
    private List<Fag> fagMedAnsvarFor;

}

