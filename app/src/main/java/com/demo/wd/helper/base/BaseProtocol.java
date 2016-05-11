package com.demo.wd.helper.base;


import com.demo.wd.helper.http.HttpHelper;
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.IOUtils;
import com.demo.wd.helper.utils.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * 获取数据的基类
 * @param <T> getData()返回的数据类型
 */
public abstract class BaseProtocol<T> {
    /**
     * 获取数据的方法
     * 参数实现分页查询
     * @return
     */
    public List<T> getData(int index) {
        //先从本地缓存获取数据
        String result = getDataFromLocal(index) ;

        //判断返回的本地数据是否为空
        if (StringUtils.isEmpty(result)) {
            //如果为空，就从网络获取数据
            result = getDataFromNet(index);
        }
        return parseXml(result);
    }

    protected abstract List<T> parseXml(String result);

    /**
     * 从本地获取缓存数据
     * @return
     */
    public String  getDataFromLocal(int index) {
        //得到缓存目录
        File cacheDir = CommonUtils.getContext().getCacheDir();
        //创建缓存文件,文件名区分哪个页面哪个数据
        File cacheFile = new File(cacheDir.getAbsolutePath(),getKey()+"page"+index);
        BufferedReader br = null;
        String result = null;
        String time = null;
        try {
            br = new BufferedReader(new FileReader(cacheFile));
            //得到有效期
            time = br.readLine();
            Long usableTime = Long.parseLong(time);
            //判断有限期
            if (System.currentTimeMillis() < usableTime) {
                String temp = null ;
                StringBuffer sb = new StringBuffer();
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
                result = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(br);
        }

        return result;
    }

    /**
     * 从网络获取数据
     * @return
     */
    public String getDataFromNet(int index) {
        // url : http://127.0.0.1:8090/
        // home : http://127.0.0.1:8090/home?index=0
        // app  : http://127.0.0.1:8090/app?index=0
        // game : http://127.0.0.1:8090/game?index=0
        String url = getUrl()+getKey()+"/page"+index+".xml";

        HttpHelper.HttpResult httpResult = HttpHelper.get(url);

        String result = null;
        if (httpResult != null) {
            result = httpResult.getString();
            //把数据缓存到本地
            setDataToLocal(result,index);
        }

        return result;
    }


	protected void setDataToLocal(String data,int index) {
        //得到缓存目录
        File cacheDir = CommonUtils.getContext().getCacheDir();
        //创建缓存文件,文件名区分哪个页面哪个数据
        File cacheFile = new File(cacheDir.getAbsolutePath(),getKey()+"page"+index);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(cacheFile));
            long usableTime = System.currentTimeMillis() + 10 * 60 * 1000;
            bw.write(String.valueOf(usableTime));
            bw.newLine();
            bw.write(data);
            bw.flush();//刷新
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bw);
        }

    };


    /**
     * 获取key
     * @return
     */
    protected abstract String getKey();


    /**
     * 使用httpHelper中的哪个url
     * @return
     */
    protected abstract String getUrl();
}
