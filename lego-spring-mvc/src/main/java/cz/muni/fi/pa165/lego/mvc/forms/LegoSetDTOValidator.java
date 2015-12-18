package cz.muni.fi.pa165.lego.mvc.forms;

import cz.muni.fi.pa165.lego.dto.LegoSetDTOGet;
import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Class validating LegoSet from input
 * 
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 16.12.2015
 */
public class LegoSetDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return LegoSetDTOGet.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LegoSetDTOGet legoSetDTO = (LegoSetDTOGet) o;
        
        if (legoSetDTO.getName() == null) {
            errors.rejectValue("name", "LegoSetDTOValidator.name.null");
        }
        
        if (legoSetDTO.getModels() == null) {
            errors.rejectValue("models", "CategoryDTOValidator.models.null");
        }
        
        if (legoSetDTO.getPrice() == null) {
            errors.rejectValue("price", "CategoryDTOValidator.price.null");
        }
        
        if (legoSetDTO.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("price", "CategoryDTOValidator.price.negative");
        }
        
        if (legoSetDTO.getCategory()== null) {
            errors.rejectValue("category", "CategoryDTOValidator.category.null");
        }
    }
    
}
