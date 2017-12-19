package com.example.hjj.mddemo.gaode;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.hjj.mddemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AmapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener {
    private MapView mapView;

    private AMap map;
    private AMapLocationClient mapLocationClient = null;
    private OnLocationChangedListener mListener = null;//定位监听器

    private AMapLocationClientOption mapLocationClientOption = null;

    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        map = mapView.getMap();
        UiSettings uiSettings = map.getUiSettings();
        map.setLocationSource(this);
        uiSettings.setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
//        map.setTrafficEnabled(true);
//        map.showIndoorMap(true);//显示室内地图
//        map.setMapType(AMap.MAP_TYPE_SATELLITE);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.interval(2000L);//设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        map.setMyLocationStyle(myLocationStyle);
        initlc();
    }

    /**
     * 开始定位
     */
    private void initlc() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1000);//自定义的code
        }
        mapLocationClient = new AMapLocationClient(getApplicationContext());
        mapLocationClient.setLocationListener(this);
        mapLocationClientOption = new AMapLocationClientOption();
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationClientOption.setNeedAddress(true);
        mapLocationClientOption.setOnceLocation(false);
        mapLocationClientOption.setMockEnable(false);
//        mapLocationClientOption.setOnceLocationLatest(true);
        mapLocationClientOption.setInterval(2000);
        mapLocationClient.setLocationOption(mapLocationClientOption);
        mapLocationClient.startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mapLocationClient) {
            mapLocationClient.onDestroy();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
            double latitude = aMapLocation.getLatitude();//获取纬度
            double longitude = aMapLocation.getLongitude();//获取经度
            aMapLocation.getAccuracy();//获取精度信息
            aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
            aMapLocation.getCountry();//国家信息
            aMapLocation.getProvince();//省信息
            aMapLocation.getCity();//城市信息
            aMapLocation.getDistrict();//城区信息
            aMapLocation.getStreet();//街道信息
            aMapLocation.getStreetNum();//街道门牌号信息
            aMapLocation.getCityCode();//城市编码
            aMapLocation.getAdCode();//地区编码
            aMapLocation.getAoiName();//获取当前定位点的AOI信息
            aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
            aMapLocation.getFloor();//获取当前室内定位的楼层
            aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
            //获取定位时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(aMapLocation.getTime());
            df.format(date);
            if (isFirstLoc) {
                //设置缩放级别
                map.moveCamera(CameraUpdateFactory.zoomTo(17));
                map.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(latitude, longitude)));
                mListener.onLocationChanged(aMapLocation);
                StringBuffer buffer = new StringBuffer();
                buffer.append(aMapLocation.getCountry() + "" + aMapLocation.getProvince() + "" + aMapLocation.getCity() + "" + aMapLocation.getProvince() + "" + aMapLocation.getDistrict() + "" + aMapLocation.getStreet() + "" + aMapLocation.getStreetNum());
                Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                isFirstLoc = false;
            }

        } else {
            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
            Log.e("AmapError", "location Error, ErrCode:"
                    + aMapLocation.getErrorCode() + ", errInfo:"
                    + aMapLocation.getErrorInfo());

            Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
