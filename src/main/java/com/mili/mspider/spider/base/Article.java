package com.mili.mspider.spider.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

abstract public class Article extends UrlObject {
    public String name;
    public String image;
    public String desc;
    private Category category;

    public Article(Category category, String url) {
        super(url);
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Stream<Chapter> generateChapters(){
        ChapterPage chapterPage = generateChapterPage();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(chapterPage, 0),false).flatMap(_chapterPage -> {
            return _chapterPage.generateChapter();
        });
    }

    public abstract ChapterPage generateChapterPage();

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("image", image)
                .append("desc", desc)
                .append("category", category)
                .toString();
    }
}
