package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import java.util.List;

import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryFacadeImpl implements {@link CategoryFacade}.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 21.11.2015
 */
@Service
@Transactional
public class CategoryFacadeImpl implements CategoryFacade {

    @Override
    public Long createCategory(CategoryCreateDTO categoryCreateDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long updateCategory(CategoryDTO categoryDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long deleteCategory(CategoryDTO categoryDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
