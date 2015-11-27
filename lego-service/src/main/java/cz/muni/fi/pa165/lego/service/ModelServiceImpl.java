package cz.muni.fi.pa165.lego.service;

import com.sun.org.apache.xpath.internal.operations.Mod;
import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.ModelDao;
import cz.muni.fi.pa165.legomanager.dao.PieceDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    @Inject
    private PieceService pieceService;

    @Override
    public void create(Model model) {
        if(model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        modelDao.create(model);
    }

    @Override
    public void update(Model model) {
        if(model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        modelDao.update(model);
    }

    @Override
    public void delete(Model model) {
        if(model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        modelDao.delete(model);
    }

    @Override
    public Model findById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        return modelDao.findById(id);
    }

    @Override
    public Model findByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name can not be null");
        }
        return modelDao.findByName(name);
    }

    @Override
    public List<Model> findByCategory(Category category) {
        if(category == null) {
            throw new IllegalArgumentException("Category can not be null");
        }
        List<Model> found = new ArrayList<>();
        for (Model m : findAll()) {
            if (m.getCategory().equals(category)) {
                found.add(m);
            }
        }
        return found;
    }


    @Override
    public List<Model> findAll() {
        return modelDao.findAll();
    }

    @Override
    public void setFiftyPercentDiscount(Model model) {
        if (model == null || model.getPrice() == null) {
            throw new IllegalArgumentException("Model or its price is null.");
        }
        model.setPrice(model.getPrice().divide(new BigDecimal("2"), RoundingMode.HALF_UP));
        modelDao.update(model);
    }

    @Override
    public void addPiece(Model model, Piece piece) {
        if (model == null || model.getPieces() == null || piece == null) {
            throw new IllegalArgumentException("Model or its pieces is null.");
        }
        List<Piece> pieces = model.getPieces();
        if (pieces.contains(piece)) {
            throw new LegoServiceException("Model: " + model.toString() + " already contains piece:" + piece.toString());
        }
        pieceService.create(piece);
        pieces.add(piece);
        model.setPieces(pieces);
        modelDao.update(model);
    }

    @Override
    public void removePiece(Model model, Piece piece) {
        if (model == null || model.getPieces() == null || piece == null) {
            throw new IllegalArgumentException("Model or its pieces is null.");
        }
        List<Piece> pieces = model.getPieces();
        if (!pieces.contains(piece)) {
            throw new LegoServiceException("Model: " + model.toString() + " does not contain piece:" + piece.toString());
        }
        pieces.remove(piece);
        model.setPieces(pieces);
        pieceService.delete(piece);
        modelDao.update(model);
    }

    @Override
    public void changeCategory(Model model, Category category) {
        if (model == null || category == null) {
            throw new IllegalArgumentException("Model or category is null.");
        }
        model.setCategory(category);
        modelDao.update(model);
    }
}
