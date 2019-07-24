package me.sunny.demo.test

import com.alibaba.fastjson.JSON
import org.apache.http.ProtocolVersion
import org.apache.http.entity.BasicHttpEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.execchain.HttpResponseProxy
import org.apache.http.message.BasicHttpResponse
import org.apache.http.message.BasicStatusLine
import spock.lang.Specification

class HttpRpcMockTest extends Specification {
    HttpClient httpClient = new HttpClient()
    CloseableHttpClient syncHttpClient = Mock(CloseableHttpClient)

    def setup() {
        httpClient.syncHttpClient = syncHttpClient
    }
    /**
     * STEP1: 首先梳理依赖关系。 HttpClient 依赖 CloseableHttpClient 实例来查询数据，并对返回的数据做处理 ;
     * STEP2: 创建一个 HttpClient 实例 httpClient 以及一个 CloseableHttpClient mock
     *          实例： CloseableHttpClient syncHttpClient = Mock(CloseableHttpClient) ;
     * STEP3: 在 setup 启动方法中，将 syncHttpClient 设置给 httpClient ;
     * STEP4: 从代码中可以知道，httpClient 依赖 syncHttpClient 的 execute 方法返回的 CloseableHttpResponse 实例，
     *         因此，需要在 given: 块中构造一个 CloseableHttpResponse 实例 resp 。
     *         这里费了一点劲，需要深入apacheHttp源代码，了解 CloseableHttpResponse 的继承实现关系，
     *         来最小化地创建一个 CloseableHttpResponse 实例 ，避开不必要的细节。不过这并不是 SpockMock单测的重点。
     * STEP5：在 when 块中调用 syncHttpClient.execute(_) >> resp ；
     * STEP6: 在 then 块中根据 resp 编写断言表达式，这里 where 是可选的。
     */
    def "testHttpClientQuery"() {
        given:
        def statusLine = new BasicStatusLine(new ProtocolVersion("Http", 1, 1), 200, "")
        def resp = new HttpResponseProxy(new BasicHttpResponse(statusLine), null)
        resp.statusCode = 200

        def httpEntity = new BasicHttpEntity()
        def respContent = JSON.toJSONString([
                "code": 200, "message": "success", "total": 1200
        ])
        httpEntity.content = new ByteArrayInputStream(respContent.getBytes("utf-8"))
        resp.entity = httpEntity

        when:
        syncHttpClient.execute() >> resp

        then:
        def callResp = httpClient.query("query", "http://127.0.0.1:80/xxx/yyy/zzz/list")
        callResp.size() == 3
        callResp[field] == value

        where:
        field     | value
        "code"    | 200
        "message" | "success"
        "total"   | 1200
    }
}
