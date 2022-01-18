package com.z.xc_inflate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View.inflate()
//        LayoutInflater.from();
//        LayoutInflater LayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        inflate方法中参数的作用
//        View inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot);

        init();
    }

    private void init() {

        mItems = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            mItems.add("item-" + i);
        }

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public Object getItem(int position) {
                return mItems.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            /*
                问题-1:
                    inflate(R.layout.item_of_list, parent, false);
                        列表中每项的高度由LinearLayout来决定

                    inflate(R.layout.item_of_list, null);
                        列表中每项的高度由TextView来决定

                解决-1:
                    root == null
                        不解析根布局产生的View的LayoutParams
                    root != null
                        解析根布局产生的View的LayoutParams


                问题-2:
                    inflate(R.layout.item_of_list, parent, true);
                        LinearLayout将自己当作子View添加到自己的布局中 - 报错
                            addView(View, LayoutParams) is not supported in AdapterView

                解决-2:
                    attachToRoot == false
                        根布局产生的View不是root的子View
                    attachToRoot == true
                        根布局产生的View是root的子View
             */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_of_list, parent, false);
//                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_of_list, parent, true);
//                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_of_list, null);
                TextView textView = view.findViewById(R.id.item_text);
                textView.setText(mItems.get(position));
                return view;
            }
        });
    }
}