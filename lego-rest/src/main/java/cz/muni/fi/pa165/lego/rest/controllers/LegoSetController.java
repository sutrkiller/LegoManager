package cz.muni.fi.pa165.lego.rest.controllers;

import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import cz.muni.fi.pa165.lego.dto.LegoSetDTOPut;
import cz.muni.fi.pa165.lego.facade.LegoSetFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final @ResponseBody
    LegoSetDTOGet createLegoSet(@Valid @RequestBody LegoSetDTOPut legoSetDTO) throws Exception {
        log.debug("rest createLegoSet()");

        return legoSetFacade.create(legoSetDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void updateLegoSet(@PathVariable("id") long id, @Valid @RequestBody LegoSetDTOPut legoSetDTO) throws Exception {
        log.debug("rest updateLegoSet({})", id);

        legoSetFacade.update(legoSetDTO, id);
    }

    @RequestMapping(value = "/addModel/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void addModel(@PathVariable("id") long id, @RequestParam long modelId) throws Exception {
        log.debug("rest addModel({})", id);

        legoSetFacade.addModel(id, modelId);
    }

    @RequestMapping(value = "/removeModel/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeModel(@PathVariable("id") long id, @RequestParam long modelId) throws Exception {
        log.debug("rest removeModel({})", id);

        legoSetFacade.removeModel(id, modelId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteLegoSet(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteLegoSet({})", id);

        legoSetFacade.delete(id);
    }

    @RequestMapping(value = "/{identifier}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final @ResponseBody
    LegoSetDTOGet getLegoSet(@PathVariable("identifier") String identifier) throws Exception {
        log.debug("rest getLegoSet({})", identifier);

        LegoSetDTOGet legoSetDTO;

        // is integer?
        if (identifier.matches("^-?\\d+$")) {

            legoSetDTO = legoSetFacade.findById(Long.parseLong(identifier));

        } else {

            legoSetDTO = legoSetFacade.findByName(identifier);

        }


        if (legoSetDTO == null) {
            // TODO throw new exception
            throw new Exception("reason..");
        }

        return legoSetDTO;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<LegoSetDTOGet> getLegoSets() {
        log.debug("rest getLegoSets()");

        return legoSetFacade.findAll();
    }

}
