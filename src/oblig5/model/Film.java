package oblig5.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Klasse som brukes for å opprette film-objekter.
 * Klassen har en statisk liste som holder på filmer som blir opprettet.
 * Arver fra klassen 'Produksjon' og bruker feltvariablene som ligger der.
 * Klassen implementerer interfacet 'Comparable' og Overrider metoden 'compareTo'.
 * @version 2.1
 * @author Ralf Leistad
 */

public class Film extends Produksjon implements Comparable<Film> {
    private static ArrayList<Film> filmer = new ArrayList<>();

    // KONSTRUKTØR
    public Film(String title, int spilleTid, LocalDate utgivelsesdato, String beskrivelse) {
        super(title, spilleTid, utgivelsesdato, beskrivelse);
        filmer.add(this);
    }
    public Film(String title, int spilleTid, LocalDate utgivelsesdato, String beskrivelse, String bildeUrl) {
        super(title, spilleTid, utgivelsesdato, beskrivelse, bildeUrl);
        filmer.add(this);
    }
    public Film(){
    }

    // GETTERE
    public static ArrayList<Film> getFilmer() {
        return filmer;
    }

    @Override
    public String toString() {
        //return "Filmen '" + getTitle() + "' kom ut " + getUtgivelsesdato() + " og varer i " + getSpilletid() + " minutter.";
        return getTitle() + " (" + getUtgivelsesdato().getYear() + ")";
    }
    @Override
    public int compareTo(Film annenFilm) {
        return this.getTitle().compareTo(annenFilm.getTitle());
    }


}
