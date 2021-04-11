package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDetailsDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.repositories.ErrandDetailsRepository;
import ru.geekbrains.javacommand.command.services.ErrandDetailsService;

@Service
@RequiredArgsConstructor
public class ErrandDetailsServiceImpl implements ErrandDetailsService {
    private final ErrandDetailsRepository errandDetailsRepository;

    @Override
    public void saveErrandDetails(ErrandDetailsDto errandDetailsDto) {
          ErrandDetails errandDetails = new ErrandDetails();
          errandDetails.setId(null);
//        errand.getErrandDetails().setMatter(errandMatterTypeRepository.findErrandMatterTypeByMatter(errandDto.getMatter()));
//        errand.getErrandDetails().setPlace(placeRepository.findPlaceByTitle(errandDto.getPlace()));
//        errand.getErrandDetails().setComment(errandDto.getComment());
    errandDetailsRepository.save(errandDetails);
    }
}
