/**
 * Created by csw on 2016/7/23 10:37.
 * Explain:
 */
function transformParams(params) {

    var paramStr = "";
    var keyArr = [];
    for (var k in params) {
        keyArr.push(k);
    }
    keyArr.sort();
    for (var i = 0; i < keyArr.length; i++) {
        var val = "";
        val = params[keyArr[i]];
        paramStr += "" + keyArr[i] + "=" + val + "&";
    }
    return paramStr;
}