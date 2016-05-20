package com.deppon.app.june.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.UiAutomation;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.deppon.app.june.util.AppConstants.Direction;

public class StrideAppUtil {

	private static Map<String, String> TEXTKEY_MAP = new HashMap<String, String>();

	static {
		TEXTKEY_MAP.put("0", "7");
		TEXTKEY_MAP.put("1", "8");
		TEXTKEY_MAP.put("2", "9");
		TEXTKEY_MAP.put("3", "10");
		TEXTKEY_MAP.put("4", "11");
		TEXTKEY_MAP.put("5", "12");
		TEXTKEY_MAP.put("6", "13");
		TEXTKEY_MAP.put("7", "14");
		TEXTKEY_MAP.put("8", "15");
		TEXTKEY_MAP.put("9", "16");
		TEXTKEY_MAP.put("a", "29");
		TEXTKEY_MAP.put("b", "30");
		TEXTKEY_MAP.put("c", "31");
		TEXTKEY_MAP.put("d", "32");
		TEXTKEY_MAP.put("e", "33");
		TEXTKEY_MAP.put("f", "34");
		TEXTKEY_MAP.put("g", "35");
		TEXTKEY_MAP.put("h", "36");
		TEXTKEY_MAP.put("i", "37");
		TEXTKEY_MAP.put("j", "38");
		TEXTKEY_MAP.put("k", "39");
		TEXTKEY_MAP.put("l", "40");
		TEXTKEY_MAP.put("m", "41");
		TEXTKEY_MAP.put("n", "42");
		TEXTKEY_MAP.put("o", "43");
		TEXTKEY_MAP.put("p", "44");
		TEXTKEY_MAP.put("q", "45");
		TEXTKEY_MAP.put("r", "46");
		TEXTKEY_MAP.put("s", "47");
		TEXTKEY_MAP.put("t", "48");
		TEXTKEY_MAP.put("u", "49");
		TEXTKEY_MAP.put("v", "50");
		TEXTKEY_MAP.put("w", "51");
		TEXTKEY_MAP.put("x", "52");
		TEXTKEY_MAP.put("y", "53");
		TEXTKEY_MAP.put("z", "54");
		TEXTKEY_MAP.put(",", "55");
		TEXTKEY_MAP.put(".", "56");
		TEXTKEY_MAP.put("`", "68");
		TEXTKEY_MAP.put("-", "69");
		TEXTKEY_MAP.put("=", "70");
		TEXTKEY_MAP.put("[", "71");
		TEXTKEY_MAP.put("]", "72");
		TEXTKEY_MAP.put("\\", "73");
		TEXTKEY_MAP.put(";", "74");
		TEXTKEY_MAP.put("'", "75");
		TEXTKEY_MAP.put("/", "76");
		TEXTKEY_MAP.put("@", "77");
		TEXTKEY_MAP.put("+", "81");
		TEXTKEY_MAP.put("*", "17");
		TEXTKEY_MAP.put("#", "18");
		TEXTKEY_MAP.put("A", "SHIFT_29");
		TEXTKEY_MAP.put("B", "SHIFT_30");
		TEXTKEY_MAP.put("C", "SHIFT_31");
		TEXTKEY_MAP.put("D", "SHIFT_32");
		TEXTKEY_MAP.put("E", "SHIFT_33");
		TEXTKEY_MAP.put("F", "SHIFT_34");
		TEXTKEY_MAP.put("G", "SHIFT_35");
		TEXTKEY_MAP.put("H", "SHIFT_36");
		TEXTKEY_MAP.put("I", "SHIFT_37");
		TEXTKEY_MAP.put("J", "SHIFT_38");
		TEXTKEY_MAP.put("K", "SHIFT_39");
		TEXTKEY_MAP.put("L", "SHIFT_40");
		TEXTKEY_MAP.put("M", "SHIFT_41");
		TEXTKEY_MAP.put("N", "SHIFT_42");
		TEXTKEY_MAP.put("O", "SHIFT_43");
		TEXTKEY_MAP.put("P", "SHIFT_44");
		TEXTKEY_MAP.put("Q", "SHIFT_45");
		TEXTKEY_MAP.put("R", "SHIFT_46");
		TEXTKEY_MAP.put("S", "SHIFT_47");
		TEXTKEY_MAP.put("T", "SHIFT_48");
		TEXTKEY_MAP.put("U", "SHIFT_49");
		TEXTKEY_MAP.put("V", "SHIFT_50");
		TEXTKEY_MAP.put("W", "SHIFT_51");
		TEXTKEY_MAP.put("X", "SHIFT_52");
		TEXTKEY_MAP.put("Y", "SHIFT_53");
		TEXTKEY_MAP.put("Z", "SHIFT_54");
		TEXTKEY_MAP.put("~", "SHIFT_68");
		TEXTKEY_MAP.put("!", "SHIFT_8");
		TEXTKEY_MAP.put("$", "SHIFT_11");
		TEXTKEY_MAP.put("%", "SHIFT_12");
		TEXTKEY_MAP.put("^", "SHIFT_13");
		TEXTKEY_MAP.put("&", "SHIFT_14");
		TEXTKEY_MAP.put("(", "SHIFT_16");
		TEXTKEY_MAP.put(")", "SHIFT_7");
		TEXTKEY_MAP.put("_", "SHIFT_69");
		TEXTKEY_MAP.put("{", "SHIFT_71");
		TEXTKEY_MAP.put("}", "SHIFT_72");
		TEXTKEY_MAP.put("|", "SHIFT_73");
		TEXTKEY_MAP.put(":", "SHIFT_74");
		TEXTKEY_MAP.put("\"", "SHIFT_75");
		TEXTKEY_MAP.put("?", "SHIFT_76");
		TEXTKEY_MAP.put("<", "SHIFT_55");
		TEXTKEY_MAP.put(">", "SHIFT_56");
	}

	/**
	 * 指定控件ID进行点击
	 * @param ui
	 * @param id
	 */
	public static void clickById(UiAutomation ui, String id) {
		clickById(ui, id, 0);
	}

	/**
	 * 指定控件ID进行点击
	 * @param ui
	 * @param id
	 * @param index
	 */
	public static void clickById(UiAutomation ui, String id, int index) {
		AccessibilityNodeInfo node = getNodeById(ui, id, index);
		Rect bounds = new Rect();
		node.getBoundsInScreen(bounds);
		clickByLocation(ui, (int) bounds.exactCenterX(), (int) bounds.exactCenterY());
	}

	/**
	 * 指定控件文本进行点击
	 * @param ui
	 * @param text
	 */
	public static void clickByText(UiAutomation ui, String text) {
		clickByText(ui, text, 0);
	}

	/**
	 * 指定控件文本进行点击
	 * @param ui
	 * @param text
	 * @param index
	 */
	public static void clickByText(UiAutomation ui, String text, int index) {
		AccessibilityNodeInfo node = getNodeByText(ui, text, index);
		Rect bounds = new Rect();
		node.getBoundsInScreen(bounds);
		clickByLocation(ui, (int) bounds.exactCenterX(), (int) bounds.exactCenterY());
	}

	/**
	 * 指定屏幕位置进行点击
	 * @param ui
	 * @param x
	 * @param y
	 */
	public static void clickByLocation(UiAutomation ui, int x, int y) {
		long eventTime = SystemClock.uptimeMillis();

		MotionEvent motionDown = MotionEvent.obtain(eventTime, eventTime, KeyEvent.ACTION_DOWN, x, y, 0);
		motionDown.setSource(InputDevice.SOURCE_TOUCHSCREEN);
		ui.injectInputEvent(motionDown, true);
		MotionEvent motionUp = MotionEvent.obtain(eventTime, eventTime, KeyEvent.ACTION_UP, x, y, 0);
		motionUp.setSource(InputDevice.SOURCE_TOUCHSCREEN);
		ui.injectInputEvent(motionUp, true);

		motionUp.recycle();
		motionDown.recycle();
	}

	/**
	 * 指定控件ID进行输入
	 * @param ui
	 * @param id
	 * @param text
	 */
	public static void inputById(UiAutomation ui, String id, String text) {
		inputById(ui, id, text, 0);
	}

	/**
	 * 指定控件ID进行输入
	 * @param ui
	 * @param id
	 * @param index 索引从0开始
	 */
	public static void inputById(UiAutomation ui, String id, String text, int index) {

		char[] cs = text.toCharArray();
		int iCode = 0;
		String sCode;
		boolean pressShift = false;
		KeyEvent evt;

		AccessibilityNodeInfo node = getNodeById(ui, id, index);
		node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);

		sleep(1000L);

		for (char c : cs) {
			sCode = TEXTKEY_MAP.get(String.valueOf(c));

			if (sCode.startsWith("SHIFT_")) {
				iCode = Integer.valueOf(sCode.substring(6));
				pressShift = true;
			} else {
				iCode = Integer.valueOf(sCode);
			}

			long eventTime = SystemClock.uptimeMillis();
			if (pressShift) {
				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT, 0);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, iCode, 0, 1);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, iCode, 0, 1);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT, 0);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				pressShift = false;
			} else {
				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, iCode, 0);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);
			}
		}
	}

	/**
	 * 指定屏幕位置进行输入
	 * @param ui
	 * @param x
	 * @param y
	 * @param index
	 */
	public static void inputByLocation(UiAutomation ui, int x, int y, String text) {

		char[] cs = text.toCharArray();
		int iCode = 0;
		String sCode;
		boolean pressShift = false;
		KeyEvent evt;

		clickByLocation(ui, x, y);

		sleep(1000L);

		for (char c : cs) {
			sCode = TEXTKEY_MAP.get(String.valueOf(c));

			if (sCode.startsWith("SHIFT_")) {
				iCode = Integer.valueOf(sCode.substring(6));
				pressShift = true;
			} else {
				iCode = Integer.valueOf(sCode);
			}

			long eventTime = SystemClock.uptimeMillis();
			if (pressShift) {
				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT, 0);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, iCode, 0, 1);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, iCode, 0, 1);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT, 0);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);

				pressShift = false;
			} else {
				evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, iCode, 0);
				evt.setSource(InputDevice.SOURCE_KEYBOARD);
				ui.injectInputEvent(evt, true);
			}
		}
	}

	/**
	 * 指定起始位置、终止位置与步数进行拖动
	 * @param ui
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @param stepCount
	 */
	public static void drag(UiAutomation ui, float fromX, float toX, float fromY, float toY, int stepCount) {
		Logger.v("fromX:" + fromX + ",toX:" + toX + "fromY:" + fromY + ",toY:" + toY + ",stepCount:" + stepCount);
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();

		float y = fromY;
		float x = fromX;

		float yStep = (toY - fromY) / stepCount;
		float xStep = (toX - fromX) / stepCount;

		MotionEvent evt = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, 0);
		evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
		ui.injectInputEvent(evt, true);
		Logger.v("yStep:" + yStep + ",xStep:" + xStep);
		for (int i = 0; i < stepCount; ++i) {
			y += yStep;
			x += xStep;
			evt = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
			evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
			ui.injectInputEvent(evt, true);
			Logger.v("y:" + y + ",x:" + x);
		}

		evt = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);
		evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
		ui.injectInputEvent(evt, true);
	}

	/**
	 * 指定方向进行滑动
	 * @param ui
	 * @param direction
	 */
	public static void swipe(UiAutomation ui, Direction direction) {
		swipe(ui, direction, 10);
	}

	/**
	 * 指定方向进行滑动
	 * @param ui
	 * @param direction
	 * @param delta
	 */
	public static void swipe(UiAutomation ui, Direction direction, int delta) {
		MotionEvent evt;
		Point size = AppConstants.SCREEN_SIZE;
		int width = size.x;
		int height = size.y;
		Logger.v("width:" + width);
		Logger.v("height:" + height);

		long downTime = SystemClock.uptimeMillis();
		if (direction.ordinal() < 2) {
			Logger.e(direction.toString());
			float xStart = ((direction == Direction.Left) ? (width - delta) : delta);
			float xEnd = ((direction == Direction.Left) ? delta : (width - delta));

			// The value for y doesn't change, as we want to swipe straight
			// across
			evt = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, xStart, size.y / 2,
					0);
			evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
			ui.injectInputEvent(evt, true);

			evt = MotionEvent
					.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, xEnd, size.y / 2, 0);
			evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
			ui.injectInputEvent(evt, true);

			evt = MotionEvent.obtain(downTime, SystemClock.uptimeMillis() + 1000, MotionEvent.ACTION_UP, xEnd,
					size.y / 2, 0);
			evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
			ui.injectInputEvent(evt, true);
		} else {
			Logger.e(direction.toString());
			float yStart = ((direction == Direction.Top) ? (height - delta) : delta);
			float yEnd = ((direction == Direction.Top) ? delta : (height - delta));
			Logger.v("yStart:" + yStart);
			Logger.v("yEnd:" + yEnd);
			// The value for x doesn't change, as we want to swipe straight
			// across
			evt = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, size.x / 2, yStart,
					0);
			evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
			ui.injectInputEvent(evt, true);

			evt = MotionEvent
					.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, size.x / 2, yEnd, 0);
			evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
			ui.injectInputEvent(evt, true);

			evt = MotionEvent.obtain(downTime, SystemClock.uptimeMillis() + 1000, MotionEvent.ACTION_UP, size.x / 2,
					yEnd, 0);
			evt.setSource(InputDevice.SOURCE_TOUCHSCREEN);
			ui.injectInputEvent(evt, true);
		}

	}

	/**
	 * 全屏查找文本是否存在
	 * @param ui
	 * @param text
	 * @return boolean
	 */
	public static boolean searchText(UiAutomation ui, String text) {

		AccessibilityNodeInfo root = null;
		Timer.start();
		while (!Timer.timeout()) {
			root = ui.getRootInActiveWindow();
			if (root != null) {
				break;
			}
		}
		List<AccessibilityNodeInfo> nodes = getAllChildNodes(root);

		for (AccessibilityNodeInfo node : nodes) {
			if (node.getText() != null && node.getText().toString().equals(text)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 指定ID清空控件文本内容
	 * @param ui
	 * @param id
	 */
	public static void clearById(UiAutomation ui, String id) {
		clearById(ui, id, 0);
	}

	/**
	 * 指定ID清空控件文本内容
	 * @param ui
	 * @param id
	 * @param index
	 */
	public static void clearById(UiAutomation ui, String id, int index) {

		KeyEvent evt;

		AccessibilityNodeInfo node = getNodeById(ui, id, index);
		node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);

		sleep(1000L);

		String text = node.getText().toString();
		for (int i = 0; i < text.length(); i++) {
			long eventTime = SystemClock.uptimeMillis();

			evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL, 0);
			evt.setSource(InputDevice.SOURCE_KEYBOARD);
			ui.injectInputEvent(evt, true);

			evt = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL, 0);
			evt.setSource(InputDevice.SOURCE_KEYBOARD);
			ui.injectInputEvent(evt, true);
		}
	}

	/**
	 * 获取指定节点下的全部节点
	 * @param parent
	 * @return List<AccessibilityNodeInfo>
	 */
	private static List<AccessibilityNodeInfo> getAllChildNodes(AccessibilityNodeInfo parent) {

		List<AccessibilityNodeInfo> nodes = new ArrayList<AccessibilityNodeInfo>();
		AccessibilityNodeInfo child = null;
		int count = parent.getChildCount();

		if (count == 0) {
			nodes.add(parent);
			return nodes;
		} else {
			nodes.add(parent);
		}

		for (int i = 0; i < count; i++) {
			Timer.start();
			while (!Timer.timeout()) {
				try {
					child = parent.getChild(i);
				} catch (Exception e) {
					Logger.e(e);
				}
				if (child != null) {
					break;
				}
			}

			if (child.getChildCount() > 0) {
				nodes.addAll(getAllChildNodes(child));
			} else {
				nodes.add(child);
			}
		}
		return nodes;

	}

	/**
	 * 指定ID获取节点
	 * @param ui
	 * @param id
	 * @param index
	 * @return AccessibilityNodeInfo
	 */
	public static AccessibilityNodeInfo getNodeById(UiAutomation ui, String id, int index) {
		Timer.start();

		AccessibilityNodeInfo node = null;
		while (!Timer.timeout()) {
			try {
				node = ui.getRootInActiveWindow().findAccessibilityNodeInfosByViewId(id).get(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (node != null) {
				break;
			}
		}

		return node;
	}

	/**
	 * 指定文本获取节点（精确查找）
	 * @param ui
	 * @param text
	 * @param index
	 * @return
	 */
	public static AccessibilityNodeInfo getNodeByText(UiAutomation ui, String text, int index) {
		Timer.start();

		AccessibilityNodeInfo node = null;
		while (!Timer.timeout()) {
			try {
				node = ui.getRootInActiveWindow().findAccessibilityNodeInfosByText(text).get(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (node != null) {
				break;
			}
		}

		return node;
	}

	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定文本查找包含此文本的全部节点（支持带%的模糊查找）
	 * 
	 * @param ui
	 * @param text
	 * @return List<AccessibilityNodeInfo>
	 */
	public static List<AccessibilityNodeInfo> getNodebyText(UiAutomation ui, String text) {
		List<AccessibilityNodeInfo> targetnodes = new ArrayList<AccessibilityNodeInfo>();
		if (text.isEmpty()) {
			return targetnodes;
		}
		int searchType = 0;
		if (text.startsWith("%") && text.endsWith("%")) {
			searchType = 1;
		} else if (text.startsWith("%")) {
			searchType = 2;
		} else if (text.endsWith("%")) {
			searchType = 3;
		}
		AccessibilityNodeInfo root = ui.getRootInActiveWindow();
		List<AccessibilityNodeInfo> nodes = getAllChildNodes(root);
		for (AccessibilityNodeInfo node : nodes) {
			if (node.getText() != null && !node.getText().toString().isEmpty()) {
				switch (searchType) {
				case 0:
					if (node.getText().toString().equals(text)) {
						targetnodes.add(node);
					}
					break;
				case 1:
					if (node.getText().toString().indexOf(text) != -1) {
						targetnodes.add(node);
					}
					break;
				case 2:
					if (node.getText().toString().endsWith(text)) {
						targetnodes.add(node);
					}
					break;
				case 3:
					if (node.getText().toString().startsWith(text)) {
						targetnodes.add(node);
					}
					break;
				}
			}
		}
		return targetnodes;
	}

	/**
	 * 根据文本查询包含此文本的全部文本（支持带%的模糊查询）
	 * 
	 * @param ui
	 * @param text
	 * @return List<AccessibilityNodeInfo>
	 */
	public static List<String> searchTextOpaque(UiAutomation ui, String text) {
		List<String> strings = new ArrayList<String>();
		if (text.isEmpty()) {
			return strings;
		}
		int searchType = 0;
		if (text.startsWith("%") && text.endsWith("%")) {
			searchType = 1;
		} else if (text.startsWith("%")) {
			searchType = 2;
		} else if (text.endsWith("%")) {
			searchType = 3;
		}
		AccessibilityNodeInfo root = ui.getRootInActiveWindow();
		List<AccessibilityNodeInfo> nodes = getAllChildNodes(root);
		for (AccessibilityNodeInfo node : nodes) {
			if (node.getText() != null && !node.getText().toString().isEmpty()) {
				switch (searchType) {
				case 0:
					if (node.getText().toString().equals(text)) {
						strings.add(node.getText().toString());
					}
					break;
				case 1:
					if (node.getText().toString().indexOf(text) != -1) {
						strings.add(node.getText().toString());
					}
					break;
				case 2:
					if (node.getText().toString().endsWith(text)) {
						strings.add(node.getText().toString());
					}
					break;
				case 3:
					if (node.getText().toString().startsWith(text)) {
						strings.add(node.getText().toString());
					}
					break;
				}
			}
		}
		return strings;
	}
	/**
	 * 根据文本查询包含此文本的全部文本（支持正则表达式的模糊查询）
	 * 
	 * @param ui
	 * @param text
	 * @return List<AccessibilityNodeInfo>
	 */
	public static List<AccessibilityNodeInfo> getNodebyOpaqueText(UiAutomation ui, String text) {
		List<AccessibilityNodeInfo> targetnodes = new ArrayList<AccessibilityNodeInfo>();
		if(text.isEmpty()){
			return targetnodes;
		}
		String head = CommonUtil.detectRegex(text.charAt(0));
		String end = CommonUtil.detectRegex(text.charAt(text.length()-1));
		String regex = head+text+end;
		Logger.v(regex);
		Pattern pattern = Pattern.compile(regex);
		AccessibilityNodeInfo root = ui.getRootInActiveWindow();
		List<AccessibilityNodeInfo> nodes = getAllChildNodes(root);
//		Pattern pattern = Pattern.compile("[0-9]{1,3}");
		for (AccessibilityNodeInfo node : nodes) {
			if (node.getText()!=null && !node.getText().toString().isEmpty()) {
				Matcher matcher = pattern.matcher(node.getText().toString());
				while(matcher.find()){
					targetnodes.add(node);
					continue;
				}
			}
		}
		return targetnodes;
	}
	
	/**
	 * 根据正则表达式获取屏幕文本
	 * @param ui
	 * @param regex
	 * @return List<String>
	 */
	public static List<String> searchTextRegex(UiAutomation ui, String regex) {
		List<String> strings = new ArrayList<String>();
		if (regex.isEmpty()) {
			return strings;
		}
		Pattern pattern = Pattern.compile(regex);
		AccessibilityNodeInfo root = ui.getRootInActiveWindow();
		List<AccessibilityNodeInfo> nodes = getAllChildNodes(root);
		for (AccessibilityNodeInfo node : nodes) {
			if (node.getText() != null && !node.getText().toString().isEmpty()) {
				Matcher matcher = pattern.matcher(node.getText().toString());
				while (matcher.find()) {
					strings.add(matcher.group());
				}
			}
		}
		return strings;
	}
}
