package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
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

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * SpringMVC Controller for administering models.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 14.12.2015
 */

@Controller
@RequestMapping("/model")
public class ModelController {

    private final static Logger log = LoggerFactory.getLogger(ModelController.class);

    @Inject
    private ModelFacade modelFacade;

    public String createModel(@Valid @ModelAttribute("modelCreate") ModelDTO modelDTO, BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create", modelDTO);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "model/new";
        }

        Long id = modelFacade.create(modelDTO);

        redirectAttributes.addFlashAttribute("alert_success", "Model " + id + " was created");
        redirectAttributes.addAttribute("id", id);

        return "redirect" + uriBuilder.path("/model/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newModel(Model model) {

        log.debug("new()");

        model.addAttribute("modelCreate", new ModelDTO());

        return "model/new";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateModel(@Valid @ModelAttribute ModelDTO modelDTO, BindingResult bindingResult,
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
            return "model/change";
        }

        modelFacade.update(modelDTO);

        Long id = modelDTO.getId();
        redirectAttributes.addFlashAttribute("alert_success", "Model " + id + " was updated");

        return "redirect:" + uriBuilder.path("/model/list").toUriString();
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String changeModel(Model model) {

        log.debug("change()");

        model.addAttribute("modelChange", new ModelDTO());

        return "model/change";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteModel(@PathVariable long id, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("delete()");

        modelFacade.delete(id);

        redirectAttributes.addFlashAttribute("alert_success", "Model " + id + " was deleted");

        return "redirect:" + uriBuilder.path("/model/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewModel(@PathVariable long id, Model model) {

        log.debug("view()", id);

        model.addAttribute("model", modelFacade.findById(id));

        return "model/view";
    }

    @RequestMapping(value = "/view/{name}", method = RequestMethod.GET)
    public String viewModel(@PathVariable String name, Model model) {

        log.debug("view()", name);

        model.addAttribute("model", modelFacade.findByName(name));

        return "model/view";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listModels(Model model) {

        log.debug("list()");

        model.addAttribute("models", modelFacade.findAll());

        return "model/list";
    }

}
