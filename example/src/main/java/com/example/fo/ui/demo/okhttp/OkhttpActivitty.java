package com.example.fo.ui.demo.okhttp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.base.BaseActivity;
import com.example.fo.bean.Result.BaseResult;
import com.example.fo.bean.Result.CategoryResult;
import com.example.fo.bean.request.CategoryRequest;
import com.example.fo.http.CallBack;
import com.example.fo.http.HttpHelper;
import com.example.fo.http.OkhttptUtils;
import com.fish.example.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static android.R.attr.type;
import static android.media.CamcorderProfile.get;

/**
 * User: 漆可
 * Date: 2016-08-27 17:06
 */
public class OkhttpActivitty extends BaseActivity
{
    private TextView tv_result;

    private static final String url = "http://zhekou.repai.com/jkjby/view/rp_b2c_category_list.php";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public int getContentView()
    {
        return R.layout.okhttp_activity;
    }

    @Override
    public void initView()
    {
        super.initView();
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    public void getDate(View v)
    {
        //        cid=2&
        //                tcid=2?
        //                sche=repai&
        //                        app_id=4251510622&
        //                        app_version=3.0.2&
        //            appkey=100071&
        //            pay=weixin&
        //            collect_pay=1&
        //            type=1
        final CategoryRequest request = new CategoryRequest();
        request.cid = "2";
        request.tcid = "2";
        request.sche = "repai";
        request.app_id = "4251510622";
        request.app_version = "3.0.2";
        request.appkey = "100071";
        request.pay = "weixin";
        request.collect_pay = "1";
        request.type = "1";

        HttpHelper mHttpHelper = new HttpHelper(url, CategoryResult.class, new HttpHelper.CallBack()
        {
            @Override
            public void onSuccess(BaseResult result)
            {
                CategoryResult categoryResult = (CategoryResult) result;
                Log.d("", categoryResult.toString());
            }

            @Override
            public void onFail(String error)
            {

            }
        });

        mHttpHelper.call(request);

        //        OkhttptUtils.getInstance().get(url, request, new CallBack()
        //        {
        //            @Override
        //            public void onSuccess(String result)
        //            {
        //                tv_result.setText(result);
        //            }
        //
        //            @Override
        //            public void onFail(String error)
        //            {
        //
        //            }
        //        });

        //        get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction()
    {
        Thing object = new Thing.Builder()
                .setName("OkhttpActivitty Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://host/path"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    //    private void get()
    //    {
    //        OkHttpClient httpClient = new OkHttpClient();
    //
    //        Request request = new Request.Builder()
    //                .url(url)
    //                .build();
    //
    //        httpClient.newCall(request).enqueue(new Callback()
    //        {
    //            @Override
    //            public void onFailure(Request request, IOException e)
    //            {
    //                Toast.makeText(OkhttpActivitty.this, "error", Toast.LENGTH_SHORT).show();
    //            }
    //
    //            @Override
    //            public void onResponse(Response response) throws IOException
    //            {
    //                final  String result = response.body().string();
    //
    //                runOnUiThread(new Runnable()
    //                {
    //                    @Override
    //                    public void run()
    //                    {
    //                        tv_result.setText(result);
    //                    }
    //                });
    //
    //                Log.d("", result);
    //            }
    //        });
    //    }
}
