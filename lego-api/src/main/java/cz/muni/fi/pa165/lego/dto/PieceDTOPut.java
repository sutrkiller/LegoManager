package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.enums.Color;

import javax.validation.constraints.NotNull;

/**
 * Data transfer object for {@link Piece class}
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public class PieceDTOPut {

    @NotNull
    private Color currentColor;

    @NotNull
    private Long pieceTypeId;

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public Long getPieceTypeId() {
        return pieceTypeId;
    }

    public void setPieceTypeId(Long pieceTypeId) {
        this.pieceTypeId = pieceTypeId;
    }

    @Override
    public String toString() {
        return "PieceDTO{" +
                "currentColor=" + getCurrentColor() + 
                ", pieceType=" + getPieceTypeId() + '}';
    }
    
}
