package no.hvl.feedbackapp.repository;

import no.hvl.feedbackapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
