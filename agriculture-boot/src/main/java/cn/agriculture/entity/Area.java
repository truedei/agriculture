package cn.agriculture.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * area
 * @author 
 */
@Data
public class Area implements Serializable {
    private String areaCode;

    private String areaName;

    private String cityCode;

    private String provinceCode;

    private static final long serialVersionUID = 1L;
}