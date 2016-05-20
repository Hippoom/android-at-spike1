package com.deppon.app.june.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.deppon.app.june.util.AppConstants;
import com.deppon.app.june.util.Logger;
import com.deppon.app.june.util.SoloAssistance;
import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;

public class AddressbookTest extends ActivityInstrumentationTestCase2 {

	private static Class<?> launcherActivityClass;
    private SoloAssistance solo;
	
	static {
		try {
			launcherActivityClass = Class.forName(AppConstants.LAUNCHER_ACTIVITY_ADDRESSBOOK);
		} catch (ClassNotFoundException e) {
			Logger.e(e);
		}
	}

	@SuppressWarnings("unchecked")
	public AddressbookTest() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new SoloAssistance(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}
	
	/*
	 * 登陆-待办事项-点击查询-点击查看详细
	 * 
	 */
	public void test002() {
		
          solo.unlockScreen();
          //取消跟新
          if(solo.waitForText("新版本更新内容")){
        	  solo.clickOnButton("忽略");
          }
		
		 // LoginActivity(登陆界面)
          if (solo.waitForActivity("com.deppon.app.addressbook.activity.LoginActivity")){
        	//用户名、密码、保存密码 、登陆
        	EditText name = (EditText) solo.getView("inputName");
  			EditText password = (EditText) solo.getView("inputPass");
  		//	View saveCheck = solo.getView("login_cbSaveUser");
  			View loginButton = solo.getView("buttonLogin");
  			
  		    //账号、密码
  			String loginName="000021";
  			String passwd="qqqqqq";
  			//s输入账号密码
  			solo.clearEditText((EditText) name);
  			solo.enterText((EditText) name, loginName);
  			solo.enterText((EditText) password, passwd);
  			//点击滑动
  		//	solo.clickOnView(saveCheck);
  			solo.clickOnView(loginButton);
          }
          
         //暂不创建快捷方式 
          
          if(solo.waitForText("暂不创建")){
        	  solo.clickOnButton("暂不创建");
          }
          
          
        //判断登陆成功，点击代办事项  
          
		if(solo.waitForActivity("com.deppon.app.addressbook.activity.HomeActivity")){
			Logger.v("登陆成功");
			//点击代办实现 0：首页
			View menu=solo.getView("0");
			solo.clickOnView(menu);
			//1:代办事项
			GridView gridview = (GridView)solo.getView("GridView");
			RelativeLayout linearlayout = (RelativeLayout) gridview.getChildAt(1);
	        solo.clickOnView(linearlayout);
			
			
		}
		//输入工作流号，点击查询
		
	      By by = solo.xpath("key",2);
		  if(solo.waitForWebElement(by, true)){
			  Logger.v("当前url:"+solo.getWebUrl());	
			  ArrayList<WebElement> w=solo.getWebElements(by);
			  
			  //输入查询的工作流号
			  String fssc="FSSC101131125000006";
			  solo.enterTextInWebElement(by, fssc);
			  
			  //点击查询
			  By by_search = solo.xpath(" //*[@id='div2']/div[1]/table/tbody/tr/td[2]/input ", 1);
			  solo.clickOnWebElement(by_search);
		  }
		  
	}
}