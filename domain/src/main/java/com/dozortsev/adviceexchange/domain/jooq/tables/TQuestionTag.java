/**
 * This class is generated by jOOQ
 */
package com.dozortsev.adviceexchange.domain.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TQuestionTag extends org.jooq.impl.TableImpl<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord> {

	private static final long serialVersionUID = 912657496;

	/**
	 * The singleton instance of <code>adviceexchange.question_tag</code>
	 */
	public static final com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag QUESTION_TAG = new com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord> getRecordType() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord.class;
	}

	/**
	 * The column <code>adviceexchange.question_tag.qt_id</code>.
	 */
	public final org.jooq.TableField<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord, java.lang.Integer> QT_ID = createField("qt_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>adviceexchange.question_tag.tag_id</code>.
	 */
	public final org.jooq.TableField<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord, java.lang.Integer> TAG_ID = createField("tag_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>adviceexchange.question_tag</code> table reference
	 */
	public TQuestionTag() {
		this("question_tag", null);
	}

	/**
	 * Create an aliased <code>adviceexchange.question_tag</code> table reference
	 */
	public TQuestionTag(java.lang.String alias) {
		this(alias, com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag.QUESTION_TAG);
	}

	private TQuestionTag(java.lang.String alias, org.jooq.Table<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord> aliased) {
		this(alias, aliased, null);
	}

	private TQuestionTag(java.lang.String alias, org.jooq.Table<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.dozortsev.adviceexchange.domain.jooq.Adviceexchange.ADVICEEXCHANGE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionTagRecord, ?>>asList(com.dozortsev.adviceexchange.domain.jooq.Keys.FK_QT_QUESTION_ID, com.dozortsev.adviceexchange.domain.jooq.Keys.FK_QT_TAG_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag as(java.lang.String alias) {
		return new com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag rename(java.lang.String name) {
		return new com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag(name, null);
	}
}