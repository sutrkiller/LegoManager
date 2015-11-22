package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.legomanager.dao.ModelDao;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.testng.annotations.BeforeClass;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for ModelService class.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 22.11.2015
 */
@Transactional
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class ModelServiceTest {

    @Mock
    private ModelDao modelDao;

    @Autowired
    @InjectMocks
    private ModelService modelService;

    private Category modelCategory;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        modelCategory = new Category();
        modelCategory.setName("cars");
        modelCategory.setDescription("Category containings cars only.");
    }

    @Test
    public void testCreateModel() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        verify(modelDao).create(testModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullModel() {
        modelService.create(null);

        verify(modelService, never()).create(any(Model.class));
    }

    @Test(expected = LegoPersistenceException.class)
    public void testCreateNullName() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName(null);
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        verify(modelDao, never()).create(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testCreateNullPieces() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(null);
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        verify(modelDao, never()).create(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testCreateNullCategory() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(null);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        verify(modelDao, never()).create(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testCreateNullPrice() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(null);
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        verify(modelDao, never()).create(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testCreateNullAgeLimit() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(null);
        modelService.create(testModel);

        verify(modelDao, never()).create(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testCreateNegativePrice() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00").negate());
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        verify(modelDao, never()).create(testModel);
    }

    @Test
    public void testUpdateModel() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        testModel.setName("KIA");
        testModel.setPrice(new BigDecimal("100.00"));
        modelService.update(testModel);
        verify(modelDao).update(testModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullModel() throws LegoPersistenceException {
        modelService.update(null);

        verify(modelDao, never()).update(any(Model.class));
    }

    @Test(expected = LegoPersistenceException.class)
    public void testUpdateNameNull() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        testModel.setName(null);
        modelService.update(testModel);
        verify(modelDao, never()).update(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testUpdatePiecesNull() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        testModel.setPieces(null);
        modelService.update(testModel);
        verify(modelDao, never()).update(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testUpdateCategoryNull() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        testModel.setCategory(null);
        modelService.update(testModel);
        verify(modelDao, never()).update(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testUpdatePriceNull() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        testModel.setPrice(null);
        modelService.update(testModel);
        verify(modelDao, never()).update(testModel);
    }

    @Test(expected = LegoPersistenceException.class)
    public void testUpdateAgeLimitNull() throws LegoPersistenceException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        testModel.setAgeLimit(null);
        modelService.update(testModel);
        verify(modelDao, never()).update(testModel);
    }

    @Test
    public void testDeleteModel() {
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullModel() throws EntityNotExistsException {
        modelService.delete(null);

        verify(modelDao, never()).delete(any(Model.class));
    }

    @Test
    public void testFindById() throws EntityNotExistsException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        doReturn(testModel).when(modelDao).findById(1L);

        Model returnedModel = modelService.findById(Long.valueOf(1));
        verify(modelDao).findById(Long.valueOf(1));

        assertNotNull(returnedModel);
        assertEquals(testModel, returnedModel);
    }

    @Test
    public void testFindByName() throws EntityNotExistsException {
        Model testModel = new Model();
        testModel.setName("BMW");
        testModel.setPieces(new ArrayList<Piece>());
        testModel.setCategory(modelCategory);
        testModel.setPrice(new BigDecimal("200.00"));
        testModel.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModel);

        doReturn(testModel).when(modelDao).findByName("BMW");

        Model returnedModel = modelService.findByName("BMW");
        verify(modelDao).findByName("BMW");

        assertNotNull(returnedModel);
        assertEquals(testModel, returnedModel);
    }

    @Test
    public void testFindAll() {
        Model testModelBMW = new Model();
        testModelBMW.setName("BMW");
        testModelBMW.setPieces(new ArrayList<Piece>());
        testModelBMW.setCategory(modelCategory);
        testModelBMW.setPrice(new BigDecimal("200.00"));
        testModelBMW.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModelBMW);
        
        Model testModelKIA = new Model();
        testModelKIA.setName("KIA");
        testModelKIA.setPieces(new ArrayList<Piece>());
        testModelKIA.setCategory(modelCategory);
        testModelKIA.setPrice(new BigDecimal("100.00"));
        testModelKIA.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModelKIA);
        
        Model testModelFord = new Model();
        testModelFord.setName("Ford");
        testModelFord.setPieces(new ArrayList<Piece>());
        testModelFord.setCategory(modelCategory);
        testModelFord.setPrice(new BigDecimal("150.00"));
        testModelFord.setAgeLimit(Byte.valueOf("5"));
        modelService.create(testModelFord);
        
        List<Model> cars = new ArrayList<>();
        cars.add(testModelBMW);
        cars.add(testModelKIA);
        cars.add(testModelFord);
        
        doReturn(cars).when(modelDao).findAll();
        
        List<Model> returnedCars = modelService.findAll();
        assertNotNull(returnedCars);
        assertEquals(cars, returnedCars);
    }

    @Test
    public void testSetFiftyPercentDiscount() {
        fail();
    }

    @Test
    public void testAddPiece() {
        fail();
    }

    @Test
    public void testChangeCategory() {
        fail();
    }
}
