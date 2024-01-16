package org.sklsft.commons.model.patterns;

import java.util.List;

import org.sklsft.commons.api.model.OrderType;
import org.sklsft.commons.text.StringUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;

/**
 * Some Jpa predicate functions used to build restrictions
 * 
 * @author Nicolas Thibault
 *
 */
public class JpaCriteriaUtils {
	
	public static Predicate getUnaccentuatedStringContainsRestriction(CriteriaBuilder builder, Expression<String> expression, String value) {	
		if (!StringUtils.isEmpty(value)) {
			return builder.like(builder.function("normalize", String.class, expression), "%" + StringUtils.normalize(value) + "%");
		}
		return null;
	}

	public static void addUnaccentuatedStringContainsRestriction(CriteriaBuilder builder, List<Predicate> predicates, Expression<String> expression, String value) {	
		if (!StringUtils.isEmpty(value)) {
			predicates.add(builder.like(builder.function("normalize", String.class, expression), "%" + StringUtils.normalize(value) + "%"));
		}
	}
	
	public static Predicate getStringContainsRestriction(CriteriaBuilder builder, Expression<String> expression, String value) {	
		if (!StringUtils.isEmpty(value)) {
			return builder.like(builder.lower(expression), "%" + value.toLowerCase() + "%");
		}
		return null;
	}

	public static void addStringContainsRestriction(CriteriaBuilder builder, List<Predicate> predicates, Expression<String> expression, String value) {	
		if (!StringUtils.isEmpty(value)) {
			predicates.add(builder.like(builder.lower(expression), "%" + value.toLowerCase() + "%"));
		}
	}
	
	public static Predicate getStringStartsWithRestriction(CriteriaBuilder builder, Expression<String> expression, String value) {	
		if (!StringUtils.isEmpty(value)) {
			return builder.like(builder.lower(expression), value.toLowerCase() + "%");
		}
		return null;
	}

	public static void addStringStartsWithRestriction(CriteriaBuilder builder, List<Predicate> predicates, Expression<String> expression, String value) {	
		if (!StringUtils.isEmpty(value)) {
			predicates.add(builder.like(builder.lower(expression), value.toLowerCase() + "%"));
		}
	}
	
	public static <T extends Comparable<? super T>> void addBetweenRestriction(CriteriaBuilder builder, List<Predicate> predicates, Expression<T> expression, T minValue, T maxValue) {
		if (minValue != null) {
			predicates.add(builder.greaterThanOrEqualTo(expression, minValue));
		}
		if (maxValue != null) {
			predicates.add(builder.lessThanOrEqualTo(expression, maxValue));
		}
	}
	
	public static void addBooleanRestriction(CriteriaBuilder builder, List<Predicate> predicates, Expression<Boolean> expression, Boolean value) {
		if (value != null) {			
			predicates.add(builder.equal(expression, value));
		}
	}
	
	public static void addEqualsRestriction(CriteriaBuilder builder, List<Predicate> predicates, Expression<?> expression, Object value) {
		if (value != null) {			
			predicates.add(builder.equal(expression, value));
		} else {
			predicates.add(builder.isNull(expression));
		}
	}
	
	public static void addEqualsIfNotNullRestriction(CriteriaBuilder builder, List<Predicate> predicates, Expression<?> expression, Object value) {
		if (value != null) {			
			predicates.add(builder.equal(expression, value));
		}
	}
	
	public static void addOrder(CriteriaBuilder builder, List<Order> orders, Expression<?> expression, OrderType orderType) {
		if (orderType != null) {
			if (orderType.equals(OrderType.ASC)) {
				orders.add(builder.asc(expression));
			} else {
				orders.add(builder.desc(expression));
			}
		}
	}
}
