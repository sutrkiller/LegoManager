package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.PieceType;
import cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Class PieceTypeDaoImpl implements {@link PieceTypeDao} interface.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 23.10.2015
 */
@Repository
public class PieceTypeDaoImpl implements PieceTypeDao {

    @PersistenceContext
    private EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(PieceTypeDaoImpl.class);

    @Override
    public void create(PieceType pieceType) throws EntityAlreadyExistsException, LegoPersistenceException {
        if (pieceType == null) {
            throw new IllegalArgumentException("PieceType entity cannot be NULL.");
        }
        if (this.checkIfExistsInDB(pieceType)) {
            throw new EntityAlreadyExistsException("PieceType already exists in DB.");
        }
        try {
            em.persist(pieceType);
        } catch (PersistenceException e) {
            throw new LegoPersistenceException("Create PieceType persistence error", e);
        }
    }

    @Override
    public void update(PieceType pieceType) throws EntityAlreadyExistsException, LegoPersistenceException {
        if (pieceType == null) {
            throw new IllegalArgumentException("PieceType entity cannot be NULL.");
        }
        if (pieceType.getName() == null) {
            throw new LegoPersistenceException("PieceType's name cannot be NULL.");
        }
        try {
            em.flush();
            em.merge(pieceType);
        } catch (PersistenceException e) {
            throw new LegoPersistenceException("Create PieceType persistence error", e);
        }
    }

    @Override
    public void delete(PieceType pieceType) throws EntityNotExistsException {
        if (pieceType == null) {
            throw new IllegalArgumentException("PieceType entity cannot be NULL.");
        }
        if (!this.checkIfExistsInDB(pieceType)) {
            throw new EntityNotExistsException("PieceType not exists in DB.");
        }

        em.remove(pieceType);
    }

    @Override
    public PieceType findById(Long id) throws EntityNotExistsException {
        if (id == null) {
            throw new IllegalArgumentException("Cannot look for PieceType entity, when id is NULL.");
        }

        if (id < 0) {
            throw new IllegalArgumentException("Cannot look for PieceType entity, when id is smaller than 0.");
        }

        try {
            return em.find(PieceType.class, id);
        } catch (NoResultException ex) {
            throw new EntityNotExistsException("PieceType not found with id \'" + id + "\'.", ex);
        }
    }

    @Override
    public PieceType findByName(String name) throws EntityNotExistsException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Cannot look for PieceType entity, when name is NULL.");
        }

        try {
            return em.createQuery("SELECT p FROM PieceType p WHERE p.name = :pieceTypeName", PieceType.class).setParameter("pieceTypeName", name).getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotExistsException("PieceType not found with name \'" + name + "\'.", ex);
        }
    }

    @Override
    public List<PieceType> findAll() {
        return em.createQuery("SELECT p FROM PieceType p", PieceType.class).getResultList();
    }

    private boolean checkIfExistsInDB(PieceType pieceType) {
        List<PieceType> existingPieceTypes = findAll();
        for (PieceType pt : existingPieceTypes) {
            if (pt.equals(pieceType)) {
                return true;
            }
        }
        return false;
    }
}
