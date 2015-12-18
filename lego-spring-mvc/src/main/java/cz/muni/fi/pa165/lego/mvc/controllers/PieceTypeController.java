package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.dto.PieceTypeDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SpringMVC Controller for managing piecetypes.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 14.12.2015
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

        fillModel(model, new PieceTypeDTO(), pieceTypeFacade.findAll());

        return "piecetype/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("pieceTypeCreate") PieceTypeDTO pieceTypeCreate,
                         BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("create()", pieceTypeCreate);

        log.error(pieceTypeCreate.toString());

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }

            fillModel(model, pieceTypeCreate, new ArrayList<PieceTypeDTO>());

            model.addAttribute("alert_danger", "Creation of PieceType " + pieceTypeCreate.getName() + " failed.");

            return "piecetype/list";
        }

        try {
            Long id = pieceTypeFacade.create(pieceTypeCreate);
        } catch (EntityAlreadyExistsException e) {

            fillModel(model, pieceTypeCreate, new ArrayList<PieceTypeDTO>());

            model.addAttribute("alert_danger", "Creation of PieceType " + pieceTypeCreate.getName() + " failed.");

            return "piecetype/list";
        }

        redirectAttributes.addFlashAttribute("alert_success", "PieceType " + pieceTypeCreate.getName() + " was created");

        return "redirect:" + uriBuilder.path("/piecetype/list").toUriString();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("delete()", id);

        pieceTypeFacade.delete(id);

        redirectAttributes.addFlashAttribute("alert_success", "PieceType " + id + " was deleted");

        return "redirect:" + uriBuilder.path("/piecetype/list").toUriString();
    }



    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("pieceTypes") List<PieceTypeDTO> pieceTypes, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("edit()", id);

        log.error(pieceTypes.toString());

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }

            fillModel(model, new PieceTypeDTO(), pieceTypes);

            model.addAttribute("alert_danger", "Editation of PieceType " + id + " failed.");

            return "piecetype/list";
        }

//        pieceTypeDTO.setId(id);
//        pieceTypeFacade.update(pieceTypeDTO);

        redirectAttributes.addFlashAttribute("alert_success", "PieceType " + id + " was edited");

        return "redirect:" + uriBuilder.path("/piecetype/list").toUriString();
    }

    private void fillModel(Model model, PieceTypeDTO pieceTypeCreate, List<PieceTypeDTO> pieceTypes) {
        model.addAttribute("pieceTypeForm",pieceTypeCreate);

        model.addAttribute("allColors", Arrays.asList(Color.values()));

        model.addAttribute("pieceTypes", pieceTypes);
    }

}
