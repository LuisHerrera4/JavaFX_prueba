package com.example.prueba;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ControladorJuego {
    @FXML private Canvas lienzoJuego;
    @FXML private Label etiquetaPuntuacion;

    private TableroJuego tablero;
    private PiezaActual piezaActual;
    private GraphicsContext gc;
    private Timeline loopJuego;
    private int puntuacion = 0;

    @FXML
    public void initialize() {
        gc = lienzoJuego.getGraphicsContext2D();
        tablero = new TableroJuego();
        piezaActual = new PiezaActual();
        iniciarLoop();
    }

    private void iniciarLoop() {
        loopJuego = new Timeline(new KeyFrame(Duration.millis(150), e -> actualizar()));
        loopJuego.setCycleCount(Timeline.INDEFINITE);
        loopJuego.play();
    }

    private void actualizar() {
        if (tablero.puedeColocarPieza(piezaActual.getPieza(), piezaActual.getX(), piezaActual.getY() + 1)) {
            piezaActual.moverAbajo();
        } else {
            // Si la pieza esta en la fila 0, termina el juego
            if (piezaActual.getY() == 0) {
                mostrarGameOver();
                return;
            }

            tablero.colocarPieza(piezaActual.getPieza(), piezaActual.getX(), piezaActual.getY());
            int lineasEliminadas = tablero.limpiarLineas();
            if (lineasEliminadas > 0) {
                puntuacion += lineasEliminadas * 100;
            }

            piezaActual = new PiezaActual();
        }

        tablero.dibujar(gc);
        piezaActual.dibujar(gc);
        etiquetaPuntuacion.setText("Puntuación: " + puntuacion);
    }



    public void configurarControles(Scene escena) {
        escena.setOnKeyPressed(event -> manejarTeclado(event.getCode()));
        escena.getRoot().requestFocus();
    }


    private void manejarTeclado(KeyCode codigo) {

        if (codigo == KeyCode.LEFT && tablero.puedeColocarPieza(piezaActual.getPieza(), piezaActual.getX() - 1, piezaActual.getY())) {
            piezaActual.moverIzquierda();
        } else if (codigo == KeyCode.RIGHT && tablero.puedeColocarPieza(piezaActual.getPieza(), piezaActual.getX() + 1, piezaActual.getY())) {
            piezaActual.moverDerecha();
        } else if (codigo == KeyCode.DOWN && tablero.puedeColocarPieza(piezaActual.getPieza(), piezaActual.getX(), piezaActual.getY() + 1)) {
            piezaActual.moverAbajo();
        }else if (codigo == KeyCode.UP) {
            if (tablero.puedeRotarPieza(piezaActual.getPieza(), piezaActual.getX(), piezaActual.getY())) {
                piezaActual.rotar();
            }
        }
    }

    private void mostrarGameOver() {
        loopJuego.stop(); // Detener el juego

        gc.setFill(Color.RED);
        gc.setFont(new Font(40));
        gc.fillText("GAME OVER", 50, 300); // Posicionar en el centro

        System.out.println("GAME OVER");
    }


    @FXML
    public void startGame() {
        if (loopJuego != null) {
            loopJuego.stop();
        }
        piezaActual = new PiezaActual();
        puntuacion = 0;
        etiquetaPuntuacion.setText("Puntuación: " + puntuacion);
        tablero = new TableroJuego();
        iniciarLoop();

        lienzoJuego.requestFocus();
    }

    @FXML
    public void restartGame() {
        startGame();
    }
}
