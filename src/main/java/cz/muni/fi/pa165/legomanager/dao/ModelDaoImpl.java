/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entities.Model;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Repository
public class ModelDaoImpl implements ModelDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Model model) {
        em.persist(model);
    }

    @Override
    public Model findById(Long id) {
        return em.find(Model.class, id);
    }

    @Override
    public Model findByName(String name) {
        return em.createQuery("SELECT m FROM Model m WHERE m.name = :name ",
                Model.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Model> findAll() {
        return em.createQuery("SELECT m FROM Model m", Model.class).getResultList();
    }

    @Override
    public Model update(Model model) {
        return em.merge(model);
    }

    @Override
    public void delete(Model model) {
        em.remove(model);
    }

}
