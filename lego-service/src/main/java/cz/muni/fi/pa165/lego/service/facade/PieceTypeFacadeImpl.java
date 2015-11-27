package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.PieceTypeDTO;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.PieceTypeService;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PieceTypeFacadeImpl implements {@link PieceTypeFacade}.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 21.11.2015
 */
@Service
@Transactional
public class PieceTypeFacadeImpl implements PieceTypeFacade {

    @Autowired
    private PieceTypeService pieceTypeService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(PieceTypeDTO pieceTypeDTO) {
        PieceType mappedPieceType = beanMappingService.mapTo(pieceTypeDTO, PieceType.class);
        pieceTypeService.create(mappedPieceType);
        return mappedPieceType.getId();
    }

    @Override
    public void update(PieceTypeDTO pieceTypeDTO) {
        PieceType mappedPieceType = beanMappingService.mapTo(pieceTypeDTO, PieceType.class);
        pieceTypeService.update(mappedPieceType);
    }

    @Override
    public void delete(Long pieceTypeId) {
        PieceType pieceType = pieceTypeService.findById(pieceTypeId);
        pieceTypeService.delete(pieceType);
    }

    @Override
    public List<PieceTypeDTO> findAll() {
        return beanMappingService.mapTo(pieceTypeService.findAll(), PieceTypeDTO.class);
    }

    @Override
    public PieceTypeDTO findById(Long id) {
        return beanMappingService.mapTo(pieceTypeService.findById(id), PieceTypeDTO.class);
    }

    @Override
    public PieceTypeDTO findByName(String name) {
        return beanMappingService.mapTo(pieceTypeService.findByName(name), PieceTypeDTO.class);
    }
}
