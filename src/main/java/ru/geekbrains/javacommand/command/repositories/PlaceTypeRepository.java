package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.PlaceType;


@Repository
public interface PlaceTypeRepository extends JpaRepository<PlaceType, Long> {
}
