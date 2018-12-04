package com.wondertek.baiying.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

//import solr.entityEnum.DocRelation;
//import solr.entityEnum.SearchEngineIndexHorizontalEnum;

/**
 * To generate a message in string form. 
 * The generated message can be sent to the search service system via a message component, such as Kafka. 
 * 用于生成string形式的消息，生成的消息可发送给消息组件，以便搜索服务系统接收
 * @author Ma Xiaohui
 * 
 */
public class SearchEngineInteractionUtil {
//	private static final int DELETE_QUERY = 0;
	private static final int IMPORT = 1;
//	private static final int DELETE_BY_ID = 2;
	
	private static final Log log = LogFactory.getLog(SearchEngineInteractionUtil.class);
	
	private static Boolean isVideo = false;
	private static final String DEFAULT_DATE_STRING_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_DATE_STRING_FORMAT_ALLNUM = "yyyyMMddHHmmss";
	private static final String DEFAULT_DATE_STRING_FORMAT = isVideo ? DEFAULT_DATE_STRING_FORMAT_ALLNUM : DEFAULT_DATE_STRING_FORMAT_NORMAL;
	
	private static ObjectMapper mapper = new ObjectMapper()
			//在序列化时忽略值为 null 的属性
//			.setSerializationInclusion(Include.NON_NULL)
//			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			//在反序列化时忽略在 JSON 中存在但 Java 对象不存在的属性
//			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
			.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_STRING_FORMAT))
			//在序列化时日期格式默认为 yyyy-MM-dd'T'HH:mm:ss.SSSZ
//			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
			.registerModule(new SimpleModule()
					.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
				@Override
				public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
						throws IOException, JsonProcessingException {
						jgen.writeString(DateTimeFormatter.ofPattern(DEFAULT_DATE_STRING_FORMAT).format(value));
				}
			})
//					.addSerializer(LocalDate.class, new LocalDateSerializer())
//					.addSerializer(LocalTime.class, new LocalTimeSerializer())
					);
	
	private static String value2String(Object obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
	
	/**
	 * 生成单个对象新增（C）/修改（U）的消息
	 * @param obj 对象
	 * @param collectionName 索引库名
	 * @return 生成消息
	 */
	public static String generateAddOrUpdateMsg4Single(Object obj, String collectionName) {
		List<Object> list = new ArrayList<Object>();
		list.add(obj);
		return generateAddOrUpdateMsg4Batch(list, collectionName);
	}
	
	/**
	 * 生成批量对象新增（C）/修改（U）的消息
	 * @param list 对象集合
	 * @param collectionName 索引库名
	 * @return 生成消息
	 */
	public static String generateAddOrUpdateMsg4Batch(List<?> list, String collectionName) {
		List<Map<String, Object>> docMapList = new ArrayList<Map<String, Object>>();
		for (Object obj : list) {
			if (obj == null) {
				continue;
			}
			docMapList.add(generateDocMap(obj, null));
		}
		if (docMapList == null || docMapList.isEmpty()) {
			log.warn("No JavaBean to generate message for indexing");
			return null;
		}
		return generateAddOrUpdateMsg(collectionName, docMapList);
	}
	
	/**
	 * 生成关联对象新增（C）/修改（U）的消息
	 * @param parent 主体对象
	 * @param childList 与主体对象绑定关联关系的对象集合
	 * @param collectionName 索引库名
	 * @return 生成消息
	 */
	public static String generateAddOrUpdateMsg4Linked(Object parent, List<?> childList, String collectionName) {
		List<Map<Object, Object>> childDocList = new ArrayList<Map<Object, Object>>();
		for (Object child : childList) {
			childDocList.add(objectConvert2Map4Index(child));
		}
		List<Map<String, Object>> docMapList = new ArrayList<Map<String, Object>>();
		docMapList.add(generateDocMap(parent, childDocList));
		return generateAddOrUpdateMsg(collectionName, docMapList);
	}
	
	private static String generateAddOrUpdateMsg(String collectionName, List<Map<String, Object>> docMapList) {
		Map<String, Object> importMap = new HashMap<String, Object>();
		importMap.put("collection", collectionName);
		importMap.put("optType", IMPORT);
		importMap.put("docDatas", docMapList);
		String sendMessage = null;
		try {
			sendMessage = value2String(importMap);
			log.info("Generate message: " + sendMessage);
			log.info(sendMessage);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return sendMessage;
	}
	
	private static Map<String, Object> generateDocMap(Object obj, List<?> childDocList) {
		Map<String, Object> docMap = new HashMap<String, Object>();
		if (!isJavaClass(obj.getClass())) {
			docMap.put("parentDoc", objectConvert2Map4Index(obj));
		} else if (obj instanceof Map<?, ?>) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			for (Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
				try {
					map.put(processFieldValue(entry.getKey()), processFieldValue(entry.getValue()));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			docMap.put("parentDoc", generateSearchId(map, obj.getClass().getName()));
		}
		docMap.put("childDocList", childDocList);
		return docMap;
	}
	
	private static Map<Object, Object> objectConvert2Map4Index(Object obj) {
		Map<Object, Object> map = objectConvert2Map(obj);
		String searchType = obj != null ? obj.getClass().getSimpleName() : null;
		return generateSearchId(map, searchType);
	}
	
	private static Map<Object, Object> generateSearchId(Map<Object, Object> map, String searchType) {
		if (map != null && !map.isEmpty() && searchType != null && searchType.trim().length() != 0) {
			map.put("searchType", searchType);
			String searchId = getPrimaryKey(map);
			if (searchId == null || searchId.trim().length() == 0) {
				return null;
			}
			map.put("searchId", searchId);
			
		}
		// TODO: 2018/3/12
		//return defineDocRelation(map, searchType);
		return map;
	}
	
	private static Map<Object, Object> objectConvert2Map(Object obj) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (obj != null) {
			Class<?> cls = obj.getClass();
			List<Field> fieldList = getOverallDeclaredFields(obj);
			Method m = null;
			for (Field f : fieldList) {
				if (f.getName().equals("serialVersionUID")) {
					continue;
				}
				try {
					PropertyDescriptor pd = new PropertyDescriptor(f.getName(), cls);
					m = pd.getReadMethod();
//					if (f.getGenericType().getTypeName().indexOf("java.util.List") > -1 || f.getGenericType().getTypeName().indexOf("[]") > -1) {
//						// 去掉 List 和 Array
//						log.info(f.getName() + ", " + f.getGenericType().getTypeName());
//						continue;
//					}						
//					map.put(f.getName(), m.invoke(obj));
					map.put(f.getName(), processFieldValue(m.invoke(obj)));
				} catch (Exception e) {
					List<Method> methodList = getOverallDeclaredMethods(obj);
					Boolean hasReadMethod = false;
					for (Method method : methodList) {
						if (!method.getName().startsWith("set") && method.getName().toLowerCase().endsWith(f.getName().toLowerCase())) {
							try {
//								map.put(f.getName(), method.invoke(obj));
								map.put(f.getName(), processFieldValue(method.invoke(obj)));
								hasReadMethod = true;
								break;
							} catch (Exception e1) {
								log.error(e1.getMessage() + " || " + f.getName(), e1);
								// TODO return 
							}
						}
					}
					if (!hasReadMethod) {
						log.error(e.getMessage(), e);
						// TODO return 
					}
				}
			}	
		}
		return map;
	}
	
	private static Object processFieldValue(Object obj) throws JsonProcessingException {
		if (obj == null) {
			return obj;
		}
//		log.info(obj.getClass().getName() + ": " + obj);
		if (obj instanceof Collection) {
			obj = ((Collection<?>)obj).toArray();
		} else if (obj instanceof Map<?, ?>) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			for (Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
				map.put(processFieldValue(entry.getKey()), processFieldValue(entry.getValue()));
			}
			obj = value2String(map);
		} else if (obj.getClass().isEnum()) {
			obj = obj.toString();
		}
		if (obj.getClass().isArray()) {
			List<Object> list = new ArrayList<Object>();
			for (Object fVal : (Object[])obj) {
				list.add(processFieldValue(fVal));
			}
			obj = list;
		}
		if (!isJavaClass(obj.getClass())) {
			obj = value2String(obj);
//			obj = objectConvert2Map(obj);
		}
		return obj;
	}
	
	/**
	 * 循环向上获取对象及其父类的Field
	 * @param object 对象实体
	 * @return
	 */
	private static List<Field> getOverallDeclaredFields(Object object) {
		Class<?> cls = object.getClass();
		List<Field> fieldList = new ArrayList<Field>();
		for (; cls != Object.class; cls = cls.getSuperclass()) {
			fieldList.addAll(Arrays.asList(cls.getDeclaredFields()));
		}
		return fieldList;
	}
	
	private static List<Method> getOverallDeclaredMethods(Object object) {
		Class<?> cls = object.getClass();
		List<Method> methodList = new ArrayList<Method>();
		for (; cls != Object.class; cls = cls.getSuperclass()) {
			methodList.addAll(Arrays.asList(cls.getDeclaredMethods()));
		}
		return methodList;
	}

	private static String getPrimaryKey(Map<Object, Object> map) {
		String result = "";
		String searchType = String.valueOf(map.get("searchType"));
		String[] idMarks = new String[]{"id", "Id", "ID", "iD"};
		String id = "";
		for (String realId : idMarks) {
			if (map.get(realId) != null) {
				id = String.valueOf(map.get(realId));
				break;
			}
		}
		if (id != null && id.trim().length() != 0 && searchType != null && searchType.trim().length() != 0) {
			result = searchType + "_" + id;
		} else {
			log.error(searchType + " " + id + " searchId generated in error");
		}
		return result;
	}
	
	/** 
     * 判断一个类是 JAVA 类型还是用户定义类型 
     * @param clz 
     * @return 
     */
    private static boolean isJavaClass(Class<?> clz) {     
        return clz != null && clz.getClassLoader() == null;     
    }
    
//    private static Map<Object, Object> defineDocRelation(Map<Object, Object> fieldMap, String searchType){
//    	List<DocRelation> docRelationList = new ArrayList<DocRelation>();
//    	if ("AssetMG".equals(searchType)) {
//    		DocRelation dr1 = new DocRelation();
//    		dr1.setRole(SearchEngineIndexHorizontalEnum.MALE);
//    		dr1.setUpdateDocRole(SearchEngineIndexHorizontalEnum.POST);
//    		dr1.setRelatedField("assetId");
//    		dr1.setRelatedSearchType("ContentMG");
//    		docRelationList.add(dr1);
//    		DocRelation dr2 = new DocRelation();
//    		dr2.setRole(SearchEngineIndexHorizontalEnum.MALE);
//    		dr2.setAttachedDocRole(SearchEngineIndexHorizontalEnum.POSTMALE);
//    		dr2.setRelatedField("assetId");
//    		dr2.setRelatedSearchType("MediaMG");
//    		dr2.setOtherRelatedExpression(" AND isSource:1");
//    		docRelationList.add(dr2);
//    	}
//    	if ("CopyrightMG".equals(searchType)) {
//    		DocRelation dr = new DocRelation();
//    		dr.setRole(SearchEngineIndexHorizontalEnum.FEMALE);
//    		dr.setUpdateDocRole(SearchEngineIndexHorizontalEnum.POST);
//    		dr.setRelatedField("copyrightId");
//    		dr.setRelatedSearchType("ContentMG");
//    		docRelationList.add(dr);
//    	}
//    	if ("ContentMG".equals(searchType)) {
//    		DocRelation dr1 = new DocRelation();
//    		dr1.setRole(SearchEngineIndexHorizontalEnum.POST);
//    		dr1.setAttachedDocRole(SearchEngineIndexHorizontalEnum.MALE);
//    		dr1.setRelatedField("assetId");
//    		dr1.setRelatedSearchType("AssetMG");
//    		docRelationList.add(dr1);
//    		DocRelation dr2 = new DocRelation();
//    		dr2.setRole(SearchEngineIndexHorizontalEnum.POST);
//    		dr2.setAttachedDocRole(SearchEngineIndexHorizontalEnum.FEMALE);
//    		dr2.setRelatedField("copyrightId");
//    		dr2.setRelatedSearchType("CopyrightMG");
//    		docRelationList.add(dr2);
//    	}
//    	if ("MediaMG".equals(searchType) && "1".equals(fieldMap.get("isSource"))) {
//    		DocRelation dr = new DocRelation();
//    		dr.setRole(SearchEngineIndexHorizontalEnum.POSTMALE);
//    		dr.setUpdateDocRole(SearchEngineIndexHorizontalEnum.MALE);
//    		dr.setRelatedField("assetId");
//    		dr.setRelatedSearchType("AssetMG");
//    		docRelationList.add(dr);
//    	}
//    	try {
//			fieldMap.put("docRelations", processFieldValue(docRelationList));
//		} catch (JsonProcessingException e) {
//			log.error(e.getMessage(), e);
//		}
//    	return fieldMap;
//    }
}