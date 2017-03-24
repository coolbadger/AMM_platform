package com.amm.gps

import com.amm.gps.GpsResultDetail
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * Created by ThinkPad on 2017-03-10.
 */
class ConvertResult {
    GpsResultDetail gpsResultDetail;
    ArrayList<GpsResultDetail> gpsResultDetails;

    ArrayList<GpsResultDetail> getObj(String json) {

        def builder = new JsonBuilder();
        def root = new JsonSlurper().parseText(json);

        gpsResultDetails = new ArrayList<GpsResultDetail>();
        root.result.each { r ->
            gpsResultDetail = new GpsResultDetail()
            gpsResultDetail.latFixed = r.y
            gpsResultDetail.lngFixed = r.x
            gpsResultDetails.push(gpsResultDetail)
        }
        return gpsResultDetails;
    }


}
