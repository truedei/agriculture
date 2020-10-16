package cn.agriculture.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * province
 * @author 
 */
@Data
public class Province implements Serializable {
    private String provinceCode;

    private String provinceName;

    private static final long serialVersionUID = 1L;
}