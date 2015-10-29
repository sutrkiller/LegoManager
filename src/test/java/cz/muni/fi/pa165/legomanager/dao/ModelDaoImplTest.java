package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
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
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    private Model m1;
    private Model m2;
    private Model m3;
    private Category c1;

    @BeforeMethod
    public void before() {
        c1 = new Category();
        c1.setDescription("New category");
        c1.setName("TestingCategory");
        categoryDao.create(c1);
        
        m1 = new Model();
        m1.setName("Model-1");
        m1.setAgeLimit(Byte.valueOf("18"));
        m1.setPrice(BigDecimal.ZERO);
        m1.setCategory(c1);
        m1.setPieces(new ArrayList<PieceType>());
        modelDao.create(m1);
        
        m2 = new Model();
        m2.setName("Model-2");
        m2.setAgeLimit(Byte.valueOf("25"));
        m2.setPrice(BigDecimal.ONE);
        m2.setCategory(c1);
        m2.setPieces(new ArrayList<PieceType>());
        modelDao.create(m2);
        
        m3 = new Model();
        m3.setName("Model-3");
        m3.setAgeLimit(Byte.valueOf("60"));
        m3.setPrice(new BigDecimal("500.00"));
        m3.setCategory(c1);
        m3.setPieces(new ArrayList<PieceType>());
        modelDao.create(m3);
        
    }
   
    

        @Test(expectedExceptions=LegoPersistenceException.class)
    public void testCreateWithNullName() {
        Model m = new Model();
   
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(BigDecimal.ONE);
        m.setCategory(c1);
        m.setPieces(new ArrayList<PieceType>());
        
       modelDao.create(m);
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testNonUniqueName() {
        Model m = new Model();
        m.setName("Model-1");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(BigDecimal.ONE);
        m.setCategory(c1);
        m.setPieces(new ArrayList<PieceType>());
        
       modelDao.create(m);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullModel() {
        
    }
    
    @Test(expectedExceptions = EntityAlreadyExistsException.class)
    public void testModelAlreadyExists(){
        
    }
    

 
    
    @Test
    public void testFindById() {
        Model res = modelDao.findById(m1.getId());
        Assert.assertEquals(res, m1);
    }
    
    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByNonExistingId() {
        Model res = modelDao.findById(Long.MAX_VALUE);
        Assert.assertNull(res);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNegativeId() {
        
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        
    }
    
    @Test
    public void testFindByName() {
        Model res = modelDao.findByName(m1.getName());
        Assert.assertEquals(res, m1);
    }
    
        @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByNonExistingName() {
        Model res = modelDao.findByName("Non existing");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullName() {
        Model res = modelDao.findByName(null);
    }
    
    @Test
    public void testFindAll() {
        List<Model> list = modelDao.findAll();
        Assert.assertEquals(list.size(),3);
    }

    @Test
    public void testUpdate() {
        String newName = "New Model";
        m1.setName(newName);
        modelDao.update(m1);
        
        Model res = modelDao.findById(m1.getId());
        Assert.assertEquals(res.getName(), newName);
    }
    
    @Test
    public void testDelete() {
      modelDao.delete(m2);
      
      Assert.assertEquals(modelDao.findAll().size(), 2);
      Assert.assertNull(modelDao.findById(m2.getId()));
    }
    
    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testDeleteNonExisting() {
        Model m = new Model();
        m.setName("Model_nonExisting");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(new BigDecimal("8888.00"));
        m.setCategory(c1);
        m.setPieces(new ArrayList<PieceType>());
        
        modelDao.delete(m);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        
    }
    
    
     
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testCreateNegativePrice() {
        Model m = new Model();
        m.setName("Model-1");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(new BigDecimal("25.0").negate());
        m.setCategory(c1);
        m.setPieces(new ArrayList<PieceType>());
        
       modelDao.create(m);
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNegativeAge() {
        
    }
    
    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullPieces() {
        Model m = new Model();
        m.setName("Model-1");
        m.setAgeLimit(Byte.valueOf("25"));
        m.setPrice(new BigDecimal("25.0"));
        m.setCategory(c1);
        m.setPieces(null);
        
       modelDao.create(m);
    }
    
    //flush everywhere
    
}
