package bk.itc.html5.mylib.component.data;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import bk.itc.html5.mylib.component.MyApplication;

/**
 * Created by Hien on 5/3/2018.
 */

public class MyFile {
    public static String readFile(int rawId) {
        InputStream inputStream = MyApplication.getContext().getResources().openRawResource(rawId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte bufferByte[] = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toString();
    }
    public static String readFile(String assetPath) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte bufferByte[] = new byte[1024];
        int length;
        try {
            inputStream = MyApplication.getContext().getResources().getAssets().open(assetPath, Context.MODE_PRIVATE);
            while ((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toString();
    }
}
