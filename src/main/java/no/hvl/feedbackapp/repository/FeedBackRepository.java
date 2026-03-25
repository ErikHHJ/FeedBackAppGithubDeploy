package no.hvl.feedbackapp.repository;

import no.hvl.feedbackapp.model.FeedBack;
import no.hvl.feedbackapp.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
    public FeedBack findByClientIdAndLecture(String clientId,  Lecture lecture);
}
