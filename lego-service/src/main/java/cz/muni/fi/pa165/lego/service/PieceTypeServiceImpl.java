package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.PieceTypeDao;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;

import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link PieceTypeService}.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 21.11.2015
 */
@Service
public class PieceTypeServiceImpl implements PieceTypeService {

    @Inject
    private PieceTypeDao pieceTypeDao;

    @Override
    public void create(PieceType pieceType) {
        if (pieceType == null) {
            throw new IllegalArgumentException("PieceType cannot be null.");
        }
        pieceTypeDao.create(pieceType);
    }

    @Override
    public void update(PieceType pieceType) {
        if (pieceType == null) {
            throw new IllegalArgumentException("PieceType cannot be null.");
        }
        pieceTypeDao.update(pieceType);
    }

    @Override
    public void delete(PieceType pieceType) {
        if (pieceType == null) {
            throw new IllegalArgumentException("PieceType cannot be null.");
        }
        pieceTypeDao.delete(pieceType);
    }

    @Override
    public PieceType findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("PieceType id cannot be null.");
        }
        return pieceTypeDao.findById(id);
    }

    @Override
    public PieceType findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("PieceType name cannot be null.");
        }
        return pieceTypeDao.findByName(name);
    }

    @Override
    public List<PieceType> findAll() {
        return pieceTypeDao.findAll();
    }

}
