/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Errand;
import java.util.List;

@Repository
public interface ErrandRepository extends JpaRepository<Errand, Long> {

  Errand findErrandById(Long id);
	
	@Override
	Errand save(Errand errand);	
	
  @Query(
      "select e from Errand e join fetch e.employee emp join fetch emp.department"
			+ " where e.dateStart < current_timestamp and e.dateEnd > current_timestamp"
			+ " and e.statusType.status = 'CONFIRMED'")
  List<Errand> findCurrent();

}
