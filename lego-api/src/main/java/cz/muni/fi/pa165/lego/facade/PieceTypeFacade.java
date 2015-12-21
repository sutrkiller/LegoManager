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
     * @return created pieceType
     */
    PieceTypeDTO create(PieceTypeDTO pieceTypeDTO);

    /**
     * Update the given pieceType.
     *
     * @param pieceTypeDTO pieceType to be created
     */
    void update(PieceTypeDTO pieceTypeDTO, Long id);

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
    PieceTypeDTO findById(Long id);

    /**
     * Get pieceType by name.
     *
     * @param name name of the pieceType
     * @return existing piece with the given name
     */
    PieceTypeDTO findByName(String name);

    /**
     * Get all existing pieceTypes.
     *
     * @return all existing pieceTypes
     */
    List<PieceTypeDTO> findAll();

}
