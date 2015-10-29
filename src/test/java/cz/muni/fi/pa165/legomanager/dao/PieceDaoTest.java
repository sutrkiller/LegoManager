package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.enums.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Testing class for PieceDao.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 27.10.2015
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.legomanager.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PieceDaoTest extends AbstractTestNGSpringContextTests {

   

}
