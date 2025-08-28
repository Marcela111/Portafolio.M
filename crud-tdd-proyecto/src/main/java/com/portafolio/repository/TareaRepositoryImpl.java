package com.portafolio.repository;

import java.util.List;
import java.util.Optional;

import com.portafolio.model.Tarea;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TareaRepositoryImpl implements TareaRepository {

    private final EntityManager em;

    public TareaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Tarea save(Tarea tarea) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(tarea);
            tx.commit();
            return tarea;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        Tarea tarea = em.find(Tarea.class, id);
        return Optional.ofNullable(tarea);
    }

    @Override
    public List<Tarea> findAll() {
        // Usa el nombre de la ENTIDAD (clase) 'Tarea', no el nombre de la tabla 'TAREAS' en HQL
        return em.createQuery("SELECT t FROM Tarea t", Tarea.class).getResultList();
    }

    @Override
    public Optional<Tarea> update(Tarea tarea) {
        EntityTransaction tx = em.getTransaction();
        try {
            // Verificar si la entidad existe antes de intentar actualizar
            Tarea existingTarea = em.find(Tarea.class, tarea.getId());
            if (existingTarea == null) {
                // Si la tarea no se encuentra, no hay nada que actualizar.
                return Optional.empty();
            }

            tx.begin();
            Tarea updated = em.merge(tarea);
            tx.commit();
            return Optional.of(updated);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tarea tarea = em.find(Tarea.class, id);
            if (tarea != null) {
                em.remove(tarea);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }
}
