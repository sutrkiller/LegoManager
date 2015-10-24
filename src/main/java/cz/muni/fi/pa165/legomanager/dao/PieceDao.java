package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.util.List;

/**
 * PieceDao manages Piece entities. Interface supports basic CRUD operations.
 * 
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 23.10.2015
 */
public interface PieceDao {

    /**
     * Creates new piece in DB.
     *
     * @param piece piece to be added to DB
     * @throws IllegalArgumentException when piece is null
     */
    public void create(Piece piece);

    /**
     * Updates already existing piece in DB.
     *
     * @param piece piece to be updated in DB
     * @throws IllegalArgumentException when piece is null
     */
    public void update(Piece piece);

    /**
     * Deletes piece from DB.
     *
     * @param piece piece to be deleted from DB
     * @throws IllegalArgumentException when piece is null
     */
    public void delete(Piece piece);

    /**
     * Returns piece with corresponding id.
     *
     * @param id id of the piece
     * @return piece with corresponding id
     * @throws IllegalArgumentException when id is null or smaller than 0
     */
    public Piece findById(Long id);

    /**
     * Returns piece with corresponding name.
     * 
     * @param name name of the piece
     * @return piece with corresponding name
     * @throws IllegalArgumentException when name is null
     */
    public Piece findByName(String name);

    /**
     * Returns list of all existing pieces in DB.
     *
     * @return list of all existing pieces
     */
    public List<Piece> findAll();

}
