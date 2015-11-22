package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.LegoSetCreateDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTO;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.LegoSetService;
import cz.muni.fi.pa165.lego.service.ModelService;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * LegoSetFacadeImpl implements {@ling LegoSeFacade}.
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
public class LegoSetFacadeImpl implements LegoSetFacade {

    @Inject
    private LegoSetService legoSetService;
    
    @Inject
    private ModelService modelService;
    
    @Inject
    private CategoryService categoryService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createLegoSet(LegoSetCreateDTO legoSetCreateDTO) {
        LegoSet mappedLegoSet = beanMappingService.mapTo(legoSetCreateDTO, LegoSet.class);
        mappedLegoSet.setName(legoSetCreateDTO.getName());
        mappedLegoSet.setPrice(legoSetCreateDTO.getPrice());
        mappedLegoSet.setCategory(legoSetCreateDTO.getCategory());
        legoSetService.createLegoSet(mappedLegoSet);
        return mappedLegoSet.getId();
    }

    @Override
    public void deleteLegoSet(Long legoSetId) {
        LegoSet lgs = legoSetService.findById(legoSetId);
        legoSetService.deleteLegoSet(lgs);
    }

    @Override
    public LegoSetDTO getLegoSetById(Long id) {
        return beanMappingService.mapTo(legoSetService.findById(id), LegoSetDTO.class);
    }

    @Override
    public LegoSetDTO getLegoSetByName(String name) {
        return beanMappingService.mapTo(legoSetService.findByName(name), LegoSetDTO.class);
    }

    @Override
    public List<LegoSetDTO> getAllLegoSets() {
        return beanMappingService.mapTo(legoSetService.findAll(), LegoSetDTO.class);
    }

    @Override
    public void addModel(Long legoSetId, Long modelId) {
        legoSetService.addModel(legoSetService.findById(legoSetId), modelService.findById(modelId));
    }

    @Override
    public void removeModel(Long legoSetId, Long modelId) {
        legoSetService.removeModel(legoSetService.findById(legoSetId), modelService.findById(modelId));
    }

    @Override
    public void changePrice(Long legoSetId, BigDecimal newPrice) {
        legoSetService.updatePrice(legoSetService.findById(legoSetId), newPrice);
    }

    @Override
    public void changeCategory(Long legoSetId, Long categoryId) {
        legoSetService.updateCategory(legoSetService.findById(legoSetId), categoryService.findById(categoryId));
    }

   
    
}
