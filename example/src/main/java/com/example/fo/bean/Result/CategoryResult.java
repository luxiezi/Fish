package com.example.fo.bean.Result;

import com.example.fo.base.BaseObject;

import java.util.List;

/**
 * User: 漆可
 * Date: 2016-08-27 18:36
 */
public class CategoryResult extends BaseResult
{
    public boolean is_end;
    public int total;
    public int current_count;
    public int next_limit;
    public int next_page;
    public String category_t;
    public String category_b;
    public String category_c;
    /**
     * rp_type : 103
     * num_iid : 2014491265979365
     * title : 韩国修眉神器美妆工具
     * pic_url : http://pic.repaiapp.com/pic/a9/a2/7b/a9a27b3744deeda279c2bc5d13d5b8d0526b2ff4.jpg@0e_310w_310h_0c_0i_1o_90Q_1x.jpg
     * now_price : 0.87
     * show_time : 无
     * origin_price : 1
     * discount : 8.7
     * start_discount : 1970-01-01 00:00:00
     * deal_num : 5
     * is_vip_price : 0
     * is_onsale : 1
     * total_love_number : 0
     * rp_quantity : 77
     * rp_sold : 29442
     * item_url : https://m.repai.com/item/view/id/2014491265979365?appkey=&amp;app_id=99999&amp;app_oid=&amp;app_version=&amp;app_channel=appstore&amp;sche=
     * item_urls : https://m.repai.com/item/view/id/2014491265979365?arg=ok
     * tagimage : http://static.m.repaiapp.com/static/new_img/buy_f.png?v=1
     * lefttop_pic : http://pic.repaiapp.com/pic/ac/2b/e0/ac2be03f3e2f86f021c2c69786c4cd7cdfc72d60.png
     * tag_bt0 : 爱美达人
     * tag_bt1 : 美妆教室
     * tag_bt0url : http://zhekou.repai.com/jkjby/view/rp_b2c_update1.php?type=1&amp;jid=19&amp;snew=1
     * tag_bt1url : http://zhekou.repai.com/jkjby/view/rp_b2c_update1.php?type=1&amp;jid=27&amp;snew=1
     */

    public List<ListBean> list;
    /**
     * cid : 1
     * name : 数码3C
     * pic : null
     * level3 : [{"cid":"32","name":"手机周边","pic":"http://pic.repaiapp.com/pic/22/84/f3/2284f3c4de12ac02fd85d91333489d106cb6ab99.png","url":"http://zhekou.repai.com/jkjby/view/rp_b2c_category_list.php?c_3=32"},{"cid":"46","name":"3C周边","pic":"http://pic.repaiapp.com/pic/22/84/f3/2284f3c4de12ac02fd85d91333489d106cb6ab99.png","url":"http://zhekou.repai.com/jkjby/view/rp_b2c_category_list.php?c_3=46"},{"cid":"47","name":"电脑配件","pic":"http://pic.repaiapp.com/pic/22/84/f3/2284f3c4de12ac02fd85d91333489d106cb6ab99.png","url":"http://zhekou.repai.com/jkjby/view/rp_b2c_category_list.php?c_3=47"}]
     * url : http://zhekou.repai.com/jkjby/view/rp_b2c_category_list.php?c_2=1
     */

    public List<CategoryTwoBean> category_two;
    /**
     * cid : 24
     * name : 顶间时尚
     * pic : http://pic.repaiapp.com/pic/22/84/f3/2284f3c4de12ac02fd85d91333489d106cb6ab99.png
     */

//    public List<CategoryThrBean> category_thr;
    public List<?> brand;

    public static class ListBean extends BaseObject
    {
        public String rp_type;
        public String num_iid;
        public String title;
        public String pic_url;
        public double now_price;
        public String show_time;
        public float origin_price;
        public double discount;
        public String start_discount;
        public int deal_num;
        public int is_vip_price;
        public int is_onsale;
        public int total_love_number;
        public int rp_quantity;
        public int rp_sold;
        public String item_url;
        public String item_urls;
        public String tagimage;
        public String lefttop_pic;
        public String tag_bt0;
        public String tag_bt1;
        public String tag_bt0url;
        public String tag_bt1url;
    }

    public static class CategoryTwoBean extends BaseObject
    {
        public String cid;
        public String name;
        public Object pic;
        public String url;
        /**
         * cid : 32
         * name : 手机周边
         * pic : http://pic.repaiapp.com/pic/22/84/f3/2284f3c4de12ac02fd85d91333489d106cb6ab99.png
         * url : http://zhekou.repai.com/jkjby/view/rp_b2c_category_list.php?c_3=32
         */

        public List<Level3Bean> level3;

        public static class Level3Bean extends BaseObject
        {
            public String cid;
            public String name;
            public String pic;
            public String url;
        }
    }

    public static class CategoryThrBean extends BaseObject
    {
        public String cid;
        public String name;
        public String pic;
    }
}
