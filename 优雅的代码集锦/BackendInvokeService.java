package com.longxin.ssls.robot.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BackendInvokeService {

    @Data
    public static class BackendInvokeContext {
        private HttpClient client;
        private HttpHost host;
        private HttpClientContext ctx;

        public HttpResponse execute(HttpRequest req) throws ClientProtocolException, IOException {
            return client.execute(host, req, ctx);
        }
    };

    @Autowired
    private DiscoveryClient discoveryClient;

    public Object invokeWithContext(String svcId, Function<BackendInvokeContext, Object> consumer) throws IOException {
        List<ServiceInstance> instances = discoveryClient.getInstances(svcId);
        String uri = choose(instances);
        if (null == uri)  {
            throw new RuntimeException("no usable backend for " + svcId);
        }
        log.info("qddebug will use " + uri);

        HttpHost host = HttpHost.create(uri);
        HttpClientContext httpCtx = HttpClientContext.create();

        BackendInvokeContext ctx = new BackendInvokeContext();
        ctx.setCtx(httpCtx);
        ctx.setHost(host);

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ctx.setClient(httpclient);
            return consumer.apply(ctx);
        } catch (IOException e) {
            throw e;
        }
    }

    private static String choose(List<ServiceInstance> instances) {
        if (instances == null)       return null;

        return instances.stream().map(ins -> (EurekaServiceInstance)ins)
                .filter(ins -> {
                    InstanceInfo info = ins.getInstanceInfo();
                    if (null == info)       return false;

                    InstanceStatus st = info.getStatus();
                    if (null == st || !st.equals(InstanceStatus.UP))        return false;

                    return true;
                })
                .map(ins -> ins.getUri().toString())
                .findFirst().orElse(null);
    }

}
