package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Service
public class LegoSetServiceImpl implements LegoSetService {

    @Inject
    private LegoSetDao legoSetDao;

    @Override
    public void create(LegoSet legoSet) {
        if (legoSet == null) {
            throw new IllegalArgumentException("Argument legoSet is null");
        }
        legoSetDao.create(legoSet);
    }

    @Override
    public void updateLegoSet(LegoSet legoSet) {
        if (legoSet == null) {
            throw new IllegalArgumentException("Argument legoSet is null");
        }
        System.out.println(legoSet.toString());
        legoSetDao.update(legoSet);
    }

    @Override
    public void deleteLegoSet(LegoSet legoSet) {
        if (legoSet == null) {
            throw new IllegalArgumentException("Argument legoSet is null");
        }
        legoSetDao.delete(legoSet);
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
        List<LegoSet> found = legoSetDao.findByCategory(category);
        return found;
    }

    @Override
    public void addModel(LegoSet legoSet, Model model) {
        if (legoSet == null || model == null) {
            throw new IllegalArgumentException("Argument legoSet or model is null");
        }
        if (legoSet.getModels().contains(model)) {
            throw new LegoServiceException("Lego set " + legoSet.getId() +" already contains model " + model);
        }
        legoSet.addModel(model);
        legoSetDao.update(legoSet);
    }

    @Override
    public void removeModel(LegoSet legoSet, Model model) {
        if (legoSet == null || model == null) {
            throw new IllegalArgumentException("Argument legoSet or model is null");
        }
        if (!legoSet.getModels().contains(model)) {
            throw new LegoServiceException("Lego set " + legoSet.getId() +":"+ legoSet.getId() + " does not contains model " + model);
        }
        legoSet.removeModel(model);
        legoSetDao.update(legoSet);
    }

}
