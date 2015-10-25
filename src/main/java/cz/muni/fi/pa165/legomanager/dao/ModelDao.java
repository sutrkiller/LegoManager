package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import java.util.List;

/**
 * Managing dao operations for Model.
 * 
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public interface ModelDao {

    public void create(Model model);
    
    public Model findById(Long id);
    
    public Model findByName(String name);

    public List<Model> findAll();
    
    public Model update(Model model);

    public void delete(Model model);

}
