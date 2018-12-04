package com.wondertek.baiying.util.UUID;

import java.util.UUID;

public class Touuid {
	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX).substring(1);
	}

	/**
	 * 以62进制（字母加数字）生成19位UUID，最短的UUID 如此一来，把原来32位或者36位的原始UUID缩短为19位，且不丢失精度。生成的UUID大概类似 wcea4ucWUxPx0g8ahel 最后，如果在digits追加一些字符，甚至可以达到七八十进制的水平，可再缩短一两位UUID长度，但鉴于UUID可读性不建议这么做
	 * 
	 * @return
	 */
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();
		sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
		sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
		sb.append(digits(uuid.getMostSignificantBits(), 4));
		sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
		sb.append(digits(uuid.getLeastSignificantBits(), 12));
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(uuid());
		System.out.println(uuid().length());
	}

}
