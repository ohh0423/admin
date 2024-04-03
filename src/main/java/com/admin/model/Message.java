package com.admin.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.Readonly;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.NumberType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Erupt(name = "留言管理",
        primaryKeyCol = "message_id"
)
@Table(name = "message")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Message implements Serializable {

        @EruptField
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "message_id")
        private Integer message_id;

        @EruptField(
                views = @View(
                        title = "用户id", sortable = true
                ),
                edit = @Edit(
                        title = "用户id",
                        type = EditType.NUMBER, notNull = true,
                        numberType = @NumberType,
                        readonly = @Readonly

                )
        )
        private Integer user_id;


        @EruptField(
                views = @View(
                        title = "用户名", sortable = true
                ),
                edit = @Edit(
                        title = "用户名",
                        type = EditType.INPUT, search = @Search(vague = true),notNull = true,
                        inputType = @InputType,
                        readonly = @Readonly
                )
        )
        private String user_login_name;

        @EruptField(
                views = @View(
                        title = "创建时间", sortable = true
                ),
                edit = @Edit(
                        title = "创建时间",
                        type = EditType.DATE, notNull = true,search = @Search(vague = true),
                        dateType = @DateType(type = DateType.Type.DATE_TIME),
                        readonly = @Readonly
                )
        )
        @Column(name = "message_create_time")
        private Date message_create_time;


        @EruptField(
                views = @View(
                        title = "留言", sortable = true
                ),
                edit = @Edit(
                        title = "留言",
                        type = EditType.INPUT, search = @Search(vague = true),notNull = true,
                        inputType = @InputType,
                        readonly = @Readonly
                )
        )
        private String message;


        @EruptField(
                views = @View(
                        title = "回复时间", sortable = true
                ),
                edit = @Edit(
                        title = "回复时间",
                        type = EditType.DATE,dateType = @DateType(type = DateType.Type.DATE_TIME)

                )
        )
        @Column(name = "reply_time")
        private Date reply_time;

        @EruptField(
                views = @View(
                        title = "回复"
                ),
                edit = @Edit(
                        title = "回复",
                        type = EditType.INPUT,search = @Search(vague = true),
                        inputType = @InputType

                )
        )
        private String reply;

}