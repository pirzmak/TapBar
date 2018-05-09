package com.example.user.tapbar.ownerViewModel.components;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.user.tapbar.R;
import com.example.user.tapbar.ownerViewModel.Table;

import java.util.ArrayList;

public class NewTableActivity extends AppCompatActivity {

    private Table newTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_table);

        NumberPicker np = (NumberPicker) findViewById(R.id.np);

        newTable = new Table(0,0, new ArrayList<>());

        np.setMinValue(0);

        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener((picker, oldVal, newVal) -> {
            //Display the newly selected number from picker
            newTable.setSize(newVal);
        });
    }

    public void click(View view) {
        switch (view.getId())
        {
            case R.id.button:
                break;
            case R.id.button2:
                break;
        }
    }
}
