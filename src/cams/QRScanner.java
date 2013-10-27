/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.io.IOException;
import java.io.InputStream;

public class QRScanner {
    public static String getText()  {
        String output = null;
        try {
            Process proc = Runtime.getRuntime().exec("java -jar qrscan/QRCodeScanner.jar");
            // Then retreive the process output
            proc.waitFor();
            InputStream in = proc.getInputStream();
            InputStream err = proc.getErrorStream();
            output = streamToString(in);
        } catch (IOException ex) {
            System.exit(1);
        } catch (InterruptedException ex) {
            System.exit(2);
        }
        return output;
    }
    
    static String streamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
