package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * SpringMVC Controller for administering categories.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 8.12.2015
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    private final static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Inject
    private CategoryFacade categoryFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCategory(@Valid @ModelAttribute("categoryCreate") CategoryDTO categoryDTO, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("create()", categoryDTO);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("alert_danger", "Creation of Category " + categoryDTO.getName() + " failed.");
            return "category/new";
        }

        try {
            categoryFacade.create(categoryDTO);
        } catch (LegoPersistenceException e) {
            model.addAttribute("alert_danger", "Creation of Category " + categoryDTO.getName() + " failed. It already exists. Try to change it's name.");
            return "category/new";
        }

        redirectAttributes.addFlashAttribute("alert_success", "Category " + categoryDTO.getName() + " was created");

        return "redirect:" + uriBuilder.path("/category/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {

        log.debug("new()");

        model.addAttribute("categoryCreate", new CategoryDTO());

        return "category/new";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCategory(@Valid @ModelAttribute("categoryChange") CategoryDTO categoryDTO, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Model model) {

        log.debug("update()");

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("alert_danger", "Editing of Category " + categoryDTO.getId() + " failed.");

            return "category/edit";
        }

        Long id = categoryDTO.getId();
        try {
            categoryFacade.update(categoryDTO);
        } catch (EntityNotExistsException e) {

            model.addAttribute("alert_danger", "Editation of Category " + categoryDTO.getName() + " failed. Category does not exists.");
            return "category/edit";
        } catch (PersistenceException e) {

            model.addAttribute("alert_danger", "Editation of Category " + categoryDTO.getName() + " failed. Category with this name already exists.");
            return "category/edit";
        } catch (LegoPersistenceException e) {

            model.addAttribute("alert_danger", "Editation of Category " + categoryDTO.getName() + " failed.");
            return "category/edit";
        }

        redirectAttributes.addFlashAttribute("alert_success", "Category " + id + " was edited");
        redirectAttributes.addAttribute("id", id);

        return "redirect:" + uriBuilder.path("/category/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String changeCategory(@PathVariable long id, Model model) {

        log.debug("edit()");

        model.addAttribute("categoryChange", categoryFacade.findById(id));

        return "category/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("delete()");

        try {
            categoryFacade.delete(id);
        } catch (DataAccessException e) {
            model.addAttribute("alert_danger", "Deletion of Category " + id + " failed. First you have to remove all related Models and Lego sets to this Category.");
            model.addAttribute("categories", categoryFacade.findAll());
            return "category/list";
        } catch (RuntimeException e) {
            model.addAttribute("alert_danger", "Deletion of Category " + id + " failed.");
            model.addAttribute("categories", categoryFacade.findAll());
            return "category/list";
        }

        redirectAttributes.addFlashAttribute("alert_success", "Category " + id + " was deleted");
        return "redirect:" + uriBuilder.path("/category/list").toUriString();
    }

    @RequestMapping(value = "/view/{identifier}", method = RequestMethod.GET)
    public String viewCategory(@PathVariable("identifier") String identifier, Model model) {

        log.debug("view()", identifier);
        CategoryDTO categoryDTO;

        if (identifier.matches("^-?\\d+$")) {
            categoryDTO = categoryFacade.findById(Long.parseLong(identifier));
        } else {
            categoryDTO = categoryFacade.findByName(identifier);
        }

        model.addAttribute("category", categoryDTO);

        return "category/view";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCategories(Model model) {

        log.debug("list()");

        model.addAttribute("categories", categoryFacade.findAll());

        return "category/list";
    }
}
