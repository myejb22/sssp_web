package com.myejb22.sssp;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/9/5
 */
public class MongoDBTest {
    private MongoClient client = null;
    private MongoCollection<Document> collection = null;
    private MongoCursor cursor = null;

    @Before
    public void init() {
        client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("redisDb");
        collection = database.getCollection("user");
    }

    @After
    public void destroy() {
        if (null != cursor) {
            cursor.close();
        }

        if (null != client) {
            client.close();
        }
    }

    @Test
    public void testFindAll() {
        FindIterable<Document> documents = collection.find();
        cursor = documents.iterator();
        while (cursor.hasNext()) {
            Document doc = (Document)cursor.next();
            String name = doc.getString("name");
            String email = doc.getString("email");
            Double age = doc.getDouble("age");
            System.out.println("name:"+name+",age:"+age+",email:"+email);
        }
    }
}
