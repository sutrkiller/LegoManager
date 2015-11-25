package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.dao.ModelDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Transactional
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class CategoryServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CategoryDao categoryDao;

    @Autowired
    @InjectMocks
    private CategoryService categoryService;

    List<Category> all;
    Category cars;
    Category planes;
    Category buildings;

    @BeforeClass
    public void setupClass() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setupMethod() throws ServiceException
    {
        cars = new Category();
        cars.setName("Cars");
        cars.setDescription("Everything with engine and four wheels.");

        planes = new Category();
        planes.setName("Planes");
        planes.setDescription("You will feel like a bird");

        buildings = new Category();
        buildings.setName("Buildings");
        buildings.setDescription("Become an architect and construct the biggest building.");

        all = new ArrayList<>();
        all.add(cars);
        all.add(planes);
        all.add(buildings);
    }

    @Test
    public void testCreate() throws Exception {
        categoryService.create(cars);

        verify(categoryDao).create(eq(cars));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullCategory() throws Exception {
        categoryService.create(null);
    }

    @Test
    public void testFindById() throws Exception {

        when(categoryDao.findById(1L)).thenReturn(cars);

        Category actual = categoryService.findById(1L);
        verify(categoryDao).findById(eq(1L));

        assertEquals(actual, cars);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNullId() throws Exception {
        categoryService.findById(null);
    }

    @Test
    public void testFindByName() throws Exception {

        when(categoryDao.findByName(eq("Cars"))).thenReturn(cars);

        Category actual = categoryService.findByName("Cars");
        verify(categoryDao).findByName(eq("Cars"));

        assertEquals(actual, cars);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameNullName() throws Exception {
        categoryService.findById(null);
    }

    @Test
    public void testFindAll() throws Exception {
        when(categoryDao.findAll()).thenReturn(all);

        List<Category> actual = categoryService.findAll();
        verify(categoryDao).findAll();

        assertTrue(equalsUnordered(actual, all));
    }

    @Test
    public void testUpdate() throws Exception {
        categoryService.update(cars);

        verify(categoryDao).update(eq(cars));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullCategory() throws Exception {
        categoryService.update(null);
    }

    @Test
    public void testDelete() throws Exception {
        categoryService.delete(cars);

        verify(categoryDao).delete(eq(cars));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullCategory() throws Exception {
        categoryService.delete(null);
    }

    private <T extends Object> boolean equalsUnordered(Collection<T> c1, Collection<T> c2) {
        Set<T> set1 = new HashSet<>();
        set1.addAll(c1);
        Set<T> set2 = new HashSet<>();
        set2.addAll(c2);
        return set1.equals(set2);
    }
}