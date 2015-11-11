package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;

/**
 * PieceDao provides CRUD operations for Piece entity.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 29.10.2015
 */
public interface PieceDao{
    /**
     * Creates new piece in DB.
     *
     * @param piece piece to be added to DB
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException when piece already in DB.
     * @throws IllegalArgumentException when piece is null
     * @throws cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException when entity constraints are violated
     */
    public void create(Piece piece) throws EntityAlreadyExistsException, LegoPersistenceException;

    /**
     * Updates already existing piece in DB.
     *
     * @param piece piece to be updated in DB
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when Piece not in DB.
     * @throws IllegalArgumentException when piece is null
     * @throws cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException when entity constraints are violated
     */
    public void update(Piece piece) throws EntityNotExistsException, LegoPersistenceException;

    /**
     * Deletes piece from DB.
     *
     * @param piece piece to be deleted from DB
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when entity not in DB.
     * @throws IllegalArgumentException when piece is null
     */
    public void delete(Piece piece) throws EntityNotExistsException;

    /**
     * Returns piece with corresponding id.
     *
     * @param id id of the piece
     * @return piece with corresponding id
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when entity not found
     * @throws IllegalArgumentException when id is null or smaller than 0
     */
    public Piece findById(Long id) throws EntityNotExistsException;

    /**
     * Returns list of all existing pieces in DB.
     *
     * @return list of all existing pieces
     */
    public List<Piece> findAll();
    
}
