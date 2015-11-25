package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.*;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.ModelService;
import cz.muni.fi.pa165.lego.service.PieceService;
import cz.muni.fi.pa165.lego.service.PieceTypeService;
import cz.muni.fi.pa165.lego.service.facade.ModelFacadeImpl;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

/**
 * Test class for ModelFacade class.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 22.11.2015
 */
@RunWith(MockitoJUnitRunner.class)
public class ModelFacadeTest {

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
    @Mock
    private ModelCreateDTO modelCreateDTO;
    @Mock
    private ModelDTO modelDTO;
    @Mock
    private ModelDTO returnedModelDTO;

    @Mock
    private Category category;
    @Mock
    private Category newCategory;
    @Mock
    private CategoryDTO categoryDTO;
    @Mock
    private CategoryDTO newCategoryDTO;

    @Mock
    private Piece piece;
    @Mock
    private PieceType pieceType;
    @Mock
    private PieceTypeDTO pieceTypeDTO;
    @Mock
    private PieceDTO oldPieceDTO;
    @Mock
    private PieceDTO newPieceDTO;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // mocking Dozer mapper
        when(mappingService.mapTo(any(), eq(Model.class))).thenReturn(modelBMW);
        when(mappingService.mapTo(any(), eq(ModelDTO.class))).thenReturn(modelDTO);
        when(mappingService.mapTo(any(), eq(PieceDTO.class))).thenReturn(newPieceDTO);
        when(mappingService.mapTo(any(), eq(PieceDTO.class))).thenReturn(oldPieceDTO);
        when(mappingService.mapTo(any(), eq(Piece.class))).thenReturn(piece);
        List<ModelDTO> models = new ArrayList<>();
        models.add(modelDTO);
        when(mappingService.mapTo(any(Collection.class), eq(ModelDTO.class))).thenReturn(models);

        // mocking service layer
        when(modelService.findById(1L)).thenReturn(modelBMW);
        when(modelService.findByName("BMW")).thenReturn(modelBMW);
        when(categoryService.findById(1L)).thenReturn(category);
        when(categoryService.findById(2L)).thenReturn(newCategory);
        when(pieceTypeService.findById(1L)).thenReturn(pieceType);
        when(pieceService.findById(1L)).thenReturn(piece);

        // mocking DTO objects
        when(modelDTO.getName()).thenReturn("BMW");
        when(modelDTO.getAgeLimit()).thenReturn(Byte.valueOf("5"));
        when(returnedModelDTO.getId()).thenReturn(1L);
        when(returnedModelDTO.getName()).thenReturn("BMW");
        when(returnedModelDTO.getAgeLimit()).thenReturn(Byte.valueOf("5"));
        when(modelDTO.getId()).thenReturn(1L);
        when(modelDTO.getName()).thenReturn("BMW");
        when(modelDTO.getAgeLimit()).thenReturn(Byte.valueOf("5"));
        when(modelDTO.getCategory()).thenReturn(categoryDTO);
        when(oldPieceDTO.getId()).thenReturn(1L);
        when(oldPieceDTO.getPieceType()).thenReturn(pieceTypeDTO);
        when(categoryDTO.getId()).thenReturn(1L);
        when(categoryDTO.getName()).thenReturn("Category containing cars only.");
        when(newCategoryDTO.getId()).thenReturn(2L);
        when(newCategoryDTO.getName()).thenReturn("Category containing BMW cars only.");
        when(pieceTypeDTO.getId()).thenReturn(1L);

        // mocking Model entity
        when(modelBMW.getId()).thenReturn(1L);
        when(modelKIA.getId()).thenReturn(2L);
        when(modelBMW.getName()).thenReturn("BMW");
        when(modelKIA.getName()).thenReturn("KIA");
        List pieces = new ArrayList();
        pieces.add(oldPieceDTO);
        when(modelBMW.getPieces()).thenReturn(pieces);

        // mocking Category entities
        when(category.getId()).thenReturn(1L);
    }

    @Test
    public void testCreateModel() {
        Long id = modelFacade.createModel(modelDTO);

        returnedModelDTO = modelFacade.findModelById(id);

        assertEquals(modelDTO, returnedModelDTO);
        verify(modelService).create(any(Model.class));
    }

    @Test
    public void testUpdate() {
        fail();

        /*
        modelFacade.update(modelDTO.getId(), newCategoryDTO.getId());
        verify(modelService).update(any(Model.class));
         */
    }

    @Test
    public void testFindModelById() {
        returnedModelDTO = modelFacade.findModelById(modelDTO.getId());

        assertEquals(modelDTO, returnedModelDTO);
        verify(modelService).findById(any(Long.class));
    }

    @Test
    public void testFindModelByName() {
        returnedModelDTO = modelFacade.findModelByName(modelDTO.getName());

        assertEquals(modelDTO, returnedModelDTO);
        verify(modelService).findByName(any(String.class));
    }

    @Test
    public void testFindAllModels() {
        List<Model> cars = new ArrayList<>();
        cars.add(modelBMW);

        List<ModelDTO> carsDTO = new ArrayList<>();
        carsDTO.add(mappingService.mapTo(modelBMW, ModelDTO.class));

        when(modelService.findAll()).thenReturn(cars);

        List<ModelDTO> returnedCarsDTO = modelFacade.findAllModels();

        assertNotNull(returnedCarsDTO);
        assertEquals(carsDTO.get(0), returnedCarsDTO.get(0));
        verify(modelService).findAll();
    }

    @Test
    public void testFindModelsByCategory() {
        List<Model> cars = new ArrayList<>();
        cars.add(modelBMW);

        List<ModelDTO> carsDTO = new ArrayList<>();
        carsDTO.add(mappingService.mapTo(modelBMW, ModelDTO.class));

        when(modelService.findByCategory(category)).thenReturn(cars);

        List<ModelDTO> returnedCars = modelFacade.findModelsByCategory(category.getId());

        assertNotNull(returnedCars);
        assertEquals(carsDTO.get(0), returnedCars.get(0));
        verify(modelService).findByCategory(category);
    }

    @Test
    public void testAddPiece() {
        modelFacade.addPiece(modelDTO.getId(), newPieceDTO);

        verify(pieceService).create(any(Piece.class));
        verify(modelService).addPiece(any(Model.class), any(Piece.class));
    }

    @Test
    public void testRemovePiece() {
        modelFacade.removePiece(modelDTO.getId(), oldPieceDTO.getId());

        verify(pieceService).delete(any(Piece.class));
        verify(modelService).removePiece(any(Model.class), any(Piece.class));
    }

    @Test
    public void testDeleteModel() {
        modelFacade.deleteModel(modelDTO.getId());

        verify(modelService).delete(any(Model.class));
    }
}
