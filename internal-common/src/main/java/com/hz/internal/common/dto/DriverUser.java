package com.hz.internal.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/12 - 11 - 12 - 15:33
 * @Description: com.hz.internal.common.dto
 * @version: 1.0
 */
@Data
public class DriverUser {
    private Integer id;
    private String address;
    private String driverName;
    private String driverPhone;
    private Integer driverGender;
    private Date driverBirthday;
    private String driverNation;
    private String driverContactAddress;
    private Integer licenseId;
    private Date getDriverLicenseDate;
    private Date driverLicenseOn;
    private Date driverLicenseOff;
    private Integer taxiDriver;
    private String certificateNo;
    private String networkCarIssueOrganization;
    private Date networkCarIssueDate;
    private Date getNetworkCarProofDate;
    private Date networkCarProofOn;
    private Date networkCarProofOff;
    private Date registerDate;
    private Integer commercialType;
    private String contractCompany;
    private Date contractOn;
    private Date contractOff;
    private Integer state;
    private Date gmtCreate;
    private Date gmtModified;
}
