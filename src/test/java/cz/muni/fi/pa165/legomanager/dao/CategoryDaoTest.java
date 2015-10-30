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
import javax.transaction.Transactional;
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
    public void setUp() throws LegoPersistenceException {

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

    @AfterMethod
    public void after() throws LegoPersistenceException {
        /*
        Becaue @TestExecutionListeners do auto-rollback after test 
        and JPA doesn't have to flush changes to DB before it. 
        It can throw some aditional exception which will appear in real usage.
        */
        em.flush();
    }

    @Test
    public void testCreate() throws LegoPersistenceException {
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
    public void testCreateNullCategory() throws LegoPersistenceException {
        categoryDao.create(null);
    }

    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    public void testCreateAlreadyExists() throws LegoPersistenceException {
        categoryDao.create(cars);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNonUniqueName() throws LegoPersistenceException {
        Category cars2 = new Category();
        cars2.setName("Cars");
        cars2.setDescription("Fast, four wheels, race!");
        categoryDao.create(cars2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullName() throws LegoPersistenceException {
        Category jungle = new Category();
        jungle.setName(null);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.create(jungle);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullDescription() throws LegoPersistenceException {
        Category jungle = new Category();
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(null);
        categoryDao.create(jungle);
    }

    @Test
    public void testFindById() throws LegoPersistenceException {
        Long carsId = cars.getId();
        assertNotNull(carsId);
        Category actual = categoryDao.findById(carsId);
        assertEquals(cars, actual);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByIdNotExist() throws LegoPersistenceException {
        Long randomId = 999L;
        assertNotSame(cars.getId(), randomId);
        assertNotSame(planes.getId(), randomId);
        assertNotSame(buildings.getId(), randomId);
        Category actual = categoryDao.findById(randomId);
        assertNull(actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNullId() throws LegoPersistenceException {
        categoryDao.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNegativeId() throws LegoPersistenceException {
        categoryDao.findById(-1L);
    }

    @Test
    public void testFindByName() throws LegoPersistenceException {
        String carsName = cars.getName();
        assertNotNull(carsName);
        Category actual = categoryDao.findByName(carsName);
        assertEquals(cars, actual);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByNameNotExist() throws LegoPersistenceException {
        String carsName = "NoCategory";
        assertNotSame(cars.getName(), carsName);
        assertNotSame(planes.getName(), carsName);
        assertNotSame(buildings.getName(), carsName);
        Category actual = categoryDao.findByName(carsName);
        assertNull(actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameNull() throws LegoPersistenceException {
        categoryDao.findByName(null);
    }

    @Test
    public void testFindAll() throws LegoPersistenceException {
        Set<Category> actual = new HashSet<>(categoryDao.findAll());

        Set<Category> expected = new HashSet<>();
        expected.add(cars);
        expected.add(planes);
        expected.add(buildings);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllEmpty() throws LegoPersistenceException {
        categoryDao.delete(cars);
        categoryDao.delete(planes);
        categoryDao.delete(buildings);

        Set<Category> actual = new HashSet<>(categoryDao.findAll());

        Set<Category> emptySet = new HashSet<>();

        assertEquals(emptySet, actual);
    }

    @Test
    public void testUpdate() throws LegoPersistenceException {
        cars.setDescription("Extra summer sales, for this category!");
        cars.setName("Cars in sale");
        categoryDao.update(cars);
        em.flush();

        Category actual = categoryDao.findById(cars.getId());

        assertEquals(cars, actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullCategory() throws LegoPersistenceException {
        categoryDao.update(null);
        em.flush();
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testUpdateNotExist() throws LegoPersistenceException {
        Category jungle = new Category();
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.update(jungle);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNonUniqueName() throws LegoPersistenceException {
        cars.setDescription("Extra summer sales, for this category!");
        cars.setName("Planes");
        categoryDao.update(cars);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullName() throws LegoPersistenceException {
        cars.setDescription("Extra summer sales, for this category!");
        cars.setName(null);
        categoryDao.update(cars);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullDescription() throws LegoPersistenceException {
        cars.setDescription(null);
        cars.setName("Cars in sale");
        categoryDao.update(cars);
        em.flush();
    }

    @Test
    public void testDelete() throws LegoPersistenceException {
        categoryDao.delete(cars);

        em.flush();

        Set<Category> actual = new HashSet<>(categoryDao.findAll());

        Set<Category> expected = new HashSet<>();
        expected.add(planes);
        expected.add(buildings);

        assertEquals(expected, actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNotExist() throws LegoPersistenceException {
        Category jungle = new Category();
        jungle.setName(JUNGLE_NAME);
        jungle.setDescription(JUNGLE_DSC);
        categoryDao.delete(jungle);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteAlreadyRemoved() throws LegoPersistenceException {
        categoryDao.delete(cars);
        em.flush();
        categoryDao.delete(cars);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() throws LegoPersistenceException {
        categoryDao.delete(null);
    }

}
