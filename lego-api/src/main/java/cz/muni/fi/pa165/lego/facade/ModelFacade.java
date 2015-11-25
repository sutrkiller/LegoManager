package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.ModelCreateDTO;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.dto.PieceDTO;

import java.util.List;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public interface ModelFacade {

    Long createModel(ModelDTO model);

    ModelDTO findModelById(Long id);
    ModelDTO findModelByName(String name);
    List<ModelDTO> findAllModels();
    List<ModelDTO> findModelsByCategory(Long categoryId);

    void update(ModelDTO model);

    void addPiece(Long modelId, PieceDTO piece);
    void removePiece(Long modelId, Long pieceId);

    void deleteModel(Long modelId);

}
