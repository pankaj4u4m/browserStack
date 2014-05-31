package com.browserstack.api.client;

import java.util.Map;

public interface BroswerStackClient {

    String get(String method);

    String post(String method, Map<String, String> input);

}
