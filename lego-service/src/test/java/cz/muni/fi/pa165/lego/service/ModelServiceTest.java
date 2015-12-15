package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.dao.ModelDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import javax.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Test class for ModelService class.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 22.11.2015
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class ModelServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ModelDao modelDao;

    @Mock
    private PieceServiceImpl pieceService;

    @Inject
    @InjectMocks
    private ModelServiceImpl modelService;

    @Mock
    private Category modelCategory;
    @Mock
    private Category newCategory;

    @Mock
    private Model modelBMW;
    @Mock
    private Model modelKIA;
    @Mock
    private Model modelFord;

    private Model returnedModel;

    @Mock
    private Piece oldPieceForBMW;
    @Mock
    private Piece newPieceForBMW;
    @Mock
    private PieceType pieceTypeForBMW;

    @BeforeMethod
    public void setUp() throws EntityNotExistsException {
        MockitoAnnotations.initMocks(this);

        // mocknig DAO object
        when(modelDao.findById(1L)).thenReturn(modelBMW);
        when(modelDao.findById(2L)).thenReturn(modelKIA);
        when(modelDao.findById(3L)).thenReturn(modelFord);
        when(modelDao.findByName("BMW")).thenReturn(modelBMW);
        when(modelDao.findByName("KIA")).thenReturn(modelKIA);
        when(modelDao.findByName("Ford")).thenReturn(modelFord);

        // mocking Category entity
        when(modelCategory.getId()).thenReturn(1L);

        // mocking Model entities
        when(modelBMW.getPrice()).thenReturn(new BigDecimal("200.00"));
        when(modelKIA.getPrice()).thenReturn(new BigDecimal("100.00"));
        when(modelFord.getPrice()).thenReturn(new BigDecimal("150.00"));
        when(modelBMW.getId()).thenReturn(1L);
        when(modelKIA.getId()).thenReturn(2L);
        when(modelFord.getId()).thenReturn(3L);
        List pieces = new ArrayList();
        pieces.add(oldPieceForBMW);
        when(modelBMW.getPieces()).thenReturn(pieces);

        // mocking Piece entities
        when(newPieceForBMW.getId()).thenReturn(2L);
        when(oldPieceForBMW.getId()).thenReturn(1L);
        when(oldPieceForBMW.getCurrentColor()).thenReturn(Color.BLACK);
        when(oldPieceForBMW.getType()).thenReturn(pieceTypeForBMW);
        when(pieceTypeForBMW.getId()).thenReturn(1L);
        when(pieceTypeForBMW.getName()).thenReturn("stvorec");
        when(pieceTypeForBMW.getColors()).thenReturn(new TreeSet<>(Arrays.asList(Color.BLACK, Color.WHITE)));

    }

    @Test
    public void testCreateModel() throws LegoPersistenceException {
        modelService.create(modelBMW);

        verify(modelDao).create(modelBMW);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullModel() throws LegoPersistenceException {
        modelService.create(null);
    }

    @Test
    public void testUpdateModel() throws LegoPersistenceException {
        modelService.update(modelBMW);

        verify(modelDao).update(modelBMW);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullModel() throws LegoPersistenceException {
        modelService.update(null);
    }

    @Test
    public void testDeleteModel() throws EntityNotExistsException {
        modelService.delete(modelBMW);

        verify(modelDao).delete(modelBMW);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullModel() throws EntityNotExistsException {
        modelService.delete(null);
    }

    @Test
    public void testFindById() throws EntityNotExistsException {
        returnedModel = modelService.findById(1L);
        verify(modelDao).findById(1L);

        assertNotNull(returnedModel);
        assertEquals(modelBMW, returnedModel);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNullId() {
        modelService.findById(null);
    }

    @Test
    public void testFindByName() throws EntityNotExistsException {
        returnedModel = modelService.findByName("BMW");
        verify(modelDao).findByName("BMW");

        assertNotNull(returnedModel);
        assertEquals(modelBMW, returnedModel);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameNullName() {
        modelService.findByName(null);
    }

    @Test
    public void testFindAll() {
        List<Model> cars = new ArrayList<>();
        cars.add(modelBMW);
        cars.add(modelKIA);
        cars.add(modelFord);

        when(modelDao.findAll()).thenReturn(cars);

        List<Model> returnedCars = modelService.findAll();

        assertNotNull(returnedCars);

        assertEquals(cars.get(0), returnedCars.get(0));
        assertEquals(cars.get(1), returnedCars.get(1));
        assertEquals(cars.get(2), returnedCars.get(2));
    }

    @Test
    public void testSetFiftyPercentDiscount() throws LegoPersistenceException {
        modelService.setFiftyPercentDiscount(modelBMW);

        verify(modelDao).update(modelBMW);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetFiftyPercentDiscountNullModel() throws LegoPersistenceException {
        modelService.setFiftyPercentDiscount(null);
    }

    @Test
    public void testAddPiece() throws LegoPersistenceException {
        modelService.addPiece(modelBMW, newPieceForBMW);
        verify(pieceService).create(newPieceForBMW);
        verify(modelDao).update(modelBMW);
        // TODO: fix test
//        assertTrue(modelBMW.getPieces().contains(newPieceForBMW));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddPieceNullPiece() throws LegoPersistenceException {
        modelService.addPiece(modelBMW, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddPieceNullModel() throws LegoPersistenceException {
        modelService.addPiece(null, newPieceForBMW);
    }

    @Test
    public void testRemovePiece() throws LegoPersistenceException {
        modelService.removePiece(modelBMW, oldPieceForBMW);
        verify(pieceService).delete(oldPieceForBMW);
        verify(modelDao).update(modelBMW);

        assertTrue(!(modelBMW.getPieces().contains(oldPieceForBMW)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemovePieceNullPiece() throws LegoPersistenceException {
        modelService.removePiece(modelBMW, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemovePieceNullModel() throws LegoPersistenceException {
        modelService.removePiece(null, oldPieceForBMW);
    }

    @Test
    public void testChangeCategory() throws LegoPersistenceException {
        modelService.changeCategory(modelBMW, newCategory);
        verify(modelDao).update(modelBMW);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testChangeCategoryNullModel() throws LegoPersistenceException {
        modelService.changeCategory(null, newCategory);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testChangeCategoryNullCategory() throws LegoPersistenceException {
        modelService.changeCategory(modelBMW, null);
    }
}
