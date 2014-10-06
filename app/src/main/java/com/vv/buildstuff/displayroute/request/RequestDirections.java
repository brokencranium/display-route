package com.vv.buildstuff.displayroute.request;

import com.google.gson.Gson;
import com.vv.buildstuff.displayroute.response.DirectionsResponse;

import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by vvennava on 10/5/14.
 */

public class RequestDirections {

    private String urlString;
    private HttpURLConnection urlConnection;
    private URL url;

    public RequestDirections() {
    this.urlString = "https://maps.googleapis.com/maps/api/directions/json?" +
               "origin=Seattle&destination=Tacoma";
//                +
//               "&key=AIzaSyDXGbPGjbLwBgMrc2yivRP6NGQwOi_-Mrc";
     }

    public RequestDirections(String urlString) {
        this.urlString = urlString;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }


   public void getDirectionsResponse(){
       try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
           urlConnection.setRequestMethod("GET");
           Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
           InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//           readInputStream(reader);
//           readXMLInputStream(inputStream);
             readJSONInputStream(inputStream);

       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

    private void readJSONInputStream(InputStream inputStream) {
        try {
            System.out.println(fromStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readXMLInputStream(InputStream inputStream) {

        try {
            DocumentBuilder documentBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse(inputStream);
            DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
            LSSerializer lsSerializer = domImplementation.createLSSerializer();
            System.out.println(lsSerializer.writeToString(doc));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readInputStream(Reader reader) {
        Gson gson = new Gson();

        DirectionsResponse directionsResponse = gson.fromJson(reader, DirectionsResponse.class);
    }


    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }

}
