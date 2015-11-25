package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link LegoSetService} service class.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
@RunWith(MockitoJUnitRunner.class)
public class LegoSetServiceTest {

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

    @BeforeClass
    public void prepareTestLegoSet() {
        MockitoAnnotations.initMocks(this);

        List<LegoSet> allSets = new ArrayList<>();
        allSets.add(testLegoSet);
        allSets.add(testLegoSet2);

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
        legoSetService.createLegoSet(testLegoSet);
        verify(legoSetDao).create(testLegoSet);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullLegoSet() {
        legoSetService.createLegoSet(null);
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
        List<LegoSet> found = legoSetService.findByCategory(category);

        List<LegoSet> expected = new ArrayList<>();
        expected.add(testLegoSet);
        Assert.assertEquals(expected, found);
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
        legoSetService.addModel(testLegoSet, testModel);
        verify(legoSetDao).update(testLegoSet);
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
