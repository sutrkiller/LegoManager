package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.PieceType;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */

@Service
public interface PieceTypeService {

    public void create(PieceType pieceType);

    public void update(PieceType pieceType);

    public void delete(PieceType pieceType);

    public PieceType findById(Long id);

    public PieceType findByName(String name);

    public List<PieceType> findAll();
}
