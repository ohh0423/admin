package com.admin.model;

import com.admin.handler.UserDataProxy;
import lombok.*;
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

/**
 * 用户管理
 */
@Entity
@Data
@DynamicUpdate
@Erupt( name = "用户",
        primaryKeyCol = "user_id",
        dataProxy = UserDataProxy.class //事件代理，这里用做校验数据 ！
)
@Table(name = "user")
public class User implements Serializable {

    @EruptField
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer user_id;

    @EruptField(
            views = @View(
                    title = "用户名", sortable = true
            ),
            edit = @Edit(
                    title = "用户名",
                    type = EditType.INPUT, notNull = true,search = @Search(vague = true),
                    inputType = @InputType
            )
    )
    @Column(name= "user_login_name")
    private String user_login_name;

    @EruptField(
            views = @View(
                    title = "手机号"
            ),
            edit = @Edit(
                    title = "手机号",
                    type = EditType.INPUT,search = @Search(vague = true),
                    inputType = @InputType
            )
    )
    @Column(name = "user_phone")
    private String user_phone;

    @EruptField(
            views = @View(
                    title = "性别"
            ),
            edit = @Edit(
                    title = "性别",
                    type = EditType.INPUT,search = @Search(vague = true),
                    inputType = @InputType
            )
    )
    @Column(name = "user_sex")
    private String user_sex;

    @EruptField(
            views = @View(
                    title = "邮箱"
            ),
            edit = @Edit(
                    title = "邮箱",
                    type = EditType.INPUT, notNull = true,search = @Search(vague = true),
                    inputType = @InputType(type = "email")
            )
    )
    @Column(name = "user_email")
    private String user_email;

    @EruptField(
            views = @View(
                    title = "生日"
            ),
            edit = @Edit(
                    title = "生日",
                    type = EditType.DATE,search = @Search(vague = true),
                    dateType = @DateType(pickerMode = DateType.PickerMode.HISTORY)
            )
    )
    @Column(name = "user_birth")
    private Date user_birth;

    @EruptField(
            views = @View(
                    title = "身份证号"
            ),
            edit = @Edit(
                    title = "身份证号",
                    type = EditType.INPUT,search = @Search(vague = true),
                    inputType = @InputType
            )
    )
    @Column(name = "user_identity_num")
    private String user_identity_num;


}