/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.Entities;

import cz.muni.fi.pa165.legomanager.Enunms.Color;
import java.util.Objects;

/**
 *
 * @author Tobias
 */
public class Piece {
    private Long id;
    private String name;
    private Color color;
    
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Piece)) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getColor(), other.getColor())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.getName());
        hash = 67 * hash + Objects.hashCode(this.getColor());
        return hash;
    }

}
