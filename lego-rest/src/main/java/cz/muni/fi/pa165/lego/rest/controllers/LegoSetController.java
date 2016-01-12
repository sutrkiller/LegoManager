package cz.muni.fi.pa165.lego.rest.controllers;

import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOPut;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 * REST Controller for Lego sets.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@RestController
@RequestMapping("/lego-sets")
public class LegoSetController {

    private final static Logger log = LoggerFactory.getLogger(LegoSetController.class);

    @Inject
    private LegoSetFacade legoSetFacade;

    /**
     * Create legoSet with given parametres
     *
     * example: curl -X POST -H "Accept:application/json" -H
     * "Content-Type:application/json"
     * http://localhost:8080/pa165/rest/lego-sets/create -d
     * '{"name":"Bionicle","price":120,"categoryId":1}' | python -m json.tool
     *
     * @param legoSetDTO DTO to be created
     * @return created LegoSet
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public final LegoSetDTOGet createLegoSet(@Valid @RequestBody LegoSetDTOPut legoSetDTO) {
        log.debug("rest createLegoSet()");

        return legoSetFacade.create(legoSetDTO);
    }

    /**
     * Update LegoSet with given id.
     *
     * example: curl -X PUT -H "Content-Type:application/json"
     * http://localhost:8080/pa165/rest/lego-sets/1 -d '{"name":"Dark
     * side","price":150,"categoryId":2}'
     *
     * @param id id of updated LegoSet
     * @param legoSetDTO new data
     * @return LegoSetDTOGet
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final LegoSetDTOGet updateLegoSet(@PathVariable("id") long id, @Valid @RequestBody LegoSetDTOPut legoSetDTO) {
        log.debug("rest updateLegoSet({})", id);

        legoSetFacade.update(legoSetDTO, id);
        return legoSetFacade.findById(id);
    }

    /**
     * add model with given modelId to legoset defined by id
     *
     * example: curl -X PUT
     * http://localhost:8080/pa165/rest/lego-sets/1/addModel?modelId=1
     *
     * @param id id of LegoSet
     * @param modelId id of model
     * @return
     */
    @RequestMapping(value = "/{id}/addModel", method = RequestMethod.PUT)
    public final LegoSetDTOGet addModel(@PathVariable("id") long id, @RequestParam long modelId) {
        log.debug("rest addModel({})", id);

        try {
            legoSetFacade.addModel(id, modelId);
        } catch (LegoServiceException ex) {
            log.warn("Cannot add model to legoset, model has already been added.", ex);
        }
        return legoSetFacade.findById(id);
    }

    /**
     * remove model with given modelId from legoset defined by id
     *
     * example: curl -X PUT
     * http://localhost:8080/pa165/rest/lego-sets/1/removeModel?modelId=1
     *
     * @param id id of LegoSet
     * @param modelId id of model
     * @return
     */
    @RequestMapping(value = "/{id}/removeModel", method = RequestMethod.PUT)
    public final LegoSetDTOGet removeModel(@PathVariable("id") long id, @RequestParam long modelId) {
        log.debug("rest removeModel({})", id);

        try {
            legoSetFacade.removeModel(id, modelId);
        } catch (LegoServiceException ex) {
            log.warn("Cannot remove model from legoset, model has already been deleted.", ex);
        }
        return legoSetFacade.findById(id);
    }

    /**
     * Delete LegoSet defined by its id
     *
     * example: curl -X DELETE http://localhost:8080/pa165/rest/lego-sets/1
     *
     * @param id of legoset
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteLegoSet(@PathVariable("id") long id) {
        log.debug("rest deleteLegoSet({})", id);

        legoSetFacade.delete(id);
    }

    /**
     * get LegoSet defined by its id. If identifier is not numeric it tries to
     * find LegoSet by its name.
     *
     * example: curl -H "Accept:application/json"
     * http://localhost:8080/pa165/rest/lego-sets/1 | python -m json.tool
     *
     * @param identifier Text or can be numeric. if it is numeric it tries to
     * find LegoSet by id. If not it tries to find LegoSet by name.
     * @return Legoset with given identifier
     */
    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public final LegoSetDTOGet getLegoSet(@PathVariable("identifier") String identifier) {
        log.debug("rest getLegoSet({})", identifier);

        LegoSetDTOGet legoSetDTO;

        // is integer?
        if (identifier.matches("^-?\\d+$")) {

            legoSetDTO = legoSetFacade.findById(Long.parseLong(identifier));

        } else {

            legoSetDTO = legoSetFacade.findByName(identifier);

        }

        return legoSetDTO;
    }

    /**
     * get all legosets from the system
     *
     * example: curl -H "Accept:application/json"
     * http://localhost:8080/pa165/rest/lego-sets | python -m json.tool
     *
     * @return List of all legosets
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public final List<LegoSetDTOGet> getLegoSets() {
        log.debug("rest getLegoSets()");

        return legoSetFacade.findAll();
    }

    /**
     * Handles Exception thrown during processing REST actions
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, 
            reason = "Cannot perform requested operation on lego sets. "
                    + "If you call create operation, lego set may already exist. "
                    + "If you call delete operation, lego set may already been removed. "
                    + "If you call get operation, be sure that lego set is already in the system.")
    @ExceptionHandler(Exception.class)
    public void notFound() {
    }
}
