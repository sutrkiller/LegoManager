package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */
@Service
public class CategoryFacadeImpl implements CategoryFacade {

    @Override
    public Long createCategory(CategoryCreateDTO categoryCreateDTO) {
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
