package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.PieceTypeDTOGet;
import cz.muni.fi.pa165.lego.dto.PieceTypeDTOPut;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.PieceTypeService;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.inject.Inject;

/**
 * PieceTypeFacadeImpl implements {@link PieceTypeFacade}.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 21.11.2015
 */
@Service
@Transactional
public class PieceTypeFacadeImpl implements PieceTypeFacade {

    @Inject
    private PieceTypeService pieceTypeService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public PieceTypeDTOGet create(PieceTypeDTOPut pieceTypeDTO) {
        PieceType mappedPieceType = beanMappingService.mapTo(pieceTypeDTO, PieceType.class);
        pieceTypeService.create(mappedPieceType);
        return beanMappingService.mapTo(pieceTypeService.findById(mappedPieceType.getId()), PieceTypeDTOGet.class);
    }

    @Override
    public void update(PieceTypeDTOPut pieceTypeDTO, Long id) {
        PieceType destination = pieceTypeService.findById(id);
        beanMappingService.mapTo(pieceTypeDTO, destination);
        destination.setColors(pieceTypeDTO.getColors());
        pieceTypeService.update(destination);
    }

    @Override
    public void delete(Long pieceTypeId) {
        PieceType pieceType = pieceTypeService.findById(pieceTypeId);
        pieceTypeService.delete(pieceType);
    }

    @Override
    public List<PieceTypeDTOGet> findAll() {
        return beanMappingService.mapTo(pieceTypeService.findAll(), PieceTypeDTOGet.class);
    }

    @Override
    public PieceTypeDTOGet findById(Long id) {
        return beanMappingService.mapTo(pieceTypeService.findById(id), PieceTypeDTOGet.class);
    }

    @Override
    public PieceTypeDTOGet findByName(String name) {
        return beanMappingService.mapTo(pieceTypeService.findByName(name), PieceTypeDTOGet.class);
    }
}
