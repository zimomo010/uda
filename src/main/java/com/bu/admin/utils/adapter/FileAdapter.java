package com.bu.admin.utils.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;


public class FileAdapter extends TypeAdapter<File> {

    @Override
    public void write(JsonWriter jsonWriter, File file) throws IOException {
        if (file == null) {
            jsonWriter.nullValue();
        } else {
            // Serializes the file to its path string
            jsonWriter.value(file.getPath());
        }
    }

    @Override
    public File read(JsonReader jsonReader) throws IOException {
        String path = jsonReader.nextString();
        // Deserializes the path string back into a File object
        return new File(path);
    }
}
