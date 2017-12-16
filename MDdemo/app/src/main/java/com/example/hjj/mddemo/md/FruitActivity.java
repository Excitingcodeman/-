package com.example.hjj.mddemo.md;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hjj.mddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    @BindView(R.id.fruit_image_view)
    ImageView fruitImageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.fruit_content_text)
    TextView fruitContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String fruit_name = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruit_name);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent = generateFruitContent(fruit_name);
        fruitContentText.setText(fruitContent);
    }

    private String generateFruitContent(String fruit_name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append(fruit_name);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
