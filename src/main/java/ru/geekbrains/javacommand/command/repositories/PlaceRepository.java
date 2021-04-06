package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
	Place findPlaceById(Long id);
}
