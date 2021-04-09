/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.File;

@Repository
public interface FileRepository
    extends JpaRepository<File, Long>, JpaSpecificationExecutor<Errand> {

  @Query(
      "SELECT f FROM File as f JOIN Errand as e ON f.errand.id = e.id WHERE e.id in (:errandsidslist)")
  List<File> getFilesByErrandsIds(@Param("errandsidslist") List<Long> errandsIdsList);

  File getFileById(Long id);

  @Query("SELECT f FROM File as f WHERE f.id in (:idslist)")
  List<File> getFilesByIdsList(@Param("idslist") List<Long> idsList);

  @Query("SELECT f FROM File as f")
  List<File> getAllFiles();
}
