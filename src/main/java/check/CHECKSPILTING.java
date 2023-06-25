package check;

import org.testng.annotations.Test;

public class CHECKSPILTING {
	@Test
	public void main() {

		String se = "D:\\new projects\\appiumclient\\driver\\chromedriver.exe";
		String[] appname = se.split("\\\\");
		System.out.println(appname.length);
		System.out.println(appname.length - 1);
		System.out.println(appname[appname.length - 1]);

		try {
			int i = 10 / 0;
		} catch (Exception e) {
			System.out.println(e.toString());
			// System.out.println(e.getMessage());
		}

		System.out.println("hiss");

		int xStartPoint = 2;
		int yStartPoint = 4;
		int xEndPoint = 6;
		int yEndPoint = 7;

System.out.println("the value is ");	
	
	
	
	}



}
