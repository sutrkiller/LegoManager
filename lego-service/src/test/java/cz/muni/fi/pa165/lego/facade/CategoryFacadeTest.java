package cz.muni.fi.pa165.lego.facade;

import cz.muni.fi.pa165.lego.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.lego.dto.CategoryDTO;
import cz.muni.fi.pa165.lego.facade.CategoryFacade;
import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.lego.service.facade.CategoryFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Transactional
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class CategoryFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    private CategoryFacade categoryFacade;

    @Test
    public void testCreateCategory() throws Exception {
        CategoryCreateDTO categoryDTO = new CategoryCreateDTO();
        categoryDTO.setName("Cars");
        categoryDTO.setDescription("Everything with engine and four wheels.");
        categoryFacade.createCategory(categoryDTO);
/*TODO  Long id - categoryFacade.createCategory(categoryDTO);
        CategoryDTO category = categoryFacade.getCategoryById(id);
        assertEquals(id, category.getId());
        assertEquals(categoryDTO.getName(), category.getName());
        assertEquals(categoryDTO.getDescription(), category.getDescription());
*/
    }

    @Test
    public void testUpdateCategory() throws Exception {

    }

    @Test
    public void testDeleteCategory() throws Exception {

    }

    @Test
    public void testGetCategoryById() throws Exception {

    }

    @Test
    public void testGetCategoryByName() throws Exception {

    }

    @Test
    public void testGetAllCategories() throws Exception {

    }

    @Test
    public void testChangeName() throws Exception {

    }

    @Test
    public void testChangeDescription() throws Exception {

    }
}