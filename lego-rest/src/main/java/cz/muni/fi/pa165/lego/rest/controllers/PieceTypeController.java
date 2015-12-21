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

    /**
     * Create pieceType with given parameters
     *
     * example: curl -X POST -H "Accept:application/json" -H
     * "Content-Type:application/json"
     * http://localhost:8080/pa165/rest/piecetypes/create -d '{ "name": "Arm",
     * "colors": [ "YELLOW", "GREEN" ] }' | python -m json.tool
     *
     * @param pieceTypeDTO DTO to be created
     * @return created PieceType
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final PieceTypeDTO createPieceType(@RequestBody PieceTypeDTO pieceTypeDTO) throws Exception {

        log.debug("rest createPieceType()");

        return pieceTypeFacade.create(pieceTypeDTO);
    }

    /**
     * Update pieceType with given parameters
     *
     * example: curl -X PUT -H "Accept:application/json" -H
     * "Content-Type:application/json"
     * http://localhost:8080/pa165/rest/piecetypes/7 -d '{ "name": "Arm",
     * "colors": [ "YELLOW" ] }' | python -m json.tool
     *
     * @param id id of updated pieceType
     * @param pieceTypeDTO DTO to be updated
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void updatePieceType(@PathVariable("id") long id, @Valid @RequestBody PieceTypeDTO pieceTypeDTO) throws Exception {

        log.debug("rest updatePieceType({})", id);

        pieceTypeFacade.update(pieceTypeDTO, id);
    }

    /**
     * Delete pieceType defined by its id
     *
     * example: curl -X DELETE http://localhost:8080/pa165/rest/piecetypes/7
     *
     * @param id of pieceType
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deletePieceType(@PathVariable("id") long id) throws Exception {

        log.debug("rest deletePieceType({})", id);

        pieceTypeFacade.delete(id);
    }

    /**
     * Get pieceType defined by its id. If identifier is not numeric it tries to
     * find LegoSet by its name.
     *
     * example: curl -H "Accept:application/json"
     * http://localhost:8080/pa165/rest/piecetypes/1 | python -m json.tool
     *
     * @param identifier Text or can be numeric. if it is numeric it tries to
     * find pieceType by id. If not it tries to find pieceType by name.
     * @return pieceType with given identifier
     */
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

    /**
     * Get all pieceTypes from the system
     *
     * example: curl -H "Accept:application/json"
     * http://localhost:8080/pa165/rest/piecetypes | python -m json.tool
     *
     * @return List of all pieceType
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public final List<PieceTypeDTO> getPieceTypes() {

        log.debug("rest getPieceTypes()");

        return pieceTypeFacade.findAll();
    }
}
