package com.livelearn.ignorance.test.assembleessay.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.rth.gragwithflowlayout.FlowDragLayoutConstant;
import com.develop.rth.gragwithflowlayout.FlowDragLayoutManager;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.assembleessay.adpter.TestAssembleEssayAdapter;
import com.livelearn.ignorance.test.assembleessay.adpter.DragItemTouchCallBack;
import com.livelearn.ignorance.test.assembleessay.contract.AssembleEssayContract;
import com.livelearn.ignorance.test.assembleessay.presenter.AssembleEssayPresenter;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.List;

import static com.livelearn.ignorance.R.id.tb_title;

public class TestAssembleEssayActivity extends BaseActivity implements AssembleEssayContract.IAssembleEssayView {

    private AssembleEssayContract.IAssembleEssayPresenter presenter;
    private RecyclerView recyclerView;
    private TestAssembleEssayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_assemble_essay;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        TitleBar tbTitle = (TitleBar) findViewById(tb_title);
        tbTitle.setTitleText(className)
                .setRightIcon(R.mipmap.ic_nav_moreset)
                .showRightIcon()
                .setOnClickRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String[] stringItems = getResources().getStringArray(R.array.TestAssembleEssay);
                        NormalListDialog normalListDialog = new NormalListDialog(mContext, stringItems);
                        normalListDialog.title("对齐方式")
                                .titleBgColor(ResourceUtils.getColor(R.color.base_background))
                                .titleTextColor(ResourceUtils.getColor(R.color.color_dark_black))
                                .show();
                        normalListDialog.setOnOperItemClickL(new OnOperItemClickL() {
                            @Override
                            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0://向中对齐
                                        recyclerView.setLayoutManager(new FlowDragLayoutManager(FlowDragLayoutConstant.CENTER));
                                        break;
                                    case 1://向右对齐
                                        recyclerView.setLayoutManager(new FlowDragLayoutManager(FlowDragLayoutConstant.RIGHT));
                                        break;
                                    case 2://向左对齐
                                        recyclerView.setLayoutManager(new FlowDragLayoutManager(FlowDragLayoutConstant.LEFT));
                                        break;
                                    case 3://两边对齐
                                        recyclerView.setLayoutManager(new FlowDragLayoutManager(FlowDragLayoutConstant.TWO_SIDE));
                                        break;
                                }
                            }
                        });
                    }
                });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new FlowDragLayoutManager());
        adapter = new TestAssembleEssayAdapter(this);
        ItemTouchHelper.Callback callback = new DragItemTouchCallBack(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        init();
    }

    @Override
    public void setListeners() {

    }

    private void init() {
        presenter = new AssembleEssayPresenter(this);
        presenter.loadEssay();
    }

    @Override
    protected void onDestroy() {
        presenter.release();
        super.onDestroy();
    }

    @Override
    public void onEssayLoaded(List<String> datas) {
        if (datas == null) {
            return;
        }
        if (datas.size() == 0) {
            return;
        }
        adapter.setDatas(datas);
        adapter.notifyDataSetChanged();
    }
}
