package main.java.http;

import java.util.*;

public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String requestUrl;
    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private List<HttpCookie> cookies;
    private boolean isResource;

    public HttpRequestImpl(String request) {
        this.init(request);
    }

    private void init(String request) {
        this.setMethod(request.split("\\s+")[0]);
        this.setRequestUrl(request.split("\\s+")[1]);
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.cookies = new ArrayList<>();

        String[] requestParams = request.split(System.lineSeparator());
        Arrays.stream(requestParams)
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(headerKvp -> this.addHeader(headerKvp[0], headerKvp[1]));

        if (!requestParams[requestParams.length - 1].isEmpty()) {
            Arrays.stream(requestParams[requestParams.length - 1].split("&"))
                    .map(b -> b.split("="))
                    .forEach(bodyKvp -> this.addBodyParameter(bodyKvp[0], bodyKvp[1]));
        }

        if (this.headers.containsKey("Cookie")) {
            String cookie = this.headers.get("Cookie");
            Arrays.stream(cookie.split("; "))
                    .map(c -> c.split("="))
                    .forEach(cookieKvp -> {
                        HttpCookie httpCookie = new HttpCookieImpl(cookieKvp[0], cookieKvp[1]);
                        this.addCookie(httpCookie);
                    });
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return this.cookies;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public void addCookie(HttpCookie cookie) {
        this.cookies.add(cookie);
    }

    @Override
    public boolean isResource() {
        return this.getRequestUrl().contains(".");
    }
}
