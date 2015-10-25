package cz.muni.fi.pa165.legomanager.entities;

import cz.muni.fi.pa165.legomanager.enums.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entity class Piece contains name, currentColor and colors that are possible
 * for a Piece instance of the same name.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.10.2015
 */

@Entity
public class Piece {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false,unique=true)
    private String name;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Color currentColor;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Color.class)
    @CollectionTable(name = "PIECE_COLORS")
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
