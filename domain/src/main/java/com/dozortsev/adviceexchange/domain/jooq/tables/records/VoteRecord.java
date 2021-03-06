/**
 * This class is generated by jOOQ
 */
package com.dozortsev.adviceexchange.domain.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VoteRecord extends org.jooq.impl.UpdatableRecordImpl<com.dozortsev.adviceexchange.domain.jooq.tables.records.VoteRecord> implements org.jooq.Record5<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.sql.Timestamp> {

	private static final long serialVersionUID = 1397072750;

	/**
	 * Setter for <code>adviceexchange.vote.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>adviceexchange.vote.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>adviceexchange.vote.user_id</code>.
	 */
	public void setUserId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>adviceexchange.vote.user_id</code>.
	 */
	public java.lang.Integer getUserId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>adviceexchange.vote.activity_id</code>.
	 */
	public void setActivityId(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>adviceexchange.vote.activity_id</code>.
	 */
	public java.lang.Integer getActivityId() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>adviceexchange.vote.score</code>.
	 */
	public void setScore(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>adviceexchange.vote.score</code>.
	 */
	public java.lang.Integer getScore() {
		return (java.lang.Integer) getValue(3);
	}

	/**
	 * Setter for <code>adviceexchange.vote.created</code>.
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>adviceexchange.vote.created</code>.
	 */
	public java.sql.Timestamp getCreated() {
		return (java.sql.Timestamp) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.sql.Timestamp> fieldsRow() {
		return (org.jooq.Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.sql.Timestamp> valuesRow() {
		return (org.jooq.Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE.ACTIVITY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE.SCORE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field5() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getActivityId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getScore();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value5() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VoteRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VoteRecord value2(java.lang.Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VoteRecord value3(java.lang.Integer value) {
		setActivityId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VoteRecord value4(java.lang.Integer value) {
		setScore(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VoteRecord value5(java.sql.Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VoteRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Integer value3, java.lang.Integer value4, java.sql.Timestamp value5) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached VoteRecord
	 */
	public VoteRecord() {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE);
	}

	/**
	 * Create a detached, initialised VoteRecord
	 */
	public VoteRecord(java.lang.Integer id, java.lang.Integer userId, java.lang.Integer activityId, java.lang.Integer score, java.sql.Timestamp created) {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE);

		setValue(0, id);
		setValue(1, userId);
		setValue(2, activityId);
		setValue(3, score);
		setValue(4, created);
	}
}
