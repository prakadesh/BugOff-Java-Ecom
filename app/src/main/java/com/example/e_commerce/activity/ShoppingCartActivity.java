package com.example.e_commerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_commerce.R;
import com.example.e_commerce.data.MyAdapter;

public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewShoppingCart;
    private LinearLayout totalAmountLL;
    private TextView noOfItemsInCartTV;
    private ImageView backBtnSCTIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        init();
        backBtnSCTIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        MyAdapter myAdapter = new MyAdapter(this,MyAdapter.shoppingList,MyAdapter.SHOPPING_CART_ACT);
        if(MyAdapter.shoppingList.size()==0){
            totalAmountLL.setVisibility(View.INVISIBLE);
        }
        String noOfItemsInCart=MyAdapter.shoppingList.size() + " items are in the cart";
        noOfItemsInCartTV.setText(noOfItemsInCart);
        recyclerViewShoppingCart.setAdapter(myAdapter);
        recyclerViewShoppingCart.setHasFixedSize(true);
        recyclerViewShoppingCart.setLayoutManager(new LinearLayoutManager(ShoppingCartActivity.this));
    }
    private void init(){
        recyclerViewShoppingCart=findViewById(R.id.recyclerViewShoppingCart);
        totalAmountLL=findViewById(R.id.totalAmountLL);
        noOfItemsInCartTV=findViewById(R.id.noOfItemsInCartTV);
        backBtnSCTIV=findViewById(R.id.backBtnSCTIV);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}