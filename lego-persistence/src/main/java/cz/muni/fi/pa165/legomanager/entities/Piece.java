package cz.muni.fi.pa165.legomanager.entities;

import cz.muni.fi.pa165.legomanager.enums.Color;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Entity class Piece contains name, currentColor and pieceType.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 28.10.2015
 */
@Entity
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Color currentColor;

    @NotNull
    @ManyToOne
    private PieceType type;

    public Long getId() {
        return id;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Piece)) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (this.getId() == null) {
            return false;
        }
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

}
