package oop.kevin.clients.search;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
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
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-21
 * Time: 下午1:45
 * 测试git 提交
 */
public class ExtendSolrServer {
    private static Logger logger = LoggerFactory.getLogger(ExtendSolrServer.class);
    private static ExtendSolrServer solrServer = null;
    private static HttpSolrServer httpSolrServer = null;
    private static String sorlServerUrl = "http://localhost:8983/solr/";

    public ExtendSolrServer() {
    }

    public static synchronized  ExtendSolrServer getInstance() {
        if (solrServer == null) {
            solrServer = new ExtendSolrServer();
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

   /* public void updateData(Answer obj) {
        if (obj == null) {
            logger.debug("{}对象信息为空", obj);
        }

        HttpSolrServer httpSolrServer = ExtendSolrServer.getInstance().getHttpSolrServer();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", Page.getUiiid());
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
        HttpSolrServer httpSolrServer = ExtendSolrServer.getInstance().getHttpSolrServer();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", Page.getUiiid());
//        document.addField("goodsIdName", goods.getGoodsIdName());
//        document.addField("goodsName", goods.getGoodsName());
//        document.addField("goodsTopic", goods.getGoodsTopic());
//        document.addField("saleTopic", goods.getSaleTopic());
        document.addField("goodsidname", goods.getGoodsIdName());
        document.addField("goodsname", goods.getGoodsName());
        document.addField("goodstopic", goods.getGoodsTopic());
        document.addField("saletopic", goods.getSaleTopic());

        try {
            httpSolrServer.add(document);
            httpSolrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
    public List<Answer> searchContent(String question, String content, String comment*//*, Page page*//*) {
        List<Answer> objectList = new ArrayList<Answer>();
        Answer answer = null;
        HttpSolrServer httpSolrServer = ExtendSolrServer.getInstance().getHttpSolrServer();
        SolrQuery query = new SolrQuery();
        StringBuilder returnResult = new StringBuilder("");
        String returns = "";
        if (StringUtils.isNotEmpty(question)) {
            returns = returns + "(问题:" + question + "OR 问答:" + question + ")";
        }
        if (StringUtils.isNotEmpty(content)) {
            returns = returns + "(回复:" + content + "OR 回答:" + content + ")";
        }
        if (StringUtils.isNotEmpty(comment)) {
            returns = returns + "(评论:" + comment + "OR 评价:" + comment + ")";
        }


        query.setQuery(returns);
//        query.setStart(page.getCurrentPageNo());
//        query.setRows(page.getPageSize());
        query.addSortField("id", SolrQuery.ORDER.asc);

        query.setHighlight(true);
        query.addHighlightField("question");
        query.addHighlightField("content");
        query.addHighlightField("comment");
        query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        query.setHighlightSimplePost("</font>");//后缀
        query.setHighlightSnippets(2);
        query.setHighlightFragsize(1000);

        query.setFacet(true).setFacetMinCount(1).setFacetLimit(5).addFacetField("content");

        try {
            QueryResponse response = httpSolrServer.query(query);
            SolrDocumentList solrDocuments = response.getResults();
            Integer counts = (int)solrDocuments.getNumFound();
            logger.info("search counts {}", counts);
//            page.setPageSize(counts);
            Map<String, Map<String, List<String>>> highlightingMaps = response.getHighlighting();

            for (SolrDocument document : solrDocuments) {
                answer = new Answer();
                answer.setContent(document.getFieldValue("content").toString());
                answer.setComment(document.getFieldValue("comment").toString());
                objectList.add(answer);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return objectList;
    }


    public static void main(String[] args) {

        ExtendSolrServer solrServer = ExtendSolrServer.getInstance();
        List<Goods> goodsList = new ArrayList<Goods>();
        int i = 10;

        while (i-- > 0) {
            Goods goods = new Goods();
            goods.setId(Long.valueOf(getUiiid()));
            goods.setGoodsIdName("测试搜索" + i + "goodsidname");
            goods.setGoodsName("不坑你坑谁" + i + "点卡母");
            goods.setGoodsTopic("主题很悲惨");
            goods.setSaleTopic("卖了");
//            goodsList.add(goods);
            solrServer.updateIndexByGoods(goods);

//            Answer answer = new Answer();
//            answer.setAgreeCount(RandomUtils.nextInt(50)); // 反对人数
//            answer.setComment("陈晶学测试---问答管理---提问回答【评价" + i + "】"); // 评价内容
//            answer.setContent("陈晶学测试---问答管理---提问回答【" + i + "】"); // 回答内容
//            int randomFlag = RandomUtils.nextInt(10);
//            answer.setDisagreeCount(RandomUtils.nextInt(50)); // 同意人数
//            answer.setCreateDate(new Date()); // 创建时间
//            answer.setAnswerStatus(i % 2 + 1); // 当前回答记录的状态  1：正常；2：冻结
//            answer.setIsAdminAnswered(1); //  是否是系统管理员回复（此回复指系统用户对问题的回复，系统用户的回复始终只有一条）1:不是；2：是
//            solrServer.updateData(answer);
        }

        solrServer.searchContent("这个先随便", "问答管理", "提问回答");
    }*/


    public static int getUiiid() {
        Random ran = new Random();
        //初始化数组
        int abc = 122;
        int a[]=new int[999];
        for(int i=0;i<9;i++)
        {
            //生成随机数放到数组中
            a[i]=ran.nextInt(289);
            abc = a[i];
            System.out.println("===========" + abc);
        }
        return abc;

    }
}

