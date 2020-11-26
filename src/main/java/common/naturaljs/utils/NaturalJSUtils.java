package common.naturaljs.utils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

public class NaturalJSUtils {
    public static Map<String, Object> convertQParamToMap(String q) throws Exception {
        if (q == null) {
            return new HashMap<String, Object>();
        }
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(URLDecoder.decode(q, "UTF-8"), new TypeReference<Map<String, Object>>() {
        });
    }
}
