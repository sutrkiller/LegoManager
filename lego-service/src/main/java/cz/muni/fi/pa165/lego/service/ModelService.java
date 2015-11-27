package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An interface that defines a service access to the {@link Model} entity.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
@Service
public interface ModelService {

    /**
     * Create given model
     *
     * @param model model to be created
     */
    void create(Model model);

    /**
     * Updates the given model. The id must not be changed.
     *
     * @param model model to update
     */
    void update(Model model);

    /**
     * Deletes the given category.
     *
     * @param model model to delete
     */
    void delete(Model model);

    /**
     * Find model by its id.
     *
     * @param id id of wanted model. It has to exists.
     * @return LegoSet with the model.
     */
    Model findById(Long id);

    /**
     * Find model by its name.
     *
     * @param name name of wanted model. It has to exists.
     * @return model with the name.
     */
    Model findByName(String name);

    /**
     * Find models by category.
     *
     * @param category category of wanted models. It has to exists.
     * @return all models in the category.
     */
    List<Model> findByCategory(Category category);

    /**
     * Find all models.
     *
     * @return list of all models
     */
    List<Model> findAll();

    /**
     * Set price of model to half.
     *
     * @param model model which is set to have discount
     */
    void setFiftyPercentDiscount(Model model);

    /**
     * Add piece to model.
     *
     * @param model model to add piece in
     * @param piece piece to be added in model
     */
    void addPiece(Model model, Piece piece);

    /**
     * Remove piece from model.
     *
     * @param model model to remove piece from
     * @param piece piece to be removed from model
     */
    void removePiece(Model model, Piece piece);

    /**
     * Change category of model.
     *
     * @param model    model to change category
     * @param category to be set on model
     */
    void changeCategory(Model model, Category category);

}
