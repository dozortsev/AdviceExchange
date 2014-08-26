/**
 * This class is generated by jOOQ
 */
package com.dozortsev.adviceexchange.domain.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserBadgeRecord extends org.jooq.impl.TableRecordImpl<com.dozortsev.adviceexchange.domain.jooq.tables.records.UserBadgeRecord> implements org.jooq.Record2<java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = 580941883;

	/**
	 * Setter for <code>adviceexchange.user_badge.ub_badge_id</code>.
	 */
	public void setUbBadgeId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>adviceexchange.user_badge.ub_badge_id</code>.
	 */
	public java.lang.Integer getUbBadgeId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>adviceexchange.user_badge.ub_user_id</code>.
	 */
	public void setUbUserId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>adviceexchange.user_badge.ub_user_id</code>.
	 */
	public java.lang.Integer getUbUserId() {
		return (java.lang.Integer) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.UserBadge.USER_BADGE.UB_BADGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.UserBadge.USER_BADGE.UB_USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getUbBadgeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getUbUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserBadgeRecord value1(java.lang.Integer value) {
		setUbBadgeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserBadgeRecord value2(java.lang.Integer value) {
		setUbUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserBadgeRecord values(java.lang.Integer value1, java.lang.Integer value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UserBadgeRecord
	 */
	public UserBadgeRecord() {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.UserBadge.USER_BADGE);
	}

	/**
	 * Create a detached, initialised UserBadgeRecord
	 */
	public UserBadgeRecord(java.lang.Integer ubBadgeId, java.lang.Integer ubUserId) {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.UserBadge.USER_BADGE);

		setValue(0, ubBadgeId);
		setValue(1, ubUserId);
	}
}