package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tobias
 */

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public void create(Category c) {
        em.persist(c);
    }

    @Override
    public void delete(Category c) {
        em.remove(c);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c",Category.class).getResultList();
    }

    @Override
    public Category findByName(String name) {
        try {
            return em.createQuery("SELECT c FROM Category c WHERE name = :name",Category.class).setParameter(":name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
