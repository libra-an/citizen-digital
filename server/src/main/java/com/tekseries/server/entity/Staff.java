package com.tekseries.server.entity;


import com.tekseries.server.entity.base.PrimaryEntity;
import com.tekseries.server.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff extends PrimaryEntity implements Serializable {

    @Column(name="code", length= EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name="name", length= EntityProperties.LENGTH_NAME)
    private String name;

    @Column(length= EntityProperties.LENGTH_NAME)
    private String email;

}
