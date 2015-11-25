package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Service
public class LegoSetServiceImpl implements LegoSetService {

    @Inject
    private LegoSetDao legoSetDao;

    @Override
    public void createLegoSet(LegoSet legoSet) {
        if (legoSet == null) {
            throw new IllegalArgumentException("Argument legoSet is null");
        }
        legoSetDao.create(legoSet);
    }

    @Override
    public LegoSet findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument id is null");
        }
        return legoSetDao.findById(id);
    }

    @Override
    public LegoSet findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Argument name is null");
        }
        return legoSetDao.findByName(name);
    }

    @Override
    public List<LegoSet> findAll() {
        return legoSetDao.findAll();
    }

    @Override
    public List<LegoSet> findByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Argument category is null");
        }
        List<LegoSet> legoSets = new ArrayList<>();
        for (LegoSet legoSet : legoSetDao.findAll()) {
            if (legoSet.getCategory().equals(category)) {
                legoSets.add(legoSet);
            }
        }
        return legoSets;
    }

    @Override
    public void updateLegoSet(LegoSet legoSet) {
        if (legoSet == null) {
            throw new IllegalArgumentException("Argument legoSet is null");
        }
        legoSetDao.update(legoSet);
    }

    @Override
    @Deprecated
    public void updateName(LegoSet legoSet, String newName) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void updatePrice(LegoSet legoSet, BigDecimal newPrice) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void updateCategory(LegoSet legoSet, Category newCategory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addModel(LegoSet legoSet, Model model) {
        if (legoSet == null || model == null) {
            throw new IllegalArgumentException("Argument legoSet or model is null");
        }
        legoSet.addModel(model);
        legoSetDao.update(legoSet);
    }

    @Override
    public void removeModel(LegoSet legoSet, Model model) {
        if (legoSet == null || model == null) {
            throw new IllegalArgumentException("Argument legoSet or model is null");
        }
        legoSet.removeModel(model);
        legoSetDao.update(legoSet);
    }

    @Override
    public void deleteLegoSet(LegoSet legoSet) {
        if (legoSet == null) {
            throw new IllegalArgumentException("Argument legoSet is null");
        }
        legoSetDao.delete(legoSet);
    }
}
