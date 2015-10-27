package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Piece;
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

    @Autowired
    public PieceDao pieceDao;

    private Piece p1;
    private Piece p2;
    private Piece p3;


    @BeforeMethod
    public void createPieces(){

        Set<Color> colors = new HashSet<>();

        p1 = new Piece();
        p1.setName("Piece 1");
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        p1.setColors(colors);
        p1.setCurrentColor(Color.BLUE);

        pieceDao.create(p1);

        p2 = new Piece();
        p2.setName("Piece 2");
        colors.clear();
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        p2.setColors(colors);
        p2.setCurrentColor(Color.BLACK);

        p3 = new Piece();
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        p3.setColors(colors);
        p3.setCurrentColor(Color.RED);
        p3.setName("Piece 3");

        pieceDao.create(p1);
        pieceDao.create(p2);
        pieceDao.create(p3);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void testCreateWithNullName() {
        Piece p = new Piece();
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        p.setColors(colors);
        p.setCurrentColor(Color.BLACK);

        pieceDao.create(p);
    }

    @Test
    public void testFindById() {
        Piece found = pieceDao.findById(p1.getId());
        Assert.assertEquals(found, p1);
    }

    @Test
    public void testFindByName() {
        Piece found = pieceDao.findByName(p2.getName());
        Assert.assertEquals(found, p2);
    }

    @Test
    public void testFindByNonExistingName() {
        Piece found = pieceDao.findByName("Non existing name");
        Assert.assertNull(found);
    }

    @Test
    public void testFindAll() {
        List<Piece> found = pieceDao.findAll();
        Assert.assertEquals(found.size(), 3);
    }

    @Test
    public void testUpdate() {
        String newName = "New name";
        p1.setName(newName);
        pieceDao.update(p1);

        Piece found = pieceDao.findById(p1.getId());
        Assert.assertEquals(newName, found.getName());
    }

    @Test
    public void testDelete() {
        pieceDao.delete(p3);
        List<Piece> foundList = pieceDao.findAll();
        Assert.assertEquals(foundList.size(), 2);

        Piece found = pieceDao.findById(p3.getId());
        Assert.assertNull(found);
    }

    @Test(expectedExceptions=PersistenceException.class)
    public void testNameIsUnique() {
        Piece p = new Piece();
        p.setName("Piece 1");
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        p.setColors(colors);
        p.setCurrentColor(Color.BLACK);

        pieceDao.create(p);
    }

    @Test
    public void testColorsInPiece() {
        Piece p4 = new Piece();
        p4.setName("Piece 4");
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        p4.setColors(colors);
        p4.setCurrentColor(Color.RED);

        pieceDao.create(p4);
        Piece found = pieceDao.findById(p4.getId());
        Assert.assertEquals(found.getColors().size(), 3);
        Assert.assertTrue(found.getColors().contains(Color.BLACK));
    }

}
