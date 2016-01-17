package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.dao.PieceDao;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the {@link PieceService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 21.11.2015
 */
@Service
public class PieceServiceImpl implements PieceService {

    @Inject
    private PieceDao pieceDao;

    @Override
    public void create(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece cannot be null.");
        }
        pieceDao.create(piece);
    }

    @Override
    public void update(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece cannot be null.");
        }
        pieceDao.update(piece);
    }

    @Override
    public void delete(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece cannot be null.");
        }
        pieceDao.delete(piece);
    }

    @Override
    public Piece findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Piece id cannot be null.");
        }
        return pieceDao.findById(id);
    }

    @Override
    public List<Piece> findAll() {
        return pieceDao.findAll();
    }

}
