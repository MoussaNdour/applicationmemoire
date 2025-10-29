package com.example.applicationmemoire;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class CacheManager {
    private static final String FILE_NAME = "services_cache.json";
    private Context context;

    public CacheManager(Context context) {
        this.context = context;
    }

    public void saveJson(String json) {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readJson() {
        try (FileInputStream fis = context.openFileInput(FILE_NAME)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);
            return new String(data);
        } catch (Exception e) {
            return null;
        }
    }
}

