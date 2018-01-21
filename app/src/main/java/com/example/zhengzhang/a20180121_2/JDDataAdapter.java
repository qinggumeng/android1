package com.example.zhengzhang.a20180121_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.panpf.sketch.SketchImageView;

/**
 * Created by zhengzhang on 2018/1/21.
 */

public class JDDataAdapter extends ArrayAdapter<JDData.DataBean>{
    int resource;

    public JDDataAdapter(@NonNull Context context, int resource, @NonNull List<JDData.DataBean> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        JDData.DataBean jd = getItem(position);
        View v = LayoutInflater.from(getContext()).inflate(resource,null);
        TextView txt = v.findViewById(R.id.textView);
        txt.setText(jd.getT());
        SketchImageView img = v.findViewById(R.id.image);
        System.out.println(jd);
        img.displayImage("https://img13.360buyimg.com/n7/"+jd.getImg());
        return v;
    }
}
