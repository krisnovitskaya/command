package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.util.ErrandFilter;
import ru.geekbrains.javacommand.command.util.PageImpl;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/errands")
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {

    private final ErrandMatterTypeService matterTypeService;
    private final ErrandService errandService;

    //TODO изменить формат вывода даты на читаемый
    @GetMapping(value = "/pending", produces = "application/json")
    public PageImpl<ErrandDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                              @RequestParam Map<String, String> params,
                                              Principal principal
    ) {
        if (page < 1) {
            page = 1;
        }
        ErrandFilter errandFilter = new ErrandFilter(params);
        return errandService.findAll(errandFilter.getSpec(), page - 1, 5);
    }

    @GetMapping(value = "/types", produces = "application/json")
    public List<ErrandMatterDto> getErrandMatters() {
        return getMatters();
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.ok(errandService.findErrandById(id));
    }


    @Override
	public ResponseEntity<?> create(ErrandDto errandDto) {
    return ResponseEntity.ok(errandService.saveErrand(errandDto));
	}

	@Override
	public ResponseEntity<?> updateById(Long id, ErrandDto errandDto) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> removeById(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<ErrandMatterDto> getMatters() {
		return matterTypeService.findAll();
	}

}
