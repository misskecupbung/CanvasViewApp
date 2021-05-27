package com.ananda.canvasviewapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private MyCanvasView myCanvasView;
    private FloatingActionButton fabColor, fabWidth;
    private int mSelectedColor = R.color.purple_700;
    private int mSelectedWidth = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyCanvasView myCanvasView;
//        myCanvasView = new MyCanvasView(this);
//        myCanvasView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        myCanvasView = findViewById(R.id.canvas_view);

        fabColor = findViewById(R.id.fb_colorpicker);
        fabWidth = findViewById(R.id.fb_widthstroke);

        fabColor.setOnClickListener((view -> {
            ColorPicker colorPicker = new ColorPicker(this);
            colorPicker.setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {
                @Override
                public void setOnFastChooseColorListener(int position, int color) {
                    mSelectedColor = color;
                    myCanvasView.setPathColor(color);
                }

                @Override
                public void onCancel() {
                    colorPicker.dismissDialog();
                }
            }).disableDefaultButtons(true)
                    .setColumns(3)
                    .setRoundColorButton(true)
                    .show();
        }));

        fabWidth.setOnClickListener((view -> {
            MaterialNumberPicker mNumberPicker = new MaterialNumberPicker.Builder(this)
                    .minValue(1)
                    .maxValue(24)
                    .defaultValue(mSelectedWidth)
                    .backgroundColor(Color.WHITE)
                    .separatorColor(Color.TRANSPARENT)
                    .textSize(12)
                    .enableFocusability(false)
                    .wrapSelectorWheel(true)
                    .build();

            new AlertDialog.Builder(this)
                    .setTitle("Set Stroke Width")
                    .setView(mNumberPicker)
                    .setNegativeButton(getString(android.R.string.cancel), null)
                    .setPositiveButton(getString(android.R.string.ok), (dialogInterface, i) -> {
                        mSelectedWidth = mNumberPicker.getValue();
                        myCanvasView.setWidth(mNumberPicker.getValue());
                    })
                    .show();
        }));
    }
}