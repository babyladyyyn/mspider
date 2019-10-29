package com.mili.mspider.m48wx;

import com.mili.mspider.spider.base.Category;
import com.mili.mspider.spider.base.Index;

import java.util.stream.Stream;

public class M48WxIndex extends Index {
    public M48WxIndex() {
        super("https://m.48wx.org/xclass/0/1.html");
    }

    @Override
    protected Stream<Category> generateCategoryStream() {
        return Stream.of(new M48WxCategory(this,getUrl()));
    }
}
