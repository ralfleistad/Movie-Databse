package oblig5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import oblig5.controller.RedigerFilmController;
import oblig5.model.Film;

import java.io.IOException;
import java.time.LocalDate;

public class MainJavaFX extends Application {
    private ObservableList<Film> filmer = FXCollections.observableArrayList();
    public static MainJavaFX mainJavaFXapp;
    private Stage primaryStage;

    public MainJavaFX() {
        mainJavaFXapp = this;
        filmer = Filbehandling.lesFraFil();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Oblig 5 - OOP");
        hovedLayout();
    }

    @Override
    public void stop() {
        System.out.println("Avsluttet");
        Filbehandling.skrivTilFil();
    }

    public void hovedLayout() throws IOException {
        FXMLLoader fxmlInnlaster = new FXMLLoader();
        fxmlInnlaster.setLocation(getClass().getResource("view/Filmoversikt.fxml"));
        Parent filmoversiktNode = fxmlInnlaster.load();
        Scene filmScene = new Scene(filmoversiktNode);
        primaryStage.setScene(filmScene);
        primaryStage.show();
    }

    public void redigerLayout(Film film) throws IOException {
        Stage redigerFilmStage = new Stage();
        FXMLLoader fxmlInnlaster = new FXMLLoader();
        fxmlInnlaster.setLocation(getClass().getResource("view/RedigerFilm.fxml"));
        Parent redigerFilmoversiktNode = fxmlInnlaster.load();
        RedigerFilmController redigerFilm = fxmlInnlaster.getController();
        redigerFilm.setFilm(film);
        Scene filmScene = new Scene(redigerFilmoversiktNode);
        redigerFilmStage.setScene(filmScene);
        redigerFilmStage.setTitle("Oblig 5 - OOP");
        redigerFilmStage.initModality(Modality.APPLICATION_MODAL);
        redigerFilmStage.initOwner(primaryStage);
        redigerFilmStage.showAndWait();
    }

    public void nyFilm() throws IOException {
        Stage nyFilmStage = new Stage();
        FXMLLoader fxmlInnlaster = new FXMLLoader();
        fxmlInnlaster.setLocation(getClass().getResource("view/NyFilm.fxml"));
        Parent nyFilmNode = fxmlInnlaster.load();
        Scene filmScene = new Scene(nyFilmNode);
        nyFilmStage.setScene(filmScene);
        nyFilmStage.setTitle("Oblig 5 - OOP");
        nyFilmStage.initModality(Modality.APPLICATION_MODAL);
        nyFilmStage.initOwner(primaryStage);
        nyFilmStage.showAndWait();
    }

    public void feilmelding(String tilbakemelding) {
        Alert feil = new Alert(Alert.AlertType.ERROR);
        feil.setTitle("En feil oppsto!");
        feil.setHeaderText(null);
        feil.setContentText(tilbakemelding);
        feil.show();
    }

    public ObservableList<Film> getFilmer() {
        return filmer;
    }


}
