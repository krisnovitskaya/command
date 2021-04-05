package ru.geekbrains.javacommand.command.services;

import org.springframework.data.jpa.domain.Specification;

import java.io.ByteArrayInputStream;
import java.util.List;
import ru.geekbrains.javacommand.command.dtos.ErrandCreateDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDeleteDto;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandRemoveDto;
import ru.geekbrains.javacommand.command.dtos.ErrandUpdateDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.util.PageImpl;

public interface ErrandService {

    /**
     * @param id
     * @return ErrandDto
     */
    ErrandDto findErrandById(Long id);

    /**
     * @param errandCreateDtoList
     * @return List ErrandUpdateDto
     */
    List<ErrandUpdateDto> createErrands(List<ErrandCreateDto> errandCreateDtoList);

    /**
     * @param errandUpdateDtoList
     * @return List ErrandUpdateDto
     */
    List<ErrandUpdateDto> updateErrands(List<ErrandUpdateDto> errandUpdateDtoList);

    /**
     * @param idsList
     * @return List ErrandDto
     */
    List<ErrandDeleteDto> deleteErrands(List<Long> idsList);

    /**
     * @param idsList
     * @return List ErrandDto
     */
    List<ErrandRemoveDto> removeErrands(List<Long> idsList);

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
