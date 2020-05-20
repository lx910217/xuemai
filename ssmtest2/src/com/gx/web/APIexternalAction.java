package com.gx.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gx.po.AiUser;
import com.gx.po.CityCode;
import com.gx.po.PhoneNUM;
import com.gx.po.RobotContact;
import com.gx.po.RobotQA;
import com.gx.po.Role;
import com.gx.po.TokenCode;
import com.gx.service.AiUserService;
import com.gx.service.CityCodeService;
import com.gx.service.PhoneNUMService;
import com.gx.service.RobotContactService;
import com.gx.service.RobotQAService;
import com.gx.service.RoleService;
import com.gx.service.TokenCodeService;
import com.gx.tokenProcessor.util.TokenProcessor;
import com.gx.web.util.KeyWordUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import javafx.scene.input.DataFormat;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

@Component
@Controller
@RequestMapping("/APIexternalAction")
public class APIexternalAction {
	
	@Autowired
	public PhoneNUMService phoneNUMService;
	@Autowired
    public TokenCodeService tokenCodeService;
	@Autowired
	public RobotQAService robotQAService;
	@Autowired
	public RobotContactService robotContactService;
	@Autowired
	public CityCodeService cityCodeService;
	
//	public TokenProcessor tp = new TokenProcessor();

	
	@RequestMapping("/putPhoneNUMbyListID")
	@ResponseBody 
	public void  putPhoneNUMbyListID(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		TokenCode tc = new TokenCode();
		
		//通过接受 token 和 listID，验证获取phoneNUM，中的数据，并且更改status 状态
		String listID = httprequest.getParameter("listID");
//		String listID = "00001";
		//用于显示查询几条数据，并且返回
		String count = httprequest.getParameter("count");
//		String count = "2";
		String tokencode = httprequest.getParameter("tokencode");
		tc.setTokenCode(tokencode);
		tc = tokenCodeService.findByTokenCode(tc);
		if(tc==null) {
			map.put("code", 1001);
			map.put("tips", "身份验证牌错误");
		}else {
			
			
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	        Date date = new Date(currentTime);
	        String sysDATE = formatter.format(date);
	        if(sysDATE.equals(tc.getSystemTime().split(" ")[0])) {
			
				String sql = "where listID = '"+listID+"' and status = 0 limit 0,"+count;
				
				List<PhoneNUM> pnList = phoneNUMService.findBySQLforPhoneNUM(sql);
				if(!pnList.isEmpty()) {
				
					List<PhoneNUM> newPN = new ArrayList<PhoneNUM>();
					for(PhoneNUM pn :pnList ) {
						pn.setStatus("1");
						phoneNUMService.updateByPrimaryKeyforPhoneNUM(pn);
						pn.setStatus(null);
						newPN.add(pn);
					}
					
					
					map.put("code", 1);
					map.put("data", newPN);
					map.put("count", newPN.size());
				
		        }else {
		        	map.put("code", 1002);
					map.put("tips", "身份验证牌过期");
		        }
				
				
			}else {
				map.put("code", 3002);
				map.put("tips", "找不到对应的listID");
			}
		}
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
	
//	@RequestMapping("/setTokenCode")
//	@ResponseBody 
	public void  setTokenCode()throws ServletException, IOException, Exception{  
		
		TokenCode tc = new TokenCode();
        String value = System.currentTimeMillis()+new Random().nextInt()+"";
//    	String value ="1531018665970";
//        System.out.println(value); 
        tc.setRandomNUM(value);
        
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(currentTime);
//        System.out.println(formatter.format(date));
        tc.setSystemTime(formatter.format(date));

        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            BASE64Encoder be = new BASE64Encoder();
            be.encode(b);
//            System.out.println(be.encode(b)); 
            tc.setTokenCode(be.encode(b));
            tokenCodeService.insert(tc);
//            return be.encode(b);//制定一个编码
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
		
	}
	
	
//	private final String[] key_word_list = {"试听课","平台","靠谱","师资","补习","没时间","辅导"};
	
	@RequestMapping("/textToWAV")
	@ResponseBody 
	public void  textToWAV(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		long t = System.currentTimeMillis();//获得当前时间的毫秒数
        Random rd = new Random(t);
        String filename = String.valueOf(rd.nextInt());
		
		String question = httprequest.getParameter("text");
		MSTTSSpeech speech=new MSTTSSpeech();
//		String text="艾斯若群无若无群若的说辞必须先吃";
		speech.setFormatType(6);
		// speech.setRate(-1);
//		speech.saveToWav(question,"D:\\WorkSpace\\ssm-2\\ssmtest2\\WebRoot\\upload\\0000.wav");
//		speech.saveToWav(question,"D:\\WorkSpace\\ssm-2\\ssmtest2\\WebRoot\\upload\\"+filename+".wav");
		speech.saveToWav(question,"D:\\xamp\\htdocs\\voice\\"+filename+".wav");
		
//		speech.saveToWav(question,"\\var\\www\\html\\ai_backend\\public\\"+filename+".wav");
		
		map.put("download", filename+".wav");
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		/*ActiveXComponent ax = new ActiveXComponent("Sapi.SpFileStream");
		Dispatch spVoice=null;// 声音对象
		Dispatch spFileStream=null;// 音频文件输出流对象，在读取或保存音频文件时使用
		Dispatch spAudioFormat=null;// 音频格式对象
		Dispatch spMMAudioOut=null;
		int formatType=22;
		int rate=0;// 频率：-10到10
		int voice=100;
		
		spFileStream=ax.getObject();
		// 创建音频流格式对象
				if(spAudioFormat==null)
				{
					ax=new ActiveXComponent("Sapi.SpAudioFormat");
					spAudioFormat=ax.getObject();
				}
				// 设置音频流格式类型
				Dispatch.put(spAudioFormat,"Type",new Variant(formatType));
				// 设置文件输出流的格式
				Dispatch.putRef(spFileStream,"Format",spAudioFormat);
//				try {
					// 调用输出文件流对象的打开方法，创建一个.wav文件
					Dispatch.call(spFileStream,"Open",new Variant("D:\\WorkSpace\\ssm-2\\ssmtest2\\WebRoot\\upload\\0000.wav"),new Variant(3),new Variant(true));
					
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.getMessage();
//				}
				Dispatch.putRef(spVoice,"AudioOutputStream",spFileStream);
				// 设置声音对象的音频输出流为输出文件流对象
				// 调整音量和读的速度
				Dispatch.put(spVoice,"Volume",new Variant(100));// 设置音量
				Dispatch.put(spVoice,"Rate",new Variant(rate));// 设置速率
				// 开始朗读
				Dispatch.call(spVoice,"Speak",new Variant("艾斯若群无若无群若的说辞必须先吃"));
				// 关闭输出文件流对象，释放资源
				Dispatch.call(spFileStream,"Close");
				Dispatch.putRef(spVoice,"AudioOutputStream",null);*/
		
		
	}
	
	
	
	
	@RequestMapping("/robotAPI")
	@ResponseBody 
	public void  robotAPI(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = formatter.format(new Date());
		
		//传过来问题 的 联系方式  格式为  ：电话+姓名+问题
		String question = httprequest.getParameter("question");
//		String question = "刘翔+13222100055+asdasd";
//		String question = "刘翔+13222100055";
//		String question = "你们这里有小班课吗";
//		String question = "我想知道今天齐齐哈尔天气";
//		String question = "我想知道去新村路170弄的地址";
		
		
		
		//建立数据库问答情况
		Set<String> wordSet = new HashSet<String>();
		wordSet.add("服务");
		wordSet.add("师资");
		wordSet.add("试听课");
		wordSet.add("辅导");
		wordSet.add("预算");
		wordSet.add("住");
		wordSet.add("双休日");
		wordSet.add("升职");
		wordSet.add("少儿");
		wordSet.add("成人");
		wordSet.add("公共");
		wordSet.add("押");
		wordSet.add("看图说话");
		wordSet.add("逻");
		wordSet.add("考证");
		wordSet.add("旅游");
		wordSet.add("商务");
		wordSet.add("偏科");
		wordSet.add("补习");
		wordSet.add("学习");
		wordSet.add("奥数");
		wordSet.add("帮助");
		wordSet.add("漏");
		wordSet.add("乐高");
		wordSet.add("测评");
		wordSet.add("提高");
		wordSet.add("小班");
		wordSet.add("校");
		wordSet.add("效");
		wordSet.add("品牌");
		wordSet.add("教育");
		wordSet.add("英语");
		wordSet.add("数学");
		
		
		
		KeyWordUtil keyWordUtil = new KeyWordUtil(wordSet);
		if(!("").equals(question)&&null!=question) {
			//判断客户打出的是否是 联系方式
			if(question.contains("+")) {
				String[] answer = question.split("\\+");
				String name = null;
				String phone = null;
				String que = null;
				if(answer.length==3) {
					name = answer[0];
					phone = answer[1];
					que = answer[2];
				}
				if(answer.length==2) {
					name = answer[0];
					phone = answer[1];
				}
				RobotContact rc = new RobotContact(null, phone, name, que);
				robotContactService.insert(rc);
				map.put("code", 1);
				map.put("answer", "好的，小脉已经为您进行登记了。稍后我们的客服人员会跟你联系的，请注意电话畅通哟~");
				map.put("datetime",formatter.format(new Date()));
				
			}else {
				//如果不是联系方式。那就判断是不是在 我们的 数据词库里面能有找到对应的回答
				Set<String> key = keyWordUtil.getWords(question);
				if(key.size()>0) {
					Iterator<String> it = key.iterator();
					String word="";
					while(it.hasNext()) {
						word = it.next();
					}
					String sql = " where key_word ='"+word+"'";
					List<RobotQA> qaList = robotQAService.findBySQLforQA(sql);
					if(qaList.size()>0) {
						RobotQA qa = qaList.get(0);
						map.put("code", 1);
						map.put("answer", qa.getAnswer());
						map.put("datetime",formatter.format(new Date()));
					}
					
					
				
				}else if(question.contains("天气")||question.contains("温度")) {
					String json = weatherAPI(question);
					if(null!=json) {
						Map map1 = JSON.parseObject(json);
						JSONArray ja =  (JSONArray) map1.get("lives");
						com.alibaba.fastjson.JSONObject jo = ja.getJSONObject(0);
						
						String dd = (String)jo.get("reporttime");
						String ymd = dd.split(" ")[0];
						
						map.put("code", 1);
						map.put("answer", "您好，据小脉所知呢, 今天的"+jo.get("city")+" "+ymd+" "+jo.get("weather")+" "+jo.get("temperature")+"摄氏度 "
								+jo.get("winddirection")+"风 "+jo.get("windpower")+"级");
						map.put("datetime",formatter.format(new Date()));
						
						
						
					}else {
						map.put("code", 0);
						map.put("answer", "不好意思哟，客户爸爸提出的问题，小脉不知道该怎么回答，您可以将您的问题整理，编辑成  ：   姓名+电话+问题        这样的格式告诉小脉，小脉会以最快的速度找到相关人员跟您联系的哟~");
						map.put("datetime",formatter.format(new Date()));
					}
				
				
				
				}else if(question.contains("去")||question.contains("到")||question.contains("在哪里")||question.contains("在什么地方")) {
					String address = null;
					if(question.contains("去")) {
						String[] qs = question.split("去");
						address  = qs[1];
					}else if(question.contains("到")){
						String[] qs = question.split("到");
						address  = qs[1];
					}else {
						address= question;
					}
					String json = wayAPI(address);					
					if(null!=json) {
						Map map1 = JSON.parseObject(json);
						JSONArray ja =  (JSONArray) map1.get("pois");
						com.alibaba.fastjson.JSONObject jo = ja.getJSONObject(0);
						
						map.put("code", 1);
						map.put("answer", "你是不是想去 "+jo.get("name")+" 这个地方呢？地址是 "+jo.get("address")+" 这个哦！ ");
						map.put("datetime",formatter.format(new Date()));
						
					}else {
						map.put("code", 0);
						map.put("answer", "不好意思哟，客户爸爸提出的问题，小脉不知道该怎么回答，您可以将您的问题整理，编辑成  ：   姓名+电话+问题        这样的格式告诉小脉，小脉会以最快的速度找到相关人员跟您联系的哟~");
						map.put("datetime",formatter.format(new Date()));
					}
					
					
					
					
				}else {
					//如果找不到就抛给 外部接口去查询下一句应该接什么
					String encodeStr = URLEncoder.encode(question, "utf-8");
//					System.out.println(encodeStr);
					robotQAService.insert(new RobotQA(null, question, null, "1"));

					OkHttpClient client = new OkHttpClient();

					Request request = new Request.Builder()
					  .url("http://api.qingyunke.com/api.php?key=free&appid=0&"
							  
//							.url("http://39.108.172.0/api.php?key=free&appid=0&"
					  		+ "msg="+encodeStr)
					  .addHeader("cache-control", "no-cache")
					  .addHeader("Postman-Token", "0e5ccc45-c79c-486e-a84e-20ff58c1931f")
					  .build();
					

					Response response = client.newCall(request).execute();
					String json = response.body().string();
					//找到了得到结果
					Map map1 = JSON.parseObject(json);
//					Map<String,Object> map1_1 = (Map<String,Object>)map1.get("Body");
					//判断这个回答的是有返回的时候
					Integer result = (Integer)map1.get("result");
					if(result==0) {
						//获得返回的 回答
						String content = (String)map1.get("content");
						map.put("code", 1);
						map.put("answer", content);
						map.put("datetime",formatter.format(new Date()));
					}else {
						//当没有获得对应的 回答的时候
						map.put("code", 0);
						map.put("answer", "不好意思哟，客户爸爸提出的问题，小脉不知道该怎么回答，您可以将您的问题整理，编辑成  ：   姓名+电话+问题        这样的格式告诉小脉，小脉会以最快的速度找到相关人员跟您联系的哟~");
						map.put("datetime",formatter.format(new Date()));
					}
				}
				
			}
			
			
			
		
		}else {
			
			map.put("code", 0);
			map.put("tips", "提出问题为空");
			map.put("datetime",formatter.format(new Date()));
		}
		
		
		
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	

//	@RequestMapping("/weatherAPI")
//	@ResponseBody 
	public String  weatherAPI(String cityname)throws ServletException, IOException, Exception{  
	
//		String citycode = "上海";
		
		
		CityCode cc =  cityCodeService.selectLocateCityName(cityname);
		
		
		if(null!=cc) {
		
			OkHttpClient client = new OkHttpClient();
	
			Request request = new Request.Builder()
					.url("http://106.11.249.75/v3/weather/weatherInfo?key=df7fd73fb5962cc651d85d08cd44b411"	
//			  .url("https://restapi.amap.com/v3/weather/weatherInfo?key=df7fd73fb5962cc651d85d08cd44b411"
			  		+ "&city="+cc.getAd_code())
	//		  .addHeader("Content-Type", "application/json")
			  .addHeader("cache-control", "no-cache")
			  .addHeader("Postman-Token", "a8be98d2-dc37-448e-840e-892ad56d3c38")
			  .build();
	
			Response response = client.newCall(request).execute();
			String json = response.body().string();
			
			
	//	System.out.println(json);
			return json;
		}
		return null;
	
	}
	
	
//	@RequestMapping("/wayAPI")
//	@ResponseBody 
	public String  wayAPI(String address)throws ServletException, IOException, Exception{  
	
//		String address = "虹口龙之梦";
		
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url("http://106.11.249.75/v3/place/text?key=df7fd73fb5962cc651d85d08cd44b411"		
//		  .url("https://restapi.amap.com/v3/place/text?key=df7fd73fb5962cc651d85d08cd44b411"
		  		+ "&keywords="+address)
//		  .addHeader("Content-Type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "a8be98d2-dc37-448e-840e-892ad56d3c38")
		  .build();

		Response response = client.newCall(request).execute();
		String json = response.body().string();
		return json;
		
//	System.out.println(json);
		
	}
	
	
}
