package com.example.prueba;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TetrisApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tetris.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ControladorJuego controlador = loader.getController();
        controlador.configurarControles(scene);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tetris JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
