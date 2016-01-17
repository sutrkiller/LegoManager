package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.ModelDTOGet;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

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
    private ModelFacade modelFacade;

    @Inject
    private LegoSetFacade legoSetFacade;

    @RequestMapping("/models")
    public String models(Model model) {
        log.debug("models()");

        List<ModelDTOGet> allModels = modelFacade.findAll();
        List<CategoryDTO> categories = categoryFacade.findAll();

        model.addAttribute("models", allModels);
        model.addAttribute("categories", categories);

        return "store/models";
    }

    @RequestMapping("/models/{id}")
    public String modelDetail(Model model, @PathVariable("id") long id) {
        log.debug("modelDetail()");

        ModelDTOGet m = modelFacade.findById(id);

        model.addAttribute("model", m);

        return "store/modelDetail";
    }

    @RequestMapping("/legosets")
    public String legosets(Model model) {
        log.debug("legosets()");

        List<LegoSetDTOGet> allLegoSets = legoSetFacade.findAll();
        List<CategoryDTO> categories = categoryFacade.findAll();

        model.addAttribute("legosets", allLegoSets);
        model.addAttribute("categories", categories);

        return "store/legosets";
    }

    @RequestMapping("/legosets/{id}")
    public String legosetDetail(Model model, @PathVariable("id") long id) {
        log.debug("legosetDetail()");

        LegoSetDTOGet legoSet = legoSetFacade.findById(id);

        model.addAttribute("legoset", legoSet);

        return "store/legosetDetail";
    }
}
