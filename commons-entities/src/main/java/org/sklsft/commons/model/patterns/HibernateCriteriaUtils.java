package org.sklsft.commons.model.patterns;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.sklsft.commons.api.model.OrderType;
import org.sklsft.commons.text.StringUtils;

/**
 * Some Hibernate criteria functions used to build restrictions
 * <br>We assume sql function normalize is present in db
 * 
 * @author Nicolas Thibault
 *
 */
public class HibernateCriteriaUtils {

	public static Criteria addStringContainsRestriction(Criteria criteria, String field, String value) {
		if (!StringUtils.isEmpty(value)) {
			criteria = criteria.add(Restrictions.sqlRestriction("normalize(" + field + ") like ?", "%" + StringUtils.normalize(value) + "%", StringType.INSTANCE));
		}
		return criteria;
	}
	
	public static <T extends Comparable<T>> Criteria addBetweenRestriction(Criteria criteria, String field, T minValue, T maxValue) {
		if (minValue != null) {
			criteria = criteria.add(Restrictions.ge(field, minValue));
		}
		if (maxValue != null) {
			criteria = criteria.add(Restrictions.le(field, maxValue));
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
