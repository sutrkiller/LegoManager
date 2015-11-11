package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.legomanager.entities.Piece;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 11.11.2015
 */

@Service
public interface PieceService {

    public void create(Piece piece);

    public void update(Piece piece);

    public void delete(Piece piece);

    public Piece findById(Long id);

    public Piece findByName(String name);

    public List<Piece> findAll();
}
