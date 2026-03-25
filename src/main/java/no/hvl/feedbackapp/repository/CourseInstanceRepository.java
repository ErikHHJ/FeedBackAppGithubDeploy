package no.hvl.feedbackapp.repository;

import no.hvl.feedbackapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseInstanceRepository extends JpaRepository<Course, Long> {
}
