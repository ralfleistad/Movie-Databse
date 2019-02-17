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

public class NyFilmController {
    @FXML
    private Button lagreEndringerOgLukk;
    @FXML
    private TextField nyTittel;
    @FXML
    private TextField nySpilletid;
    @FXML
    private DatePicker nyUtgivelse;
    @FXML
    private TextArea nyBeskrivelse;

    public void initialize() {
        nyBeskrivelse.setWrapText(true);
    }

    public void lagreEndringer(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lagreEndringerOgLukk.getScene().getWindow();
        MainJavaFX.mainJavaFXapp.hovedLayout();
        Film enNyFilm = new Film(nyTittel.getText(), Integer.valueOf(nySpilletid.getText()), nyUtgivelse.getValue(), nyBeskrivelse.getText());
        MainJavaFX.mainJavaFXapp.getFilmer().add(enNyFilm);
        //Filbehandling.lesFraFil().add(enNyFilm);
        stage.close();
    }
}
