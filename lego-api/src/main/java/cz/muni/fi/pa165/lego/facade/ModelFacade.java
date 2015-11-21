package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.ModelCreateDTO;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.dto.PieceCreateDTO;
import cz.muni.fi.pa165.lego.enums.Color;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
public interface ModelFacade {

    Long createModel(ModelCreateDTO model);

    ModelDTO findModelById(Long id);
    ModelDTO findModelByName(String name);
    List<ModelDTO> findAllModels();
    List<ModelDTO> findModelsByCategory(String categoryName);

    void updateName(String newName);
    void updateAgeLimit(Byte newAgeLimit);
    void updatePrice(BigDecimal newPrice);
    void updateCategory(Long categoryId);

    void addPiece(Long modelId, PieceCreateDTO piece);
    void removePiece(Long modelId, Long pieceId);

    void deleteModel(Long modelId);

}
