package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import static cz.muni.fi.pa165.legomanager.enums.Color.*;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.EnumSet;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;

/**
 * Testing class for PieceDao.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.legomanager.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PieceDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public PieceDao pieceDao;

    @Autowired
    public PieceTypeDao pieceTypeDao;

    @PersistenceContext
    public EntityManager em;

    private PieceType cube;
    private PieceType block;

    private Piece blueCube;
    private Piece redCube1;
    private Piece redCube2;
    private Piece blueBlock;

    private final String NEW_PIECE_NAME = "#05";

    @BeforeMethod
    public void setUp() throws LegoPersistenceException {

        cube = new PieceType();
        cube.setName("Cube 2x2");
        cube.setColors(EnumSet.of(RED, BLUE));

        block = new PieceType();
        block.setName("Block 4x2");
        block.setColors(EnumSet.of(BLUE, GREEN));

        pieceTypeDao.create(cube);
        pieceTypeDao.create(block);

        blueCube = new Piece();
        blueCube.setPieceType(cube);
        blueCube.setColor(BLUE);
        blueCube.setName("#01");

        redCube1 = new Piece();
        redCube1.setPieceType(cube);
        redCube1.setColor(RED);
        redCube1.setName("#02");

        redCube2 = new Piece();
        redCube2.setPieceType(cube);
        redCube2.setColor(RED);
        redCube2.setName("#03");

        blueBlock = new Piece();
        blueBlock.setPieceType(block);
        blueBlock.setColor(BLUE);
        blueBlock.setName("#04");

        pieceDao.create(blueCube);
        pieceDao.create(redCube1);
        pieceDao.create(redCube2);
        pieceDao.create(blueBlock);

        em.flush();
    }

    @AfterMethod
    public void after() throws Exception {
        /* 
         Because we have to flush changes to DB before auto-rollback after test.
         Because it can throw some exception.
         */
        em.flush();
    }

    @Test
    public void testCreate() throws Exception {
        Piece blueCube2 = new Piece();
        blueCube2.setPieceType(cube);
        blueCube2.setColor(BLUE);
        blueCube2.setName(NEW_PIECE_NAME);

        pieceDao.create(blueCube);

        Long blueCubeId = blueCube2.getId();
        assertNotNull(blueCubeId);

        Piece actual = pieceDao.findById(blueCubeId);
        assertEquals(blueCube2, actual);

        actual = pieceDao.findByName(NEW_PIECE_NAME);
        assertEquals(blueCube2, actual);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullPiece() throws Exception {
        pieceDao.create(null);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullPieceType() throws Exception {
        Piece blueCube2 = new Piece();
        blueCube2.setPieceType(null);
        blueCube2.setColor(BLUE);
        blueCube2.setName(NEW_PIECE_NAME);

        pieceDao.create(blueCube2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNonSuitableColor() throws Exception {
        Piece blueCube2 = new Piece();
        blueCube2.setPieceType(cube);
        blueCube2.setColor(PINK);
        blueCube2.setName(NEW_PIECE_NAME);

        pieceDao.create(blueCube2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullColor() throws Exception {
        Piece blueCube2 = new Piece();
        blueCube2.setPieceType(cube);
        blueCube2.setColor(null);
        blueCube2.setName(NEW_PIECE_NAME);

        pieceDao.create(blueCube2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateNullName() throws Exception {
        Piece blueCube2 = new Piece();
        blueCube2.setPieceType(cube);
        blueCube2.setColor(BLUE);
        blueCube2.setName(null);

        pieceDao.create(blueCube2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateAlreadyExists() throws Exception {
        pieceDao.create(blueCube);
    }

    @Test
    public void testFindById() throws LegoPersistenceException {
        Long blueCubeId = blueCube.getId();
        assertNotNull(blueCubeId);
        Piece actual = pieceDao.findById(blueCubeId);
        assertEquals(blueCube, actual);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByIdNotExist() throws LegoPersistenceException {
        Long randomId = 999L;
        assertNotSame(blueCube.getId(), randomId);
        assertNotSame(redCube1.getId(), randomId);
        assertNotSame(redCube2.getId(), randomId);
        assertNotSame(blueBlock.getId(), randomId);
        pieceDao.findById(randomId);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNull() throws LegoPersistenceException {
        pieceDao.findById(null);
    }

    @Test
    public void testFindByName() throws LegoPersistenceException {
        String blueCubeName = blueCube.getName();
        assertNotNull(blueCubeName);
        Piece actual = pieceDao.findByName(blueCubeName);
        assertEquals(blueCube, actual);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByNameNotExist() throws EntityNotExistsException {
        String randomName = "#99";
        assertNotSame(blueCube.getName(), randomName);
        assertNotSame(redCube1.getName(), randomName);
        assertNotSame(redCube2.getName(), randomName);
        assertNotSame(blueBlock.getName(), randomName);
        pieceDao.findByName(randomName);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameNull() throws LegoPersistenceException {
        pieceDao.findByName(null);
    }

    @Test
    public void testFindAll() throws LegoPersistenceException {
        Set<Piece> actual = new HashSet<>(pieceDao.findAll());

        Set<Piece> expected = new HashSet<>();
        expected.add(blueCube);
        expected.add(redCube1);
        expected.add(redCube1);
        expected.add(blueBlock);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllEmpty() throws EntityNotExistsException {
        pieceDao.delete(blueCube);
        pieceDao.delete(redCube1);
        pieceDao.delete(redCube1);
        pieceDao.delete(blueBlock);

        Set<Piece> actual = new HashSet<>(pieceDao.findAll());

        Set<Piece> emptySet = new HashSet<>();

        assertEquals(emptySet, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        blueCube.setPieceType(block);
        blueCube.setColor(GREEN);
        blueCube.setName("#06");

        pieceDao.update(blueCube);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullPiece() throws Exception {
        pieceDao.update(null);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullPieceType() throws Exception {
        blueCube.setPieceType(null);

        pieceDao.update(blueCube);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNonSuitableColor() throws Exception {
        blueCube.setColor(PINK);

        pieceDao.update(blueCube);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullColor() throws Exception {
        blueCube.setColor(null);

        pieceDao.update(blueCube2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNullName() throws Exception {
        blueCube.update(null);

        pieceDao.update(blueCube2);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testUpdateNotExist() throws Exception {
        Piece blueCube2 = new Piece();
        blueCube2.setPieceType(cube);
        blueCube2.setColor(BLUE);
        blueCube2.setName(NEW_PIECE_NAME);

        pieceDao.update(blueCube2);
    }

    @Test
    public void testDelete() throws EntityNotExistsException {
        pieceDao.delete(blueCube);

        Set<Piece> actual = new HashSet<>(pieceDao.findAll());

        Set<Piece> expected = new HashSet<>();
        expected.add(redCube1);
        expected.add(redCube1);
        expected.add(blueBlock);

        assertEquals(expected, actual);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testDeleteNotExist() {
        Piece blueCube2 = new Piece();
        blueCube2.setPieceType(cube);
        blueCube2.setColor(BLUE);
        blueCube2.setName(NEW_PIECE_NAME);

        pieceDao.delete(blueCube);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testDeleteAlreadyRemoved() throws EntityNotExistsException {
        pieceDao.delete(blueCube);
        em.flush();
        pieceDao.delete(blueCube);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() throws EntityNotExistsException {
        pieceDao.delete(null);
    }

}
