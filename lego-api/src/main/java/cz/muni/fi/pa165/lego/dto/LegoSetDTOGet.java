package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Data transfer object for {@link LegoSet class}
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 21.11.2015
 */
public class LegoSetDTOGet {

    private Long id;

    private String name;

    private List<ModelDTOGet> models = new ArrayList<>();

    private BigDecimal price;

    private CategoryDTO category;

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

    public List<ModelDTOGet> getModels() {
        return models;
    }

    public void setModels(List<ModelDTOGet> models) {
        this.models = models;
    }

    public void addModel(ModelDTOGet model) {
        models.add(model);
    }

    public void removeModel(ModelDTOGet model) {
        models.remove(model);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
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
        if (!(obj instanceof LegoSetDTOGet)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final LegoSetDTOGet other = (LegoSetDTOGet) obj;
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
        return "LegoSetDTOGet{" + "id=" + getId() + ", name=" + getName() + ", models=" + getModels() +
                ", price=" + getPrice() + ", category=" + getCategory() + '}';
    }
}
