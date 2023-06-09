package baodientu.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import baodientu.entity.BaiBao;
import baodientu.form.BaiBaoFilterForm;
import baodientu.form.CreateBaiBaoForm;
import baodientu.form.UpdateBaiBaoForm;


public interface IBaiBaoService {
	public Page<BaiBao> getAllBaiBaos(Pageable pageable, String search, BaiBaoFilterForm form);

	public BaiBao getBaiBaoById(int id);

	public BaiBao getBaiBaoByTieuDe(String tieuDe);

	public void createBaiBao(CreateBaiBaoForm form);
	
	public void uploadAnhTieuDe(MultipartFile image, int id) throws IOException;

	public void updateBaiBao(int id, UpdateBaiBaoForm from);

	public void deleteBaiBaoById(int id);

//	public boolean isBaiBaoExistsByUsername(String username);
//
//	public List<BaiBao> getBaiBaoByDepartmentIdIsNull();
//
//	public List<BaiBao> getBaiBaoByDepartmentIdIsNullOrDepartmentIdIsParam(int departmentId);
//
//	void sendRegistrationAccountConfirm(String email);
}
