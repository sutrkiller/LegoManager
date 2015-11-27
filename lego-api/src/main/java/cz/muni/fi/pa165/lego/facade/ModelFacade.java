package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.dto.PieceDTO;

import java.util.List;

/**
 * ModelFacade defines operations available within the Lego API to simplify
 * the usage of the application.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public interface ModelFacade {

    /**
     * Create the given model
     *
     * @param model model to be created. It shouldn't have setted id.
     * @return id of the created model
     */
    Long create(ModelDTO model);

    /**
     * Update the given model.
     *
     * @param model to be updated. It should have setted id.
     */
    void update(ModelDTO model);

    /**
     * Delete the model.
     *
     * @param modelId id of the category.
     */
    void delete(Long modelId);

    /**
     * Get model with the given ID.
     *
     * @param id id of the model
     * @return existing model with given id
     */
    ModelDTO findById(Long id);

    /**
     * Get model with the given name.
     *
     * @param name name of the model
     * @return existing model with given name
     */
    ModelDTO findByName(String name);

    /**
     * Get all existing models.
     *
     * @return list of existing models
     */
    List<ModelDTO> findByName();

    /**
     * Get all existing models in given category.
     *
     * @param categoryId id of existing category.
     * @return list of existing categories
     */
    List<ModelDTO> findByCategory(Long categoryId);

    /**
     * Add given piece to the model defined by id. It also create piece and than add it to the model.
     *
     * @param modelId id of the model. It has to exist.
     * @param piece piece to be created and added to model.
     */
    void addPiece(Long modelId, PieceDTO piece);

    /**
     * Remove the piece defined by its id from the model defined by its id. It also delete piece at all.
     *
     * @param modelId id of the model. It has to exist.
     * @param pieceId id of the piece to be removed and deleted.
     */
    void removePiece(Long modelId, Long pieceId);

}
