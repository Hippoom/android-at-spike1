package com.deppon.app.june.test;

import java.util.List;

import android.app.UiAutomation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;

import com.deppon.app.june.util.AppConstants;
import com.deppon.app.june.util.SoloAssistance;
import com.deppon.app.june.util.StrideAppUtil;
import com.deppon.app.june.util.Logger;
import com.robotium.solo.Solo;

public class DpappTest extends ActivityInstrumentationTestCase2 {

	private static Class<?> launcherActivityClass;
	private UiAutomation ui;
	private SoloAssistance assist;
	static {
		try {
			launcherActivityClass = Class.forName(AppConstants.LAUNCHER_ACTIVITY_DPAPP);
		} catch (ClassNotFoundException e) {
			Logger.e(e);
		}
	}

	@SuppressWarnings("unchecked")
	public DpappTest() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		assist = new SoloAssistance(getInstrumentation(), getActivity());
		ui = getInstrumentation().getUiAutomation();
	}

	@Override
	public void tearDown() throws Exception {
		assist.finishOpenedActivities();
		super.tearDown();
	}
	/**
	 * 获得验证码
	 */
	public String getSmsCode(){
		List<String> smscontents = StrideAppUtil.searchTextRegex(ui, "\\d{6,}");
		for(String contents:smscontents){
			Logger.v(contents);
		}
		List<AccessibilityNodeInfo> nodes = StrideAppUtil.getNodebyOpaqueText(ui,"(已读|close|关闭)");
		Logger.v(nodes.size()+"");
		for(AccessibilityNodeInfo node:nodes){
			if(node.isClickable()){
				node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
			}
		}
		if(smscontents.size()==0){
			return "";
		}
		return smscontents.get(0);
	}
	/**
	 * 登录界面-注册-输入所有信息-勾选同意协议-点击注册
	 */
	public void test0001() {
		assist.unlockScreen();
		// LoginActivity(登录界面)
		if (assist.waitForActivity("com.deppon.ecappactivites.member.LoginActivity")) {
			
			View rightBtn = assist.getViewById("rightBtn");
			assist.clickOnView(rightBtn);
		}
		// RegisterActivity(注册界面)
		if (assist.waitForActivity("com.deppon.ecappactivites.member.RegisterActivity")) {
			
			View etPhone = assist.getViewById("register_etPhone");
			View etCode = assist.getViewById("register_etCode");
			View btnGetCode = assist.getViewById("register_btnGetCode");
			View etPwd = assist.getViewById("register_etPwd");
			View etRepwd = assist.getViewById("register_etRepwd");
			View cbAgreement = assist.getViewById("register_cbAgreement");
			View readAgreement = assist.getViewById("register_readAgreement");
			View btnRegist = assist.getViewById("register_btnRegist");
			assist.clickOnView(btnGetCode);
			assist.sleep(50000);
			Logger.v("1111111111111111111111111");
			String code = getSmsCode();
			if(assist.waitForView(etCode)){
				assist.enterText((EditText)etCode, code);
			}
			assist.enterText((EditText)etPwd, "deppon123");
			assist.enterText((EditText)etRepwd, "deppon123");
			assist.clickOnView(cbAgreement);
			assist.clickOnView(btnRegist);
		}
		
		// RootActivity(主界面)
		boolean flag = assist.waitForActivity("com.deppon.ecappactivites.common.RootActivity");
		assertTrue("can not jump to com.deppon.ecappactivites.common.RootActivity", flag);
	}
	
	/**
	 * 登录界面-注册-输入所有信息-勾选同意协议-点击注册-注销登陆-输入用户名密码-点击登陆
	 */
	public void test0002() {
		assist.unlockScreen();
		// LoginActivity(登录界面)
		if (assist.waitForActivity("com.deppon.ecappactivites.member.LoginActivity")) {
			
			View rightBtn = assist.getViewById("rightBtn");
			assist.clickOnView(rightBtn);
		}
		// RegisterActivity(注册界面)
		if (assist.waitForActivity("com.deppon.ecappactivites.member.RegisterActivity")) {
			
			View etPhone = assist.getViewById("register_etPhone");
			View etCode = assist.getViewById("register_etCode");
			View btnGetCode = assist.getViewById("register_btnGetCode");
			View etPwd = assist.getViewById("register_etPwd");
			View etRepwd = assist.getViewById("register_etRepwd");
			View cbAgreement = assist.getViewById("register_cbAgreement");
			View readAgreement = assist.getViewById("register_readAgreement");
			View btnRegist = assist.getViewById("register_btnRegist");
			assist.clickOnView(btnGetCode);
			assist.sleep(50000);
			Logger.v("1111111111111111111111111");
			String code = getSmsCode();
			if(assist.waitForView(etCode)){
				assist.enterText((EditText)etCode, code);
			}
			assist.enterText((EditText)etPwd, "deppon123");
			assist.enterText((EditText)etRepwd, "deppon123");
			assist.clickOnView(cbAgreement);
			assist.clickOnView(btnRegist);
		}
		
		// RootActivity(主界面)
		boolean flag = assist.waitForActivity("com.deppon.ecappactivites.common.RootActivity");
		assertTrue("can not jump to com.deppon.ecappactivites.common.RootActivity", flag);
		View mydeppon = assist.getViewById("root_action3");
		assist.clickOnView(mydeppon);
		View btnLogout = assist.getViewById("myDeppon_btnLogout");
		assist.clickOnView(btnLogout);
		flag = assist.waitForActivity("com.deppon.ecappactivites.member.LoginActivity");
		assertTrue("can not jump to com.deppon.ecappactivites.member.LoginActivity", flag);
	}
	/**
	 * 我要下单-提示请填写姓名
	 */
	public void ytest0160() {
		assist.unlockScreen();
		
		// RootActivity(主界面)
		if (assist.waitForActivity("com.deppon.ecappactivites.common.RootActivity")) {
			
			View orderButton = assist.getViewById("index_btnOrder");
			assist.clickOnView(orderButton);
		}
		
		// DraftEditActivity(我要下单)
		if (assist.waitForActivity("com.deppon.ecappactivites.order.DraftEditActivity")) {
			
			// 立即下单
			View submitButton = assist.getViewById("btn_Submit");
			
			assist.clickOnView(submitButton);
			boolean flag = assist.searchText("请填写发货人姓名！", true);
			assertTrue("Error message is not found", flag);
		}
	}

}