package com.joseph.framework.result;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CustomDecimalSerialize extends JsonSerializer<BigDecimal> {


    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (null != value) {
            if (value.compareTo(BigDecimal.ZERO) == 0) {
                gen.writeString("0.00");
            }
            else {
                gen.writeString(value.setScale(2, RoundingMode.HALF_UP).toString());
            }
        }
    }
}
