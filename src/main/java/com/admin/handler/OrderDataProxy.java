package com.admin.handler;

import com.admin.model.Trips;
import com.admin.dao.TripsRepository;
import com.admin.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.core.exception.EruptWebApiRuntimeException;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;


/**
 * 订单数据校验和处理
 *
 */
@Component
public class OrderDataProxy implements DataProxy<Orders> {

    @Autowired
    private TripsRepository tripsRepository;

    /**
     * 在进行数据更新前对一些更改的字段进行数据校验
     *
     * @param orders 订单
     * @return void
     */

    @Override
    public void beforeUpdate(Orders orders) {
        //校验身份证号
        String order_passenger_identity_num = orders.getOrder_passenger_identity_num();
        String regex_identity="^\\d{15}|\\d{18}$";
        if(!order_passenger_identity_num.matches(regex_identity))
            throw new EruptWebApiRuntimeException("请请输入正确的身份证号!");

        //校验联系人手机号
        String order_linkman_phone = orders.getOrder_linkman_phone();
        String regex_phone="0?(13|14|15|18|17)[0-9]{9}";
        if(!order_linkman_phone.matches(regex_phone))
            throw new EruptWebApiRuntimeException("请请输入正确的手机号!");

    }

    /**
     * 在数据更改后更改订单表的更新时间
     *
     */

    @Override
    public void afterUpdate(Orders orders) {
        //更改时设置修改时间
        Date date = new Date();
        orders.setOrder_update_time(date);
    }

    /**
     * 在查询时对每个订单的状态进行更新，如果订单的班次已发车，则状态也要相应改变
     *
     * @param list 查询到订单列表
     * @return void
     */

    @Override
    public void afterFetch(Collection<Map<String, Object>> list) {
        if (list != null && !list.isEmpty()) {
            list.forEach(order -> {
                // 1. 处理 order_status
                Object orderStatusObj = order.get("order_status");
                if (orderStatusObj instanceof String) {
                    String orderStatus = (String) orderStatusObj;
                    if ("创建".equals(orderStatus)) {
                        // 2. 处理 trips_trips_id
                        Object tripsIdObj = order.get("trips_trips_id");
                        if (tripsIdObj instanceof Integer) {
                            Integer tripsId = (Integer) tripsIdObj;

                            Optional<Trips> trips = tripsRepository.findById(tripsId);
                            trips.ifPresent(t -> {
                                if (t.getTrips_start_time().getTime() < new Date().getTime()) {
                                    order.put("order_status", "已起飞");
                                }
                            });
                        }
                    }
                }

            });
        }
    }
}
