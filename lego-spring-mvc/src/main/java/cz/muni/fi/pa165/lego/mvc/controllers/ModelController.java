package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.*;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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

    @Autowired
    private ModelFacade modelFacade;

    @Autowired
    private CategoryFacade categoryFacade;

    @Autowired
    private PieceTypeFacade pieceTypeFacade;

    @Autowired
    private MessageSource msg;

    @ModelAttribute("categories")
    public List<CategoryDTO> categories() {
        log.debug("categories()");
        return categoryFacade.findAll();
    }

    @ModelAttribute("pieceTypes")
    public List<PieceTypeDTOGet> pieceTypes() {
        log.debug("pieceTypes()");
        return pieceTypeFacade.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createModel(@Valid @ModelAttribute("modelCreate") ModelDTOPut modelDTO, BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {

        log.debug("create", modelDTO);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("alert_danger",
                    msg.getMessage("model.create.fail", new String[]{modelDTO.getName()}, locale));
            return "model/new";
        }

        Long id = modelFacade.create(modelDTO);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("model.create.success", new String[]{modelDTO.getName()}, locale));
        redirectAttributes.addAttribute("id", id);

        return "redirect:" + uriBuilder.path("/model/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newModel(Model model) {

        log.debug("new()");

        model.addAttribute("modelCreate", new ModelDTOPut());

        return "model/new";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editModel(@PathVariable long id, @Valid @ModelAttribute("modelChange") ModelDTOPut modelDTO, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Model model, Locale locale) {

        log.debug("edit()");

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            redirectAttributes.addFlashAttribute("alert_danger",
                    msg.getMessage("model.edit.fail", new String[]{modelDTO.getName()}, locale));
            model.addAttribute("pieces", modelFacade.findById(id).getPieces());
            return "model/edit";
        }

        modelFacade.update(modelDTO, id);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("model.edit.success", new String[]{modelDTO.getName()}, locale));

        return "redirect:" + uriBuilder.path("/model/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String changeModel(Model model, @PathVariable long id) {

        log.debug("edit()");

        ModelDTOGet modelDTO = modelFacade.findById(id);

        ModelDTOPut createModelDTO = new ModelDTOPut();
        createModelDTO.setName(modelDTO.getName());
        createModelDTO.setPrice(modelDTO.getPrice());
        createModelDTO.setAgeLimit(modelDTO.getAgeLimit());
        createModelDTO.setCategoryId(modelDTO.getCategory().getId());

        model.addAttribute("modelChange", createModelDTO);
        model.addAttribute("pieces", modelDTO.getPieces());

        return "model/edit";
    }


    @RequestMapping(value = "/edit/{id}/addPiece", method = RequestMethod.GET)
    public String changeModel(Model model, @PathVariable long id, @RequestParam long pieceTypeId) {

        log.debug("addPiece()");

        PieceDTOPut pieceDTOPut = new PieceDTOPut();
        pieceDTOPut.setPieceTypeId(pieceTypeId);

        model.addAttribute("piece",pieceDTOPut);
        model.addAttribute("pieceType", pieceTypeFacade.findById(pieceTypeId));
        model.addAttribute("model", modelFacade.findById(id));

        return "model/addPiece";
    }


    @RequestMapping(value = "/edit/{id}/addPiece", method = RequestMethod.POST)
    public String changeModel(Model model, @PathVariable long id, @Valid @ModelAttribute("piece") PieceDTOPut pieceDTOPut,
                              BindingResult bindingResult, UriComponentsBuilder uriBuilder,
                              RedirectAttributes redirectAttributes, Locale locale) {

        log.debug("addPiece()");

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            redirectAttributes.addFlashAttribute("alert_danger",
                    msg.getMessage("model.addpiece.fail", new Object[]{id,pieceDTOPut.getPieceTypeId()}, locale));
            model.addAttribute("piece",pieceDTOPut);
            model.addAttribute("pieceType", pieceTypeFacade.findById(pieceDTOPut.getPieceTypeId()));
            model.addAttribute("model", modelFacade.findById(id));
            return "model/addPiece";
        }

        modelFacade.addPiece(id, pieceDTOPut);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("model.addpiece.success", new Object[]{id,pieceDTOPut.getPieceTypeId()}, locale));
        return "redirect:" + uriBuilder.path("/model/edit/"+id).toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteModel(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
                              UriComponentsBuilder uriBuilder, Locale locale) {

        log.debug("delete()");

        ModelDTOGet modelDTO = modelFacade.findById(id);

        modelFacade.delete(id);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("model.delete.success", new Object[]{modelDTO.getName()}, locale));

        return "redirect:" + uriBuilder.path("/model/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewModel(@PathVariable long id, Model model) {

        log.debug("view()", id);

        model.addAttribute("model", modelFacade.findById(id));

        return "model/view";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listModels(Model model) {

        log.debug("list()");

        model.addAttribute("models", modelFacade.findAll());

        return "model/list";
    }

    @RequestMapping(value = "/{id}/piece", method = RequestMethod.GET)
    public String listPieces(@PathVariable long id, Model model) {
        log.debug("list()");

        model.addAttribute("model", modelFacade.findById(id));

        return "model/piece";
    }


    @RequestMapping(value = "/{id}/deletePiece/{pieceId}", method = RequestMethod.GET)
    public String deletePiece(@PathVariable long id, @PathVariable long pieceId, RedirectAttributes redirectAttributes,
                              UriComponentsBuilder uriBuilder, Locale locale) {
        modelFacade.removePiece(id, pieceId);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("model.removepiece.success", new Object[]{id, pieceId}, locale));

        return "redirect:" + uriBuilder.path("/model/edit/" + id).toUriString();
    }

    @RequestMapping(value = "/discount/{id}", method = RequestMethod.POST)
    public String setDiscount(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
                              UriComponentsBuilder uriBuilder, Locale locale) {

        ModelDTOGet modelDTO = modelFacade.findById(id);

        modelFacade.setFiftyPercentDiscount(id);

        redirectAttributes.addFlashAttribute("alert_success",
                msg.getMessage("model.discount.success", new Object[]{modelDTO.getName()}, locale));

        return "redirect:" + uriBuilder.path("/model/list").toUriString();
    }


}
