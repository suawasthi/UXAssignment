package com.uxpsystems.assignment.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property = "ID"
)
public abstract class Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name ="sequence", allocationSize=10)
	private long ID;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, updatable=false)
	private Date createdAt= new Date();
	
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt= new Date();
	
	
	

}
