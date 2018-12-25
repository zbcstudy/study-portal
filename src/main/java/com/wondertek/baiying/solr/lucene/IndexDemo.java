package com.wondertek.baiying.solr.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * 使用Lucene创建索引
 */
public class IndexDemo {

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        //指定文件目录
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D://indexDir"));

        //创建分词器对象
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        //创建索引写入器
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        Document document = new Document();

        document.add(new LongField("id", 1l, Field.Store.YES));
        document.add(new StringField("title", "lucene", Field.Store.YES));
        document.add(new TextField("content", "lucene是全文索引和搜索的开源API", Field.Store.YES));

        indexWriter.addDocument(document);
        indexWriter.commit();
        indexWriter.close();

    }



}
