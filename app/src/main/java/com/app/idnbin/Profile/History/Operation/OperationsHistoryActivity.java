package com.app.idnbin.Profile.History.Operation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class OperationsHistoryActivity extends BaseActivity implements View.OnClickListener {
    Toolbar Tb_App;
    LinearLayout LLOperations,LLStatus,LLDate;
    TextView TvOperationValue,TvStatusValue,TvDateValue;
    ArrayList<String> operationArray=new ArrayList<>();
    ArrayList<String> statusArray=new ArrayList<>();
    ArrayList<String> dateArray=new ArrayList<>();
    StringBuilder strOperation=new StringBuilder();
    StringBuilder strStatus=new StringBuilder();
    String strDate="All Time";
    private static final String TAG = "OperationsHistoryActivi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_history);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Operation");
        LLOperations=findViewById(R.id.LLOperations);
        TvOperationValue=findViewById(R.id.TvOperationValue);
        TvDateValue=findViewById(R.id.TvDateValue);
        TvStatusValue=findViewById(R.id.TvStatusValue);

        LLStatus=findViewById(R.id.LLStatus);
        LLDate=findViewById(R.id.LLDate);
        TextView TvApply = findViewById(R.id.TvApply);

        if (!getPref(this, "TransactionHistoryOperation").equalsIgnoreCase("null")) {
            operationArray = new Gson().fromJson(getPref(this, "TransactionHistoryOperation"), new TypeToken<ArrayList<String>>() {
            }.getType());

            if (operationArray == null || operationArray.size()==0 || operationArray.size() == 4) {
                strOperation.append("All");
            } else {
                for (int i = 0; i < operationArray.size(); i++) {
                    if (i == 0) {
                        strOperation.append(operationArray.get(i));
                    } else {
                        strOperation.append(", ");
                        strOperation.append(operationArray.get(i));
                    }
                }
            }
            TvOperationValue.setText(strOperation);
        }
        else {
            TvOperationValue.setText("All");
        }

        if (!getPref(this, "TransactionHistoryStatus").equalsIgnoreCase("null")) {
            statusArray = new Gson().fromJson(getPref(this, "TransactionHistoryStatus"), new TypeToken<ArrayList<String>>() {
            }.getType());


            if (statusArray == null || statusArray.size()==0 || statusArray.size() == 4) {

            strStatus.append("All");
        }
        else {
            for (int i=0;i<statusArray.size();i++)
            {
                if(i==0)
                {
                    strStatus.append(statusArray.get(i));
                }
                else {
                    strStatus.append(", ");
                    strStatus.append(statusArray.get(i));
                }
            }
        }
            TvStatusValue.setText(strStatus);
        }
        else {
            TvStatusValue.setText("All");
        }

        if (!getPref(this, "TransactionHistoryDate").equalsIgnoreCase("null")) {
            strDate = getPref(this, "TransactionHistoryDate");

            TvDateValue.setText(strDate);
        }
        else {
            TvDateValue.setText("All Time");
        }

        LLOperations.setOnClickListener(this);
        LLDate.setOnClickListener(this);
        LLStatus.setOnClickListener(this);
        TvApply.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LLOperations:
                startActivityForResult(new Intent(this, OperationActivity.class),1);
                break;
            case R.id.LLStatus:
                startActivityForResult(new Intent(this, StatusActivity.class),2);
                break;
            case R.id.LLDate:

                Intent intent=new Intent(this,DateActivity.class);
                intent.putExtra("SourceActivity", "OperationHistoryActivity");
                startActivityForResult(intent,3);

                break;
            case R.id.TvApply:
                startActivity(new Intent(this,OperationHistoryActivity.class));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            if(!data.getExtras().getString("HistoryOperation").equalsIgnoreCase(""))
            {
                TvOperationValue.setText(data.getExtras().getString("HistoryOperation"));
            }
            else {
                TvOperationValue.setText("All");
            }
        }
        else if(requestCode==2)
        {
            if(!data.getExtras().getString("HistoryStatus").equalsIgnoreCase(""))
            {
                TvStatusValue.setText(data.getExtras().getString("HistoryStatus"));
            }
            else {
                TvStatusValue.setText("All");
            }
        }
        else if(requestCode==3)
        {
            if(!data.getExtras().getString("TransactionHistoryDate").equalsIgnoreCase(""))
            {
                TvDateValue.setText(data.getExtras().getString("TransactionHistoryDate"));
            }
            else {
                TvDateValue.setText("All Time");
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}