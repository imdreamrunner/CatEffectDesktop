/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dreamrunner
 */
public class ServerInterface {
    private static String serverAddress = "http://localhost:9000";
    
    
    private static String username, password, errorMessage;
    private static int managerType;
    
    public static boolean login(int type, String newUsername, String newPassword) {
        managerType = type;
        username = newUsername;
        password = newPassword;
        try {
            boolean success = postRequest();
            if (success) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private static boolean postRequest() throws UnsupportedEncodingException, IOException, JSONException {
        HttpClient httpclient = new DefaultHttpClient();
        
        String url;
        if (managerType == 0) {
            url = getUrl("/stall/auth");
        } else {
            url = getUrl("/system/auth");
        }
        
        HttpPost httppost = new HttpPost(url);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("auth_username", username));
        params.add(new BasicNameValuePair("auth_password", password));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                String result = getStringFromInputStream(instream);
                JSONObject jsonObj = new JSONObject(result);
                String errorCode = jsonObj.get("error").toString();
                if (errorCode.equals("0")) {
                    return true;
                } else {
                    errorMessage = jsonObj.get("message").toString();
                }
            } finally {
                instream.close();
            }
        }
        return false;
    }
    
    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    
    public static void setServerAddress (String newServerAddress) {
        serverAddress = newServerAddress;
    }
    
    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }
    public static boolean isSystem() {
        return managerType == 1;
    }
    public static String getErrorMessage() {
        return errorMessage;
    }
    public static String getUrl(String url) {
        String ret = serverAddress + url;
        return ret;
    }
    
}
