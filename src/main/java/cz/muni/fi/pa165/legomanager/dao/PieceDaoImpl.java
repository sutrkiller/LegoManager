package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Class PieceDaoImpl implements {@link PieceDao} interface.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 23.10.2015
 */
@Repository
public class PieceDaoImpl implements PieceDao {

    @PersistenceContext
    private EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(PieceDaoImpl.class);

    @Override
    public void create(Piece piece) {
        if(piece == null) {
            throw new IllegalArgumentException("Piece entity cannot be NULL.");
        }

        em.persist(piece);
    }

    @Override
    public void update(Piece piece) {
        if(piece == null) {
            throw new IllegalArgumentException("Piece entity cannot be NULL.");
        }

        em.merge(piece);
    }

    @Override
    public void delete(Piece piece) {
        if(piece == null) {
            throw new IllegalArgumentException("Piece entity cannot be NULL.");
        }

        em.remove(piece);
    }

    @Override
    public Piece findById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Cannot look for Piece entity, when id is NULL.");
        }
        if(id < 0) {
            throw new IllegalArgumentException("Cannot look for Piece entity, when id is smaller than 0.");
        }

        return em.find(Piece.class, id);
    }

    @Override
    public Piece findByName(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Cannot look for Piece entity, when name is NULL.");
        }
        
        try {
            return em.createQuery("SELECT p FROM Piece p WHERE p.name = :pieceName", Piece.class).setParameter("pieceName", name).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Piece> findAll() {
        return em.createQuery("SELECT p FROM Piece p",Piece.class).getResultList();
    }

}
