package cz.muni.fi.pa165.lego.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */
public class CategoryDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CategoryDTO)) {
            return false;
        }
        final CategoryDTO other = (CategoryDTO) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        if (!Objects.equals(this.description, other.getDescription())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}
