package com.zhy.spider.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.zhy.spider.bean.LinkTypeData;
import com.zhy.spider.rule.Rule;
import com.zhy.spider.rule.RuleException;
import com.zhy.spider.util.TextUtil;

/**
 * 
 * @author zhy
 * 
 */
public class ExtractService
{
	/**
	 * @param rule
	 * @return
	 * @throws DocumentException 
	 * @throws SAXException 
	 */
	public static List<LinkTypeData> extract(Rule rule)
	{

		// 进行对rule的必要校验
		validateRule(rule);

		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		LinkTypeData data = null;
		try
		{
			/**
			 * 解析rule
			 */
			//String url = rule.getUrl();
			String[] params = rule.getParams();
			String[] values = rule.getValues();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			String htmlContent = "";
			java.io.InputStream inputStream;
			java.net.URL url = new java.net.URL(rule.getUrl());

			java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
			connection.connect();
			inputStream = connection.getInputStream();
			byte bytes[] = new byte[1024*100]; 
			int index = 0;
			int count = inputStream.read(bytes, index, 1024*100);
			//System.out.println (count);
			while (count != -1) {
				
			    index += count;
			    count = inputStream.read(bytes, index, 1);
			}
			htmlContent = new String(bytes, "gbk");

			//System.out.println(htmlContent);
			
			org.jsoup.nodes.Document doc = Jsoup.parse(htmlContent);
			//处理返回数据
			Elements results = new Elements();
			switch (type)
			{
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				//当resultTagName为空时默认去body标签
				if (TextUtil.isEmpty(resultTagName))
				{
					results = doc.getElementsByTag("body");
				}
			}

			for (Element result : results)
			{
				Elements links = result.getElementsByTag("a");

				for (Element link : links)
				{
					//必要的筛选
					String linkHref = link.attr("href");
					String linkText = link.text();

					data = new LinkTypeData();
					data.setLinkHref(linkHref);
					data.setLinkText(linkText);

					datas.add(data);
				}
				if (links.size() == 0) {
					Elements links1 = result.getElementsByTag("td");
					for (Element link : links1)
					{
						String linkText = link.text();

						data = new LinkTypeData();
						data.setLinkHref(null);
						data.setLinkText(linkText);

						datas.add(data);
					}
				}
			}
			
			/*Connection conn = Jsoup.connect(url);
			// 设置查询参数

			if (params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					conn.data(params[i], values[i]);
				}
			}

			// 设置请求类型
			Document doc = null;
			switch (requestType)
			{
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			}

			//处理返回数据
			Elements results = new Elements();
			switch (type)
			{
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				//当resultTagName为空时默认去body标签
				if (TextUtil.isEmpty(resultTagName))
				{
					results = doc.getElementsByTag("body");
				}
			}

			for (Element result : results)
			{
				Elements links = result.getElementsByTag("a");

				for (Element link : links)
				{
					//必要的筛选
					String linkHref = link.attr("href");
					String linkText = link.text();

					data = new LinkTypeData();
					data.setLinkHref(linkHref);
					data.setLinkText(linkText);

					datas.add(data);
				}
				if (links.size() == 0) {
					Elements links1 = result.getElementsByTag("td");
					for (Element link : links1)
					{
						String linkText = link.text();

						data = new LinkTypeData();
						data.setLinkHref(null);
						data.setLinkText(linkText);

						datas.add(data);
					}
				}
			}*/

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return datas;
	}

	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule)
	{
		String url = rule.getUrl();
		if (TextUtil.isEmpty(url))
		{
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://"))
		{
			throw new RuleException("url的格式不正确！");
		}

		if (rule.getParams() != null && rule.getValues() != null)
		{
			if (rule.getParams().length != rule.getValues().length)
			{
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}


}
