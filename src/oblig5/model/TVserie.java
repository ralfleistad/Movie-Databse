package oblig5.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Klassen representerer en TVserie og informasjon om den.
 * Informasjon som holdes her er tittel, beskrivelse, utgivelsesdato, liste med episoder, gjennomsnittlig spilletid, antall sesonger og en liste med alle serier.
 * Klassen implementerer interfacet Comparable og Overrider metoden 'compareTo'.
 * @version 2.1
 * @author Ralf Leistad
 */
public class TVserie implements Comparable<TVserie> {
    private String serieTitle;
    private String beskrivelse;
    private LocalDate utgivelse;
    private ArrayList<Episode> episoder = new ArrayList<>();
    private int gjennomsnittligSpilletid;
    private int antallSesonger;
    private static ArrayList<TVserie> serier = new ArrayList<>();

    // KONSTRUKTØR
    public TVserie(String serieTitle, String beskrivelse, LocalDate utgivelse) {
        this.serieTitle = serieTitle;
        this.beskrivelse = beskrivelse;
        this.utgivelse = utgivelse;
        serier.add(this);
    }

    // METODER

    /**
     * Går gjennom en liste med alle episoder i en serie.
     * @return alle rollene for hver episode i en serie (oppstår duplikater).
     */
    public ArrayList<Rolle> hentCast() {
        ArrayList<Rolle> alleRollerSerie = new ArrayList<>();
        for (Episode enEpisode : episoder) {
            alleRollerSerie.addAll(enEpisode.getRoller());
        }
        return alleRollerSerie;
    }

    /**
     * Oppdaterer variabelen "gjennomsnittligSpilletid" basert på alle episodene i en serie ved hjelp av en for-løkke.
     */
    private void oppdaterGjennomsnittligSpilletid() {
        gjennomsnittligSpilletid = 0;
        for (int i = 0; i < episoder.size(); i++) {
            gjennomsnittligSpilletid += episoder.get(i).getSpilletid();
        }
        gjennomsnittligSpilletid = gjennomsnittligSpilletid / episoder.size();
    }

    /**
     * Legger en episode til i listen så fremt episodens sesong ikke overskrider forrige sesong + 1.
     * @param episode Tar en enkelt episode som parameter og legger den til i listen.
     */
    public void leggTilEpisode(Episode episode) {
        if(episode.getSesong() > antallSesonger + 1) {
            System.out.println("Episoden du prøvde å legge til har ikke en tilgjengelig sesong, og ble derfor ikke lagt til!"); // Denne utskriften kommer øverst i konsollen
            System.out.println("////////////////");
        }
        else {
            this.episoder.add(episode);
            oppdaterGjennomsnittligSpilletid();
            antallSesonger = episode.getSesong();
        }
    }

    // GETTERE
    public String getSerieTitle() {
        return serieTitle;
    }
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public LocalDate getUtgivelse() {
        return utgivelse;
    }
    public ArrayList<Episode> getEpisoder() {
        return episoder;
    }
    public int getGjennomsnittligSpilletid() {
        return gjennomsnittligSpilletid;
    }
    public int getAntallSesonger() {
        return antallSesonger;
    }

    /**
     * Lar deg skrive ut alle episoder i en valgt sesong basert på input.
     * @param sesong Hvilken sesong du vil ha tilhørende episoder til.
     * @return En liste med alle episodene i den valgte sesongen.
     */
    public ArrayList<Episode> getEpisoderSesong(int sesong) {
        ArrayList<Episode> liste = new ArrayList<>();
        for (int i = 0; i < episoder.size(); i++) {
            if (episoder.get(i).getSesong() == sesong) {
                liste.add(episoder.get(i));
            }
        }
        return liste;
    }
    public static ArrayList<TVserie> getSerier() {
        return serier;
    }

    @Override
    public String toString() {
        return getSerieTitle() + getBeskrivelse() + ", første episode gikk på luften " + getUtgivelse() + ".";
    }

    /**
     * Override av 'compareTo' som sammenligner TVserier alfabetisk på tittel.
     * @param annenSerie Valgt serie som skal brukes til sammenligning.
     * @return Returnerer sammenligningen av to serier.
     */
    @Override
    public int compareTo(TVserie annenSerie) {
        return this.serieTitle.compareTo(annenSerie.serieTitle);
    }
}
