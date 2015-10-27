package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
public class ModelDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public ModelDao modelDao;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    //@PersistenceContext
    private EntityManager e;
    
    private Model m1;
    private Category c1;

    @BeforeMethod
    public void before() {
        
        e = emf.createEntityManager();
        e.getTransaction().begin();
        c1 = new Category();
        c1.setName("Category-1");
        c1.setDescription("I have no idea.");
        e.persist(c1);
        
        
        
        m1 = new Model();
        m1.setName("Model-1");
        m1.setAgeLimit(Byte.valueOf("18"));
        m1.setPrice(BigDecimal.ZERO);
        m1.setCategory(c1);
        m1.setPieces(new ArrayList<Piece>());
        e.persist(m1);
        
       Model m2 = new Model();
        m2.setName("Model-2");
        m2.setAgeLimit(Byte.valueOf("25"));
        m2.setPrice(BigDecimal.ONE);
        m2.setCategory(c1);
        m2.setPieces(new ArrayList<Piece>());
        e.persist(m2);
        
        
        
        //e.getTransaction().commit();
        //e.close();
    }
   
    @AfterMethod
    public void after() {
        e.getTransaction().rollback();
        e.close();
    }

    @Test
    public void testCreate() {
        Model m3 = new Model();
        m3.setName("Model-2");
        m3.setAgeLimit(Byte.valueOf("25"));
        m3.setPrice(BigDecimal.ONE);
        m3.setCategory(c1);
        m3.setPieces(new ArrayList<Piece>());
        
       modelDao.create(m3);
        
        Assert.assertNotNull(m3.getId());
        
        
    }

    @Test
    public void testFindById() {
        Model res = modelDao.findById(m1.getId());
        Assert.assertEquals(m1.getName(), res.getName());
    }

    @Test
    public void testFindByName() {
        Model res = modelDao.findByName("Model-1");
        Assert.assertEquals(m1.getId(), res.getId());
    }

    @Test
    public void testFindAll() {
        List<Model> list = modelDao.findAll();
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void testDelete() {
      modelDao.delete(m1);
      Assert.assertNull(e.find(Model.class, m1.getId()));
        
    }
}
