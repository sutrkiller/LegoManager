package cz.muni.fi.pa165.legomanager.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class User represents users in system.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Authorities> authorities = new HashSet<Authorities>(0);

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }
}
