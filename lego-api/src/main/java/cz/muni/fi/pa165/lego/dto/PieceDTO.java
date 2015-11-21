package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.lego.enums.Color;

import java.util.Objects;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public class PieceDTO {

    private Long id;

    private Color color;

    private Long pieceTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Long getPieceTypeId() {
        return pieceTypeId;
    }

    public void setPieceTypeId(Long pieceTypeId) {
        this.pieceTypeId = pieceTypeId;
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
                ", color=" + color +
                ", pieceTypeId=" + pieceTypeId +
                '}';
    }
}
