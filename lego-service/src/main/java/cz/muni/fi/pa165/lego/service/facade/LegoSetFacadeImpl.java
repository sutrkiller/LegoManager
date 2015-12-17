package cz.muni.fi.pa165.lego.service.facade;

import cz.muni.fi.pa165.lego.dto.LegoSetDTO;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.service.BeanMappingService;
import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.LegoSetService;
import cz.muni.fi.pa165.lego.service.ModelService;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * LegoSetFacadeImpl implements {@ling LegoSeFacade}.
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
    public Long create(LegoSetDTO legoSetDTO) {
        LegoSet mappedLegoSet = beanMappingService.mapTo(legoSetDTO, LegoSet.class);
        mappedLegoSet.setName(legoSetDTO.getName());
        mappedLegoSet.setPrice(legoSetDTO.getPrice());
        mappedLegoSet.setCategory(categoryService.findById(legoSetDTO.getCategory().getId()));
        legoSetService.create(mappedLegoSet);
        return mappedLegoSet.getId();
    }

    @Override
    public void update(LegoSetDTO updated) {
        LegoSet mapped = beanMappingService.mapTo(updated, LegoSet.class);
        mapped.setCategory(categoryService.findById(updated.getCategory().getId()));
        legoSetService.updateLegoSet(mapped);
    }

    @Override
    public void delete(Long legoSetId) {
        LegoSet lgs = legoSetService.findById(legoSetId);
        legoSetService.deleteLegoSet(lgs);
    }

    @Override
    public LegoSetDTO findById(Long id) {
        return beanMappingService.mapTo(legoSetService.findById(id), LegoSetDTO.class);
    }

    @Override
    public LegoSetDTO findByName(String name) {
        return beanMappingService.mapTo(legoSetService.findByName(name), LegoSetDTO.class);
    }

    @Override
    public List<LegoSetDTO> findAll() {
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

}
