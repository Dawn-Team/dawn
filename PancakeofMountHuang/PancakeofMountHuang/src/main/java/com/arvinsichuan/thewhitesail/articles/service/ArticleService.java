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

package com.arvinsichuan.thewhitesail.articles.service;


import com.arvinsichuan.thewhitesail.articles.entity.Article;
import com.arvinsichuan.thewhitesail.articles.repository.ArticlesRepository;
import com.arvinsichuan.thewhitesail.articles.repository.TopicsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/8
 * <p>
 * Package: com.arvinsichuan.thewhitesail.articles.service
 * @author ArvinSiChuan
 */
@Service("articleService")
public class ArticleService {

    @Resource(name = "articlesRepository")
    private ArticlesRepository articlesRepository;

    @Resource(name = "topicsRepository")
    private TopicsRepository topicsRepository;

    public boolean saveNewArticle(Article article) {
        try {
            articlesRepository.save(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
