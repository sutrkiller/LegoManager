package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Data transfer object for {@link LegoSet class}
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 21.11.2015
 */
public class ModelDTOPut {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Long categoryId;

    @NotNull
    @Min(0)
    @Max(100)
    private Byte ageLimit;

    @NotNull
    @Min(0)
    private BigDecimal price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Byte getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Byte ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return "ModelDTOPut{" +
                ", name='" + name + '\'' +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                '}';
    }
}