package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOPut;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.LegoSetService;
import cz.muni.fi.pa165.lego.service.ModelService;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * LegoSetFacadeImpl implements {@link LegoSetFacade}.
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
@Service
@Transactional
public class LegoSetFacadeImpl implements LegoSetFacade {

    @Inject
    private LegoSetService legoSetService;
    
    @Inject
    private ModelService modelService;
    
    @Inject
    private CategoryService categoryService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public LegoSetDTOGet create(LegoSetDTOPut legoSetDTO) {
        LegoSet mappedLegoSet = beanMappingService.mapTo(legoSetDTO, LegoSet.class);
        mappedLegoSet.setCategory(categoryService.findById(legoSetDTO.getCategoryId()));
        legoSetService.create(mappedLegoSet);
        return beanMappingService.mapTo(legoSetService.findById(mappedLegoSet.getId()), LegoSetDTOGet.class);
    }

    @Override
    public void update(LegoSetDTOPut updated, Long id) {

        LegoSet destination = legoSetService.findById(id);
        beanMappingService.mapTo(updated, destination);
        destination.setCategory(categoryService.findById(updated.getCategoryId()));
        legoSetService.updateLegoSet(destination);
    }

    @Override
    public void delete(Long legoSetId) {
        LegoSet lgs = legoSetService.findById(legoSetId);
        legoSetService.deleteLegoSet(lgs);
    }

    @Override
    public LegoSetDTOGet findById(Long id) {
        return beanMappingService.mapTo(legoSetService.findById(id), LegoSetDTOGet.class);
    }

    @Override
    public LegoSetDTOGet findByName(String name) {
        return beanMappingService.mapTo(legoSetService.findByName(name), LegoSetDTOGet.class);
    }

    @Override
    public List<LegoSetDTOGet> findAll() {
        return beanMappingService.mapTo(legoSetService.findAll(), LegoSetDTOGet.class);
    }

    @Override
    public void addModel(Long legoSetId, Long modelId) {
        legoSetService.addModel(legoSetService.findById(legoSetId), modelService.findById(modelId));
    }

    @Override
    public void removeModel(Long legoSetId, Long modelId) {
        legoSetService.removeModel(legoSetService.findById(legoSetId), modelService.findById(modelId));
    }

}
