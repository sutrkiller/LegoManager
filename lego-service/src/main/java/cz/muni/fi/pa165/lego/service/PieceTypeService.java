package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.PieceType;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Service for entity {@link PieceType}.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */
@Service
public interface PieceTypeService {

    /**
     * Create the given PieceType entity.
     *
     * @param pieceType entity
     */
    public void create(PieceType pieceType);

    /**
     * Update the given existing PieceType entity.
     *
     * @param pieceType entity
     */
    public void update(PieceType pieceType);

    /**
     * Delete the given existing PieceType entity.
     *
     * @param pieceType entity
     */
    public void delete(PieceType pieceType);

    /**
     * Get PieceType entity with the given ID.
     *
     * @param id id of the pieceType
     * @return existing PieceType with given id
     */
    public PieceType findById(Long id);

    /**
     * Get PieceType entity with the given name.
     *
     * @param name name of the pieceType
     * @return existing PieceType with given name
     */
    public PieceType findByName(String name);

    /**
     * Get all existing PieceType entities.
     *
     * @return list of existing PieceType entities
     */
    public List<PieceType> findAll();
}
