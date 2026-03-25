package no.hvl.feedbackapp.repository;

import no.hvl.feedbackapp.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseInstance, Long> {

}
