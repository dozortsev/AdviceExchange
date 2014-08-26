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
public class QuestionRecord extends org.jooq.impl.UpdatableRecordImpl<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionRecord> implements org.jooq.Record4<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = -955862481;

	/**
	 * Setter for <code>adviceexchange.question.qs_id</code>.
	 */
	public void setQsId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>adviceexchange.question.qs_id</code>.
	 */
	public java.lang.Integer getQsId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>adviceexchange.question.qs_title</code>.
	 */
	public void setQsTitle(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>adviceexchange.question.qs_title</code>.
	 */
	public java.lang.String getQsTitle() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>adviceexchange.question.qs_votes</code>.
	 */
	public void setQsVotes(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>adviceexchange.question.qs_votes</code>.
	 */
	public java.lang.Integer getQsVotes() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>adviceexchange.question.qs_asw_count</code>.
	 */
	public void setQsAswCount(java.lang.Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>adviceexchange.question.qs_asw_count</code>.
	 */
	public java.lang.Integer getQsAswCount() {
		return (java.lang.Integer) getValue(3);
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
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.Question.QUESTION.QS_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.Question.QUESTION.QS_TITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.Question.QUESTION.QS_VOTES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field4() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.Question.QUESTION.QS_ASW_COUNT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getQsId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getQsTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getQsVotes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value4() {
		return getQsAswCount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuestionRecord value1(java.lang.Integer value) {
		setQsId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuestionRecord value2(java.lang.String value) {
		setQsTitle(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuestionRecord value3(java.lang.Integer value) {
		setQsVotes(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuestionRecord value4(java.lang.Integer value) {
		setQsAswCount(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuestionRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.Integer value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached QuestionRecord
	 */
	public QuestionRecord() {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.Question.QUESTION);
	}

	/**
	 * Create a detached, initialised QuestionRecord
	 */
	public QuestionRecord(java.lang.Integer qsId, java.lang.String qsTitle, java.lang.Integer qsVotes, java.lang.Integer qsAswCount) {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.Question.QUESTION);

		setValue(0, qsId);
		setValue(1, qsTitle);
		setValue(2, qsVotes);
		setValue(3, qsAswCount);
	}
}