package org.sklsft.commons.model.patterns;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.sklsft.commons.api.model.OrderType;
import org.sklsft.commons.text.StringUtils;

/**
 * Some Hibernate criteria functions used to build restrictions
 * <br>We assume some sql functions are present in db such as :
 * <li>unaccent
 * <li>long_to_string
 * <li>date_to_string
 * 
 * @author Nicolas Thibault
 *
 */
public class HibernateCriteriaUtils {

	public static Criteria addStringContainsRestriction(Criteria criteria, String field, String value) {
		if (!StringUtils.isEmpty(value)) {
			criteria = criteria.add(Restrictions.sqlRestriction("unaccent(" + field + ") like ?", "%" + StringUtils.unaccent(value) + "%", StringType.INSTANCE));
		}
		return criteria;
	}
	
	public static Criteria addLongContainsRestriction(Criteria criteria, String field, String value) {
		if (!StringUtils.isEmpty(value)) {
			criteria = criteria.add(Restrictions.sqlRestriction("long_to_string(" + field + ") like ?", "%" + value.toLowerCase() + "%", StringType.INSTANCE));
		}
		return criteria;
	}
	
	public static Criteria addDateContainsRestriction(Criteria criteria, String field, String value) {
		if (!StringUtils.isEmpty(value)) {
			criteria = criteria.add(Restrictions.sqlRestriction("date_to_string(" + field + ") like ?", "%" + value.toLowerCase() + "%", StringType.INSTANCE));
		}
		return criteria;
	}
	
	public static Criteria addBooleanRestriction(Criteria criteria, String field, Boolean value) {
		if (value != null) {
			criteria = criteria.add(Restrictions.eq(field, value));
		}
		return criteria;
	}
	
	public static Criteria addOrder(Criteria criteria, String property, OrderType orderType) {
		if (orderType != null) {
			if (orderType.equals(OrderType.ASC)) {
				criteria.addOrder(Order.asc(property));
			} else {
				criteria.addOrder(Order.desc(property));
			}
		}
		return criteria;
	}
}
