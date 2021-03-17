package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;

@Repository
public interface ErrandStatusTypeRepository extends JpaRepository<ErrandStatusType, Long> {
}
