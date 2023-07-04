package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Application {
    private static ObjectMapper mapper = new ObjectMapper();

    public void run() throws IOException {

        CloseableHttpClient httpClient = getHttpClient();

        HttpGet request = new HttpGet(getResource(AccountInfo.ACCOUNT_ID));
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        NasaInfo info = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {
                });

        String urlPicture = info.getUrl();

        downloadFileToDisk(new URL(urlPicture), getImageNameInUrl(urlPicture));
    }

    private void downloadFileToDisk(URL url, String fileName) throws IOException {

        String path = String.format("C:/Programming/Netology/Test/%s", fileName);

        try (InputStream in = url.openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(path);
        ) {
            byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    private String getResource(String ApiKey) {
        return String.format("https://api.nasa.gov/planetary/apod?api_key=%s", ApiKey);
    }

    private String getImageNameInUrl(String url) {

        if (url.isEmpty()) {
            return "NONAME.jpg";
        }

        String[] array = url.split("/");

        return  array[array.length - 1];
    }

    private CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("Test NASA site resource")
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                                .setConnectTimeout(5000)
                                .setSocketTimeout(1000)
                                .setRedirectsEnabled(false)
                                .build()
                )
                .build();
        return httpClient;
    }
}
