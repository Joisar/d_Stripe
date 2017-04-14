package com.mehuljoisar.d_stripe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.AuthenticationException;

public class MainActivity extends AppCompatActivity {

    private Button btnPay;
    private TextView tvToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btnPay = (Button) findViewById(R.id.btnPay);
        tvToken = (TextView) findViewById(R.id.tvToken);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTokenFromDummyCard();
            }
        });


    }


    private void getTokenFromDummyCard(){
        Card card = new Card("4242424242424242", 12, 2017, "123");

        Stripe stripe = null;
        try {
            stripe = new Stripe("pk_test_6pRNASCoBOKtIshFeQd4XMUh");
            stripe.createToken(
                    card,
                    new TokenCallback() {
                        public void onSuccess(Token token) {
                            // Send token to your server
                            tvToken.setText(token.getId());
                        }
                        public void onError(Exception error) {
                            // Show localized error message
                            tvToken.setText("error: "+error.getLocalizedMessage());
                            Toast.makeText(MainActivity.this.getApplicationContext(),
                                    error.getLocalizedMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
            );
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

}
