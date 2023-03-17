package com.kafein.cms.model.entity;

import com.kafein.cms.model.base.ExtendedModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product extends BaseEntity {

    private String name;

    private String description;

    private Long stockSize;

    private Long stockDownLimit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATALOG_ID")
    private Catalog catalog;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WAREHOUSE_ID")
    private Warehouse warehouse;
}
