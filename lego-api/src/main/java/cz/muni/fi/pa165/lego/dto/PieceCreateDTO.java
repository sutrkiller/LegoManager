package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.lego.enums.Color;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PieceCreateDTO {

    @NotNull
    private Color color;

    @NotNull
    private Long pieceTypeId;

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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PieceCreateDTO)) {
            return false;
        }
        final PieceCreateDTO other = (PieceCreateDTO) obj;
        if (!Objects.equals(this.getColor(), other.getColor())) {
            return false;
        }
        if (!Objects.equals(this.getPieceTypeId(), other.getPieceTypeId())) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.getColor());
        hash = 71 * hash + Objects.hashCode(this.getPieceTypeId());
        return hash;
    }

    @Override
    public String toString() {
        return "PieceCreateDTO{" +
                "color=" + color +
                ", pieceTypeId=" + pieceTypeId +
                '}';
    }
}
