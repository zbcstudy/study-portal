package com.wondertek.baiying.util.UUID;

import java.util.Arrays;
import java.util.UUID;

public class TestUUID {
	public static void main(String[] args) {
		UUID u=UUID.randomUUID();
		String uuid=u.toString();
		System.out.println(uuid);
		String[] strArr=uuid.split("-");
		System.out.println(Arrays.toString(strArr));
		String str="";
		for(int i=0;i<strArr.length;i++){
			str=str+strArr[i];
		}
		System.out.println(str);
		System.out.println(str.length()); 
	}
}
