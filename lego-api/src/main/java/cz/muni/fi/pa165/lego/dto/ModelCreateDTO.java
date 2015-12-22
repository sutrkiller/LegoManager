package cz.muni.fi.pa165.lego.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Marek on 18/12/2015.
 */
public class ModelCreateDTO {

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
        return "ModelCreateDTO{" +
                ", name='" + name + '\'' +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                '}';
    }
}