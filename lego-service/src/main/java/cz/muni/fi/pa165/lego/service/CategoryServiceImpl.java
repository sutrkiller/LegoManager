package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Implementation of the {@link CategoryService}/ This class is part of the
 * service module of the application that provides the implementation of 
 *  the business logic (main logic of the application).
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Inject
    private CategoryDao categoryDao;
    
    @Override
    public void create(Category c){
        try{
        categoryDao.create(c);
        } catch(LegoPersistenceException e) {
            throw new LegoServiceException("Persistence of "+c+" failed",e);
        }
    }

    @Override
    public void update(Category c){
        try {
            categoryDao.update(c);
        } catch (LegoPersistenceException ex) {
            throw new LegoServiceException("Could not update category: "+c,ex);
        }
    }

    @Override
    public void delete(Category c){
        try {
            categoryDao.delete(c);
        } catch (EntityNotExistsException ex) {
            throw new LegoServiceException("Could not delete category: "+c,ex);
        }
    }

    @Override
    public Category findById(Long id) {
        try {
            return categoryDao.findById(id);
        } catch (EntityNotExistsException ex) {
            throw new LegoServiceException("Could not find category with id: "+id, ex);
        }
    }

    @Override
    public Category findByName(String name) {
        try {
            return categoryDao.findByName(name);
        } catch (EntityNotExistsException ex) {
            throw new LegoServiceException("Could not find category with name: "+name,ex);
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
    
}
