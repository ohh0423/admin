package com.admin.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Erupt(name = "公告管理",
        primaryKeyCol = "notice_id")
@Table(name = "Notice")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Notice implements Serializable {

        @EruptField
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "notice_id")
        private Integer notice_id;

        @EruptField(
                views = @View(
                        title = "发布时间", sortable = true
                ),
                edit = @Edit(
                        title = "发布时间",
                        type = EditType.DATE, notNull = true,search = @Search(vague = true),
                        dateType = @DateType(type = DateType.Type.DATE_TIME)
                )
        )
        @Column(name = "create_time")
        private Date create_time;

        @EruptField(
                views = @View(
                        title = "公告名称", sortable = true
                ),
                edit = @Edit(
                        title = "公告名称",
                        type = EditType.INPUT, notNull = true,search = @Search(vague = true),
                        inputType = @InputType
                )
        )
        @Column(name = "notice_name")
        private String notice_name;

        @EruptField(
                views = @View(
                        title = "内容"
                ),
                edit = @Edit(
                        title = "内容",
                        type = EditType.INPUT,search = @Search(vague = true),
                        inputType = @InputType
                )
        )
        @Column(name = "text")
        private String text;
}

    