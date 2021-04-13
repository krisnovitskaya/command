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
import ru.geekbrains.javacommand.command.entities.FileEntity;

@Repository
public interface FileRepository
    extends JpaRepository<FileEntity, Long>, JpaSpecificationExecutor<Errand> {

  @Query(
      "SELECT f FROM FileEntity as f JOIN Errand as e ON f.errand.id = e.id WHERE e.id in (:errandsidslist)")
  List<FileEntity> getFilesByErrandsIds(@Param("errandsidslist") List<Long> errandsIdsList);

	@Query("SELECT f FROM FileEntity as f WHERE f.id = :id")
  FileEntity getFileById(@Param("id") Long id);

  @Query("SELECT f FROM FileEntity as f WHERE f.id in (:idslist)")
  List<FileEntity> getFilesByIdsList(@Param("idslist") List<Long> idsList);

  @Query("SELECT f FROM FileEntity as f")
  List<FileEntity> getAllFiles();
}
