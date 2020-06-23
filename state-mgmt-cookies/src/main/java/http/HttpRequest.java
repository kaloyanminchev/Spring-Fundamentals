package main.java.http;

import java.util.List;
import java.util.Map;

public interface HttpRequest {

    String getMethod();

    String getRequestUrl();

    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    List<HttpCookie> getCookies();

    boolean isResource();

    void setMethod(String method);

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addBodyParameter(String parameter, String value);

    void addCookie(HttpCookie cookie);
}
