package com.kafein.cms.model.entity;

import com.kafein.cms.constant.ActionType;
import com.kafein.cms.model.base.ExtendedModel;
import com.kafein.cms.model.entity.converter.ActionTypeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "HISTORY")
@Data
public class History extends BaseEntity {

    @Convert(converter = ActionTypeConverter.class)
    private ActionType actionType;
    private String relatedTable;
    private Long efectedId;
    private String description;
}
