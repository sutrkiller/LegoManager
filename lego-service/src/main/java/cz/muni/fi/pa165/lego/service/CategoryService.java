package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;

/**
 *An interface that defines a service access to the {@link Category} entity
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
public interface CategoryService {
    public void create(Category c);
    public void update(Category c);
    public void delete(Category c);
    public Category findById(Long id);
    public Category findByName(String name);
    public List<Category> findAll();
}
