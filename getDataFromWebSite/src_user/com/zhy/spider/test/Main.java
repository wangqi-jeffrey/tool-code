package com.zhy.spider.test;

import java.io.Reader;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yihaomen.mybatis.inter.IUserOperation;
import com.yihaomen.mybatis.model.Region;
import com.yihaomen.mybatis.model.RegionMapper;
import com.yihaomen.test.TestTest;
import com.zhy.spider.bean.LinkTypeData;
import com.zhy.spider.core.ExtractService;
import com.zhy.spider.rule.Rule;

public class Main {
	
	private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private static Log logger = LogFactory.getLog(TestTest.class);

    static{
        try{
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

	private static String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015/";
	public static void main(String[] args) {
		/*SqlSession session = sqlSessionFactory.openSession();
		// 查询所有需要更新的数据
		// 查找编号
		RegionMapper userOperation=session.getMapper(RegionMapper.class);
		List<Region> list = userOperation.selectList();
		String urlStr = "";
		int count = 0;
		for (Region r : list) {
			String code = r.getCode();
			if (r.getGrade() == 4) {
				urlStr = code.substring(0, 2) + "/" + code.substring(2, 4) + "/" + code.substring(4, 6) + "/" + code.substring(0, 9) + ".html";
				System.out.println(urlStr);
				Rule rule = new Rule(url+urlStr,null, null,"villagetr", Rule.CLASS, Rule.GET);
				List<LinkTypeData> extracts = ExtractService.extract(rule);
				for (int l = 1; l<= extracts.size() ; l++){
					if (l%3 == 0 && l != 0) {
						System.out.println("===============================居委会=====================================");
						LinkTypeData d = extracts.get(l-1);
						String linkTextd = d.getLinkText();
						String linkHrefd = d.getLinkHref();
						System.out.println("	编码+名称：" + extracts.get(l-3).getLinkText() + "-" + linkTextd);
						System.out.println("===============================居委会=====================================");
						
						if (extracts.get(l-3).getLinkText().equals(code)) {
							// 修改
							userOperation.update(code,linkTextd);
							count ++;
							break;
						}
					}
				}
			}
			if (r.getGrade() == 3) {
				urlStr = code.substring(0, 2) + "/" + code.substring(2, 4) + "/" + code.substring(0, 6) + ".html";
				System.out.println(urlStr);
				Rule rule = new Rule(url+urlStr,null, null,"towntr", Rule.CLASS, Rule.GET);
				List<LinkTypeData> extracts = ExtractService.extract(rule);
				for (int l = 1; l<= extracts.size() ; l++){
					if (l%2 != 0) {
						System.out.println("===============================居委会=====================================");
						LinkTypeData d = extracts.get(l);
						String linkTextd = d.getLinkText();
						String linkHrefd = d.getLinkHref();
						System.out.println("	编码+名称：" + extracts.get(l-1).getLinkText() + "-" + linkTextd);
						System.out.println("===============================居委会=====================================");
						
						if (extracts.get(l-1).getLinkText().equals(code)) {
							// 修改
							userOperation.update(code,linkTextd);
							count ++;
							break;
						}
					}
				}
			}
			if (r.getGrade() == 2) {
				urlStr = code.substring(0, 2) + "/" + code.substring(0, 4) + ".html";
				System.out.println(urlStr);
				Rule rule = new Rule(url+urlStr,null, null,"countytr", Rule.CLASS, Rule.GET);
				List<LinkTypeData> extracts = ExtractService.extract(rule);
				for (int l = 1; l<= extracts.size() ; l++){
					if (l%2 != 0) {
						System.out.println("===============================县级=====================================");
						LinkTypeData d = extracts.get(l);
						String linkTextd = d.getLinkText();
						String linkHrefd = d.getLinkHref();
						System.out.println("	编码+名称：" + extracts.get(l-1).getLinkText() + "-" + linkTextd);
						System.out.println("===============================县级=====================================");
						
						if (extracts.get(l-1).getLinkText().equals(code)) {
							// 修改
							userOperation.update(code,linkTextd);
							count ++;
							break;
						}
					}
				}
			}
		}
		System.out.println("======"+count);
		session.commit();*/
		String code = "510681114209";
		String urlStr = code.substring(0, 2) + "/" + code.substring(2, 4) + "/" + code.substring(4, 6) + "/" + code.substring(0, 9) + ".html";
		System.out.println(urlStr);
		Rule rule = new Rule(url+urlStr,null, null,"villagetr", Rule.CLASS, Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		for (int l = 1; l<= extracts.size() ; l++){
			if (l%3 == 0 && l != 0) {
				LinkTypeData d = extracts.get(l-1);
				String linkTextd = d.getLinkText();
				String linkHrefd = d.getLinkHref();
				
				if (extracts.get(l-3).getLinkText().equals(code)) {
					System.out.println("	编码+名称：" + extracts.get(l-3).getLinkText() + "-" + linkTextd);
					// 修改
					//userOperation.update(code,linkTextd);
					//count ++;
					break;
				}
			}
		}
		
		
		
		
		
		/*Rule rule = new Rule(url,null, null,"provincetr", Rule.CLASS, Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);*/
	}
	public static void printf(List<LinkTypeData> datas)
	{
		SqlSession session = sqlSessionFactory.openSession();
		try {
		// 省级
		for (int f = 0; f< datas.size(); f++)
		{
			LinkTypeData data = datas.get(f);
			System.out.println("-------------------------------------省级-------------------------------------");
			String linkText = data.getLinkText();
			String linkHref = data.getLinkHref();
			System.out.println("名称：" + linkText + ",链接：" + linkHref);
			// 查找编号
			RegionMapper userOperation=session.getMapper(RegionMapper.class);
			Region f1 = userOperation.selectForKey(linkText.trim());
			
			
			// 市级
			String urla = url + linkHref;
			Rule rule = new Rule(urla,null, null,"citytr", Rule.CLASS, Rule.GET);
			List<LinkTypeData> extracts = ExtractService.extract(rule);
			System.out.println(urla);
			for (int i = 0; i< extracts.size() ; i++){
				if (i%2 != 0) {
					System.out.println("===============================市级=====================================");
					LinkTypeData a = extracts.get(i);
					String linkTexta = a.getLinkText();
					String linkHrefa = a.getLinkHref();
					System.out.println("	编码+名称：" + extracts.get(i-1).getLinkText() + "-" + linkTexta + ",链接：" + linkHrefa);
					
					// 插入市
					Region a1 = new Region();
					String uida = UUID.randomUUID().toString().toUpperCase();
					a1.setId(uida);
					a1.setCode(extracts.get(i-1).getLinkText());
					a1.setGrade(1);
					a1.setName(linkTexta);
					a1.setParentId(f1.getId());
					userOperation.insert(a1);
					
					// 如果是空的话就不遍历下级
					if (linkHrefa == null || "".equals(linkHrefa)) {
						continue ;
					}
					// 县级
					String urlb = url + "/" + linkHrefa;
					Rule ruleb = new Rule(urlb,null, null,"countytr", Rule.CLASS, Rule.GET);
					List<LinkTypeData> extractsb = ExtractService.extract(ruleb);
					System.out.println(urlb);
					for (int j = 0; j< extractsb.size() ; j++){
						if (j%2 != 0) {
							System.out.println("===============================县级=====================================");
							LinkTypeData b = extractsb.get(j);
							String linkTextb = b.getLinkText();
							String linkHrefb = b.getLinkHref();
							System.out.println("	编码+名称：" + extractsb.get(j-1).getLinkText() + "-" + linkTextb + ",链接：" + linkHrefb);
							System.out.println("===============================县级=====================================");
							
							// 插入县
							Region b1 = new Region();
							String uidb = UUID.randomUUID().toString().toUpperCase();
							b1.setId(uidb);
							b1.setCode(extractsb.get(j-1).getLinkText());
							b1.setGrade(2);
							b1.setName(linkTextb);
							b1.setParentId(uida);
							userOperation.insert(b1);
							
							if (linkHrefb == null || "".equals(linkHrefb)) {
								System.out.println("跳出循环");
								continue ;
							}
							
							// 街道办
							String urlc = urlb.substring(0, urlb.lastIndexOf('/')) + "/" + linkHrefb;
							Rule rulec = new Rule(urlc,null, null,"towntr", Rule.CLASS, Rule.GET);
							List<LinkTypeData> extractsc = ExtractService.extract(rulec);
							System.out.println(urlc);
							for (int k = 0; k< extractsc.size() ; k++){
								if (k%2 != 0) {
									System.out.println("===============================街道办=====================================");
									LinkTypeData c = extractsc.get(k);
									String linkTextc = c.getLinkText();
									String linkHrefc = c.getLinkHref();
									System.out.println("	编码+名称：" + extractsc.get(k-1).getLinkText() + "-" + linkTextc + ",链接：" + linkHrefc);
									System.out.println("===============================街道办=====================================");
									
									// 插入街道办
									Region c1 = new Region();
									String uidc = UUID.randomUUID().toString().toUpperCase();
									c1.setId(uidc);
									c1.setCode(extractsc.get(k-1).getLinkText());
									c1.setGrade(3);
									c1.setName(linkTextc);
									c1.setParentId(uidb);
									userOperation.insert(c1);
									
									if (linkHrefc == null || "".equals(linkHrefc)) {
										System.out.println("跳出循环");
										continue ;
									}
									// 居委会
									String urld = urlc.substring(0, urlc.lastIndexOf('/')) + "/" + linkHrefc;
									Rule ruled = new Rule(urld,null, null,"villagetr", Rule.CLASS, Rule.GET);
									List<LinkTypeData> extractsd = ExtractService.extract(ruled);
									System.out.println(urld);
									for (int l = 1; l<= extractsd.size() ; l++){
										if (l%3 == 0 && l != 0) {
											System.out.println("===============================居委会=====================================");
											LinkTypeData d = extractsd.get(l-1);
											String linkTextd = d.getLinkText();
											String linkHrefd = d.getLinkHref();
											System.out.println("	编码+名称：" + extractsd.get(l-3).getLinkText() + "-" + linkTextd);
											System.out.println("===============================居委会=====================================");
											
											// 插入居委会
											Region d1 = new Region();
											String uidd = UUID.randomUUID().toString().toUpperCase();
											d1.setId(uidd);
											d1.setCode(extractsd.get(l-3).getLinkText());
											d1.setGrade(4);
											d1.setName(linkTextd);
											d1.setParentId(uidc);
											userOperation.insert(d1);
										}
									}
								}
							}
							
							
						}
					}
					
					System.out.println("===============================市级=====================================");
				}
			}
			
			System.out.println("-------------------------------------省级-------------------------------------");
		}
		session.commit();
		} finally {
    	    session.close();
    	}

	}
	public static void printf1(List<LinkTypeData> datas)
	{
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// 省级
			for (int f = 0; f< datas.size(); f++)
			{
				LinkTypeData data = datas.get(f);
				System.out.println("-------------------------------------省级-------------------------------------");
				String linkText = data.getLinkText();
				String linkHref = data.getLinkHref();
				System.out.println("名称：" + linkText + ",链接：" + linkHref);
				// 查找编号
				RegionMapper userOperation=session.getMapper(RegionMapper.class);
				Region f1 = userOperation.selectForKey(linkText.trim());
				
				
				// 市级
				String urla = url + linkHref;
				Rule rule = new Rule(urla,null, null,"citytr", Rule.CLASS, Rule.GET);
				List<LinkTypeData> extracts = ExtractService.extract(rule);
				System.out.println(urla);
				for (int i = 0; i< extracts.size() ; i++){
					if (i%2 != 0) {
						System.out.println("===============================市级=====================================");
						LinkTypeData a = extracts.get(i);
						String linkTexta = a.getLinkText();
						String linkHrefa = a.getLinkHref();
						System.out.println("	编码+名称：" + extracts.get(i-1).getLinkText() + "-" + linkTexta + ",链接：" + linkHrefa);
						
						// 插入市
						Region a1 = new Region();
						String uida = UUID.randomUUID().toString().toUpperCase();
						a1.setId(uida);
						a1.setCode(extracts.get(i-1).getLinkText());
						a1.setGrade(1);
						a1.setName(linkTexta);
						a1.setParentId(f1.getId());
						userOperation.insert(a1);
						
						// 如果是空的话就不遍历下级
						if (linkHrefa == null || "".equals(linkHrefa)) {
							continue ;
						}
						// 县级
						String urlb = url + "/" + linkHrefa;
						Rule ruleb = new Rule(urlb,null, null,"countytr", Rule.CLASS, Rule.GET);
						List<LinkTypeData> extractsb = ExtractService.extract(ruleb);
						System.out.println(urlb);
						for (int j = 0; j< extractsb.size() ; j++){
							if (j%2 != 0) {
								System.out.println("===============================县级=====================================");
								LinkTypeData b = extractsb.get(j);
								String linkTextb = b.getLinkText();
								String linkHrefb = b.getLinkHref();
								System.out.println("	编码+名称：" + extractsb.get(j-1).getLinkText() + "-" + linkTextb + ",链接：" + linkHrefb);
								System.out.println("===============================县级=====================================");
								
								// 插入县
								Region b1 = new Region();
								String uidb = UUID.randomUUID().toString().toUpperCase();
								b1.setId(uidb);
								b1.setCode(extractsb.get(j-1).getLinkText());
								b1.setGrade(2);
								b1.setName(linkTextb);
								b1.setParentId(uida);
								userOperation.insert(b1);
								
								if (linkHrefb == null || "".equals(linkHrefb)) {
									System.out.println("跳出循环");
									continue ;
								}
								
								// 街道办
								String urlc = urlb.substring(0, urlb.lastIndexOf('/')) + "/" + linkHrefb;
								Rule rulec = new Rule(urlc,null, null,"towntr", Rule.CLASS, Rule.GET);
								List<LinkTypeData> extractsc = ExtractService.extract(rulec);
								System.out.println(urlc);
								for (int k = 0; k< extractsc.size() ; k++){
									if (k%2 != 0) {
										System.out.println("===============================街道办=====================================");
										LinkTypeData c = extractsc.get(k);
										String linkTextc = c.getLinkText();
										String linkHrefc = c.getLinkHref();
										System.out.println("	编码+名称：" + extractsc.get(k-1).getLinkText() + "-" + linkTextc + ",链接：" + linkHrefc);
										System.out.println("===============================街道办=====================================");
										
										// 插入街道办
										Region c1 = new Region();
										String uidc = UUID.randomUUID().toString().toUpperCase();
										c1.setId(uidc);
										c1.setCode(extractsc.get(k-1).getLinkText());
										c1.setGrade(3);
										c1.setName(linkTextc);
										c1.setParentId(uidb);
										userOperation.insert(c1);
										
										if (linkHrefc == null || "".equals(linkHrefc)) {
											System.out.println("跳出循环");
											continue ;
										}
										// 居委会
										String urld = urlc.substring(0, urlc.lastIndexOf('/')) + "/" + linkHrefc;
										Rule ruled = new Rule(urld,null, null,"villagetr", Rule.CLASS, Rule.GET);
										List<LinkTypeData> extractsd = ExtractService.extract(ruled);
										System.out.println(urld);
										for (int l = 1; l<= extractsd.size() ; l++){
											if (l%3 == 0 && l != 0) {
												System.out.println("===============================居委会=====================================");
												LinkTypeData d = extractsd.get(l-1);
												String linkTextd = d.getLinkText();
												String linkHrefd = d.getLinkHref();
												System.out.println("	编码+名称：" + extractsd.get(l-3).getLinkText() + "-" + linkTextd);
												System.out.println("===============================居委会=====================================");
												
												// 插入居委会
												Region d1 = new Region();
												String uidd = UUID.randomUUID().toString().toUpperCase();
												d1.setId(uidd);
												d1.setCode(extractsd.get(l-3).getLinkText());
												d1.setGrade(4);
												d1.setName(linkTextd);
												d1.setParentId(uidc);
												userOperation.insert(d1);
											}
										}
									}
								}
								
								
							}
						}
						
						System.out.println("===============================市级=====================================");
					}
				}
				
				System.out.println("-------------------------------------省级-------------------------------------");
			}
			session.commit();
		} finally {
			session.close();
		}
		
	}

}
