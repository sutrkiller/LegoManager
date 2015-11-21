package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.PieceDao;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link PieceService}.
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
        try {
            pieceDao.create(piece);
        } catch (LegoPersistenceException ex) {
            throw new LegoServiceException("Creating Piece entity " + piece + " failed.", ex);
        }
    }

    @Override
    public void update(Piece piece) {
        try {
            pieceDao.update(piece);
        } catch (LegoPersistenceException ex) {
            throw new LegoServiceException("Updating Piece entity " + piece + " failed.", ex);
        }
    }

    @Override
    public void delete(Piece piece) {
        try {
            pieceDao.delete(piece);
        } catch (LegoPersistenceException ex) {
            throw new LegoServiceException("Deleting Piece entity " + piece + " failed.", ex);
        }
    }

    @Override
    public Piece findById(Long id) {
        try {
            return pieceDao.findById(id);
        } catch (LegoPersistenceException ex) {
            throw new LegoServiceException("Looking for Piece entity with id " + id + " failed.", ex);
        }
    }

    @Override
    public List<Piece> findAll() {
        return pieceDao.findAll();
    }

}
