package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.ModelDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Implementation of the {@link ModelService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Inject
    private ModelDao modelDao;

    @Override
    public void create(Model model) throws LegoServiceException {
        try {
            modelDao.create(model);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Could not create model:" + model.toString());
        }
    }

    @Override
    public void update(Model model) throws LegoServiceException {
        try {
            modelDao.update(model);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Could not update model: " + model.toString());
        }
    }

    @Override
    public void delete(Model model) throws LegoServiceException {
        try {
            modelDao.delete(model);
        } catch (EntityNotExistsException e) {
            throw new LegoServiceException("Could not delete model: " + model.toString());
        }
    }

    @Override
    public Model findById(Long id) throws LegoServiceException {
        try {
            return modelDao.findById(id);
        } catch (EntityNotExistsException e) {
            throw new LegoServiceException("Could not find model with id: " + id);
        }
    }

    @Override
    public Model findByName(String name) throws LegoServiceException {
        try {
            return modelDao.findByName(name);
        } catch (EntityNotExistsException e) {
            throw new LegoServiceException("Could not find model with name: " + name);
        }
    }

    @Override
    public List<Model> findAll() {
        return modelDao.findAll();
    }

    @Override
    public void setFiftyPercentDiscount(Model model) throws LegoServiceException {
        if(model == null || model.getPrice() == null) {
            throw new IllegalArgumentException("Model or its price is null.");
        }
        model.setPrice(model.getPrice().divide(new BigDecimal("2"), RoundingMode.HALF_UP));
        try {
            modelDao.update(model);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Could not change price of model: " + model.toString());
        }
    }

    @Override
    public void addPiece(Model model, Piece piece) throws LegoServiceException {
        if(model == null || model.getPieces() == null) {
            throw new IllegalArgumentException("Model or its pieces is null.");
        }
        List<Piece> pieces = model.getPieces();
        if (pieces.contains(piece)) {
            throw new LegoServiceException("Model: " + model.toString() + " already contains piece:" + piece.toString());
        }
        pieces.add(piece);
        model.setPieces(pieces);
        try {
            modelDao.update(model);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Could not add piece to model: " + model.toString());
        }
    }

    @Override
    public void removePiece(Model model, Piece piece) throws LegoServiceException {
        if(model == null || model.getPieces() == null) {
            throw new IllegalArgumentException("Model or its pieces is null.");
        }
        List<Piece> pieces = model.getPieces();
        if (!pieces.contains(piece)) {
            throw new LegoServiceException("Model: " + model.toString() + " does not contain piece:" + piece.toString());
        }
        pieces.remove(piece);
        model.setPieces(pieces);
        try {
            modelDao.update(model);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Could not remove piece from model: " + model.toString());
        }
    }

    @Override
    public void changeCategory(Model model, Category category) throws LegoServiceException {
        if(model == null) {
            throw new IllegalArgumentException("Model is null.");
        }
        model.setCategory(category);
        try {
            modelDao.update(model);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Could not change category of model: " + model.toString());
        }
    }
}
