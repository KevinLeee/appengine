package oop.kevin.clients.search;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
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
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-21
 * Time: 下午1:45
 * To change this template use File | Settings | File Templates.
 */
public class IndexAndSearchByGoods {
    private static Logger logger = LoggerFactory.getLogger(IndexAndSearchByGoods.class);
    private static IndexAndSearchByGoods solrServer = null;
    private static HttpSolrServer httpSolrServer = null;
    private static String sorlServerUrl = "http://localhost:8983/solr/";

    public IndexAndSearchByGoods() {
    }

    public static synchronized IndexAndSearchByGoods getInstance() {
        if (solrServer == null) {
            solrServer = new IndexAndSearchByGoods();
        }
        return solrServer;
    }

    public static HttpSolrServer getHttpSolrServer() {
        if (httpSolrServer == null) {
            httpSolrServer = new HttpSolrServer(sorlServerUrl);
            httpSolrServer.setSoTimeout(1000);  // socket read timeout
            httpSolrServer.setConnectionTimeout(1000);
            httpSolrServer.setDefaultMaxConnectionsPerHost(100);
            httpSolrServer.setMaxTotalConnections(100);
            httpSolrServer.setFollowRedirects(false);  // defaults to false  
            //allowCompression defaults to false.  
            //Server side must support gzip or deflate for this to have any effect.  
            httpSolrServer.setAllowCompression(true);
            httpSolrServer.setMaxRetries(1); // defaul
        }
        return httpSolrServer;
    }

    /*public void updateData(Answer obj) {
        if (obj == null) {
            logger.debug("{}对象信息为空", obj);
        }

        HttpSolrServer httpSolrServer = IndexAndSearchByGoods.getInstance().getHttpSolrServer();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", getUiiid());
        document.addField("content", obj.getContent());
        document.addField("comment", obj.getComment());
        try {
            httpSolrServer.add(document);
            httpSolrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void updateIndexByGoods(Goods goods) {
        HttpSolrServer httpSolrServer = IndexAndSearchByGoods.getInstance().getHttpSolrServer();
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", getUiiid());
        doc.addField("goodsIdName", goods.getGoodsIdName());
        doc.addField("goodsName", goods.getGoodsName());
        doc.addField("goodIsTopic", goods.getGoodsTopic());
        doc.addField("saleTopic", goods.getSaleTopic());
        try {
            httpSolrServer.add(doc);
            httpSolrServer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

    }
*/
    public static void main(String[] args) {

        HttpSolrServer httpSolrServer = IndexAndSearchByGoods.getInstance().getHttpSolrServer();
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", getUiiid());
        doc.addField("goodsIdName", "阿第克");
        doc.addField("goodsName", "商品的名字");
        doc.addField("goodsTopic", "主题很悲惨");
        doc.addField("saleTopic", "愤怒");
        try {
            httpSolrServer.add(doc);
            httpSolrServer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }


    public static int getUiiid() {
        Random ran = new Random();
        //初始化数组
        int abc = 122;
        int a[]=new int[999];
        for(int i=0;i<9;i++)
        {
            //生成随机数放到数组中
            a[i]=ran.nextInt(289);
            abc = a[i]*888;
            System.out.println("===========" + abc);
        }
        return abc;

    }
}

