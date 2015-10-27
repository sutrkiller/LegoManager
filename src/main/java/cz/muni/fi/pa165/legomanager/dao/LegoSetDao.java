package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;

import java.util.List;

/**
 * LegoSetDao provides CRUD operations for LegoSet entity.
 *
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
public interface LegoSetDao {

    /**
     * Creates new lego set in DB.
     *
     * @param ls lego set to be added to DB
     */
    public void create(LegoSet ls);

    /**
     * Returns lego set with corresponding id.
     *
     * @param id id of the lego set
     * @return lego set with corresponding id
     */
    public LegoSet findById(Long id);


    /**
     * Returns list of all existing lego sets in DB.
     *
     * @return list of all existing lego sets
     */
    public List<LegoSet> findAll();

    /**
     * Updates already existing lego set in DB.
     *
     * @param ls lego set to be updated in DB
     */
    public void update(LegoSet ls);

    /**
     * Deletes lego set from DB.
     *
     * @param ls lego set to be deleted from DB
     */
    public void delete(LegoSet ls);


}
