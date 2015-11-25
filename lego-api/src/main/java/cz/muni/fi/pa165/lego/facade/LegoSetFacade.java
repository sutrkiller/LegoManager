package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.LegoSetDTO;
import java.util.List;

/**
 * LegoSetFacade defines operations available within the Lego API to simplify the usage of the application.
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
public interface LegoSetFacade {

    /**
     * Create the give legoSet
     * 
     * @param legoSetDTO legoSet to be created
     * @return
     */
    public Long createLegoSet(LegoSetDTO legoSetDTO);

    /**
     * Delete the given legoSet.
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
     * Update the legoSet. Update does not apply to id.
     * @param updated LegoSetDTO with updated parameters.
     */
    public void updateLegoSet(LegoSetDTO updated);
    
    
    
}
