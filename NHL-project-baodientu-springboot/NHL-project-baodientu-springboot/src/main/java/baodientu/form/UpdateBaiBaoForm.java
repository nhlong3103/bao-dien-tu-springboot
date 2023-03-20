package baodientu.form;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBaiBaoForm {
	
	private int id;

	private String tieuDe;

	private String gioiThieuNgan;

	private String noiDung;

	private List<Integer> danhMucId;
}
