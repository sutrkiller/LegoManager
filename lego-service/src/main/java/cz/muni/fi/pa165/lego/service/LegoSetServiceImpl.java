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
        try {
            legoSetDao.create(legoSet);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Persistence of " + legoSet + " Failed", e);
        }
    }

    @Override
    public LegoSet findById(Long id) {
        try {
            return legoSetDao.findById(id);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Load from persistence context failed", e);
        }
    }

    @Override
    public LegoSet findByName(String name) {
        try {
            return legoSetDao.findByName(name);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Load from persistence context failed", e);
        }
    }

    @Override
    public List<LegoSet> findAll() {
        return legoSetDao.findAll();
    }

    @Override
    public List<LegoSet> findByCategory(Category category) {
        List<LegoSet> legoSets = new ArrayList<>();
        for (LegoSet legoSet : legoSetDao.findAll()) {
            if (legoSet.getCategory().equals(category)) {
                legoSets.add(legoSet);
            }
        }
        return legoSets;
    }

    @Override
    public void updateName(LegoSet legoSet, String newName) {
        legoSet.setName(newName);
        try {
            legoSetDao.update(legoSet);
        } catch (EntityAlreadyExistsException e) {
            throw new LegoServiceException("Update name of " + legoSet + " failed. " +
                    "New name " + newName + " is not unique.", e);
        } catch (LegoPersistenceException e) {
            throw new LegoServiceException("Update name of " + legoSet + " failed", e);
        }
    }

    @Override
    public void updatePrice(LegoSet legoSet, BigDecimal newPrice) {
        legoSet.setPrice(newPrice);
    }

    @Override
    public void updateCategory(LegoSet legoSet, Category newCategory) {
        legoSet.setCategory(newCategory);
    }

    @Override
    public void addModel(LegoSet legoSet, Model model) {
        legoSet.addModel(model);
    }

    @Override
    public void removeModel(LegoSet legoSet, Model model) {
        legoSet.removeModel(model);
    }

    @Override
    public void deleteLegoSet(LegoSet legoSet) {
        try {
            legoSetDao.delete(legoSet);
        } catch (EntityNotExistsException e) {
            throw new LegoServiceException("LegoSer " + legoSet + " does not exist. So it couldn't be deleted.", e);
        }
    }
}
