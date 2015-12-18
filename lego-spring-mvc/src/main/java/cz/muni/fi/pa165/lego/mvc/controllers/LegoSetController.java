package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOPut;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * SpringMVC controller for administering LegoSets
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 13.12.2015
 */

@Controller
@RequestMapping("/legoset")
public class LegoSetController {
    private final static Logger log = LoggerFactory.getLogger(LegoSetController.class);

    @Inject
    private LegoSetFacade legoSetFacade;
    
    @Inject
    private CategoryFacade categoryFacade;
    
    @Inject
    private ModelFacade modelFacade;
    
    @ModelAttribute("categories")
    public List<CategoryDTO> categories() {
        log.debug("categories()");
        return categoryFacade.findAll();
    }
    
    @ModelAttribute("models")
    public List<ModelDTO> models() {
        log.debug("models()");
        return modelFacade.findAll();
    }
    
   
    
    @RequestMapping(value = "/create", method =RequestMethod.POST)
    public String createLegoSet(@Valid @ModelAttribute LegoSetDTOPut legoSetDTO, BindingResult bindingResult,
                                Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        log.debug("create()", legoSetDTO);
        
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}",fe);
            }
            return "legoset/new";
        }
        
        LegoSetDTOGet created = legoSetFacade.create(legoSetDTO);
        
        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + created.getName() + " was created");
        redirectAttributes.addAttribute("id", created.getId());
        
        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    } 
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLegoSet(Model model) {
        log.debug("new()");
        
        model.addAttribute("legoSetCreate", new LegoSetDTOPut());
        
        return "legoset/new";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateLegoSet(@Valid @ModelAttribute LegoSetDTOPut legoSetDTO, @PathVariable("id") long id, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,
                                Model model) {
        
        log.debug("update()");
        
        if(bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "legoset/change/"+id;
        }
        
        legoSetFacade.update(legoSetDTO, id);

        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + id + " was updated");
        
        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    }
    
    @RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
    public String changeLegoSet(@PathVariable long id, Model model) {

        log.debug("change()");

        model.addAttribute("legosetChange", legoSetFacade.findById(id));

        return "legoset/change";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteLegoSet(@PathVariable long id, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("delete()");

        legoSetFacade.delete(id);

        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + id + " was deleted");

        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewLegoSet(@PathVariable long id, Model model) {

        log.debug("view()", id);

        model.addAttribute("legoset", legoSetFacade.findById(id));

        return "legoset/view";
    }
    
   /* @RequestMapping(value = "/view/{name}", method = RequestMethod.GET)
    public String viewLegoSet(@PathVariable String name, Model model) {

        log.debug("view()", name);

        model.addAttribute("legoset", legoSetFacade.findByName(name));

        return "legoset/view";
    }*/

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listLegoSets(Model model) {

        log.debug("list()");

        model.addAttribute("legosets", legoSetFacade.findAll());

        return "legoset/list";
    }
    
    @RequestMapping(value="/{id}/addModel", method = RequestMethod.POST)
    public final String addModel(@PathVariable("id") long id, @RequestParam long modelId, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) throws Exception {
        log.debug("addModel({})",id);
        
        legoSetFacade.addModel(id, modelId);
        
        redirectAttributes.addFlashAttribute("alert_success", "Model " + modelId + " was added to legoset");
        return "redirect:" + uriBuilder.path("/legoset/change/"+id).toUriString();
    }
    
    @RequestMapping(value="/{id}/removeModel", method = RequestMethod.POST)
    public final String removeModel(@PathVariable("id") long id, @RequestParam long modelId, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) throws Exception {
        log.debug("removeModel({})",id);
        
        legoSetFacade.removeModel(id, modelId);
        
        redirectAttributes.addFlashAttribute("alert_success", "Model " + modelId + " was removed from legoset");
        return "redirect:" + uriBuilder.path("/legoset/change/"+id).toUriString();
    }
}
