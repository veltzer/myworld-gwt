package org.meta.gwtworld.client.db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TbDevice database table.
 * 
 */
@Entity
@Table(name="TbDevice")
public class TbDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	private Boolean isAudio;

	private Boolean isBrowser;

	private Boolean isText;

	private Boolean isVideo;

	private String name;

	private String slug;

	public TbDevice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsAudio() {
		return this.isAudio;
	}

	public void setIsAudio(Boolean isAudio) {
		this.isAudio = isAudio;
	}

	public Boolean getIsBrowser() {
		return this.isBrowser;
	}

	public void setIsBrowser(Boolean isBrowser) {
		this.isBrowser = isBrowser;
	}

	public Boolean getIsText() {
		return this.isText;
	}

	public void setIsText(Boolean isText) {
		this.isText = isText;
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

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}