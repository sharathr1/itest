
/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.common;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableBaseEntity {

	@Column(name = "create_date", nullable = false, insertable = true, updatable = false)
	@CreatedDate
	private ZonedDateTime creationTime;

	@Column(name = "created_by", nullable = false, insertable = true, updatable = false)
	private long createdBy = 0;

	@Column(name = "update_date", nullable = true, insertable = false, updatable = true)
	@LastModifiedDate
	private ZonedDateTime modificationTime;

	@Column(name = "updated_by", nullable = true, insertable = false, updatable = true)
	private Long updatedBy = 0L;

	public ZonedDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(ZonedDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public ZonedDateTime getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(ZonedDateTime modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

}
