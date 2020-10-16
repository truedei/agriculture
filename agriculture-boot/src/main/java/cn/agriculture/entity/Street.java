package cn.agriculture.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * street
 * @author 
 */
@Data
public class Street implements Serializable {
    private String streetCode;

    private String streetName;

    private String areaCode;

    private String provinceCode;

    private String cityCode;

    private static final long serialVersionUID = 1L;
}