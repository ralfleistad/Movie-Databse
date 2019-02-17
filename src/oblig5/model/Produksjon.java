package oblig5.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Klasse som holder på store deler av informasjon som er nødvendig for en film eller episode.
 * @version 2.1
 * @author Ralf Leistad
 */

abstract class Produksjon {
    private String title;
    private int spilletid;
    private LocalDate utgivelsesdato;
    private Person regissor;
    private ArrayList<Rolle> roller = new ArrayList<>();
    private String beskrivelse;
    private String bildeUrl;

    //KONSTRUKTØRER
    public Produksjon(String title, int spilletid, LocalDate utgivelsesdato, String beskrivelse) {
        this.title = title;
        this.spilletid = spilletid;
        this.utgivelsesdato = utgivelsesdato;
        this.beskrivelse = beskrivelse;
    }
    public Produksjon(String title, int spilletid, LocalDate utgivelsesdato, String beskrivelse, String bildeUrl) {
        this.title = title;
        this.spilletid = spilletid;
        this.utgivelsesdato = utgivelsesdato;
        this.beskrivelse = beskrivelse;
        this.bildeUrl = bildeUrl;
    }
    public Produksjon() {
    }

    @Override
    public String toString() {
        return title + utgivelsesdato + spilletid + regissor + roller;
    }

    //METODER

    /**
     * Lar deg legge til en rolle i en liste som opprettes som feltvariabel.
     * @param rolle Tar en rolle som parameter da metoden kalles.
     */
    public void leggTilEnRolle(Rolle rolle) {
        roller.add(rolle);
    }

    /**
     * Lar deg legge til en liste med roller i listen som instansieres som feltvariabel.
     * @param rolle Tar en liste med roller som parameter.
     */
    public void leggTilMangeRoller(ArrayList<Rolle> rolle) {
        roller.addAll(rolle);
    }

    //GETTERE
    public String getTitle() {
        return title;
    }
    public int getSpilletid() {
        return spilletid;
    }
    public LocalDate getUtgivelsesdato() {
        return utgivelsesdato;
    }
    public void getRegissor() {
        if (this instanceof Film) { // IF-TEST SOM SJEKKER OM DET ER FILM ELLER EPISODE OG SKRIVER UT RELEVANT INFO
            System.out.println(regissor.getFultNavn() + " regisserte filmen '" + getTitle() + "'");
        }
        else if (this instanceof Episode) {
            System.out.println(regissor.getFultNavn() + " regisserte episoden '" + getTitle() + "'");
        }
        else {
            System.out.println("Valgt element er hverken en film eller en episode som tilhører en TV-serie!");
        }
    }
    public ArrayList<Rolle> getRoller() {
        return roller;
    }
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public String getBildeUrl() {
        return bildeUrl;
    }

    // SETTERE
    public void setRegissor(Person regissor) {
        this.regissor = regissor;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setSpilletid(int spilletid) {
        this.spilletid = spilletid;
    }
    public void setUtgivelsesdato(LocalDate utgivelsesdato) {
        this.utgivelsesdato = utgivelsesdato;
    }
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}
