package com.wondertek.baiying.solr;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by wd on 2018/1/16.
 */
public class Person {

    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
