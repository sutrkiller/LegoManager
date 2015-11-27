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
 * An interface that defines a service access to the {@link Model} entity.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Service
public interface LegoSetService {

    /**
     * Create given legoSet
     *
     * @param legoSet legoSet to be created
     */
    void create(LegoSet legoSet);

    /**
     * Update data of the given legoset.
     *
     * @param legoSet legoset to be updated.
     */
    void updateLegoSet(LegoSet legoSet);

    /**
     * Delete legoset. It has to exists in legoset.
     *
     * @param legoSet legoSet to be deleted.
     */
    void deleteLegoSet(LegoSet legoSet);

    /**
     * find legoSet by its id.
     *
     * @param id id of wanted legoSet. It has to exists.
     * @return LegoSet with the id.
     */
    LegoSet findById(Long id);

    /**
     * find legoSet by its name.
     *
     * @param name name of wanted legoSet. It has to exists.
     * @return LegoSet with the name.
     */
    LegoSet findByName(String name);

    /**
     * find all legoSets.
     *
     * @return List of LegoSets
     */
    List<LegoSet> findAll();

    /**
     * find all legoSets in given category.
     *
     * @return List of LegoSets in given category
     */
    List<LegoSet> findByCategory(Category category);

    /**
     * Add model to legoset. Model has to exists already and shouldn't be in legoset.
     *
     * @param legoSet legoset where you want to add the model
     * @param model   model you want to add to legoset
     */
    void addModel(LegoSet legoSet, Model model);

    /**
     * Add model to legoset. Model has to exists already and be already in legoset.
     *
     * @param legoSet legoset where you want to remove the model
     * @param model   model you want to remove from legoset
     */
    void removeModel(LegoSet legoSet, Model model);

}
