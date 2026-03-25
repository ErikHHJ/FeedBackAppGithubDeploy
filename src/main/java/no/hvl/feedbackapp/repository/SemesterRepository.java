package no.hvl.feedbackapp.repository;

import no.hvl.feedbackapp.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
}
