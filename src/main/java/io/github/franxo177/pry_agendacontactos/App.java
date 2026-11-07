package io.github.franxo177.pry_agendacontactos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/" + "io/github/franxo177/pry_agendacontactos" + "/view/main-view.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Agenda Contactos - By Franxo");
        stage.show();
    }
}
