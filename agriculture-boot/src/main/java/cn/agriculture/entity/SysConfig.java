package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-17 14:42
 * @Description: 字典实体类
 */
@Getter
@Setter
public class SysConfig {

    private Integer configId;//字典ID
    private String configName;//字典名称
    private Integer subId;//子项ID
    private String subName;//子项名称

    private String configIdStr;//拼接的多个id
    private String subIdStr;

    @Override
    public String toString() {
        return "SysConfig{" +
                "configId=" + configId +
                ", configName='" + configName + '\'' +
                ", subId=" + subId +
                ", subName='" + subName + '\'' +
                '}';
    }
}

