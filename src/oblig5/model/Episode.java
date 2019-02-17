package oblig5.model;

import java.time.LocalDate;

public class Episode extends Produksjon implements Comparable<Episode> {
    private int episodeNr;
    private int sesong;

    // KONSTRUKTØR
    public Episode(int episodeNr, int sesong, String title, int spilletid, LocalDate utgivelsesdato, String beskrivelse) {
        super(title, spilletid, utgivelsesdato, beskrivelse);
        this.episodeNr = episodeNr;
        this.sesong = sesong;
    }

    // GETTERE
    public int getEpisodeNr() {
        return episodeNr;
    }
    public int getSesong() {
        return sesong;
    }

    @Override
    public String toString() {
        return "S" + getSesong() + "E" + getEpisodeNr() + ". Den er titulert '" + getTitle() + "'";
    }
    @Override
    public int compareTo(Episode annenEpisode) {
        if (this.sesong > annenEpisode.sesong) {
            return 1;
        }
        else if (this.sesong < annenEpisode.sesong) {
            return -1;
        }
        if (this.episodeNr > annenEpisode.episodeNr) {
            return 1;
        }
        else if (this.episodeNr < annenEpisode.episodeNr) {
            return -1;
        }
        else {
            return 0;
        }
    }
}