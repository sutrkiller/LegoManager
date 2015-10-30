/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ValidationException;
import org.springframework.stereotype.Repository;

/**
 * PieceDaoImpl implements {@link PieceDao}.
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 29.10.2015
 */
@Repository
public class PieceDaoImpl implements PieceDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Piece piece) throws EntityAlreadyExistsException, LegoPersistenceException {
        if (piece == null) {
            throw new IllegalArgumentException("Piece argument is null.");
        }
        if (piece.getCurrentColor() != null && piece.getType() != null && piece.getType().getColors() != null && !piece.getType().getColors().contains(piece.getCurrentColor())) {
            throw new LegoPersistenceException("Create piece persistence error.");
        }
        if (em.contains(piece)) {
            throw new EntityAlreadyExistsException("piece already in DB.");
        }
        try {
            em.persist(piece);
        } catch (ValidationException | PersistenceException e) {
            throw new LegoPersistenceException("Create piece persistence error", e);
        }
    }

    @Override
    public void update(Piece piece) throws EntityNotExistsException, LegoPersistenceException {
        if (piece == null) {
            throw new IllegalArgumentException("Argument piece is null.");
        }
        if (piece.getCurrentColor() != null && piece.getType() != null && piece.getType().getColors() != null && !piece.getType().getColors().contains(piece.getCurrentColor())) {
            throw new LegoPersistenceException("Create piece persistence error.");
        }
        if (!em.contains(piece)) {
            throw new EntityNotExistsException("Entity not in database.");
        }
        try {
            em.merge(piece);
            em.flush();
        } catch (ValidationException | PersistenceException e) {
            throw new LegoPersistenceException("Update piece persistence error", e);
        }
    }

    @Override
    public void delete(Piece piece) throws EntityNotExistsException {
        if (piece == null) {
            throw new IllegalArgumentException("Argument piece is null.");
        }
        if (!em.contains(piece)) {
            throw new EntityNotExistsException("Entity not in DB.");
        }
        em.remove(piece);
    }

    @Override
    public Piece findById(Long id) throws EntityNotExistsException {
        if (id == null) {
            throw new IllegalArgumentException("Id is null.");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id < 0");
        }
        try {
            Piece p = em.find(Piece.class, id);
            if (p == null) {
                throw new EntityNotExistsException("Id not found");
            }
            return p;
        } catch (NoResultException e) {
            throw new EntityNotExistsException("Entity with id not found", e);
        }
    }

    @Override
    public Piece findByName(String name) throws EntityNotExistsException {
        if (name == null) {
            throw new IllegalArgumentException("Name is null or empty");
        }
        try {
            return em.createQuery("SELECT p FROM Piece p WHERE name = :name", Piece.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotExistsException("No result found", e);
        }
    }

    @Override
    public List<Piece> findAll() {
        return em.createQuery("SELECT p FROM Piece p", Piece.class).getResultList();
    }

}
