package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
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
     * @return id of the created category
     */
    Long createCategory(CategoryCreateDTO categoryCreateDTO);

    /**
     * Update the given category.
     *
     * @param categoryDTO category to be updated
     */
    void updateCategory(CategoryDTO categoryDTO);

    /**
     * Delete the given category.
     *
     * @param id id of the category
     */
    void deleteCategory(Long id);

    /**
     * Get category with the given ID.
     *
     * @param id id of the category
     * @return existing category with given id
     */
    CategoryDTO getCategoryById(Long id);

    /**
     * Get category with the given name.
     *
     * @param name name of the category
     * @return existing category with given name
     */
    CategoryDTO getCategoryByName(String name);

    /**
     * Get all existing categories.
     *
     * @return list of existing categories
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Update name of the Category
     *
     * @param id id of the category
     * @param newName new category name
     */
    void changeName(Long id, String newName);

    /**
     * Update description of the Category
     *
     * @param id id of the category
     * @param newDescription new category description
     */
    void changeDescription(Long id, String newDescription);
}
