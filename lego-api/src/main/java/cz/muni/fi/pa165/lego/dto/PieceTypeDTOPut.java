package cz.muni.fi.pa165.lego.dto;

import cz.muni.fi.pa165.legomanager.enums.Color;
import cz.muni.fi.pa165.legomanager.entities.PieceType;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data transfer object for {@link PieceType class}
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.11.2015
 */
public class PieceTypeDTOPut {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Set<Color> colors = new HashSet<>();

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
    public String toString() {
        return "PieceTypeDTO{" + "name=" + getName() + ", colors=" + getColors() + '}';
    }

}
