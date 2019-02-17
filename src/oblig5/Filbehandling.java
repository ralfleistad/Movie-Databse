package oblig5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oblig5.model.Film;

import java.io.*;
import java.time.LocalDate;

public class Filbehandling {
    private static File filMedFilmer = new File("filmer_1000.csv");

    public static ObservableList<Film> lesFraFil(){
        ObservableList<Film> filmene = FXCollections.observableArrayList();
        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filMedFilmer))) {
            String datalinje;
            while ((datalinje = bufretLeser.readLine()) != null) {
                String[] deltDatalinje = datalinje.split(";");
                Film enFilm = new Film(deltDatalinje[0], Integer.valueOf(deltDatalinje[2]), LocalDate.parse(deltDatalinje[3]), deltDatalinje[1], deltDatalinje[4]);
                filmene.add(enFilm);
            }
        } catch (FileNotFoundException fileExc) {
            MainJavaFX.mainJavaFXapp.feilmelding("Klarte ikke å laste inn alle filmene...");
        } catch (IOException ioeExc) {
            System.out.println(ioeExc);
        }
        return filmene;
    }

    public static void skrivTilFil() {
        try (BufferedWriter bufretSkriver = new BufferedWriter(new FileWriter(filMedFilmer))) {
            ObservableList<Film> filmene = MainJavaFX.mainJavaFXapp.getFilmer();
            for (Film enFilm : filmene) {
                bufretSkriver.write(enFilm.getTitle() + ";" + enFilm.getBeskrivelse() + ";" + enFilm.getSpilletid() + ";" + enFilm.getUtgivelsesdato() + ";" + enFilm.getBildeUrl());
                bufretSkriver.newLine();
            }
        } catch (FileNotFoundException fileExc) {
            MainJavaFX.mainJavaFXapp.feilmelding("Klarte ikke å skrive til fil :'(");
        } catch (IOException ioeExc) {
            System.out.println(ioeExc);
        }
    }
}
