package com.wondertek.baiying.mongoDB;

import com.mongodb.MongoClient;
import com.mongodb.client.*;

import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 * Created by wd on 2017/6/22.
 */
public class MongoFactory {

    public static void main(String[] args) {
        try {
            MongoClient client = new MongoClient("127.0.0.1",27017);
			for (String s : client.listDatabaseNames()) {
				System.out.println(s);
			}
			MongoDatabase database = client.getDatabase("interacts");
			MongoCollection<Document> collection = database.getCollection("comments");
            long count = collection.count();
			System.err.println(count);
//			for (Document document : collection.find()) {
//                System.out.println(document);
//            }
            FindIterable<Document> documents = collection.find();
            collection.insertOne(new Document("abc","123").append("name","zhaobicheng"));
            collection.updateMany(Filters.eq("abc","123"),new Document("$set",new Document("sss",222)));
            for (Document document:documents) {
                System.out.println(document);
            }
        } catch (Exception e) {
           // e.printStackTrace();
            System.err.println(e.getClass().getName()+ ":" + e.getMessage());
        }
    }
	
}
