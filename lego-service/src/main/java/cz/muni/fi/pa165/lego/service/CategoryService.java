package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Category;
import java.util.List;

/**
 * An interface that defines a service access to the {@link Category} entity
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
public interface CategoryService {

    /**
     * Creates the given category.
     * 
     * @param category category to create
     * @throws IllegalArgumentException if the parameter is null
     */
    public void create(Category category);

    /**
     * Updates the given category. The id must not be changed.
     * 
     * @param category category to update
     * @throws IllegalArgumentException if the parameter is null
     */
    public void update(Category category);

    /**
     * Deletes the given category.
     * 
     * @param category category to delete
     * @throws IllegalArgumentException if the parameter is null
     */
    public void delete(Category category);

    /**
     * Retrieves the category by id.
     * 
     * @param id id of the category to find
     * @return the category with the given id
     * @throws IllegalArgumentException if the parameter is null
     */
    public Category findById(Long id);

    /**
     * Retrieves the category by name.
     * 
     * @param name name of the category to find
     * @return the category with the given name
     * @throws IllegalArgumentException if the parameter is null.
     */
    public Category findByName(String name);

    /**
     * Retrieves all categories.
     * 
     * @return all categories from persistence layer.
     */
    public List<Category> findAll();
}
