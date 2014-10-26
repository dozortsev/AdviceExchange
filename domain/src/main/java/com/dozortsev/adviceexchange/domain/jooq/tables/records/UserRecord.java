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
public class UserRecord extends org.jooq.impl.UpdatableRecordImpl<com.dozortsev.adviceexchange.domain.jooq.tables.records.UserRecord> implements org.jooq.Record11<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Byte, java.lang.Integer> {

	private static final long serialVersionUID = 1993456928;

	/**
	 * Setter for <code>adviceexchange.user.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>adviceexchange.user.name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.name</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>adviceexchange.user.age</code>.
	 */
	public void setAge(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.age</code>.
	 */
	public java.lang.Integer getAge() {
		return (java.lang.Integer) getValue(2);
	}

	/**
	 * Setter for <code>adviceexchange.user.about_me</code>.
	 */
	public void setAboutMe(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.about_me</code>.
	 */
	public java.lang.String getAboutMe() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>adviceexchange.user.joined</code>.
	 */
	public void setJoined(java.sql.Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.joined</code>.
	 */
	public java.sql.Timestamp getJoined() {
		return (java.sql.Timestamp) getValue(4);
	}

	/**
	 * Setter for <code>adviceexchange.user.location</code>.
	 */
	public void setLocation(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.location</code>.
	 */
	public java.lang.String getLocation() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>adviceexchange.user.site</code>.
	 */
	public void setSite(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.site</code>.
	 */
	public java.lang.String getSite() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>adviceexchange.user.email</code>.
	 */
	public void setEmail(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.email</code>.
	 */
	public java.lang.String getEmail() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>adviceexchange.user.password</code>.
	 */
	public void setPassword(java.lang.String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.password</code>.
	 */
	public java.lang.String getPassword() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>adviceexchange.user.enabled</code>.
	 */
	public void setEnabled(java.lang.Byte value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.enabled</code>.
	 */
	public java.lang.Byte getEnabled() {
		return (java.lang.Byte) getValue(9);
	}

	/**
	 * Setter for <code>adviceexchange.user.reputation</code>.
	 */
	public void setReputation(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>adviceexchange.user.reputation</code>.
	 */
	public java.lang.Integer getReputation() {
		return (java.lang.Integer) getValue(10);
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
	// Record11 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Byte, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row11) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Byte, java.lang.Integer> valuesRow() {
		return (org.jooq.Row11) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.AGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.ABOUT_ME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Timestamp> field5() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.JOINED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.LOCATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.SITE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.PASSWORD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field10() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.ENABLED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field11() {
		return com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER.REPUTATION;
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
	public java.lang.String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getAge();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getAboutMe();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Timestamp value5() {
		return getJoined();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getLocation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getSite();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getPassword();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value10() {
		return getEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value11() {
		return getReputation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value2(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value3(java.lang.Integer value) {
		setAge(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value4(java.lang.String value) {
		setAboutMe(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value5(java.sql.Timestamp value) {
		setJoined(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value6(java.lang.String value) {
		setLocation(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value7(java.lang.String value) {
		setSite(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value8(java.lang.String value) {
		setEmail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value9(java.lang.String value) {
		setPassword(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value10(java.lang.Byte value) {
		setEnabled(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord value11(java.lang.Integer value) {
		setReputation(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.Integer value3, java.lang.String value4, java.sql.Timestamp value5, java.lang.String value6, java.lang.String value7, java.lang.String value8, java.lang.String value9, java.lang.Byte value10, java.lang.Integer value11) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UserRecord
	 */
	public UserRecord() {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER);
	}

	/**
	 * Create a detached, initialised UserRecord
	 */
	public UserRecord(java.lang.Integer id, java.lang.String name, java.lang.Integer age, java.lang.String aboutMe, java.sql.Timestamp joined, java.lang.String location, java.lang.String site, java.lang.String email, java.lang.String password, java.lang.Byte enabled, java.lang.Integer reputation) {
		super(com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER);

		setValue(0, id);
		setValue(1, name);
		setValue(2, age);
		setValue(3, aboutMe);
		setValue(4, joined);
		setValue(5, location);
		setValue(6, site);
		setValue(7, email);
		setValue(8, password);
		setValue(9, enabled);
		setValue(10, reputation);
	}
}
