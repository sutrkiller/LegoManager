package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.lego.service.CategoryService;
import cz.muni.fi.pa165.lego.service.LegoSetService;
import cz.muni.fi.pa165.lego.service.ModelService;
import cz.muni.fi.pa165.lego.service.PieceTypeService;
import cz.muni.fi.pa165.legomanager.entities.*;
import cz.muni.fi.pa165.legomanager.enums.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Loads some sample data to populate the lego manager database.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @since 14/12/2015.
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private PieceTypeService pieceTypeService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LegoSetService legoSetService;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public void loadData() throws IOException {

        Category vehicles = category("Vehicles", "Category containing cars.");
        Category houses = category("Houses", "Category containing houses.");
        Category starWars = category("Star Wars", "May the force be with you!");
        log.info("Loaded lego manager categories.");

        PieceType head = pieceType("Head", colors(Color.WHITE, Color.YELLOW, Color.BLACK, Color.GREEN));
        PieceType body = pieceType("Body", colors(Color.WHITE, Color.YELLOW, Color.BLACK, Color.GREEN));

        PieceType cube = pieceType("Cube", colors(Color.WHITE, Color.BLACK, Color.BLUE, Color.RED, Color.GREEN));
        PieceType wheel = pieceType("Wheel", colors(Color.BLACK));
        PieceType car = pieceType("Car", colors(Color.RED, Color.YELLOW));

        PieceType lightSaber = pieceType("Light Saber", colors(Color.BLUE, Color.RED, Color.GREEN));
        log.info("Loaded lego manager piece types.");

        Piece blackCube = piece(cube, Color.BLACK);
        Piece redCar = piece(car, Color.RED);
        Piece yellowCar = piece(car, Color.YELLOW);
        Piece blackWheel = piece(wheel, Color.BLACK);

        Piece yodaHead = piece(head, Color.GREEN);
        Piece yodaBody = piece(body, Color.GREEN);
        Piece yodaSaber = piece(lightSaber, Color.GREEN);

        Piece darthVaderHead = piece(head, Color.BLACK);
        Piece darthVaderBody = piece(body, Color.BLACK);
        Piece darthVaderSaber = piece(lightSaber, Color.RED);

        Model yoda = model("Yoda", starWars, 20 , 4, pieces(yodaHead, yodaBody, yodaSaber));
        Model darthVader = model("Darth Vader", starWars, 22, 8, pieces(darthVaderHead, darthVaderBody, darthVaderSaber));
        Model deathStar = model("Death Star", starWars, 50, 8, pieces(blackCube));

        Model ferrari = model("Ferrari", vehicles, 80, 4, pieces(redCar, blackWheel));
        Model lamborghini = model("Lamborghini", vehicles, 80, 4, pieces(yellowCar));

        Model house = model("Simple House", houses, 20, 2, pieces());
        log.info("Loaded lego manager models.");

        LegoSet jediSet = legoSet("Jedi", starWars, 100, models(yoda, darthVader, deathStar));
        LegoSet sportCarsSet = legoSet("Sport cars", vehicles, 140, models(ferrari, lamborghini));
        log.info("Loaded lego manager lego sets.");

        // save users with passwords and roles. (admin:admin[USER, ADMIN], user:user[USER]) (pass encoded with BCrypt10)
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("INSERT INTO users(username,password,enabled) VALUES ('admin','$2a$10$OhN1mx0y/AA1JbT2l6cJXuQ/b3VIZOhIPwWE0VpPN2h00z/uVha1q', true)").executeUpdate();
        em.createNativeQuery("INSERT INTO users(username,password,enabled) VALUES ('user' ,'$2a$10$8zuLWrC1OSjxBh00gQ.kh.HKYSGcdiad0WwkHrnTvdIr0PzFl1eBi', true)").executeUpdate();
        em.createNativeQuery("INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN')").executeUpdate();
        em.createNativeQuery("INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER' )").executeUpdate();
        em.createNativeQuery("INSERT INTO authorities (username, authority) VALUES ('user',  'ROLE_USER' )").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    private Category category(String name, String description) {
        Category c = new Category();
        c.setName(name);
        c.setDescription(description);
        categoryService.create(c);
        return c;
    }

    private PieceType pieceType(String name, Set<Color> colors) {
        PieceType p = new PieceType();
        p.setName(name);
        p.setColors(colors);
        pieceTypeService.create(p);
        return p;
    }

    private Set<Color> colors(Color... colors) {
        Set<Color> c = new HashSet<>();
        Collections.addAll(c, colors);
        return c;
    }

    private Model model(String name, Category category, int price, int ageLimit, List<Piece> pieceList) {
        Model m = new Model();
        m.setName(name);
        m.setCategory(category);
        m.setPrice(BigDecimal.valueOf(price));
        m.setAgeLimit((byte) ageLimit);
        modelService.create(m);
        for(Piece p : pieceList) {
            modelService.addPiece(m, p);
        }
        return m;
    }

    private List<Model> models(Model... models) {
        List<Model> p = new ArrayList<Model>();
        Collections.addAll(p, models);
        return p;
    }

    private Piece piece(PieceType pieceType, Color color) {
        Piece p = new Piece();
        p.setType(pieceType);
        p.setCurrentColor(color);
        return p;
    }

    private List<Piece> pieces(Piece... pieces) {
        List<Piece> p = new ArrayList<>();
        Collections.addAll(p, pieces);
        return p;
    }

    private LegoSet legoSet(String name, Category category, int price, List<Model> models) {
        LegoSet ls = new LegoSet();
        ls.setName(name);
        ls.setCategory(category);
        ls.setPrice(BigDecimal.valueOf(price));
        ls.setModels(models);
        legoSetService.create(ls);
        return ls;
    }
}
