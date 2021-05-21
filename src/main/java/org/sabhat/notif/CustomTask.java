package org.sabhat.notif;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Schedules the scanning of slots and sending message (for every 15 minutes).
 * Run this class to schedule the scanCowinApp
 * @author sabhat
 *
 */
public class CustomTask extends TimerTask {
	private static final Logger logger = LogManager.getLogger(App.class);
	
	public static void main(String args[])
	{
		runTask();
	}

	public CustomTask() {

	}

	public void run() {
		try {

			App.scanCowinCalendar();

		} catch (Exception ex) {
			System.out.println("error running thread " + ex.getMessage());
		}
	}

	
	/**
	 * Its important to get the starting time correct for the schedule. 
	 * This method returns which quarter of the hour we are currently in.
	 * 
	 * @return
	 */
    private static Calendar getFirstTime() {
        Calendar cal = Calendar.getInstance();

        int currentMinute = cal.get(Calendar.MINUTE);

        if (currentMinute < 45) {
            cal.set(Calendar.MINUTE, 45);
        }
        if (currentMinute < 30) {
            cal.set(Calendar.MINUTE, 30);
        }
        if (currentMinute < 15) {
            cal.set(Calendar.MINUTE, 15);
        }
        if (currentMinute >= 45) {
            cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
            cal.set(Calendar.MINUTE, 0);
        }

        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    /**
     * Atomic method to schedule the task for every 15 minutes.
     */
	public static void runTask() {

		Calendar calendar = getFirstTime();

		Timer time = new Timer(); // Instantiate Timer Object
		logger.info("Scheduling task to scanCowinApp starting {}",calendar.getTime());
		long milliseconds = 1000 * 60 * 15; //15 minutes * 60 seconds per minute * 1000 milliseconds per second
		time.schedule(new CustomTask(), calendar.getTime(), milliseconds);
	}
}