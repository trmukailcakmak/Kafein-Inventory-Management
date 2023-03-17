package com.kafein.cms.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kafein.cms.model.entity.converter.BooleanConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class ExtendedModel {

    public abstract Long getId();

    public abstract void setId(Long id);

    @JsonIgnore
    @Convert(converter = BooleanConverter.class)
    private Boolean deleted;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createDate;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @JsonIgnore
    @CreatedBy
    private Long insertBy;

    @JsonIgnore
    @LastModifiedBy
    private Long lastModifiedBy;

}

