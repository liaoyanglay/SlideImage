package com.example.dizzylay.popupmenu;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FlipperAdapter adapter;
    private AdapterViewFlipperPlus flipper;
    private AlertDialog dialog;
    private ImageAdapter imageAdapter;
    private View menuView;
    private List<Drawable> drawableList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button previous = findViewById(R.id.previous);
        Button next = findViewById(R.id.next);
        menuView = LayoutInflater.from(this).inflate(R.layout.dialog_menu, null);
        RecyclerView recyclerView = menuView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);
        flipper = findViewById(R.id.adapter_view_flipper);
        Resources resources = getResources();
        drawableList.add(resources.getDrawable(R.mipmap.pic1));
        drawableList.add(resources.getDrawable(R.mipmap.pic2));
        drawableList.add(resources.getDrawable(R.mipmap.pic3));
        drawableList.add(resources.getDrawable(R.mipmap.pic4));
        drawableList.add(resources.getDrawable(R.mipmap.pic5));
        drawableList.add(resources.getDrawable(R.mipmap.pic6));
        drawableList.add(resources.getDrawable(R.mipmap.pic7));
        drawableList.add(resources.getDrawable(R.mipmap.pic8));
        drawableList.add(resources.getDrawable(R.mipmap.pic9));
        drawableList.add(resources.getDrawable(R.mipmap.pic10));
        drawableList.add(resources.getDrawable(R.mipmap.pic1));
        drawableList.add(resources.getDrawable(R.mipmap.pic2));
        drawableList.add(resources.getDrawable(R.mipmap.pic3));
        adapter = new FlipperAdapter(drawableList);
        flipper.setAdapter(adapter);
        imageAdapter.setDrawableList(drawableList);
        imageAdapter.setListener((v, index) -> flipper.setDisplayedChild(index));
        previous.setOnClickListener(v -> flipper.showPrevious());
        next.setOnClickListener(v -> flipper.showNext());
        setPreviousAnim();
        setNextAnim();
        buildDialog();
    }

    private void setNextAnim() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat(
                "translationX", 580f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat(
                "translationY", -1000f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat(
                "scaleX", 0f);
        PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat(
                "scaleY", 0f);
        PropertyValuesHolder holder5 = PropertyValuesHolder.ofFloat(
                "rotation", 360f);
        PropertyValuesHolder holder6 = PropertyValuesHolder.ofFloat(
                "alpha", 0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                flipper, holder1, holder2, holder3, holder4, holder5, holder6);
//        animator.setDuration(1000);
//        ObjectAnimator in = ObjectAnimator.ofFloat(null, "alpha", 0f, 1f);
        PropertyValuesHolder holderIn1 = PropertyValuesHolder.ofFloat(
                "translationX", -580f, flipper.getLeft());
        PropertyValuesHolder holderIn2 = PropertyValuesHolder.ofFloat(
                "translationY", 1000f, flipper.getTop());
        PropertyValuesHolder holderIn3 = PropertyValuesHolder.ofFloat(
                "scaleX", 0f, 1f);
        PropertyValuesHolder holderIn4 = PropertyValuesHolder.ofFloat(
                "scaleY", 0f, 1f);
        PropertyValuesHolder holderIn5 = PropertyValuesHolder.ofFloat(
                "rotation", -180f, 0f);
        PropertyValuesHolder holderIn6 = PropertyValuesHolder.ofFloat(
                "alpha", 0f, 1f);
        ObjectAnimator in = ObjectAnimator.ofPropertyValuesHolder(
                flipper, holderIn1, holderIn2, holderIn3, holderIn4, holderIn5, holderIn6);
        animator.setDuration(700);
        in.setDuration(700);
        flipper.setNextOutAnimator(animator);
        flipper.setNextInAnimator(in);
    }

    private void setPreviousAnim() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat(
                "translationX", 580f, flipper.getLeft());
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat(
                "translationY", -1000f, flipper.getTop());
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat(
                "scaleX", 0f, 1f);
        PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat(
                "scaleY", 0f, 1f);
        PropertyValuesHolder holder5 = PropertyValuesHolder.ofFloat(
                "rotation", -180f, 0f);
        PropertyValuesHolder holder6 = PropertyValuesHolder.ofFloat(
                "alpha", 0f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                flipper, holder1, holder2, holder3, holder4, holder5, holder6);
        animator.setDuration(700);
//        ObjectAnimator out = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        PropertyValuesHolder holderOut1 = PropertyValuesHolder.ofFloat(
                "translationX", -580f);
        PropertyValuesHolder holderOut2 = PropertyValuesHolder.ofFloat(
                "translationY", 1000f);
        PropertyValuesHolder holderOut3 = PropertyValuesHolder.ofFloat(
                "scaleX", 0f);
        PropertyValuesHolder holderOut4 = PropertyValuesHolder.ofFloat(
                "scaleY", 0f);
        PropertyValuesHolder holderOut5 = PropertyValuesHolder.ofFloat(
                "rotation", 360f);
        PropertyValuesHolder holderOut6 = PropertyValuesHolder.ofFloat(
                "alpha", 0f);
        ObjectAnimator out = ObjectAnimator.ofPropertyValuesHolder(
                flipper, holderOut1, holderOut2, holderOut3, holderOut4, holderOut5, holderOut6);
        animator.setDuration(700);
        out.setDuration(700);
        flipper.setPreviousInAnimator(animator);
        flipper.setPreviousOutAnimator(out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.image_menu:
                dialog.show();
                break;
        }
        return true;
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Page")
                .setView(menuView);
        dialog = builder.create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.CustomDialog);
        }
    }
}
