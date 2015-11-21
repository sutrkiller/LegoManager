package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service for entity {@link Piece}.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */
@Service
public interface PieceService {

    /**
     * Create the given Piece entity.
     *
     * @param piece entity
     */
    public void create(Piece piece);

    /**
     * Update the given existing Piece entity.
     *
     * @param piece entity
     */
    public void update(Piece piece);

    /**
     * Delete the given existing Piece entity.
     *
     * @param piece entity
     */
    public void delete(Piece piece);

    /**
     * Get Piece entity with the given ID.
     *
     * @param id id of the piece
     * @return existing Piece with given id
     */
    public Piece findById(Long id);

    /**
     * Get Piece entity with the given name.
     *
     * @param name name of the piece
     * @return existing Piece with given name
     */
    public Piece findByName(String name);

    /**
     * Get all existing Piece entities.
     *
     * @return list of existing Piece entities
     */
    public List<Piece> findAll();
}
