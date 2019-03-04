package com.example.tuan.demo_asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button buttonLoad;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoad = findViewById(R.id.buttonLoad);
        imageView = findViewById(R.id.imageView);

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LoadImageInternet loadImageInternet = new LoadImageInternet();
                new loadImageInternet().execute("https://i.pinimg.com/originals/e5/75/d7/e575d7a5e8afa1a75640b9eec5288d7a--xe-h%C6%A1i-vi%E1%BB%87t-nam.jpg");
                new loadImageInternet().execute("https://hips.hearstapps.com/amv-prod-cad-assets.s3.amazonaws.com/images/16q3/669461/2017-mercedes-benz-c300-coupe-test-review-car-and-driver-photo-669851-s-original.jpg");
            }
        });

    }

    private class loadImageInternet extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmapImage = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapImage = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmapImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
