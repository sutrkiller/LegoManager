package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
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
    public String createCategory(@Valid @ModelAttribute CategoryDTO categoryDTO, BindingResult bindingResult,
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
            return "category/new";
        }

        Long id = categoryFacade.create(categoryDTO);

        redirectAttributes.addFlashAttribute("alert_success", "Category " + id + " was created");
        redirectAttributes.addAttribute("id", id);

        return "redirect:" + uriBuilder.path("/category/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {

        log.debug("new()");

        model.addAttribute("categoryCreate", new CategoryDTO());

        return "category/new";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateCategory(@Valid @ModelAttribute CategoryDTO categoryDTO, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,
            Model model) {

        log.debug("update()");

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "category/change";
        }

        categoryFacade.update(categoryDTO);

        Long id = categoryDTO.getId();
        redirectAttributes.addFlashAttribute("alert_success", "Category " + id + " was updated");

        return "redirect:" + uriBuilder.path("/category/list").toUriString();
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String changeCategory(Model model) {

        log.debug("change()");

        model.addAttribute("categoryChange", new CategoryDTO());

        return "category/change";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable long id, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("delete()");

        categoryFacade.delete(id);

        redirectAttributes.addFlashAttribute("alert_success", "Category " + id + " was deleted");

        return "redirect:" + uriBuilder.path("/category/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewCategory(@PathVariable long id, Model model) {

        log.debug("view()", id);

        model.addAttribute("category", categoryFacade.findById(id));

        return "category/view";
    }

    /*@RequestMapping(value = "/view/{name}", method = RequestMethod.GET, params = {"name"})
    public String viewCategory(@PathVariable String name, Model model) {

        log.debug("view()", name);

        model.addAttribute("category", categoryFacade.findByName(name));

        return "category/view";
    }*/

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCategories(Model model) {

        log.debug("list()");

        model.addAttribute("categories", categoryFacade.findAll());

        return "category/list";
    }
}
