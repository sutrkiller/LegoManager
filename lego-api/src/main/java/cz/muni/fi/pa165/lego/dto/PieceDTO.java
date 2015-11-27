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
public class PieceDTO {

    private Long id;

    @NotNull
    private Color currentColor;

    @NotNull
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

    public PieceTypeDTO getPieceType() {
        return type;
    }

    public void setPieceType(PieceTypeDTO type) {
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

        if (!(o instanceof PieceDTO)) {
            return false;
        }

        PieceDTO pieceDTO = (PieceDTO) o;

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
        return "PieceDTO{" +
                "id=" + id +
                ", currentColor=" + currentColor +
                ", type=" + type +
                '}';
    }
}
