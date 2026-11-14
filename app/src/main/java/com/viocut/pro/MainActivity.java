package com.viocut.pro;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

public class MainActivity extends Activity {

    VideoView videoView;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 30, 24, 24);
        layout.setBackgroundColor(Color.rgb(15, 15, 25));

        TextView title = new TextView(this);
        title.setText("VioCut Pro");
        title.setTextSize(30);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Create Your Own Videos");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setGravity(Gravity.CENTER);
        layout.addView(subtitle);

        videoView = new VideoView(this);
        layout.addView(videoView,
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 220));

        status = new TextView(this);
        status.setText("Choose a video to get started");
        status.setTextColor(Color.WHITE);
        status.setGravity(Gravity.CENTER);
        layout.addView(status);

        Button importButton = new Button(this);
        importButton.setText("Import Video");
        layout.addView(importButton);

        importButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("video/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 100);
        });

        Button trimButton = new Button(this);
        trimButton.setText("Trim Video");
        layout.addView(trimButton);

        trimButton.setOnClickListener(v ->
            status.setText("Video trimming: Coming soon"));

        Button musicButton = new Button(this);
        musicButton.setText("Add Music");
        layout.addView(musicButton);

        musicButton.setOnClickListener(v ->
            status.setText("Music feature: Coming soon"));

        Button textButton = new Button(this);
        textButton.setText("Add Text");
        layout.addView(textButton);

        textButton.setOnClickListener(v ->
            status.setText("Text feature: Coming soon"));

        setContentView(layout);
    }

    @Override
    protected void onActivityResult(
        int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 &&
            resultCode == RESULT_OK && data != null) {

            Uri videoUri = data.getData();

            videoView.setVideoURI(videoUri);
            videoView.setMediaController(
                new MediaController(this));
            videoView.start();

            status.setText("Video loaded successfully!");
        }
    }
}
