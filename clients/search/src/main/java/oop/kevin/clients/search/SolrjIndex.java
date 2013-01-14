package oop.kevin.clients.search;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-17
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class SolrjIndex {
    private static final Logger logger = LoggerFactory.getLogger(SolrjIndex.class);
    private static final String solrUrl = "http://localhost:8983/solr/";
    private static SolrServer solrServer = new HttpSolrServer(solrUrl);

    public SolrjIndex() {
    }

    public static void init() {
           updateIndex();
    }

    public static void updateIndex() {
        SolrInputDocument goods = new SolrInputDocument();
        goods.addField("id", "101");
        goods.addField("goodsIdName", "ID名称xyz0");
        goods.addField("goodsName", "商品名称0");
        goods.addField("goodsTopic", "1");
        goods.addField("sateTopic", "来电促销与");
        SolrInputDocument answer = new SolrInputDocument();
        answer.addField("id", "299");
        answer.addField("content", "陈晶学测试---问答管理---提问回答【0】");
        answer.addField("comment", "陈晶学测试---问答管理---提问回答【评价1】");
        answer.addField("createByName", "18110000099@haixue.com");

        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        documents.add(goods);
        documents.add(answer);
        try {
            solrServer.add(documents);
            solrServer.commit();
            logger.info("索引已经更新============");
            System.out.println("======================索引更新========================");
        } catch (SolrServerException e) {
            logger.debug("-------------");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public static void doSearch() {
        SolrQuery solrQuery = new SolrQuery("测试");
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrServer.query(solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        SolrDocumentList documents = queryResponse.getResults();
        logger.info("docments  {}, Documents counts getNumFound {}", documents, documents.getNumFound());
        logger.debug("search time is {}", queryResponse.getQTime());
        System.out.println("size====" + documents.size());
        System.out.println("000000000000===" + queryResponse.getQTime());

        for (SolrDocument doc : documents) {
            logger.info("content === {}", doc.getFieldValue("content"));
            System.out.println("==========" +  doc.getFieldValue("content"));
            logger.debug("comment === {}", doc.getFieldValue("comment"));
            System.out.println("-----------" + doc.getFieldValue("comment"));
        }

    }

    public static void main(String[] args) {
        doSearch();

    }
}
