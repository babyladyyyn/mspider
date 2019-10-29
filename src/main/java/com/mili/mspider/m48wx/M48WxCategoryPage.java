package com.mili.mspider.m48wx;

import com.mili.mspider.spider.base.Article;
import com.mili.mspider.spider.base.Category;
import com.mili.mspider.spider.base.CategoryPage;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Html;

import java.util.stream.Stream;

public class M48WxCategoryPage extends CategoryPage {
    public M48WxCategoryPage(Category category, String url) {
        super(category, url);
    }

    @Override
    protected Stream<Article> generateArticleStream() {
        Html html = getHtml();
        CategoryPage categoryPage = generateCategoryPage();
        return html.$("#main .hot_sale").nodes().stream().flatMap(node -> {
            String allUrl = fixWholeUrl(node.$("a", "href").toString());
            Article article = new M48WxArticle(categoryPage.getCategory(), allUrl);
            return Stream.of(article);
        });
    }

    @Override
    public boolean hasNext() {
        Html html = getHtml();
        String categoryPageUrl = html.$("div p.page a", "href").toString();
        return StringUtils.isNotEmpty(categoryPageUrl);
    }

    @Override
    public CategoryPage generateCategoryPage() {
        Html html = getHtml();
        String categoryPageUrl = html.$("div p.page a", "href").toString();
        return new M48WxCategoryPage(this.getCategory(), categoryPageUrl);
    }
}
