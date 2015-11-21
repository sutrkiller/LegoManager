package cz.muni.fi.pa165.lego.dto;

import java.math.BigDecimal;
import java.util.*;

public class ModelDTO
{
    private Long id;

    private String name;

    private CategoryDTO category;

    private Byte ageLimit;

    private BigDecimal price;

    private Set<PieceDTO> pieces;

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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
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

    public Set<PieceDTO> getPieces() {
        return pieces;
    }

    public void setPieces(Set<PieceDTO> pieces) {
        this.pieces = pieces;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ModelDTO)) {
            return false;
        }
        final ModelDTO other = (ModelDTO) obj;
        if (this.getId() == null) {
            return false;
        }
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                ", pieces=" + pieces +
                '}';
    }
}
