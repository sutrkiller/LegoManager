package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;

/**
 * PieceTypeDao manages PieceType entities. Interface supports basic CRUD operations.
 * 
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 23.10.2015
 */
public interface PieceTypeDao {

    /**
     * Creates new pieceType in DB.
     *
     * @param pieceType pieceType to be added to DB
     * @throws IllegalArgumentException when pieceType is null
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException when PieceType already exists in DB.
     * @throws cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException when entity constraints are violated (e.g. name is null)
     */
    public void create(PieceType pieceType) throws EntityAlreadyExistsException, LegoPersistenceException;

    /**
     * Updates already existing pieceType in DB.
     *
     * @param pieceType pieceType to be updated in DB
     * @throws IllegalArgumentException when pieceType is null
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when PieceType not exists in DB.
     * @throws cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException when entity constraints are violated (e.g. name is null)
     */
    public void update(PieceType pieceType) throws EntityNotExistsException, LegoPersistenceException;

    /**
     * Deletes pieceType from DB.
     *
     * @param pieceType pieceType to be deleted from DB
     * @throws IllegalArgumentException when pieceType is null
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when PieceType not exists in DB.
     */
    public void delete(PieceType pieceType) throws EntityNotExistsException;

    /**
     * Returns piece with corresponding id.
     *
     * @param id id of the piece
     * @return piece with corresponding id
     * @throws IllegalArgumentException when id is null or smaller than 0
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when PieceType not exists in DB.
     */
    public PieceType findById(Long id) throws EntityNotExistsException;

    /**
     * Returns piece with corresponding name.
     * 
     * @param name name of the piece
     * @return piece with corresponding name
     * @throws IllegalArgumentException when name is null
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when PieceType not exists in DB.
     */
    public PieceType findByName(String name) throws EntityNotExistsException;

    /**
     * Returns list of all existing pieces in DB.
     *
     * @return list of all existing pieces
     */
    public List<PieceType> findAll();

}
