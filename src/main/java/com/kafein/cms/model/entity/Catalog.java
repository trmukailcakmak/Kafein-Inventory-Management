package com.kafein.cms.model.entity;

import com.kafein.cms.model.base.ExtendedModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "CATALOG")
@Data
public class Catalog extends BaseEntity {

    private String name;
    private String description;
}
