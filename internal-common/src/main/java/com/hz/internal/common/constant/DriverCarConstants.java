package com.hz.internal.common.constant;


/**
 * @Auther: huangzhong
 * @Date: 2023/2/12 - 02 - 12 - 20:06
 * @Description: com.hz.internal.common.constant
 * @version: 1.0
 */
public class DriverCarConstants {
    /**
     * 司机车辆状态：绑定
     */
    public static final int DRIVER_CAR_BIND = 1;

    /**
     * 机车辆状态：解绑
     */
    public static final int DRIVER_CAR_UNBIND = 2;

    /**
     * 司机状态 1 有效
     */
    public static final int DRIVER_STATE_VALID = 1;

    /**
     * 司机状态 0 无效
     */
    public static final int DRIVER_STATE_INVALID = 0;

    /**
     * 司机的存在状态 1 存在  0 不存在
     */
    public static  int DRIVER_EXISTS = 1;
    public static  int DRIVER_NOT_EXISTS = 0;

    /**
     * 司机出车状态
     */
    public static int DRIVER_WORK_STATUS_START = 1;
    /**
     * 司机收车状态
     */
    public static int DRIVER_WORK_STATUS_STOP = 0;

    /**
     * 司机暂停状态
     */
    public static int DRIVER_WORK_STATUS_SUSPEND = 2;
}
