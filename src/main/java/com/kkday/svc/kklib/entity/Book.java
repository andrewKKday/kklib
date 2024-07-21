package com.kkday.svc.kklib.entity;

import com.kkday.sdk.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookOid;

    private String bookCategory;

    private String bookTitle;

    private String bookAuthor;

    private String bookCurrency;

    private Integer bookPrice;

    private Integer bookCost;
}
