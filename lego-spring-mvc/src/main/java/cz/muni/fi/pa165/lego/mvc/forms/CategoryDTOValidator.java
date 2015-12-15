package cz.muni.fi.pa165.lego.mvc.forms;

import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Class validating Category from input.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 12.12.2015
 */
public class CategoryDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return CategoryDTO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDTO categoryDTO = (CategoryDTO) target;

        if (categoryDTO.getName() == null) {
            errors.rejectValue("name", "CategoryDTOValidator.name.null");
        }
        if (categoryDTO.getDescription() == null) {
            errors.rejectValue("description", "CategoryDTOValidator.description.null");
        }
    }
}
