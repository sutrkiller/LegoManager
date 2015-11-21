package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;

/**
 * CategoryFacade defines operations available within the Lego API to simplify
 * the usage of the application.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */
public interface CategoryFacade {

    /**
     * Create the given category.
     *
     * @param categoryCreateDTO category to be created
     * @throws LegoPersistenceException entity has wrong attributes
     */
    void createCategory(CategoryCreateDTO categoryCreateDTO) throws LegoPersistenceException;

    /**
     * Update the given category.
     *
     * @param categoryDTO category to be updated
     * @throws LegoPersistenceException entity has wrong attributes
     */
    void updateCategory(CategoryDTO categoryDTO) throws LegoPersistenceException;

    /**
     * Delete the given category.
     *
     * @param id id of the category
     * @throws EntityNotExistsException entity does not exist
     */
    void deleteCategory(Long id) throws EntityNotExistsException;

    /**
     * Get category with the given ID.
     *
     * @param id id of the category
     * @throws EntityNotExistsException entity not found
     * @return existing category with given id
     */
    CategoryDTO getCategoryById(Long id) throws EntityNotExistsException;

    /**
     * Get category with the given name.
     *
     * @param name name of the category
     * @throws EntityNotExistsException entity not found
     * @return existing category with given name
     */
    CategoryDTO getCategoryByName(String name) throws EntityNotExistsException;

    /**
     * Get all existing categories.
     *
     * @return list of existing categories
     */
    List<CategoryDTO> getAllCategories();
}
