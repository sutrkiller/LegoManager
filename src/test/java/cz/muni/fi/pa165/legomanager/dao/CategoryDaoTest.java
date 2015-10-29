package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.AfterMethod;

/**
 * Test class for Category dao manager.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@ContextConfiguration(classes = {PersistenceApplicationContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CategoryDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public CategoryDao categoryDao;

    @PersistenceContext
    public EntityManager em;

    private Category cars;
    private Category planes;
    private Category buildings;

    private final String JUNGLE_NAME = "Jungle";
    private final String JUNGLE_DSC = "Wild animals, secrets, dangerous. It's the Jungle.";

    @BeforeMethod
    public void setUp() {

        cars = new Category();
        cars.setName("Cars");
        cars.setDescription("Everything with engine and four wheels.");

        planes = new Category();
        planes.setName("Planes");
        planes.setDescription("Machines which can be free like birds.");

        buildings = new Category();
        buildings.setName("Buildings");
        buildings.setDescription("Category for all small builders.");

        categoryDao.create(cars);
        categoryDao.create(planes);
        categoryDao.create(buildings);
        em.flush();

    }

    @Test
    public void testCreate() {
        Category jungle = new Category();
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.create(jungle);
        
        em.flush();

        Long jungleId = jungle.getId();
        assertNotNull(jungleId);

        Category actual = categoryDao.findById(jungleId);
        assertEquals(jungle, actual);

        actual = categoryDao.findByName(JUNGLE_NAME);
        assertEquals(jungle, actual);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullCategory() {
        categoryDao.create(null);
    }

    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    public void testCreateAlreadyExists() {
        categoryDao.create(cars);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNonUniqueName() {
        Category cars2 = new Category();
        cars2.setName("Cars");
        cars2.setDescription("Fast, four wheels, race!");
        categoryDao.create(cars2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullName() {
        Category jungle = new Category();
        jungle.setName(null);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.create(jungle);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullDescription() {
        Category jungle = new Category();
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(null);
        categoryDao.create(jungle);
    }

    @Test
    public void testFindById() {
        Long carsId = cars.getId();
        assertNotNull(carsId);
        Category actual = categoryDao.findById(carsId);
        assertEquals(cars, actual);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByIdNotExist() {
        Long randomId = 999L;
        assertNotSame(cars.getId(), randomId);
        assertNotSame(planes.getId(), randomId);
        assertNotSame(buildings.getId(), randomId);
        Category actual = categoryDao.findById(randomId);
        assertNull(actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNullId() {
        categoryDao.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNegativeId() {
        categoryDao.findById(-1L);
    }

    @Test
    public void testFindByName() {
        String carsName = cars.getName();
        assertNotNull(carsName);
        Category actual = categoryDao.findByName(carsName);
        assertEquals(cars, actual);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByNameNotExist() {
        String carsName = "NoCategory";
        assertNotSame(cars.getName(), carsName);
        assertNotSame(planes.getName(), carsName);
        assertNotSame(buildings.getName(), carsName);
        Category actual = categoryDao.findByName(carsName);
        assertNull(actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameNull() {
        categoryDao.findByName(null);
    }

    @Test
    public void testFindAll() {
        Set<Category> actual = new HashSet<>(categoryDao.findAll());

        Set<Category> expected = new HashSet<>();
        expected.add(cars);
        expected.add(planes);
        expected.add(buildings);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllEmpty() {
        categoryDao.delete(cars);
        categoryDao.delete(planes);
        categoryDao.delete(buildings);

        Set<Category> actual = new HashSet<>(categoryDao.findAll());

        Set<Category> emptySet = new HashSet<>();

        assertEquals(emptySet, actual);
    }

    
    @Test
    public void testUpdate() {
        fail();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullCategory() {
        fail();
    }

    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    public void testUpdateAlreadyExists() {
        fail();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNonUniqueName() {
        fail();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullName() {
        fail();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullDescription() {
        fail();
    }
    
    @Test
    public void testDelete() {
        categoryDao.delete(cars);

        em.flush();
        
        Set<Category> actual = new HashSet<>(categoryDao.findAll());

        Set<Category> expected = new HashSet<>();
        expected.add(planes);
        expected.add(buildings);

        assertEquals(expected, actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNotExist() {
        Category jungle = new Category();
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.delete(jungle);
        em.flush();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteAlreadyRemoved() {
        categoryDao.delete(cars);
        em.flush();
        categoryDao.delete(cars);
        em.flush();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        categoryDao.delete(null);
    }

}
