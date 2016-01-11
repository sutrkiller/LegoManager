package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.PieceTypeDTOGet;
import cz.muni.fi.pa165.lego.dto.PieceTypeDTOPut;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;
import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * SpringMVC Controller for managing piecetypes.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Controller
@RequestMapping("/piecetype")
public class PieceTypeController {

    private final static Logger log = LoggerFactory.getLogger(PieceTypeController.class);

    @Autowired
    private PieceTypeFacade pieceTypeFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        log.debug("list()");

        return loadListModel(model, new PieceTypeDTOPut());
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPieceType(@Valid @ModelAttribute("pieceTypeCreate") PieceTypeDTOPut pieceTypeCreate,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("create()", pieceTypeCreate);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("alert_danger", "Creation of PieceType " + pieceTypeCreate.getName() + " failed.");
            return loadListModel(model, pieceTypeCreate);
        }

        PieceTypeDTOGet created;
        try {
            created = pieceTypeFacade.create(pieceTypeCreate);
        } catch (LegoPersistenceException e) {
            model.addAttribute("alert_danger", "Creation of PieceType " + pieceTypeCreate.getName() + " failed. It already exists. Try to change It's name.");
            return loadListModel(model, pieceTypeCreate);
        }

        redirectAttributes.addFlashAttribute("alert_success", "PieceType " + created.getName() + " was created");
        redirectAttributes.addAttribute("id", created.getId());

        return "redirect:" + uriBuilder.path("/piecetype/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPieceType(Model model) {
        log.debug("new()");

        model.addAttribute("pieceTypeCreate", new PieceTypeDTOPut());
        model.addAttribute("allColors", Arrays.asList(Color.values()));

        return "piecetype/new";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("delete()", id);

        try {
            pieceTypeFacade.delete(id);
        } catch (DataAccessException e) {
            model.addAttribute("alert_danger", "Deletion of PieceType " + id + " failed. You have to remove all related Pieces with this PieceType before.");
            return loadListModel(model, new PieceTypeDTOPut());
        } catch (RuntimeException e) {
            model.addAttribute("alert_danger", "Deletion of PieceType " + id + " failed.");
            return loadListModel(model, new PieceTypeDTOPut());
        }

        redirectAttributes.addFlashAttribute("alert_success", "PieceType " + id + " was deleted");
        return "redirect:" + uriBuilder.path("/piecetype/list").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable long id, Model model) {

        log.debug("edit()", id);

        try {
            PieceTypeDTOGet pieceTypeDTOGet = pieceTypeFacade.findById(id);
            return loadEditModel(model, pieceTypeDTOGet, id);
        } catch (LegoPersistenceException e) {

            model.addAttribute("alert_danger", "Editation of PieceType can not be performed. PieceType does not exists.");
            return loadEditModel(model, new PieceTypeDTOPut(), id);
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id,
            @Valid @ModelAttribute("pieceTypeEdit") PieceTypeDTOPut pieceTypeEdit,
            BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("edit()", id);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }

            model.addAttribute("alert_danger", "Editation of PieceType " + id + " failed.");
            return loadEditModel(model, pieceTypeEdit, id);
        }

        try {
            PieceTypeDTOPut pieceTypePut = new PieceTypeDTOPut();
            pieceTypePut.setName(pieceTypeEdit.getName());
            pieceTypePut.setColors(pieceTypeEdit.getColors());

            pieceTypeFacade.update(pieceTypePut, id);
        } catch (EntityNotExistsException e) {

            model.addAttribute("alert_danger", "Editation of PieceType" + pieceTypeEdit.getName() + "failed. PieceType does not exists.");
            return loadEditModel(model, pieceTypeEdit, id);
        } catch (PersistenceException e) {

            model.addAttribute("alert_danger", "Editation of PieceType" + pieceTypeEdit.getName() + "failed. PieceType with this name already exists.");
            return loadEditModel(model, pieceTypeEdit, id);
        } catch (LegoPersistenceException e) {

            model.addAttribute("alert_danger", "Editation of PieceType" + pieceTypeEdit.getName() + "failed.");
            return loadEditModel(model, pieceTypeEdit, id);
        }

        redirectAttributes.addFlashAttribute("alert_success", "PieceType " + id + " was edited");

        return "redirect:" + uriBuilder.path("/piecetype/list").toUriString();
    }


    private String loadListModel(Model model, PieceTypeDTOPut create) {

        model.addAttribute("pieceTypeCreate", create);
        model.addAttribute("allColors", Arrays.asList(Color.values()));
        model.addAttribute("pieceTypes", pieceTypeFacade.findAll());

        return "piecetype/list";
    }

    private String loadEditModel(Model model, PieceTypeDTOPut edit, long pieceTypeEditId) {

        model.addAttribute("pieceTypeEditId", pieceTypeEditId);
        model.addAttribute("pieceTypeEdit", edit);
        model.addAttribute("allColors", Arrays.asList(Color.values()));

        return "piecetype/edit";
    }
    private String loadEditModel(Model model, PieceTypeDTOGet edit, long pieceTypeEditId) {

        model.addAttribute("pieceTypeEditId", pieceTypeEditId);
        model.addAttribute("pieceTypeEdit", edit);
        model.addAttribute("allColors", Arrays.asList(Color.values()));

        return "piecetype/edit";
    }


}
