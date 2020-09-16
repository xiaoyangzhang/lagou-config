package com.lagou.edu.filter;

import com.lagou.edu.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: stage-3-module-4
 * @description:
 * @author: zhangxiaoyang
 * @date: 2020-09-15 19:48
 **/
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    private static Map<String, AtomicInteger> IP_REGISTER_COUNT = new ConcurrentHashMap<>();
    private static Map<String, LinkedList<Long>> IP_REGISTER_TIME = new ConcurrentHashMap<>();
    private static Map<String, String> LOGIN_TOKEN = new ConcurrentHashMap<>();
    private static List<String> ignoreUrlList = new ArrayList<>();

    static {
        ignoreUrlList.add("/api/user/");
        ignoreUrlList.add("/api/code/");
    }

    @Value("${register.time.length:3}")
    private Integer registerTimeLength;

    @Value("${register.time.length.limit:5}")
    private Integer registerTimeLengthLimit;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String clientIp = request.getRemoteAddress()
                                 .getHostString();
        System.out.println("====>IP:" + clientIp);
        String uri = request.getURI()
                            .getPath();
        System.out.println("====>uri:" + uri);
        HttpCookie httpCookie = request.getCookies()
                                       .getFirst("token");
        //校验注册接口访问次数，防止爆刷IP
        if (uri.startsWith("/api/user/register")) {
            AtomicInteger visitCount = IP_REGISTER_COUNT.getOrDefault(clientIp, new AtomicInteger(0));
            LinkedList<Long> visitTimeStampList = IP_REGISTER_TIME.getOrDefault(clientIp, new LinkedList<>());
            long now = System.currentTimeMillis();
            visitTimeStampList.addLast(now);
            if (visitTimeStampList.size() > registerTimeLengthLimit) {
                System.out.println("》》》》》》》》超过注册接口访问次数,禁止访问");
                response.setStatusCode(HttpStatus.SEE_OTHER);
                System.out.println("=====>IP:" + clientIp + " 在⿊名单中，将被拒绝访问！");
                String data = "您频繁进⾏注册，请求已被拒绝";
                DataBuffer wrap = response.bufferFactory()
                                          .wrap(data.getBytes());
                return response.writeWith(Mono.just(wrap));
            }
            if (now - visitTimeStampList.getFirst() >= registerTimeLength * 60 * 1000) {
                visitTimeStampList.removeFirst();
            }
            IP_REGISTER_TIME.put(clientIp, visitTimeStampList);
            IP_REGISTER_COUNT.put(clientIp, new AtomicInteger(visitCount.incrementAndGet()));
        }
        boolean ignoreUrl = ignoreUrlList.stream()
                                         .anyMatch(url -> uri.startsWith(url));
        //校验token
        if (!ignoreUrl) {
            if (httpCookie == null || StringUtils.isBlank(httpCookie.getValue())) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                System.out.println("=====>IP:" + clientIp + " 没有权限！");
                String data = "没有权限访问该接口";
                DataBuffer wrap = null;
                try {
                    wrap = response.bufferFactory()
                                              .wrap(data.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                response.getHeaders().set("Content-Type","text/plain;charset=utf8");
                return response.writeWith(Mono.just(wrap));
            }
            String email = userFeignClient.getInfoByToken(httpCookie.getValue());
            if (StringUtils.isNotBlank(email)) {
                System.out.println("》》》》》》》》》重定向欢迎页面");
                response.setStatusCode(HttpStatus.MOVED_TEMPORARILY);
                try {
                    response.getHeaders().setLocation(new URI("http://www.test.com/static/welcome.html?email=" + email));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
//                response.getHeaders()
//                        .add("Location", "http://www.test.com/static/welcome.html?email=" + email);
                return response.setComplete();
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
