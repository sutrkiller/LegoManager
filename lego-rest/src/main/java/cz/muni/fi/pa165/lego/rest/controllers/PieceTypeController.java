package cz.muni.fi.pa165.lego.rest.controllers;

import cz.muni.fi.pa165.lego.dto.PieceTypeDTO;
import cz.muni.fi.pa165.lego.facade.PieceTypeFacade;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for PieceTypes.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 8.12.2015
 */
@RestController
@RequestMapping("/piecetypes")
public class PieceTypeController {

    private final static Logger log = LoggerFactory.getLogger(PieceTypeController.class);

    @Inject
    private PieceTypeFacade pieceTypeFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final PieceTypeDTO createPieceType(@RequestBody PieceTypeDTO pieceTypeDTO) throws Exception {

        log.debug("rest createPieceType()");

        return pieceTypeFacade.create(pieceTypeDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void updatePieceType(@PathVariable("id") long id, @Valid @ModelAttribute PieceTypeDTO pieceTypeDTO) throws Exception {

        log.debug("rest updatePieceType({})", id);

        pieceTypeFacade.update(pieceTypeDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deletePieceType(@PathVariable("id") long id) throws Exception {

        log.debug("rest deletePieceType({})", id);

        pieceTypeFacade.delete(id);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public final PieceTypeDTO getPieceType(@PathVariable("identifier") String identifier) throws Exception {

        log.debug("rest getPieceType({})", identifier);

        PieceTypeDTO pieceTypeDTO;

        if (identifier.matches("^-?\\d+$")) {
            pieceTypeDTO = pieceTypeFacade.findById(Long.parseLong(identifier));
        } else {
            pieceTypeDTO = pieceTypeFacade.findByName(identifier);
        }

        return pieceTypeDTO;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public final List<PieceTypeDTO> getPieceTypes() {

        log.debug("rest getPieceTypes()");

        return pieceTypeFacade.findAll();
    }
}
