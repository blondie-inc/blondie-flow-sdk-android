package com.blondie.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blondie.sdk.Blondie;
import com.blondie.sdk.BlondieEvent;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJmbG93SWQiOiIyYnN0TFkyOGUycUhYbW5GUGh1ZSIsImlhdCI6MTU0Mjc5MzY2OH0.EIV0SehG9aDBLVuKjbkZCCSKZIFiY-RVNsuWmLHC7afqmRQMJ4oHfuBX-idV6p7jNaSIC_juCqyNF5toSd0yFKOinsRjUZwxuIEDLWfOisnd5STSoeCywOn9n8tO346hrTLItTWalv7XPAp2oqf_fOjtBOcXLwv6j_hmBBmrFutrTckXVLwdu65otpxd9eWDnRPxEO1o2rvmCkeYkrAtDOzULjTC1MMYU2jlxPRndrzZDk5tjWv1Ts6jgX0jzL6lsEqQd_z_D2EnJp5DmhWuShcmJGqheTmllYfu2-7E7l4wz35V6gty2Gn2fxi1AlaPh1aVINJCx_wVkNsNgsp5EpL9HPHHcCpBdT78z1DD1WbWKk-JzsZZ4_OFgR4Wno6og41Hljw9yuh-_nC5N36CHv_HhPfVHjlNtcACCFOhAxzWn9c-tNhTvnqHPmaMTLNZAN3HwiMpNUDg4BNBvC9yZ_pi-IgHTle_yl1C06whVz3Vj7QqfkHXt6TEMYf5KIqh4woImif9CoeHvLTKGpaqB6sm5SzA4XLwxLObF5G00U3AU3ihu6yqGxiNzNkMAUC1ZuzUCcanEgk5oaAE8McR3YOfK9eDWmIgtLZ4NHZnGSHhaUCvM7ZMMjO14F9G9KS2aQ-syL-x25bCY3Iz4JM_zqR-pLZKyxUBT4CGPkB2pGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Blondie.setApiKey(API_KEY);

        Button mButton = findViewById(R.id.request_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(System.currentTimeMillis() % 2 == 0){
                    BlondieEvent blondieEvent = new BlondieEvent("Submitted phone number");
                    blondieEvent.set("entityType", "Devices");
                    blondieEvent.set("entityId", "000004");
                    Blondie.triggerEvent(getApplicationContext(), blondieEvent);
                }else{
                    BlondieEvent blondieEvent = new BlondieEvent("Submitted device info");
                    blondieEvent.set("deviceType", "Sumsung");
                    blondieEvent.set("deviceId", "234445666");
                    Blondie.triggerEvent(getApplicationContext(), blondieEvent);
                }


            }
        });
    }
}