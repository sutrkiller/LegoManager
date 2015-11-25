package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.PieceDTO;
import cz.muni.fi.pa165.lego.facade.PieceFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.ModelService;
import cz.muni.fi.pa165.lego.service.PieceService;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 21.11.2015
 */
@Service
@Transactional
public class PieceFacadeImpl implements PieceFacade {

    @Autowired
    private PieceService pieceService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createPiece(PieceDTO pieceDTO) {
        Piece mappedPiece = beanMappingService.mapTo(pieceDTO, Piece.class);
        pieceService.create(mappedPiece);

        // TODO: make pieceService.create return created Piece
        return null;
    }

    @Override
    public void updatePiece(PieceDTO pieceDTO) {
        Piece mappedPiece = beanMappingService.mapTo(pieceDTO, Piece.class);
        pieceService.update(mappedPiece);
    }

    @Override
    public void deletePiece(Long pieceId) {
        Piece piece = pieceService.findById(pieceId);
        pieceService.delete(piece);
    }

    @Override
    public List<PieceDTO> getAllPieces() {
        return beanMappingService.mapTo(pieceService.findAll(), PieceDTO.class);
    }

    @Override
    public PieceDTO getPieceById(Long id) {
        return null;
    }
}