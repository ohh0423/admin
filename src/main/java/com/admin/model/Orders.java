package com.admin.model;

import com.admin.handler.OrderDataProxy;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.Readonly;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.handler.DictChoiceFetchHandler;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单管理
 *
 */
@Erupt( name = "订单",
        primaryKeyCol = "order_id",
        dataProxy = OrderDataProxy.class
)
@Table(name = "orders")
@Entity
@Data
@DynamicUpdate
public class Orders implements Serializable {

    @EruptField
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "order_user_id")
    @EruptField(
            views = {
                    @View(title = "用户编号", column = "user_id"),
            },
            edit = @Edit(title = "用户编号选择", type = EditType.REFERENCE_TABLE, search = @Search,
                    referenceTableType = @ReferenceTableType(label = "user_id",id = "user_id")
            )
    )
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_trips_id")
    @EruptField(
            views = {
                    @View(title = "班次编号", column = "trips_id"),
            },
            edit = @Edit(title = "班次编号选择", type = EditType.REFERENCE_TABLE, search = @Search,
                    referenceTableType = @ReferenceTableType(label = "trips_id",id = "trips_id")
            )
    )
    private Trips trips;

    @EruptField(
            views = @View(
                    title = "创建时间", sortable = true
            ),
            edit = @Edit(
                    title = "创建时间",
                    type = EditType.DATE, notNull = true,search = @Search(vague = true),
                    dateType = @DateType(type = DateType.Type.DATE_TIME)
            )
    )
    @Column(name = "order_create_time")
    private Date order_create_time;

    @EruptField(
            views = @View(
                    title = "修改时间", sortable = true
            ),
            edit = @Edit(
                    title = "修改时间",
                    type = EditType.DATE,dateType = @DateType(type = DateType.Type.DATE_TIME),
                    readonly = @Readonly
            )
    )
    @Column(name = "order_update_time")
    private Date order_update_time;

    @EruptField(
            views = @View(title = "状态"),
            edit = @Edit(
                    search = @Search,
                    title = "状态", type = EditType.CHOICE, desc = "动态获取字典项的值",
                    choiceType = @ChoiceType(
                            fetchHandler = DictChoiceFetchHandler.class,
                            fetchHandlerParams = "order_status" //字典编码，通过字典编码获取字典项列表
                    ))
    )
    @Column(name = "order_status")
    private Integer order_status;

    @EruptField(
            views = @View(
                    title = "乘客姓名"
            ),
            edit = @Edit(
                    title = "乘客姓名",search = @Search,
                    type = EditType.INPUT, notNull = true,
                    inputType = @InputType
            )
    )
    @Column(name = "order_passenger_name")
    private String order_passenger_name;

    @EruptField(
            views = @View(
                    title = "乘客身份证号码"
            ),
            edit = @Edit(
                    title = "乘客身份证号码",search = @Search,
                    type = EditType.INPUT, notNull = true,
                    inputType = @InputType
            )
    )
    @Column(name = "order_passenger_identity_num")
    private String order_passenger_identity_num;

    @EruptField(
            views = @View(
                    title = "联系人姓名"
            ),
            edit = @Edit(
                    title = "联系人姓名",search = @Search,
                    type = EditType.INPUT,
                    inputType = @InputType
            )
    )
    @Column(name = "order_linkman_name")
    private String order_linkman_name;

    @EruptField(
            views = @View(
                    title = "联系人手机号"
            ),
            edit = @Edit(
                    title = "联系人手机号",search = @Search,
                    type = EditType.INPUT,
                    inputType = @InputType
            )
    )
    @Column(name = "order_linkman_phone")
    private String order_linkman_phone;

    @EruptField(
            views = @View(title = "坐席"),
            edit = @Edit(
                    search = @Search,
                    title = "坐席", type = EditType.CHOICE, desc = "动态获取字典项的值",
                    choiceType = @ChoiceType(
                            fetchHandler = DictChoiceFetchHandler.class,
                            fetchHandlerParams = "order_seat_level" //字典编码，通过字典编码获取字典项列表
                    ))
    )
    @Column(name = "order_seat_level")
    private Integer order_seat_level;

    @EruptField(
            views = @View(
                    title = "订单金额", sortable = true
            ),
            edit = @Edit(
                    title = "订单金额",
                    type = EditType.NUMBER, notNull = true,search = @Search(vague = true),
                    numberType = @NumberType
            )
    )
    @Column(name = "order_price")
    private Float order_price;

}
