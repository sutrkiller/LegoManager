package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.legomanager.entities.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * CategoryFacadeImpl implements {@link CategoryFacade}.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 21.11.2015
 */
@Service
@Transactional
public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    private CategoryService categoryService;

    @Inject
    private BeanMappingService mappingService;

    @Override
    public Long create(CategoryDTO categoryDTO) {
        Category category = mappingService.mapTo(categoryDTO, Category.class);
        categoryService.create(category);
        return category.getId();
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        categoryService.update(mappingService.mapTo(categoryDTO, Category.class));
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(categoryService.findById(id));
    }

    @Override
    public CategoryDTO findById(Long id) {
        return mappingService.mapTo(categoryService.findById(id), CategoryDTO.class);
    }

    @Override
    public CategoryDTO findByName(String name) {
        return mappingService.mapTo(categoryService.findByName(name), CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return mappingService.mapTo(categoryService.findAll(), CategoryDTO.class);
    }
}
