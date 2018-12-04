package com.wondertek.baiying.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

@SuppressWarnings("static-access")
public class ListTest {

	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> remMap = new HashMap<String, Object>();
		
		for (int i = 0; i < 10; i++) {
			resultMap.put(""+i, "值"+i);
		}
		remMap.put("xinde", resultMap);
		//resultMap.put("1", "值11");
		System.out.println(resultMap);
		
		for (int i = 0; i < 10; i++) {
			list.add("添加数据" + i);
		}
		list.add(remMap);
		resultMap.put("1", "值11");
		//JSONObject jsonObject = new JSONObject(resultMap);
		JSONObject jsonObject = new JSONObject();
		System.out.println(resultMap.toString());
		System.out.println(jsonObject);
		System.out.println("fswg".hashCode());
		System.out.println(list.size());
		System.out.println(list.hashCode());
		Iterator<Object> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(Locale.getDefault());
	}

	@Test
	public void test01() {
		Object[] objects = {"1", "1"};
		List<Object[]> objectList = new ArrayList<>();
		objectList.add(objects);
		System.out.println(objectList.get(0)[0].equals("1"));
	}
}
