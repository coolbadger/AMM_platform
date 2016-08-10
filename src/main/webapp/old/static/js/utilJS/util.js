/**
 * Created by csw on 2016/7/23 10:37.
 * Explain:
 */
/**
 * 将json对象里面的字段拼接成url请求头的形式
 * @param params
 * @returns {string}
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