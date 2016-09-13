package com.kaiyuanxueyuan.fragment.liveFragment;

import com.kaiyuanxueyuan.utils.ShowInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by 张国帅 on 2016/9/12.
 */
public class LiveTrailer {

    public static void  getTraile(){
        Document doc = null;
        try {
            System.out.println("start");
            doc = Jsoup.connect(
                    "http://epg.tvsou.com/programys/TV_1/Channel_3/W1.htm")
                    .get();
            Elements content = doc.getElementById("con2").getAllElements();
            int count = content.size();
            for (int i = 0; i < count; i++) {
                Element pm1 = content.get(i).getElementById("PMT1");
                if (pm1 != null) {
                    Element e1 = content.get(i).getElementById("e1");
                    Element e2 = content.get(i).getElementById("e2");
                    System.out.println("电视台节目预告=========================：" + e1.text()+"  "+e2.text());
                }
            }
        } catch (Exception e) {
        }
        System.out.println("end");
    }
}
