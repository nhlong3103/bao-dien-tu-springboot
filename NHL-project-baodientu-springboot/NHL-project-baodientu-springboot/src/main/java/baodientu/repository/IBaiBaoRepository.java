package baodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import baodientu.entity.BaiBao;

public interface IBaiBaoRepository extends JpaRepository<BaiBao, Integer>, JpaSpecificationExecutor<BaiBao> {

}
