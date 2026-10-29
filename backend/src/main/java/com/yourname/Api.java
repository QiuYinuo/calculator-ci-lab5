package com.yourname;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
public class Api {
 public static double calculate(String query) {
  var values = new HashMap<String,String>();
  if(query!=null) for(String pair:query.split("&")) {String[] p=pair.split("=",2); if(p.length==2)values.put(p[0],URLDecoder.decode(p[1],StandardCharsets.UTF_8));}
  double result=new Calculator().eval(values.get("a"),values.get("op"),values.get("b"));
  if(!Double.isFinite(result))throw new IllegalArgumentException("Result must be finite");
  return result;
 }
 public static void main(String[] args)throws Exception {
  var server=HttpServer.create(new InetSocketAddress("0.0.0.0",8000),0);
  server.createContext("/api",exchange->{
   int status=200;String response;
   if(!exchange.getRequestMethod().equals("GET")){status=405;response="{\"error\":\"GET required\"}";}
   else if(exchange.getRequestURI().getPath().equals("/api/health"))response="{\"status\":\"ok\"}";
   else if(!exchange.getRequestURI().getPath().equals("/api/calculate")){status=404;response="{\"error\":\"Not found\"}";}
   else try{response="{\"result\":"+calculate(exchange.getRequestURI().getRawQuery())+"}";}
   catch(IllegalArgumentException e){status=400;response="{\"error\":\"Invalid operands, operator, or division by zero\"}";}
   byte[] bytes=response.getBytes(StandardCharsets.UTF_8);exchange.getResponseHeaders().set("Content-Type","application/json; charset=utf-8");exchange.sendResponseHeaders(status,bytes.length);exchange.getResponseBody().write(bytes);exchange.close();System.out.println("HTTP "+status);
  });server.start();System.out.println("Calculator API listening on 8000");
 }
}
