package com.example.zhengzhang.a20180121_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.JsonResponseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int page = 0;
    List<JDData.DataBean> list = new ArrayList<JDData.DataBean>();
    RefreshLayout refreshLayout;
    JDDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);

//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//               // refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//            }
//        });

        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
               // refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                loadData();
            }
        });


       loadData();
    }

    private void loadData(){
        page++;
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                String jdurl = "https://api.m.jd.com/api?callback=jQuery68717&appid=pc&functionId=mixer&t=1516538306646&body=%7B%22pin%22%3A%22%22%2C%22p%22%3A504000%2C%22uuid%22%3A%2215153357932791412551591%22%2C%22lid%22%3A%221%22%2C%22lim%22%3A10%2C%22skus%22%3A%22%22%2C%22ck%22%3A%22ipLoction%22%2C%22ec%22%3A%22utf-8%22%2C%22c1%22%3A%226728%22%2C%22c2%22%3A%226740%22%2C%22c3%22%3A%226964%22%2C%22hi%22%3A%22brand%3A%2Cprice%3A%2Cpage%3A"+page+"%22%7D&jsonp=jQuery68717&_=1516538306646";
                String jddata = NetUtil.get(jdurl);
                System.out.println(jdurl);

                int first = jddata.indexOf("{");

                if(page==5){

                    return null;

                }else{
                    int end = jddata.lastIndexOf("}");

                    jddata = jddata.substring(first,end+1);
                    Log.d("test",jddata);

                    // Log.d("test", JSON.parseObject(jddata).toJSONString());
                    return JSON.parseObject(jddata);
                }


            }

            @Override
            protected void onPostExecute(Object o) {
                if(o==null){
                    refreshLayout.finishLoadmoreWithNoMoreData();
                    return;
                }
                JSONObject data = (JSONObject) o;
                JSONArray arr = data.getJSONArray("data");



                for(int i=0;i<arr.size();i++){
                    JSONObject item = arr.getJSONObject(i);
                    // Log.d("test",item.getString("t"));
                    JDData.DataBean jddb = new JDData.DataBean();
                    jddb.setT(item.getString("t"));
                    jddb.setImg(item.getString("img"));
                    list.add(jddb);
                }

                if(page>1){
                    adapter.notifyDataSetChanged();
                }else {
                    adapter  = new JDDataAdapter(MainActivity.this, R.layout.item_jddata, list);
                    ListView lv = MainActivity.this.findViewById(R.id.list1);
                    lv.setAdapter(adapter);
                }


                refreshLayout.finishLoadmore(0);

            }
        }.execute();
    }
}
