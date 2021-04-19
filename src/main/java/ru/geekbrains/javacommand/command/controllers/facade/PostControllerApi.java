package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;

import java.util.List;

@RequestMapping("/api/v1/post")
public interface PostControllerApi {

    /**
     *Return All Errand as CurrentErrandDto which have status CONFIRMED and the current time is in the range date_start and date_and
     * @return List<CurrentErrandDto>
     */

    @Secured({"ROLE_ADMIN","ROLE_POST"})
    @GetMapping(produces = "application/json")

		List<CurrentErrandDto> getListCurrent();
}
