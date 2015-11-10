package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Testing class for PieceTypeDao.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 27.10.2015
 */
@ContextConfiguration(classes = cz.muni.fi.pa165.legomanager.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PieceTypeDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public PieceTypeDao pieceDao;

    private PieceType p1;
    private PieceType p2;
    private PieceType p3;


    @BeforeMethod
    public void createPieces() throws LegoPersistenceException {

        Set<Color> colors = new HashSet<>();

        p1 = new PieceType();
        p1.setName("Piece 1");
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        p1.setColors(colors);

        p2 = new PieceType();
        p2.setName("Piece 2");
        colors.clear();
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        p2.setColors(colors);

        p3 = new PieceType();
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        p3.setColors(colors);
        p3.setName("Piece 3");

        pieceDao.create(p1);
        pieceDao.create(p2);
        pieceDao.create(p3);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testCreateWithNullName() throws LegoPersistenceException {
        PieceType p = new PieceType();
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        p.setColors(colors);

        pieceDao.create(p);
    }

    @Test
    public void testFindById() throws EntityNotExistsException {
        PieceType found = pieceDao.findById(p1.getId());
        Assert.assertEquals(found, p1);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testFindByIdNotExists() throws EntityNotExistsException {
        pieceDao.findById(p1.getId() + p2.getId() + p3.getId());
    }

    @Test
    public void testFindByName() throws EntityNotExistsException {
        PieceType found = pieceDao.findByName(p2.getName());
        Assert.assertEquals(found, p2);
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testFindByNonExistingName() throws EntityNotExistsException {
        pieceDao.findByName("Non existing name");
    }

    @Test
    public void testFindAll() {
        List<PieceType> found = pieceDao.findAll();
        Assert.assertEquals(found.size(), 3);
    }

    @Test
    public void testUpdate() throws LegoPersistenceException {
        String newName = "New name";
        p1.setName(newName);
        pieceDao.update(p1);

        PieceType found = pieceDao.findById(p1.getId());
        Assert.assertEquals(newName, found.getName());
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateWithNullName() throws LegoPersistenceException {
        p1.setName(null);
        pieceDao.update(p1);
        pieceDao.findById(p1.getId());
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testUpdateNotExists() throws LegoPersistenceException {
        PieceType p = new PieceType();
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        p.setColors(colors);
        pieceDao.update(p);
    }

    @Test(expectedExceptions = EntityNotExistsException.class)
    public void testDelete() throws EntityNotExistsException {
        pieceDao.delete(p3);
        List<PieceType> foundList = pieceDao.findAll();
        Assert.assertEquals(foundList.size(), 2);

        pieceDao.findById(p3.getId());
    }

    @Test(expectedExceptions = LegoPersistenceException.class)
    public void testNameIsUnique() throws LegoPersistenceException {
        PieceType p = new PieceType();
        p.setName("Piece 1");
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        p.setColors(colors);

        pieceDao.create(p);
    }

    @Test
    public void testColorsInPiece() throws LegoPersistenceException {
        PieceType p4 = new PieceType();
        p4.setName("Piece 4");
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        p4.setColors(colors);

        pieceDao.create(p4);
        PieceType found = pieceDao.findById(p4.getId());
        Assert.assertEquals(found.getColors().size(), 3);
        Assert.assertTrue(found.getColors().contains(Color.BLACK));
    }

}
