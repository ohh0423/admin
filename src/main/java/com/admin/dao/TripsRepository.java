package com.admin.dao;

import com.admin.model.Trips;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 班次数据操作接口
 *
 */
public interface TripsRepository  extends JpaRepository<Trips,Integer> {

    /**
     * 通过id获得班次信息
     * @param id 班次编号
     * @return java.util.Optional<com.admin.model.Trips>
     */

    @Override
    Optional<Trips> findById(Integer id);
}
