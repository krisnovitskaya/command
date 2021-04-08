package ru.geekbrains.javacommand.command.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;
import ru.geekbrains.javacommand.command.repositories.ErrandMatterTypeRepository;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;

@Service
@RequiredArgsConstructor
public class ErrandMatterTypeServiceImpl implements ErrandMatterTypeService {
    
	private final ErrandMatterTypeRepository matterTypeRepository;

	@Override
	public List<ErrandMatterDto> findAll() {
		return matterTypeRepository.findAll().stream().map(ErrandMatterDto::new).collect(Collectors.toList());
	}
    @Override
    public ErrandMatterDto findById(Long id) {
        return new ErrandMatterDto(matterTypeRepository.findById(id));
    }

	@Override
	public ErrandMatterType findErrandMatterTypeById(Long id) {
		return matterTypeRepository.findErrandMatterTypeById(id);
	}		

		@Override
    public ErrandMatterType convertToErrandMatterType(ErrandMatterDto errandMatterDto){
        ErrandMatterType errandMatterType = new ErrandMatterType();
        errandMatterType.setId(errandMatterDto.getId());
        errandMatterType.setMatter(errandMatterDto.getMatter());
        return errandMatterType;
    }
}
