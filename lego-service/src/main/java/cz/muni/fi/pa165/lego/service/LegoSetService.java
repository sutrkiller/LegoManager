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

    void updateName(String name);

    void updatePrice(BigDecimal price);

    void updateCategory(Category category);

    void addModel(Model model);

    void removeModel(Model model);

    void deleteLegoSet(LegoSet ls);

}
