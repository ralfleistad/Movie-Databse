package oblig5.model;

public class Person {
    private String fornavn;
    private String etternavn;

    // KONSTRUKTØR
    public Person(String fornavn, String etternavn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    @Override
    public String toString() {
        return fornavn + " " + etternavn;
    }

    // METODER
    public String getFultNavn() {
        return getFornavn() + " " + getEtternavn();
    }

    // GETTERE
    public String getFornavn() {
        return fornavn;
    }
    public String getEtternavn() {
        return etternavn;
    }


}
