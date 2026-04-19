package com.bu.admin.utils.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateAdapter extends TypeAdapter<Date> {
    private final SimpleDateFormat simpleDateFormat;

    public DateAdapter() {
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(simpleDateFormat.format(value));
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == null) {
            return null;
        }
        try {
            return simpleDateFormat.parse(in.nextString());
        } catch (Exception e) {
            throw new IOException("Unable to parse date", e);
        }
    }
}

