package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.PieceTypeDTO;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.PieceTypeService;
import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.lego.service.facade.PieceTypeFacadeImpl;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for PieceTypeFacade class.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.11.2015
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class PieceTypeFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private PieceTypeService pieceTypeService;
    
    @Mock
    private BeanMappingService mappingService;
    
    @Inject
    @InjectMocks
    private PieceTypeFacadeImpl pieceTypeFacade;
    
    @Mock
    private PieceType pieceTypeCube;
          
    @Mock
    private PieceTypeDTO pieceTypeDTO;
    
    //@Mock
    private PieceTypeDTO returnedPieceTypeDTO;
    
    @BeforeMethod 
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        when(mappingService.mapTo(any(), eq(PieceType.class))).thenReturn(pieceTypeCube);
        when(mappingService.mapTo(any(), eq(PieceTypeDTO.class))).thenReturn(pieceTypeDTO);
        List<PieceTypeDTO> pieceTypesDTO = new ArrayList<>();
        pieceTypesDTO.add(pieceTypeDTO);
        when(mappingService.mapTo(any(Collection.class), eq(PieceTypeDTO.class))).thenReturn(pieceTypesDTO);
        
        when(pieceTypeService.findById(1L)).thenReturn(pieceTypeCube);
        
        when(pieceTypeCube.getId()).thenReturn(1L);
        when(pieceTypeCube.getName()).thenReturn("cube");
        when(pieceTypeCube.getColors()).thenReturn(new HashSet<>(Arrays.asList(cz.muni.fi.pa165.legomanager.enums.Color.BLACK,cz.muni.fi.pa165.legomanager.enums.Color.WHITE)));
        
        when(pieceTypeDTO.getId()).thenReturn(1L);
        //when(pieceTypeDTO.g)
    }
    
    @Test
    public void testCreatePieceType() {
        Long id = pieceTypeFacade.create(pieceTypeDTO);
        
        returnedPieceTypeDTO = pieceTypeFacade.findById(id);
        
        assertEquals(returnedPieceTypeDTO,pieceTypeDTO);
        verify(pieceTypeService).create(any(PieceType.class));
    }
    
    @Test
    public void testUpdatePieceType() {
        pieceTypeFacade.update(pieceTypeDTO);
        verify(pieceTypeService).update(any(PieceType.class));
    }

    @Test
    public void testDeletePieceType() {
        pieceTypeFacade.delete(pieceTypeDTO.getId());
        
        verify(pieceTypeService).delete(any(PieceType.class));
    }
    
    @Test
    public void testGetPieceTypeById() {
        returnedPieceTypeDTO = pieceTypeFacade.findById(pieceTypeDTO.getId());
        
        assertEquals(returnedPieceTypeDTO, pieceTypeDTO);
        verify(pieceTypeService).findById(any(Long.class));
    }
    
    @Test
    public void testGetAllPieceTypes() {
        List<PieceType> pieceTypes = new ArrayList<>();
        pieceTypes.add(pieceTypeCube);
        
        List<PieceTypeDTO> pieceTypesDTO = new ArrayList<>();
        pieceTypesDTO.add(mappingService.mapTo(pieceTypeCube, PieceTypeDTO.class));
        
        when(pieceTypeService.findAll()).thenReturn(pieceTypes);
        
        List<PieceTypeDTO> returnedPieceTypesDTO = pieceTypeFacade.findAll();
        
        assertNotNull(returnedPieceTypesDTO);
        assertEquals(returnedPieceTypesDTO.get(0),pieceTypesDTO.get(0));
        verify(pieceTypeService).findAll();
    }
    
    @Test
    public void testGetPieceTypeByName() {
        returnedPieceTypeDTO = pieceTypeFacade.findByName(pieceTypeDTO.getName());
        
        assertEquals(returnedPieceTypeDTO, pieceTypeDTO);
        verify(pieceTypeService).findByName(any(String.class));
    }
}
