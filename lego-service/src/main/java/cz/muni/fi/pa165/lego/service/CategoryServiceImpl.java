package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the {@link CategoryService}/ This class is part of the
 * service module of the application that provides the implementation of
 * the business logic (main logic of the application).
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDao categoryDao;

    @Override
    public void create(Category c) {
        if (c == null) {
            throw new IllegalArgumentException("Category is null.");
        }
        categoryDao.create(c);
    }

    @Override
    public void update(Category c) {
        if (c == null) {
            throw new IllegalArgumentException("Category is null.");
        }
        categoryDao.update(c);
    }

    @Override
    public void delete(Category c) {
        if (c == null) {
            throw new IllegalArgumentException("Category is null.");
        }
        categoryDao.delete(c);
    }

    @Override
    public Category findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null.");
        }
        return categoryDao.findById(id);
    }

    @Override
    public Category findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null.");
        }
        return categoryDao.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

}
