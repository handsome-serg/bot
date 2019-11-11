package com.handsome;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CoinMarketCup {

    private static final Logger log = LoggerFactory.getLogger(CoinMarketCup.class);

    private static String apiKey; //secret key - must be stored in database
    private static final String apiKeyProduction  = "529235c4-4f94-4064-9b86-4bbf6d1e748f"; //secret key - must be stored in database
    private static final String apiKeySandbox     = "ab8e19af-5825-4398-8ab3-78fed823a015"; //secret key - must be stored in database

    private static String domainRuntime;
    private static final String domainProduction  = "https://pro-api.coinmarketcap.com";
    private static final String domainSandbox     = "https://sandbox-api.coinmarketcap.com"; //for test environment

    private static boolean sandboxMode = false;

    //static constructor
    static {
        setSandboxMode(false);
    }

    public static boolean isSandboxMode() {
        return sandboxMode;
    }

    public static void setSandboxMode(boolean sandboxMode) {
        CoinMarketCup.sandboxMode = sandboxMode;


        if (sandboxMode){
            CoinMarketCup.domainRuntime = CoinMarketCup.domainSandbox; //test cases will change it
            CoinMarketCup.apiKey        = CoinMarketCup.apiKeySandbox;

        } else {
            CoinMarketCup.domainRuntime = CoinMarketCup.domainProduction; //default in static constructor
            CoinMarketCup.apiKey        = CoinMarketCup.apiKeyProduction;
        }
    }

    public static String getDomainRuntime() {
        return domainRuntime;
    }

    public static String hello() {
        String result = null;
        String uri = domainRuntime + "/v1/cryptocurrency/listings/latest";
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("start","1"));
        parameters.add(new BasicNameValuePair("limit","5000"));
        parameters.add(new BasicNameValuePair("convert","USD"));

        try {
            result = makeAPICall(uri, parameters);
        } catch (IOException e) {
            log.error("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            log.error("Error: Invalid URL " + e.toString());
        }

        return result;
    }

    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

}

