package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.dto.*;
import cz.muni.fi.pa165.lego.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.legomanager.entities.*;
import cz.muni.fi.pa165.legomanager.enums.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService mappingService;

    private Category cars;
    private CategoryDTO carsDTO;
    private Model ferrari;
    private ModelDTO ferrariDTO;
    private LegoSet sportCars;
    private LegoSetDTOGet sportCarsDTO;
    private PieceType wheel;
    private PieceTypeDTO wheelDTO;
    private Piece leftFrontWheel;
    private PieceDTOGet leftFrontWheelDTO;

    @BeforeMethod
    public void setup() {

        cars = new Category();
        cars.setName("Cars");
        cars.setDescription("Cars dsc");

        carsDTO = new CategoryDTO();
        carsDTO.setId(1L);
        carsDTO.setName("Cars");
        carsDTO.setDescription("Cars dsc");

        ferrari = new Model();
        ferrari.setName("Ferrari");
        ferrari.setPrice(BigDecimal.valueOf(100));
        ferrari.setAgeLimit((byte)10);

        ferrariDTO = new ModelDTO();
        ferrariDTO.setId(1L);
        ferrariDTO.setName("Ferrari");
        ferrariDTO.setPrice(BigDecimal.valueOf(100));
        ferrariDTO.setAgeLimit((byte)10);

        sportCars = new LegoSet();
        sportCars.setName("Sport cars");
        ferrari.setPrice(BigDecimal.valueOf(1000));

        sportCarsDTO = new LegoSetDTOGet();
        sportCarsDTO.setId(1L);
        sportCarsDTO.setName("Sport cars");
        sportCarsDTO.setPrice(BigDecimal.valueOf(1000));

        Set<Color> colors = new HashSet<>();
        colors.add(Color.RED);
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);

        wheel = new PieceType();
        wheel.setName("Wheel");
        wheel.setColors(colors);


        wheelDTO = new PieceTypeDTO();
        wheelDTO.setId(1L);
        wheelDTO.setName("Wheel");
        wheelDTO.setColors(colors);

        leftFrontWheel = new Piece();
        leftFrontWheel.setCurrentColor(Color.RED);

        leftFrontWheelDTO = new PieceDTOGet();
        leftFrontWheelDTO.setId(1L);
        leftFrontWheelDTO.setCurrentColor(Color.RED);
    }



    @Test
    public void testMapToCategory() throws Exception {
        Category actual = mappingService.mapTo(carsDTO, Category.class);

        assertEquals(actual.getName(), carsDTO.getName());
        assertEquals(actual.getDescription(), carsDTO.getDescription());
    }

    @Test
    public void testMapToCategoryDTO() throws Exception {
        CategoryDTO actual = mappingService.mapTo(cars, CategoryDTO.class);

        assertEquals(actual.getName(), cars.getName());
        assertEquals(actual.getDescription(), cars.getDescription());
    }



    @Test
    public void testMapToModel() throws Exception {
        Model actual = mappingService.mapTo(ferrariDTO, Model.class);

        assertEquals(actual.getName(), ferrariDTO.getName());
        assertEquals(actual.getAgeLimit(), ferrariDTO.getAgeLimit());
        assertEquals(actual.getPrice(), ferrariDTO.getPrice());
    }

    @Test
    public void testMapToModelDTO() throws Exception {
        ModelDTO actual = mappingService.mapTo(ferrari, ModelDTO.class);

        assertEquals(actual.getName(), ferrari.getName());
        assertEquals(actual.getAgeLimit(), ferrari.getAgeLimit());
        assertEquals(actual.getPrice(), ferrari.getPrice());
    }



    @Test
    public void testMapToLegoSet() throws Exception {
        LegoSet actual = mappingService.mapTo(sportCarsDTO, LegoSet.class);

        assertEquals(actual.getName(), sportCarsDTO.getName());
        assertEquals(actual.getPrice(), sportCarsDTO.getPrice());
    }

    @Test
    public void testMapToLegoSetDTO() throws Exception {
        LegoSetDTOGet actual = mappingService.mapTo(sportCars, LegoSetDTOGet.class);

        assertEquals(actual.getName(), sportCars.getName());
        assertEquals(actual.getPrice(), sportCars.getPrice());
    }



    @Test
    public void testMapToPieceType() throws Exception {
        PieceType actual = mappingService.mapTo(wheelDTO, PieceType.class);

        assertEquals(actual.getName(), wheelDTO.getName());
    }

    @Test
    public void testMapToPieceTypeDTO() throws Exception {
        PieceTypeDTO actual = mappingService.mapTo(wheel, PieceTypeDTO.class);

        assertEquals(actual.getName(), wheel.getName());
    }



    @Test
    public void testMapToPiece() throws Exception {
        Piece actual = mappingService.mapTo(leftFrontWheelDTO, Piece.class);

        assertEquals(actual.getCurrentColor(), leftFrontWheel.getCurrentColor());
    }

    @Test
    public void testMapToPieceDTO() throws Exception {
        PieceDTOGet actual = mappingService.mapTo(leftFrontWheel, PieceDTOGet.class);

        assertEquals(actual.getCurrentColor(), leftFrontWheelDTO.getCurrentColor());
    }

}