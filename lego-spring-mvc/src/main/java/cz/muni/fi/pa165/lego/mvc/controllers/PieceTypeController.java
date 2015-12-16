package cz.muni.fi.pa165.lego.mvc.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.util.List;

/**
 * SpringMVC Controller for administering models.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 14.12.2015
 */

@Controller
@RequestMapping("/piece-type")
public class PieceTypeController {

    private final static Logger log = LoggerFactory.getLogger(PieceTypeController.class);

    @Autowired
    private PieceTypeFacade pieceTypeFacade;

}
