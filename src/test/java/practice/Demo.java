package practice;

import java.util.Date;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date d=new Date();
		String date = d.toString().replace(" ","_").replace(":","_");
		System.out.println(date);
	}

}
