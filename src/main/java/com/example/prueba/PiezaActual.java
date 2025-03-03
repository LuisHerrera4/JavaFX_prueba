package com.example.prueba;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PiezaActual {
    private PiezasTetris pieza;
    private int x, y;

    public PiezaActual() {
        int tipoPieza = (int) (Math.random() * PiezasTetris.FORMAS.length);
        pieza = new PiezasTetris(tipoPieza);
        x = 4;
        y = 0;
    }

    public PiezasTetris getPieza() {
        return pieza;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moverIzquierda() {
        x--;
    }

    public void moverDerecha() {
        x++;
    }

    public void moverAbajo() {
        y++;
    }

    public void rotar() {
        pieza.rotar();
    }

    public void dibujar(GraphicsContext gc) {
        int[][] forma = pieza.getForma();
        Color color = pieza.getColor();
        gc.setFill(color);

        for (int row = 0; row < forma.length; row++) {
            for (int col = 0; col < forma[row].length; col++) {
                if (forma[row][col] != 0) {
                    gc.fillRect((x + col) * 30, (y + row) * 30, 30, 30);
                }
            }
        }
    }
}
