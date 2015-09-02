package com.univocity.api.common;

import com.univocity.api.common.helpers.*;

import java.io.*;
import java.util.*;

public class HttpRequest {

	private String url;
	private int timeout = 0;
	private boolean followRedirects = false;
	private boolean validateSSL = true;
	private HttpMethodType httpMethodType = HttpMethodType.GET;
	private final LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();
	private final LinkedHashMap<String, String> cookies = new LinkedHashMap<String, String>();
	private final List<Object[]> data = new ArrayList<Object[]>();

	public HttpRequest(String url) {
		this.url = url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public void setUserAgent(String userAgent) {
		setHeader("User-Agent", userAgent);
	}

	public void setReferrer(String referrer) {
		setHeader("Referer", referrer);
	}

	public String getUserAgent() {
		return headers.get("User-Agent");
	}

	public String getReferrer() {
		return headers.get("Referer");
	}

	public void setHeader(String header, String value) {
		set(headers, header, value);
	}

	public void setCookie(String cookie, String value) {
		set(cookies, cookie, value);
	}

	private void set(Map<String, String> map, String key, String value) {
		if (value == null) {
			map.remove(key);
		} else {
			map.put(key, value);
		}
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;

	}

	public void setHttpMethodType(HttpMethodType httpMethodType) {
		this.httpMethodType = httpMethodType;
	}

	public String getUrl() {
		return url;
	}

	public int getTimeout() {
		return timeout;
	}

	public boolean getFollowRedirects() {
		return followRedirects;
	}

	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}

	public boolean getValidateSSL() {
		return validateSSL;
	}

	public void setValidateSSL(boolean validateSSL) {
		this.validateSSL = validateSSL;
	}

	public HttpMethodType getHttpMethodType() {
		return httpMethodType;
	}

	public void setDataParameter(String paramName, String value) {
		this.data.add(new Object[]{paramName, value});
	}

	public void setDataStreamParameter(String paramName, String filename, ResourceProvider<InputStream> dataProvider) {
		this.data.add(new Object[]{paramName, filename, dataProvider});
	}

	public void setFileParameter(String paramName, String filename, FileProvider file) {
		this.data.add(new Object[]{paramName, filename, file});
	}

	public Map<String, String> getHeaders() {
		return Collections.unmodifiableMap(headers);
	}

	public Map<String, String> getCookies() {
		return Collections.unmodifiableMap(cookies);
	}

	public List<Object[]> getData() {
		return Collections.unmodifiableList(data);
	}
}
