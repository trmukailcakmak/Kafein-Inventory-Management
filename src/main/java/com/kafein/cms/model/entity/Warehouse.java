package com.kafein.cms.model.entity;

import com.kafein.cms.model.base.ExtendedModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "WAREHOUSE")
@Data
public class Warehouse extends BaseEntity {

    private String name;

    private String area;

    private String city;

    private String district;

    private String address;

    private String description;
}
