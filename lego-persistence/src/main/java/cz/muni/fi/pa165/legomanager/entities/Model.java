package cz.muni.fi.pa165.legomanager.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity class Model contains name, age limit for children, price, category to
 * which the model belongs and list of pieces that belong to the model.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 16.10.2015
 */
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Min(0)
    private Byte ageLimit;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @ManyToOne
    private Category category;

    @OneToMany
    @NotNull
    private List<Piece> pieces = new ArrayList<>();

    public Long getId() {
        return id;
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
        return Collections.unmodifiableList(pieces);
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        this.pieces.remove(piece);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        if ((this.id == null) || (other.getId() == null)) {
            return false;
        }
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + '}';
    }
}
