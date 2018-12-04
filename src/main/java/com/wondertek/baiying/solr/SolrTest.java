package com.wondertek.baiying.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

/**
 * Created by wd on 2018/1/16.
 */
public class SolrTest {

    /**
     * solr地址一定要正确 my_core一定要在solr中存在
     */
    private final static String SOLR_URL = "http://127.0.0.1:8983/solr/my_core";

    /**
     * 创建solrServer
     */
    public HttpSolrClient createSolrServer() {
        HttpSolrClient httpSolrClient = null;
        httpSolrClient = new HttpSolrClient(SOLR_URL);
        return httpSolrClient;
    }

    /**
     * 向索引库中添加文档
     * 文档field中的字段一定要在solr中存在，否则无法识别
     */

    public void addDoc() throws IOException, SolrServerException {
        //构建一篇文档
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加数据
        document.addField("id", "22");
        document.addField("name", "zhaobicheng22");
        document.addField("description"," 赵必成否撒个哈公我乌尔禾股票无");
        HttpSolrClient client = new HttpSolrClient(SOLR_URL);
        client.add(document);
        client.commit();
        client.close();
        System.out.println("添加数据成功");
    }

    /**
     * 查询数据
     */
    public void querySolr() throws IOException, SolrServerException {
        HttpSolrClient client = new HttpSolrClient(SOLR_URL);
        SolrQuery query = new SolrQuery();

        //设置查询参数
        query.set("q", "zhaobicheng22");

        //参数fq, 给query增加过滤查询条件
        //query.addFilterQuery("id:[0 to 9]");

        //设置默认搜索域
        query.set("df","name");

        //设置排序参数
        query.setSort("id", SolrQuery.ORDER.desc);

        //设置分页
        query.setStart(0);
        query.setRows(10);

        //设置高亮
        query.setHighlight(true);

        //设置高亮字段
        query.addHighlightField("description");

        //设置高亮样式
        query.setHighlightSimplePre("<font color = 'read'>");
        query.setHighlightSimplePost("</font>");

        //获取查询结果
        QueryResponse response = client.query(query);

        /**
         * 获取结果集有两种方式
         * 1 文档格式获取
         * 2 实体对象获取
         */
        SolrDocumentList documentList = response.getResults();
        System.out.println("获取到文档结果集总数：" + documentList.getNumFound());
        for (SolrDocument entries : documentList) {
            System.out.println("id: "+entries.get("id")+"; name:"+entries.get("name")+"; description:"+entries.get("description"));
        }

        List<Person> list = response.getBeans(Person.class);
        if (list != null || list.size() > 0) {
            for (Person person : list) {
                System.out.println(person);
            }
        }

    }


    public static void main(String[] args) throws IOException, SolrServerException {
        SolrTest solrTest = new SolrTest();
        //solrTest.addDoc();
        solrTest.querySolr();
    }
}
