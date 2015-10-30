package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing class for ModelDaoImpl
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.10.2015
 */
@ContextConfiguration(classes = {PersistenceApplicationContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ModelDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public ModelDao modelDao;

    @Autowired
    public CategoryDao categoryDao;

    @PersistenceContext
    private EntityManager em;

    private Model m1;
    private Model m2;
    private Model m3;
    private Category c1;

    @BeforeMethod
    public void before() throws LegoPersistenceException {
        c1 = new Category();
        c1.setDescription("New category");
        c1.setName("TestingCategory");
        categoryDao.create(c1);
        
        m1 = new Model();
        m1.setName("Model-1");
        m1.setAgeLimit(Byte.valueOf("18"));
        m1.setPrice(BigDecimal.ZERO);
        m1.setCategory(c1);
        m1.setPieces(new ArrayList<Piece>());
        modelDao.create(m1);

        m2 = new Model();
        m2.setName("Model-2");
        m2.setAgeLimit(Byte.valueOf("25"));
        m2.setPrice(BigDecimal.ONE);
        m2.setCategory(c1);
        m2.setPieces(new ArrayList<Piece>());
        modelDao.create(m2);

        m3 = new Model();
        m3.setName("Model-3");
        m3.setAgeLimit(Byte.valueOf("60"));
        m3.setPrice(new BigDecimal("500.00"));
        m3.setCategory(c1);
        m3.setPieces(new ArrayList<Piece>());
        modelDao.create(m3);

    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateWithNullName() throws Exception {
        Model m = new Model();

        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(BigDecimal.ONE);
        m.setCategory(c1);
        m.setPieces(new ArrayList<Piece>());

        modelDao.create(m);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNonUniqueName() throws Exception {
        em.flush();
        Model m = new Model();
        m.setName("Model-1");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(BigDecimal.ONE);
        m.setCategory(c1);
        m.setPieces(new ArrayList<Piece>());

        modelDao.create(m);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNegativePrice() throws Exception {
        Model m = new Model();
        m.setName("Model-5");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(new BigDecimal("25.0").negate());
        m.setCategory(c1);
        m.setPieces(new ArrayList<Piece>());

        modelDao.create(m);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNegativeAge() throws Exception {
        Model m = new Model();
        m.setName("Model-5");
        m.setAgeLimit(Byte.valueOf("-25"));
        m.setPrice(new BigDecimal("25.0"));
        m.setCategory(c1);
        m.setPieces(new ArrayList<Piece>());

        modelDao.create(m);
        em.flush();
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullPieces() throws Exception {
        Model m = new Model();
        m.setName("Model-5");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(new BigDecimal("25.0"));
        m.setCategory(c1);
        m.setPieces(null);

        modelDao.create(m);
        em.flush();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullModel() throws Exception {
        modelDao.create(null);
        em.flush();
    }
    
    
    
    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    public void testCreateModelAlreadyExists() throws Exception {
        em.flush();
        modelDao.create(m1);
        em.flush();
    }

    @Test
    public void testFindById() throws Exception {
        Model res = modelDao.findById(m1.getId());
        Assert.assertEquals(res, m1);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByNonExistingId() throws Exception {
        Model res = modelDao.findById(Long.MAX_VALUE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNegativeId() throws Exception {
        Model res = modelDao.findById(Long.MIN_VALUE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() throws Exception {
        Model res = modelDao.findById(null);
    }

    @Test
    public void testFindByName() throws Exception {
        Model res = modelDao.findByName(m1.getName());
        Assert.assertEquals(res, m1);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByNonExistingName() throws Exception {
        Model res = modelDao.findByName("Non existing");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullName() throws Exception {
        Model res = modelDao.findByName(null);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Model> list = modelDao.findAll();
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void testUpdate() throws Exception {
        em.flush();
        String newName = "New Model";
        m1.setName(newName);
        modelDao.update(m1);
        em.flush();
        Model res = modelDao.findById(m1.getId());
        Assert.assertEquals(res.getName(), newName);
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullname() throws Exception {
        em.flush();
        m1.setName(null);
        modelDao.update(m1);
        em.flush();
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullPieces() throws Exception {
        em.flush();
        m1.setPieces(null);
        modelDao.update(m1);
        em.flush();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullModel() throws Exception {
        em.flush();
        modelDao.update(null);
        em.flush();
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNonUniqueName() throws Exception {
        em.flush();
        m2.setName(m1.getName());
        modelDao.update(m2);
        em.flush();
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNegativePrice() throws Exception {
        em.flush();
        m1.setPrice(new BigDecimal("1000").negate());
        modelDao.update(m1);
        em.flush();
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNegativeAge() throws Exception {
        em.flush();
        m1.setAgeLimit(Byte.MIN_VALUE);
        modelDao.update(m1);
        em.flush();
    }
    
    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testUpdateNonExisting() throws Exception {
        em.flush();
        Model m = new Model();
        m.setName("Model_nonExisting");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(new BigDecimal("8888.00"));
        m.setCategory(c1);
        m.setPieces(new ArrayList<Piece>());
        
        modelDao.update(m);
        em.flush();
    }

    @Test
    public void testDelete() throws Exception {
        modelDao.delete(m2);
        em.flush();
        Assert.assertEquals(modelDao.findAll().size(), 2);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testDeleteNonExisting() throws Exception {
        Model m = new Model();
        m.setName("Model_nonExisting");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(new BigDecimal("8888.00"));
        m.setCategory(c1);
        m.setPieces(new ArrayList<Piece>());

        modelDao.delete(m);
        em.flush();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() throws Exception {
        modelDao.delete(null);
        em.flush();
    }

    
}
