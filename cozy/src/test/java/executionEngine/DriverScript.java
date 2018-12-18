package executionEngine;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Properties;
import javax.activation.*;


import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.activation.*;

import main.ActionKeywords;
import main.Constants;
import utility.ExcelUtils;

public class DriverScript {

	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	public static boolean bResult;
	public static int count, counter;

	public DriverScript() throws NoSuchMethodException, SecurityException {
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();

	}

	public static void main(String[] args) throws Exception {

		Instant start = Instant.now();
		String instant = start.toString();
		String execution_start_time_hh = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
		String execution_start_time_mm = DateTimeFormatter.ofPattern("mm").format(LocalDateTime.now());
		String execution_start_time_ss = DateTimeFormatter.ofPattern("ss").format(LocalDateTime.now());
		String execution_start_time_SS = DateTimeFormatter.ofPattern("SS").format(LocalDateTime.now());
		String execution_start_time = DateTimeFormatter.ofPattern("HH:mm:ss:SS").format(LocalDateTime.now());

		System.out.println(start);
		System.out.println(execution_start_time);
		System.out.println("Epoch is " + Instant.now().getEpochSecond());

		final String username = "keviv8@gmail.com";
		final String password = "aditiramkolla";

		DOMConfigurator.configure("log4j.xml");
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		String sPath = "C:\\Users\\vkolla\\.jenkins\\workspace\\Cozy-Daily-Regression\\DataEngine1.xlsx";
		ExcelUtils.setExcelFile(sPath, Constants.Path_TestData);
		// System.out.println("I'm at Path_TestData");
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\vkolla\\Downloads\\chromedriver_win32\\chromedriver.exe");
		// String Path_OR = Constants.Path_OR;
		// FileInputStream fs = new FileInputStream(Path_OR);
		OR = new Properties(System.getProperties());
		// OR.load(fs);
		// System.out.println("I'm at load(fs)");
		DriverScript startEngine = new DriverScript();
		// startEngine.SendMail();
		startEngine.execute_TestCase();
		// startEngine.SendMail();
		System.out.println("\nYou have run this program " + count + " number of times");
		count++;
		// Thread.sleep(99900);
		String execution_end_time_HH = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
		String execution_end_time_mm = DateTimeFormatter.ofPattern("mm").format(LocalDateTime.now());
		String execution_end_time_ss = DateTimeFormatter.ofPattern("ss").format(LocalDateTime.now());
		String execution_end_time_SS = DateTimeFormatter.ofPattern("SS").format(LocalDateTime.now());
		String execution_end_time = DateTimeFormatter.ofPattern("HH:mm:ss:SS").format(LocalDateTime.now());
		int hours = Integer.parseInt(execution_end_time_HH) - Integer.parseInt(execution_start_time_hh);
		int minutes = Integer.parseInt(execution_end_time_mm) - Integer.parseInt(execution_start_time_mm);
		int seconds = Integer.parseInt(execution_end_time_ss) - Integer.parseInt(execution_start_time_ss);
		int milliseconds = Integer.parseInt(execution_end_time_SS) - Integer.parseInt(execution_start_time_SS);
		System.out.println(execution_end_time);
		// System.out.println("Execution time: " + hours + "hours " + minutes + "minutes
		// " + seconds + "seconds "
		// + milliseconds + "milliseconds");
		// if (execution_start_time_hh.equals(execution_end_time_HH)
		// & execution_end_time_mm.equals(execution_start_time_mm)) {
		// System.out.println("from this 2nd option guy");
		// System.out.println(
		// "Execution time: " + minutes + "minutes " + seconds + "seconds " +
		// milliseconds + "milliseconds");
		// } else if (!execution_start_time_hh.equals(execution_end_time_HH)) {
		// System.out.println("from this 2nd option guy in elseif");
		// System.out.println(
		// "Execution time: " + minutes + "minutes " + seconds + "seconds " +
		// milliseconds + "milliseconds");
		// }
		Instant end = Instant.now();
		System.out.println(end);
		Duration timeElapsed = Duration.between(start, end);
		if (timeElapsed.toMillis() < 60000) {
			System.out.println("Execution time: " + timeElapsed.toMillis() / 1000 + " seconds");
		} else if (timeElapsed.toMillis() >= 60000 & timeElapsed.toMillis() < 3599000) {
			System.out.println("Execution time: " + timeElapsed.toMinutes() + " minute(s) "
					+ ((timeElapsed.toMillis() - (timeElapsed.toMinutes() * 60000)) / 1000) + " seconds");
		} else if (timeElapsed.toMillis() >= 3599000) {
			System.out.println("Execution time: " + timeElapsed.toHours() + " hour(s)"
					+ ((timeElapsed.toMillis() - 3599000) / 60000) + " minutes "
					+ ((timeElapsed.toMillis() - 120000) / 1000) + " seconds");
		}

		if (timeElapsed.toHours() == 0) {
			if (timeElapsed.toMinutes() == 0) {
				System.out.println("New Execution time is: " + timeElapsed.toMillis() / 1000 + " seconds");
			} else if (timeElapsed.toMinutes() > 0) {
				System.out.println("New Execution time is: " + timeElapsed.toMinutes() + " minutes and "
						+ ((timeElapsed.toMinutes() * 60) - timeElapsed.toMillis()) / 1000 + " seconds");
			}
		} else if (timeElapsed.toHours() > 0) {
			System.out.println("New Execution time is: " + timeElapsed.toHours() + " hours and "
					+ timeElapsed.toMinutes() / 1000 + " minutes");
		}
		System.out.println("Time taken in milliseconds: " + timeElapsed.toMillis() + " milliseconds");
		// System.out.println("It took "+(execution_end_time-execution_start_time));
	}

	private void execute_TestCase() throws Exception {
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);

		Constants.cashcadeenv = ExcelUtils.getCellData(0, 1, Constants.cashcade);
		Constants.cclabel = ExcelUtils.getCellData(1, 1, Constants.cashcade);
		Constants.ccbrand = Constants.cclabel.replace(" Bingo", "").toLowerCase();
		if (Constants.cclabel.equalsIgnoreCase("FOXY BINGO")) {
			if (Constants.cashcadeenv.equalsIgnoreCase("QA1")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(1, 2, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(2, 2, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(3, 2, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("QA2")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(1, 3, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(2, 3, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(3, 3, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("E7")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(1, 4, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(2, 4, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(3, 4, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("Beta")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(1, 5, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(2, 5, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(3, 5, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("Live")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(1, 6, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(2, 6, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(3, 6, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} else if (Constants.cclabel.equalsIgnoreCase("CHEEKY BINGO")) {
			if (Constants.cashcadeenv.equalsIgnoreCase("QA1")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(4, 2, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(5, 2, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(6, 2, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("QA2")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(4, 3, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(5, 3, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(6, 3, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("E7")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(4, 4, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(5, 4, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(6, 4, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("Beta")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(4, 5, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(5, 5, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(6, 5, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}if (Constants.cashcadeenv.equalsIgnoreCase("Live")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(4, 6, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(5, 6, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(6, 6, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// System.out.println("Foxy URLs
		// are\n"+Constants.foxyhome+"\n"+Constants.foxycasino+"\n"+Constants.foxyoffers);
		for (int iTestcase = 1; iTestcase <= iTotalTestCases; iTestcase++) {
			// System.out.println(method);
			bResult = true;

			sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
			count = 0;
			count = ExcelUtils.getRegistrationsCount(Constants.Col_CountRow, Constants.Col_CountCol,
					Constants.Sheet_TestCases);
			// System.out.println("Count value from sheet is :"+count);
			// System.out.println("just retrieved count value and it is");
			// System.out.print(count);
			// ExcelUtils.setRegistrationsCount(count);
			counter = count;
			count++;
			ExcelUtils.setRegistrationsCount(count);
			sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);
			if (sRunMode.equals("Yes")) {
				iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				bResult = true;
				for (; iTestStep < iTestLastStep; iTestStep++) {
					sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,
							Constants.Sheet_TestSteps);
					String ActionKeywordText = sActionKeyword.toString();
					bResult = true;
					// System.out.println("\n");
					// System.out.println(sTestCaseID);
					// System.out.println(iTestStep);
					System.out.print(counter);
					System.out.println(sActionKeyword);
					// for (int iRow = 1; iRow <= 8; iRow++) {
					// String sActionKeyword = ExcelUtils.getCellData(iTestStep, 3, "Test Cases");
					// System.out.println(sActionKeyword);
					sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject,
							Constants.Sheet_TestSteps);
					sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);

					// for(int i=0;i<)

					for (int i = 0; i < method.length; i++) {

						if (method[i].getName().equals(sActionKeyword)) {
							method[i].invoke(actionKeywords, sData);
							if (bResult == true) {
								ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult,
										Constants.Sheet_TestSteps);
								ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Col_TestCaseResult,
										Constants.Sheet_TestCases);
								// ExcelUtils.setRegistrationsCount(count);
								break;
							} else {
								ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult,
										Constants.Sheet_TestSteps);
								ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Col_TestCaseResult,
										Constants.Sheet_TestCases);
								// ExcelUtils.setRegistrationsCount(count);
								// ActionKeywords.closeBrowser("","");
								break;
							}
						}

					}
				}

			}
		}

	}

	
}
