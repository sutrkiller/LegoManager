package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.LegoSetDTO;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
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
    
    @RequestMapping(value = "/create", method =RequestMethod.POST)
    public String createLegoSet(@Valid @ModelAttribute LegoSetDTO legoSetDTO, BindingResult bindingResult,
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
        
        Long id = legoSetFacade.create(legoSetDTO);
        
        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + id + " was created");
        redirectAttributes.addAttribute("id", id);
        
        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    } 
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLegoSet(Model model) {
        log.debug("new()");
        
        model.addAttribute("legoSetCreate", new LegoSetDTO());
        
        return "legoset/new";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateLegoSet(@Valid @ModelAttribute LegoSetDTO legoSetDTO, BindingResult bindingResult,
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
            return "legoset/change";
        }
        
        legoSetFacade.update(legoSetDTO);
        
        Long id = legoSetDTO.getId();
        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + id + " was updated");
        
        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    }
    
    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String changeLegoSet(Model model) {

        log.debug("change()");

        model.addAttribute("legoSetChange", new LegoSetDTO());

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
}
