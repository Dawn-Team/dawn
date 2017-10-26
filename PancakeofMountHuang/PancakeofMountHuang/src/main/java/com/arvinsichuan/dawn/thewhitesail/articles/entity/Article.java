/*
 *     This project is part of project Dawn, A Data Process Solution.
 *     Copyright (C) 2017, Dawn team<https://github.com/Dawn-Team>.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.arvinsichuan.dawn.thewhitesail.articles.entity;


import com.arvinsichuan.dawn.general.DbGeneral;
import com.arvinsichuan.dawn.thewhitesail.users.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/7
 * <p>
 * Package: com.arvinsichuan.thewhitesail.articles.entity
 */
@Entity
@Table(schema = DbGeneral.SCHEMA_NAME, name = "articles")
public class Article implements Serializable {
    private static final long serialVersionUID = -7306373709517800488L;

    @Id
    // using 36 length in case of '-'
    @Column(name = "article_uuid", length = 36)
    @GeneratedValue
    private UUID articleUUID;

    @Column(name = "article_title", length = 128, nullable = false)
    private String articleTitle;

    @Column(name = "article_text", length = 102400)
    private String articleText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_user", insertable = false, nullable = false,
            foreignKey = @ForeignKey(name = "fk_articles_users"))
    @JsonBackReference
    private User articleUser;

    @Column(name = "article_datetime")
    private LocalDateTime articleDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_topic", insertable = false, nullable = false,
            foreignKey = @ForeignKey(name = "fk_articles_topics"))
    @JsonBackReference
    private Topic articleTopic;

    @Column(name = "article_click")
    private int articleClick;

    @Column(name = "article_status", length = 16)
    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;

    public Article() {

    }

    public Article(UUID articleUUID, String articleTitle, String articleText, User articleUser, LocalDateTime
            articleDatetime, Topic articleTopic, int articleClick, ArticleStatus articleStatus) {
        this.articleUUID = articleUUID;
        this.articleTitle = articleTitle;
        this.articleText = articleText;
        this.articleUser = articleUser;
        this.articleDatetime = articleDatetime;
        this.articleTopic = articleTopic;
        this.articleClick = articleClick;
        this.articleStatus = articleStatus;
    }

    public UUID getArticleUUID() {
        return articleUUID;
    }

    public void setArticleUUID(UUID articleUUID) {
        this.articleUUID = articleUUID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public User getArticleUser() {
        return articleUser;
    }

    public void setArticleUser(User articleUser) {
        this.articleUser = articleUser;
    }

    public LocalDateTime getArticleDatetime() {
        return articleDatetime;
    }

    public void setArticleDatetime(LocalDateTime articleDatetime) {
        this.articleDatetime = articleDatetime;
    }

    public Topic getArticleTopic() {
        return articleTopic;
    }

    public void setArticleTopic(Topic articleTopic) {
        this.articleTopic = articleTopic;
    }

    public int getArticleClick() {
        return articleClick;
    }

    public void setArticleClick(int articleClick) {
        this.articleClick = articleClick;
    }

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }
}
