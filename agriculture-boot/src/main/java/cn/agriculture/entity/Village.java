package cn.agriculture.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * village
 * @author 
 */
@Data
public class Village implements Serializable {
    private String villageCode;

    private String villageName;

    private String streetCode;

    private String provinceCode;

    private String cityCode;

    private String areaCode;

    private static final long serialVersionUID = 1L;
}