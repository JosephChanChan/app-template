package com.byb.framework.utils.previous;

import org.elasticsearch.ResourceAlreadyExistsException;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class Esutil {

    public static Client client = null;

    /**
     * 获取客户端
     * @return
     */
    public static  Client getClient() {
        if(client!=null){
            return client;
        }
        try {
            Settings settings = Settings.builder().put("cluster.name", "byb_es").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.10.237"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static int autoCreateIndex(String indexName, List<Map<String,String>> columnInfo) {
        //创建映射
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder()
                    .startObject()
                    .startObject("properties");
            for (Map<String, String> map : columnInfo) {
                mapping.startObject(map.get("columnName")).field("type", map.get("columnType")).endObject();
            }
            mapping.endObject().endObject();
        } catch (IOException e) {
            return -2;
        }

        //index：索引名   type：类型名（可以自己定义）
        PutMappingRequest putmap = Requests.putMappingRequest(indexName).type("doc").source(mapping);
        //创建索引
        try {
            getClient().admin().indices().prepareCreate(indexName).execute().actionGet();
            //为索引添加映射
            getClient().admin().indices().putMapping(putmap).actionGet();
            return 0;
        } catch (ResourceAlreadyExistsException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int bulkupdate() {
        client=getClient();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        try {
            for(int i=1;i<100000;i++) {
                bulkRequest.add(client.prepareIndex("twitter", "tweet", String.valueOf(i))
                        .setSource(jsonBuilder()
                                .startObject()
                                .field("user", "kimchy")
                                .field("postDate", new Date())
                                .field("message", "trying out Elasticsearch")
                                .endObject()
                        )
                );
            }
            BulkResponse bulkResponse = bulkRequest.get();
            if (bulkResponse.hasFailures()) {
                // process failures by iterating through each bulk response item
            }
            bulkRequest.request().requests().clear();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 1;
    }


//    public void esinstall(){
//        String Name="D:\\test\\test.txt";
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(Name));
//            String json = null;
//            int count = 0;
//            // 开启批量插入
//            BulkRequestBuilder bulkRequest = client.prepareBulk();
//            while ((json = br.readLine()) != null) {
//                bulkRequest.add(client.prepareIndex("test", "all")
//                        .setSource(json));
//                // 每一千条提交一次
//                if (count % 1000 == 0) {
//                    bulkRequest.execute().actionGet();
//                    //此处新建一个bulkRequest，类似于重置效果
//                    bulkRequest = client.prepareBulk();
//                    System.out.println("提交了：" + count);
//                }
//                count++;
//            }
//            bulkRequest.execute().actionGet();
//            System.out.println("插入完毕");
//            br.close();
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//     }

}