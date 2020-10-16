package cn.agriculture.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * city
 * @author 
 */
@Data
public class City implements Serializable {
    private String cityCode;

    private String cityName;

    private String provinceCode;

    private static final long serialVersionUID = 1L;
}