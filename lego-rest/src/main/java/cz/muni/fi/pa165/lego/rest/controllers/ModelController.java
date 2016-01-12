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
import org.springframework.http.HttpStatus;

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

    /**
     * Create model with given parameters.
     *
     * example:
     * curl -X POST -H "Accept:application/json" -H "Content-Type:application/json"
     * http://localhost:8080/pa165/rest/models/create
     * -d '{"name":"House","price":100, "ageLimit": 12, "categoryId":2}'
     * | python -m json.tool
     *
     * @param modelDTO to be created
     * @return created modelDTO
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ModelDTO createModel(@RequestBody ModelCreateDTO modelDTO) {

        log.debug("rest createModel()");

        Long id = modelFacade.create(modelDTO);

        return modelFacade.findById(id);
    }

    /**
     * Update model with given id.
     *
     * example:
     * curl -X PUT -H "Content-Type:application/json"
     * http://localhost:8080/pa165/rest/models/1
     * -d '{"name":"House","price":120, "ageLimit":90, "categoryId":2}'
     *
     * @param id id of updated model
     * @param modelDTO new data
     * @return 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ModelDTO updateModel(@PathVariable("id") long id, @Valid @RequestBody ModelCreateDTO modelDTO) {

        log.debug("rest updateModel({})", id);

        modelFacade.update(modelDTO, id);

        return modelFacade.findById(id);
    }

    /**
     * Delete model defined by its id.
     *
     * example:
     * curl -X DELETE http://localhost:8080/pa165/rest/models/1
     *
     * @param id of model
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteModel(@PathVariable("id") long id) {

        log.debug("rest deleteModel({})", id);

        modelFacade.delete(id);
    }


    /**
     * Get model by id.
     *
     * example:
     * curl -H "Accept:application/json" http://localhost:8080/pa165/rest/models/1
     *
     * @param id of the model
     * @return model with given id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ModelDTO getModel(@PathVariable("id") long id) {

        log.debug("rest getModel({})", id);

        ModelDTO modelDTO = modelFacade.findById(id);

        return modelDTO;
    }

    /**
     * Get all models.
     *
     * example:
     * curl -H "Accept:application/json" http://localhost:8080/pa165/rest/models
     *
     * @return List of all models
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ModelDTO> getModels() {

        log.debug("rest getModels()");

        return modelFacade.findAll();
    }

    /**
     * Handles Exception throw during processing REST actions
     */
    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Requested model was not found")
    @ExceptionHandler(Exception.class)
    public void notFound() {
    }
}
