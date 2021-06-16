package com.livelearn.ignorance.test.dragfooterview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.dragfooterview.adapter.TestDragFooterAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.dragfooterview.DragContainer;
import com.livelearn.ignorance.widget.dragfooterview.DragListener;

import butterknife.BindView;

/**
 * Created on 2016/11/12.
 */
public class TestDragFooterActivity extends BaseActivity implements DragListener {


    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.drag_recycler_view)
    DragContainer dragRecyclerView;

    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @BindView(R.id.scroll_view)
    HorizontalScrollView scrollView;

    @BindView(R.id.drag_scroll_view)
    DragContainer dragScrollView;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.drag_image_view)
    DragContainer dragImageView;

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.drag_text_view)
    DragContainer dragTextView;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.drag_button)
    DragContainer dragButton;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_drag_footer;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        setupImageView();
        setupButton();
        setupTextView();
        setupRecyclerView();
        setupHorizontalScrollView();
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onDragEvent() {
        IntentUtils.startActivity(mContext,TestShowMoreActivity.class);
    }

    private void setupImageView() {
        Glide.with(this).load(Url.urls[0]).into(imageView);

        dragImageView.setDragListener(this);
    }

    private void setupTextView() {
        dragTextView.setDragListener(this);
    }

    private void setupButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestDragFooterActivity.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(TestDragFooterActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        dragButton.setDragListener(this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        TestDragFooterAdapter adapter = new TestDragFooterAdapter(this);
        recyclerView.setAdapter(adapter);

        dragRecyclerView.setDragListener(this);
    }

    private void setupHorizontalScrollView() {
        for (int i = 10; i < 20; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp2px(120), ViewGroup.LayoutParams.MATCH_PARENT);
            params.leftMargin = 0;
            params.rightMargin = dp2px(5);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(params);
            linearLayout.addView(imageView);
            Glide.with(this).load(Url.urls[i]).into(imageView);
        }

        dragScrollView.setDragListener(this);
    }

    private int dp2px(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
