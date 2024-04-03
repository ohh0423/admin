package com.admin.model;

import lombok.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.NumberType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 航班管理
 *
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Erupt( name = "航班",
        primaryKeyCol = "flight_id"
)
@Table(name = "flight")
public class Flight implements Serializable {

    @EruptField
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_id")
    private Integer flight_id;

    @EruptField(
            views = @View(
                    title = "航班名"
            ),
            edit = @Edit(
                    title = "航班名",
                    type = EditType.INPUT, notNull = true,search = @Search(vague = true),
                    inputType = @InputType
            )
    )
    @Column(name = "flight_name")
    private String flightName;

    @EruptField(
            views = @View(
                    title = "最大速度km/h", sortable = true
            ),
            edit = @Edit(
                    title = "最大速度",
                    type = EditType.NUMBER,search = @Search(vague = true),
                    numberType = @NumberType
            )
    )
    @Column(name = "flight_speed")
    private Float flight_speed;

    @EruptField(
            views = @View(
                    title = "座位数量",sortable = true
            ),
            edit = @Edit(
                    title = "座位数量",
                    type = EditType.NUMBER, notNull = true,search = @Search(vague = true),
                    numberType = @NumberType
            )
    )
    @Column(name = "flight_seat_num")
    private Integer flight_seat_num;

}
