package oblig5.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oblig5.MainJavaFX;
import oblig5.model.Film;

import java.io.IOException;

public class RedigerFilmController {
    @FXML
    private Button lagreEndringerOgLukk;
    @FXML
    private DatePicker redigerUtgivelse;
    @FXML
    private TextField redigerTittel;
    @FXML
    private TextField redigerSpilletid;
    @FXML
    private TextArea redigerBeskrivelse;
    private Film film;

    public void setFilm(Film film) {
        this.film = film;
        redigerTittel.setText(film.getTitle());
        redigerSpilletid.setText(String.valueOf(film.getSpilletid()));
        redigerUtgivelse.setValue(film.getUtgivelsesdato());
        redigerBeskrivelse.setText(film.getBeskrivelse());
        redigerBeskrivelse.setWrapText(true);
    }

    public void lagreEndringer(ActionEvent actionEvent) throws IOException {
        film.setTitle(redigerTittel.getText());
        film.setSpilletid(Integer.valueOf(redigerSpilletid.getText()));
        film.setUtgivelsesdato(redigerUtgivelse.getValue());
        film.setBeskrivelse(redigerBeskrivelse.getText());
        MainJavaFX.mainJavaFXapp.hovedLayout();
        Stage stage = (Stage) lagreEndringerOgLukk.getScene().getWindow();
        stage.close();
    }
}
