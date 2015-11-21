package cz.muni.fi.pa165.lego.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

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

    public Byte getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Byte ageLimit) {
        this.ageLimit = ageLimit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ModelCreateDTO)) {
            return false;
        }

        ModelCreateDTO that = (ModelCreateDTO) o;

        if (!Objects.equals(getName(), that.getName())) {
            return false;
        }
        if (!Objects.equals(getCategoryId(), that.getCategoryId())) {
            return false;
        }
        if (!Objects.equals(getAgeLimit(), that.getAgeLimit())) {
            return false;
        }
        return Objects.equals(getPrice(), that.getPrice());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getCategoryId().hashCode();
        result = 31 * result + getAgeLimit().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ModelCreateDTO{" +
                "name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                '}';
    }
}
