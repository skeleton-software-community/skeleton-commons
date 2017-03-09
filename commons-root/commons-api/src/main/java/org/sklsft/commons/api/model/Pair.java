package org.sklsft.commons.api.model;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * A pair consisting of two elements.
 * </p>
 * 
 * <p>
 * This class is an abstract implementation defining the basic API. It refers to
 * the elements as 'left' and 'right'. It also implements the {@code Map.Entry}
 * interface where the key is 'left' and the value is 'right'.
 * </p>
 * 
 * @param <L>
 *            the left element type
 * @param <R>
 *            the right element type
 *
 * @author Jerome RANCATI
 */
public class Pair<L, R> implements Map.Entry<L, R>, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * properties
	 */
	private L left;
	private R right;

	/*
	 * constructors
	 */
	public Pair(L left, R right) {
		super();
		this.left = left;
		this.right = right;
	}

	public Pair() {
		super();
	}

	/*
	 * getters and setters
	 */
	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}

	@Override
	public final L getKey() {
		return getLeft();
	}

	@Override
	public R getValue() {
		return getRight();
	}

	@Override
	public R setValue(R right) {
		R old = this.getRight();
		this.setValue(right);
		return old;
	}

	public Pair<L, R> of(final L left, final R right) {
		return new Pair<L, R>(left, right);
	}

	/**
	 * <p>
	 * Returns a String representation of this pair using the format
	 * {@code ($left,$right)}.
	 * </p>
	 * 
	 * @return a string describing this object, not null
	 */
	@Override
	public String toString() {
		return new StringBuilder().append('(').append(getLeft()).append(',').append(getRight()).append(')').toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair other = (Pair) obj;
		if (left == null) {
			if (other.left != null) {
				return false;
			}
		} else if (!left.equals(other.left)) {
			return false;
		}
		if (right == null) {
			if (other.right != null) {
				return false;
			}
		} else if (!right.equals(other.right)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Returns a suitable hash code. The hash code follows the definition in
	 * {@code Map.Entry}.
	 * </p>
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		// see Map.Entry API specification
		return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
	}

	/**
	 * <p>
	 * Formats the receiver using the given format.
	 * </p>
	 * 
	 * <p>
	 * This uses {@link java.util.Formattable} to perform the formatting. Two
	 * variables may be used to embed the left and right elements. Use
	 * {@code %1$s} for the left element (key) and {@code %2$s} for the right
	 * element (value). The default format used by {@code toString()} is
	 * {@code (%1$s,%2$s)}.
	 * </p>
	 * 
	 * @param format
	 *            the format string, optionally containing {@code %1$s} and
	 *            {@code %2$s}, not null
	 * @return the formatted string, not null
	 */
	public String toString(final String format) {
		return String.format(format, getLeft(), getRight());
	}

}