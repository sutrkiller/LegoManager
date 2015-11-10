package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Model;
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
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Repository
public class ModelDaoImpl implements ModelDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Model model) throws LegoPersistenceException {
        if (model == null) {
            throw new IllegalArgumentException("Model can't be null.");
        }
        if (em.contains(model)) {
            throw new EntityAlreadyExistsException("Model is already persist");
        }
        try {
            em.persist(model);
        } catch (PersistenceException | ValidationException e) {
            throw new LegoPersistenceException("Persist fail", e);
        }
    }

    @Override
    public Model findById(Long id) throws EntityNotExistsException {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null.");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id can't be negative");
        }
        Model model = em.find(Model.class, id);
        if (model == null) {
            throw new EntityNotExistsException("No model found.");
        }
        return model;
    }

    @Override
    public Model findByName(String name) throws EntityNotExistsException {
        if (name == null) {
            throw new IllegalArgumentException("Name can");
        }
        try {
            return em.createQuery("SELECT m FROM Model m WHERE m.name = :name ",
                    Model.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotExistsException("No model found", e);
        }
    }

    @Override
    public List<Model> findAll() {
        return em.createQuery("SELECT m FROM Model m", Model.class).getResultList();
    }

    @Override
    public Model update(Model model) throws LegoPersistenceException {
        if (model == null) {
            throw new IllegalArgumentException("Model can't be null.");
        }
        if (!em.contains(model)) {
            throw new EntityNotExistsException("Model is not managed by entityManager");
        }
        try {
            em.merge(model);
            em.flush();
        } catch (PersistenceException | ValidationException e) {
            throw new LegoPersistenceException("Persistence failed", e);
        }
        return em.merge(model);
    }

    @Override
    public void delete(Model model) throws EntityNotExistsException {
        if (model == null) {
            throw new IllegalArgumentException("Model can't be null.");
        }
        if (!em.contains(model)) {
            throw new EntityNotExistsException("Model is not managed by entityManager.");
        }
        em.remove(model);
    }

}
