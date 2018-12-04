package com.wondertek.baiying.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Entity2JsonUtil {
	public static String entity2JsonConvert(List<Object> list) {
		ObjectMapper mapper = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
		String sendMessage = "";
		try {
			List<ImportData> datas = new ArrayList<ImportData>();
			ImportData importData = new ImportData();
			for (Object obj : list) {
				importData.setDataMap(objectTransfer2Map(obj));
				importData.setOptType(ImportData.PARAM_SOLR_IMPORT);
				datas.add(importData);
			}
			ImportParam ip = new ImportParam();
			ip.setCollection("cmam_video");
			ip.setDatas(datas);
			
			sendMessage = mapper.writeValueAsString(ip);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return sendMessage;
	}
	
	public static Map<String, Object> objectTransfer2Map(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj != null) {
			Class<?> cls = obj.getClass();
			Field[] filedArrays = cls.getDeclaredFields();
			Method m = null;
			for (Field f : filedArrays) {
				if (!f.getName().equals("serialVersionUID")) {
					try {
						PropertyDescriptor pd = new PropertyDescriptor(f.getName(), cls);
						m = pd.getReadMethod();
						map.put(f.getName(), m.invoke(obj));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (map.size() > 0) {
				map.put("searchType", cls.getSimpleName());
				map.put("searchId", getPrimaryKey(map));
			}
		}
		return map;
	}

	public static String getPrimaryKey(Map<String, Object> map) {
		String result = "";
		String searchType = (String) map.get("searchType");
		if (searchType != null && !"".equals(searchType)) {
			result = searchType + "_" + map.get("id");
		}
		return result;
	}
}

class ImportParam {
	private List<ImportData> datas; 
	private String collection;	//对应索引库,可以是多个索引库，但是必须用","分割
	
	public List<ImportData> getDatas() {
		return datas;
	}
	public void setDatas(List<ImportData> datas) {
		this.datas = datas;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		return "ImportParam [datas=" + datas + ", collection=" + collection
				+ "]";
	}
}

class ImportData {
	private Map<String,Object> dataMap;	//添加时使用 
	private String query;			//删除时使用
	private int optType;		// 0代表按照查询删除，1代表新增，2代表ID删除
	public static final int PARAM_SOLR_DELETE_QUERY = 0;//按照查询删除
	public static final int PARAM_SOLR_IMPORT = 1;//导入
	public static final int PARAM_SOLR_DELETE_BYID = 2;//按照ID删除，直接只传ID
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getOptType() {
		return optType;
	}
	public void setOptType(int optType) {
		this.optType = optType;
	}
//	@Override
//	public String toString() {
//		return "ImportData [optType=" + optType + "]";
//	}
	@Override
	public String toString() {
		return "ImportData [dataMap=" + dataMap + ", query=" + query + ", optType=" + optType + "]";
	}
}
