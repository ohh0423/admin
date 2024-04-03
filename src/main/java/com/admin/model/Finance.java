package com.admin.model;/*
 * Copyright © 2020-2035 erupt.xyz All rights reserved.
 * Author: YuePeng (erupts@126.com)
 */

import xyz.erupt.annotation.*;
import xyz.erupt.annotation.sub_erupt.*;
import xyz.erupt.annotation.sub_field.*;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.upms.model.base.HyperModel;
import javax.persistence.*;
import java.util.Set;
import xyz.erupt.jpa.model.BaseModel;
import java.util.Date;

@Erupt(name = "财务管理")
@Table(name = "finance")
@Entity
public class Finance extends BaseModel {

        @EruptField(
                views = @View(
                        title = "总价"
                ),
                edit = @Edit(
                        title = "总价",
                        type = EditType.NUMBER,
                        numberType = @NumberType
                )
        )
        private Float total;

}
    