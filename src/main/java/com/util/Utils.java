package com.util;

import com.converter.IConvertAPI;
import com.converter.JsonConverter;
import com.converter.model.ExtraParamsDto;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Pavel Neizhmak
 */
public class Utils {

    private static final String SECRET_KEY = "1Hbfh667adfDEJ78";
    private static final String ALGORITHM = "AES";

    public static URI buildRequest(final String schema, final String host, final String path, List<NameValuePair> params) throws URISyntaxException {
        return new URIBuilder()
                .setScheme(schema)
                .setHost(host)
                .setPath(path)
                .setParameters(params)
                .build();
    }

    /**
     * Builds JSON response
     *
     * @param stringResponse stringResponse
     * @return json response representation
     */
    public static Response buildResponse(final String stringResponse) {
        System.out.println(stringResponse);
        return Response.ok(stringResponse).build();
    }

    /**
     * Builds POJO response
     *
     * @param stringResponse stringResponse
     * @param convertAPI     {@see IConvertAPI}
     * @return pojo response representation
     */
    public static String buildResponse(final String stringResponse, final IConvertAPI convertAPI) {
        JsonConverter jsonConverter = new JsonConverter(convertAPI);
        final String parsedData = jsonConverter.parseUserInfo(stringResponse);

        System.out.println(parsedData);
        return parsedData;
    }

    public static ExtraParamsDto parseExtraParams(String params) {

        Gson gson = new Gson();
        final String updatedParams = "{" + params.substring(1, params.length()-1) + "}";

        return gson.fromJson(updatedParams, ExtraParamsDto.class);
    }

    public static Timestamp newTimestamp() {
        return new Timestamp(new java.util.Date().getTime());
    }

    public static Key generateSecretKey() throws Exception {
        return new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }

    public static String encryptPassword(String password) throws Exception {
        Key key = Utils.generateSecretKey();
        Cipher cipher = Cipher.getInstance(Utils.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedByteValue = cipher.doFinal(password.getBytes(Constants.UTF_8));

        return new BASE64Encoder().encode(encryptedByteValue);
    }

    public static String decryptPassword(String passwordHash) throws Exception {
        Key key = Utils.generateSecretKey();
        Cipher cipher = Cipher.getInstance(Utils.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(passwordHash);
        byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);

        return new String(decryptedByteValue, Constants.UTF_8);
    }
}
