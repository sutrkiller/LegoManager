package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Service
public interface LegoSetService {

    void createLegoSet(LegoSet legoSet);

    LegoSet findById(Long id);

    LegoSet findByName(String name);

    List<LegoSet> findAll();

    List<LegoSet> findByCategory(Category category);

    void updateLegoSet(LegoSet legoSet);

    @Deprecated
    void updateName(LegoSet legoSet, String newName);

    @Deprecated
    void updatePrice(LegoSet legoSet, BigDecimal newPrice);

    @Deprecated
    void updateCategory(LegoSet legoSet, Category newCategory);

    void addModel(LegoSet legoSet, Model model);

    void removeModel(LegoSet legoSet, Model model);

    void deleteLegoSet(LegoSet legoSet);

}
