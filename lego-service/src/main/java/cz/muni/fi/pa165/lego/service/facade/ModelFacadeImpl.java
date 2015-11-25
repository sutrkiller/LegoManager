package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.ModelCreateDTO;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.dto.PieceDTO;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.lego.service.*;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
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
    public Long createModel(ModelDTO modelDTO) {
        Model model = beanMappingService.mapTo(modelDTO, Model.class);
        model.setCategory(categoryService.findById(modelDTO.getCategory().getId()));
        modelService.create(model);
        return model.getId();
    }

    @Override
    public ModelDTO findModelById(Long id) {
        return beanMappingService.mapTo(modelService.findById(id), ModelDTO.class);
    }

    @Override
    public ModelDTO findModelByName(String name) {
        return beanMappingService.mapTo(modelService.findByName(name), ModelDTO.class);
    }

    @Override
    public List<ModelDTO> findAllModels() {
        return beanMappingService.mapTo(modelService.findAll(), ModelDTO.class);
    }

    @Override
    public List<ModelDTO> findModelsByCategory(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        return beanMappingService.mapTo(modelService.findByCategory(category), ModelDTO.class);
    }

    @Override
    public void update(ModelDTO modelDTO) {
        Model model = beanMappingService.mapTo(modelDTO, Model.class);
        model.setCategory(categoryService.findById(modelDTO.getCategory().getId()));
        modelService.update(model);
    }

    @Override
    public void addPiece(Long modelId, PieceDTO pieceDTO) {
        Model model = modelService.findById(modelId);
        Piece piece = beanMappingService.mapTo(pieceDTO, Piece.class);
        piece.setType(pieceTypeService.findById(pieceDTO.getPieceType().getId()));
        modelService.addPiece(model, piece);
    }

    @Override
    public void removePiece(Long modelId, Long pieceId) {
        Model model = modelService.findById(modelId);
        Piece piece = pieceService.findById(pieceId);
        modelService.removePiece(model, piece);
    }

    @Override
    public void deleteModel(Long modelId) {
        Model model = modelService.findById(modelId);
        modelService.delete(model);
    }
}
