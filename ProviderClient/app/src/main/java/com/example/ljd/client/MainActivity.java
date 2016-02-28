package com.example.ljd.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.show_linear)
    LinearLayout showLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.insert_button,R.id.delete_button,R.id.update_button,R.id.query_button,R.id.clear_button})
    public void onClickButton(View view){
        switch (view.getId()){
            case R.id.insert_button:
                HandleProvider.getInstance(this).create();
                break;
            case R.id.delete_button:
                HandleProvider.getInstance(this).delete();
                break;
            case R.id.update_button:
                HandleProvider.getInstance(this).update();
                break;
            case R.id.query_button:

                List<String> list = HandleProvider.getInstance(this).query();
                for (int i = 0;i<list.size();i++){
                    TextView textView = new TextView(this);
                    textView.setText(list.get(i));
                    showLinear.addView(textView);
                }
                TextView lineText = new TextView(this);
                lineText.setText("-----------------------");
                showLinear.addView(lineText);
                break;
            case R.id.clear_button:
                showLinear.removeAllViews();
                break;
            default:
                break;
        }
    }
}
