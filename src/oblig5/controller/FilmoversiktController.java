package oblig5.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import oblig5.MainJavaFX;
import oblig5.model.Film;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class FilmoversiktController {
    @FXML
    public Button KnappRedigerFilm;
    @FXML
    private Label filmNavn;
    @FXML
    private ListView<Film> filmerListe;
    @FXML
    private TextField filmUtgivelse, filmSpilletid;
    @FXML
    private TextArea filmBeskrivelse;
    @FXML
    private ImageView filmBilde = new ImageView();
    @FXML
    private ComboBox filmSortering;


    public void initialize() {
        filmerListe.setItems(MainJavaFX.mainJavaFXapp.getFilmer());

        ObservableList<String> sorteringsValg = FXCollections.observableArrayList();
        sorteringsValg.add("A - Å");
        sorteringsValg.add("Å - A");
        sorteringsValg.add("Først - Sist");
        sorteringsValg.add("Sist - Først");

        filmSortering.setValue("VELG SORTERING");
        filmSortering.setItems(sorteringsValg);


        filmerListe.setCellFactory(new Callback<ListView<Film>, ListCell<Film>>() {
            @Override
            public ListCell<Film> call(ListView<Film> enFilm) {
                return new NestetFilmCelleKlasse();
            }
        });

        filmerListe.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Film>() {
            @Override
            public void changed(ObservableValue<? extends Film> observable, Film oldValue, Film newValue) {
                filmNavn.setText(newValue.getTitle());
                filmUtgivelse.setText(String.valueOf(newValue.getUtgivelsesdato()));
                filmSpilletid.setText(String.valueOf(newValue.getSpilletid()) + " minutter.");
                filmBeskrivelse.setText(newValue.getBeskrivelse());

                Image bilde = new Image("https://image.tmdb.org/t/p/w500" + newValue.getBildeUrl());
                filmBilde.setImage(bilde);

                filmBeskrivelse.setWrapText(true);
            }
        });

        KnappRedigerFilm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (filmerListe.getSelectionModel().getSelectedItem() != null) {
                    try {
                        MainJavaFX.mainJavaFXapp.redigerLayout(filmerListe.getSelectionModel().getSelectedItem());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    MainJavaFX.mainJavaFXapp.feilmelding("Du må velge en film fra listen før du kan redigere!");
                }
            }
        });

    }

    public void slettFilm(ActionEvent actionEvent) {
            MainJavaFX.mainJavaFXapp.getFilmer().remove(filmerListe.getSelectionModel().getSelectedIndex());
    }

    public void leggTilFilm(ActionEvent actionEvent) throws IOException {
        MainJavaFX.mainJavaFXapp.nyFilm();
    }

    public void sorterFilm(ActionEvent actionEvent) {
        if(filmSortering.getValue() == "A - Å") {
            Collections.sort(MainJavaFX.mainJavaFXapp.getFilmer());
            filmerListe.setItems(MainJavaFX.mainJavaFXapp.getFilmer());
        }
        if(filmSortering.getValue() == "Å - A") {
            Collections.reverse(MainJavaFX.mainJavaFXapp.getFilmer());
            filmerListe.setItems(MainJavaFX.mainJavaFXapp.getFilmer());
        }
        if(filmSortering.getValue() == "Først - Sist") {
            Collections.sort(MainJavaFX.mainJavaFXapp.getFilmer(), new Comparator<Film>() {
                @Override
                public int compare(Film o1, Film o2) {
                    return o1.getUtgivelsesdato().compareTo(o2.getUtgivelsesdato());
                }
            });
        }
        if(filmSortering.getValue() == "Sist - Først") {
            Collections.sort(MainJavaFX.mainJavaFXapp.getFilmer(), new Comparator<Film>() {
                @Override
                public int compare(Film o1, Film o2) {
                    return o2.getUtgivelsesdato().compareTo(o1.getUtgivelsesdato());
                }
            });
        }
    }

    public static class NestetFilmCelleKlasse extends ListCell<Film> {
        @Override
        public void updateItem(Film enFilm, boolean empty) {
            super.updateItem(enFilm, empty);
            if (enFilm != null) {
                setItem(enFilm);
                setText(enFilm.getTitle() + " (" + enFilm.getUtgivelsesdato().getYear() + ")");
            }
        }
    }


}
