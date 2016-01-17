package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.ModelDTOGet;
import cz.muni.fi.pa165.lego.dto.ModelDTOPut;
import cz.muni.fi.pa165.lego.dto.PieceDTOPut;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.lego.service.*;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * ModelFacadeImpl implements {@link ModelFacade}.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Service
@Transactional
public class ModelFacadeImpl implements ModelFacade {

    @Inject
    private ModelService modelService;

    @Inject
    private PieceTypeService pieceTypeService;

    @Inject
    private PieceService pieceService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long create(ModelDTOPut modelDTO) {
        Model model = beanMappingService.mapTo(modelDTO, Model.class);
        model.setCategory(categoryService.findById(modelDTO.getCategoryId()));
        modelService.create(model);
        return model.getId();
    }

    @Override
    public ModelDTOGet findById(Long id) {
        return beanMappingService.mapTo(modelService.findById(id), ModelDTOGet.class);
    }

    @Override
    public ModelDTOGet findByName(String name) {
        return beanMappingService.mapTo(modelService.findByName(name), ModelDTOGet.class);
    }

    @Override
    public List<ModelDTOGet> findAll() {
        return beanMappingService.mapTo(modelService.findAll(), ModelDTOGet.class);
    }

    @Override
    public List<ModelDTOGet> findByCategory(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        return beanMappingService.mapTo(modelService.findByCategory(category), ModelDTOGet.class);
    }

    @Override
    public void update(ModelDTOPut modelDTO, Long id) {
        Model model = modelService.findById(id);
        beanMappingService.mapTo(modelDTO, model);
        model.setCategory(categoryService.findById(modelDTO.getCategoryId()));
        modelService.update(model);
    }

    @Override
    public void addPiece(Long modelId, PieceDTOPut pieceDTO) {
        Model model = modelService.findById(modelId);
        Piece piece = beanMappingService.mapTo(pieceDTO, Piece.class);
        PieceType pieceType = pieceTypeService.findById(pieceDTO.getPieceTypeId());
        piece.setType(pieceType);
        if (!pieceType.getColors().contains(pieceDTO.getCurrentColor())) {
            throw new IllegalArgumentException("piece do not have color from its piece type.");
        }
        modelService.addPiece(model, piece);
    }

    @Override
    public void removePiece(Long modelId, Long pieceId) {
        Model model = modelService.findById(modelId);
        Piece piece = pieceService.findById(pieceId);
        modelService.removePiece(model, piece);
    }

    @Override
    public void setFiftyPercentDiscount(Long modelId) {
        Model model = modelService.findById(modelId);
        modelService.setFiftyPercentDiscount(model);
    }

    @Override
    public void delete(Long modelId) {
        Model model = modelService.findById(modelId);
        modelService.delete(model);
    }
}
