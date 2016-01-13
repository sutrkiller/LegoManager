package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.Utils;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.legomanager.entities.Category;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
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

    List<CategoryDTO> allDTO;
    CategoryDTO carsDTO;
    CategoryDTO planesDTO;
    CategoryDTO buildingsDTO;
    CategoryDTO carsDTOReturned;

    List<Category> all;
    Category cars;
    Category planes;
    Category buildings;
    Category carsReturned;

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        carsDTO = new CategoryDTO();
        carsDTO.setId(1L);
        carsDTO.setName("Cars");
        carsDTO.setDescription("Everything with engine and four wheels.");

        cars = new Category();
        cars.setName("Cars");
        cars.setDescription("Everything with engine and four wheels.");

        planesDTO = new CategoryDTO();
        planesDTO.setId(2L);
        planesDTO.setName("Planes");
        planesDTO.setDescription("You will feel like a bird");

        planes = new Category();
        planes.setName("Planes");
        planes.setDescription("You will feel like a bird");

        buildingsDTO = new CategoryDTO();
        buildingsDTO.setId(3L);
        buildingsDTO.setName("Buildings");
        buildingsDTO.setDescription("Become an architect and construct the biggest building.");

        buildings = new Category();
        buildings.setName("Buildings");
        buildings.setDescription("Become an architect and construct the biggest building.");

        allDTO = new ArrayList<>();
        allDTO.add(carsDTO);
        allDTO.add(planesDTO);
        allDTO.add(buildingsDTO);

        all = new ArrayList<>();
        all.add(cars);
        all.add(planes);
        all.add(buildings);

        // setup mocks
        when(beanMappingService.mapTo(any(Category.class), eq(CategoryDTO.class))).thenReturn(carsDTO);
        when(beanMappingService.mapTo(any(CategoryDTO.class), eq(Category.class))).thenReturn(cars);
        when(beanMappingService.mapTo(any(Collection.class), eq(CategoryDTO.class))).thenReturn(allDTO);
        when(beanMappingService.mapTo(any(Collection.class), eq(Category.class))).thenReturn(all);
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws NoSuchFieldException, IllegalAccessException {
                Category cars = (Category)invocation.getArguments()[0];
                Field f = cars.getClass().getDeclaredField("id");
                f.setAccessible(true);
                f.set(cars, 1L);
                return null;
            }
        }).when(categoryService).create(eq(cars));
        when(categoryService.findById(eq(1L))).thenReturn(cars);
        when(categoryService.findByName(eq("Cars"))).thenReturn(cars);
        when(categoryService.findAll()).thenReturn(all);
    }


    @Test
    public void testCreateCategory() throws Exception {
        carsDTO.setId(null);

        Long id = categoryFacade.create(carsDTO);

        verify(beanMappingService).mapTo(eq(carsDTO), eq(Category.class));
        verify(categoryService).create(cars);
        assertEquals(id, (Long)1L);
    }

    @Test
    public void testUpdateCategory() throws Exception {

        carsDTO.setName("newName");

        categoryFacade.update(carsDTO, carsDTO.getId());

        
        verify(categoryService).update(any(Category.class));
    }

    @Test
    public void testDeleteCategory() throws Exception {

        categoryFacade.delete(1L);

        verify(categoryService).findById(eq(1L));
        verify(categoryService).delete(cars);
    }

    @Test
    public void testGetCategoryById() throws Exception {

        CategoryDTO categoryDTO = categoryFacade.findById(1L);

        verify(categoryService).findById(eq(1L));
        verify(beanMappingService).mapTo(cars, CategoryDTO.class);

        assertEquals(categoryDTO, carsDTO);
    }

    @Test
    public void testGetCategoryByName() throws Exception {

        CategoryDTO categoryDTO = categoryFacade.findByName("Cars");

        verify(categoryService).findByName("Cars");
        verify(beanMappingService).mapTo(cars, CategoryDTO.class);

        assertEquals(categoryDTO, carsDTO);
    }

    @Test
    public void testGetAllCategories() throws Exception {

        List<CategoryDTO> categories = categoryFacade.findAll();

        verify(categoryService).findAll();
        verify(beanMappingService).mapTo(all, CategoryDTO.class);

        assertTrue(Utils.equalsUnordered(categories, allDTO));
    }


}