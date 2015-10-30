package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;

/**
 * CategoryDao provides CRUD operations for Category entity.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.10.2015
 */
public interface CategoryDao {

    /**
     * Add new Category in DB.
     * 
     * @param c category to be added to DB.
     * @throws IllegalArgumentException when argument is null.
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException when Category already in DB.
     * @throws cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException when entity constrains are violated.
     */
    public void create(Category c) throws EntityAlreadyExistsException, LegoPersistenceException;
    
    
    /**
     * Returns Category with corresponding id.
     * 
     * @param id id of the Category
     * @return Category with corresponding id
     * @throws IllegalArgumentException when id is null or lower than 0.
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when no entity found.
     */
    public Category findById(Long id) throws EntityNotExistsException;
    
    /**
     * Returns Category with corresponding name.
     * 
     * @param name name of the Category
     * @return Category with corresponding name
     * @throws IllegalArgumentException when name is null.
     * @throws EntityNotExistsException when no entity found.
     */
    public Category findByName(String name) throws EntityNotExistsException;
    
    /**
     * Returns all Categories in DB
     * @return Categories stored in DB
     */
    public List<Category> findAll();
    
    /**
     * Removes Category from DB.
     * 
     * @param c Category to remove
     * @throws IllegalArgumentException when Category is null.
     * @throws EntityNotExistsException when Category does not exist in db.
     */
    public void delete(Category c) throws EntityNotExistsException;
    
    
    /**
     * Updates Category in DB.
     * 
     * @param c category to update
     * @throws EntityNotExistsException when c is not in DB.
     * @throws LegoPersistenceException  when entity constraints are violated.
     */
    public void update(Category c)throws EntityNotExistsException, LegoPersistenceException;
   

    
}
