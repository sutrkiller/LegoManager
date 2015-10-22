package cz.muni.fi.pa165.legomanager.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Entity class Model contains name, age limit for children, price, category to
 * which the model belongs and list of pieces that belong to the model.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 16.10.2015
 */
@Entity
@Table(name = "MODELS")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    private Byte ageLimit;

    @DecimalMin("0.0")
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    private Category category;

    @OneToMany
    @NotNull
    private List<Piece> pieces;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.ageLimit);
        hash = 71 * hash + Objects.hashCode(this.price);
        hash = 71 * hash + Objects.hashCode(this.pieces);
        hash = 71 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Model)) {
            return false;
        }
        final Model other = (Model) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        if (!Objects.equals(this.ageLimit, other.getAgeLimit())) {
            return false;
        }
        if (!Objects.equals(this.price, other.getPrice())) {
            return false;
        }
        if (!Objects.equals(this.pieces, other.getPieces())) {
            return false;
        }
        if (!Objects.equals(this.category, other.getCategory())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + '}';
    }
}
