package com.example.e_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.R;

import java.util.Objects;

public class ViewItem extends AppCompatActivity {
    ImageView backBtn_IVVI;
    private ImageView itemImageIVVI;
    private TextView itemNameTVVI;
    private TextView itemDescriptionTVVI;
    private TextView priceTVVI;
    private TextView noOfItemTVVI;

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        init();
        backBtn_IVVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String IMAGE2=getIntent().getStringExtra("IMAGE2");
        int IMAGE1=getIntent().getIntExtra("IMAGE1",0);
        String ITEM_NAME=getIntent().getStringExtra("ITEM_NAME");
        String ITEM_DESCRIPTION=getIntent().getStringExtra("ITEM_DESCRIPTION");
        double ITEM_PRICE=getIntent().getDoubleExtra("ITEM_PRICE",400.00);
        int ITEM_QTY=getIntent().getIntExtra("ITEM_QTY",1);

        if(Objects.equals(IMAGE2, "")){
            itemImageIVVI.setImageResource(IMAGE1);
        }else{
            Glide.with(ViewItem.this).load(IMAGE2).into(itemImageIVVI);
        }

        itemNameTVVI.setText(ITEM_NAME);
        itemDescriptionTVVI.setText(ITEM_DESCRIPTION);
        priceTVVI.setText("Rs: "+String.valueOf(ITEM_PRICE*ITEM_QTY));
        noOfItemTVVI.setText(String.valueOf(ITEM_QTY));


    }
    private void init(){
        backBtn_IVVI=findViewById(R.id.backBtn_IVVI);
        itemImageIVVI=findViewById(R.id.itemImageIVVI);
        itemNameTVVI=findViewById(R.id.itemNameTVVI);
        itemDescriptionTVVI=findViewById(R.id.itemDescriptionTVVI);
        priceTVVI=findViewById(R.id.priceTVVI);
        noOfItemTVVI=findViewById(R.id.noOfItemTVVI);
    }
}