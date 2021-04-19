/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.FileEntity;

@Repository
public interface FileRepository
    extends JpaRepository<FileEntity, Long>, JpaSpecificationExecutor<Errand> {

  @Query(
      "SELECT fe FROM FileEntity as fe JOIN Errand as e ON fe.errand.id = e.id WHERE e.id in (:errandsidslist) AND fe.deleted = 'false'")
  List<FileEntity> getFilesByErrandsIds(@Param("errandsidslist") List<Long> errandsIdsList);

	@Query("SELECT fe FROM FileEntity as fe WHERE fe.id = :id AND fe.deleted = 'false'")
  FileEntity getFileById(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FileEntity SET deleted = 'true' WHERE id = :id")
  void deleteFileById(@Param("id") Long id);

	@Query("SELECT fe FROM FileEntity as fe WHERE fe.id in (:idslist) AND fe.deleted = 'false'")
  List<FileEntity> getFilesByIdsList(@Param("idslist") List<Long> idsList);

  @Query("SELECT fe FROM FileEntity as fe WHERE fe.deleted = 'false'")
  List<FileEntity> getAllFiles();
}
