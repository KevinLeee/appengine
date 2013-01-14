package oop.kevin.clients.search;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-18
 * Time: 上午11:15
 * To change this template use File | Settings | File Templates.
 */
public class SearchBySolr {
    private static Logger logger = LoggerFactory.getLogger(SearchBySolr.class);
    public static void main(String[] args) {
        String url = "http://localhost:8983/solr/";
        SolrServer server = new HttpSolrServer(url);
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField("id", "1");
        doc1.addField("title", "云南xxx科技");
        doc1.addField("cat", "企业信息门户，元数据，数字沙盘，知识管理");
        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField("id", "2");
        doc2.addField("title", "胡启稳");
        doc2.addField("cat", "知识管理，企业信息门户，云南，昆明");
        SolrInputDocument doc3 = new SolrInputDocument();
        doc3.addField("id", "3");
        doc3.addField("title", "liferay");
        doc3.addField("test_s", "这个内容能添加进去么？这是动态字段呀");
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);

        try {
            server.add(docs);
            server.commit();
            logger.info("索引已经更新============");
            System.out.println("======================索引更新========================");
        } catch (SolrServerException e) {
            logger.debug("-------------");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        SolrQuery query = new SolrQuery("云南");
            QueryResponse response = null;
            try {
                response = server.query(query);
                SolrDocumentList docc = response.getResults();
                System.out.println("文档个数：" + docc.getNumFound());
                System.out.println("查询时间：" + response.getQTime());
                for (SolrDocument doc : docc) {
                    System.out.println("id: " + doc.getFieldValue("id"));
                    System.out.println("name: " + doc.getFieldValue("title"));
                    System.out.println();
                }
            } catch (SolrServerException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
    }
}

