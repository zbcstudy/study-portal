package com.wondertek.baiying.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StringTest {

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		StringBuffer s1 = new StringBuffer("zhaobicheng");
		String s2 = "zhebcdabcdnshuai都是发飞";
		int i3 = 1234;
		s1.append(" ");
		s1.append(s2);
		s1.append(" " + i3);
		String format = String.format("update studio_room set room_state = 2 where 1 = 1 and id  in %s %s", s2,i3);
		System.out.println(format);
		System.out.println(s1);
		System.out.println("--------------------");
		int l1 = 456;
		System.out.println(l1/2);
		System.out.println(s2.toUpperCase(Locale.getDefault()));
		System.out.println('a'+1);
		System.out.println(s2.codePointAt(0));
		System.out.println(s2.codePointCount(0,4));

		System.out.println(Long.MAX_VALUE);

		//哈希值的算法
//		System.out.println(97*31+98);
//		System.out.println("ab".hashCode());

		char[] chars = "a".toCharArray();
		System.out.println(chars[0]);

	}

	@Test
	public void stringTest1(){
		Class<? extends StringTest> aClass = this.getClass();
		System.out.println(aClass.isLocalClass());
		String s = "abcdef";
		String json = "{" +
				"\"user\":\"kimchy\"," +
				"\"postDate\":\"2013-01-30\"," +
				"\"message\":\"trying out Elasticsearch\"" +
				"}";
		JSONObject jsonObject = new JSONObject();
		JSONObject parse = (JSONObject) JSON.parse(json);
		System.out.println(parse);
		System.out.println(s.codePointAt(0));
	}

	enum week {
		mon,tus,fri
	}

	@Test
	public void enumTest() {
		System.out.println(week.mon);
	}

	@Test
	public void enumIterator() {
		for (Object o : week.values()) {
			System.out.println(o.toString());
		}
		System.out.println(1l << 8);

	}
	/**
	 * 分割字符串
	 */
	@Test
	public void splitTest() {
		String name = "abcdefghijklmn page pageContext request response out session exception application config";
		String[] cds = name.split("cd");
		for (String cd : cds) {
			System.out.println(cd);
		}
	}

	@Test
	public void test() {
		String name = "abcdefghijklmn page pageContext request response out session exception application config";

		String[] strings = StringUtils.tokenizeToStringArray(name, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		for (String string : strings) {
			System.out.println(string);
		}
	}

	@Test
	public void classLoaderTest() {
//		ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
//		System.out.println(classLoader.getClass());
		Class<?> clazz ;
		try {
			clazz = ClassUtils.forName("java.lang.String", StringTest.class.getClassLoader());
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				System.out.println(method);
			}
			System.out.println(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	@Test
	public void testHash() {
		System.out.println(("aa".hashCode())^(("aa".hashCode()) >>> 16));
		System.out.println(0 >>> 16);
	}
}
