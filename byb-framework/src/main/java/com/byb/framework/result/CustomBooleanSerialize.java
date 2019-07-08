package com.byb.framework.result;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Author: hhj
 * Date: 2019/6/3
 * Description:
 */
public class CustomBooleanSerialize extends JsonSerializer<Boolean> {


        @Override
        public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            if (null != value) {
                gen.writeString(value ? "1" : "0");
            }
        }
}
