/**
 * This class is generated by jOOQ
 */
package com.dozortsev.adviceexchange.domain.jooq.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Question extends UserActivity implements java.io.Serializable {

	private static final long serialVersionUID = 775347022;

	private java.lang.Integer id;
	private java.lang.String  title;

	public Question() {}

	public Question(
		java.lang.Integer id,
		java.lang.String  title
	) {
		this.id = id;
		this.title = title;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}
}
