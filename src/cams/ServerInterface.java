/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

/**
 *
 * @author dreamrunner
 */
public class ServerInterface {
    private static String serverAddress = "http://localhost:9000";
    
    private static String username, password;
    public static boolean login(String newUsername, String newPassword) {
        username = newUsername;
        password = newPassword;
        return true;
    }
    public static String getUsername() {
        return "admin";
    }
    public static String getPassword() {
        return "admin";
    }
    public static String getUrl(String url) {
        String ret = serverAddress + url;
        return ret;
    }
    
}
