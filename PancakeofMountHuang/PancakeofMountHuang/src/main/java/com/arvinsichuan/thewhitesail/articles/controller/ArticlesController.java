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

package com.arvinsichuan.thewhitesail.articles.controller;

import com.arvinsichuan.general.WebTransmissionInfo;
import com.arvinsichuan.thewhitesail.articles.entity.Article;
import com.arvinsichuan.thewhitesail.articles.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;



/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/8
 * <p>
 * Package: com.arvinsichuan.thewhitesail.articles.controller
 */
@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @Resource(name = "articleService")
    private ArticleService articleService;

    @RequestMapping(path = "/new", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebTransmissionInfo saveArticle(String articleTitle, String articleText) {
        WebTransmissionInfo info = new WebTransmissionInfo();
        Article article=new Article();
        article.setArticleTitle(articleTitle);
        article.setArticleText(articleText);
        boolean savingFlag= articleService.saveNewArticle(article);
        if (savingFlag){
            info.ok();
        }
        return info;
    }

    @RequestMapping(path = "/{article_uuid}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebTransmissionInfo updateArticle(@PathVariable("article_uuid") String article_uuid, Article article) {
        WebTransmissionInfo info = new WebTransmissionInfo();
        return info;
    }

    @RequestMapping(path = "/publishedList",method = RequestMethod.GET)
    public List<Article> getPublishedArticles(){
        return null;
    }

    @RequestMapping(path = "/privateList",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Article> getPrivateArticles(){
        return  null;
    }

}
