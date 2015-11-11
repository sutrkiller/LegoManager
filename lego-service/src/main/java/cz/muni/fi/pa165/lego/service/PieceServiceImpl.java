
package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.dao.PieceDao;
import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 */

@Service
public class PieceServiceImpl implements PieceService {
    
    @Inject
    private PieceDao pieceDao;

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
