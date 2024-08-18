package org.vyarmalovich.roomallocation.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RevenueSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            if (value % 1 == 0) {
                gen.writeNumber(value.longValue());
            } else {
                gen.writeNumber(value);
            }
        } else {
            gen.writeNull();
        }
    }
}