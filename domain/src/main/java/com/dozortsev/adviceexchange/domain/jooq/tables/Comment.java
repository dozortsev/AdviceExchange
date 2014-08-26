/**
 * This class is generated by jOOQ
 */
package com.dozortsev.adviceexchange.domain.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Comment extends org.jooq.impl.TableImpl<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord> {

	private static final long serialVersionUID = 393548734;

	/**
	 * The singleton instance of <code>adviceexchange.comment</code>
	 */
	public static final com.dozortsev.adviceexchange.domain.jooq.tables.Comment COMMENT = new com.dozortsev.adviceexchange.domain.jooq.tables.Comment();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord> getRecordType() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord.class;
	}

	/**
	 * The column <code>adviceexchange.comment.cm_id</code>.
	 */
	public final org.jooq.TableField<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord, java.lang.Integer> CM_ID = createField("cm_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>adviceexchange.comment.cm_question_id</code>.
	 */
	public final org.jooq.TableField<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord, java.lang.Integer> CM_QUESTION_ID = createField("cm_question_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>adviceexchange.comment</code> table reference
	 */
	public Comment() {
		this("comment", null);
	}

	/**
	 * Create an aliased <code>adviceexchange.comment</code> table reference
	 */
	public Comment(java.lang.String alias) {
		this(alias, com.dozortsev.adviceexchange.domain.jooq.tables.Comment.COMMENT);
	}

	private Comment(java.lang.String alias, org.jooq.Table<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord> aliased) {
		this(alias, aliased, null);
	}

	private Comment(java.lang.String alias, org.jooq.Table<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.dozortsev.adviceexchange.domain.jooq.Adviceexchange.ADVICEEXCHANGE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord, java.lang.Integer> getIdentity() {
		return com.dozortsev.adviceexchange.domain.jooq.Keys.IDENTITY_COMMENT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord> getPrimaryKey() {
		return com.dozortsev.adviceexchange.domain.jooq.Keys.KEY_COMMENT_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord>>asList(com.dozortsev.adviceexchange.domain.jooq.Keys.KEY_COMMENT_PRIMARY, com.dozortsev.adviceexchange.domain.jooq.Keys.KEY_COMMENT_CM_ID, com.dozortsev.adviceexchange.domain.jooq.Keys.KEY_COMMENT_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord, ?>>asList(com.dozortsev.adviceexchange.domain.jooq.Keys.FK_CM_ID, com.dozortsev.adviceexchange.domain.jooq.Keys.FK_CM_QUESTION_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.dozortsev.adviceexchange.domain.jooq.tables.Comment as(java.lang.String alias) {
		return new com.dozortsev.adviceexchange.domain.jooq.tables.Comment(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.dozortsev.adviceexchange.domain.jooq.tables.Comment rename(java.lang.String name) {
		return new com.dozortsev.adviceexchange.domain.jooq.tables.Comment(name, null);
	}
}