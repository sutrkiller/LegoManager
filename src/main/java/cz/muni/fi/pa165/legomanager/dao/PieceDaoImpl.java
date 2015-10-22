package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.util.List;
import javax.persistence.EntityManager;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Piece piece) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Piece piece) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Piece findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Piece findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Piece> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
