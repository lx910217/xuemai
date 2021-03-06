package com.gx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gx.po.Detail;
import com.gx.po.EducationEf;
import com.gx.po.ReportResult;
import com.gx.po.Result;
import com.gx.service.DetailService;
import com.gx.service.EducationYinFuService;
import com.gx.service.ReportResultService;
import com.gx.service.ResultService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/YinFuAction")
public class YinFuAction {

	@Autowired
	public EducationYinFuService educationYinFuService;
	@Autowired
	public DetailService detailService;
	@Autowired
	public ResultService resultService;
	@Autowired
	public ReportResultService reportResultService;
	

	@RequestMapping("/addYinFu")
	@ResponseBody
	public void addYinFu(HttpServletRequest httprequest, HttpServletResponse httpresponse)
			throws ServletException, IOException, Exception {

		String stu_name = httprequest.getParameter("stu_name");
		String stu_sex = httprequest.getParameter("stu_sex");
		String stu_grade = httprequest.getParameter("stu_grade");
		String parent_name = httprequest.getParameter("parent_name");
		String parent_phone = httprequest.getParameter("parent_phone");
		String list_id = httprequest.getParameter("list_id");
		String lead_id = httprequest.getParameter("lead_id");
		String call_id = httprequest.getParameter("call_id");
		String city = httprequest.getParameter("city");
		String status = httprequest.getParameter("status");
		String created_time = httprequest.getParameter("created_time");
		String update_time = httprequest.getParameter("update_time");
		String q_Other = httprequest.getParameter("q_Other");
		String q_ChildAge = httprequest.getParameter("q_ChildAge");
		String q_RegistrationActivities = httprequest.getParameter("q_RegistrationActivities");
		String interest_degree = httprequest.getParameter("interest_degree");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		EducationEf emxc = new EducationEf(null, stu_name, stu_sex, stu_grade, parent_name, parent_phone, list_id,
				lead_id, call_id, city, status, created_time, update_time, q_Other, q_ChildAge,
				q_RegistrationActivities, interest_degree, null, null,null, interest_degree);
		EducationEf newemxc = educationYinFuService.findforEEF(emxc);

		if (null == newemxc) {
			educationYinFuService.insert(emxc);

			map.put("code", 1);
			map.put("data", emxc);

		} else {
			map.put("code", 0);
			map.put("tips", "该手机号已领取免费课程，如有疑问请联系客服！");

		}

		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8");
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin", "*");
		httpresponse.getWriter().print(jsonObject.toString());

	}

	@RequestMapping("/countClick")
	@ResponseBody
	public void countClick(HttpServletRequest httprequest, HttpServletResponse httpresponse)
			throws ServletException, IOException, Exception {

		int inputInt = Integer.valueOf(httprequest.getParameter("inputInt"));

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		inputInt += 1;
		map.put("count", inputInt);

		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8");
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin", "*");
		httpresponse.getWriter().print(jsonObject.toString());

	}
	/* 根据查找信息进行分页 */

	@RequestMapping("/SelectYinFu")
	@ResponseBody
	public void SelectYinFu(HttpServletRequest httprequest, HttpServletResponse httpresponse)
			throws ServletException, IOException, Exception {

		httprequest.setCharacterEncoding("UTF-8");
		httpresponse.setContentType("text/html;charset=UTF-8");
		String SQL = "";
		int currentPage = 0;
		if (httprequest.getParameter("currentPage") != null) {
			currentPage = Integer.valueOf(httprequest.getParameter("currentPage"));
		}
		int pageSize = 0;
		if (httprequest.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(httprequest.getParameter("pageSize"));
		}
		currentPage = currentPage - 1;
		currentPage = currentPage * pageSize;
		List<EducationEf> powerVos = educationYinFuService.SelectYinFu(SQL, currentPage, pageSize);
		JSONObject json = new JSONObject();
		json.put("data", powerVos);
		PrintWriter out = httpresponse.getWriter();
		out.write(json.toString());

	}

	
	@RequestMapping("/findALLEF")
	@ResponseBody
	public void findALLEF(HttpServletRequest httprequest, HttpServletResponse httpresponse)
			throws ServletException, IOException, Exception {
		
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

		String listId = httprequest.getParameter("listID");
		String status = httprequest.getParameter("status");   //传值为 0,1
		String customerType = httprequest.getParameter("customeType");
		String talkTime = httprequest.getParameter("talkTime");
		String campaign = httprequest.getParameter("campaign");
		
		String SQL = " where ";
		if (campaign != null) {
			SQL += " campaign_id ='" + campaign+"'";
		}
		if (null != listId && !("").equals(listId)) {
			SQL += " and list_id='" + listId + "'";
		}
		if (null != customerType && !("").equals(customerType)) {
			SQL += " and interest_degree='" + customerType + "'";
		}
		if (null != status && !("").equals(status)) {
			SQL += " and status='" + status + "'";
		}
		if (null != talkTime && !("").equals(talkTime)) {
			if (60 >= Integer.valueOf(talkTime)) {
				SQL += " and talk_time<='" + 60 + "'";
			} else if (60 >= Integer.valueOf(talkTime) & 120 >= Integer.valueOf(talkTime)) {
				SQL += " and" + 120 + " <=talk_time<='" + 60 + "'";
			} else if (120 < Integer.valueOf(talkTime)) {
				SQL += " and talk_time>='" + 120 + "'";
			}

		}
		SQL+= " order by update_time desc ";
		List<EducationEf> efList = educationYinFuService.findBySQLforEducationEf(SQL);
		if (!efList.isEmpty()) {
			map.put("code", 1);
			map.put("data", efList);
		} else {
			map.put("code", 0);
			map.put("tips", "查询结果不存在");
		}
		
		
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8");
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin", "*");
		httpresponse.getWriter().print(jsonObject.toString());
	
	}
	
	
	@RequestMapping("/aiTextButton")
	@ResponseBody
	public void aiTextButton(HttpServletRequest httprequest, HttpServletResponse httpresponse)
			throws ServletException, IOException, Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

		int id = Integer.parseInt(httprequest.getParameter("id"));
//		int id = Integer.parseInt("6");
		
		EducationEf newEF = educationYinFuService.selectByPrimaryKeyforEf(id);
		if(null!=newEF) {
			
//			String sql = "where list_id = '"+newEF.getList_id()+"' and phone_number = '"+newEF.getParent_phone()+"' ";
			Detail dd = new Detail(null, null, newEF.getList_id(), null, null, null, newEF.getParent_phone(), null, null, null, null, null, null, null);
			Detail newdd = detailService.findforDetail(dd);
			if(null!=newdd) {
			String resultID = null;
			ReportResult rr = new ReportResult();
			String sqlForAI = "where fromUser = '" + newdd.getPhone_number() + "' and robotName='"+newdd.getList_id()+"' order  by fieldName";

			// 查询当天，电话下的所有对话的 result文本数据
			List<Result> resultListnew = resultService.findBySQL(sqlForAI);
			if (resultListnew.size() > 0) {
				for (int x = 0; x < resultListnew.size(); x++) {
//					String Word = (resultListnew.get(x)).getQuestion();
					// 如果没有数据
					if (resultID == null) {
						// 通过下标获取id
						resultID = (resultListnew.get(x)).getId();
					} else {
						// id不为空，对数据进行划分，并获取id
						resultID += ";" + (resultListnew.get(x)).getId();
					}
					
				}
			
				rr.setListID(newdd.getList_id());
				rr.setStatus(newdd.getStatus());
				rr.setPhoneNumber(newdd.getPhone_number());
				rr.setStartTime(newdd.getStart_date());
				rr.setCallTime(newdd.getCall_time());
				rr.setRingTime(newdd.getRing_time());
				rr.setTalkTime(newdd.getTalk_time());
				rr.setRecordDownload(newdd.getDownload());
				rr.setAiText(resultID);
				
				ReportResult result=reportResultService.findByRr(rr);
				if (null==result) {
					reportResultService.insert(rr);
				}
				
			
			}
			
			String sql = " where listID='" + newEF.getList_id() + "' and phoneNumber='" + newEF.getParent_phone() + "'";
			List<ReportResult> newrrList = reportResultService.findBySQL(sql);
			if (newrrList.size() > 0) {
				String aiT = newrrList.get(0).getAiText();
				Map<String, String> mapForQA = new HashMap<String, String>();
				if (null != aiT) {
					String[] aiList = aiT.split(";");

					for (int x = 0; x < aiList.length; x++) {
						Result result = resultService.selectByPrimaryKey(aiList[x]);
						mapForQA.put("Queation" + x, result.getAnswer());
						mapForQA.put("Answer" + x, result.getQuestion());
						mapForQA.put("Time" + x, result.getCreatedTime());
					}
					// rr.setMapforQA(mapForQA);

					// List<Result> rList = resultService.
				}
				map.put("code", 1);
				map.put("mapForQA", mapForQA);
			}
			}else {
				map.put("code", 0);
				map.put("tips", "no service");
			}
			
			
		}else {
			
			map.put("code", 0);
			map.put("tips", "no service");
			
		}	
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8");
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin", "*");
		httpresponse.getWriter().print(jsonObject.toString());
	
	}
	
	
	@RequestMapping("/recordButton")
	@ResponseBody
	public void recordButton(HttpServletRequest httprequest, HttpServletResponse httpresponse)
			throws ServletException, IOException, Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

		int id = Integer.parseInt(httprequest.getParameter("id"));
		
		EducationEf newEF = educationYinFuService.selectByPrimaryKeyforEf(id);
		if(null!=newEF) {
			
//			String sql = "where list_id = '"+newEF.getList_id()+"' and phone_number = '"+newEF.getParent_phone()+"' ";
			Detail dd = new Detail(null, null, newEF.getList_id(), null, null, null, newEF.getParent_phone(), null, null, null, null, null, null, null);
			Detail newdd = detailService.findforDetail(dd);
			if(null!=newdd) {
				
				if(!("").equals(newdd.getDownload())) {
					map.put("code", 1);
					map.put("download",newdd.getDownload());
				}else {
					map.put("code", 0);
					map.put("tips", "no service");
				}
			}else {
				map.put("code", 0);
				map.put("tips", "no service");
			}
			
			
			
		}else {
			
			map.put("code", 0);
			map.put("tips", "no service");
			
		}	
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8");
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin", "*");
		httpresponse.getWriter().print(jsonObject.toString());
		
	}
	
}
