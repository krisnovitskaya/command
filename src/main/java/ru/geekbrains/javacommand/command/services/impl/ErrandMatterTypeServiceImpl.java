package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.ErrandMatterTypeRepository;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;

@Service
@RequiredArgsConstructor
public class ErrandMatterTypeServiceImpl implements ErrandMatterTypeService {
    
	private final ErrandMatterTypeRepository matterTypeRepository;

	@Override
	public List<ErrandMatterDto> findAll() {
		return matterTypeRepository.findAll().stream().map(ErrandMatterDto::new).collect(Collectors.toList());
	}

	@Override
	public ErrandMatterType findErrandMatterTypeById(Long id) {
		return matterTypeRepository.findErrandMatterTypeById(id);
	}

	@Override
	public ErrandMatterDto findById(Long id) {
		ErrandMatterType errandMatterType = matterTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("ErrandMatterType with id = " + id + " not found", id)));
		return new ErrandMatterDto(errandMatterType);
	}

	@Override
	public ErrandMatterType convertToErrandMatterType(ErrandMatterDto errandMatterDto){
		ErrandMatterType errandMatterType = new ErrandMatterType();
		errandMatterType.setId(errandMatterDto.getId());
		errandMatterType.setMatter(errandMatterDto.getMatter());
		return errandMatterType;
	}
}
