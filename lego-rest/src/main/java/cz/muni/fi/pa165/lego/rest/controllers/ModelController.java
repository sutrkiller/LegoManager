package cz.muni.fi.pa165.lego.rest.controllers;

import cz.muni.fi.pa165.lego.dto.ModelCreateDTO;
import cz.muni.fi.pa165.lego.dto.ModelDTO;
import cz.muni.fi.pa165.lego.facade.ModelFacade;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST Controller for Models.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 15.12.2015
 */
@RestController
@RequestMapping("/models")
public class ModelController {

    private final static Logger log = LoggerFactory.getLogger(ModelController.class);


    @Inject
    private ModelFacade modelFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ModelDTO createModel(@RequestBody ModelCreateDTO modelDTO) throws Exception {

        log.debug("rest createModel()");

        Long id = modelFacade.create(modelDTO);

        return modelFacade.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ModelDTO updateModel(@PathVariable("id") long id, @Valid @ModelAttribute ModelCreateDTO modelDTO) throws Exception {

        log.debug("rest updateModel({})", id);

        modelFacade.update(modelDTO, id);

        return modelFacade.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteModel(@PathVariable("id") long id) throws Exception {

        log.debug("rest deleteModel({})", id);

        modelFacade.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ModelDTO getModel(@PathVariable("id") long id) throws Exception {

        log.debug("rest getModel({})", id);

        ModelDTO modelDTO = modelFacade.findById(id);

        if (modelDTO == null) {
            // TO-DO throw new exception
            throw new Exception("reason..");
        }

        return modelDTO;
    }
/*

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ModelDTO getModel(@PathVariable("name") String name) throws Exception {

        log.debug("rest getModel({})", name);

        ModelDTO modelDTO = modelFacade.findByName(name);

        if (modelDTO == null) {
            // TO-DO throw new exception
            throw new Exception("reason..");
        }

        return modelDTO;
    }
*/
//    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public final ModelDTO getModel(@PathVariable("name") String name) throws Exception {
//
//        log.debug("rest getModel({})", name);
//
//        ModelDTO modelDTO = modelFacade.findByName(name);
//
//        if (modelDTO == null) {
//            // TO-DO throw new exception
//            throw new Exception("reason..");
//        }
//
//        return modelDTO;
//    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ModelDTO> getModels() {

        log.debug("rest getModels()");

        return modelFacade.findAll();
    }

}
