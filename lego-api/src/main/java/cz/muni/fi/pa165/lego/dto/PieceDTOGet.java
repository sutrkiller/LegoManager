package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.entities.Piece;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Data transfer object for {@link Piece class}
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public class PieceDTOGet {

    private Long id;

    private Color currentColor;

    private PieceTypeDTO type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public PieceTypeDTO getType() {
        return type;
    }

    public void setType(PieceTypeDTO type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (!(o instanceof PieceDTOGet)) {
            return false;
        }

        PieceDTOGet pieceDTO = (PieceDTOGet) o;

        if (getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pieceDTO.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "PieceDTO{"
                + "id=" + getId()
                + ", currentColor=" + getCurrentColor()
                + ", type=" + getType() + '}';
    }
}
