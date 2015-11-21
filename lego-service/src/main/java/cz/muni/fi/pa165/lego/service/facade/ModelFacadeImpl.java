package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.ModelCreateDTO;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.dto.PieceCreateDTO;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.legomanager.entities.Model;
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

//    @Inject
//    private ModelService modelService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public Long createModel(ModelCreateDTO modelDTO) {
        Model model = beanMappingService.mapTo(modelDTO, Model.class);
        //model.setCategory(categoryService.findById(modelDTO.getCategoryId())).
        //return modelService.create(model).getId();
        return null;
    }

    @Override
    public ModelDTO findModelById(Long id) {
        return null;
    }

    @Override
    public ModelDTO findModelByName(String name) {
        return null;
    }

    @Override
    public List<ModelDTO> findAllModels() {
        return null;
    }

    @Override
    public List<ModelDTO> findModelsByCategory(String categoryName) {
        return null;
    }

    @Override
    public void updateName(String newName) {

    }

    @Override
    public void updateAgeLimit(Byte newAgeLimit) {

    }

    @Override
    public void updatePrice(BigDecimal newPrice) {

    }

    @Override
    public void updateCategory(Long categoryId) {

    }

    @Override
    public void addPiece(Long modelId, PieceCreateDTO piece) {

    }

    @Override
    public void removePiece(Long modelId, Long pieceId) {

    }

    @Override
    public void deleteModel(Long modelId) {

    }
}
