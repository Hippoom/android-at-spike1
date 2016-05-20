package com.deppon.app.june.test;

import android.app.Activity;
import android.app.UiAutomation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.deppon.app.june.util.AppConstants;
import com.deppon.app.june.util.AppConstants.Direction;
import com.deppon.app.june.util.StrideAppUtil;
import com.deppon.app.june.util.Logger;
import com.robotium.solo.Solo;

public class DepponTest extends ActivityInstrumentationTestCase2 {

	private Solo solo;
	
	private static Class<?> launchActivityClass;
	
	private UiAutomation ui;
	
	static {
		try {
			launchActivityClass = Class.forName(AppConstants.LAUNCHER_ACTIVITY_DPAPP);
//			launchActivityClass = Class.forName(AppConstants.LAUNCHER_ACTIVITY_ADDRESSBOOK);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public DepponTest() {
		super(launchActivityClass);
	}

	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		ui = getInstrumentation().getUiAutomation();
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	/**
	 * 欢迎界面-登录-我要下单
	 */
	public void testDpapp001() throws Exception {
		
		solo.unlockScreen();
		
		// InitGuideActivity(欢迎界面)
		if (solo.waitForActivity("com.deppon.ecappactivites.common.InitGuideActivity")) {
			
			solo.waitForView(android.widget.ImageView.class);
			solo.scrollToSide(Solo.RIGHT);
			solo.waitForView(android.widget.ImageView.class);
			solo.scrollToSide(Solo.RIGHT);
			solo.waitForView(android.widget.ImageView.class);
			solo.scrollToSide(Solo.RIGHT);
			solo.waitForView(android.widget.ImageView.class);
			solo.scrollToSide(Solo.RIGHT);

			waitForView(solo, "guide_btnEnd");
			View guideButton = solo.getView("guide_btnEnd");
			solo.clickOnView(guideButton);
		}
		
		// LoginActivity(登录界面)
		if (solo.waitForActivity("com.deppon.ecappactivites.member.LoginActivity")) {
			
			EditText phone = (EditText) solo.getView("login_etPhone");
			EditText password = (EditText) solo.getView("login_etPwd");
			View saveCheck = solo.getView("login_cbSaveUser");
			View loginButton = solo.getView("loginBtn");

			solo.clearEditText((EditText) phone);
			solo.enterText((EditText) phone, "15821387135");
			solo.enterText((EditText) password, "cxf134520");
			solo.clickOnView(saveCheck);
			solo.clickOnView(loginButton);
		}
		
		// RootActivity(主界面)
		if (solo.waitForActivity("com.deppon.ecappactivites.common.RootActivity")) {
			
			View orderButton = solo.getView("index_btnOrder");
			solo.clickOnView(orderButton);
		}
		
		// DraftEditActivity(我要下单)
		if (solo.waitForActivity("com.deppon.ecappactivites.order.DraftEditActivity")) {
			
			// 1.发货人信息
			// 姓名
			EditText name = (EditText) solo.getView("order_etPosterName");
			// 手机号
			EditText phone = (EditText) solo.getView("order_etPosterPhone");
			// 城市
			TextView city = (TextView) solo.getView("order_tvPosterAreaName");
			// 详细地址（街道门牌号）
			EditText address = (EditText) solo.getView("order_etPosterAddress");
			// 优惠券代码
			EditText depponDode = (EditText) solo.getView("order_etCouponCode");
			// 填写更多信息
			View showMore = solo.getView("show_more");

			solo.enterText((EditText) name, "Tester");
			solo.enterText((EditText) phone, "1234567890");
			
			solo.clickOnView(city);
			waitForView(solo, "year");
			
			View year = solo.getView("year");
			View month = solo.getView("month");
			View day = solo.getView("day");
			
			int[] locYear = new int[2];
			int[] locMonth = new int[2];
			int[] locDay = new int[2];
			year.getLocationOnScreen(locYear);
			month.getLocationOnScreen(locMonth);
			day.getLocationOnScreen(locDay);
			
			// 选择广东省（向上拖动两格）
			solo.drag(locYear[0], locYear[0], locYear[1], locYear[1] - 100, 5);
			// 选择云浮市（向上拖动三格）
			solo.drag(locMonth[0], locMonth[0], locMonth[1], locMonth[1] - 150, 5);
			// 选择市辖区（向上拖动四格）
			solo.drag(locDay[0], locDay[0], locDay[1], locDay[1] - 200, 5);
			
			View sureButton = solo.getView("btn_datetime_sure");
			solo.clickOnView(sureButton);
			
			solo.enterText((EditText) address, "明珠路1018号");
			solo.enterText((EditText) depponDode, "1234");
			
			solo.clickOnView(showMore);
			waitForView(solo, "order_etReceiverName");

			// 2.收货人信息
			// 姓名
			EditText recName = (EditText) solo.getView("order_etReceiverName");
			// 手机号
			EditText recPhone = (EditText) solo.getView("order_etReceiverPhone");
			// 城市
			TextView recCity = (TextView) solo.getView("order_tvReceiverAreaName");
			// 详细地址（街道门牌号）
			EditText recAddress = (EditText) solo.getView("order_etReceiverAddress");
			
			solo.enterText((EditText) recName, "Receiver");
			solo.enterText((EditText) recPhone, "13100000000");
			
			solo.clickOnView(recCity);
			waitForView(solo, "year");
			
			year.getLocationOnScreen(locYear);
			day.getLocationOnScreen(locDay);
			
			// 选择上海市
			solo.drag(locYear[0], locYear[0], locYear[1], locYear[1] + 50, 5);
			// 选择青浦区
			solo.drag(locDay[0], locDay[0], locDay[1], locDay[1] - 200, 10);
			
			sureButton = solo.getView("btn_datetime_sure");
			solo.clickOnView(sureButton);
			
			solo.enterText((EditText) recAddress, "徐祥路1号");
			
			// 3.其他
			// 物品名称
			EditText recCargo = (EditText) solo.getView("order_etCargoName");
			// 数量
			EditText quantity = (EditText) solo.getView("order_etCargoQuantity");
			// 重量
			EditText weight = (EditText) solo.getView("order_etCargoWeight");
			
			solo.enterText((EditText) recCargo, "Other Cargo");
			solo.enterText((EditText) quantity, "100");
			solo.enterText((EditText) weight, "500");
			
			// 4.增值服务
			// 保价声明
			EditText insurance = (EditText) solo.getView("order_etInsurance");
			// 代收货款类型
			TextView deleType = (TextView) solo.getView("order_delegateType");
			// 代收货款金额
			EditText amount = (EditText) solo.getView("order_delegateAmount");
			// 签收单
			TextView signType = (TextView) solo.getView("order_signType");
			// 送货方式
			TextView deliType = (TextView) solo.getView("order_deliverType");
			
			solo.enterText((EditText) insurance, "200");
			solo.clickOnView(deleType);
			solo.clickOnMenuItem("三日退");
			solo.enterText((EditText) amount, "100");
			solo.clickOnView(signType);
			solo.clickOnMenuItem("签收单传真返回");
			solo.clickOnView(deliType);
			solo.clickOnMenuItem("送货上楼");
			
		}
		
		Logger.v(solo.getCurrentActivity().getLocalClassName());
	}

	/**
	 * 欢迎界面-登录-我要下单-发货人姓名未填写
	 */
	public void testDpapp002() throws Exception {
		
		solo.unlockScreen();
		
		// RootActivity(主界面)
		if (solo.waitForActivity("com.deppon.ecappactivites.common.RootActivity")) {
			
			View orderButton = solo.getView("index_btnOrder");
			solo.clickOnView(orderButton);
		}
		
		// DraftEditActivity(我要下单)
		if (solo.waitForActivity("com.deppon.ecappactivites.order.DraftEditActivity")) {
			
			// 立即下单
			View submitButton = solo.getView("btn_Submit");
			
			solo.clickOnView(submitButton);
			boolean flg = solo.searchText("请填写发货人姓名！", true);
			assertTrue("Error message is not found", flg);
			
		}
	}
	
	/**
	 * 欢迎界面-登录-我要下单-从通讯录选择发货人
	 */
	public void testDpapp003() throws Exception {
		
		Logger.v("in testDpapp003");
		
		solo.unlockScreen();
		
		// RootActivity(主界面)
		if (solo.waitForActivity("com.deppon.ecappactivites.common.RootActivity")) {
			
			if (waitForView(solo, "myDialog_content")) {
				View dialog = solo.getView("myDialog_content");
				if (dialog != null) {
					View cancelButton = solo.getView("myDialog_cancel");
					solo.clickOnView(cancelButton);
				}
			}
			
			View orderButton = solo.getView("index_btnOrder");
			solo.clickOnView(orderButton);
		}
		
		// DraftEditActivity(我要下单)
		if (solo.waitForActivity("com.deppon.ecappactivites.order.DraftEditActivity")) {
			
			// 选择联系人按钮
			View addPosButton = solo.getView("order_btnAddPoster");
			solo.clickOnView(addPosButton);
			solo.clickOnMenuItem("通讯录");
			
			// 选择联系人
			StrideAppUtil.clickById(ui, "com.android.contacts:id/cliv_name_textview", 1);
			
			solo.sleep(1000);
			
			waitForView(solo, "btn_Submit");
			View submitButton = solo.getView("btn_Submit");
			solo.clickOnView(submitButton);
		}
	}

	/**
	 * 欢迎界面-登录-从微信登录
	 */
	public void testDpapp004() throws Exception {
		
		Logger.v("in testDpapp004");
		solo.unlockScreen();
		
		// LoginActivity(登录界面)
		if (solo.waitForActivity("com.deppon.ecappactivites.member.LoginActivity")) {

			View weixinButton = solo.getView("WeiXinLoginBtn");
			solo.clickOnView(weixinButton);
			solo.sleep(2000);
			
			StrideAppUtil.inputById(ui, "com.tencent.mm:id/df", "duMMy", 0);
			StrideAppUtil.inputById(ui, "com.tencent.mm:id/df", "duMMy", 1);
			StrideAppUtil.clickById(ui, "com.tencent.mm:id/aiq");
			
		}
	}
	
	/**
	 * 主界面-网点查询-拨打电话
	 */
	public void testDpapp005() throws Exception {
		
		Logger.v("in testDpapp005");
		solo.unlockScreen();
		
		// RootActivity(主界面)
		if (solo.waitForActivity("com.deppon.ecappactivites.common.RootActivity")) {

			View depButton = solo.getView("index_btnDepartureSearch");
			solo.clickOnView(depButton);
			solo.sleep(1000);
			
			View telephone = solo.getView("branchItem_telephone", 1);
			solo.clickOnView(telephone);
			solo.sleep(1000);
			
			StrideAppUtil.clickById(ui, "com.android.contacts:id/two");
			solo.sleep(1000);
			StrideAppUtil.clickById(ui, "com.android.contacts:id/call1");
			
			solo.sleep(1000);
			boolean flg = StrideAppUtil.searchText(ui, "无法访问移动网络");
			assertTrue("Error message is not found", flg);
		}
	}
	
	public void testAddressbook001() throws Exception {
		
	}
	
	private boolean waitForView(Solo solo, String strId) {
		int id = getIntId(solo.getCurrentActivity(), strId);
		return solo.waitForView(id);
	}
	
	private int getIntId(Activity activity, String id) {
		return activity.getResources().getIdentifier(id, "id",
				activity.getPackageName());
	}
}
