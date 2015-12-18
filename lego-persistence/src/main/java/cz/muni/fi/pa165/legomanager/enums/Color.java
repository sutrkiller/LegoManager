/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.enums;

/**
 * Colors for LegoManager classes
 *
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 2015/10/30
 */
public enum Color {
    WHITE(230, 230, 230),
    GRAY(150, 150, 150),
    BLACK(50, 50, 50),

    BROWN(150, 100, 50),
    RED(230, 50, 50),
    ORANGE(255, 150, 0),
    YELLOW(255, 230, 0),
    GREEN(100, 200, 50),
    CYAN(0, 200, 200),
    BLUE(50, 100, 230),
    MAGENTA(150, 80, 255),
    PINK(255, 120, 200);

    private int r;
    private int g;
    private int b;

    Color(int r, int g, int b) {
        if (0 > r || r > 255) throw new IllegalArgumentException("Red par of color has to be between 0 and 255.");
        if (0 > g || g > 255) throw new IllegalArgumentException("Green par of color has to be between 0 and 255.");
        if (0 > b || b > 255) throw new IllegalArgumentException("Blue par of color has to be between 0 and 255.");
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }




}
