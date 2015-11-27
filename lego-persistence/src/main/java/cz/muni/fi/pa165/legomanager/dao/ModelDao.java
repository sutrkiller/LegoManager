package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;

/**
 * Managing dao operations for Model.
 * 
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public interface ModelDao {

    /**
     * store new Model. set auto-generated id to given model.
     * 
     * @param model to be stored
     * @throws IllegalArgumentException when model is null
     * @throws LegoPersistenceException if model is already stored or 
     * violate constraint (e.g. unique name).
     */
    public void create(Model model);

    /**
     * update data of given model
     *
     * @param model model with updated data but with same id.
     * @return updated model. Should be same as given model.
     * @throws IllegalArgumentException when model is null
     * @throws LegoPersistenceException if model is not found in storage
     * or if violate some constraint.
     */
    public Model update(Model model);

    /**
     * remove given model from storage.
     *
     * @param model model to be deleted
     * @throws IllegalArgumentException when model is null
     * @throws EntityNotExistsException if entity is already removed or
     * is not in storage.
     */
    public void delete(Model model);
    
    /**
     * find model by its id. Model with ID has to exists.
     * 
     * @param id id of model
     * @return model with defined id
     * @throws IllegalArgumentException when id is null
     * @throws EntityNotExistsException if model with given id was not found 
     * in storage
     */
    public Model findById(Long id);
    
    /**
     * find model by its name. Model with name has to exists.
     * 
     * @param name name of model
     * @return model with given name
     * @throws IllegalArgumentException when name is null
     * @throws EntityNotExistsException if model with given name was not found 
     * in storage
     */
    public Model findByName(String name);

    /**
     * find all stored models
     * 
     * @return all stored models or empty list if no models is found.
     */
    public List<Model> findAll();

}
