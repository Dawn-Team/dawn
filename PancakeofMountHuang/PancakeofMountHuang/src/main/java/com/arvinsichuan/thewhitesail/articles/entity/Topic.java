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

package com.arvinsichuan.thewhitesail.articles.entity;


import com.arvinsichuan.general.PmhConfigurations;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
@Table(name = "topics")
public class Topic implements Serializable {
    private static final long serialVersionUID = 5889885016886937689L;

    @Id
    @Column(name = "topic_name", nullable = false, length = 32)
    private String topicName;

    @Column(name = "topic_heat", nullable = false)
    @ColumnDefault("0")
    private int topicHeat;

    @OneToMany(mappedBy = "articleTopic",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Article> relatedArticles;

    public Topic(){
    }

    public Topic(String topicName, int topicHeat, List<Article> relatedArticles) {
        this.topicName = topicName;
        this.topicHeat = topicHeat;
        this.relatedArticles = relatedArticles;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTopicHeat() {
        return topicHeat;
    }

    public void setTopicHeat(int topicHeat) {
        this.topicHeat = topicHeat;
    }

    public List<Article> getRelatedArticles() {
        return relatedArticles;
    }

    public void setRelatedArticles(List<Article> relatedArticles) {
        this.relatedArticles = relatedArticles;
    }
}
