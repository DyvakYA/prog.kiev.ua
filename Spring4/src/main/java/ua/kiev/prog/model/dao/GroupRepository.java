package ua.kiev.prog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.model.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
