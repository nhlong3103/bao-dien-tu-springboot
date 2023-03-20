package baodientu.controller;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import baodientu.entity.BaiBao;
import baodientu.form.BaiBaoFilterForm;
import baodientu.form.CreateBaiBaoForm;
import baodientu.form.UpdateBaiBaoForm;
import baodientu.service.IBaiBaoService;

@RestController
@RequestMapping(value = "api/baodientu-springboot/baibaos")
@CrossOrigin("*")

public class BaiBaoController {

	@Autowired
	private IBaiBaoService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public Page<BaiBao> getAllBaiBaos(Pageable pageable,
			@RequestParam(value = "search", required = false) String search, BaiBaoFilterForm filterForm) {
		return service.getAllBaiBaos(pageable, search, filterForm);
	}

	@PostMapping()
	public void createBaiBao(@RequestBody CreateBaiBaoForm form) {
		service.createBaiBao(form);
	}

	@GetMapping("/{id}")
	public BaiBao getById(@PathVariable int id) {
		return service.getBaiBaoById(id);
	}

	@PutMapping(value = "/image/{id}")
	public void upLoadImage(@RequestParam(name = "image") MultipartFile image, @PathVariable int id) throws IllegalStateException, IOException {
		service.uploadAnhTieuDe(image, id);
	}

	@PutMapping(value = "/update/{id}")
	public void updateBaiBao(@PathVariable(name = "id") int id, @RequestBody UpdateBaiBaoForm form) {
		service.updateBaiBao(id, form);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteBaiBao(@PathVariable(name = "id") int id) {
		service.deleteBaiBaoById(id);
	}
}
