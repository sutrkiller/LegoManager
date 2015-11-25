package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.dao.PieceDao;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
 * Test class for PieceService class.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 24.11.2015
 */
@RunWith(MockitoJUnitRunner.class)
public class PieceServiceTest {
    
    @Mock
    private PieceDao pieceDao;
    
    @Inject
    @InjectMocks
    private PieceServiceImpl pieceService;
    
    @Mock 
    private PieceType pieceTypeCube;
    
    @Mock
    private PieceType pieceTypeSphere;
    
    @Mock
    private Piece pieceBlackCube;
    
    @Mock
    private Piece pieceWhiteCube;
    
    @Mock
    private Piece pieceBlackSphere;
            
    @Mock
    private Piece pieceWhiteSphere;
    
    //@Mock
    private Piece returnedPiece;
    
    
    @BeforeMethod
    public void setup() throws EntityNotExistsException
    {
        MockitoAnnotations.initMocks(this);
        
        when(pieceDao.findById(1L)).thenReturn(pieceBlackCube);
        when(pieceDao.findById(2L)).thenReturn(pieceWhiteCube);
        when(pieceDao.findById(3L)).thenReturn(pieceBlackSphere);
        when(pieceDao.findById(4L)).thenReturn(pieceWhiteSphere);
        
        when(pieceBlackCube.getCurrentColor()).thenReturn(Color.BLACK);
        when(pieceBlackSphere.getCurrentColor()).thenReturn(Color.BLACK);
        when(pieceWhiteCube.getCurrentColor()).thenReturn(Color.WHITE);
        when(pieceWhiteSphere.getCurrentColor()).thenReturn(Color.WHITE);
        when(pieceBlackCube.getId()).thenReturn(1L);
        when(pieceWhiteCube.getId()).thenReturn(2L);
        when(pieceBlackSphere.getId()).thenReturn(3L);
        when(pieceWhiteSphere.getId()).thenReturn(4L);
        when(pieceBlackCube.getType()).thenReturn(pieceTypeCube);
        when(pieceWhiteCube.getType()).thenReturn(pieceTypeCube);
        when(pieceBlackSphere.getType()).thenReturn(pieceTypeSphere);
        when(pieceWhiteSphere.getType()).thenReturn(pieceTypeSphere);
        
        when(pieceTypeCube.getId()).thenReturn(1L);
        when(pieceTypeSphere.getId()).thenReturn(2L);
        when(pieceTypeCube.getName()).thenReturn("cube");
        when(pieceTypeSphere.getName()).thenReturn("sphere");
        when(pieceTypeCube.getColors()).thenReturn(new HashSet<>(Arrays.asList(Color.BLACK,Color.WHITE,Color.RED)));
        when(pieceTypeSphere.getColors()).thenReturn(new HashSet<>(Arrays.asList(Color.BLACK,Color.WHITE,Color.BLUE)));
        
        
    }
    
    @Test
    public void testCreatePiece() throws LegoPersistenceException {
        pieceService.create(pieceBlackCube);
        
        verify(pieceDao).create(pieceBlackCube);
    }
    
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullPiece() throws LegoPersistenceException {
        //doThrow(IllegalArgumentException.class).when(pieceDao).create(null);
        
        pieceService.create(null);
    }
    
    @Test
    public void testUpdatePiece() throws LegoPersistenceException   {
        pieceService.update(pieceBlackCube);
        
        verify(pieceDao).update(pieceBlackCube);
    }
    
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullPiece() {
        pieceService.update(null);
    }
    
    @Test
    public void testDeletePiece() throws EntityNotExistsException {
        pieceService.delete(pieceBlackCube);
        verify(pieceDao).delete(pieceBlackCube);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullPiece() {
        pieceService.delete(null);
    }
    
    @Test
    public void testFindById() throws EntityNotExistsException {
        returnedPiece = pieceService.findById(Long.valueOf(1));
        verify(pieceDao).findById(Long.valueOf(1));
        
        assertNotNull(returnedPiece);
        assertEquals(pieceBlackCube, returnedPiece);
    }
    
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        pieceService.findById(null);
    }
    
    @Test
    public void testFindAll() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(pieceBlackCube);
        pieces.add(pieceBlackSphere);
        pieces.add(pieceWhiteCube);
        pieces.add(pieceWhiteSphere);
        
        when(pieceDao.findAll()).thenReturn(pieces);
        
        List<Piece> returnedPieces = pieceService.findAll();
        
        assertNotNull(returnedPieces);
        
        assertEquals(pieces.get(0),returnedPieces.get(0));
        assertEquals(pieces.get(1),returnedPieces.get(1));
        assertEquals(pieces.get(2),returnedPieces.get(2));
        assertEquals(pieces.get(3),returnedPieces.get(3));
    }
}
