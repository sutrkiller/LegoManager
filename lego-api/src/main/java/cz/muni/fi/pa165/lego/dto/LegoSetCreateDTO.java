package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.legomanager.entities.Category;

import java.math.BigDecimal;

/**
 * Created by Marek on 21/11/2015.
 */
public class LegoSetCreateDTO {

    private String name;
    private BigDecimal price;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null) return false;
        if (!(o instanceof LegoSetCreateDTO)) return false;

        LegoSetCreateDTO that = (LegoSetCreateDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) return false;
        return !(getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LegoSetCreateDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
