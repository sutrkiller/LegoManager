package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
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
 * Testing class for SetDaoImpl.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 24.10.2015
 */
@ContextConfiguration(classes = {PersistenceApplicationContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LegoSetDaoImplTest extends AbstractTestNGSpringContextTests {

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
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullSet() {
        legoSetDao.create(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateSetWithNullName() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName(null);
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(new BigDecimal("800.00"));

        legoSetDao.create(setBuildings2);
    }

    @Test
    public void testUpdateSet() {
        setBuildings.setName("Cars set");

        legoSetDao.update(setBuildings);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testUpdateSetWithNullName() {
        setBuildings.setName(null);

        legoSetDao.update(setBuildings);
    }

    @Test
    public void testDeleteSet() {
        legoSetDao.delete(setBuildings);
        List<LegoSet> existingSets = legoSetDao.findAll();
        Assert.assertEquals(existingSets.size(), 1);

        LegoSet foundSet = legoSetDao.findById(setBuildings.getId());
        Assert.assertNull(foundSet);
    }

    @Test
    public void testFindSetById() {
        LegoSet foundSet = legoSetDao.findById(setHarryPotter.getId());
        Assert.assertEquals(foundSet, setHarryPotter);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindSetByWrongId() {
        legoSetDao.findById(-10L);
    }

    @Test
    public void testFindSetByIdWithNonExistingId() {
        LegoSet foundSet = legoSetDao.findById(100L);
        Assert.assertNull(foundSet);
    }

    @Test
    public void testFindSetByName() {
        // LegoSet foundSet = legoSetDao.findByName(setHarryPotter.getName());
        // Assert.assertEquals(foundSet, setHarryPotter);
        fail("Test is not implemented yet.");
    }

    @Test
    public void testFindSetByNameWithNonExistingName() {
        // LegoSet foundSet = legoSetDao.findByName("Random nonexisting name);
        // Assert.assertNull(foundSet);
        fail("Test is not implemented yet.");
    }

    @Test
    public void testFindAllSets() {
        List<LegoSet> foundSet = legoSetDao.findAll();
        Assert.assertEquals(foundSet.size(), 2);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testNameIsUnique() {
        LegoSet setBuildings2 = new LegoSet();
        setBuildings2.setName("Buildings set");
        setBuildings2.setModels(new ArrayList<Model>());
        setBuildings2.setCategory(categoryBuildings);
        setBuildings2.setPrice(new BigDecimal("800.00"));

        legoSetDao.create(setBuildings2);
    }
}
