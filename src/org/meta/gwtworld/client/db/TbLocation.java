package org.meta.gwtworld.client.db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TbLocation database table.
 * 
 */
@Entity
@Table(name="TbLocation")
public class TbLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Boolean isVideo;

	private String name;

	private String remark;

	private String slug;

	public TbLocation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getIsVideo() {
		return this.isVideo;
	}

	public void setIsVideo(Boolean isVideo) {
		this.isVideo = isVideo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}