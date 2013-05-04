package org.meta.gwtworld.client.db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TbWkWork database table.
 * 
 */
@Entity
@Table(name="TbWkWork")
public class TbWkWork implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String alternateName;

	private int chapters;

	private int languageId;

	private BigDecimal length;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date nameCheckedDate;

	private String remark;

	private BigInteger size;

	private int tagGrpId;

	private int tickets;

	private int typeId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAliasesDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedLengthDate;

	public TbWkWork() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlternateName() {
		return this.alternateName;
	}

	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}

	public int getChapters() {
		return this.chapters;
	}

	public void setChapters(int chapters) {
		this.chapters = chapters;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public BigDecimal getLength() {
		return this.length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getNameCheckedDate() {
		return this.nameCheckedDate;
	}

	public void setNameCheckedDate(Date nameCheckedDate) {
		this.nameCheckedDate = nameCheckedDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigInteger getSize() {
		return this.size;
	}

	public void setSize(BigInteger size) {
		this.size = size;
	}

	public int getTagGrpId() {
		return this.tagGrpId;
	}

	public void setTagGrpId(int tagGrpId) {
		this.tagGrpId = tagGrpId;
	}

	public int getTickets() {
		return this.tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Date getUpdatedAliasesDate() {
		return this.updatedAliasesDate;
	}

	public void setUpdatedAliasesDate(Date updatedAliasesDate) {
		this.updatedAliasesDate = updatedAliasesDate;
	}

	public Date getUpdatedLengthDate() {
		return this.updatedLengthDate;
	}

	public void setUpdatedLengthDate(Date updatedLengthDate) {
		this.updatedLengthDate = updatedLengthDate;
	}

}