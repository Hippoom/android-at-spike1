package com.deppon.app.june.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;

import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;

public class SoloAssistance extends Solo {
	
	public SoloAssistance(Instrumentation instrumentation) {
		super(instrumentation);
		// TODO Auto-generated constructor stub
	}
	public SoloAssistance(Instrumentation instrumentation,Activity activity ) {
		super(instrumentation,activity);
		// TODO Auto-generated constructor stub
	}
	
	public SoloAssistance(Instrumentation instrumentation,Config config ) {
		super(instrumentation,config);
		// TODO Auto-generated constructor stub
	}
	
	public SoloAssistance(Instrumentation instrumentation,Config config,Activity activity ) {
		super(instrumentation,config,activity);
		// TODO Auto-generated constructor stub
	}
	

	public void enterText(EditText editText, String text) {
		sleep(AppConstants.GAP_TIME);
		clearEditText(editText);
		super.enterText(editText, text);
	}

	public void drag(float fromX, float toX, float fromY, float toY, int stepCount) {
		sleep(AppConstants.GAP_TIME);
		super.drag(fromX, toX, fromY, toY, stepCount);
	}

	public void clickOnButton(String text) {
		sleep(AppConstants.GAP_TIME);
		super.clickOnButton(text);
	}

	/************************************************* modify ***************************************************/

	/* WaitFor */
	public boolean waitForWebElement(By by, boolean scroll) {
		Timer.start();
		while(!Timer.timeout()) {
			Logger.v("WaitForWebElement" + getCurrentActivity().getClass().getName());
			if (super.waitForWebElement(by, (int)Timer.WAIT_TIME, scroll)) {
				return true;
			}
		}
		Logger.v("no found juneWaitForWebElement");
		return false;
	}

	public boolean waitForActivity(String activity) {
		Timer.start();
		
		while(!Timer.timeout()) {
			if (activity.equals(getCurrentActivity().getClass().getName())) {
				Logger.v(getCurrentActivity().getClass().getName());
				return true;
			}
			Logger.v("expected:" + activity + "  real: " + getCurrentActivity().getClass().getName());
		}
		Logger.v("outer expected:" + "real:" + getCurrentActivity().getClass().getName());
		return false;
	}

	public boolean waitForActivity(String activity, String activity1) {
		Timer.start();
		
		while(!Timer.timeout()) {
			Logger.v("WaitForActivity" + getCurrentActivity().getClass().getName());
			String ta = getCurrentActivity().getClass().getName();
			if (activity.equals(ta) || activity1.equals(ta)) {
				Logger.v(getCurrentActivity().getClass().getName());
				return true;
			}
			Logger.v("expected:" + activity + "  real: " + getCurrentActivity().getClass().getName());
		}
		Logger.v("outer expected:" + "real:" + getCurrentActivity().getClass().getName());
		return false;
	}

	/* scroll */
	public void webScrollVertical(WebView wv, boolean down) {
		final Rect r = new Rect();
		wv.getHitRect(r);
		final int deltax = (int) (r.width() * 0.05);
		final int spany = (int) (r.height() * 0.3);
		int[] location = new int[2];
		wv.getLocationOnScreen(location);
		sleep(AppConstants.GAP_TIME);
		if (down) {
			Logger.v((location[0] + r.width() / 2 - deltax) + "," + ((location[0] + r.width() / 2) + deltax) + ","
					+ ((location[1] + r.height() / 2) + spany) + "," + ((location[1] + r.height() / 2) - spany));
			this.drag((location[0] + r.width() / 2) - deltax, (location[0] + r.width() / 2) + deltax,
					(location[1] + r.height() / 2) + spany, (location[1] + r.height() / 2) - spany, 5);
		} else {
			Logger.v((location[0] + r.width() / 2 - deltax) + "," + ((location[0] + r.width() / 2) + deltax) + ","
					+ ((location[1] + r.height() / 2) - spany) + "," + ((location[1] + r.height() / 2) + spany));
			this.drag((location[0] + r.width() / 2) - deltax, (location[0] + r.width() / 2) + deltax,
					(location[1] + r.height() / 2) - spany, (location[1] + r.height() / 2) + spany, 5);
			Logger.v("webScrollVertical");
		}
	}

	public void webScrollHorizontal(WebView wv, boolean left) {
		final Rect r = new Rect();
		wv.getHitRect(r);
		final int deltay = (int) (r.height() * 0.05);
		final int spanx = (int) (r.width() * 0.3);
		int[] location = new int[2];
		wv.getLocationOnScreen(location);
		sleep(AppConstants.GAP_TIME);
		if (left) {
			Logger.v((location[0] + r.width() / 2 - spanx) + "," + ((location[0] + r.width() / 2) + spanx) + ","
					+ ((location[1] + r.height() / 2) - deltay) + "," + ((location[1] + r.height() / 2) + deltay));
			this.drag((location[0] + r.width() / 2) - spanx, (location[0] + r.width() / 2) + spanx,
					(location[1] + r.height() / 2) - deltay, (location[1] + r.height() / 2) + deltay, 5);
		} else {
			Logger.v((location[0] + r.width() / 2 + spanx) + "," + ((location[0] + r.width() / 2) - spanx) + ","
					+ ((location[1] + r.height() / 2) - deltay) + "," + ((location[1] + r.height() / 2) + deltay));
			this.drag((location[0] + r.width() / 2) + spanx, (location[0] + r.width() / 2) - spanx,
					(location[1] + r.height() / 2) - deltay, (location[1] + r.height() / 2) + deltay, 5);
		}
		Logger.v("webScrollHorizontal");
	}

	public void slideon(String id) {// frome left to right
		slideOnView(id, "right");
	}

	public void slideoff(String id) {// frome right to left
		slideOnView(id, "left");
	}

	/* click */
	public void clickOnView(View view) {
		sleep(AppConstants.GAP_TIME);
		try {
			super.clickOnView(view);
		} catch (Exception e) {
			Logger.v("clickOnView->ClickOnView");
			clickOnViewByLocation(view);
		}

	}

	public void clickOnViewByLocation(View v) {
		final Rect r = new Rect();
		v.getHitRect(r);
		int[] location = new int[2];
		v.getLocationOnScreen(location);
		sleep(AppConstants.GAP_TIME);
		clickOnScreen(location[0] + r.width() / 2, location[1] + r.height() / 2);
		Logger.v("juneClickOnView");
	}

	public void slideOnView(String id, String direction) {// frome right to left
		final Rect r = new Rect();
		View slide = getViewById(id);
		slide.getHitRect(r);
		final int deltax = (int) (r.width() * 0.05);
		final int spany = (int) (r.height() * 0.4);
		final int deltay = (int) (r.height() * 0.05);
		final int spanx = (int) (r.width() * 0.4);
		int[] location = new int[2];
		slide.getLocationOnScreen(location);
		sleep(AppConstants.GAP_TIME);
		if (direction.equalsIgnoreCase("left")) {
			this.drag((location[0] + r.width() / 2) + spanx, (location[0] + r.width() / 2) - spanx,
					(location[1] + r.height() / 2) + deltay, (location[1] + r.height() / 2) - deltay, 5);
			Logger.v("SlideOnView left");
		} else if (direction.equalsIgnoreCase("right")) {
			this.drag((location[0] + r.width() / 2) - spanx, (location[0] + r.width() / 2) + spanx,
					(location[1] + r.height() / 2) + deltay, (location[1] + r.height() / 2) - deltay, 5);
			Logger.v("SlideOnView right");
		} else if (direction.equalsIgnoreCase("down")) {
			this.drag((location[0] + r.width() / 2) - deltax, (location[0] + r.width() / 2) + deltax,
					(location[1] + r.height() / 2) - spany, (location[1] + r.height() / 2) + spany, 5);
			Logger.v("SlideOnView down");
		} else if (direction.equalsIgnoreCase("up")) {
			this.drag((location[0] + r.width() / 2) - deltax, (location[0] + r.width() / 2) + deltax,
					(location[1] + r.height() / 2) + spany, (location[1] + r.height() / 2) - spany, 5);
			Logger.v("SlideOnView down");
		} else {
			Logger.v("SlideOnView++invalid " + direction);
		}
	}

	public void clickWebElement(String positionin, int flag) {
		By by = xpath(positionin, flag);
		waitForWebElement(by, false);
		super.clickOnWebElement(by);
	}

	/* xpath */
	private By searchXpath(String xpath, int flag) {// 1:xpath 2:id 3:tagname 4: name
		By by = null; // 5:textcontent 6:classname 7:css
		try {
			Logger.v("jBy.xpath::" + xpath);
			switch (flag) {
			case 1:
				by = By.xpath(xpath);
				break;
			case 2:
				by = By.id(xpath);
				break;
			case 3:
				by = By.tagName(xpath);
				break;
			case 4:
				by = By.name(xpath);
				break;
			case 5:
				by = By.textContent(xpath);
				break;
			case 6:
				by = By.className(xpath);
				break;
			case 7:
				by = By.cssSelector(xpath);
				break;
			default:
				Logger.v("Xpath ---default");
			}
		} catch (Exception e) {
			Logger.v("juneXpath++false");
		}
		Logger.v("jBy.xpath:" + xpath);
		return by;
	}

	public By xpath(String xpath, int flag) {
		By by = null;
		Timer.start();
		
		while (!Timer.timeout()) {
			by = searchXpath(xpath, flag);
			if (null != by) {
				Logger.v("june++xpath true  " + by.toString());
				break;
			}
		}
		return by;
	}

	/* getView */
	public View getViewById(int id) {
		View view = null;
		Timer.start();
		
		while (!Timer.timeout()) {
			if (super.waitForView(id)) {
				try {
					view = super.getView(id);
				} catch (Exception ex) {
					Logger.e(ex);
				}
				if (view != null) {
					break;
				}
			}
		}

		Logger.v("getViewById end");
		return view;
	}

	public View getViewById(String id) {
		View view = null;
		Timer.start();
		
		int jid = stringId2intId(id);
		while (!Timer.timeout()) {
			if (waitForView(jid)) {
				try {
					view = getView(id);
				} catch (Exception e) {
					Logger.e(e);
				}
				if (view != null) {
					break;
				}
			}
		}

		Logger.v("getViewById end");
		return view;
	}

	public int stringId2intId(String id) {
		// 获取当前Activity
		Activity act = getCurrentActivity();
		// 获取id
		int jid = act.getResources().getIdentifier(id, "id", getCurrentActivity().getPackageName());
		return jid;
	}

	public int intId2stringId(int id) {
		return getView(id).getId();
	}

	public void printCurrentWebElements() {
		for (WebElement webElement : getCurrentWebElements()) {
			// System.out.println("next element:");
			Logger.v("id: '" + webElement.getId() + "' text: '" + webElement.getText() + "' name: '"
					+ webElement.getName() + "' class name: '" + webElement.getClassName() + "' tag name: '"
					+ webElement.getTagName() + "'" + "--X:" + webElement.getLocationX() + " Y :  "
					+ webElement.getLocationY());
		}
	}

	public void printWebElements() {
		for (WebElement webElement : getWebElements()) {
			// System.out.println("next element:");
			Logger.v("id: '" + webElement.getId() + "' text: '" + webElement.getText() + "' name: '"
					+ webElement.getName() + "' class name: '" + webElement.getClassName() + "' tag name: '"
					+ webElement.getTagName() + "'" + "--X:" + webElement.getLocationX() + " Y :  "
					+ webElement.getLocationY());
		}
	}

	public void printWebElements(List<WebElement> webElements) {
		for (WebElement webElement : webElements) {
			// System.out.println("next element:");
			Logger.v("id: '" + webElement.getId() + "' text: '" + webElement.getText() + "' name: '"
					+ webElement.getName() + "' class name: '" + webElement.getClassName() + "' tag name: '"
					+ webElement.getTagName() + "'" + "--X:" + webElement.getLocationX() + " Y :  "
					+ webElement.getLocationY());
		}
	}

	public void printWebElements(WebElement webElement) {
		Logger.v("id: '" + webElement.getId() + "' text: '" + webElement.getText() + "' name: '" + webElement.getName()
				+ "' class name: '" + webElement.getClassName() + "' tag name: '" + webElement.getTagName() + "'"
				+ "--X:" + webElement.getLocationX() + " Y :  " + webElement.getLocationY());
	}

	public ArrayList<WebElement> getWebElements(By by) {
		ArrayList<WebElement> webElements = new ArrayList<WebElement>();
		Timer.start();
		
		while (!Timer.timeout()) {
			webElements.addAll(super.getWebElements(by));
			Logger.v("ArrayList<WebElement> getWebElements(By by)");
			if (webElements.size() > 0) {
				break;
			}
		}
		return webElements;
	}

	public List<WebElement> getWebElements(String Tagname) {
		List<WebElement> tartgetwebElements = new ArrayList<WebElement>();
		List<WebElement> webElements = getCurrentWebElements();
		for (WebElement webElement : webElements) {
			if (webElement.getTagName().equalsIgnoreCase(Tagname)) {
				tartgetwebElements.add(webElement);
			}
		}
		return tartgetwebElements;
	}

	public View getViewFromParentByid(View parentView, String id) {
		View target = null;
		ViewGroup p = null;

		try {
			p = (ViewGroup) parentView;
		} catch (Exception e) {
			Logger.v("getViewFromParentByid parentView:" + parentView.toString() + "is not the type of ViewGroup");
		}
		try {
			if (p != null) {
				int id_t = stringId2intId(id);
				target = p.findViewById(id_t);
			}
		} catch (Exception e) {
			Logger.v("getViewFromParentByid id:" + id + "is not include within parentView(" + parentView.toString()
					+ ")");
		}
		return target;
	}

	public List<View> getViewWithTextFromParent(View parentView, String text) {
		ViewGroup p = null;
		ArrayList<View> outViews = new ArrayList<View>();
		try {
			p = (ViewGroup) parentView;
		} catch (Exception e) {
			Logger.v("getViewWithTextFromParent parentView:" + parentView.toString() + "is not the type of ViewGroup");
		}
		try {
			if (p != null) {
				outViews.clear();
				p.findViewsWithText(outViews, text, View.FIND_VIEWS_WITH_TEXT);
			}
		} catch (Exception e) {
			Logger.v("getViewWithTextFromParent text:" + text + "is not include within parentView("
					+ parentView.toString() + ")");
		}
		return outViews;
	}

	// public ArrayList<WebElement>
	// getWebElementsFromWebElements(ArrayList<WebElement> webElements,String
	// xpath){
	// for(WebElement webElement : webElements){
	// // System.out.println("next element:");
	// Logger.v("ArrayList-joel", "id: '" + webElement.getId() + "' text: '" +
	// webElement.getText() + "' name: '" + webElement.getName() +
	// "' class name: '" + webElement.getClassName() + "' tag name: '" +
	// webElement.getTagName() + "'");
	// }
	// }
	public boolean compareBothViews(View v1, View v2) {

		Bitmap b1 = ViewUtil.cut(v1);
		Bitmap b2 = ViewUtil.cut(v2);
		return ImagComparation.compare(b1, b2, 1.0);
	}

}
