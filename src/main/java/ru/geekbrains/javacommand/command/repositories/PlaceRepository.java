package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Place;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
		Place findPlaceById(Long id);

    Place findPlaceByTitle(String place);

    @Query("select p from Place p where p.placeType.id = ?1")
    List<Place> findAllByPlaceTypeId(Long id);
}
