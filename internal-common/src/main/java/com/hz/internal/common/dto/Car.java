package com.hz.internal.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 黄仲
 * @since 2023-02-12
 */
@Data
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 车辆所在城市
     */
    private String address;

    /**
     * 车辆号牌
     */
    private String vehicleNo;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 核定载客位
     */
    private Integer seats;

    /**
     * 车辆厂牌
     */
    private String brand;

    /**
     * 车辆型号
     */
    private String model;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 车辆所有人
     */
    private String ownerName;

    /**
     * 车辆颜色
     */
    private String vehicleColor;

    /**
     * 发动机号
     */
    private String engineId;

    private String vin;

    /**
     * 车辆注册日期
     */
    private LocalDate certifyDateA;

    /**
     * 车辆燃料类型
     */
    private String fueType;

    /**
     * 发动机排量
     */
    private String engineDisplace;

    /**
     * 车辆运输证发证机构
     */
    private String transAgency;

    /**
     * 车辆经营区域
     */
    private String transArea;

    /**
     * 车辆运输证有效期起
     */
    private LocalDate transDateStart;

    /**
     * 车辆运输证有效期止
     */
    private LocalDate transDateEnd;

    /**
     * 车辆初次登记日期
     */
    private LocalDate certifyDateB;

    /**
     * 车辆检修状态
     */
    private String fixState;

    /**
     * 下次年检时间
     */
    private LocalDate nextFixDate;

    /**
     * 车辆年度审验状态
     */
    private String checkState;

    /**
     * 发票打印设备序列号
     */
    private String feePrintId;

    /**
     * 卫星定位装置品牌
     */
    private String gpsBrand;

    /**
     * 卫星定位装置型号
     */
    private String gpsModel;

    /**
     * 卫星定位安装日期
     */
    private LocalDate gpsInstallDate;

    /**
     * 报备日期
     */
    private LocalDate registerDate;

    /**
     * 服务类型
     */
    private Integer commercialType;

    /**
     * 运价类型编码
     */
    private String fareType;

    /**
     * 状态
     */
    private Integer state;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}
