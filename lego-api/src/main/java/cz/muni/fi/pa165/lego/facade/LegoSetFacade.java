package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOPut;

import java.util.List;

/**
 * LegoSetFacade defines operations available within the Lego API to simplify the usage of the application.
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 22.11.2015
 */
public interface LegoSetFacade {

    /**
     * Create the given legoSet with empty set of models.
     *
     * @param legoSetDTO legoSet to be created
     * @return created legoSet
     */
    public LegoSetDTOGet create(LegoSetDTOPut legoSetDTO);

    /**
     * Update the legoSet. Update does not apply to id.
     *
     * @param updated LegoSetDTOPut with parameters.
     * @param id identifier of updated legoset.
     */
    public void update(LegoSetDTOPut updated, Long id);

    /**
     * Delete the given legoSet.
     *
     * @param legoSetId id of the legoSet
     */
    public void delete(Long legoSetId);

    /**
     * Get legoSet with given id.
     *
     * @param id id of the legoSet
     * @return existing legoSet with the given id
     */
    public LegoSetDTOGet findById(Long id);

    /**
     * Get legoSet with given name
     *
     * @param name name of the legoSet
     * @return existing legoSet with the given name
     */
    public LegoSetDTOGet findByName(String name);

    /**
     * Get all legoSets
     *
     * @return list of existing legoSets
     */
    public List<LegoSetDTOGet> findAll();

    /**
     * Add model to the legoSet
     *
     * @param legoSetId id of the target legoSet
     * @param modelId   id of the added model
     */
    public void addModel(Long legoSetId, Long modelId);

    /**
     * Remove model from the legoSet
     *
     * @param legoSetId id of the target legoSet
     * @param modelId   id of the removed model
     */
    public void removeModel(Long legoSetId, Long modelId);

}
