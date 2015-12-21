package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.dto.PieceTypeDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringMVC Controller for managing store.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 15.12.2015
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    final static Logger log = LoggerFactory.getLogger(StoreController.class);

    @Inject
    private CategoryFacade categoryFacade;

    @Inject
    private PieceTypeFacade pieceTypeFacade;

    @Inject
    private ModelFacade modelFacade;

    @Inject
    private LegoSetFacade legoSetFacade;

    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    public void setPieceTypeFacade(PieceTypeFacade pieceTypeFacade) {
        this.pieceTypeFacade = pieceTypeFacade;
    }

    public void setModelFacade(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public void setLegoSetFacade(LegoSetFacade legoSetFacade) {
        this.legoSetFacade = legoSetFacade;
    }

    @RequestMapping("/show")
    public String list(Model model) {
        log.debug("show()");

        List<CategoryDTO> allCategories = categoryFacade.findAll();
        List<PieceTypeDTO> allPieceTypes = pieceTypeFacade.findAll();
        List<ModelDTO> allModels = modelFacade.findAll();
        List<LegoSetDTOGet> allLegoSets = legoSetFacade.findAll();

        model.addAttribute("categories", allCategories);
        model.addAttribute("piecetypes", allPieceTypes);
        model.addAttribute("models", allModels);
        model.addAttribute("legosets", allLegoSets);

        return "store/show";
    }
}
