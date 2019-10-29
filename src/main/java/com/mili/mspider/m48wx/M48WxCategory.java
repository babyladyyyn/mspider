package com.mili.mspider.m48wx;

import com.mili.mspider.spider.base.Category;
import com.mili.mspider.spider.base.CategoryPage;
import com.mili.mspider.spider.base.Index;

public class M48WxCategory extends Category {
    public M48WxCategory(Index index, String url) {
        super(index, url);
    }

    @Override
    public CategoryPage generateCategoryPage() {
        return new M48WxCategoryPage(this, this.getUrl());
    }
}
