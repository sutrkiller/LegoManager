package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ValidationException;
import org.springframework.stereotype.Repository;

/**
 * CategoryDaoImpl implements {@link CategoryDao}.
 *
 * @author Tobias Kamenicky <tobias.kamenicky@gmail.com>
 * @date 25.10.2015
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Category findById(Long id) throws EntityNotExistsException {
        if (id == null) {
            throw new IllegalArgumentException("Id is null.");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id < 0");
        }
        try {
            Category c = em.find(Category.class, id);
            if (c == null) {
                throw new EntityNotExistsException("Id not found");
            }
            return c;
        } catch (NoResultException e) {
            throw new EntityNotExistsException("Id not found", e);
        }
    }

    @Override
    public void create(Category c) throws EntityAlreadyExistsException, LegoPersistenceException {
        if (c == null) {
            throw new IllegalArgumentException("Category is null.");
        }
        if (em.contains(c)) {
            throw new EntityAlreadyExistsException("Category already in database");
        }
        try {
            em.persist(c);
        } catch (ValidationException | PersistenceException e) {
            throw new LegoPersistenceException("Persistence eror", e);
        }
    }

    @Override
    public void delete(Category c) throws EntityNotExistsException {
        if (c == null) {
            throw new IllegalArgumentException("Category is null.");
        }
        if (!em.contains(c)) {
            throw new EntityNotExistsException("Category not in database");
        }
        em.remove(c);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public Category findByName(String name) throws EntityNotExistsException {
        if (name == null) {
            throw new IllegalArgumentException("Name is null or empty");
        }
        try {
            return em.createQuery("SELECT c FROM Category c WHERE name = :name", Category.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotExistsException("No result found", e);
        }
    }

    @Override
    public void update(Category c) throws EntityNotExistsException, LegoPersistenceException {
        if (c == null) {
            throw new IllegalArgumentException("Category is null.");
        }
        if (!em.contains(c)) {
            throw new EntityNotExistsException("Category not in database");
        }
        em.merge(c);
        try {
            em.flush();
        } catch (ValidationException | PersistenceException e) {
            throw new LegoPersistenceException("Persistence eror", e);
        }
    }

}
