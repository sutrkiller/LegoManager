package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.lego.service.facade.CategoryFacadeImpl;
import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Transactional
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class CategoryFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BeanMappingService beanMappingService;

    @Autowired
    @InjectMocks
    private CategoryFacade categoryFacade;

    List<CategoryDTO> all;
    CategoryDTO cars;
    CategoryDTO planes;
    CategoryDTO buildings;

    @BeforeMethod
    public void setupMethod() throws ServiceException
    {
        cars = new CategoryDTO();
        cars.setId(1L);
        cars.setName("Cars");
        cars.setDescription("Everything with engine and four wheels.");

        planes = new CategoryDTO();
        planes.setId(2L);
        planes.setName("Planes");
        planes.setDescription("You will feel like a bird");

        buildings = new CategoryDTO();
        buildings.setId(3L);
        buildings.setName("Buildings");
        buildings.setDescription("Become an architect and construct the biggest building.");

        all = new ArrayList<>();
        all.add(cars);
        all.add(planes);
        all.add(buildings);
    }

    @Test
    public void testCreateCategory() throws Exception {
        cars.setId(null);

        //Long id = categoryFacade.createCategory(cars);
        //CategoryDTO category = categoryFacade.getCategoryById(id);
        //assertEquals(id, category.getId());
    }

    @Test
    public void testUpdateCategory() throws Exception {

    }

    @Test
    public void testDeleteCategory() throws Exception {

    }

    @Test
    public void testGetCategoryById() throws Exception {

    }

    @Test
    public void testGetCategoryByName() throws Exception {

    }

    @Test
    public void testGetAllCategories() throws Exception {

    }

    @Test
    public void testChangeName() throws Exception {

    }

    @Test
    public void testChangeDescription() throws Exception {

    }
}