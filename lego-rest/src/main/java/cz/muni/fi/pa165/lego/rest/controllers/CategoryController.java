package cz.muni.fi.pa165.lego.rest.controllers;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Category
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 11.12.2015
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private final static Logger log = LoggerFactory.getLogger(CategoryController.class);
    
    @Inject
    private CategoryFacade categoryFacade;
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        
        log.debug("rest createCategory()");
        
        Long id = categoryFacade.create(categoryDTO);
        
        return categoryFacade.findById(id);
    }
    
     @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
     public final CategoryDTO updateCategory(@PathVariable("id") long id, @Valid @ModelAttribute CategoryDTO categoryDTO) throws Exception {
         
         log.debug("rest updateCategory({})",id);
         
         categoryFacade.update(categoryDTO);
         return categoryFacade.findById(id);
     }
     
     @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
     public final void deleteCategory(@PathVariable("id") long id) throws Exception {
         log.debug("rest deleteCategory({})",id);
         
         categoryFacade.delete(id);
     }
     
     @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public final CategoryDTO getCategory(@PathVariable("id") long id) throws Exception {
         log.debug("rest getCategory({})",id);
         CategoryDTO categoryDTO = categoryFacade.findById(id);
         
         if (categoryDTO == null) {
             //throw exception
             throw new Exception("blabla");
         }
         
         return categoryDTO;
     }
     
     @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public final CategoryDTO getCategory(@PathVariable("name") String name) throws Exception {
         log.debug("res getCategory({})",name);
         CategoryDTO categoryDTO = categoryFacade.findByName(name);
         
         if (categoryDTO == null) {
             //throw exception
             throw new Exception("blabla");
         }
         
         return categoryDTO;
     }
     
     @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public final List<CategoryDTO> getCategories() {
         log.debug("rest getCategories()");
         
         return categoryFacade.findAll();
     }
}
