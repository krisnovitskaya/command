package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;

@Repository
public interface ErrandMatterTypeRepository extends JpaRepository<ErrandMatterType, Long> {
	
	ErrandMatterType findErrandMatterTypeById(Long id);
}
