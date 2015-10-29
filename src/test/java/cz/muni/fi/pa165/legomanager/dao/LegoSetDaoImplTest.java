package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing class for LegoSetDaoImpl.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 24.10.2015
 */
@ContextConfiguration(classes = {cz.muni.fi.pa165.legomanager.PersistenceApplicationContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LegoSetDaoImplTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public LegoSetDao legoSetDao;

    @Autowired
    public CategoryDao categoryDao;

    private LegoSet setHarryPotter;
    private LegoSet setBuildings;

    private Category categoryHP;
    private Category categoryBuildings;

    @BeforeMethod
    public void setUp() {
        categoryHP = new Category();
        categoryHP.setDescription("Category containing items from Harry Potter.");
        categoryHP.setName("Harry Potter");
        categoryDao.create(categoryHP);

        categoryBuildings = new Category();
        categoryBuildings.setDescription("Category containing buildings.");
        categoryBuildings.setName("Buildings");
        categoryDao.create(categoryBuildings);

        setHarryPotter = new LegoSet();
        setHarryPotter.setName("Harry Potter set");
        setHarryPotter.setPrice(new BigDecimal("1000.00"));
        setHarryPotter.setCategory(categoryHP);
        setHarryPotter.setModels(new ArrayList<Model>());
        legoSetDao.create(setHarryPotter);

        setBuildings = new LegoSet();
        setBuildings.setName("Buildings set");
        setBuildings.setPrice(new BigDecimal("700.00"));
        setBuildings.setCategory(categoryBuildings);
        setBuildings.setModels(new ArrayList<Model>());
        legoSetDao.create(setBuildings);
        em.flush();
    }

    @Test
    public void testCreate() {
        Category categoryCars = new Category();
        categoryCars.setName("Cars");
        categoryCars.setDescription("Category containing cars.");
        categoryDao.create(categoryCars);

        LegoSet setCars = new LegoSet();
        setCars.setName("Cars set.");
        setCars.setPrice(new BigDecimal("200.00"));
        setCars.setCategory(categoryCars);
        setCars.setModels(new ArrayList<Model>());
        legoSetDao.create(setCars);

        em.flush();

        Long carSetId = setCars.getId();
        assertNotNull(carSetId);

        LegoSet actual = legoSetDao.findById(carSetId);
        assertEquals(setCars, actual);

        // actual = legoSetDao.findByName(setCars.getName());
        // assertEquals(setCars, actual);
    }

    // Testing create() method
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullSet() {
        legoSetDao.create(null);
        em.flush();
    }

    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    public void testCreateAlreadyExists() {
        legoSetDao.create(setBuildings);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNonUniqueName() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName("Buildings set");
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(new BigDecimal("800.00"));

        legoSetDao.create(setBuildings2);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateSetWithNullName() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName(null);
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(new BigDecimal("800.00"));

        legoSetDao.create(setBuildings2);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateSetWithNullModels() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName("Second buildings set");
        setBuildings2.setModels(null);
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(new BigDecimal("800.00"));

        legoSetDao.create(setBuildings2);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateSetWithNullCategory() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName("Second buildings set");
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(null);
        setBuildings2.setPrice(new BigDecimal("800.00"));

        legoSetDao.create(setBuildings2);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateSetWithNullPrice() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName("Second buildings set");
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(null);

        legoSetDao.create(setBuildings2);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateSetWithNegativePrice() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName("Second buildings set");
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(new BigDecimal("800.00").negate());

        legoSetDao.create(setBuildings2);
        em.flush();
    }

    // Testing update() method
    @Test
    public void testUpdateSet() {
        setBuildings.setName("Cars set");

        em.flush();
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullSet() {
        setBuildings = null;

        em.flush();
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    public void testUpdateSetAlreadyExists() {
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNonUniqueName() {
        setBuildings.setName("Harry Potter set");

        em.flush();
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateSetWithNullName() {
        setBuildings.setName(null);

        em.flush();
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateSetWithNullModels() {
        setBuildings.setModels(null);

        em.flush();
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateSetWithNullCategory() {
        setBuildings.setCategory(null);

        em.flush();
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateSetWithNullPrice() {
        setBuildings.setPrice(null);

        em.flush();
        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateSetWithNegativePrice() {
        setBuildings.setPrice(new BigDecimal("70.00").negate());

        em.flush();
        legoSetDao.update(setBuildings);
    }

    // Testing delete() method
    @Test
    public void testDeleteSet() {
        legoSetDao.delete(setBuildings);
        em.flush();

        List<LegoSet> existingSets = legoSetDao.findAll();
        Assert.assertEquals(existingSets.size(), 1);

        LegoSet foundSet = legoSetDao.findById(setBuildings.getId());
        Assert.assertNull(foundSet);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testDeleteNotExist() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName("Second buildings set");
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(new BigDecimal("800.00"));

        legoSetDao.delete(setBuildings2);
        em.flush();
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testDeleteAlreadyRemoved() {
        legoSetDao.delete(setBuildings);
        em.flush();

        legoSetDao.delete(setBuildings);
        em.flush();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullSet() {
        legoSetDao.delete(null);
        em.flush();
    }

    // Testing findById() method
    @Test
    public void testFindSetById() {
        LegoSet foundSet = legoSetDao.findById(setHarryPotter.getId());
        Assert.assertEquals(foundSet, setHarryPotter);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindSetByWrongId() {
        legoSetDao.findById(-10L);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindSetByIdWithNonExistingId() {
        legoSetDao.findById(100L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindSetByIdNull() {
        legoSetDao.findById(null);
    }

    // Testing findByName() method
    @Test
    public void testFindSetByName() {
        // LegoSet foundSet = legoSetDao.findByName(setHarryPotter.getName());
        // Assert.assertEquals(foundSet, setHarryPotter);

        fail("Test is not implemented yet.");
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindSetByNameWithNonExistingName() {
        // LegoSet foundSet = legoSetDao.findByName("Random nonexisting name");
        // Assert.assertNull(foundSet);

        fail("Test is not implemented yet.");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameNull() {
        // LegoSet foundSet = legoSetDao.findByName(null);
        // Assert.assertNull(foundSet);

        fail("Test is not implemented yet.");
    }

    // Testing findAll() method
    @Test
    public void testFindAllSets() {
        List<LegoSet> foundSet = legoSetDao.findAll();
        Assert.assertEquals(foundSet.size(), 2);
    }
}
