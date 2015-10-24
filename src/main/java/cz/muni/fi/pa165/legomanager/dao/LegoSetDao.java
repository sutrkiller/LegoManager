package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.LegoSet;

import java.util.List;

/**
 * @author Marek Abaffy <abaffy.m@gmail.com>
 * @date 24.10.2015
 */
public interface LegoSetDao {

    public LegoSet findById(Long id);

    public void create(LegoSet ls);

    public void delete(LegoSet ls);

    public List<LegoSet> findAll();

}
