/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;

/**
 *
 * @author Tobias
 */
public interface CategoryService {
    public void create(Category c) throws EntityAlreadyExistsException, LegoPersistenceException;
    public void update(Category c) throws EntityNotExistsException, LegoPersistenceException;
    public void delete(Category c) throws EntityNotExistsException;
    public Category findById(Long id) throws EntityNotExistsException;
    public Category findByName(String name) throws EntityNotExistsException;
    public List<Category> findAll();
}
