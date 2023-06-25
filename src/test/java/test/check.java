package test;

import org.testng.annotations.Test;

import com.alliedTechnologies.utility.BaseClass;
import com.sns.loginPage.LoginPage;

public class check extends BaseClass {

	@Test
	public void Login() {
		LoginPage lp = new LoginPage(webUtil);
		lp.loginTest();
	}

}
