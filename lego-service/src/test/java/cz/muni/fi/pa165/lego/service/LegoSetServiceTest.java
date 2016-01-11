package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link LegoSetService} service class.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class LegoSetServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private Category category;

    @Mock
    private LegoSet testLegoSet;

    @Mock
    private LegoSet testLegoSet2;

    @Mock
    private Model testModel;

    @Mock
    private LegoSetDao legoSetDao;

    @Inject
    @InjectMocks
    private LegoSetServiceImpl legoSetService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<LegoSet> allSets = new ArrayList<>();
        allSets.add(testLegoSet);
        allSets.add(testLegoSet2);

        when(testModel.getId()).thenReturn(1L);

        List<Model> allModels = new ArrayList<>();
        allModels.add(testModel);

        when(testLegoSet.getId()).thenReturn(1L);
        when(testLegoSet.getCategory()).thenReturn(category);
        when(testLegoSet.getName()).thenReturn("TestName");
        when(testLegoSet.getModels()).thenReturn(allModels);

        when(testLegoSet2.getId()).thenReturn(2L);
        when(testLegoSet2.getCategory()).thenReturn(new Category());

        when(legoSetDao.findById(1L)).thenReturn(testLegoSet);
        when(legoSetDao.findByName("TestName")).thenReturn(testLegoSet);

        when(legoSetDao.findAll()).thenReturn(allSets);

    }

    @Test
    public void testCreateLegoSet() {
        legoSetService.create(testLegoSet);
        verify(legoSetDao).create(testLegoSet);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullLegoSet() {
        legoSetService.create(null);
        verify(legoSetDao).create(null);
    }

    @Test
    public void testFindById() {
        LegoSet found = legoSetService.findById(1L);
        verify(legoSetDao).findById(1L);
        Assert.assertEquals(testLegoSet, found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        legoSetService.findById(null);
        verify(legoSetDao).findById(null);
    }

    @Test
    public void testFindByName() {
        LegoSet found = legoSetService.findByName("TestName");
        verify(legoSetDao).findByName("TestName");
        Assert.assertEquals(testLegoSet, found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullName() {
        legoSetService.findByName(null);
        verify(legoSetDao).findByName(null);
    }

    @Test
    public void testFindAll() {
        List<LegoSet> found = legoSetService.findAll();
        verify(legoSetDao).findAll();

        List<LegoSet> expected = new ArrayList<>();
        expected.add(testLegoSet);
        expected.add(testLegoSet2);

        Assert.assertEquals(expected, found);
    }

    @Test
    public void testFindByCategory() throws LegoPersistenceException {
        List<LegoSet> expected = new ArrayList<>();
        expected.add(testLegoSet);

        when(legoSetDao.findByCategory(category)).thenReturn(expected);
        List<LegoSet> found = legoSetService.findByCategory(category);

        Assert.assertEquals(expected, found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullCategory() {
        legoSetService.findByCategory(null);
    }

    @Test
    public void testDeleteLegoSet() {
        legoSetService.deleteLegoSet(testLegoSet);
        verify(legoSetDao).delete(testLegoSet);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullLegoSet() {
        legoSetService.deleteLegoSet(null);
        verify(legoSetDao).delete(testLegoSet);
    }

    @Test
    public void testUpdateLegoSet() {
        legoSetService.updateLegoSet(testLegoSet);
        verify(legoSetDao).update(testLegoSet);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullLegoSet() {
        legoSetService.updateLegoSet(null);
    }

    @Test
    public void testAddModel() {
        legoSetService.addModel(testLegoSet2, testModel);
        verify(legoSetDao).update(testLegoSet2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddNullModel() {
        legoSetService.addModel(testLegoSet, null);
    }

    @Test
    public void testRemoveModel() {
        legoSetService.removeModel(testLegoSet, testModel);
        verify(legoSetDao).update(testLegoSet);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveNullModel() {
        legoSetService.removeModel(testLegoSet, null);
    }

}
