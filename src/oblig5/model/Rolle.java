package oblig5.model;

public class Rolle {
    private String karakterFornavn;
    private String karakterEtternavn;
    private Person skuespiller;

    // KONSTRUKTØR
    public Rolle(String karakterFornavn, String karakterEtternavn, Person skuespiller) {
        this.karakterFornavn = karakterFornavn;
        this.karakterEtternavn = karakterEtternavn;
        this.skuespiller = skuespiller;
    }

    @Override
    public String toString() {
        return skuespiller + " spiller " + karakterFornavn + " " + karakterEtternavn + ".";
    }
}
