package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.legomanager.PersistenceApplicationContext;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test class for Category dao manager.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@ContextConfiguration(classes={cz.muni.fi.pa165.legomanager.PersistenceApplicationContext.class})
public class CategoryDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public CategoryDao categoryDao;

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testCreate() {
        categoryDao.create(new Category());
    }

    @Test
    public void testFindById() {
        categoryDao.findById(12L);
    }

    @Test
    public void testFindByName() {
        categoryDao.findByName("Cars");
    }

    @Test
    public void testFindAll() {
        categoryDao.findAll();
    }

    @Test
    public void testDelete() {
        categoryDao.delete(new Category());
    }

}
