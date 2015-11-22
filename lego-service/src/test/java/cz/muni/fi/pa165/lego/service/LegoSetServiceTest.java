package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for {@link LegoSetService} service class.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
@Transactional
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class LegoSetServiceTest extends AbstractTestNGSpringContextTests {

    private LegoSet testLegoSet;

    @Mock
    private LegoSetDao legoSetDao;

    @Autowired
    @InjectMocks
    private LegoSetService legoSetService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public void prepareTestLegoSet() throws LegoPersistenceException {
        testLegoSet = new LegoSet();
    }

}
