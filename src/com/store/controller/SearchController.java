package com.store.controller;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
// 
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrQuery.ORDER;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocument;
//import org.apache.solr.common.SolrDocumentList;
// 
// 
//public class SearchController {
//	//指定solr服务器的地址  
//    private final static String SOLR_URL = "http://localhost:8080/solr/index.html#/store"; 
//    
//    
//    public void search() throws SolrServerException, IOException {
//    	HttpSolrClient httpSolrServer = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000).withSocketTimeout(60000).build();
//		//创建查询数据对象（便于设置查询条件）
//		SolrQuery solrQuery = new SolrQuery();
//		//设置查询的域和值，这个在之后的项目中可以用于动态
//		//方法一：参数q就代表query查询
//		//solrQuery.set("q","name:佘超伟123");
//		//方法二：(一般使用该方法)
//		solrQuery.setQuery("*:*");
//		//方法三：通过设置默认域
//		//solrQuery.set("df", "name");
//		//solrQuery.setQuery("佘超伟");
//		
//		//设置查询过滤条件(可以设置多个，只要域和值有改变就可以了)
//		//solrQuery.set("fq", "id:haha123");
//		//添加排序方式（可选内容）
//		//solrQuery.addSort("需要排序的域",ORDER.asc);//升序
//		//solrQuery.addSort("需要排序的域",ORDER.desc);//降序
//		//设置分页处理(比如这是设置每次显示5个)
//		solrQuery.setStart(0);
//		solrQuery.setRows(5);
//		//设置只查询显示指定的域和值(第二个参数可以是多个，之间用“逗号”分割)
//		//solrQuery.set("fl", "name");
//		//设置某域进行高亮显示
//		solrQuery.setHighlight(true);
//		solrQuery.addHighlightField("name");
//		//设置高亮显示格式的前后缀
//		solrQuery.setHighlightSimplePre("<span style='color:red'>");
//		solrQuery.setHighlightSimplePost("</span");	
//		
//		//执行查询，获得查询结果对象
//		QueryResponse query = httpSolrServer.query(solrQuery);
//		//获取查询的结果集
//		SolrDocumentList results = query.getResults();
//		//获取高亮显示的查询结果
//		//注意点：因为高亮的结果和正常的查询结果是不一样的，所以要进行特别的处理
//		Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
//		//遍历结果集
//		for (SolrDocument solrDocument : results) {
//			String idStr = (String) solrDocument.get("id");
//			System.out.println("id----------------" + idStr);
//			String nameStr = (String) solrDocument.get("name");
//			System.out.println("name----------------" + nameStr);
//			System.out.println("===========高亮显示=====================");
//			Map<String, List<String>> map = highlighting.get(idStr);
//			List<String> list = map.get("name");
//			String resultString = list.get(0);
//			System.out.println("高亮结果为：-----" + resultString);
//		}		
//
//    }
//    public static void main(String[] args) {
//    	SearchController s = new SearchController();
//    	try {
//			s.search();
//		} catch (SolrServerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//
//}

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController{
	
	@RequestMapping("search")
	public ModelAndView search() {
		return new ModelAndView("jsp/search");
	}
}