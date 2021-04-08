package ru.geekbrains.javacommand.command.util;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;

public class ErrandEmailMessageAlertHelper {
    public static String generateEmailMessage(ErrandDto newErrandDto) {
        return  "Статус: '" + newErrandDto.getStatusType() + '\'' + "\n" +
                "Начало: " + newErrandDto.getDateStart() + "\n" +
                "Завершение: " + newErrandDto.getDateEnd() + "\n" +
                "Сотрудник: '" + newErrandDto.getEmployeeFIO() + '\'' + "\n" +
                "Департамент: '" + newErrandDto.getDepartmentTitle() + '\'' + "\n" +
                "Место: '" + newErrandDto.getPlace() + '\'' + "\n" +
                "Комментарий: '" + newErrandDto.getComment() + '\'' + "\n" +
                "Создана: '" + newErrandDto.getCreatedByFIO() + '\'' + "\n" +
                "Подтверждена:'" + newErrandDto.getConfirmedOrRejectedByFIO() + '\'' + "\n";
    }

}
