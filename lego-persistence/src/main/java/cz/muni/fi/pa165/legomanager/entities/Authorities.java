package cz.muni.fi.pa165.legomanager.entities;

import javax.persistence.*;

/**
 * Entity class Authorities represents authorities in system.
 *
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */

@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = { "authority", "username" }))
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_authority_id", unique = true, nullable = false)
    private Integer userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public User getUser() {
        return user;
    }

    public String getAuthority() {
        return authority;
    }
}
