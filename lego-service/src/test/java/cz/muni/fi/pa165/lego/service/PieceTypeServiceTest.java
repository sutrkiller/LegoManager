package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.dao.PieceTypeDao;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import java.util.Arrays;
import java.util.HashSet;
import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for PieceTypeService class.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 24.11.2015
 */

@RunWith(MockitoJUnitRunner.class)
public class PieceTypeServiceTest {
    
    @Mock
    private PieceTypeDao pieceTypeDao;
    
    @Inject
    @InjectMocks
    private PieceTypeServiceImpl pieceTypeService;
    
    @Mock
    private PieceType pieceTypeCube;
    
    @Mock
    private PieceType pieceTypeSphere;
    
    //@Mock
    private PieceType returnedType;
    
    @BeforeMethod
    public void setup() throws EntityNotExistsException {
        MockitoAnnotations.initMocks(this);
        
        when(pieceTypeDao.findById(1L)).thenReturn(pieceTypeCube);
        when(pieceTypeDao.findById(2L)).thenReturn(pieceTypeSphere);
        when(pieceTypeDao.findByName("cube")).thenReturn(pieceTypeCube);
        when(pieceTypeDao.findByName("sphere")).thenReturn(pieceTypeSphere);
        
        when(pieceTypeCube.getId()).thenReturn(1L);
        when(pieceTypeSphere.getId()).thenReturn(2L);
        when(pieceTypeCube.getName()).thenReturn("cube");
        when(pieceTypeSphere.getName()).thenReturn("sphere");
        when(pieceTypeCube.getColors()).thenReturn(new HashSet<>(Arrays.asList(Color.BLACK,Color.WHITE, Color.RED)));
        when(pieceTypeSphere.getColors()).thenReturn(new HashSet<>(Arrays.asList(Color.BLACK,Color.WHITE, Color.BLACK)));
    }
    
    @Test
    public void testCreateTypes() throws LegoPersistenceException {
        pieceTypeService.create(pieceTypeCube);
        
        verify(pieceTypeDao).create(pieceTypeCube);
    }
    
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullTypes() {
        pieceTypeService.create(null);
    }
    
    @Test
    public void testUpdateType() throws LegoPersistenceException {
        pieceTypeService.update(pieceTypeCube);
        
        verify(pieceTypeDao).update(pieceTypeCube);
    }
    
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullType() {
        pieceTypeService.update(null);
    }
    
    @Test
    public void testDeleteType() throws EntityNotExistsException {
        pieceTypeService.delete(pieceTypeCube);
        verify(pieceTypeDao).delete(pieceTypeCube);
    }
    
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullType() {
        pieceTypeService.delete(null);
    }
    
    @Test
    public void testFindById() throws EntityNotExistsException {
        returnedType = pieceTypeService.findById(Long.valueOf(1));
        verify(pieceTypeDao).findById(Long.valueOf(1));
        
        assertNotNull(returnedType);
        assertEquals(pieceTypeCube,returnedType);
    }
    
    @Test
    public void testFindByName() throws EntityNotExistsException {
        returnedType = pieceTypeService.findByName("cube");
        verify(pieceTypeDao).findByName("cube");
        
        assertNotNull(returnedType);
        assertEquals(pieceTypeCube, returnedType);
    }
        
    @Test
    public void testFindAll() {
        List<PieceType> types = new ArrayList<>();
        types.add(pieceTypeCube);
        types.add(pieceTypeSphere);
        
        when(pieceTypeDao.findAll()).thenReturn(types);
        
        List<PieceType> returnedTypes = pieceTypeService.findAll();
        
        assertNotNull(returnedTypes);
        
        assertEquals(types.get(0),returnedTypes.get(0));
        assertEquals(types.get(1),returnedTypes.get(1));
    }
}
