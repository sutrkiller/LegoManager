/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.Entities;

import cz.muni.fi.pa165.legomanager.Enums.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 *
 * @author Tobias
 */
public class Piece {
    private Long id;
    private String name;
    private Color currentColor;
    private Set<Color> colors = new HashSet<Color>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.currentColor);
        hash = 89 * hash + Objects.hashCode(this.colors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Piece)) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getCurrentColor(), other.getCurrentColor())) {
            return false;
        }
        if (!Objects.equals(this.getColors(), other.getColors())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Piece{" + "id=" + id + ", name=" + name + ", currentColor=" + currentColor + '}';
    }

    

    

}
