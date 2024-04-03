package com.admin.dao;

import com.admin.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


/**
 * 提供航班的一些数据操作接口
 *
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {

    /**
     * 通过航班名称获得对应的航班
     *
     * @param flight_name 航班名称
     * @return com.admin.model.Train
     */
    List<Flight> findByFlightName(String flight_name);
}