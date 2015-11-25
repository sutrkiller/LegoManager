package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.PieceDTO;

import java.util.List;

/**
 * PieceTypeFacade defines operations available within the Lego API to simplify
 * the usage of the application.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 21.11.2015
 */
public interface PieceTypeFacade {

    /**
     * Create the given piece.
     *
     * @param pieceDTO pieceDTO to be created
     * @return id of created piece
     */
    Long createPiece(PieceDTO pieceDTO);

    /**
     * Update the given piece.
     *
     * @param pieceDTO pieceDTO to be created
     */
    void updatePiece(PieceDTO pieceDTO);

    /**
     * Delete the given piece.
     *
     * @param pieceId id of the piece
     */
    void deletePiece(Long pieceId);

    /**
     * Get all existing pieces.
     *
     * @return all existing pieces
     */
    List<PieceDTO> getAllPieces();


    /**
     * Get pieces of model.
     *
     * @param id id of the model
     * @return existing piece with the given id
     */
    public PieceDTO getPieceById(Long id);

}
