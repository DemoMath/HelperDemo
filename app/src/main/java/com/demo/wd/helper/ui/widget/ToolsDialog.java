package com.demo.wd.helper.ui.widget;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.bean.PointDouble;
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.ModifyOffsetUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 这是首页显示工具的Dialog
 * Created by Administrator on 2016/4/27.
 */
public class ToolsDialog extends Dialog implements View.OnClickListener {

    private String mLongitude;
    private String mLatitude;
    private LocationManager mLManager;

    public ToolsDialog(Context context) {
        super(context);
    }

    public ToolsDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ToolsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_plus);
        TextView qiandao = (TextView) findViewById(R.id.qiandao);
        TextView image = (TextView) findViewById(R.id.image);
        TextView camoer = (TextView) findViewById(R.id.camoer);

        qiandao.setOnClickListener(this);
        image.setOnClickListener(this);
        camoer.setOnClickListener(this);

        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        attributes.width = LayoutParams.MATCH_PARENT;
        window.setWindowAnimations(R.style.MyDialogAnimTheme);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qiandao:
                if (ActivityCompat.checkSelfPermission(CommonUtils.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CommonUtils.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Toast.makeText(CommonUtils.getContext(), "签到失败，请查看是否打开定位和网络！", Toast.LENGTH_SHORT).show();
                    return;
                }

                mLManager = (LocationManager) CommonUtils.getContext().getSystemService(CommonUtils.getContext().LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                criteria.setAccuracy(0);
                criteria.setBearingAccuracy(Criteria.ACCURACY_MEDIUM);
                criteria.setCostAllowed(true);
                String provider = mLManager.getBestProvider(criteria, true);
                mLManager.requestLocationUpdates(provider, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        AssetManager assets = CommonUtils.getContext().getAssets();
                        ModifyOffsetUtils mo = null;
                        try {
                            mo = ModifyOffsetUtils.getInstance(assets.open("axisoffset.dat"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        PointDouble pointerCoords = mo.s2c(new PointDouble(location.getLatitude(), location.getLongitude()));
                        getAddress(pointerCoords.x + "", pointerCoords.y + "");
                        if (ActivityCompat.checkSelfPermission(CommonUtils.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CommonUtils.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            Toast.makeText(CommonUtils.getContext(), "签到失败，请查看是否打开定位和网络！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mLManager.removeUpdates(this);
//                        ModifyOffsetUtils.getInstance()
//                        String address = getAddress(mLatitude, mLongitude);
//                        Toast.makeText(CommonUtils.getContext(),"定位成功："+address,Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {}
                    @Override
                    public void onProviderEnabled(String provider) {}
                    @Override
                    public void onProviderDisabled(String provider) {}
                });

                break;
            case R.id.image:
                Toast.makeText(CommonUtils.getContext(),"功能不予开放！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.camoer:
                Toast.makeText(CommonUtils.getContext(),"功能不予开放！",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getAddress(final String latitude, final String longitude) {
        final String location = null;
        HttpUtils http = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("lngx", longitude + "");
        params.addQueryStringParameter("lngy", latitude + "");
        params.addQueryStringParameter("dtype", "json");
        http.send(HttpRequest.HttpMethod.GET, "http://lbs.juhe.cn/api/getaddressbylngb", params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        // 获取位置信息失败
                        Toast.makeText(CommonUtils.getContext(),"定位失败，请查看是否打开网络！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        // 获取位置信息成功
                        String result = arg0.result;
                        try {
                            JSONObject jo = new JSONObject(result);
                            JSONObject rowObj = jo.getJSONObject("row");  // 判断resultcode是否等于1
                            JSONObject resultObj = rowObj.getJSONObject("result");
                            String address = resultObj.getString("formatted_address");
                            SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss     ");
                            Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
                            String    str    =    formatter.format(curDate);
                            Toast.makeText(CommonUtils.getContext(),str+" "+address,Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CommonUtils.getContext(),"不好意思，定位失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }


}
