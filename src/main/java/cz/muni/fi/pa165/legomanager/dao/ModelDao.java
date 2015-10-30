package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;
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

    public void create(Model model) throws LegoPersistenceException;
    
    public Model findById(Long id) throws EntityNotExistsException;
    
    public Model findByName(String name) throws EntityNotExistsException;

    public List<Model> findAll();
    
    public Model update(Model model) throws LegoPersistenceException;

    public void delete(Model model) throws EntityNotExistsException;

}
