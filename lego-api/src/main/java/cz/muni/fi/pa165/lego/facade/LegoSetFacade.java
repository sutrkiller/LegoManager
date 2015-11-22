package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.LegoSetCreateDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 * LegoSetFacade defines operations available within the Lego API to simplify the usage of the application.
 * 
 * @author Tobias <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
public interface LegoSetFacade {

    /**
     * Create the give legoSet
     * 
     * @param legoSetCreateDTO legoSet to be created
     * @return
     */
    public Long createLegoSet(LegoSetCreateDTO legoSetCreateDTO);

    /**
     * Delete the given category
     * 
     * @param legoSetId id of the legoSet
     */
    public void deleteLegoSet(Long legoSetId);

    /**
     * Get legoSet with given id.
     * 
     * @param id id of the legoSet
     * @return existing legoSet with the given id
     */
    public LegoSetDTO getLegoSetById(Long id);

    /**
     * Get legoSet with given name
     * @param name name of the legoSet
     * @return existing legoSet with the given name
     */
    public LegoSetDTO getLegoSetByName(String name);

    /**
     * Get all legoSets
     * @return list of existing legoSets
     */
    public List<LegoSetDTO> getAllLegoSets();
    
    /**
     * Add model to the legoSet
     * @param legoSetId id of the target legoSet
     * @param modelId id of the added model
     */
    public void addModel(Long legoSetId, Long modelId);

    /**
     * Remove model from the legoSet
     * @param legoSetId id of the target legoSet
     * @param modelId id of the removed model
     */
    public void removeModel(Long legoSetId, Long modelId);
    
    /**
     * Update price of the legoSet
     * @param legoSetId id of the target legoSet
     * @param newPrice new price of the legoSet
     */
    public void changePrice(Long legoSetId, BigDecimal newPrice);
    
    /**
     * Update category of the legoSet
     * @param legoSetId id of the target legoSet
     * @param categoryId id of the new category
     */
    public void changeCategory(Long legoSetId, Long categoryId);
    
}
