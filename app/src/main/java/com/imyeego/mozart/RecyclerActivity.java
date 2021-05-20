package com.imyeego.mozart;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @authur : liuzhao
 * @time : 5/21/21 12:16 AM
 * @Des :
 */
public class RecyclerActivity extends AppCompatActivity {

    BaseAdapter adapter;
    List<String> list = new ArrayList<>();
    int count = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        findViewById(R.id.btn_remove).setOnClickListener(v -> {
            remove(5, 3);

        });

        findViewById(R.id.btn_merge).setOnClickListener(v -> {
            merge(4, 5, 3);
        });

        findViewById(R.id.btn_add).setOnClickListener(v -> {
            add(count++, 5, 3);

        });
        adapter = new BaseAdapter(R.layout.item_fourth, list);
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        load();
    }

    private void load() {
        for (int i = 1; i <= 20; i++) {
            list.add("init" + i);
        }
        adapter.notifyDataSetChanged();
    }

    private void merge(int start, int remove, int add) {
        remove(start, remove);
        add(count++, start, add);
    }

    private void remove(int start, int size) {
        for (int i = 0; i < size; i++) {
            list.remove(start);
        }
        adapter.notifyItemRangeRemoved(start, size);
    }

    private void add(int count, int start, int size) {
        List<String> subList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            subList.add("add:" + count + " " + i);
        }
        list.addAll(start, subList);

        adapter.notifyItemRangeInserted(start, size);
    }

    class BaseAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public BaseAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_string, item);
        }
    }
}
