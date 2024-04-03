package com.admin.model;

import com.admin.handler.LineDataProxy;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler ;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 线路管理
 */
@Erupt(name = "线路",
        primaryKeyCol = "line_id",
        dataProxy = LineDataProxy.class
)
@Table(name = "line")
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class Line implements Serializable {

    @EruptField(
            views = @View(
                    title = "线路编号"
            )
    )
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "line_id")
    private Integer line_id;

    @EruptField(
            views = @View(
                    title = "起始站点"
            ),
            edit = @Edit(
                    search = @Search(vague = true),
                    title = "起始站点",
                    notNull = true,
                    type = EditType.CHOICE,
                    desc = "获取已有的站点名",
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            fetchHandlerParams = "select station_name from station"
                    )
            )
    )
    @Column(name = "line_start_station_name")
    private String line_start_station_name;

    @EruptField(
            views = @View(
                    title = "到达站点"
            ),
            edit = @Edit(
                    search = @Search(vague = true),
                    title = "到达站点",
                    notNull = true,
                    type = EditType.CHOICE,
                    desc = "获取已有的站点名",
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            fetchHandlerParams = "select station_name from station"
                    )
            )
    )
    @Column(name = "line_end_station_name")
    private String line_end_station_name;

}
