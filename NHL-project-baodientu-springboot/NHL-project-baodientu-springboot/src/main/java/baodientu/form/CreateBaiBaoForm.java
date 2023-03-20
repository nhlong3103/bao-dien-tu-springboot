package baodientu.form;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBaiBaoForm {

	private String tieuDe;

	private String gioiThieuNgan;

	private String noiDung;

	private List<Integer> danhMucId;
}