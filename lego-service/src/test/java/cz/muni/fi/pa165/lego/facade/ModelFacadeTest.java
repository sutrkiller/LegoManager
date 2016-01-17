package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.*;
import cz.muni.fi.pa165.lego.service.*;
import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.lego.service.facade.ModelFacadeImpl;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.enums.Color;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test class for ModelFacade class.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 22.11.2015
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class ModelFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ModelService modelService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private PieceTypeService pieceTypeService;
    @Mock
    private PieceService pieceService;
    @Mock
    private BeanMappingService mappingService;

    @Inject
    @InjectMocks
    private ModelFacadeImpl modelFacade;

    @Mock
    private Model modelBMW;
    @Mock
    private Model modelKIA;

    private ModelDTOPut modelDTOPut;
    private ModelDTOGet modelDTO;

    @Mock
    private Category category;
    private Category newCategory;
    private CategoryDTO categoryDTO;
    private CategoryDTO newCategoryDTO;

    private Piece piece;
    @Mock
    private PieceType pieceType;
    private PieceTypeDTOGet pieceTypeDTO;
    private PieceDTOGet oldPieceDTO;
    private PieceDTOPut newPieceDTORed;
    private PieceDTOPut newPieceDTOGreen;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Category containing cars only.");

        newCategoryDTO = new CategoryDTO();
        newCategoryDTO.setId(2L);
        newCategoryDTO.setName("Category containing BMW cars only.");

        // mocking Category entity
        when(category.getId()).thenReturn(1L);

        pieceTypeDTO = new PieceTypeDTOGet();
        pieceTypeDTO.setId(1L);
        Set<Color> clrs = new HashSet<>();
        clrs.add(Color.BLUE);
        clrs.add(Color.RED);
        pieceTypeDTO.setColors(clrs);

        // mocking PieceType entity
        when(pieceType.getId()).thenReturn(1L);
        when(pieceType.getColors()).thenReturn(clrs);
        when(pieceType.getName()).thenReturn("wheels");

        piece = new Piece();
        piece.setCurrentColor(Color.RED);

        List pieces = new ArrayList();
        pieces.add(oldPieceDTO);

        oldPieceDTO = new PieceDTOGet();
        oldPieceDTO.setId(1L);
        oldPieceDTO.setType(pieceTypeDTO);

        newPieceDTORed = new PieceDTOPut();
        newPieceDTORed.setPieceTypeId(pieceType.getId());
        newPieceDTORed.setCurrentColor(Color.RED);

        newPieceDTOGreen = new PieceDTOPut();
        newPieceDTOGreen.setPieceTypeId(pieceType.getId());
        newPieceDTOGreen.setCurrentColor(Color.GREEN);

        // mocking Model entities
        when(modelBMW.getId()).thenReturn(1L);
        when(modelBMW.getName()).thenReturn("BMW");
        when(modelBMW.getPieces()).thenReturn(pieces);

        when(modelKIA.getId()).thenReturn(2L);
        when(modelKIA.getName()).thenReturn("KIA");

        modelDTO = new ModelDTOGet();
        modelDTO.setId(1L);
        modelDTO.setName("BMW");
        modelDTO.setAgeLimit(Byte.valueOf("5"));
        modelDTO.setCategory(categoryDTO);
        modelDTO.setPieces(pieces);

        modelDTOPut = new ModelDTOPut();
        modelDTOPut.setName("BMW");
        modelDTOPut.setAgeLimit(Byte.valueOf("5"));

        // mocking Dozer mapper
        when(mappingService.mapTo(any(), eq(Model.class))).thenReturn(modelBMW);
        when(mappingService.mapTo(any(), eq(ModelDTOGet.class))).thenReturn(modelDTO);
        when(mappingService.mapTo(any(), eq(ModelDTOPut.class))).thenReturn(modelDTOPut);
        when(mappingService.mapTo(any(), eq(PieceDTOPut.class))).thenReturn(newPieceDTORed);
        when(mappingService.mapTo(any(), eq(PieceDTOGet.class))).thenReturn(oldPieceDTO);
        when(mappingService.mapTo(any(), eq(Piece.class))).thenReturn(piece);
        List<ModelDTOGet> models = new ArrayList<>();
        models.add(modelDTO);
        when(mappingService.mapTo(any(Collection.class), eq(ModelDTOGet.class))).thenReturn(models);

        // mocking service layer
        when(modelService.findById(1L)).thenReturn(modelBMW);
        when(modelService.findByName("BMW")).thenReturn(modelBMW);
        when(categoryService.findById(1L)).thenReturn(category);
        when(categoryService.findById(2L)).thenReturn(newCategory);
        when(pieceTypeService.findById(1L)).thenReturn(pieceType);
        when(pieceService.findById(1L)).thenReturn(piece);
    }

    @Test
    public void testCreateModel() {
        Long id = modelFacade.create(modelDTOPut);

        modelDTO = modelFacade.findById(id);

        assertEquals(modelDTO.getId(), id);
        verify(modelService).create(any(Model.class));
    }

    @Test
    public void testUpdate() {
        modelFacade.update(modelDTOPut, 1L);
        verify(modelService).update(any(Model.class));
    }

    @Test
    public void testFindModelById() {
        ModelDTOGet returned = modelFacade.findById(modelDTO.getId());

        assertEquals(returned, modelDTO);
        verify(modelService).findById(any(Long.class));
    }

    @Test
    public void testFindModelByName() {
        ModelDTOGet returned = modelFacade.findByName(modelDTO.getName());

        assertEquals(returned, modelDTO);
        verify(modelService).findByName(any(String.class));
    }

    @Test
    public void testFindAllModels() {
        List<Model> cars = new ArrayList<>();
        cars.add(modelBMW);

        List<ModelDTOGet> carsDTO = new ArrayList<>();
        carsDTO.add(mappingService.mapTo(modelBMW, ModelDTOGet.class));

        when(modelService.findAll()).thenReturn(cars);

        List<ModelDTOGet> returnedCarsDTO = modelFacade.findAll();

        assertNotNull(returnedCarsDTO);
        assertEquals(carsDTO.get(0), returnedCarsDTO.get(0));
        verify(modelService).findAll();
    }

    @Test
    public void testFindModelsByCategory() {
        categoryService.create(category);

        List<Model> cars = new ArrayList<>();
        cars.add(modelBMW);

        List<ModelDTOGet> carsDTO = new ArrayList<>();
        carsDTO.add(mappingService.mapTo(modelBMW, ModelDTOGet.class));

        when(modelService.findByCategory(category)).thenReturn(cars);

        List<ModelDTOGet> returnedCars = modelFacade.findByCategory(category.getId());

        assertNotNull(returnedCars);
        assertEquals(carsDTO.get(0), returnedCars.get(0));
        verify(modelService).findByCategory(category);
    }

    @Test
    public void testAddPiece() {
        pieceTypeService.create(pieceType);

        modelFacade.addPiece(modelDTO.getId(), newPieceDTORed);

        verify(modelService).addPiece(any(Model.class), any(Piece.class));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddPieceBadColor() {
        modelFacade.addPiece(modelDTO.getId(), newPieceDTOGreen);

        verify(modelService).addPiece(any(Model.class), any(Piece.class));
    }

    @Test
    public void testRemovePiece() {
        modelFacade.removePiece(modelDTO.getId(), oldPieceDTO.getId());

        verify(modelService).removePiece(any(Model.class), any(Piece.class));
    }

    @Test
    public void testDeleteModel() {
        modelFacade.delete(modelDTO.getId());

        verify(modelService).delete(any(Model.class));
    }
}
