package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
import java.util.List;

/**
 *
 * @author Tobias
 */
public interface CategoryDao {

    public Category findById(Long id);

    public void create(Category c);

    public void delete(Category c);

    public List<Category> findAll();

    public Category findByName(String name);
}
