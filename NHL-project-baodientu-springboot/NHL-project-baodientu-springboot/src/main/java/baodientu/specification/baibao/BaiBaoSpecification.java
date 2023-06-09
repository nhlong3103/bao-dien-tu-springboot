package baodientu.specification.baibao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import baodientu.entity.BaiBao;
import baodientu.form.BaiBaoFilterForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class BaiBaoSpecification {

	@SuppressWarnings("deprecation")
	public static Specification<BaiBao> buildWhere(String search, BaiBaoFilterForm filterForm) {

		Specification<BaiBao> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification tieuDe = new CustomSpecification("tieuDe", search);
			where = Specification.where(tieuDe);
		}

		// if there is filter by min id
		if (filterForm != null && filterForm.getMinId() != null) {
			CustomSpecification minId = new CustomSpecification("minId", filterForm.getMinId());
			if (where == null) {
				where = minId;
			} else {
				where = where.and(minId);
			}
		}

		// if there is filter by max id
		if (filterForm != null && filterForm.getMaxId() != null) {
			CustomSpecification maxId = new CustomSpecification("maxId", filterForm.getMaxId());
			if (where == null) {
				where = maxId;
			} else {
				where = where.and(maxId);
			}
		}

		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<BaiBao> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(Root<BaiBao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (field.equalsIgnoreCase("tieuDe")) {
			return criteriaBuilder.like(root.get("tieuDe"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("minId")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
		}

		if (field.equalsIgnoreCase("maxId")) {
			return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
		}

		return null;
	}

}
