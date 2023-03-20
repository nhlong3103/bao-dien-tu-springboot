package baodientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import baodientu.entity.DanhMuc;

public interface IDanhMucRepository extends JpaRepository<DanhMuc, Integer>, JpaSpecificationExecutor<DanhMuc>{
	
	List<DanhMuc> findByIdIn(List<Integer> ids);
}
