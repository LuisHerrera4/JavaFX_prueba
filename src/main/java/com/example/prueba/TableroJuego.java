package com.example.prueba;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TableroJuego {
    private static final int COLUMNAS = 10;
    private static final int FILAS = 20;
    private static final int TAMANO_CELDA = 30;
    private int[][] tablero;

    public TableroJuego() {
        tablero = new int[FILAS][COLUMNAS];
    }

    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, COLUMNAS * TAMANO_CELDA, FILAS * TAMANO_CELDA);

        for (int row = 0; row < FILAS; row++) {
            for (int col = 0; col < COLUMNAS; col++) {
                if (tablero[row][col] != 0) {
                    gc.setFill(PiezasTetris.COLORS[tablero[row][col] - 1]);
                    gc.fillRect(col * TAMANO_CELDA, row * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);
                }
            }
        }
    }

    public boolean puedeColocarPieza(PiezasTetris pieza, int x, int y) {
        int[][] forma = pieza.getForma();
        for (int row = 0; row < forma.length; row++) {
            for (int col = 0; col < forma[row].length; col++) {
                if (forma[row][col] != 0) {
                    int newX = x + col;
                    int newY = y + row;
                    if (newX < 0 || newX >= COLUMNAS || newY >= FILAS || (newY >= 0 && tablero[newY][newX] != 0)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void colocarPieza(PiezasTetris pieza, int x, int y) {
        int[][] forma = pieza.getForma();
        int colorIndex = obtenerIndiceColor(pieza.getColor());

        for (int row = 0; row < forma.length; row++) {
            for (int col = 0; col < forma[row].length; col++) {
                if (forma[row][col] != 0) {
                    tablero[y + row][x + col] = colorIndex;
                }
            }
        }
    }

    public boolean puedeRotarPieza(PiezasTetris pieza, int x, int y) {
        int[][] formaRotada = pieza.getFormaRotada();

        for (int row = 0; row < formaRotada.length; row++) {
            for (int col = 0; col < formaRotada[row].length; col++) {
                if (formaRotada[row][col] != 0) {
                    int newX = x + col;
                    int newY = y + row;

                    // Verificar si la rotación está fuera del tablero o choca con otra pieza
                    if (newX < 0 || newX >= COLUMNAS || newY >= FILAS || (newY >= 0 && tablero[newY][newX] != 0)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private int obtenerIndiceColor(Color colorPieza) {
        for (int i = 0; i < PiezasTetris.COLORS.length; i++) {
            if (PiezasTetris.COLORS[i] == colorPieza) {
                return i + 1;
            }
        }
        return -1;
    }

    public int limpiarLineas() {
        int lineasEliminadas = 0;
        for (int row = FILAS - 1; row >= 0; row--) {
            boolean full = true;
            for (int col = 0; col < COLUMNAS; col++) {
                if (tablero[row][col] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int r = row; r > 0; r--) {
                    tablero[r] = tablero[r - 1].clone();
                }
                tablero[0] = new int[COLUMNAS];
                lineasEliminadas++;
                row++;
            }
        }
        return lineasEliminadas;
    }

}
