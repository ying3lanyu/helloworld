package user.util;
//еп©усО╬Д
public class Stringutil {

	public static boolean isEmpty(String str){
		if ("".equals(str)||str == null){
			return true ;
		}
		else 
			return false;
	}
	public static boolean isNotEmpty(String str){
		if (!"".equals(str)&& str!= null){
			return true;
		}
		else
			return false;
	}
}
