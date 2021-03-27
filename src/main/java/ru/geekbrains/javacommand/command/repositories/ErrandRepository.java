/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Errand;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Repository
public interface ErrandRepository extends JpaRepository<Errand, Long> {
	
	Errand findErrandById(Long id);
	
}
