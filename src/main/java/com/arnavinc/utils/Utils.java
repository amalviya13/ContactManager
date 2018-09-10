package com.arnavinc.utils;

import java.util.UUID;

public class Utils {

	public static String getGuid()
	{
		  UUID uuid = UUID.randomUUID();
	      return uuid.toString();
	}
}
