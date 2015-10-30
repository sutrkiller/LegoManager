package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;
import cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException;
import cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException;

import java.util.List;

/**
 * LegoSetDao provides CRUD operations for LegoSet entity.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
public interface LegoSetDao {

    /**
     * Creates new LegoSet in DB.
     *
     * @param ls LegoSet to be added to DB
     * @throws IllegalArgumentException when LegoSet is null
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityAlreadyExistsException when LegoSet already exists in DB.
     * @throws cz.muni.fi.pa165.legomanager.exceptions.LegoPersistenceException when entity constraints are violated (e.g. name is null)
     */
    public void create(LegoSet ls) throws LegoPersistenceException;

    /**
     * Returns LegoSet with corresponding id.
     *
     * @param id id of the LegoSet
     * @return LegoSet with corresponding id
     * @throws IllegalArgumentException when id is null or lower than 0.
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when no entity found.
     */
    public LegoSet findById(Long id) throws EntityNotExistsException;

    /**
     * Returns piece with corresponding name.
     *
     * @param name name of the LegoSet
     * @return LegoSet with corresponding name
     * @throws cz.muni.fi.pa165.legomanager.exceptions.EntityNotExistsException when entity not found.
     * @throws IllegalArgumentException when name is null
     */
    public LegoSet findByName(String name) throws EntityNotExistsException;

    /**
     * Returns list of all existing LegoSets in DB.
     *
     * @return list of all existing LegoSets
     */
    public List<LegoSet> findAll();

    /**
     * Updates already existing LegoSet in DB.
     *
     * @param ls LegoSet to be updated in DB
     */
    public void update(LegoSet ls) throws LegoPersistenceException;

    /**
     * Deletes LegoSet from DB.
     *
     * @param ls LegoSet to be deleted from DB
     * @throws IllegalArgumentException when LegoSet is null.
     * @throws EntityNotExistsException when LegoSet does not exist in db.
     */
    public void delete(LegoSet ls) throws EntityNotExistsException;


}
