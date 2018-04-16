/*
 * 文 件 名:  TimerTaskManager.java
 * 版    权:  3G
 * 描    述:  <描述>
 * 修 改 人:  caoshilei
 * 修改时间:  2014-12-20
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.androidtest.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * <br>类描述:定时任务管理器
 * <br>功能详细描述:
 * 
 * @author  caoshichao
 * @date  [2014-12-20]
 */
public class TimerTaskManager {    
	/** 创建一个定时器，定时器为守护线程*/
	private static Timer sTimer = new Timer(true);

	/** <br>功能简述:指定时间执行定时任务
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param task
	 * @param when
	 */
	public static void scheduleTimerTask(TimerTask task, Date when) {
		sTimer.schedule(task, when);		
	}

	/** <br>功能简述:指定时间后开始周期执行定时任务
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param task
	 * @param when
	 * @param period
	 */
	public static void scheduleTimerTask(TimerTask task, Date when, long period) {
		sTimer.schedule(task, when, period);
	}

	/** <br>功能简述:执行延时任务
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param task
	 * @param delay
	 */
	public static void scheduleTimerTask(TimerTask task, long delay) {
		sTimer.schedule(task, delay);
	}

	/** <br>功能简述:延迟一段时间周期执行定时任务
	 * <br>功能详细描述:
	 * <br>注意:
	 * @param task
	 * @param delay
	 * @param period
	 */
	public static void scheduleTimerTask(TimerTask task, long delay, long period) {
		sTimer.schedule(task, delay, period);
	}
	
	/** <br>功能简述:取消所有定时任务
	 * <br>功能详细描述:
	 * <br>注意:
	 */
	public static void cancelAllTimerTask() {
		sTimer.cancel();
	}
}
