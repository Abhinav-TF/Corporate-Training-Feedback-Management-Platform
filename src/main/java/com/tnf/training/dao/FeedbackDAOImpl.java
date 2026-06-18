package com.tnf.training.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tnf.training.entity.Feedback;
import com.tnf.training.util.HibernateUtil;

public class FeedbackDAOImpl implements FeedbackDAO {

    @Override
    public Feedback save(Feedback feedback) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(feedback);
            tx.commit();
            return feedback;
        } catch (RuntimeException ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback(); // undo partial write on failure
            }
            throw ex;
        }
    }

    @Override
    public Optional<Feedback> findById(Long feedbackId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Feedback.class, feedbackId));
        }
    }

    @Override
    public List<Feedback> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Feedback f", Feedback.class).getResultList();
        }
    }

    @Override
    public List<Feedback> findByTrainer(Long trainerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Feedback f WHERE f.trainer.trainerId = :trainerId", Feedback.class)
                    .setParameter("trainerId", trainerId)
                    .getResultList();
        }
    }

    @Override
    public List<Feedback> findByBatch(Long batchId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Feedback f WHERE f.trainingBatch.batchId = :batchId", Feedback.class)
                    .setParameter("batchId", batchId)
                    .getResultList();
        }
    }
}
