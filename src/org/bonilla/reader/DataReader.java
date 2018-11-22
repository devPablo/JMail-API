package org.bonilla.reader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;

/**
 * Class for mail configuration & authentication
 * data retrieval for usage in application.
 *
 * @author  Pablo Bonilla G.
 * @version 1.0
 * @since   2018-11-22
 */

public class DataReader {
    private static File JMAIL_DATA = new File("src/JMailData.json");
    private static JsonParser jsonParser = new JsonParser();

    public DataReader(File file) {
        JMAIL_DATA = file;
    }

    public DataReader() {}

    /**
     * Extracts the identifier's value from
     * the JSON file through a keyword.
     *
     * @param key Keyword that represents the identifier in the JSON file.
     * @return Identifier's value.
     */
    public String getJMailData(String key) {
        JsonElement jsonData;
        Object obj;
        JsonObject jsonObj;
        String data = "";
        try {
            obj = jsonParser.parse(new FileReader(JMAIL_DATA));
            jsonObj = (JsonObject) obj;

            if (key.equalsIgnoreCase("host") || key.equalsIgnoreCase("auth") ||
                    key.equalsIgnoreCase("tls") || key.equalsIgnoreCase("port")) {
                jsonObj = jsonObj.getAsJsonObject("smtpProperties");
                jsonData = jsonObj.get(key);
                data = jsonData.getAsString();
            } else {
                jsonData = jsonObj.get(key);
                data = jsonData.getAsString();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @return Identifier value of "email".
     */
    public String getEmail() {
        return getJMailData("email");
    }

    /**
     * @return Identifier value of "password".
     */
    public String getPassword() {
        return getJMailData("password");
    }

    /**
     * @return Identifier value of "host".
     */
    public String getHost() {
        return getJMailData("host");
    }

    /**
     * @return Identifier value of "auth".
     */
    public String getAuth() {
        return getJMailData("auth");
    }

    /**
     * @return Identifier value of "tls".
     */
    public String getTls() {
        return getJMailData("tls");
    }

    /**
     * @return Identifier value of "port".
     */
    public String getPort() {
        return getJMailData("port");
    }
}