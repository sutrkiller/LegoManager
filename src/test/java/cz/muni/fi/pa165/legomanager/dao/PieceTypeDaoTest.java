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
    public void createPieces(){

        Set<Color> colors = new HashSet<>();

        p1 = new PieceType();
        p1.setName("Piece 1");
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        p1.setColors(colors);

        pieceDao.create(p1);

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

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void testCreateWithNullName() {
        PieceType p = new PieceType();
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        p.setColors(colors);

        pieceDao.create(p);
    }

    @Test
    public void testFindById() {
        PieceType found = pieceDao.findById(p1.getId());
        Assert.assertEquals(found, p1);
    }

    @Test
    public void testFindByName() {
        PieceType found = pieceDao.findByName(p2.getName());
        Assert.assertEquals(found, p2);
    }

    @Test
    public void testFindByNonExistingName() {
        PieceType found = pieceDao.findByName("Non existing name");
        Assert.assertNull(found);
    }

    @Test
    public void testFindAll() {
        List<PieceType> found = pieceDao.findAll();
        Assert.assertEquals(found.size(), 3);
    }

    @Test
    public void testUpdate() {
        String newName = "New name";
        p1.setName(newName);
        pieceDao.update(p1);

        PieceType found = pieceDao.findById(p1.getId());
        Assert.assertEquals(newName, found.getName());
    }

    @Test
    public void testDelete() {
        pieceDao.delete(p3);
        List<PieceType> foundList = pieceDao.findAll();
        Assert.assertEquals(foundList.size(), 2);

        PieceType found = pieceDao.findById(p3.getId());
        Assert.assertNull(found);
    }

    @Test(expectedExceptions=PersistenceException.class)
    public void testNameIsUnique() {
        PieceType p = new PieceType();
        p.setName("Piece 1");
        Set<Color> colors = new HashSet<>();
        colors.add(Color.BLACK);
        p.setColors(colors);

        pieceDao.create(p);
    }

    @Test
    public void testColorsInPiece() {
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
