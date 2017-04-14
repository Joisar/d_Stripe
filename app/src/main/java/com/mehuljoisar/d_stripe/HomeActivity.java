package com.mehuljoisar.d_stripe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private EditText etUserId;
    private Button btnConnect;
    private TextView tvStripeUserId;

    private String stripe_connection_url = "http://rentpayez.infoenum.com/v1/connect_stripe.php?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {

        etUserId = (EditText) findViewById(R.id.etUserId);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        tvStripeUserId = (TextView) findViewById(R.id.tvStripeUserId);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                display_url(HomeActivity.this,stripe_connection_url+etUserId.getText().toString());
                Intent mIntent = new Intent(HomeActivity.this,WebviewActivity.class);
                mIntent.putExtra("url",stripe_connection_url+etUserId.getText().toString());
                mIntent.putExtra("key","stripe_user_id");
                startActivityForResult(mIntent,1000);
            }
        });

    }

    public static void display_url(Context context, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

/*
        Intent receivedIntent = getIntent();
        Uri receivedData = receivedIntent.getData();
        if(receivedData!=null){
            String stripe_user_id = receivedData.getQueryParameter("stripe_user_id");
            if(stripe_user_id!=null){
                tvStripeUserId.setText(stripe_user_id);
            }
        }
*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==1000){
                String value = data.getStringExtra("stripe_user_id");
                tvStripeUserId.setText("Stripe User Id :\n"+value);
            }
        }
    }
}
