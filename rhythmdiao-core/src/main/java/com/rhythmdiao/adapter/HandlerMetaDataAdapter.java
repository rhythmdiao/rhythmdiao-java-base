package com.rhythmdiao.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.rhythmdiao.entity.HandlerMetaData;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class HandlerMetaDataAdapter extends TypeAdapter<HandlerMetaData> {

    @Override
    public void write(JsonWriter jsonWriter, HandlerMetaData handlerMetaData) throws IOException {
        jsonWriter.beginObject()
                .name("method").value(handlerMetaData.getMethod())
                .name("target").value(handlerMetaData.getTarget())
                .name("description").value(handlerMetaData.getDescription());
        jsonWriter.name("parameters").beginObject();
        for (Map.Entry<Field, Class<? extends Annotation>> annotatedField : handlerMetaData.getAnnotatedFields().entrySet()) {
            jsonWriter
                    .name(annotatedField.getKey().getName())
                    .value(annotatedField.getKey().getType().getSimpleName() + ";@" + annotatedField.getValue().getSimpleName());
        }
        jsonWriter.endObject();
        jsonWriter.endObject();
    }

    @Override
    public HandlerMetaData read(JsonReader jsonReader) {
        throw new UnsupportedOperationException("can not read handler meta data");
    }
}
