package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.entities.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 21.11.2015
 */
public class LegoSetDTO {

    private Long id;
    private String name;
    private List<Model> models;
    private BigDecimal price;
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public void addModel(Model model) {
        models.add(model);
    }

    public void removeModel(Model model) {
        models.remove(model);
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
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LegoSet)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final LegoSet other = (LegoSet) obj;
        if (this.getId() == null) {
            return false;
        }
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LegoSet{" + "id=" + getId() + ", name=" + getName() + ", models=" + getModels() +
                ", price=" + getPrice() + ", category=" + getCategory() + '}';
    }
}
