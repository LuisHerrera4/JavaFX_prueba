package com.example.prueba;

import javafx.scene.paint.Color;

public class PiezasTetris {
    private int[][] forma;
    private Color color;

    public static final int[][][] FORMAS = {
            {{1, 1, 1, 1}}, // Pieza I
            {{1, 1}, {1, 1}}, // Pieza O
            {{1, 1, 1}, {1, 0, 0}}, // Pieza L
            {{1, 1, 1}, {0, 1, 0}}, // Pieza T
            {{0, 1, 1}, {1, 1, 0}}, // Pieza S
            {{1, 1, 0}, {0, 1, 1}}, // Pieza Z
            {{1, 1, 1}, {0, 0, 1}}  // Pieza J
    };

    public static final Color[] COLORS = {
            Color.CYAN, Color.YELLOW, Color.ORANGE,
            Color.PURPLE, Color.GREEN, Color.RED, Color.BLUE
    };

    public PiezasTetris(int type) {
        forma = FORMAS[type];
        color = COLORS[type];
    }

    public int[][] getForma() {
        return forma;
    }

    public Color getColor() {
        return color;
    }

    public int[][] getFormaRotada() {
        int filas = forma.length;
        int columnas = forma[0].length;
        int[][] rotada = new int[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                rotada[j][filas - i - 1] = forma[i][j];
            }
        }
        return rotada;
    }


    public void rotar() {
        int filas = forma.length;
        int columnas = forma[0].length;
        int[][] rotada = new int[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                rotada[j][filas - i - 1] = forma[i][j];
            }
        }
        forma = rotada;
    }

}
