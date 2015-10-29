/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.util.List;

/**
 *
 * @author Tobias
 */
public interface PieceDao{
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
