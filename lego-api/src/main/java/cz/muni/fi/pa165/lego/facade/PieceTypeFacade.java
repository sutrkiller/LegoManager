package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.PieceTypeDTO;

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
     * @param pieceTypeDTO pieceType to be created
     * @return id of created pieceType
     */
    Long createPieceType(PieceTypeDTO pieceTypeDTO);

    /**
     * Update the given pieceType.
     *
     * @param pieceTypeDTO pieceType to be created
     */
    void updatePieceType(PieceTypeDTO pieceTypeDTO);

    /**
     * Delete the given pieceType.
     *
     * @param pieceTypeId id of the pieceType
     */
    void deletePieceType(Long pieceTypeId);

    /**
     * Get all existing pieceTypes.
     *
     * @return all existing pieceTypes
     */
    List<PieceTypeDTO> getAllPieceTypes();

    /**
     * Get pieceType by id.
     *
     * @param id id of the pieceType
     * @return existing piece with the given id
     */
    public PieceTypeDTO getPieceTypeById(Long id);

    /**
     * Get pieceType by name.
     *
     * @param name name of the pieceType
     * @return existing piece with the given name
     */
    PieceTypeDTO getPieceTypeByName(String name);
}
