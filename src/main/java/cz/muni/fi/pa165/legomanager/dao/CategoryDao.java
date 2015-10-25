package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
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
     */
    public void create(Category c);
    
    
    /**
     * Returns Category with corresponding id.
     * 
     * @param id id of the Category
     * @return Category with corresponding id
     */
    public Category findById(Long id);
    
    /**
     * Returns Category with corresponding name.
     * 
     * @param name name of the Category
     * @return Category with corresponding name
     */
    public Category findByName(String name);
    
    /**
     * Returns all Categories in DB
     * @return Categories stored in DB
     */
    public List<Category> findAll();
    
    /**
     * Removes Category from DB.
     * 
     * @param c Category to remove
     */
    public void delete(Category c);

   

    
}
