package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTO;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.LegoSetService;
import cz.muni.fi.pa165.lego.service.ModelService;
import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.lego.service.facade.LegoSetFacadeImpl;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for ModelFacade class.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 25.11.2015
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class LegoSetFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private LegoSetService legoSetService;

    @Mock
    private ModelService modelService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private LegoSetDTO legoSetDTO;

    @Mock
    private LegoSet testLegoSet;

    @Mock
    private Model testModel;

    @Mock
    private Model newModel;

    @Mock
    private Category testCategory;

    @Mock
    private CategoryDTO categoryDTO;

    @Mock
    private BeanMappingService mappingService;

    @Inject
    @InjectMocks
    private LegoSetFacade legoSetFacade;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mappingService.mapTo(any(), eq(LegoSet.class))).thenReturn(testLegoSet);
        when(mappingService.mapTo(any(), eq(LegoSetDTO.class))).thenReturn(legoSetDTO);

        when(testModel.getId()).thenReturn(1L);
        when(newModel.getId()).thenReturn(2L);
        when(testCategory.getId()).thenReturn(1L);

        when(modelService.findById(1L)).thenReturn(testModel);
        when(modelService.findById(2L)).thenReturn(newModel);

        when(categoryService.findById(testCategory.getId())).thenReturn(testCategory);

        when(legoSetService.findById(1L)).thenReturn(testLegoSet);

        List<Model> allModels = new ArrayList<>();
        allModels.add(testModel);

        when(testLegoSet.getId()).thenReturn(1L);
        when(testLegoSet.getCategory()).thenReturn(testCategory);
        when(testLegoSet.getName()).thenReturn("TestName");
        when(testLegoSet.getModels()).thenReturn(allModels);

        when(categoryDTO.getId()).thenReturn(1L);

        when(legoSetDTO.getId()).thenReturn(1L);
        when(legoSetDTO.getCategory()).thenReturn(categoryDTO);
        when(legoSetDTO.getName()).thenReturn("TestName");
        when(legoSetDTO.getModels()).thenReturn(allModels);
    }

    @Test
    public void testCreateLegoSet() {
        Long id = legoSetFacade.create(legoSetDTO);

        LegoSetDTO found = legoSetFacade.findById(id);

        Assert.assertEquals(legoSetDTO, found);
        verify(legoSetService).create(any(LegoSet.class));
    }

    @Test
    public void testUpdateLegoSet() {
        legoSetFacade.update(legoSetDTO);
        verify(legoSetService).updateLegoSet(any(LegoSet.class));
    }

    @Test
    public void testDeleteLegoSet() {
        legoSetFacade.delete(legoSetDTO.getId());
        verify(legoSetService).deleteLegoSet(any(LegoSet.class));
    }

    @Test
    public void testFindLegoSetById() {
        legoSetFacade.findById(legoSetDTO.getId());
        verify(legoSetService).findById(legoSetDTO.getId());
    }

    @Test
    public void testFindLegoSetByName() {
        LegoSetDTO found = legoSetFacade.findByName(legoSetDTO.getName());
        verify(legoSetService).findByName(legoSetDTO.getName());
        Assert.assertEquals(legoSetDTO, found);
    }

    @Test
    public void testFindAllLegoSets() {
        legoSetFacade.findAll();
        verify(legoSetService).findAll();

    }

    @Test
    public void testAddModel() {
        legoSetFacade.addModel(testLegoSet.getId(), newModel.getId());
        verify(legoSetService).addModel(testLegoSet, newModel);
    }

    @Test
    public void testRemoveModel() {
        legoSetFacade.removeModel(testLegoSet.getId(), newModel.getId());
        verify(legoSetService).removeModel(testLegoSet, newModel);
    }

}
