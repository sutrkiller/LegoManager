package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public class LegoSetServiceImpl implements LegoSetService {

    @Override
    public void createLegoSet(LegoSet legoSet) {

    }

    @Override
    public LegoSet findById(Long id) {
        return null;
    }

    @Override
    public LegoSet findByName(String name) {
        return null;
    }

    @Override
    public List<LegoSet> findAll() {
        return null;
    }

    @Override
    public List<LegoSet> findByCategory(Category category) {
        return null;
    }

    @Override
    public void updateName(String name) {

    }

    @Override
    public void updatePrice(BigDecimal price) {

    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void addModel(Model model) {

    }

    @Override
    public void removeModel(Model model) {

    }

    @Override
    public void deleteLegoSet(LegoSet ls) {

    }
}
