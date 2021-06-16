package edu.escuelaing.arsw;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class HttpServerTest
{
    /**
     * Rigorous Test :-)
     */

    /* Metodo para retornar el String  del url */
    private String getResult(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    /*@BeforeClass*/
    public static void setup() throws IOException {
        HttpServer.main(null);
    }

    @Test
    public void ComprobarPeticion () throws IOException {
        String result = getResult("http://localhost:35000/mypage.html");
        assertNotNull(result);

    }

    @Test
    public void RecursoNoExistente() throws IOException{
        String result = getResult("http://localhost:35000/mhola.html");
        assertTrue(result.isEmpty());
    }

    @Test
    public void ProbarHtml() throws IOException {
        String result = getResult("http://localhost:35000/mypage.html");
        assertEquals("<!DOCTYPE html><html lang=\"en\"><head>    <meta charset=\"UTF-8\">    <title>Title</title></head><body>    <img src=\"https://png.pngtree.com/thumb_back/fw800/background/20190223/ourmid/pngtree-plant-flowers-background-design-backgroundpink-image_73306.jpg\" alt=\"flores\"/></body></html>",result);
    }
}
