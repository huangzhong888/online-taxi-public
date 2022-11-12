package com.hz.servicemap.service;

import com.hz.internal.common.constant.AmapConfigConstant;
import com.hz.internal.common.constant.CommonStatusEnum;
import com.hz.internal.common.dto.DicDistrict;
import com.hz.internal.common.dto.ResponseResult;
import com.hz.servicemap.mapper.DicDistrictMapper;
import com.hz.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: huangzhong
 * @Date: 2022/11/6 - 11 - 06 - 15:41
 * @Description: com.hz.servicemap.service
 * @version: 1.0
 */
@Service
public class DicDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords) {
        //请求地图
        String dicDistrict = mapDicDistrictClient.DicDistrict(keywords);

        //解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrict);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstant.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstant.DISTRICTS);
        //循环district节点
        for (int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJSONObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJSONObject.getString(AmapConfigConstant.ADCODE);
            String countryAddressName = countryJSONObject.getString(AmapConfigConstant.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJSONObject.getString(AmapConfigConstant.LEVEL);

            insertDicDistrict(countryAddressCode, countryAddressName, countryLevel, countryParentAddressCode);

            JSONArray provinceJsonArray = countryJSONObject.getJSONArray(AmapConfigConstant.DISTRICTS);
            for (int p = 0; p < provinceJsonArray.size(); p++) {
                JSONObject provinceJSONObject = provinceJsonArray.getJSONObject(p);
                String provinceAddressCode = provinceJSONObject.getString(AmapConfigConstant.ADCODE);
                String provinceAddressName = provinceJSONObject.getString(AmapConfigConstant.NAME);
                String provinceParentAddressCode = countryParentAddressCode;
                String provinceLevel = provinceJSONObject.getString(AmapConfigConstant.LEVEL);

                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceLevel, provinceParentAddressCode);

                JSONArray cityJsonArray = provinceJSONObject.getJSONArray(AmapConfigConstant.DISTRICTS);
                for (int city = 0; city < cityJsonArray.size(); city++) {
                    JSONObject cityJSONObject = cityJsonArray.getJSONObject(city);
                    String cityAddressCode = cityJSONObject.getString(AmapConfigConstant.ADCODE);
                    String cityAddressName = cityJSONObject.getString(AmapConfigConstant.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityJSONObject.getString(AmapConfigConstant.LEVEL);

                    insertDicDistrict(cityAddressCode, cityAddressName, cityLevel, cityParentAddressCode);
                    JSONArray districtJsonArray = cityJSONObject.getJSONArray(AmapConfigConstant.DISTRICTS);
                    for (int d = 0; d < districtJsonArray.size(); d++) {
                        JSONObject districtJSONObject = districtJsonArray.getJSONObject(d);
                        String districtAddressCode = districtJSONObject.getString(AmapConfigConstant.ADCODE);
                        String districtAddressName = districtJSONObject.getString(AmapConfigConstant.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJSONObject.getString(AmapConfigConstant.LEVEL);
                        if(districtLevel.equals(AmapConfigConstant.STREET)){
                            continue;
                        }
                        insertDicDistrict(districtAddressCode,districtAddressName,districtLevel,districtParentAddressCode);
                    }
                }
            }
        }
        return ResponseResult.success("");
    }


    /**
     * 地区级别转换为数字
     * @param level
     * @return
     */
    public int generatorLevel(String level){

        int levelInt = 0;

        if(level.trim().equals("country")){
            levelInt = 0;
        }else if(level.trim().equals("province")){
            levelInt = 1;
        }else if (level.trim().equals("city")){
            levelInt = 2;
        }else if(level.trim().equals("district")){
            levelInt = 3;
        }
        return levelInt;
    }

    /**
     * 插入行政区域数据
     * @param addressCode
     * @param addressName
     * @param level
     * @param parentAddressCode
     */
    public void insertDicDistrict(String addressCode,String addressName,String level,String parentAddressCode){
        //数据库对象
        DicDistrict district = new DicDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        district.setLevel(generatorLevel(level));
        district.setParentAddressCode(parentAddressCode);

        //插入数据库
         dicDistrictMapper.insert(district);
    }
}

