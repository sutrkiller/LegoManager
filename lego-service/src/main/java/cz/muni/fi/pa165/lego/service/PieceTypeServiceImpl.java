package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.dao.PieceTypeDao;
import cz.muni.fi.pa165.legomanager.entities.PieceType;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 */

@Service
public class PieceTypeServiceImpl implements PieceTypeService {
    
    @Inject
    private PieceTypeDao pieceTypeDao;

    @Override
    public void create(PieceType pieceType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(PieceType pieceType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(PieceType pieceType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PieceType findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PieceType findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PieceType> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
