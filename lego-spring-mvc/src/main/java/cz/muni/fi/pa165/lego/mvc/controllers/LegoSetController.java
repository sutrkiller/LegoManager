package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOPut;
import cz.muni.fi.pa165.lego.dto.ModelDTOGet;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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

    @Inject
    private MessageSource msg;

    @ModelAttribute("categories")
    public List<CategoryDTO> categories() {
        log.debug("categories()");
        return categoryFacade.findAll();
    }

    @ModelAttribute("models")
    public List<ModelDTOGet> models() {
        log.debug("models()");
        return modelFacade.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createLegoSet(@Valid @ModelAttribute("legosetCreate") LegoSetDTOPut legoSetCreate, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {

        log.debug("create()", legoSetCreate);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.create.fail", new String[]{legoSetCreate.getName()}, locale));
            return "legoset/new";
        }

        LegoSetDTOGet created;
        try {
            created = legoSetFacade.create(legoSetCreate);
        } catch (LegoPersistenceException e) {
            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.create.fail.exists", new String[]{legoSetCreate.getName()}, locale));
            return "legoset/new";
        }

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("legoset.create.success", new String[]{legoSetCreate.getName()}, locale));
        redirectAttributes.addAttribute("id", created.getId());

        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLegoSet(Model model) {
        log.debug("new()");

        model.addAttribute("legosetCreate", new LegoSetDTOPut());

        return "legoset/new";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateLegoSet(@PathVariable long id, @Valid @ModelAttribute("legosetChange") LegoSetDTOPut legosetChange, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Model model, Locale locale) {

        log.debug("update()", id);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }

            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.edit.fail", new String[]{legosetChange.getName()}, locale));
            model.addAttribute("legosetChangeModels", legoSetFacade.findById(id).getModels());
            return "legoset/edit";
        }

        try {
            legoSetFacade.update(legosetChange, id);
        } catch (EntityNotExistsException e) {

            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.edit.fail.notexists", new Long[]{id}, locale));
            model.addAttribute("legosetChangeModels", legoSetFacade.findById(id).getModels());
            return "legoset/edit";
        } catch (LegoPersistenceException e) {

            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.edit.fail.exists", new String[]{legosetChange.getName()}, locale));
            model.addAttribute("legosetChangeModels", legoSetFacade.findById(id).getModels());
            return "legoset/edit";
        } catch (PersistenceException e) {

            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.edit.fail", new String[]{legosetChange.getName()}, locale));
            model.addAttribute("legosetChangeModels", legoSetFacade.findById(id).getModels());
            return "legoset/edit";
        }

        redirectAttributes.addFlashAttribute("alert_success", "LegoSet " + id + " was updated");

        return "redirect:" + uriBuilder.path("/legoset/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String changeLegoSet(@PathVariable long id, Model model) {

        log.debug("change()", id);

        LegoSetDTOGet legoSetDTO = legoSetFacade.findById(id);

        LegoSetDTOPut legoSetChange = new LegoSetDTOPut();
        legoSetChange.setName(legoSetDTO.getName());
        legoSetChange.setPrice(legoSetDTO.getPrice());
        legoSetChange.setCategoryId(legoSetDTO.getCategory().getId());

        model.addAttribute("legosetChange", legoSetChange);
        model.addAttribute("legosetChangeModels", legoSetDTO.getModels());
        return "legoset/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLegoSet(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
                                UriComponentsBuilder uriBuilder, Locale locale) {

        log.debug("delete()", id);

        try {
            legoSetFacade.delete(id);
        } catch (DataAccessException e) {
            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.delete.fail", new Long[]{id}, locale));
            return "legoset/list";
        } catch (RuntimeException e) {
            model.addAttribute("alert_danger",
                    msg.getMessage("legoset.delete.fail", new Long[]{id}, locale));
            return "legoset/list";
        }

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("legoset.delete.success", new Long[]{id}, locale));
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

    @RequestMapping(value = "edit/{id}/addModel", method = RequestMethod.GET)
    public final String addModel(@PathVariable("id") long id, @RequestParam long modelId, RedirectAttributes redirectAttributes,
                                 UriComponentsBuilder uriBuilder, Locale locale) throws Exception {
        log.debug("addModel({})", id);

        legoSetFacade.addModel(id, modelId);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("legoset.addmodel.success", new Long[]{id, modelId}, locale));

        return "redirect:" + uriBuilder.path("/legoset/edit/" + id).toUriString();
    }

    @RequestMapping(value = "edit/{id}/removeModel", method = RequestMethod.GET)
    public final String removeModel(@PathVariable("id") long id, @RequestParam long modelId, RedirectAttributes redirectAttributes,
                                    UriComponentsBuilder uriBuilder, Locale locale) throws Exception {
        log.debug("removeModel({})", id);

        legoSetFacade.removeModel(id, modelId);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("legoset.removemodel.success", new Long[]{id, modelId}, locale));
        return "redirect:" + uriBuilder.path("/legoset/edit/" + id).toUriString();
    }

}
