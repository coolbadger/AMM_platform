package com.amm.gps;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2017-03-13.
 */
public class GpsResultDetail {

    private BigDecimal lngFixed;

    private BigDecimal latFixed;

    public BigDecimal getLngFixed() {
        return lngFixed;
    }

    public BigDecimal getLatFixed() {
        return latFixed;
    }

    public void setLngFixed(BigDecimal lngFixed) {
        this.lngFixed = lngFixed;
    }

    public void setLatFixed(BigDecimal latFixed) {
        this.latFixed = latFixed;
    }
}
