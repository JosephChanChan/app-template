package com.byb.framework.result;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @Author: hhj
 * Date: 2019/6/3
 * Description:
 */
public class CustomDoubleSerialize extends JsonSerializer<Double> {

    private static DecimalFormat df = new DecimalFormat("##.00");

    @Override
    public void serialize(Double value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if(value != null) {
            if(value.intValue()==0){
                jsonGenerator.writeString("0.00");
            }else {
                jsonGenerator.writeString(df.format(value));
            }
        }
    }
}
