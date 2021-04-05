package ru.geekbrains.javacommand.command.services;

import org.springframework.data.jpa.domain.Specification;

import java.io.ByteArrayInputStream;
import java.util.List;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.util.PageImpl;

public interface ErrandService {

    /**
     * @param id
     * @return ErrandDto
     */
    ErrandDto findErrandById(Long id);

    /**
     * @param errandDtoList
     * @return List ErrandDto
     */
    List<ErrandDto> createErrands(List<ErrandDto> errandDtoList);

    /**
     * @param errandDtoList
     * @return List ErrandDto
     */
    List<ErrandDto> updateErrands(List<ErrandDto> errandDtoList);

    /**
     * @param idsList
     * @return List ErrandDto
     */
    List<ErrandDto> deleteErrands(List<Long> idsList);

    /**
     * @param idsList
     * @return List ErrandDto
     */
    List<ErrandDto> removeErrands(List<Long> idsList);

    /**
     * @return List ErrandDto
     */
    List<ErrandDto> getListCurrent();

    /**
     * @param spec
     * @param page
     * @param size
     * @return PageImpl ErrandDto
     */
    PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size);

    /**
     *
     * @param spec
     * @return ByteArrayInputStream report
     */
    ByteArrayInputStream findAllForReport(Specification<Errand> spec);
}
