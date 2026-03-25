package no.hvl.feedbackapp.repository;

import no.hvl.feedbackapp.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    Lecture findByToken(String token);
}
