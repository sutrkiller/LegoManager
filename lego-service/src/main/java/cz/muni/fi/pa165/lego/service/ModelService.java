package cz.muni.fi.pa165.lego.service;

import cz.muni.fi.pa165.lego.service.exceptions.LegoServiceException;
import cz.muni.fi.pa165.legomanager.entities.Category;
import cz.muni.fi.pa165.legomanager.entities.Model;
import cz.muni.fi.pa165.legomanager.entities.Piece;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An interface that defines a service access to the {@link Model} entity.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
@Service
public interface ModelService {

    void create(Model model);

    void update(Model model);

    void delete(Model model);

    Model findById(Long id);

    Model findByName(String name);

    List<Model> findAll();

    void setFiftyPercentDiscount(Model model);

    void addPiece(Model model, Piece piece);

    void removePiece(Model model, Piece piece);

    void changeCategory(Model model, Category category);

}
