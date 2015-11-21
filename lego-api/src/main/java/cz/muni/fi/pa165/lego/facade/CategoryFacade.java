package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import java.util.List;

/**
 * CategoryFacade defines operations available within the Lego API to simplify the usage of the application.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */
public interface CategoryFacade {

    /**
     * Create the given category.
     *
     * @param categoryCreateDTO category to be created
     */
    Long createCategory(CategoryCreateDTO categoryCreateDTO);

    /**
     * Update the given category.
     *
     * @param categoryDTO category to be updated
     */
    Long updateCategory(CategoryDTO categoryDTO);

    /**
     * Delete the given category.
     *
     * @param categoryDTO category to be deleted
     */
    Long deleteCategory(CategoryDTO categoryDTO);

    /**
     * Get category with the given ID.
     *
     * @param id id of the category
     */
    CategoryDTO getCategoryById(Long id);

    /**
     * Get category with the given name.
     *
     * @param name name of the category
     */
    CategoryDTO getCategoryByName(String name);

    /**
     * Get all existing categories.
     */
    List<CategoryDTO> getAllCategories();
}
