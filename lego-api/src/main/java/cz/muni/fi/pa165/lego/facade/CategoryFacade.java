package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import java.util.List;

/**
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */
public interface CategoryFacade {

    Long createCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO getCategoryByName(String name);

    List<CategoryDTO> getAllCategories();
}
