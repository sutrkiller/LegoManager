package cz.muni.fi.pa165.lego.dto;


import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.entities.PieceType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data transfer object for {@link PieceType class}
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.11.2015
 */
public class PieceTypeDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Set<Color> colors = new HashSet<>();

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

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PieceTypeDTO)) {
            return false;
        }
        final PieceTypeDTO other = (PieceTypeDTO) obj;
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
        return Objects.hashCode(this.getId());
    }

    @Override
    public String toString() {
        return "PieceTypeDTO{" + "id=" + id + ", name=" + name + ", colors=" + colors + '}';
    }


}
