package baodientu.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BaiBao")
@Data
@NoArgsConstructor
public class BaiBao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tieu_de", length = 255, nullable = false, unique = true)
	private String tieuDe;

	@Column(name = "gioi_thieu_ngan", length = 110, nullable = false)
	private String gioiThieuNgan;

	@Column(name = "anh_tieu_de", length = 255, nullable = true)
	private String anhTieuDe;

	@Column(name = "noi_dung", nullable = false)
	private String noiDung;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date updatedDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Danhmuc_Baibao",
	    joinColumns = {@JoinColumn(name = "baibao_id", referencedColumnName = "id")},
	    inverseJoinColumns = {@JoinColumn(name = "danhmuc_id", referencedColumnName = "id")})
	private List<DanhMuc> danhMucs;
}
