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

public class ClassifyBean  implements Serializable{

    private static final long serialVersionUID = -522825021104165105L;
    public int code;
    public String message;
    public int ttl;
    public List<DataEntity> data;

    public static class DataEntity implements Serializable{

        public static final long serialVersionUID = 2825212259995373367L;
        public String param;
        public String type;
        public String style;
        public String title;
        public BannerEntity banner;
        public List<BodyEntity> body;

        public static class BannerEntity implements Serializable {

            private static final long serialVersionUID = 173564147111788696L;
            public List<BottomEntity> bottom;

            public static class BottomEntity implements Serializable{
                public static final long serialVersionUID = -8987833653937442771L;

                public int id;
                public String title;
                public String image;
                public String hash;
                public String uri;
                @SerializedName("resource_id")
                public int resourceId;
                @SerializedName("request_id")
                public String requestId;
                @SerializedName("is_ad")
                public boolean isAd;
                @SerializedName("cm_mark")
                public int cmMark;
                public int index;
                @SerializedName("server_type")
                public int serverType;


            }
        }

        public static class BodyEntity implements Serializable{
            public static final long serialVersionUID = -9037115765038425572L;

            public String title;
            public String cover;
            public String uri;
            public String param;
            @SerializedName("goto")
            public String gotoX;
            public int play;
            public String index;
            @SerializedName("total_count")
            public String totalCount;
            public String mtime;
            public int status;
            public int favourite;
            @SerializedName("is_ad")
            public boolean isAd;
            @SerializedName("cm_mark")
            public int cmMark;
        }
    }
}
