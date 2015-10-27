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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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

    }

    @Test
    public void testCreate() {
        Category jungle = new Category();
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.create(jungle);

        Long jungleId = jungle.getId();
        assertNotNull(jungleId);

        Category actual = categoryDao.findById(jungleId);
        assertEquals(jungle, actual);

        actual = categoryDao.findByName(JUNGLE_NAME);
        assertEquals(jungle, actual);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAlreadyExists() {
        em.flush();
        categoryDao.create(cars);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testCreateWithId() {
        Category jungle = new Category();
        jungle.setId(3L);
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.create(jungle);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testCreateNonUniqueName() {
        Category cars2 = new Category();
        cars2.setName("Cars");
        cars2.setDescription("Fast, four wheels, race!");
        categoryDao.create(cars2);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateNullName() {
        Category jungle = new Category();
        jungle.setName(null);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.create(jungle);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
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

    @Test
    public void testFindByIdNotExist() {
        Long randomId = 999L;
        assertNotSame(cars.getId(), randomId);
        assertNotSame(planes.getId(), randomId);
        assertNotSame(buildings.getId(), randomId);
        Category actual = categoryDao.findById(randomId);
        assertNull(actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNull() {
        categoryDao.findById(null);
    }

    @Test
    public void testFindByName() {
        String carsName = cars.getName();
        assertNotNull(carsName);
        Category actual = categoryDao.findByName(carsName);
        assertEquals(cars, actual);
    }

    @Test
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
    public void testDelete() {
        categoryDao.delete(cars);

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
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteAlreadyRemoved() {
        categoryDao.delete(cars);
        em.flush();
        categoryDao.delete(cars);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        categoryDao.delete(null);
    }

}
