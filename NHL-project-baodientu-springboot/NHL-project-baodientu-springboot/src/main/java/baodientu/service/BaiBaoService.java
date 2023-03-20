package baodientu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import baodientu.entity.BaiBao;
import baodientu.entity.DanhMuc;
import baodientu.form.BaiBaoFilterForm;
import baodientu.form.CreateBaiBaoForm;
import baodientu.form.UpdateBaiBaoForm;
import baodientu.repository.IBaiBaoRepository;
import baodientu.repository.IDanhMucRepository;
import baodientu.specification.baibao.BaiBaoSpecification;
import baodientu.utils.FileManager;

@Service
@Transactional
public class BaiBaoService implements IBaiBaoService {

	@Autowired
	private IBaiBaoRepository baiBaoRepository;

	@Autowired
	private IDanhMucRepository danhMucRepository;

	@Autowired
	private ModelMapper modelMapper;

	private FileManager fileManager = new FileManager();
	private String linkFolder = "C:\\Users\\Long\\Desktop\\anhdemo";

	@Override
	public Page<BaiBao> getAllBaiBaos(Pageable pageable, String search, BaiBaoFilterForm filterForm) {
		Specification<BaiBao> where = BaiBaoSpecification.buildWhere(search, filterForm);
		return baiBaoRepository.findAll(where, pageable);
	}

	@Override
	public BaiBao getBaiBaoById(int id) {
		return baiBaoRepository.findById(id).get();
	}

	@Override
	public BaiBao getBaiBaoByTieuDe(String tieuDe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBaiBao(CreateBaiBaoForm form) {
		// them moi bai bao
		BaiBao baiBao = new BaiBao();
		baiBao.setTieuDe(form.getTieuDe());
		baiBao.setGioiThieuNgan(form.getGioiThieuNgan());
		baiBao.setNoiDung(form.getNoiDung());

		//them tham danh muc cho bai bao
		List<DanhMuc> listDanhMuc = new ArrayList<>();

		List<Integer> danhMucIds = form.getDanhMucId();

		if (danhMucIds != null && !danhMucIds.isEmpty()) {
			for (Integer id : danhMucIds) {
				DanhMuc danhmuc = danhMucRepository.findById(id).get();

				listDanhMuc.add(danhmuc);
			}
			baiBao.setDanhMucs(listDanhMuc);
		}

		baiBaoRepository.save(baiBao);
	}

	@Override
	public void updateBaiBao(int id, UpdateBaiBaoForm form) {
		form.setId(id);

		BaiBao baiBao = baiBaoRepository.findById(id).get();

		baiBao.setTieuDe(form.getTieuDe());
		baiBao.setGioiThieuNgan(form.getGioiThieuNgan());
		baiBao.setNoiDung(form.getNoiDung());

		List<DanhMuc> oldListDanhMuc = baiBao.getDanhMucs();
		// Xóa các danh mục không được chọn trong form
		oldListDanhMuc = null;

		List<DanhMuc> listDanhMuc = new ArrayList<>();

		List<Integer> danhMucIds = form.getDanhMucId();

		if (danhMucIds != null && !danhMucIds.isEmpty()) {
			for (Integer danhMucId : danhMucIds) {
				DanhMuc danhmuc = danhMucRepository.findById(danhMucId).get();

				listDanhMuc.add(danhmuc);
			}
			baiBao.setDanhMucs(listDanhMuc);
		}

		baiBaoRepository.save(baiBao);
	}

	@Override
	public void deleteBaiBaoById(int id) {
		baiBaoRepository.deleteById(id);
	}

	@Override
	public void uploadAnhTieuDe(MultipartFile image, int id) throws IllegalStateException, IOException {
		BaiBao baiBao = baiBaoRepository.findById(id).get();

		String nameImage = new Date().getTime() + "." + fileManager.getFormatFile(image.getOriginalFilename());

		String path = linkFolder + "\\" + nameImage;

		fileManager.createNewMultiPartFile(path, image);

		baiBao.setAnhTieuDe(path);
		baiBaoRepository.save(baiBao);
	}

}
