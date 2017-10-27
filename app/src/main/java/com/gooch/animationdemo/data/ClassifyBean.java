package com.gooch.animationdemo.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @description: TODO
 * Date: 2017/10/26 19:56
 * @author: zhaoguangchao(gooch)
 * Email:zhaoguangchao@100tal.com
 */

public class ClassifyBean implements Serializable {


    private static final long serialVersionUID = 8005312700423228875L;
    public int code;
    public String message;
    public int ttl;
    public List<DataEntity> data;


    public static class DataEntity implements Serializable {
        private static final long serialVersionUID = -5698955887324289359L;

        public String param;
        public String type;
        public String style;
        public String title;
        public BannerEntity banner;
        public List<BodyEntity> body;

        public static class BannerEntity {

            public List<BottomEntity> bottom;

            public static class BottomEntity {
                public int id;
                public String title;
                public String image;
                public String hash;
                public String uri;
                public int resource_id;
                public String request_id;
                public boolean is_ad;
                public int cm_mark;
                public int index;
                public int server_type;


            }
        }

        public static class BodyEntity {

            public String title;
            public String cover;
            public String uri;
            public String param;
            @SerializedName("goto")
            public String gotoX;
            public int play;
            public String index;
            public String total_count;
            public String mtime;
            public int status;
            public int favourite;
            public boolean is_ad;
            public int cm_mark;


        }
    }
}
