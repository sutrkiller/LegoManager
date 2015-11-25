/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.PieceDTO;
import cz.muni.fi.pa165.lego.dto.PieceTypeDTO;
import cz.muni.fi.pa165.lego.enums.Color;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.PieceService;
import cz.muni.fi.pa165.lego.service.facade.PieceFacadeImpl;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
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
 *Test class for PieceFacade class.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.11.2015
 */

@RunWith(MockitoJUnitRunner.class)
public class PieceFacadeTest {
    
    @Mock
    private PieceService pieceService;
    
    @Mock
    private BeanMappingService mappingService;
    
    @Inject
    @InjectMocks
    private PieceFacadeImpl pieceFacade;
    
    @Mock
    private Piece pieceBlackCube;
    
    @Mock
    private PieceType pieceTypeCube;
    
    @Mock
    private PieceDTO pieceDTO;
    
    @Mock
    private PieceTypeDTO pieceTypeDTO;
    
    //@Mock
    private PieceDTO returnedPieceDTO;
    
    
    
    @BeforeMethod 
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        when(mappingService.mapTo(any(), eq(Piece.class))).thenReturn(pieceBlackCube);
        when(mappingService.mapTo(any(), eq(PieceDTO.class))).thenReturn(pieceDTO);
        List<PieceDTO> piecesDTO = new ArrayList<>();
        piecesDTO.add(pieceDTO);
        when(mappingService.mapTo(any(Collection.class), eq(PieceDTO.class))).thenReturn(piecesDTO);
        //when(pieceService.findById(1L)).thenReturn(pieceBlackCube);
        
        when(pieceBlackCube.getId()).thenReturn(1L);
        when(pieceBlackCube.getCurrentColor()).thenReturn(cz.muni.fi.pa165.legomanager.enums.Color.BLACK);
        when(pieceBlackCube.getType()).thenReturn(pieceTypeCube);
        when(pieceTypeCube.getId()).thenReturn(1L);
        when(pieceTypeCube.getName()).thenReturn("cube");
        when(pieceTypeCube.getColors()).thenReturn(new HashSet<>(Arrays.asList(cz.muni.fi.pa165.legomanager.enums.Color.BLACK,cz.muni.fi.pa165.legomanager.enums.Color.WHITE)));
        when(pieceDTO.getId()).thenReturn(1L);
        when(pieceDTO.getCurrentColor()).thenReturn(Color.BLACK);
        when(pieceDTO.getPieceType()).thenReturn(pieceTypeDTO);
        when(pieceTypeDTO.getId()).thenReturn(1L);
        //TODO: mock other get methods for pieceTypeDTO
    }
    
    
    @Test
    public void testCreatePiece() {
        Long id = pieceFacade.createPiece(pieceDTO);
        
        returnedPieceDTO = pieceFacade.getPieceById(id);
        
        assertEquals(returnedPieceDTO, pieceDTO);
        verify(pieceService).create(any(Piece.class));
    }
    
    @Test
    public void testUpdate() {
        pieceFacade.updatePiece(pieceDTO);
        verify(pieceService).update(any(Piece.class));
    }
    
    @Test
    public void testDelete() {
        pieceFacade.deletePiece(pieceDTO.getId());
        
        verify(pieceService).delete(any(Piece.class));
    }
    
    @Test
    public void testFindById() {
        returnedPieceDTO = pieceFacade.getPieceById(pieceDTO.getId());
        
        assertEquals(returnedPieceDTO, pieceDTO);
        verify(pieceService).findById(any(Long.class));
    }
    
    @Test
    public void testFindAll() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(pieceBlackCube);
        
        List<PieceDTO> piecesDTO = new ArrayList<>();
        piecesDTO.add(mappingService.mapTo(pieceBlackCube, PieceDTO.class));
        
        when(pieceService.findAll()).thenReturn(pieces);
        
        List<PieceDTO> returnedPiecesDTO = pieceFacade.getAllPieces();
        
        assertNotNull(returnedPiecesDTO);
        assertEquals(returnedPiecesDTO.get(0),piecesDTO.get(0));
        verify(pieceService).findAll();
    }
    
}
