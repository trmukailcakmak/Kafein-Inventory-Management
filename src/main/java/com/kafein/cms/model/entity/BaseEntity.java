package com.kafein.cms.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @CreatedBy
    @Column(name = "CREATED_USER")
    private String createdUser;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @LastModifiedBy
    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "DELETED")
    private Boolean deleted;


    @PrePersist
    protected void prePersist() {
        if (this.createdDate == null) createdDate = new Date();
        if (this.updateDate == null) updateDate = new Date();
        if (this.createdUser == null) createdUser="SYSTEM";
    }

    @PreUpdate
    protected void preUpdate() {
        this.updateDate = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updateDate = new Date();
        deleted=Boolean.TRUE;
    }
}
