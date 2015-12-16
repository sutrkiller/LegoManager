package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ValidationException;
import java.util.List;

/**
 * LegoSetDaoImpl implements {@link LegoSetDao}.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */

@Repository
public class LegoSetDaoImpl implements LegoSetDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(LegoSet ls) throws LegoPersistenceException {
        if (ls == null) {
            throw new IllegalArgumentException("LegoSet is null.");
        }
        if (em.contains(ls)) {
            throw new EntityAlreadyExistsException("LegoSet already exists in db.");
        }
        try {
            em.persist(ls);
        } catch (PersistenceException | ValidationException e) {
            throw new LegoPersistenceException("Error creating LegoSet.", e);
        }
    }

    @Override
    public LegoSet findById(Long id) throws LegoPersistenceException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Wrong id param.");
        }
        try {
            LegoSet ls = em.find(LegoSet.class, id);
            if (ls == null) {
                throw new EntityNotExistsException("Entity does not exist.");
            }
            return ls;
        } catch (NoResultException e) {
            throw new EntityNotExistsException("Entity does not exist.", e);
        } catch (PersistenceException | ValidationException e) {
            throw new LegoPersistenceException("Error finding LegoSet.", e);
        }
    }

    @Override
    public LegoSet findByName(String name) throws LegoPersistenceException {
        if (name == null) {
            throw new IllegalArgumentException("Wrong name param.");
        }
        try {
            LegoSet ls = em.createQuery("SELECT ls FROM LegoSet ls WHERE name = :name", LegoSet.class).setParameter("name", name).getSingleResult();
            if (ls == null) {
                throw new EntityNotExistsException("Entity does not exist.");
            }
            return ls;
        } catch (NoResultException e) {
            throw new EntityNotExistsException("Entity does not exist", e);
        } catch (PersistenceException | ValidationException e) {
            throw new LegoPersistenceException("Error finding LegoSet.", e);
        }
    }

    @Override
    public List<LegoSet> findAll() {
        return em.createQuery("SELECT ls FROM LegoSet ls", LegoSet.class).getResultList();
    }

    @Override
    public void update(LegoSet ls) throws LegoPersistenceException {
        if (ls == null) {
            throw new IllegalArgumentException("LegoSet is null.");
        }
//        if (!em.contains(ls)) {
//            throw new EntityNotExistsException("Entity does not exist.");
//        }
        try {
            em.merge(ls);
            em.flush();
        } catch (PersistenceException | ValidationException e) {
            throw new LegoPersistenceException("Error updating LegoSet.", e);
        }
    }

    @Override
    public void delete(LegoSet ls) throws EntityNotExistsException {
        if (ls == null) {
            throw new IllegalArgumentException("LegoSet is null.");
        }
        if (!em.contains(ls)) {
            throw new EntityNotExistsException("Entity does not exist.");
        }
        em.remove(ls);
    }

}
