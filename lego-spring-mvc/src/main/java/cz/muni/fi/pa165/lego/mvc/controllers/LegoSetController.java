package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOPut;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
    public String createLegoSet(@Valid @ModelAttribute LegoSetDTOPut legoSetCreate, BindingResult bindingResult,
                                Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        log.debug("create()", legoSetCreate);
        
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}",fe);
            }
            model.addAttribute("alert_danger", "Creation of LegoSet " + legoSetCreate.getName() + " failed.");
            return "legoset/new";
        }
        
        LegoSetDTOGet created;
        try {
            created = legoSetFacade.create(legoSetCreate);
        } catch (LegoPersistenceException e) {
            model.addAttribute("alert_danger", "Creation of LegoSet " + legoSetCreate.getName() + " failed. It already exists. Try to change It's name.");
            return "legoset/new";
        }
        
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
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateLegoSet(@Valid @ModelAttribute("legosetChange") LegoSetDTOPut legosetChange, BindingResult bindingResult, @PathVariable("id") long id, 
                                RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,
                                Model model) {
        
        log.debug("update()",id);
        
        if(bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("alert_danger", "Update of LegoSet " + id + " failed.");
            model.addAttribute("legosetChange", legosetChange);
            model.addAttribute("legosetChangeId", id);
            return "legoset/edit";
        }
       
        try {
            LegoSetDTOPut legoSetPut = new LegoSetDTOPut();
            legoSetPut.setName(legosetChange.getName());
            legoSetPut.setPrice(legosetChange.getPrice());
            legoSetPut.setCategoryId(legosetChange.getCategoryId());
            
            legoSetFacade.update(legoSetPut, id);
        } catch (EntityNotExistsException e) {
            model.addAttribute("alert_danger", "Editation of LegoSet" + legosetChange.getName() + "failed. LegoSet does not exists.");
            model.addAttribute("legosetChange", legosetChange);
            model.addAttribute("legosetChangeId", id);
            return "legoset/edit";
        } catch (PersistenceException e) {
            model.addAttribute("alert_danger", "Editation of LegoSet" + legosetChange.getName() + "failed. LegoSet with this name already exists.");
            model.addAttribute("legosetChange", legosetChange);
            model.addAttribute("legosetChangeId", id);
            return "legoset/edit";
        } catch (LegoPersistenceException e) {
            model.addAttribute("alert_danger", "Editation of LegoSet" + legosetChange.getName() + "failed.");
            model.addAttribute("legosetChange", legosetChange);
            model.addAttribute("legosetChangeId", id);
            return "legoset/edit";
        }
        

        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + id + " was updated");
        
        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String changeLegoSet(@PathVariable long id, Model model) {

        log.debug("change()", id);
        
        try {
            model.addAttribute("legosetChange", legoSetFacade.findById(id));
            model.addAttribute("legosetChangeId", id);
            return "legoset/edit";
        } catch (LegoPersistenceException e) {
            model.addAttribute("alert_danger", "Editation of LegoSet can not be performed. LegoSet does not exists.");
            model.addAttribute("legosetChange", new LegoSetDTOPut());
            model.addAttribute("legosetChangeId",id);
            return "legoset/edit";
        }
        
        
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLegoSet(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        
        log.debug("delete()",id);
        
        try {
            legoSetFacade.delete(id);
        } catch (DataAccessException  e) {
            model.addAttribute("alert_danger", "Deletion of LegoSet " + id + " failed.");
            return "legoset/list";
        } catch (RuntimeException e) {
            model.addAttribute("alert_danger", "Deletion of LegoSet " + id + " failed.");
            return "legoset/list";
        }
        
        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + id + " was deleted");
        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewLegoSet(@PathVariable long id, Model model) {

        log.debug("view()", id);

        model.addAttribute("legoset", legoSetFacade.findById(id));

        return "legoset/view";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listLegoSets(Model model) {

        log.debug("list()");

        model.addAttribute("legosets", legoSetFacade.findAll());

        return "legoset/list";
    }
    
    @RequestMapping(value="edit/{id}/addModel", method = RequestMethod.GET)
    public final String addModel(@PathVariable("id") long id, @RequestParam long modelId, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) throws Exception {
        log.debug("addModel({})",id);
        
        legoSetFacade.addModel(id, modelId);
        
        redirectAttributes.addFlashAttribute("alert_success", "Model " + modelId + " was added to legoset");
        
        return "redirect:" + uriBuilder.path("/legoset/edit/"+id).toUriString();
    }
    
    @RequestMapping(value="edit/{id}/removeModel", method = RequestMethod.GET)
    public final String removeModel(@PathVariable("id") long id, @RequestParam long modelId, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) throws Exception {
        log.debug("removeModel({})",id);
        
        legoSetFacade.removeModel(id, modelId);
        
        redirectAttributes.addFlashAttribute("alert_success", "Model " + modelId + " was removed from legoset");
        return "redirect:" + uriBuilder.path("/legoset/edit/"+id).toUriString();
    }
    
    
}
