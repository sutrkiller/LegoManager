package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.PieceTypeDTOGet;
import cz.muni.fi.pa165.lego.dto.PieceTypeDTOPut;

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
     * @return created pieceType
     */
    PieceTypeDTOGet create(PieceTypeDTOPut pieceTypeDTO);

    /**
     * Update the given pieceType.
     *
     * @param pieceTypeDTO pieceType to be created
     * @param id identifier of updated pieceType
     */
    void update(PieceTypeDTOPut pieceTypeDTO, Long id);

    /**
     * Delete the given pieceType.
     *
     * @param pieceTypeId id of the pieceType
     */
    void delete(Long pieceTypeId);

    /**
     * Get pieceType by id.
     *
     * @param id id of the pieceType
     * @return existing piece with the given id
     */
    PieceTypeDTOGet findById(Long id);

    /**
     * Get pieceType by name.
     *
     * @param name name of the pieceType
     * @return existing piece with the given name
     */
    PieceTypeDTOGet findByName(String name);

    /**
     * Get all existing pieceTypes.
     *
     * @return all existing pieceTypes
     */
    List<PieceTypeDTOGet> findAll();

}
