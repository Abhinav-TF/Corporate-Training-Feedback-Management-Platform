package com.tnf.training.dao;

import java.util.List;
import java.util.Optional;

import com.tnf.training.entity.Feedback;
public interface FeedbackDAO {

    // Save a new feedback and return it with the generated id.
    Feedback save(Feedback feedback);

    // Find one feedback by id, or empty if not found.
    Optional<Feedback> findById(Long feedbackId);

    // Get all feedback records.
    List<Feedback> findAll();

    // Get all feedback for a trainer.
    List<Feedback> findByTrainer(Long trainerId);

    // Get all feedback for a batch.
    List<Feedback> findByBatch(Long batchId);
}
