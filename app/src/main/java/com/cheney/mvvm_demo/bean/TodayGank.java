package com.cheney.mvvm_demo.bean;

import androidx.annotation.NonNull;

import com.cheney.mvvm_demo.entity.Gank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * 今日干货
 */
public class TodayGank {
    private final List<Gank> fuli;// 福利

    private final List<Gank> android;// Android

    private final List<Gank> frondEnd;// 前端

    private final List<Gank> video;// 视频

    public static final long GROUP_ID_FULI = -1L;
    public static final long GROUP_ID_ANDROID = -2L;
    public static final long GROUP_ID_FRONT_END = -3L;
    public static final long GROUP_ID_VIDEO = -4L;

    private static Gank GANK_GROUP_FULI = new Gank(Gank.GROUP_FULI);
    private static Gank GANK_GROUP_ANDROID = new Gank(Gank.GROUP_ANDROID);
    private static Gank GANK_GROUP_FRONT_END = new Gank(Gank.GROUP_FRONT_END);
    private static Gank GANK_GROUP_VIDEO = new Gank(Gank.GROUP_VIDEO);

    public TodayGank(@NonNull List fuli, @NonNull List android, @NonNull List frondEnd, @NonNull List video) {
        super();
        this.fuli = fuli;
        this.android = android;
        this.frondEnd = frondEnd;
        this.video = video;
    }

    public TodayGank() {
        fuli = emptyList();
        android = emptyList();
        frondEnd = emptyList();
        video = emptyList();
    }

    public int size() {
        return fuli.size() + android.size() + frondEnd.size() + video.size();
    }

    public boolean isEmpty() {
        return fuli.isEmpty() && android.isEmpty() && frondEnd.isEmpty() && video.isEmpty();
    }

    public List<Gank> toList() {

        List<Gank> arrayList = new ArrayList();
        arrayList.add(GANK_GROUP_FULI);
        for (Gank gank : fuli) {
            gank.setTypeId(Gank.FULI);
        }
        arrayList.addAll(fuli);
        arrayList.add(GANK_GROUP_ANDROID);
        for (Gank gank : android) {
            gank.setTypeId(Gank.ANDROID);
        }
        arrayList.addAll(android);
        arrayList.add(GANK_GROUP_FRONT_END);
        for (Gank gank : frondEnd) {
            gank.setTypeId(Gank.FRONT_END);
        }
        arrayList.addAll(frondEnd);
        arrayList.add(GANK_GROUP_VIDEO);
        for (Gank gank : video) {
            gank.setTypeId(Gank.VIDEO);
        }
        arrayList.addAll(video);

        return arrayList;
    }

    // 根据id查找
    // 这里只是演示用, 不要吐槽性能
    public Gank findGankById(String gankId) {

        Gank fuliGank = findGank(fuli.iterator(), gankId);
        if (null != fuliGank) {
            Gank androidGank = findGank(android.iterator(), gankId);
            if (null != androidGank) {
                Gank frondEndGank = findGank(frondEnd.iterator(), gankId);
                if (null != frondEndGank) {
                    Gank videoGank = findGank(video.iterator(), gankId);
                    return videoGank;
                }
            }
        }
        return null;
    }

    // 写个统一的查找方法
    // 这里也不讨论性能
    public Gank findGank(Iterator iterator, String gankId) {

        Object result;
        while (((Iterator) iterator).hasNext()) {
            Object obj = ((Iterator) iterator).next();
            Gank it = (Gank) obj;

            if (it.getGankId() == gankId) {
                return it;
            }
        }
        return null;
    }

    public List getFuli() {
        return fuli;
    }

    public List getAndroid() {
        return android;
    }

    public List getFrondEnd() {
        return frondEnd;
    }

    public List getVideo() {
        return video;
    }
}

