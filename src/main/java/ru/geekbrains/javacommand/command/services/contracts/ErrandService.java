/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.contracts;

import org.springframework.data.jpa.domain.Specification;

import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.util.MultiValueMap;
import ru.geekbrains.javacommand.command.dtos.CreatedErrandDto;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandStatisticDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.entities.User;
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
     * @param errand
     */
    void saveErrand(Errand errand);

    /**
     * @param errand
     * @return
     */
    ErrandDto saveOrUpdate(Errand errand);

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
    List<CurrentErrandDto> getListCurrent();

    /**
     * @param spec
     * @param page
     * @param size
     * @return PageImpl ErrandDto
     */
    PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size);

    /**
     * @param page
     * @param params
     * @param user
     * @return PageImpl ErrandDto
     */
    PageImpl<ErrandDto> findErrandsByMaster(int page, MultiValueMap<String, String> params, User user);

    /**
     * @param spec
     * @return ByteArrayInputStream report
     */
    ByteArrayInputStream findAllForReport(Specification<Errand> spec);

    /**
     * ?????????????????? ???????????? ???????????????????????? ???? ???????????? ?? ??????????????
     *
     * @param errandId
     * @param errandStatusType
     * @author jackwizard88
     */
    void updateErrandStatus(Long errandId, ErrandStatusType errandStatusType);
	/**
	 *
	 * @param errand
	 */
	void updateErrands(ErrandDto errand);

	void saveErrand(CreatedErrandDto errandDto);

    List<ErrandStatisticDto> findAllByParams(Specification<Errand> spec);
}
