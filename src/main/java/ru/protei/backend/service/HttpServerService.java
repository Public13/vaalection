package ru.protei.backend.service;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class HttpServerService {

    public HttpServerService() {
        try {
            new Thread(() -> {
                try { HttpServerService.this.runServer(); } catch (IOException ignore) {} }).start();
        } catch (Exception e) {e.printStackTrace();}
    }

    private void runServer() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        System.out.println("http server starts on 8888");
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8888), 0);
        server.createContext("/anti", new AntiHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
    }

    private static class AntiHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String requestParamValue = null;
            System.out.println("request: "+ httpExchange.getRequestMethod());
            if("OPTIONS".equals(httpExchange.getRequestMethod())) {
                handleOptionsResponse(httpExchange, null);
            }else if ("GET".equals(httpExchange.getRequestMethod())) {
                requestParamValue = handleGetRequest(httpExchange);
            } else if ("POST".equals(httpExchange)) {
                requestParamValue = handlePostRequest(httpExchange);
            }
            handleResponse(httpExchange, requestParamValue);
        }

        private String handlePostRequest(HttpExchange httpExchange) {
            return "";
        }

        private String handleGetRequest(HttpExchange httpExchange) {
            return "";
//            return httpExchange.
//                    getRequestURI()
//                    .toString()
//                    .split("\\?")[1]
//                    .split("=")[1];
        }

        private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();

            Headers headers = httpExchange.getRequestHeaders();
            String origin = headers.getFirst("Origin");
            System.out.println("GET: origin="+origin);

            String login = headers.getFirst("X-User");
            System.out.println("GET: login="+login);

            String password = headers.getFirst("X-Password");
            System.out.println("GET: password="+password);

            htmlBuilder.append("{\n" +
                    "  \"customerId\": -1, \n" +
                    "  \"id\": 872057738, \n" +
                    "  \"login\": \""+login+"\", \n" +
                    "  \"name\": \""+login+"_HACKED"+"\", \n" +
                    "  \"password\": \""+password+"\", \n" +
                    "  \"role\": \"ROOT\"\n" +
                    "}");

            Headers respHeaders = httpExchange.getResponseHeaders();
            respHeaders.put("Access-Control-Allow-Origin", Collections.singletonList("*"));

            httpExchange.sendResponseHeaders(200, htmlBuilder.toString().length());

            outputStream.write(htmlBuilder.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        }

        private void handleOptionsResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();

            Headers headers = httpExchange.getRequestHeaders();
            String origin = headers.getFirst("Origin");
            System.out.println("OPTIONS: origin="+origin);

            Headers respHeaders = httpExchange.getResponseHeaders();

            respHeaders.put("Access-Control-Allow-Origin", Collections.singletonList("*"));
            respHeaders.put("Access-Control-Request-Method", Arrays.asList("PUT","PATCH","DELETE"));
            respHeaders.put("Access-Control-Allow-Headers",  Arrays.asList("Content-Type", "X-Password", "X-User"));
            respHeaders.put("Access-Control-Max-Age", Collections.singletonList("86400"));

            httpExchange.sendResponseHeaders(200, -1);

            outputStream.flush();
            outputStream.close();
        }
    }
}
