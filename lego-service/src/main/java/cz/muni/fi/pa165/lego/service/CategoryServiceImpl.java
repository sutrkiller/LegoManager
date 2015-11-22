/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Tobias
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Inject
    private CategoryDao categoryDao;
    
    @Override
    public void create(Category c) throws LegoPersistenceException {
        categoryDao.create(c);
    }

    @Override
    public void update(Category c) throws LegoPersistenceException {
        categoryDao.update(c);
    }

    @Override
    public void delete(Category c) throws EntityNotExistsException {
        categoryDao.delete(c);
    }

    @Override
    public Category findById(Long id) throws EntityNotExistsException {
        return categoryDao.findById(id);
    }

    @Override
    public Category findByName(String name) throws EntityNotExistsException {
        return categoryDao.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
    
}
