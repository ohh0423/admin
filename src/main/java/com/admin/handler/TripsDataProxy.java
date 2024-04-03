package com.admin.handler;

import com.admin.model.Flight;
import com.admin.model.Trips;
import com.admin.dao.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.core.exception.EruptWebApiRuntimeException;

/**
 * 对班次操作时的数据校验
 *
 */
@Component
public class TripsDataProxy implements DataProxy<Trips> {

    @Autowired
    private FlightRepository flightRepository;

    /**
     * 在修改数据前验证
     *
     * @param trips 班次
     * @return void
     */

    @Override
    public void beforeUpdate(Trips trips) {
        //获取对应航班所允许的最大座位数量
        String flight_name = trips.getTrips_flight_name();
        Flight flight = flightRepository.findByFlightName(flight_name).get(0);
        Integer flight_seat_num = flight.getFlight_seat_num();

        if (trips.getBusiness_class_seat_num()+trips.getEconomy_class_seat_num()>flight_seat_num) {
            throw new EruptWebApiRuntimeException("商务舱经济舱数量之和大于飞机能承载的数量！");
        }


        //机票数量不能小于0
        if (trips.getBusiness_class_seat_num()<0||trips.getEconomy_class_seat_num()<0) {
            throw new EruptWebApiRuntimeException("机票数量不能低于0");
        }
    }

    /**
     * 在插入数据前验证商务舱经济舱数量之和是否大于飞机能承载的数量
     * 增加一条新记录删除标志初始为，依据情况初始为0或1
     * @param trips 班次
     * @return void
     */

    @Override
    public void beforeAdd(Trips trips) {
        //获取对应航班所允许的最大座位数量
        String flight_name = trips.getTrips_flight_name();
        Flight flight = flightRepository.findByFlightName(flight_name).get(0);
        Integer flight_seat_num = flight.getFlight_seat_num();

        if (trips.getBusiness_class_seat_num()+trips.getEconomy_class_seat_num()>flight_seat_num) {
            throw new EruptWebApiRuntimeException("商务舱经济舱数量之和大于飞机能承载的数量！");
        }

        //增加一条新记录删除标志初始为0
        trips.setTrips_delete_flag(0);

        //机票数量不能小于0
        if (trips.getBusiness_class_seat_num()<0||trips.getEconomy_class_seat_num()<0) {
            throw new EruptWebApiRuntimeException("机票数量不能低于0");
        }
    }
}
