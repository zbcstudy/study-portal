package com.wondertek.baiying.solr.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class LuceneHelloWorld {



    public static void main(String[] args) {
        String field = "业余草网站：www.xttblog.com，"
                + "公众号：业余草(yyucao)！IKAnalyer can analysis english text too";
        //构建ik分词模式，使用smart分词模式
        Analyzer analyzer = new IKAnalyzer(true);
        //获取Lucene的tokenStream对象
        TokenStream tokenStream = null;
        try {
            tokenStream = analyzer.tokenStream("firstField",new StringReader(field));

            //获取词元位置属性
            OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
            //获取词元文本属性
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

            //获取词元文本属性
            TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);

            //重置tokenstream
            tokenStream.reset();

            //迭代获取分词结果
            while (tokenStream.incrementToken()) {
                System.out.println(offsetAttribute.startOffset() + "-" + offsetAttribute.endOffset() +
                        " : " + charTermAttribute.toString() + " " + typeAttribute.type());
            }

            tokenStream.end();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (tokenStream != null) {
                try {
                    tokenStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
