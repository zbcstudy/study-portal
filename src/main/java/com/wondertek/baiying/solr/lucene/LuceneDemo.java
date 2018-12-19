package com.wondertek.baiying.solr.lucene;

import com.alibaba.fastjson.serializer.FieldSerializer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class LuceneDemo {

    private Directory directory;
    private String[] ids = {"1", "2"};
    private String[] unIndex = {"Netherlands", "Italy"};
    private String[] unStored = {"Amsterdam has lots of bridges", "Venice has lots of canals"};
    private String[] text = {"Amsterdam", "Venice"};
    private IndexWriter indexWriter;

    private IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());

    @Test
    public void createIndex() throws IOException{
        directory = new RAMDirectory();
        //指定将索引信息打印到控制台
        indexWriterConfig.setInfoStream(System.out);
        indexWriter = new IndexWriter(directory, indexWriterConfig);

        FieldType fieldType = new FieldType();
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
        fieldType.setStored(true); //存储
        fieldType.setTokenized(true); //分词
        for (int i = 0; i < ids.length; i++) {
            Document document = new Document();
            document.add(new Field("id", ids[i], fieldType));
            document.add(new Field("country", unIndex[i], fieldType));
            document.add(new Field("contents", unStored[i], fieldType));
            document.add(new Field("city", text[i], fieldType));
            indexWriter.addDocument(document);
        }
        indexWriter.commit();

    }

    @Test
    public void testDelete() throws IOException {
        RAMDirectory ramDirectory = new RAMDirectory();
        IndexWriter indexWriter = new IndexWriter(ramDirectory, new IndexWriterConfig(new StandardAnalyzer()));

        Document document = new Document();
        document.add(new TextField("ID", "1", Field.Store.YES));
        indexWriter.addDocument(document);
        indexWriter.commit();

        //无法删除ID为1的
        indexWriter.deleteDocuments(new Term("ID", "1"));
        indexWriter.commit();

        DirectoryReader reader = DirectoryReader.open(ramDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("ID", "1")), 10);
        System.out.println(topDocs.totalHits);
    }

    /**
     * 创建索引写入器
     * @param indexPath
     * @param create
     * @return
     */
    public IndexWriter getIndexWriter(String indexPath, boolean create) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        if (create) {
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        } else {
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }
        Directory directory = FSDirectory.open(Paths.get(indexPath));

        IndexWriter indexWriter = new IndexWriter(directory, config);
        return indexWriter;
    }
}
