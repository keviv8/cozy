package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.text.*;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.html.MinimalHTMLWriter;

import static executionEngine.DriverScript.OR;

import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.WordUtils;
//import org.apache.commons.text.WordUtilsTest;
import org.apache.http.util.ExceptionUtils;
//import org.apache.http.util.TextUtils;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
//import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.usermodel.DateAndTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService.Builder;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;

import com.jcraft.jsch.Logger;
import com.melloware.jintellitype.Main;

//import Flash.FlashObjectWebDriver;
import executionEngine.DriverScript;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utility.ExcelUtils;
import utility.Log;

public class ActionKeywords {

	public static WebDriver driver;
	public static Instant start;
	public static Class actionKeywords;
	public static Instant end;
	public static Method[] method;
	public static Set<Cookie> allCookies = null;
	public Screen s;
	public static String datalog = null;
	public static boolean iopenattheclose = false;
	public static Boolean tResult;

	static {

		datalog = "Log";

	}

	@Test
	public static String captureScreen() {
		String path;
		String directorypath;
		try {
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year = localDate.getYear();
			Month month = localDate.getMonth();
			int day = localDate.getDayOfMonth();
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			directorypath = "C:\\Users\\vkolla\\Documents\\SeleniumShotsNew\\" + year + "\\" + month + "\\" + day
					+ "\\";
			// System.out.println(directorypath);
			new File(directorypath).mkdirs();
			path = directorypath + source.getName();
			FileUtils.copyFile(source, new File(path));
			// if(data!=null)
			// new File(directorypath).renameTo(data);
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
		return path;
	}

	public static String captureScreenWithFilename(String data) {
		String path = null;
		String directorypath;
		try {
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year = localDate.getYear();
			Month month = localDate.getMonth();
			int day = localDate.getDayOfMonth();
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			directorypath = "C:\\Users\\vkolla\\Documents\\SeleniumShotsNew\\" + year + "\\" + month + "\\" + day
					+ "\\";
			// System.out.println(directorypath);
			new File(directorypath).mkdirs();
			path = directorypath + data + "_" + Instant.now().getEpochSecond() + ".PNG";
			FileUtils.copyFile(source, new File(path));
			// if(data!=null)
			// new File(directorypath).renameTo(data);
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
		return path;
	}

	public static long captureScreenSize(String data) {
		String path = null;
		String directorypath;
		long size = 0;
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		Month month = localDate.getMonth();
		int day = localDate.getDayOfMonth();
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		directorypath = "C:\\Users\\vkolla\\Documents\\SeleniumShotsNew\\" + year + "\\" + month + "\\" + day + "\\";
		// System.out.println(directorypath);
		new File(directorypath).mkdirs();
		path = directorypath + data + rand(5) + ".PNG";
		// FileUtils.copyFile(source, new File(path));
		size = source.length();
		// if(data!=null)
		// new File(directorypath).renameTo(data);
		// return path;
		return size;
	}

	public static int rand(int i) {
		String zeros = "9";
		for (int n = 1; n < i; n++) {
			zeros = zeros + "0";
			// System.out.println(zeros);
		}
		int cz = Integer.parseInt(zeros);
		return ((int) ((long) Math.floor(Math.random() * cz) + cz));
	}

	public static void ashot() throws IOException {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		Month month = localDate.getMonth();
		int day = localDate.getDayOfMonth();
		final Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		final Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2))
				.takeScreenshot(driver);
		String directorypath = "C:\\Users\\vkolla\\Documents\\SeleniumaShots\\" + year + "\\" + month + "\\" + day
				+ "\\";
		// ImageIO.write(screenshot.getImage(), "PNG",
		// new File("C:\\Users\\vkolla\\Documents\\SeleniumShots\\" +
		// System.currentTimeMillis() + ".JPG"));
		new File(directorypath).mkdirs();
		ImageIO.write(screenshot.getImage(), "PNG",
				new File(directorypath + "\\" + System.currentTimeMillis() + ".JPG"));

	}

	public static void openBrowser(String data) {
		try {

			if (data.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.state.flash", 1); // # 1->allow
				prefs.put("profile.default_content_setting_values.plugins", 1);
				prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
				prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
				prefs.put("PluginsAllowedForUrls", Constants.URL);
				options.addArguments("start-maximized");
				options.addArguments("incognito");
				options.addArguments("disable-infobars");
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				Constants.parentwindow = driver.getWindowHandle();
			} else if (data.equals("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				// options.addArguments("start-maximized");
				// options.addArguments("incognito");
				driver = new FirefoxDriver(options);
				driver.manage().window().maximize();
			} else if (data.equals("Edge")) {
				System.setProperty("webdriver.edge.driver",
						"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\MicrosoftWebDriver.exe");
				driver.manage().window().maximize();
				driver.manage().window();
				driver = new EdgeDriver();
			} else {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.state.flash", 1); // # 1->allow
				prefs.put("profile.default_content_setting_values.plugins", 1);
				prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
				prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
				prefs.put("PluginsAllowedForUrls", Constants.URL);
				options.addArguments("start-maximized");
				options.addArguments("incognito");
				options.addArguments("disable-infobars");
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				Constants.parentwindow = driver.getWindowHandle();
			}
			// System.out.println("I almost opened Chrome");
			// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			// WebDriver augmentedDriver = new Augmenter().augment(driver);
			// File screenshot = ((TakesScreenshot)
			// augmentedDriver).getScreenshotAs(OutputType.FILE);
		} catch (WebDriverException e) {
			DriverScript.bResult = false;
			// System.out.println("in catch block");
			System.out.print(DriverScript.bResult);

		}

	}

	public static float timediff(Instant from, Instant to) {
		return (float) ((float) (Duration.between(from, to).toMillis()) / (float) 1000);
	}

	public static void browseClient(String data) {
		try {
			System.out.print(data);
			Constants.URL = data;
			Constants.parentwindow = driver.getWindowHandle();
			driver.manage().deleteAllCookies();
			Instant pagehit = Instant.now();
			driver.get(data);
			System.out.println("Page load time " + Duration.between(pagehit, Instant.now()).toMillis()
					+ " in milli seconds and "
					+ new DecimalFormat("##.##").format(Duration.between(pagehit, Instant.now()).toMillis() / 1000)
					+ " in seconds");
			System.out.println(
					"Page load time " + Duration.between(pagehit, Instant.now()).toMillis() + " in milli seconds and "
							+ (float) ((float) (Duration.between(pagehit, Instant.now()).toMillis()) / (float) 1000)
							+ " in float seconds");
			dataEngineLog(data + " Page load time is "
					+ (float) ((float) (Duration.between(pagehit, Instant.now()).toMillis()) / (float) 1000)
					+ " in float seconds");
			List<WebElement> hrefs = driver.findElements(By.xpath("//a"));
			try {
				driver.findElement(By.xpath("//*[@class='agree-button']")).click();
			} catch (Exception e) {
			}
			System.out.println("There are " + hrefs.size() + " number of links");
			Constants.serviceURL = getServiceURL();
			// Constants.handle1 = driver.getWindowHandle();
			// System.out.printf("\n\n" + Constants.handle1);
			// captureScreen();
			// int firstperiod = Constants.URL.indexOf(".");
			// int secondperiod = Constants.URL.indexOf(".", firstperiod + 1);
			// String labelname = Constants.URL.substring(firstperiod + 1, secondperiod);
			// System.out.println(labelname);
			// System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
			// int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
			// String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
			// // String adminpass1=Constants.admin_password_list[];
			// System.out.println(len);
			// System.out.println(adminpass);

		} catch (Exception e) {
			DriverScript.bResult = false;
		}
	}

	public static WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				element);
		return ele;
	}

	public static float defaultBrowseClient(String data) {
		float timetaken = 0;
		try {
			System.out.println(DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now()));
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe"); /// last update - nov
																								/// 6th, driver version
																								/// 2.43
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			// options.addArguments("incognito");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			// options.addArguments("--mute-audio");
			// options.addExtensions((new
			// File("C:\\Users\\vkolla\\Downloads\\ModHeader.crx")));
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.state.flash", 1); // # 1->allow
			prefs.put("profile.default_content_setting_values.plugins", 1);
			prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("PluginsAllowedForUrls", data);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);

			// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			Constants.parentwindow = driver.getWindowHandle();
			Constants.URL = data;
			System.out.print(data);
			driver.manage().deleteAllCookies();

			// driver.navigate().to("chrome://extensions/?id=idgpnmonknjnojddfkpgkljpfnnfcklj");
			// driver.get("chrome://extensions/?id=idgpnmonknjnojddfkpgkljpfnnfcklj");
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("localStorage.setItem('profiles', JSON.stringify([{ "
			// + " title: 'Selenium', hideComment: true, appendMode: '', "
			// + " headers: [ "
			// + " {enabled: true, name: 'X-Forwarded-For', value: '245.43.38.111', comment:
			// 'Foxy'}, "
			// + "]," + "respHeaders:[]," + "filters:[]" + "}]));");

			Instant pagehit = Instant.now();

			//////// TO CLEAR BROWSER CACHE ///////////////////

			// driver.get("chrome://settings/clearBrowserData");
			// WebElement settingsui = driver.findElement(By.tagName("settings-ui"));
			// WebElement shadow1 = expandRootElement(settingsui);
			// WebElement settingsmain = shadow1.findElement(By.tagName("settings-main"));
			// WebElement shadow2 = expandRootElement(settingsmain);
			// WebElement settingsbasicpage =
			// shadow2.findElement(By.tagName("settings-basic-page"));
			// WebElement shadow3 = expandRootElement(settingsbasicpage);
			// WebElement settingssection =
			// shadow3.findElement(By.tagName("settings-section"));
			// WebElement shadow4 = expandRootElement(settingssection);
			// WebElement settingsprivacypage =
			// shadow4.findElement(By.tagName("settings-privacy-page"));
			// WebElement shadow5 = expandRootElement(settingsprivacypage);
			// WebElement settingsclearbrowsingdatadialog =
			// shadow5.findElement(By.tagName("settings-clear-browsing-data-dialog"));
			// WebElement shadow6 = expandRootElement(settingsclearbrowsingdatadialog);
			// WebElement crdialogue = shadow6.findElement(By.tagName("cr-dialogue"));
			// WebElement shadow7 = expandRootElement(crdialogue);
			// WebElement ironpages = shadow7.findElement(By.tagName("iron-pages"));
			// WebElement shadow8 = expandRootElement(ironpages);
			// WebElement settingsdropdownmenu =
			// shadow8.findElement(By.tagName("settings-dropdown-menu"));
			// WebElement shadow9 = expandRootElement(settingsdropdownmenu);
			// WebElement timerange = shadow9.findElement(By.id("dropdownMenu"));
			// Select timer = new Select(timerange);
			// timer.selectByIndex(4);

			////// TO CLEAR BROWSER CACHE ///////////////////

			driver.get(data);
			iopenattheclose = true;
			System.out.println("Page load time " + Duration.between(pagehit, Instant.now()).toMillis()
					+ " in milli seconds and "
					+ new DecimalFormat("##.##").format(Duration.between(pagehit, Instant.now()).toMillis() / 1000)
					+ " in seconds");
			System.out.println(
					"Page load time " + Duration.between(pagehit, Instant.now()).toMillis() + " in milli seconds and "
							+ (float) ((float) (Duration.between(pagehit, Instant.now()).toMillis()) / (float) 1000)
							+ " in float seconds");

			dataEngineLog(data + " Page load time is "
					+ (float) ((float) (Duration.between(pagehit, Instant.now()).toMillis()) / (float) 1000)
					+ " in float seconds");

			timetaken = (float) ((float) (Duration.between(pagehit, Instant.now()).toMillis()) / (float) 1000);
			List<WebElement> hrefs = driver.findElements(By.xpath("//a"));
			try {
				driver.findElement(By.xpath("//*[@class='agree-button']")).click();
			} catch (Exception e) {
			}
			// System.out.println("There are " + hrefs.size() + " links");
			Constants.serviceURL = getServiceURL();

			// System.out.println("Foxy URLs are\n" + foxyhome + "\n" + foxycasino + "\n" +
			// foxyoffers);

			// String links;
			// Set<String> all_links = null;
			// for (WebElement hreftext : hrefs) {
			// // System.out.println(hreftext.getAttribute("href"));
			// // links = hreftext.getAttribute("href");
			// // all_links.add(hreftext.getAttribute("href"));
			//
			// }
			// System.out.println(all_links);
			// driver.close();
		} catch (

		Exception e) {
			e.printStackTrace();
			DriverScript.bResult = false;
		}
		captureScreenWithFilename(getLabel(data));// End of method screenshot
		return timetaken;

	}

	public static void getPageLoadTimes(String data) throws Exception {
		String log;
		heuristicLogin(data);
		int free;
		for (free = 0; !ExcelUtils.getCellData(0, free, Constants.Sheet_Performance).isEmpty(); free++) {
		}
		System.out.println(free + " column is empty");
		ExcelUtils.setCellData(getLabel(data), 0, free, Constants.Sheet_Performance);
		for (int i = 1; !ExcelUtils.getCellData(i, 0, Constants.Sheet_Performance).isEmpty(); i++) {
			if (!ExcelUtils.getCellData(i, 1, Constants.Sheet_Performance).isEmpty()) {
				System.out.println(Constants.URL + ExcelUtils.getCellData(i, 2, Constants.Sheet_Performance));
				Instant pagehittime = Instant.now();
				driver.get(Constants.URL + ExcelUtils.getCellData(i, 2, Constants.Sheet_Performance));
				System.out.println("Page load time "
						+ (float) ((float) Duration.between(pagehittime, Instant.now()).toMillis() / (float) 1000)
						+ " seconds");
				ExcelUtils.setCellData(
						(float) ((float) Duration.between(pagehittime, Instant.now()).toMillis() / (float) 1000)
								+ " seconds",
						i, free, Constants.Sheet_Performance);
			}
		}
	}

	public static void getBingoGameLoadTime(String data) throws Exception {
		launchBingoRoom(data);
		if (!driver.getWindowHandle().toString().equals(Constants.parentwindow))
			driver.close();
		System.out.println("Bingo Room was launched at " + Constants.bingolaunchtime);
		System.out.println("Bingo Room loading was completed at " + Constants.bingoloadcompletetime);
		System.out.println(
				(float) (Duration.between(Constants.bingolaunchtime, Constants.bingoloadcompletetime).toMillis()) / 1000
						+ " seconds");
		dataEngineLog(String.valueOf(
				(float) (Duration.between(Constants.bingolaunchtime, Constants.bingoloadcompletetime).toMillis())
						/ 1000)
				+ " seconds");

	}

	public static void getSlotsGameLoadTime(String data) throws Exception {
		launchSlotsGame(data);
		captureScreenWithFilename(data + "_" + Instant.now().getEpochSecond());
		if (!driver.getWindowHandle().toString().equals(Constants.parentwindow))
			driver.close();
		if (Constants.slotsloadcompletetime == null)
			return;
		System.out.println("Slots game was launched at " + Constants.slotslaunchtime);
		System.out.println("Slots game loading was completed at " + Constants.slotsloadcompletetime);
		System.out.println(
				(float) (Duration.between(Constants.slotslaunchtime, Constants.slotsloadcompletetime).toMillis()) / 1000
						+ " seconds");
		if ((float) (Duration.between(Constants.slotslaunchtime, Constants.slotsloadcompletetime).toMillis())
				/ 1000 > 0.00)
			dataEngineLog(String.valueOf(
					(float) (Duration.between(Constants.slotslaunchtime, Constants.slotsloadcompletetime).toMillis())
							/ 1000)
					+ " seconds");
	}

	public static void getCasinoGameLoadTime(String data) {

	}

	public static void getScratchGameLoadTime(String data) {

	}

	public static void CMAPopUpCheck(String data) throws Exception, UnhandledAlertException {
		// START OF LAUNCHSLOTSGAME
		Constants.slotdude = data;
		boolean loginissue = false;
		try {
			// Thread.sleep(1000);
			driver.switchTo().window(Constants.parentwindow);
			allCookies = driver.manage().getCookies();
			gotoSlotsLobby();
			System.out.println("Getting cash balance...");
			getWalletBalance("Cash");
			driver.findElement(By.xpath("//*[@id='edit-title']")).clear();
			driver.findElement(By.xpath("//*[@id='edit-title']")).sendKeys(data);
			// Thread.sleep(5000);
			int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
			String gameinternalid = null;
			try {
				gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
			} catch (Exception e2) {
				dataEngineLog("Game ID is not available in array");
			}
			System.out.println("Game ID for " + data + " is " + gameinternalid);
			try {
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]")));
			} catch (Exception e1) {
				// e1.printStackTrace();
				dataEngineLog("Search for the game " + data + " has not returned any results");
			}
			WebElement gameid = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"));
			String gameidtext = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"))
					.getAttribute("innerHTML").trim();
			System.out.println("GameID Text is :" + gameidtext);
			WebElement gameid2 = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
			String gameid2text = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
					.getAttribute("innerHTML").trim();
			System.out.println("GameID2 Test is: " + gameid2text);
			Actions action = new Actions(driver);
			if (gameidtext.contains("Play")) {
				Thread.sleep(5000);
				try {
					action.moveToElement(gameid).click(gameid).build().perform();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					gameid = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
					gameidtext = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
							.getAttribute("innerHTML").trim();
					System.out.println("GameID2 Test is: " + gameidtext);
					action = new Actions(driver);
					action.moveToElement(gameid).click(gameid).build().perform();
				}
				Constants.slotslaunchtime = Instant.now();
				// captureScreen();562018
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> slotgamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + slotgamewindow);
				System.out.println("Clicked first Play");
			} else if (gameid2text.contains("Play")) {
				Thread.sleep(3000);
				try {
					action.moveToElement(gameid2).click(gameid2).build().perform();
				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					gameid2 = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
					gameid2text = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
							.getAttribute("innerHTML").trim();
					System.out.println("GameID2 Test is: " + gameid2text);
					action = new Actions(driver);
					action.moveToElement(gameid2).click(gameid2).build().perform();
					System.out.println("Clicked second Play");
				}
				Constants.slotslaunchtime = Instant.now();
				// captureScreen();562018
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> slotgamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + slotgamewindow);
				ExcelUtils.setCellData("Launched " + data + " Slots Game successfully", DriverScript.iTestStep,
						Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			}

			for (String slotswindows : driver.getWindowHandles()) {
				if (!slotswindows.toString().equals(Constants.parentwindow)
						&& !slotswindows.toString().equals(Constants.bingohandle)
						&& !slotswindows.toString().equals(Constants.casinohandle)
						&& !slotswindows.toString().equals(Constants.scratchhandle)) {
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					// captureScreenWithFilename("before_switch_"+Instant.now().getEpochSecond());
					driver.switchTo().window(slotswindows);
					driver.manage().window().maximize();
					Constants.slothandle = driver.getWindowHandle();
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("After switching to Slotswindow, driver has :" + Constants.driverhandle);
					System.out.println("Slot Game window ID is :" + Constants.slothandle);
					System.out.println("Slot window title is :" + driver.getTitle());
					driver.manage().window().setSize(new Dimension(1024, 786));
					// captureScreenWithFilename("after_switch1_"+Instant.now().getEpochSecond());
					Screen s = new Screen();
					// captureScreenWithFilename("after_switch2_"+Instant.now().getEpochSecond());
					Pattern cma1 = new Pattern(Constants.slotsrepo + "CMA24.PNG");
					try {
						Scanner scanner = new Scanner(System.in);

						for (int i = 0; i < 30; i++) {

							System.out.println(captureScreenSize(data + "_" + Instant.now().toEpochMilli()));
							Thread.sleep(2000);
							if (captureScreenSize(data) > 250000) {

							}
						}
						dataEngineLog("CMA pop-up shown; screenshot saved.");
						System.out.println("Do you want to spin " + data + "?");
						// int spin = scanner.nextInt();
						// if(spin==1) {
						System.out.println("Is spin for " + data + " successful?");
						int spinyes = scanner.nextInt();
						if (spinyes == 1)
							captureScreenWithFilename(
									data + "_Successful Spin after CMA popup_" + Instant.now().getEpochSecond());
						else
							captureScreenWithFilename(
									data + "_Error Spin after CMA popup_" + Instant.now().getEpochSecond());
					} catch (Exception e) {
						// captureScreenWithFilename("after_switch5_"+Instant.now().getEpochSecond());
						System.out.println("Alert exception");
						Alert alert = driver.switchTo().alert();
						System.out.println(alert.getText());
						alert.accept();
						captureScreenWithFilename(data + "_Alert Exception_" + Instant.now().getEpochSecond());
						e.printStackTrace();
					}
					if (!driver.getWindowHandle().toString().equals(Constants.parentwindow))
						driver.close();

				}
			}

		} catch (Exception e) {
			// driver.close();
			e.printStackTrace();
			DriverScript.bResult = false;
			ExcelUtils.setCellData("Slots game " + data + " is not available", DriverScript.iTestStep,
					Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			System.out.println("Unable to launch Slots");
		}

	}

	public static void CMAPopUpCheckCasino(String data) throws Exception, UnhandledAlertException {
		// START OF LAUNCHCASINOGAME
		Constants.casinodude = data;
		boolean loginissue = false;
		try {
			// Thread.sleep(1000);
			driver.switchTo().window(Constants.parentwindow);
			allCookies = driver.manage().getCookies();
			gotoCasinoLobby();
			getWalletBalance("Cash");
			driver.findElement(By.xpath("//*[@id='edit-title']")).clear();
			driver.findElement(By.xpath("//*[@id='edit-title']")).sendKeys(data);
			// Thread.sleep(5000);
			int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
			String gameinternalid = null;
			try {
				gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
			} catch (Exception e2) {
				dataEngineLog("Game ID is not available in array");
			}
			System.out.println("Game ID for " + data + " is " + gameinternalid);
			try {
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]")));
			} catch (Exception e1) {
				// e1.printStackTrace();
				dataEngineLog("Search for the game " + data + " has not returned any results");
			}
			WebElement gameid = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"));
			String gameidtext = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"))
					.getAttribute("innerHTML").trim();
			System.out.println("GameID Text is :" + gameidtext);
			WebElement gameid2 = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
			String gameid2text = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
					.getAttribute("innerHTML").trim();
			System.out.println("GameID2 Test is: " + gameid2text);
			Actions action = new Actions(driver);
			if (gameidtext.contains("Play")) {
				Thread.sleep(5000);
				try {
					action.moveToElement(gameid).click(gameid).build().perform();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					gameid = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
					gameidtext = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
							.getAttribute("innerHTML").trim();
					System.out.println("GameID2 Test is: " + gameidtext);
					action = new Actions(driver);
					action.moveToElement(gameid).click(gameid).build().perform();
				}
				Constants.casinolaunchtime = Instant.now();
				// captureScreen();562018
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> casinogamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + casinogamewindow);
				System.out.println("Clicked first Play");
			} else if (gameid2text.contains("Play")) {
				Thread.sleep(3000);
				try {
					action.moveToElement(gameid2).click(gameid2).build().perform();
				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					gameid2 = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
					gameid2text = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
							.getAttribute("innerHTML").trim();
					System.out.println("GameID2 Test is: " + gameid2text);
					action = new Actions(driver);
					action.moveToElement(gameid2).click(gameid2).build().perform();
					System.out.println("Clicked second Play");
				}
				Constants.casinolaunchtime = Instant.now();
				// captureScreen();562018
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> casinogamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + casinogamewindow);
				ExcelUtils.setCellData("Launched " + data + " Casino Game successfully", DriverScript.iTestStep,
						Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			}

			for (String casinowindows : driver.getWindowHandles()) {
				if (!casinowindows.toString().equals(Constants.parentwindow)
						&& !casinowindows.toString().equals(Constants.bingohandle)
						&& !casinowindows.toString().equals(Constants.casinohandle)
						&& !casinowindows.toString().equals(Constants.scratchhandle)) {
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					// captureScreenWithFilename("before_switch_"+Instant.now().getEpochSecond());
					driver.switchTo().window(casinowindows);
					driver.manage().window().maximize();
					Constants.casinohandle = driver.getWindowHandle();
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("After switching to Casinowindow, driver has :" + Constants.driverhandle);
					System.out.println("Casino Game window ID is :" + Constants.casinohandle);
					System.out.println("Casino window title is :" + driver.getTitle());
					driver.manage().window().setSize(new Dimension(1024, 786));
					// captureScreenWithFilename("after_switch1_"+Instant.now().getEpochSecond());
					Screen s = new Screen();
					// captureScreenWithFilename("after_switch2_"+Instant.now().getEpochSecond());
					Pattern cma1 = new Pattern(Constants.casinorepo + "CMA24.PNG");
					try {
						// for (int i = 0; i < 15; i++) {
						//
						// try {
						// s.find(cma1);
						// captureScreenWithFilename(data + "_CMA_" + Instant.now().getEpochSecond());
						// playSound();
						// dataEngineLog("Found the CMA pop-up");
						// } catch (Exception e) {
						// captureScreenWithFilename(data + "_Catch CMA_" +
						// Instant.now().getEpochSecond());
						// // TODO Auto-generated catch block
						//// e.printStackTrace();
						// }
						//
						//// if (s.exists(cma1) != null) {
						//// captureScreenWithFilename(data + "_CMA_" + Instant.now().getEpochSecond());
						//// playSound();
						//// dataEngineLog("Found the CMA pop-up");
						//// }
						// }
						// BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
						// String[] campusDetail = bi.readLine().split("\\s");
						Scanner scanner = new Scanner(System.in);
						System.out.println("Did you find the CMA pop-up for " + data + " ?");
						int cma = scanner.nextInt();
						// for (int i = 0; i < 25; i++) {
						// Thread.sleep(999);
						// System.out.print(i);
						// }
						if (cma == 1) {
							// captureScreenWithFilename(data + "_playngo_loadcomplete_" +
							// Instant.now().getEpochSecond());
							// System.out.println("s.getBounds() - " + s.getBounds());
							// System.out.println("s.getBounds().getWidth() - " + s.getBounds().getWidth());
							// System.out.println("s.getBounds().getHeight() - " +
							// s.getBounds().getHeight());
							// System.out.println("s.getBounds().getSize() - " + s.getBounds().getSize());
							// System.out.println("s.getBounds().getCenterX() - " +
							// s.getBounds().getCenterX());
							// System.out.println("s.getBounds().getCenterY() - " +
							// s.getBounds().getCenterY());
							// System.out.println("s.getBounds().isEmpty() - " + s.getBounds().isEmpty());

							// if (s.exists(cma1, 15) != null) {
							captureScreenWithFilename(data + "_CMA_" + Instant.now().getEpochSecond());
							// //
							// captureScreenWithFilename("after_switch4_"+Instant.now().getEpochSecond());
							// System.out.println("Taking a dirty picture");
							// playSound();
							dataEngineLog("CMA pop-up shown; screenshot saved.");
							System.out.println("Do you want to spin " + data + "?");
							// int spin = scanner.nextInt();
							// if(spin==1) {
							System.out.println("Is spin for " + data + " successful?");
							int spinyes = scanner.nextInt();
							if (spinyes == 1)
								captureScreenWithFilename(
										data + "_Successful Spin after CMA popup_" + Instant.now().getEpochSecond());
							else
								captureScreenWithFilename(
										data + "_Error Spin after CMA popup_" + Instant.now().getEpochSecond());
							// }
						}
						// } else {
						// System.out.println("CMA popup not found");
						// captureScreenWithFilename(data + "_NO CMA_" +
						// Instant.now().getEpochSecond());
						// }
						// Thread.sleep(5000);
						// captureScreenWithFilename(data + "_CMA_" + Instant.now().getEpochSecond());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						// captureScreenWithFilename("after_switch5_"+Instant.now().getEpochSecond());
						System.out.println("Alert exception");
						Alert alert = driver.switchTo().alert();
						System.out.println(alert.getText());
						alert.accept();
						captureScreenWithFilename(data + "_Alert Exception_" + Instant.now().getEpochSecond());
						// e.printStackTrace();
					}
					if (!driver.getWindowHandle().toString().equals(Constants.parentwindow))
						driver.close();

				}
			}

		} catch (Exception e) {
			// driver.close();
			e.printStackTrace();
			DriverScript.bResult = false;
			ExcelUtils.setCellData("Casino game " + data + " is not available", DriverScript.iTestStep,
					Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			System.out.println("Unable to launch Casino");
		}

	}

	public static void inputUsername(String data) {
		// Constant Variable is used in place of UserName
		// NEED to stop loading the page if username/password and login button are
		// identified
		try {
			System.out.print(data);
			driver.findElement(By.xpath("//input[@placeholder='Username/Email']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Username/Email']")).sendKeys(data);
			captureScreen();
		} catch (Exception e) {
			DriverScript.bResult = false;
		}
	}

	public static void inputPassword(String data) {

		// Constant Variable is used in place of Password
		try {
			System.out.print(data);
			driver.findElement(By.xpath("//input[@type='password']")).clear();
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(data);
			captureScreen();
		} catch (Exception e) {
			DriverScript.bResult = false;
		}

	}

	public static void clickLogin(String data) {

		try {
			driver.findElement(By.id("edit-submit")).click();
			captureScreen();
		} catch (Exception e) {
			DriverScript.bResult = false;
		}
		// driver.navigate().refresh();

	}

	public static void defaultLogin(String data) {
		System.out.println("Logging in as user " + data);
		System.out.println(getLabel(Constants.URL));
		// System.out.println(getSiteCodeLikeABoss());
		try {
			try {
				if (getSiteCodeLikeABoss().contains("MCN")) {
					System.out.println("It's an MCN site");
					driver.get(Constants.URL + "/user/login");
				}
				if (Constants.URL.contains("bingoanywhere") || Constants.URL.contains("kelly")
						|| Constants.URL.contains("igloobingo") || Constants.URL.contains("morethanbingo"))

					if (!Constants.URL.contains("kelly"))
						driver.findElement(By.xpath("//li[@class='login-button']")).click();

			} catch (Exception e3) {
				// System.out.println("Regular login");
			}
			try {
				driver.findElement(By.xpath("//*[@class='login-popup']")).click();
			} catch (Exception e2) {
				// System.out.println("Regular login");
			}
			try {
				driver.findElement(By.xpath("//input[@placeholder='Username/Email']")).clear();
				driver.findElement(By.xpath("//input[@placeholder='Username/Email']")).sendKeys(data);
			} catch (Exception e) {
				// e.printStackTrace();
				try {
					driver.findElement(By.xpath("//input[@placeholder='Username']")).clear();
					driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(data);
				} catch (Exception e1) {
					try {
						driver.findElement(By.xpath("//input[@id='edit-name']")).clear();
						driver.findElement(By.xpath("//input[@id='edit-name']")).sendKeys(data);
					} catch (Exception e2) {
						driver.findElement(By.xpath("//input[@id='edit-name--2']")).clear();
						driver.findElement(By.xpath("//input[@id='edit-name--2']")).sendKeys(data);
					}
				}
			}
			try {
				driver.findElement(By.xpath("//input[@type='password']")).clear();
				driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Constants.Password);
			} catch (Exception e1) {
				try {
					driver.findElement(By.id("edit-pass--2")).clear();
					driver.findElement(By.id("edit-pass--2")).sendKeys(Constants.Password);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					driver.findElement(By.id("edit-pass")).clear();
					driver.findElement(By.id("edit-pass")).sendKeys(Constants.Password);
				}
			}
			// captureScreen();
			try {
				driver.findElement(By.id("edit-submit")).click();
			} catch (Exception e) {
				try {
					driver.findElement(By.id("edit-submit--2")).click();
				} catch (Exception e1) {
					driver.findElement(By.xpath("//*[@class='agree-button']")).click();
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit")));
					driver.findElement(By.id("edit-submit")).click();
				}
			}
			if (driver.getPageSource().contains("session_id"))
				Constants.sessionid = getSessionID();
			captureScreenWithFilename(getLabel(Constants.URL) + "_login" + rand(3));
			ExcelUtils.setCellData("Logged in with " + data + " successfully", DriverScript.iTestStep,
					Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			// updateGDPR("");/////////5172018
			Constants.sessionid = getSessionID();
			Constants.serviceURL = getServiceURL();
			System.out.println(Constants.serviceURL);
			System.out.println(getSiteCode());
		} catch (Exception e) {
			DriverScript.bResult = false;
			e.printStackTrace();
		}

	}

	public static void defaultAdminLogin(String data) {
		System.out.println("Logging in as user " + data);

		try {

			try {
				if (Constants.URL.contains("bingoanywhere"))
					driver.get(Constants.URL + "/user/login");
				driver.findElement(By.xpath("//li[@class='login-button']")).click();
			} catch (Exception e3) {
				// System.out.println("Regular login");
			}

			try {
				driver.findElement(By.xpath("//*[@class='login-popup']")).click();
			} catch (Exception e2) {
				// System.out.println("Regular login");
			}
			try {
				driver.findElement(By.xpath("//input[@placeholder='Username/Email']")).clear();
				driver.findElement(By.xpath("//input[@placeholder='Username/Email']")).sendKeys("admin");
			} catch (Exception e) {
				// e.printStackTrace();
				try {
					driver.findElement(By.xpath("//input[@placeholder='Username']")).clear();
					driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
				} catch (Exception e1) {
					try {
						driver.findElement(By.xpath("//input[@id='edit-name']")).clear();
						driver.findElement(By.xpath("//input[@id='edit-name']")).sendKeys("admin");
					} catch (Exception e2) {
						driver.findElement(By.xpath("//input[@id='edit-name--2']")).clear();
						driver.findElement(By.xpath("//input[@id='edit-name--2']")).sendKeys("admin");
					}
				}
			}
			try {
				driver.findElement(By.xpath("//input[@type='password']")).clear();
				driver.findElement(By.xpath("//input[@type='password']")).sendKeys(data);
			} catch (Exception e1) {
				driver.findElement(By.id("edit-pass--2")).clear();
				driver.findElement(By.id("edit-pass--2")).sendKeys(data);
			}
			// captureScreen();
			try {
				driver.findElement(By.id("edit-submit")).click();
			} catch (Exception e) {
				try {
					driver.findElement(By.id("edit-submit--2")).click();
				} catch (Exception e1) {
					driver.findElement(By.xpath("//*[@class='agree-button']")).click();
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit")));
					driver.findElement(By.id("edit-submit")).click();
				}
			}
			// if (driver.getPageSource().contains("session_id"))
			// Constants.sessionid = getSessionID();
			// captureScreenWithFilename(getLabel(Constants.URL) + "_login" + rand(3));
			// ExcelUtils.setCellData("Logged in with " + data + " successfully",
			// DriverScript.iTestStep,
			// Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			// updateGDPR("");/////////5172018
		} catch (Exception e) {
			DriverScript.bResult = false;
			e.printStackTrace();
		}

	}

	public static boolean isClickable(WebElement el, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 6);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static void waitUntil(String data) throws Exception {
		try {
			Thread.sleep(5000);
			captureScreen();
		} catch (Exception e) {

			DriverScript.bResult = false;
		}

	}

	public static void clickLogout(String data) {
		try {
			captureScreen();
			driver.findElement(By.xpath(".//*[@id=\"bingo-user-info\"]/div/div/div[1]/div[2]/div/a[2]")).click();
		} catch (Exception e) {

			DriverScript.bResult = false;
		}
	}

	public static void confirmLogout(String data) {
		try {
			captureScreen();
			driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/button[1]/span")).click();
		} catch (Exception e) {

			DriverScript.bResult = false;
		}

	}

	@Test
	public static void testNG() {
		System.out.println("First TestNG run");
	}

	@Test
	public static void timeout() throws InterruptedException {
		Thread.sleep(5000);
	}

	public static void clickRegister(String data) {
		try {
			captureScreen();
			// driver.findElement(By.id("register")).click();
			// driver.findElement(By.id("header-register")).click();
			driver.get(data + "/user/register");
			//// *[@id="header-register"]/span/a
			// *[@id="register"]/a
		} catch (Exception e) {
			DriverScript.bResult = false;
		}
	}

	public static void inputRegistrationStep1(String data) {

		try {
			Constants.rand = (int) ((long) Math.floor(Math.random() * 9_000L) + 1_000);
			String username = "Autoguy" + Constants.rand;
			String email = username + "." + Constants.LastName + "@discard.email";
			driver.findElement(By.name("name")).sendKeys(username);
			driver.findElement(By.name("first_name")).sendKeys(Constants.FirstName);
			driver.findElement(By.name("last_name")).sendKeys(Constants.LastName);
			driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click();
			driver.findElement(By.id("edit-mail")).sendKeys(email);
			driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
			driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);

			try {
				driver.findElement(
						By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[2]/div[6]/a/span[1]")).click();
			} catch (Exception e) {

				driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
			}
			captureScreen();
			// *[@id="user-register-form"]/div/div/div[4]/div/div[2]/div[6]/a
			// *[@id="user-register-form"]/div/div/div[4]/div/div[2]/div[6]/a/span[1]
			// *[@id="user-register-form"]/div/div/div[4]/div/div[2]/div[6]/a/span[2]
		} catch (Exception e) {

			DriverScript.bResult = false;
		}

	}

	public static void inputRegistrationStep2(String data) {
		try {
			WebElement day = driver.findElement((By.name("date_of_birth[day]")));
			WebElement month = driver.findElement((By.name("date_of_birth[month]")));
			WebElement year = driver.findElement((By.name("date_of_birth[year]")));
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
			Select sday = new Select(day);
			Select smonth = new Select(month);
			Select syear = new Select(year);
			// driver.navigate()
			sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
			smonth.selectByIndex(getRandomNumberInRange(1, 12));
			syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
			driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
			WebElement country = driver.findElement((By.id("edit-country")));
			Select scountry = new Select(country);
			scountry.selectByIndex(1);
			WebElement nationality = driver.findElement((By.id("edit-nationality")));
			Select snationality = new Select(nationality);
			snationality.selectByIndex(1);
			driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
			driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
			driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
			try {
				driver.findElement(By.name("terms-of-use")).click();
				System.out.println("Tried successfully");
			} catch (Exception e) {
				try {
					driver.findElement(
							By.xpath("//*[@id=\"user-register-form\"]/div/div/div[5]/div[1]/div[2]/div[9]/div/label"))
							.click();
					System.out.println("Tried again successfully");
				} catch (Exception e1) {

					driver.findElement(By.xpath("//*[@id=\"edit-terms-of-use\"]")).click();
					System.out.println("Caught successfully");
				}
			}

			driver.findElement(By.id("edit-submit")).click();
			String hand = driver.getWindowHandle();
			System.out.println(hand);
			captureScreen();

		} catch (Exception e1) {

			DriverScript.bResult = false;
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {// i wrote this thread to show the browser for

			e.printStackTrace();
		}
		// driver.quit();

		// submit the registration. now see the code
		// from main class

	}

	public static void getGameURL(String data) throws Exception {

		try {
			launchSlotsGame(data);
			Thread.sleep(5000);
			String currenturl = driver.getCurrentUrl();
			try {
				int userIDindex = currenturl.indexOf("userId");
				String userId = currenturl.substring(userIDindex, 22);
				System.out.print(userId);
			} catch (StringIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			ExcelUtils.setCellData(currenturl, DriverScript.iTestStep, Constants.Col_TestStepLog,
					Constants.Sheet_TestSteps);
			if (!driver.getWindowHandle().equals(Constants.parentwindow)) {
				driver.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			DriverScript.bResult = false;
			if (!driver.getWindowHandle().equals(Constants.parentwindow)) {
				driver.close();
			}
		}

	}

	public static String getEnvLabelPassword(String data) {
		int slashindex = data.indexOf("/");
		int firstperiod = data.indexOf(".");
		int secondperiod = data.indexOf(".", firstperiod + 1);
		String labelname = data.substring(firstperiod + 1, secondperiod);
		String env = data.substring(slashindex + 2, firstperiod);
		System.out.println(labelname);
		System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
		int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
		String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
		// String adminpass1=Constants.admin_password_list[];
		System.out.println(len);
		System.out.println(adminpass);
		return (env + "|" + labelname + "|" + adminpass);
	}

	public static String getLabel(String data) {
		String labelname;
		if (!driver.getCurrentUrl().contains("stg")) {
			int firstperiod = data.indexOf(".");
			int secondperiod = data.indexOf(".", firstperiod + 1);
			labelname = data.substring(firstperiod + 1, secondperiod);
		} else {
			int stgtext = data.indexOf("-");
			int period = data.indexOf(".");
			labelname = data.substring(stgtext + 1, period);
		}
		return (labelname);
	}

	public static void getAccountID(String data) {

		// try {
		// driver.findElement(By.xpath("//*[@id=\"bingo-user-info\"]/div/div/div[1]/div[2]/div/a[1]")).click();
		// captureScreen();
		// System.out.println("Clicked on My Account");
		// } catch (Exception e) {
		//
		// System.out.print("Could not click, so refreshing the page");
		// //driver.navigate().refresh();
		try {
			driver.get(Constants.URL + "/user");
			String reflink = driver.findElement(By.xpath("//*[@rel=\"shortlink\"]")).getAttribute("href");

			System.out.println(reflink);
			Constants.drupalID = reflink.substring(reflink.lastIndexOf('/') + 1);
			System.out.println(Constants.drupalID);
			int drupalAcc = (Integer.parseInt(Constants.drupalID));
			// boolean isNumeric = Constants.drupalID.chars().allMatch( Character::isDigit
			// );
			// System.out.print(isNumeric);
			if (Constants.drupalID.chars().allMatch(Character::isDigit)) {
				// DriverScript.bResult = false;
			}
		} catch (Exception e) {

			System.out.print("Could not click, so refreshing the page");
			DriverScript.bResult = false;
			System.out.print(DriverScript.bResult);
		}

		// e.printStackTrace();
	}

	public static String getAPIKey(String data) {
		int index = Arrays.asList(Constants.apisitelist).indexOf(data);
		return Arrays.asList(Constants.apikeys).get(index);
	}

	public static String getSiteCodeLikeABoss() {
		int indexofsitecode = driver.getPageSource().indexOf("site_code");
		String sub = driver.getPageSource().substring(indexofsitecode, indexofsitecode + 23);
		String site_code = sub.substring(sub.indexOf(":") + 2, sub.indexOf(",") - 1);
		return site_code;
	}

	public static String getServiceURL() {
		String serviceurl = null;
		try {
			int indexofsitecode = driver.getPageSource().indexOf("site_code");
			String sub = driver.getPageSource().substring(indexofsitecode, indexofsitecode + 23);
			String site_code = sub.substring(sub.indexOf(":") + 2, sub.indexOf(",") - 1);

			System.out.println(site_code);// 5112018
			if (!driver.getCurrentUrl().contains("stg")) {
				if (site_code.contains("LBN"))
					serviceurl = "https://api.livebingonetwork.co.uk/service/1";
				if (site_code.contains("BBN") || site_code.contains("FBN") || site_code.contains("SBN"))
					serviceurl = "https://api.bestbingonetwork.co.uk/service/1";
				if (site_code.contains("WBN"))
					serviceurl = "https://api.winnersbingonetwork.com/service/1";
				if (site_code.contains("GVN"))
					serviceurl = "https://api.luckyducknetwork.com/service/1";
				if (site_code.contains("MCN"))
					serviceurl = "https://api.mobilecasinonetwork.com/service/1";
				if (site_code.contains("8BG"))
					serviceurl = "https://api.slotgamesnetwork.com/service/1";
			} else if (driver.getCurrentUrl().contains("stg")) {
				if (site_code.contains("LBN"))
					serviceurl = "https://stg-api-lbn.ivycomptech.co.in/service/1";
				if (site_code.contains("BBN") || site_code.contains("FBN") || site_code.contains("SBN"))
					serviceurl = "https://stg-api-bbn.ivycomptech.co.in/service/1";
				if (site_code.contains("WBN"))
					serviceurl = "https://stg-api-wbn.ivycomptech.co.in/service/1";
				if (site_code.contains("GVN"))
					serviceurl = "https://stg-api-ldn.ivycomptech.co.in/service/1";
				if (site_code.contains("MCN"))
					serviceurl = "https://stg-api-mcn.ivycomptech.co.in/service/1";
				if (site_code.contains("8BG"))
					serviceurl = "https://stg-api-sgn.ivycomptech.co.in/service/1";
			}
		} catch (Exception e) {
		}
		return serviceurl;

		// https://api.livebingonetwork.co.uk/service/1/game/ -
		// {"id":"json-rpc","jsonrpc":"2.0","params":["iefiK3eeQu7Shait","all","default","weekly",1528089714,0,10],"method":"game.get_winners"}

	}

	public static String[] getProviderGames(String data) throws ParseException, MalformedURLException, IOException {
		String games[] = new String[100];
		String vendor;
		Object response = casinoAPI();
		Object result = new JSONParser().parse(response.toString());
		JSONObject result_jo = (JSONObject) result;
		Object gameslist = result_jo.get("result");
		JSONObject gameslist_jo = (JSONObject) gameslist;
		Object game = gameslist_jo.get("games_list");
		JSONObject gamedetail = (JSONObject) game;
		System.out.println(gamedetail);

		// System.out.println(gameslist_jo);
		for (int i = 0, j = 0; i < gameslist_jo.size(); i++) {
			// Object game = gameslist_jo.get(j);
			// JSONObject gamedetail = (JSONObject) game;
			System.out.println(gamedetail);
			vendor = (String) gamedetail.get("vendor");
			if (vendor.equalsIgnoreCase(data))
				games[i] = (String) gamedetail.get(j);
		}
		return games;

	}

	public static double getWalletBalance(String data) throws MalformedURLException, IOException, ParseException {

		double Cash_GBP = 0, CasinoCash_GBP = 0, Bonus_GBP = 0;
		try {
			Object response = walletAPI();
			Object wallet = new JSONParser().parse(response.toString());
			JSONObject wallet_jo = (JSONObject) wallet;
			Object walletresult = wallet_jo.get("result");
			JSONObject wallet_result_jo = (JSONObject) walletresult;
			Object wallet_balance = wallet_result_jo.get("wallet_balance");
			JSONObject wallet_balance_jo = (JSONObject) wallet_balance;
			System.out.println(Cash_GBP);
			System.out.println(CasinoCash_GBP);
			System.out.println(Bonus_GBP);
			if (data.equals("Cash")) {
				try {
					Cash_GBP = (double) wallet_balance_jo.get("Cash_GBP");
				} catch (Exception e) {
					// e.printStackTrace();
					System.out.println("Player does not have " + data + " balance");
				}
				return Cash_GBP;
			}
			if (data.equals("Game Bonus")) {
				try {
					CasinoCash_GBP = (double) wallet_balance_jo.get("CasinoCash_GBP");
				} catch (Exception e) {
					// e.printStackTrace();
					System.out.println("Player does not have " + data + " balance");
				}
				return CasinoCash_GBP;
			}

			if (data.equals("Bingo Bonus")) {
				try {
					Bonus_GBP = (double) wallet_balance_jo.get("Bonus_GBP");
				} catch (Exception e) {
					// e.printStackTrace();
					System.out.println("Player does not have " + data + " balance");
				}
				return Bonus_GBP;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Cash_GBP;

	}

	public static void playSound() {
		try {
			// Open an audio input stream.
			File soundFile = new File("C:\\Windows\\media\\Windows Error.wav"); // can also use URL
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void restrictCountry(String data) {
		try {
			defaultBrowseClient(data);
			ArrayList<String> countrylist = returnCountryDuringRegistration("Country");
			ArrayList<String> nationalitylist = returnCountryDuringRegistration("Nationality");
			System.out.println("Country list is :\n" + countrylist + "\n\n\nNationality list is :\n" + nationalitylist);
			if (countrylist.contains("United Kingdom"))
				System.out.println("It's there in Country");
			if (nationalitylist.contains("United Kingdom"))
				System.out.println("It's there in Nationality");
			if (!countrylist.contains("United Kingdom") & !nationalitylist.contains("United Kingdom"))
				System.out.println("It's NOT there any where");
		} catch (InterruptedException e) {
		}

	}

	public static void cozyDownsizing(String data) {
		restrictRegistration(data);
		checkOnsitePopUpOnLogin(data);
	}

	public static void checkOnsitePopUpOnLogin(String data) {
		try {
			// heuristicLogin(data);
			heuristicStagingLogin(data);
			captureScreenWithFilename("OnsitePopUp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void restrictRegistration(String data) {
		defaultBrowseClient(data);
		clickRegister(data);
		captureScreenWithFilename("ClickedRegister");
	}

	public static String getSessionID() throws MalformedURLException, IOException, ParseException {
		// Object loginAPI = loginAPI();
		// int indexofhostname = driver.getPageSource().indexOf("hostname");
		// int livehelpid = driver.getPageSource().indexOf("live_help_id");
		// String sourcejson = driver.getPageSource().substring(indexofhostname,
		// livehelpid-1);
		// int lastcomma = sourcejson.lastIndexOf(",");
		// String correctjson = "{\"" + sourcejson + "}";
		// String stringify = correctjson.replace(":\"", "\":\"");
		// stringify=correctjson.replace("\",", "\",\"");
		// System.out.println(correctjson);
		// System.out.println(stringify);
		// System.out.println(sourcejson);
		// Object json = new JSONParser().parse(correctjson.toString());
		// JSONObject jsonob = (JSONObject) json;
		// String sessionid=(String) jsonob.get("session_id");
		int indexofsid = driver.getPageSource().indexOf("session_id");
		String sub = driver.getPageSource().substring(indexofsid, indexofsid + 48);
		String session_id = sub.substring(sub.indexOf(":") + 2, sub.indexOf(",") - 1);
		// System.out.println(session_id);// 5112018
		return session_id;
	}

	public static String getAccountID() throws ParseException, MalformedURLException, IOException {
		String account_id = null, jsonrpc = null, currency = null;
		try {
			Object response = profileAPI();
			Object profile = new JSONParser().parse(response.toString());
			JSONObject profile_jo = (JSONObject) profile;
			Object profresult = profile_jo.get("result");
			JSONObject profile_result_jo = (JSONObject) profresult;
			Object total_payouts = profile_result_jo.get("total_payouts");
			JSONObject total_payouts_jo = (JSONObject) total_payouts;
			try {
				currency = total_payouts_jo.get("Cash_GBP").toString();
			} catch (Exception e) {
			}
			try {
				jsonrpc = (String) profile_jo.get("jsonrpc");
			} catch (Exception e) {
			}
			try {
				account_id = profile_result_jo.get("account_id").toString();
			} catch (Exception e) {
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		// System.out.println("Account ID is " + account_id);// 5112018
		// System.out.println("JSON RPC is "+jsonrpc);
		// System.out.println("Currency is "+currency);
		return account_id;
	}

	public static String getSiteCode() throws ParseException, MalformedURLException, IOException {
		String full_site_code = null;
		try {
			Object response = profileAPI();
			Object profile = new JSONParser().parse(response.toString());
			JSONObject profile_jo = (JSONObject) profile;
			Object profresult = profile_jo.get("result");
			JSONObject profile_result_jo = (JSONObject) profresult;
			Object total_payouts = profile_result_jo.get("total_payouts");
			JSONObject total_payouts_jo = (JSONObject) total_payouts;
			try {
				full_site_code = profile_result_jo.get("full_site_code").toString();
			} catch (Exception e) {
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		// System.out.println("Account ID is " + account_id);// 5112018
		// System.out.println("JSON RPC is "+jsonrpc);
		// System.out.println("Currency is "+currency);
		return full_site_code;
	}

	public static String getTime() {
		String reconcileformat = DateTimeFormatter.ofPattern("EEE, dd-MMM-YYYY").format(LocalDateTime.now());
		String reconciletime = Instant.now().toString().substring(11, 16);
		String spintime = Instant.now().toString();
		String getTime = reconcileformat + " " + reconciletime;
		System.out.println("Reconcile Format is :" + reconcileformat + " " + reconciletime);
		System.out.println("Reconcile Format getTime is :" + getTime);
		System.out.println("Spin Time is :" + spintime);
		return getTime;
	}

	public static Object RegisterAPI(String url) throws MalformedURLException, IOException {
		String requesturl = getServiceURL() + "/registration";
		HttpURLConnection con = (HttpURLConnection) ((new URL(url).openConnection()));
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "username=" + "&password=123456";
		con.setDoOutput(true);
		con.connect();
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : ");
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println("Response is: " + response.toString());
		return response;

	}

	public static Object loginAPI(String url, String username) throws MalformedURLException, IOException {

		HttpURLConnection con = (HttpURLConnection) ((new URL(url).openConnection()));
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "username=" + username + "&password=123456";
		con.setDoOutput(true);
		con.connect();
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : ");
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println("Response is: " + response.toString());
		return response;

	}

	public static void foxyScheduleAPI(String data) throws IOException {
		try {
			URL url = new URL("http://qa2.bingo.foxybingo.com/en/bingoApi/api/schedule");
			HttpURLConnection con = (HttpURLConnection) ((url.openConnection()));
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
			con.setRequestProperty("Host", "qa2.bingo.foxybingo.com");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);

			// con.setRequestProperty("", "");
			// con.setRequestProperty("", "");
			con.setRequestMethod("GET");
			con.connect();
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println("Response : " + response.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static Object profileAPI() throws MalformedURLException, IOException, ParseException {
		// System.out.println("Calling Nucleus Profile API");
		URL url = new URL(Constants.serviceURL + "/profile");
		HttpURLConnection con = (HttpURLConnection) ((new URL(Constants.serviceURL + "/profile").openConnection()));
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// String Parameters = "[\"iefiK3eeQu7Shait\",\""+sessionid+"\"]";
		String urlParameters = "{\"id\":\"json-rpc\",\"jsonrpc\":\"2.0\",\"params\":[\""
				+ getAPIKey(getLabel(Constants.URL)) + "\",\"" + Constants.sessionid
				+ "\"],\"method\":\"profile.get\"}";
		// System.out.println(urlParameters);//5112018
		con.setDoOutput(true);
		con.connect();
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : ");
		// System.out.println("Post parameters : " + urlParameters);
		// System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		java.net.CookieManager mscm = new java.net.CookieManager();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		// System.out.println("Profile API Response for [profile.get] is: " +
		// response.toString());// 5112018

		Object profile = new JSONParser().parse(response.toString());
		JSONObject profile_jo = (JSONObject) profile;
		Object profresult = profile_jo.get("result");
		JSONObject profile_result_jo = (JSONObject) profresult;
		try {
			String jsonrpc = (String) profile_jo.get("jsonrpc");
		} catch (Exception e1) {
		}
		try {
			String account_id = profile_result_jo.get("account_id").toString();
		} catch (Exception e) {
		}
		return response;

	}

	public static Object casinoAPI() throws MalformedURLException, IOException, ParseException {

		HttpURLConnection con = (HttpURLConnection) ((new URL(Constants.serviceURL + "/casino/").openConnection()));
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "{\"jsonrpc\":\"2.0\",\"id\":\"2\",\"method\":\"casino.get_games\",\"params\":[\""
				+ getAPIKey(getLabel(Constants.URL)) + "\",\"slots\",\"" + getSiteCode() + "\"]}";
		con.setDoOutput(true);
		con.connect();
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// System.out.println(response);
		return response;

	}

	public static void getSRCLinksold(String data) throws Exception {
		loginAPI("https://www.gamevillage.com/api/nucleus/login", "refer2");
		driver.get("https://www.gamevillage.com/game-lobby?qt-mini_bingo_lobby_tabs=2");
		// System.out.println(driver.getPageSource());
		List<WebElement> srclinks = driver
				.findElements(By.xpath("//*[@id='all-casino-games']/child::*/child::*/child::li"));

		// for (WebElement srclink : srclinks) {
		// System.out.println(srclink.findElement(By.xpath("/child::img")).getAttribute("src"));
		//
		// }

	}

	public static void getSRCLinks(String data) throws MalformedURLException, IOException {

		defaultBrowseClient("https://www.gamevillage.com/game-lobby?qt-mini_bingo_lobby_tabs=2");
		List<WebElement> srclinks = driver
				.findElements(By.xpath("//*[@id='all-casino-games']/child::*/child::*/child::li"));
		List<WebElement> imgele = driver
				.findElements(By.xpath("//*[@id='all-casino-games']/child::*/child::*/child::li/child::img"));
		for (WebElement imgeles : imgele) {
			String imageurl = imgeles.getAttribute("src");
			System.out.println(imgeles.getAttribute("class") + "		" + imageurl + "		");
			HttpURLConnection con = (HttpURLConnection) ((new URL(imageurl).openConnection()));
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
			con.setDoOutput(true);
			con.connect();
			System.out
					.println(imgeles.getAttribute("class") + "		" + imageurl + "		" + con.getResponseCode());
		}
		// for (WebElement srclink : srclinks) {
		// System.out.println(srclink.findElement(By.xpath("./child::img")).getAttribute("src"));
		//
		// }
		// loginAPI("https://www.gamevillage.com/api/nucleus/login","refer2");
	}

	public static void getTrackingStatus(String data) {

		try {
			String status = null;
			for (int i = 0; i < 100; i++) {
				Thread.sleep(10000);
				System.out.println("Getting tracking status for " + data);
				System.setProperty("javax.net.debug", "all");
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.1.100.60", 80));
				HttpURLConnection con = (HttpURLConnection) ((new URL(
						"http://www.dhl.co.in/shipmentTracking?AWB=9000233303&countryCode=in&languageCode=en&_=1535029255413")
								.openConnection()));
				con.setRequestMethod("GET");
				con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
				con.setRequestProperty("Host", "www.dhl.co.in");
				con.setRequestProperty("Accept-Encoding", "gzip, deflate");
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
				con.setRequestProperty("Proxy-Connection", "keep-alive");
				con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
				// con.setDoOutput(true);
				String urlParameters = "AWB=" + data + "&countryCode=in&languageCode=en";
				System.out.println(urlParameters);// 5112018
				con.connect();
				// DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				// wr.writeBytes(urlParameters);
				// wr.flush();
				// wr.close();
				System.out.println("Response Code : " + con.getResponseCode());
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				// System.out.println("API Response is: " + response.toString());// 5112018
				Object trackstatus = new JSONParser().parse(response.toString());
				JSONObject trackstatus_jo = (JSONObject) trackstatus;
				Object trackresult = trackstatus_jo.get("results");
				JSONArray trackresult_jo = (JSONArray) trackresult;
				Object checkpoints = trackresult_jo.get(0);
				JSONObject checkpointsa = (JSONObject) checkpoints;
				Object checkpoints0 = checkpointsa.get("checkpoints");
				JSONArray checkpointsinside = (JSONArray) checkpoints0;
				Object description = checkpointsinside.get(0);
				JSONObject desc = (JSONObject) description;
				status = (String) desc.get("description");
				// System.out.println("Checkpoints Result is \n"+checkpoints0.toString());
				System.out.println("Description is : " + desc.toString());
				System.out.println("Status is : " + status);
				if (!status.equals("Shipment picked up"))
					playSound();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getTrackingStatusRandom(String data) {
		try {
			System.out.println("Getting tracking status for " + data);
			String status = null;
			long rand = rand(9);
			for (int i = 0; i < 100; i++) {
				System.setProperty("javax.net.debug", "all");
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.1.100.60", 80));
				HttpURLConnection con = (HttpURLConnection) ((new URL("http://www.dhl.co.in/shipmentTracking?AWB="
						+ rand(9) + "&countryCode=in&languageCode=en&_=1535029255413").openConnection()));
				con.setRequestMethod("GET");
				con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
				con.setRequestProperty("Host", "www.dhl.co.in");
				con.setRequestProperty("Accept-Encoding", "gzip, deflate");
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
				con.setRequestProperty("Proxy-Connection", "keep-alive");
				con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
				// con.setDoOutput(true);
				String urlParameters = "AWB=" + rand + "&countryCode=in&languageCode=en";
				// System.out.println(urlParameters);// 5112018
				con.connect();
				// DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				// wr.writeBytes(urlParameters);
				// wr.flush();
				// wr.close();
				// System.out.println("Response Code : " + con.getResponseCode());
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println("API Response is: " + response.toString());// 5112018
				// Object trackstatus = new JSONParser().parse(response.toString());
				// JSONObject trackstatus_jo = (JSONObject) trackstatus;
				// Object trackresult = trackstatus_jo.get("results");
				// JSONArray trackresult_jo = (JSONArray) trackresult;
				// Object checkpoints = trackresult_jo.get(0);
				// JSONObject checkpointsa = (JSONObject) checkpoints;
				// Object checkpoints0 = checkpointsa.get("checkpoints");
				// JSONArray checkpointsinside = (JSONArray) checkpoints0;
				// Object description = checkpointsinside.get(0);
				// JSONObject desc = (JSONObject) description;
				// status = (String) desc.get("description");
				// // System.out.println("Checkpoints Result is \n"+checkpoints0.toString());
				// System.out.println("Description is : " + desc.toString());
				// System.out.println("Status is : " + status);
				// if(!status.equals("Shipment picked up"))
				// playSound();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object walletAPI() throws MalformedURLException, IOException, ParseException {
		// System.out.println("Calling Nucleus Wallet API");
		HttpURLConnection con = (HttpURLConnection) ((new URL(Constants.serviceURL + "/wallet/").openConnection()));
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// String Parameters = "[\"iefiK3eeQu7Shait\",\""+sessionid+"\"]";
		// {"jsonrpc":"2.0","id":"2","method":"wallet.get_balance","params":["iefiK3eeQu7Shait","3dc38f5aa75d46cd9894bdecf36069ac",1831740]}

		String urlParameters = "{\"jsonrpc\":\"2.0\",\"id\":\"2\",\"method\":\"wallet.get_balance\",\"params\":[\""
				+ getAPIKey(getLabel(Constants.URL)) + "\",\"" + Constants.sessionid + "\"," + getAccountID() + "]}";
		System.out.println(urlParameters);// 5112018
		con.setDoOutput(true);
		con.connect();
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : ");
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println("Wallet API Response for [wallet.get_balance] is: " + response.toString());// 5112018
		// Object profile = new JSONParser().parse(response.toString());
		// JSONObject profile_jo = (JSONObject) profile;
		// Object profresult = profile_jo.get("result");
		// JSONObject profile_result_jo = (JSONObject) profresult;
		// String account_id = profile_result_jo.get("account_id").toString();
		// System.out.println(account_id);
		return response;

	}

	public static Object gameAPI(String data) throws MalformedURLException, IOException, ParseException {

		HttpURLConnection con = (HttpURLConnection) ((new URL(Constants.serviceURL + "/wallet/").openConnection()));
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// String Parameters = "[\"iefiK3eeQu7Shait\",\""+sessionid+"\"]";
		// {"jsonrpc":"2.0","id":"2","method":"wallet.get_balance","params":["iefiK3eeQu7Shait","3dc38f5aa75d46cd9894bdecf36069ac",1831740]}

		String urlParameters = "{\"jsonrpc\":\"2.0\",\"id\":\"2\",\"method\":\"wallet.get_balance\",\"params\":[\""
				+ getAPIKey(getLabel(Constants.URL)) + "\",\"" + Constants.sessionid + "\"," + getAccountID() + "]}";
		String urlParameters2 = "{\"id\":\"json-rpc\",\"jsonrpc\":\"2.0\",\"params\":[\""
				+ getAPIKey(getLabel(Constants.URL)) + "\"\",\"all\",\"default\","
				+ "\"weekly\",1528089714,0,10],\"method\":\"game.get_winners\"}";
		// System.out.println(urlParameters);// 5112018
		con.setDoOutput(true);
		con.connect();
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters2);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : ");
		// System.out.println("Post parameters : " + urlParameters);
		// System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println("Wallet API Response for [wallet.get_balance] is: " + response.toString());// 5112018
		// Object profile = new JSONParser().parse(response.toString());
		// JSONObject profile_jo = (JSONObject) profile;
		// Object profresult = profile_jo.get("result");
		// JSONObject profile_result_jo = (JSONObject) profresult;
		// String account_id = profile_result_jo.get("account_id").toString();
		// System.out.println(account_id);
		return response;
	}

	public static void convertBetaTester(String data) {
		// C:\Users\vkolla\Downloads\geckodriver-v0.19.1-win64
		// that's the path
		// use it
		try {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\vkolla\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			WebDriver fdriver = new FirefoxDriver();
			// fdriver.get(Constants.URL+"/user/"+Constants.accountID+"/edit?destination=admin/people");
			fdriver.get(Constants.URL + "/admin" + "/people");

			try {
				fdriver.findElement(By.name("name")).clear();
				fdriver.findElement(By.name("name")).sendKeys("admin");
				fdriver.findElement(By.name("pass")).clear();
				fdriver.findElement(By.name("pass")).sendKeys("C0zy123$");
				fdriver.findElement(By.name("op")).click();
				String errormessage = fdriver.getPageSource();
				if (errormessage.contains("Oops! Sorry, you have entered an invalid username or password.")) {
					int firstperiod = Constants.URL.indexOf(".");
					int secondperiod = Constants.URL.indexOf(".", firstperiod + 1);
					String labelname = Constants.URL.substring(firstperiod + 1, secondperiod);
					System.out.println(labelname);
					System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
					int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
					String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
					// String adminpass1=Constants.admin_password_list[];
					// *[@data-game-id='internal_netentstarbursthtml']
					System.out.println(len);
					System.out.println(adminpass);
					fdriver.findElement(By.name("name")).clear();
					fdriver.findElement(By.name("name")).sendKeys("admin");
					fdriver.findElement(By.name("pass")).clear();
					fdriver.findElement(By.name("pass")).sendKeys(adminpass);
					fdriver.findElement(By.name("op")).click();
				}
			} catch (Exception e1) {

			}
			captureScreen();
			try {

				fdriver.findElement(
						By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
				/// html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a
				fdriver.findElement(
						By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
			} catch (Exception e) {

				fdriver.findElement(
						By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
				fdriver.findElement(
						By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
			}
			fdriver.findElement(By.name("accounts[" + Constants.drupalID + "]")).click();
			WebElement role = fdriver.findElement((By.name("operation")));
			role.click();
			Select btester = new Select(role);
			btester.selectByValue("add_role-4");
			captureScreen();
			fdriver.findElement(By.id("edit-submit--2")).click();
			captureScreen();
			fdriver.close();
		} catch (Exception e) {

			DriverScript.bResult = false;
			System.out.println("Couldn't convert beta tester because this is insane");
		}

	}

	public static void def(String data) throws Exception {
		defaultBrowseClient("https://stg-landmarkbingo.ivycomptech.co.in");
		defaultLogin(data);
		testDeposit("10");
		getGameURL("Finn And The Swirly Spin");
	}

	public static void testDeposit(String data) {
		// driver.switchTo().window(Constants.handle1);
		System.out.println("Entered test Deposit method...");
		try {
			System.out.println(getWalletBalance("Cash"));
			double depchecker = 34;
			if (getWalletBalance("Cash") < depchecker) {
				captureScreen();
				System.out.print(data);
				driver.get(Constants.URL + "/banking/deposit/test");
				// edit-submit
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit")));
				driver.findElement(By.xpath("//*[@alt='Paysafe Card'][1]")).click();
				driver.findElement(By.name("other_amount")).clear();
				driver.findElement(By.name("other_amount")).sendKeys(data);
				driver.findElement(By.xpath("//*[@id=\"combo-coupons\"]/li[2]")).click();
				captureScreen();
				// driver.findElement(By.id("edit-environment")).click();
				WebElement env = driver.findElement(By.name("environment"));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-environment")));
				Select senv = new Select(env);
				senv.selectByIndex(2);
				captureScreen();
				try {

					try {
						driver.findElement(By.xpath("//*[@for='edit-customer-protection']")).click();
					} catch (Exception e) {
						driver.findElement(By.xpath("//label[@class='option'])[2]")).click();
					}
					// *[@id="nucleus-cashier-deposit-form-new"]/div/div/div[2]/div[2]/div[5]/div[6]/div[2]/label
				} catch (Exception e) {
					try {
						driver.findElement(By.xpath(
								"//*[@id=\"nucleus-cashier-deposit-form-new\"]/div/div/div[2]/div[2]/div[3]/div[6]/div[2]/label"))
								.click();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
					}

					// e.printStackTrace();
				}
				driver.findElement(By.id("edit-submit")).click();
				captureScreenWithFilename(
						getLabel(Constants.URL) + "_deposit_" + data + "_" + Instant.now().getEpochSecond());
				Log.info("Deposited " + data + " pounds for player");
				System.out.println("Deposited " + data + " pounds for player");
				try {
					new WebDriverWait(driver, 10).until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@class='deposit-success']/child::p")));
					System.out.println("Found the deposit success message's parent");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					new WebDriverWait(driver, 10).until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='transaction-id']")));
					System.out.println("Found the deposit success message's child");
				}
				String transactionid = driver.findElement(By.xpath("//*[@class='transaction-id']")).getText();
				validateDepositHistoryLogs(transactionid);
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			DriverScript.bResult = false;
			e.printStackTrace();
		}
		// List<WebElement> deps = driver.findElements(By.className("coupon-code"));
		// List<WebElement> currencykey =
		// driver.findElements(By.className("currency-key"));

		// for (int i = 0; i < deps.size(); i++)

		// {
		// System.out.print(i);

		// String att = currencykey.get(i).getText();
		// System.out.println("printing what's in the getText\n\n");
		// System.out.print(att);
		// System.out.println("/nthat's it.");
		//// *[@id="combo-coupons"]/li[13]/div/span[1]
		// if (att.contains("CB")) {
		// WebElement bb = driver.findElement(By.className("coupon-code"));
		// driver.findElement(By.className("coupon-code")).getAttribute("");
		// String xpath = generateXPATH(bb,"");
		// System.out.print(xpath);
		// driver.findElement(By.xpath(xpath)).click();
		// String attval = deps.get(i).getAttribute("value");

		// System.out.printf(attval);
		// if (attval.equals(20)) {
		// driver.findElement(By.name("accounts[" + Constants.drupalID + "]")).click();
		// break;
		// }
		// }
		// }

	}

	public static void validateDepositHistoryLogs(String data) throws Exception {
		try {
			navDashboardMenu("Deposit");
			navDashboardDepositSubMenu("Deposit History");
			driver.findElement(By.id("edit-submit--4")).click();
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='table-container']")));
			List<WebElement> tr_collection = driver
					.findElements(By.xpath("(//*[@id='table-container']/child::*)[2]/child::tbody/child::*"));

			int row_num, col_num;
			System.out.println("Reading rows");
			row_num = 1;
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
				System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); // VK -
				// commenting
				col_num = 1;
				for (WebElement tdElement : td_collection) {
					// VK - USE THE BELOW PRINT LINE TO SEE WHAT THE TABLE DATA CONTAINS - ROW WISE
					// AND COLUMN WISE
					System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
					// System.out.println("Reconcile's "+reconcile[row_num][col_num]);
					col_num++;
					if (tdElement.getText().contains(data)) {
						System.out.println("Deposit history has the transaction");
						System.out.println("It's at row " + row_num + " and column " + col_num);
						dataEngineLog("Deposit history has the transaction " + data + " at row " + row_num
								+ " and column " + col_num);
						captureScreenWithFilename(getLabel(data) + "_DepositHistory_" + Instant.now().getEpochSecond());
					} else {
						DriverScript.bResult = false;
						// ExcelUtils.setCellData(
						// "Verification of Bingo tickets purchase in Reconcile report unsuccessful",
						// DriverScript.iTestStep, Constants.Col_TestStepLog,
						// Constants.Sheet_TestSteps);

					}
				}
				row_num++;
			}
		} catch (Exception e) {
			driver.get(Constants.URL + "/banking/deposit/history");
			driver.findElement(By.id("edit-submit")).click();
			captureScreenWithFilename(getLabel(Constants.URL) + "DepositHistoryLogs");
		}
	}

	public static void withdrawCash(String data) throws InterruptedException {
		try {
			driver.get(Constants.URL + "/dashboard");
			navDashboardMenu("my profile");
			// driver.findElement(By.xpath("//*[@id='quicktabs-tab-dashboard_tabs-0']")).click();
			String id = driver.findElement(By.xpath("(//span[@class='profile-value'])[2]")).getText();
			System.out.println(id);
			String str_id = id;
			String[] arrSplit_2 = str_id.split(": ", 2);
			String Actualid = arrSplit_2[1];
			System.out.println(Actualid);
			// int index_of_semicolon = id.lastIndexOf(":");
			// String actual_id = id.substring(index_of_semicolon);
			// System.out.println(actual_id);
			navDashboardMenu("withdraw");
			navDashboardWithdrawSubMenu("request withdrawal");
			// driver.findElement(By.xpath("//*[@id='quicktabs-tab-dashboard_tabs-2']")).click();
			// driver.findElement(By.xpath("//*[@id='quicktabs-tab-withdraw_tabs-0']")).click();
			String bal = driver.findElement(By.xpath("(//*[@class='cash-deposit fLeft']/child::*)[2]")).getText();
			System.out.println(bal);
			Constants.balance = bal.substring(bal.lastIndexOf('£') + 1);
			System.out.println(Constants.balance);
			float cashbalance = (Float.parseFloat(Constants.balance));
			float cash_to_be_withdrawn = (Float.parseFloat(data));
			String cashwithdraw = String.valueOf(cash_to_be_withdrawn);
			System.out.println("cashbalance is");
			System.out.print(cashbalance);
			System.out.println("cash to be withdrawn is");
			System.out.print(cash_to_be_withdrawn);
			Thread.sleep(10000);
			try {
				boolean stat = driver.findElement(By.xpath("//span[@class='approved']")).isDisplayed();// getting kyc
																										// status
				System.out.println(stat);
				System.out.println("kyc done");
				// driver.findElement(By.name("withdraw")).click();
				driver.findElement(By.name("requested_amount")).sendKeys(cashwithdraw);
				driver.findElement(By.name("next")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("(//label[@class='option'])[30]")).click();
				// driver.findElement(By.xpath("//*[@id='edit-submit--16']")).click();//5152018
				// Vijay asked me to comment this and replace with line below
				driver.findElement(By.xpath("//div[@class='paysafe-next']")).click();

				// balance & flowback after withdraw
				String withdraw_url = driver.getCurrentUrl();
				driver.get(withdraw_url);
				Thread.sleep(10000);
				WebElement flwbck = driver.findElement(By.xpath("(//div[@class='pending-withdraw-message'])[2]"));
				if (flwbck.isDisplayed() == true) {
					System.out.println("Flowback message is displayed");
				} else {
					System.out.println("Flowback message is not displayed");
				}
				String aftbal = driver.findElement(By.xpath("(//*[@class='cash-deposit fLeft']/child::*)[2]"))
						.getText();
				System.out.println(aftbal);
				Constants.afterbalance = aftbal.substring(bal.lastIndexOf('£') + 1);
				System.out.println(Constants.afterbalance);
				float cashbalanceaftrwithdraw = (Float.parseFloat(Constants.afterbalance));
				if (cashbalance - cash_to_be_withdrawn == cashbalanceaftrwithdraw)
					System.out.println("Successful withdraw" + "Remaining Balance:+cashbalanceaftrwithdraw+");
				else
					System.out.println("UnSuccessful withdraw balance mismatch");

			} catch (Exception e) {

				System.out.println("kyc not done");
				driver.navigate().to("https://cozy-gms.prod.partygaming.local/lbn/gms");
				String b = driver.getCurrentUrl();
				System.out.println(b);
				Thread.sleep(5000);
				driver.findElement(By.name("admin_login")).sendKeys("swathi");
				driver.findElement(By.name("admin_passwd")).sendKeys("PvB4ALu$");
				driver.findElement(By.xpath("//input[@value='Submit']")).click();
				Thread.sleep(5000);
				driver.get("https://cozy-gms.prod.partygaming.local/lbn/gms/ums/search_players.php?&tag=1");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@value='account_id']")).click();
				driver.findElement(By.name("search")).sendKeys(Actualid);
				driver.findElement(By.name("submit")).click();
				Thread.sleep(3000);

				driver.findElement(By.xpath("(//font[@color='green'])[17]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//font[@color='green'])[17]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@accesskey='l']")).click();
				driver.get(Constants.URL + "/dashboard");
				navDashboardMenu("withdraw");
				navDashboardWithdrawSubMenu("request withdrawal");
				// driver.findElement(By.xpath("//*[@id='quicktabs-tab-dashboard_tabs-2']")).click();
				// driver.findElement(By.xpath("//*[@id='quicktabs-tab-withdraw_tabs-0']")).click();
				// driver.findElement(By.name("withdraw")).click();
				driver.findElement(By.name("requested_amount")).sendKeys(cashwithdraw);
				driver.findElement(By.name("next")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("(//label[@class='option'])[30]")).click();
				// driver.findElement(By.xpath("//*[@id='edit-submit--17']")).click();//5152018
				// Vijay asked me to comment this and replace with line below
				driver.findElement(By.xpath("//div[@class='paysafe-next']")).click();
				// balance & flowback after withdraw
				String withdraw_url = driver.getCurrentUrl();
				driver.get(withdraw_url);
				Thread.sleep(10000);
				WebElement flwbck = driver.findElement(By.xpath("(//div[@class='pending-withdraw-message'])[2]"));
				if (flwbck.isDisplayed() == true) {
					System.out.println("Flowback message is displayed");
				} else {
					System.out.println("Flowback message is not displayed");
				}
				String aftbal = driver.findElement(By.xpath("(//*[@class='cash-deposit fLeft']/child::*)[2]"))
						.getText();
				System.out.println(aftbal);
				Constants.afterbalance = aftbal.substring(bal.lastIndexOf('£') + 1);
				System.out.println(Constants.afterbalance);
				float cashbalanceaftrwithdraw = (Float.parseFloat(Constants.afterbalance));
				if (cashbalance - cash_to_be_withdrawn == cashbalanceaftrwithdraw)
					System.out.println("Successful withdraw" + "Remaining Balance:+cashbalanceaftrwithdraw+");
				else
					System.out.println("UnSuccessful withdraw balance mismatch");
				DriverScript.bResult = false;

			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			DriverScript.bResult = false;
			System.out.println("cash withdraw failed");
		}

	}

	public static void validateWithDrawHistory(String data) throws Exception {
		navDashboardMenu("Withdraw");
		navDashboardWithdrawSubMenu("History");
		driver.findElement(By.xpath("//*[@for='edit-log-types-complete']")).click();
		driver.findElement(By.xpath("//*[@for='edit-log-types-payment-pending']")).click();
		driver.findElement(By.id("edit-submit--16")).click();
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='table-container']")));
		List<WebElement> tr_collection = driver
				.findElements(By.xpath("(//*[@id='table-container']/child::*)[2]/child::tbody/child::*"));

		int row_num, col_num;
		System.out.println("Reading rows");
		row_num = 1;
		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); // VK -
			// commenting
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				// VK - USE THE BELOW PRINT LINE TO SEE WHAT THE TABLE DATA CONTAINS - ROW WISE
				// AND COLUMN WISE
				System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				// System.out.println("Reconcile's "+reconcile[row_num][col_num]);
				col_num++;
				if (tdElement.getText().contains(data)) {
					System.out.println("Withdraw history has the transaction");
					System.out.println("It's at row " + row_num + " and column " + col_num);
					dataEngineLog("Withdraw history has the transaction " + data + " at row " + row_num + " and column "
							+ col_num);
					captureScreenWithFilename(getLabel(data) + "_WithdrawHistory_" + Instant.now().getEpochSecond());
				} else {
					DriverScript.bResult = false;
					// ExcelUtils.setCellData(
					// "Verification of Bingo tickets purchase in Reconcile report unsuccessful",
					// DriverScript.iTestStep, Constants.Col_TestStepLog,
					// Constants.Sheet_TestSteps);

				}
			}
			row_num++;
		}
	}

	public static void reconcileReport(String data) {

	}

	@SuppressWarnings("unused")
	private static String generateXPATH(WebElement childElement, String current) {
		String childTag = childElement.getTagName();
		if (childTag.equals("html")) {
			return "/html[1]" + current;
		}
		WebElement parentElement = childElement.findElement(By.xpath(".."));
		List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
		int count = 0;
		for (int i = 0; i < childrenElements.size(); i++) {
			WebElement childrenElement = childrenElements.get(i);
			String childrenElementTag = childrenElement.getTagName();
			if (childTag.equals(childrenElementTag)) {
				count++;
			}
			if (childElement.equals(childrenElement)) {
				return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
			}
		}
		return null;
	}

	public static void AdminLoginAPIKey(String data) {

		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("incognito");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			Map<String, Object> prefs = new HashMap<String, Object>();

			prefs.put("profile.default_content_settings.state.flash", 1); // # 1->allow
			prefs.put("profile.default_content_setting_values.plugins", 1);
			prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("PluginsAllowedForUrls", data);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			// System.out.print(data);
			driver.manage().deleteAllCookies();
			driver.get(data + "/admin/config/system/nucleus");
			try {
				driver.findElement(By.name("name")).clear();
				driver.findElement(By.name("name")).sendKeys("admin");
				driver.findElement(By.name("pass")).clear();
				driver.findElement(By.name("pass")).sendKeys("C0zy123$");
				driver.findElement(By.name("op")).click();
				String errormessage = driver.getTitle();
				if (errormessage.contains("Access")) {
					System.out.println("in IF cause of Access");
					int firstperiod = data.indexOf(".");
					int secondperiod = data.indexOf(".", firstperiod + 1);
					String labelname = data.substring(firstperiod + 1, secondperiod);
					System.out.println(labelname);
					// System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
					int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
					String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
					// String adminpass1=Constants.admin_password_list[];
					// *[@data-game-id='internal_netentstarbursthtml']
					System.out.println(adminpass);
					driver.findElement(By.name("name")).clear();
					driver.findElement(By.name("name")).sendKeys("admin");
					driver.findElement(By.name("pass")).clear();
					driver.findElement(By.name("pass")).sendKeys(adminpass);
					driver.findElement(By.name("op")).click();
				}
			} catch (Exception e1) {

			}
			try {
				if (driver.getTitle().contains("Nucleus")) {
					new WebDriverWait(driver, 5).until(
							ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("edit-nucleus-common-api-key")));
					String DesktopAPIKey = driver.findElement(By.id("edit-nucleus-common-api-key"))
							.getAttribute("value");
					System.out.println(DesktopAPIKey);
					ExcelUtils.setCellData(DesktopAPIKey, DriverScript.iTestStep, Constants.Col_TestStepLog + 1,
							Constants.Sheet_TestSteps);
					String MobileAPIKey = driver.findElement(By.id("edit-nucleus-common-mobile-api-key"))
							.getAttribute("value");
					System.out.println(MobileAPIKey);
					ExcelUtils.setCellData(MobileAPIKey, DriverScript.iTestStep, Constants.Col_TestStepLog + 2,
							Constants.Sheet_TestSteps);
				} else {
					driver.get(data + "/admin/config/system/nucleus");
					new WebDriverWait(driver, 5).until(
							ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("edit-nucleus-common-api-key")));
					String DesktopAPIKey = driver.findElement(By.id("edit-nucleus-common-api-key"))
							.getAttribute("value");
					System.out.println(DesktopAPIKey);
					ExcelUtils.setCellData(DesktopAPIKey, DriverScript.iTestStep, Constants.Col_TestStepLog + 1,
							Constants.Sheet_TestSteps);
					String MobileAPIKey = driver.findElement(By.id("edit-nucleus-common-mobile-api-key"))
							.getAttribute("value");
					System.out.println(MobileAPIKey);
					ExcelUtils.setCellData(MobileAPIKey, DriverScript.iTestStep, Constants.Col_TestStepLog + 2,
							Constants.Sheet_TestSteps);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			captureScreen();
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
			DriverScript.bResult = false;
			System.out.println("Couldn't get the API Key because this is insane");
		}

	}

	public static void cozylogs(int row, String result, String notes) throws Exception {
		String sPath = "C:\\Users\\vkolla\\.jenkins\\workspace\\Cozy-Daily-Regression\\DataEngine1.xlsx";
		ExcelUtils.setExcelFile(sPath, Constants.cozysitelist);
		ExcelUtils.setCellData(notes, row, 6, Constants.cozysitelist);
		ExcelUtils.setCellData(result, row, 5, Constants.cozysitelist);
	}

	@Test
	public static void CozyDailyRegression() throws Exception {
		Method mets[] = ActionKeywords.class.getMethods();
		// for (int ind = 1; ind < Constants.methods.size(); ind++) {
		// bResult = true;

		for (int i = 0, j = 0; i < mets.length; i++) {
			// System.out.println("Comparing "+mets[i].getName()+" with
			// "+Constants.methods.get(j));
			tResult = true;
			if (mets[i].getName().equals(Constants.methods.get(j))) {
				mets[i].invoke(Constants.methods.get(j), Constants.idata.get(j));

				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("RegisterBetaDeposit")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS",
							"Registration successful. Alias - " + Constants.cozyalias);
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("testDeposit")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS",
							"Deposited " + Constants.idata.get(j) + " successfully");
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("validateDepositHistoryLogs")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS",
							"Deposit history validated. Screenshot saved");
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("LaunchBingoGame")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS",
							"Launched " + Constants.idata.get(j) + " room successfully");
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("purchaseBingoTickets")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS",
							"Purchased " + Constants.idata.get(i) + " tickets successfully");
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("ValidateBingoGameLogs")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS", "");
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("LaunchSlotsGame")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS", "");
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("ValidateSlotsGameLogs")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS", "");
				}
				if (tResult == true && Constants.methods.get(j).equalsIgnoreCase("LaunchCasinoGame")) {
					System.out.println("Logging things");
					cozylogs(Integer.parseInt(Constants.index.get(j)), "PASS", "");
				}
				j++;
				i = 0;
			}
		}

		// }

	}

	@BeforeSuite
	public static void getTestData() throws Exception {

		System.out.println("Before Suite");
		String sPath = "C:\\Users\\vkolla\\.jenkins\\workspace\\Cozy-Daily-Regression\\DataEngine1.xlsx";
		ExcelUtils.setExcelFile(sPath, Constants.Path_TestData);
		Constants.cashcadeenv = ExcelUtils.getCellData(0, 1, Constants.cashcade);
		Constants.cclabel = ExcelUtils.getCellData(1, 1, Constants.cashcade);
		Constants.ccbrand = Constants.cclabel.replace(" Bingo", "").toLowerCase();
		System.out.println("Row count is " + ExcelUtils.getRowCount(Constants.cozysitelist));
		ArrayList<String> cozysitelist = new ArrayList<>();
		Constants.cozyregressionlist = new ArrayList<>();
		Constants.methods = new ArrayList<>();
		Constants.idata = new ArrayList<>();
		Constants.rmode = new ArrayList<>();
		Constants.index = new ArrayList<>();
		for (int i = 0; i < ExcelUtils.getRowCount(Constants.Sheet_TestCases); i++) {
			if (ExcelUtils.getCellData(i, 4, Constants.cozysitelist).equals("Yes")) {
				Constants.methods.add(ExcelUtils.getCellData(i, 2, Constants.cozysitelist));
				Constants.idata.add(ExcelUtils.getCellData(i, 3, Constants.cozysitelist));
				Constants.rmode.add(ExcelUtils.getCellData(i, 4, Constants.cozysitelist));
				Constants.index.add(ExcelUtils.getCellData(i, 0, Constants.cozysitelist));
				cozysitelist.add(ExcelUtils.getCellData(i, 1, Constants.cozysitelist));
				Constants.cozyregressionlist.add(ExcelUtils.getCellData(i, 1, Constants.cozysitelist));

			}
		}
		System.out
				.println(Constants.methods + "\n" + Constants.idata + "\n" + Constants.rmode + "\n" + Constants.index);
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
			}
			if (Constants.cashcadeenv.equalsIgnoreCase("Live")) {
				try {
					Constants.cchome = ExcelUtils.getCellData(4, 6, Constants.cashcade);
					Constants.cccasino = ExcelUtils.getCellData(5, 6, Constants.cashcade);
					Constants.ccoffers = ExcelUtils.getCellData(6, 6, Constants.cashcade);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Cashcade URLs on " + Constants.cashcadeenv + " environment are\n" + Constants.cchome + "\n"
				+ Constants.cccasino + "\n" + Constants.ccoffers);
	}

	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("Max is lesser than Min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	// @Test(groups = { "daily_regression" }, dataProvider = "utility.ExcelUtils")
	public static void RegisterBetaDeposit(String data) throws InterruptedException {

		//////////////// REGISTRATION BEGIN ////////////////
		Constants.URL = data;
		Constants.newregistration = true;
		String labelpassword = null;
		Constants.rand = (int) ((long) Math.floor(Math.random() * 9_0000L) + 1_0000);
		// Constants.rand = (long) Math.random(); //on a roll
		Constants.FirstName = Constants.firstnames[new Random().nextInt(Constants.firstnames.length)];
		String username = Constants.FirstName + Constants.rand;
		String email = username + "@yopmail.com";
		defaultBrowseClient(data);
		captureScreen();
		driver.get(data + "/user/register");
		String pagesource = driver.getPageSource();
		int sitecodestartindex = pagesource.indexOf("site_code");
		String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
		System.out.println(sitecode);
		// driver.findElement(By.id("edit-name")).clear();
		// driver.findElement(By.id("edit-name")).sendKeys(username);
		if (sitecode.equals("LBN") || sitecode.equals("WBN") || sitecode.equals("BBN") || sitecode.equals("GVN")
				|| sitecode.equals("FBN") || sitecode.equals("SBN")) {
			try {
				// System.out.println("ocha loop loki");
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-name")));
				try {
					driver.findElement(By.id("edit-name")).clear();
					driver.findElement(By.id("edit-name")).sendKeys(username);
				} catch (Exception e3) {

				}
				try {
					driver.findElement(By.id("edit-first-name")).clear();
					driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				} catch (Exception e2) {
					driver.findElement(By.id("edit-first-name--2")).clear();// FOR ALL LDN SITES
					driver.findElement(By.id("edit-first-name--2")).sendKeys(Constants.FirstName);
				}
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				driver.findElement(By.xpath("//*[@for='edit-gender-male']")).click();
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				System.out.println("pass2entered, clicking register");

				try {
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
				} catch (Exception e3) {
				}
				try {
					driver.findElement(By.className("register-login-button")).click();
					driver.findElement(By.className("register-login-button")).click();
				} catch (Exception e3) {
				}
				try {
					driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
					driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
				} catch (Exception e3) {
				}
				// driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
				// driver.findElement(By.xpath("//*[@class='register-login-button']")).click();

				System.out.println("clicked register");

				// Thread.sleep(5000);
				// System.out.println("ocha loop loki");
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-mobile")));
				System.out.println("waiting until");
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				driver.findElement(By.xpath("//*[@for='edit-opinion-no']")).click();

				// ///////// SWATHI'S TASK 5172018
				//
				// String[] offers = { "call", "text", "telemarketing" };
				// String[] emailyn = { "emailyes", "emailno" };
				// int offersrand = new Random().nextInt(offers.length);
				// int emailynrand = new Random().nextInt(emailyn.length);
				// System.out.println("Offers random is: " + offersrand);
				// System.out.println("Email random is: " + emailynrand);
				// driver.findElement(By.id("edit-receive-email")).click();
				// if (emailynrand == 0) {
				// driver.findElement(By.id("edit-receive-email")).click();
				// ExcelUtils.setCellData("Email unchecked", DriverScript.iTestStep, 8,
				// Constants.Sheet_TestSteps);
				// } else {
				// ExcelUtils.setCellData("Email checked", DriverScript.iTestStep, 8,
				// Constants.Sheet_TestSteps);
				// }
				// // driver.findElement(By.id("edit-receive-call")).click();
				// // driver.findElement(By.id("edit-receive-text")).click();
				// // driver.findElement(By.id("edit-receive-telemarketing")).click();
				// if (offersrand == 0) {
				// driver.findElement(By.id("edit-receive-call")).click();
				// ExcelUtils.setCellData("Call unchecked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// } else if (offersrand == 1) {
				// driver.findElement(By.id("edit-receive-text")).click();
				// ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text unchecked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// } else if (offersrand == 2) {
				// driver.findElement(By.id("edit-receive-telemarketing")).click();
				// ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post unchecked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// captureScreen();
				// }
				//
				// //////// SWATHI'S TASK 5172018

				driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
				driver.findElement(By.id("edit-lookup-address")).click();
				Thread.sleep(5000);
				WebElement getAddr = driver.findElement((By.xpath("//select[@id='edit-address-list']")));
				Select addr = new Select(getAddr);
				addr.selectByValue("1");
				// selectByVisibleText("53-54 Regency Square");
				// Thread.sleep(3000);
				ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
						Constants.Sheet_TestSteps);
				if (getLabel(data).equals("bootybingo") || getLabel(data).equals("readysetbingo")
						|| getLabel(data).equals("lmaobingo") || getLabel(data).equals("carbootbingo")
						|| getLabel(data).equals("newlookbingo") || getLabel(data).equals("mummiesbingo")
						|| getLabel(data).equals("bingolegacy") || getLabel(data).equals("luckysocksbingo")
						|| getLabel(data).equals("vampirebingo") || getLabel(data).equals("casinomagix")
						|| getLabel(data).equals("slotdiamond") || getLabel(data).equals("unitedcoloursofbingo")) {
					Thread.sleep(4000);
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]")).click();
					// WebElement e1 =
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]"));
					// boolean isTermsChecked = e1.isSelected();
					// System.out.println(Constants.URL);
					// System.out.println(isTermsChecked);
					// if (!isTermsChecked) {
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]")).click();
					// }
					// WebElement e2 =
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]"));
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).click();
					// boolean isPrivacyChecked = e2.isSelected();
					// System.out.println(Constants.URL);
					// System.out.println(isPrivacyChecked);
					// if (!isPrivacyChecked) {
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).click();
					// }
					// WebElement e3 =
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]"));
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]")).click();
					// boolean isAgeChecked = e3.isSelected();
					// System.out.println(Constants.URL);
					// System.out.println(isAgeChecked);
					// if (!isAgeChecked) {
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]")).click();
					// }
				} else {
					try {

						try {
							driver.findElement(By.name("terms-of-use")).click();
						} catch (Exception e) {
							try {
								driver.findElement(By.xpath(
										"//*[@id=\"user-register-form\"]/div/div/div[5]/div[1]/div[2]/div[9]/div/label"))
										.click();
							} catch (Exception e1) {
								try {
									driver.findElement(By.xpath("//*[@id=\"edit-terms-of-use\"]")).click();
								} catch (Exception e2) {

									driver.findElement(By.xpath("//*[@for=\"edit-terms-of-use\"]")).click();
								}
							}
						}

					}

					catch (Exception e) {// FOR 8BG ACQUIRED SITES
						System.out.println("In catch loop");
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]")).click();
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).click();
						// driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).sendKeys(org.sikuli.hotkey.Keys.PRINTSCREEN);
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]")).click();
					}
				}

				driver.findElement(By.id("edit-submit")).click();
				ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
						Constants.Sheet_TestSteps);
				captureScreenWithFilename(getLabel(data) + "_SuccessfulRegistration_" + Instant.now().getEpochSecond());
				String successfulregistration;
				try {
					successfulregistration = driver.findElement(By.xpath("//*[@class='messages__list']/child::*[1]"))
							.getText();
					System.out.println(successfulregistration);
					if (successfulregistration.contains("has been successfully registered")) {
						ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
								Constants.Sheet_TestSteps);
					}
				} catch (Exception e) {

				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
				DriverScript.bResult = false;
				System.out.println("LBN/BBN/WBN/FBN/SBN Registration failed");
				// driver.close();
			}

		} else if (sitecode.equals("MCN")) {

			try {
				try {
					labelpassword = getEnvLabelPassword(data);
					System.out.println(labelpassword);
				} catch (Exception e) {

				}
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-first-name")));
				driver.findElement(By.id("edit-first-name")).clear();
				driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click();// vijay to change the
																								// xpath for this and
																								// the one below

				// ///////// SWATHI'S TASK 5172018
				//
				// String[] offers = { "call", "text", "telemarketing" };
				// String[] emailyn = { "emailyes", "emailno" };
				// int offersrand = new Random().nextInt(offers.length);
				// int emailynrand = new Random().nextInt(emailyn.length);
				// System.out.println("Offers random is: " + offersrand);
				// System.out.println("Email random is: " + emailynrand);
				// driver.findElement(By.id("edit-receive-email")).click();
				// if (emailynrand == 0) {
				// driver.findElement(By.id("edit-receive-email")).click();
				// ExcelUtils.setCellData("Email unchecked", DriverScript.iTestStep, 8,
				// Constants.Sheet_TestSteps);
				// } else {
				// ExcelUtils.setCellData("Email checked", DriverScript.iTestStep, 8,
				// Constants.Sheet_TestSteps);
				// }
				// // driver.findElement(By.id("edit-receive-call")).click();
				// // driver.findElement(By.id("edit-receive-text")).click();
				// // driver.findElement(By.id("edit-receive-telemarketing")).click();
				// if (offersrand == 0) {
				// driver.findElement(By.id("edit-receive-call")).click();
				// ExcelUtils.setCellData("Call unchecked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// } else if (offersrand == 1) {
				// driver.findElement(By.id("edit-receive-text")).click();
				// ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text unchecked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// } else if (offersrand == 2) {
				// driver.findElement(By.id("edit-receive-telemarketing")).click();
				// ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post unchecked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// captureScreen();
				// }
				//
				// //////// SWATHI'S TASK 5172018

				WebElement country = driver.findElement((By.id("edit-country")));
				Select scountry = new Select(country);
				scountry.selectByIndex(1);
				WebElement nationality = driver.findElement((By.id("edit-nationality")));
				Select snationality = new Select(nationality);
				snationality.selectByIndex(1);
				driver.findElement(By.xpath("//*[@for='edit-opinion-no']")).click();
				driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
				System.out.println("got till city");
				driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
				System.out.println("got till address");
				driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
				System.out.println("got till checkbox");
				if (labelpassword.contains("slotdiamond") || labelpassword.contains("casinomagix")) {
					driver.findElement(By.id("edit-confirm-terms")).click();
					WebElement e1 = driver.findElement(By.id("edit-confirm-terms"));
					boolean isTermsChecked = e1.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isTermsChecked);
					if (!isTermsChecked) {
						driver.findElement(By.id("edit-confirm-terms")).click();
					}
					driver.findElement(By.id("edit-confirm-privacy")).click();
					WebElement e2 = driver.findElement(By.id("edit-confirm-privacy"));
					boolean isPrivacyChecked = e2.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isPrivacyChecked);
					if (!isPrivacyChecked) {
						driver.findElement(By.id("edit-confirm-privacy")).click();
					}
					driver.findElement(By.id("edit-confirm-age")).click();
					WebElement e3 = driver.findElement(By.id("edit-confirm-age"));
					boolean isAgeChecked = e3.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isAgeChecked);
					if (!isAgeChecked) {
						driver.findElement(By.id("edit-confirm-age")).click();
					}
				} else {
					driver.findElement(By.id("edit-terms-of-use")).click();
				}
				System.out.println("In The End");
				driver.findElement(By.id("edit-submit")).click();
				captureScreen();
				String successfulregistration = driver.findElement(By.xpath("//*[@class='messages__list']/child::*[1]"))
						.getText();
				System.out.println(successfulregistration);
				if (successfulregistration.contains("has been successfully registered")) {
					ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
							Constants.Sheet_TestSteps);
				}
			} catch (Exception e) {

				DriverScript.bResult = false;
				System.out.println("MCN Registration failed");
			}

		} else if (sitecode.equals("8BG")) {
			try {
				driver.findElement(By.id("edit-name")).clear();
				driver.findElement(By.id("edit-name")).sendKeys(username);
				driver.findElement(By.id("edit-first-name")).clear();
				driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				try {
					driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click(); // put this in Try
																									// because only one
																									// of the 8BG sites
																									// has Gender as a
																									// required field
				} catch (Exception e) {
				}
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				captureScreenWithFilename(getLabel(data) + "_RegistrationStep1_" + Instant.now().getEpochSecond());
				try {
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					driver.findElement(By.xpath("//*[@class=\"register-personal-details\"]/child::*/child::a")).click();
				} catch (Exception e) {
				}
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				driver.findElement(By.xpath("//*[@for='edit-opinion-no']")).click();
				WebElement country = driver.findElement((By.id("edit-country")));
				Select scountry = new Select(country);
				scountry.selectByIndex(1);
				WebElement nationality = driver.findElement((By.id("edit-nationality")));
				Select snationality = new Select(nationality);
				snationality.selectByIndex(1);
				driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
				driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
				try {
					driver.findElement(By.id("edit-city")).sendKeys(Constants.City);
				} catch (Exception e4) {
					driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
				}

				// //////// SWATHI'S TASK 5172018
				//
				// String[] offers = { "call", "text", "telemarketing" };
				// String[] emailyn = { "emailyes", "emailno" };
				// int offersrand = new Random().nextInt(offers.length);
				// int emailynrand = new Random().nextInt(emailyn.length);
				// System.out.println("Offers random is: " + offersrand);
				// System.out.println("Email random is: " + emailynrand);
				// driver.findElement(By.id("edit-receive-email")).click();
				// if (emailynrand == 0) {
				// driver.findElement(By.id("edit-receive-email")).click();
				// ExcelUtils.setCellData("Email unchecked", DriverScript.iTestStep, 8,
				// Constants.Sheet_TestSteps);
				// } else {
				// ExcelUtils.setCellData("Email checked", DriverScript.iTestStep, 8,
				// Constants.Sheet_TestSteps);
				// }
				// // driver.findElement(By.id("edit-receive-call")).click();
				// // driver.findElement(By.id("edit-receive-text")).click();
				// // driver.findElement(By.id("edit-receive-telemarketing")).click();
				// if (offersrand == 0) {
				// driver.findElement(By.id("edit-receive-call")).click();
				// ExcelUtils.setCellData("Call unchecked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// } else if (offersrand == 1) {
				// driver.findElement(By.id("edit-receive-text")).click();
				// ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text unchecked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// } else if (offersrand == 2) {
				// driver.findElement(By.id("edit-receive-telemarketing")).click();
				// ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10,
				// Constants.Sheet_TestSteps);
				// ExcelUtils.setCellData("Post unchecked", DriverScript.iTestStep, 11,
				// Constants.Sheet_TestSteps);
				// captureScreen();
				// }
				//
				// //////// SWATHI'S TASK 5172018

				try {
					driver.findElement(By.id("edit-confirm-terms")).click();
					WebElement e1 = driver.findElement(By.id("edit-confirm-terms"));
					boolean isTermsChecked = e1.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isTermsChecked);
					if (!isTermsChecked) {
						driver.findElement(By.id("edit-confirm-terms")).click();
					}
				} catch (Exception e) {
				}
				driver.findElement(By.id("edit-submit")).click();
				System.out.println("Registration successful");
				new WebDriverWait(driver, 10).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@class='messages__list']/child::*[1]")));
				ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
						Constants.Sheet_TestSteps);
				captureScreenWithFilename(getLabel(data) + "_RegistrationStep1_" + Instant.now().getEpochSecond());
				try {
					String successfulregistration = driver
							.findElement(By.xpath("//*[@class='messages__list']/child::*[1]")).getText();
					System.out.println(successfulregistration);
					if (successfulregistration.contains("has been successfully registered")) {
						ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
								Constants.Sheet_TestSteps);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// driver.close();// 5172018
			} catch (Exception e) {
				e.printStackTrace();
				DriverScript.bResult = false;
				System.out.println("8BG Registration failed");
			}
		}

		///////////////////////// REGISTRATION END //////////////////////////////

		///////////////////////// CLOSE DARK OVERLAYS BEGIN ////////////////
		try {
			driver.findElement(By.className("my-account-responsible-gaming"));
			driver.findElement(By.id("edit-deposit-limit-period-0")).click();
			driver.findElement(By.id("edit-deposit-amount")).sendKeys("25");
			driver.findElement(By.id("edit-submit")).click();
			WebElement cydl = driver.findElement(
					By.cssSelector(".ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only.accept"));
			cydl.findElement(By.xpath("./child::*")).click();
			captureScreenWithFilename(getLabel(Constants.URL) + "DepositLimit");
			driver.findElement(By.xpath("//*[@id='nucleus-velocity-deposit-limits-wrapper']/child::*")).click();
		} catch (Exception e2) {
		}

		/////////////////////// CLOSE DARK OVERLAYS BEGIN ////////////////

		//////////////////////// GET ACCOUNT ID BEGIN ////////////////////////////////
		// 5172018 SWATHI'S TASK
		//
		try

		{
			driver.get(Constants.URL + "/user");
			String reflink = driver.findElement(By.xpath("//*[@rel=\"shortlink\"]")).getAttribute("href");

			System.out.println(reflink);
			Constants.drupalID = reflink.substring(reflink.lastIndexOf('/') + 1);
			System.out.println(Constants.drupalID);
			int drupalAcc = (Integer.parseInt(Constants.drupalID));
			// boolean isNumeric = Constants.drupalID.chars().allMatch(
			// Character::isDigit
			// );
			// System.out.print(isNumeric);
			if (Constants.drupalID.chars().allMatch(Character::isDigit)) {
				// DriverScript.bResult = false;
			}
			Constants.sessionid = getSessionID();
			Constants.serviceURL = getServiceURL();
		} catch (Exception e) {
			System.out.print("Could not click, so refreshing the page");
			// DriverScript.bResult = false;
			System.out.print(DriverScript.bResult);
		}

		/////////////////////// GET ACCOUNT ID END //////////////////////

		//////////////////////// CONVERT BETA TESTER BEGIN
		//////////////////////// //////////////////////////////

		try {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\vkolla\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			WebDriver fdriver = new FirefoxDriver();
			//
			fdriver.get(Constants.URL + "/user/" + Constants.accountID + "/edit?destination=admin/people");
			fdriver.get(Constants.URL + "/admin" + "/people");

			try {
				fdriver.findElement(By.name("name")).clear();
				fdriver.findElement(By.name("name")).sendKeys("admin");
				fdriver.findElement(By.name("pass")).clear();
				fdriver.findElement(By.name("pass")).sendKeys("C0zy123$");
				fdriver.findElement(By.name("op")).click();
				String errormessage = fdriver.getPageSource();
				if (errormessage.contains("Oops! Sorry, you have entered an invalid username or password.")) {
					int firstperiod = Constants.URL.indexOf(".");
					int secondperiod = Constants.URL.indexOf(".", firstperiod + 1);
					String labelname = Constants.URL.substring(firstperiod + 1, secondperiod);
					System.out.println(labelname);
					System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
					int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
					String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
					// String adminpass1=Constants.admin_password_list[];
					System.out.println(len);
					System.out.println(adminpass);
					fdriver.findElement(By.name("name")).clear();
					fdriver.findElement(By.name("name")).sendKeys("admin");
					fdriver.findElement(By.name("pass")).clear();
					fdriver.findElement(By.name("pass")).sendKeys(adminpass);
					fdriver.findElement(By.name("op")).click();
				}
			} catch (Exception e1) {

			}
			captureScreen();
			try {

				fdriver.findElement(
						By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
				///
				// html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a
				fdriver.findElement(
						By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
			} catch (Exception e) {

				fdriver.findElement(
						By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
				fdriver.findElement(
						By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
						.click();
			}
			fdriver.findElement(By.name("accounts[" + Constants.drupalID + "]")).click();
			WebElement role = fdriver.findElement((By.name("operation")));
			role.click();
			Select btester = new Select(role);
			btester.selectByValue("add_role-4");
			captureScreenWithFilename("Converting Beta Tester_" + Instant.now().getEpochSecond());
			fdriver.findElement(By.id("edit-submit--2")).click();
			Thread.sleep(2000);
			// fdriver.quit();

			// testDeposit("20");
		} catch (Exception e) {
			e.printStackTrace();
			DriverScript.bResult = false;
		}
		//////////////////////// CONVERT BETA TESTER END ////////////////////////
		////////////////////// test deposit ////////////////////////////
		Constants.cozyalias = username;
		// 5172018 SWATHI'S TASK
	}

	public static ArrayList<String> returnCountryDuringRegistration(String data) throws InterruptedException {
		ArrayList<String> dropies = null, naties = null;
		// Constants.URL = data;
		Constants.newregistration = true;
		String labelpassword = null;
		Constants.rand = (int) ((long) Math.floor(Math.random() * 9_0000L) + 1_0000);
		// Constants.rand = (long) Math.random(); //on a roll
		String username = Constants.firstnames[new Random().nextInt(Constants.firstnames.length)] + Constants.rand;
		String email = username + "@yopmail.com";
		captureScreen();
		driver.get(Constants.URL + "/user/register");
		String pagesource = driver.getPageSource();
		int sitecodestartindex = pagesource.indexOf("site_code");
		String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
		// System.out.println(sitecode);
		// driver.findElement(By.id("edit-name")).clear();
		// driver.findElement(By.id("edit-name")).sendKeys(username);
		if (sitecode.equals("LBN") || sitecode.equals("WBN") || sitecode.equals("BBN") || sitecode.equals("GVN")
				|| sitecode.equals("FBN") || sitecode.equals("SBN")) {
			try {
				// System.out.println("ocha loop loki");
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-name")));
				try {
					driver.findElement(By.id("edit-name")).clear();
					driver.findElement(By.id("edit-name")).sendKeys(username);
				} catch (Exception e3) {

				}
				try {
					driver.findElement(By.id("edit-first-name")).clear();
					driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				} catch (Exception e2) {
					driver.findElement(By.id("edit-first-name--2")).clear();// FOR ALL LDN SITES
					driver.findElement(By.id("edit-first-name--2")).sendKeys(Constants.FirstName);
				}
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				driver.findElement(By.xpath("//*[@for='edit-gender-male']")).click();
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				try {
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
				} catch (Exception e3) {
				}
				try {
					driver.findElement(By.className("register-login-button")).click();
					driver.findElement(By.className("register-login-button")).click();
				} catch (Exception e3) {
				}
				try {
					driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
					driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
				} catch (Exception e3) {
				}
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-mobile")));
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				driver.findElement(By.xpath("//*[@for='edit-opinion-no']")).click();
				WebElement country = driver.findElement(By.id("edit-country"));
				Select scountry = new Select(country);
				List<WebElement> allCountries = driver.findElements(By.xpath("//*[@id='edit-country']//child::option"));
				dropies = new ArrayList<String>();
				// System.out.println("All Countries are : ");
				for (WebElement dropvalues : allCountries) {
					dropies.add(dropvalues.getText());
					// System.out.println(dropvalues.getText());
				}
				List<WebElement> allNationalities = driver
						.findElements(By.xpath("//*[@id='edit-nationality']//child::option"));
				naties = new ArrayList<String>();
				// System.out.println("All Nationalities are : ");
				for (WebElement dropvalues : allNationalities) {
					naties.add(dropvalues.getText());
					// System.out.println(dropvalues.getText());
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
				DriverScript.bResult = false;
				System.out.println("LBN/BBN/WBN/FBN/SBN Registration failed");
			}

		} else if (sitecode.equals("MCN")) {

			try {
				try {
					labelpassword = getEnvLabelPassword(data);
					System.out.println(labelpassword);
				} catch (Exception e) {

				}
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-first-name")));
				driver.findElement(By.id("edit-first-name")).clear();
				driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click();// vijay to change the
																								// xpath for this and
																								// the one below

				///// restrict country code////////
				List<WebElement> allCountries = driver.findElements(By.xpath("//*[@id='edit-country']//child::option"));
				dropies = new ArrayList<String>();
				// System.out.println("All Countries are : ");
				for (WebElement dropvalues : allCountries) {
					dropies.add(dropvalues.getText());
					// System.out.println(dropvalues.getText());
				}
				List<WebElement> allNationalities = driver
						.findElements(By.xpath("//*[@id='edit-nationality']//child::option"));
				naties = new ArrayList<String>();
				// System.out.println("All Nationalities are : ");
				for (WebElement dropvalues : allNationalities) {
					naties.add(dropvalues.getText());
					// System.out.println(dropvalues.getText());
				}
				///// restrict country code////////

			} catch (Exception e) {

				DriverScript.bResult = false;
				System.out.println("MCN Registration failed");
			}

		} else if (sitecode.equals("8BG")) {
			try {
				driver.findElement(By.id("edit-name")).clear();
				driver.findElement(By.id("edit-name")).sendKeys(username);
				driver.findElement(By.id("edit-first-name")).clear();
				driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				try {
					driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click(); // put this in Try
																									// because only one
																									// of the 8BG sites
																									// has Gender as a
																									// required field
				} catch (Exception e) {
				}
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				captureScreenWithFilename(getLabel(data) + "_RegistrationStep1_" + Instant.now().getEpochSecond());
				try {
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					// Thread.sleep(1000);
					try {
						driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					driver.findElement(By.xpath("//*[@class=\"register-personal-details\"]/child::*/child::a")).click();
				} catch (Exception e) {
					e.printStackTrace();
				}
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				driver.findElement(By.xpath("//*[@for='edit-opinion-no']")).click();

				///// restrict country code////////
				List<WebElement> allCountries = driver.findElements(By.xpath("//*[@id='edit-country']//child::option"));
				dropies = new ArrayList<String>();
				// System.out.println("All Countries are : ");
				for (WebElement dropvalues : allCountries) {
					dropies.add(dropvalues.getText());
					// System.out.println(dropvalues.getText());
				}
				List<WebElement> allNationalities = driver
						.findElements(By.xpath("//*[@id='edit-nationality']//child::option"));
				naties = new ArrayList<String>();
				// System.out.println("All Nationalities are : ");
				for (WebElement dropvalues : allNationalities) {
					naties.add(dropvalues.getText());
					// System.out.println(dropvalues.getText());
				}
				///// restrict country code////////

			} catch (Exception e) {
				e.printStackTrace();
				DriverScript.bResult = false;
				System.out.println("8BG Registration failed");
			}
		}

		///////////////////////// REGISTRATION END //////////////////////////////
		if (data.equals("Country"))
			return dropies;
		else
			return naties;
	}

	public static void enablePromotion(String data) {
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("incognito");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			Map<String, Object> prefs = new HashMap<String, Object>();

			prefs.put("profile.default_content_settings.state.flash", 1); // # 1->allow
			prefs.put("profile.default_content_setting_values.plugins", 1);
			prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("PluginsAllowedForUrls", data);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			// fdriver.get(Constants.URL+"/user/"+Constants.accountID+"/edit?destination=admin/people");
			driver.get(data + "/admin/modules");
			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("name")).sendKeys("admin");
			driver.findElement(By.name("pass")).clear();
			driver.findElement(By.name("pass")).sendKeys("C0zy123$");
			driver.findElement(By.name("op")).click();
			// defaultAdminLogin("C0zy123$");
			String errormessage = driver.getPageSource();
			if (driver.getTitle().contains("Access") || driver.getTitle().contains("Error")
					|| driver.getTitle().contains("Denied")) {
				int firstperiod = data.indexOf(".");
				int secondperiod = data.indexOf(".", firstperiod + 1);
				String labelname = data.substring(firstperiod + 1, secondperiod);
				System.out.println(labelname);
				System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
				int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
				String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
				// String adminpass1=Constants.admin_password_list[];
				// *[@data-game-id='internal_netentstarbursthtml']
				System.out.println(len);
				System.out.println(adminpass);
				driver.findElement(By.name("name")).clear();
				driver.findElement(By.name("name")).sendKeys("admin");
				driver.findElement(By.name("pass")).clear();
				driver.findElement(By.name("pass")).sendKeys(adminpass);
				driver.findElement(By.name("op")).click();
				// defaultAdminLogin(adminpass);
			}
			if (driver.getTitle().contains("Error"))
				driver.get(data + "/admin/modules");
			if (driver.getTitle().contains("Modules")) {
				new WebDriverWait(driver, 5).until(ExpectedConditions
						.visibilityOfElementLocated(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable")));
				WebElement promomodule = driver
						.findElement(By.xpath("//*[@for='edit-modules-nucleus-nucleus-promotion-crazy-days-enable']"));
				WebElement promomodulecheckbox = driver
						.findElement(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable"));

				// System.out.println(driver
				// .findElement(By.id("edit-modules-nucleus-nucleus-promotion-cashtourney-enable")).isSelected());
				System.out.println(promomodulecheckbox.isSelected());
				// System.out.println(driver.findElement(By.id("edit-modules-nucleus-nucleus-bbn-bridezilla-enable")).isSelected());
				if (!promomodulecheckbox.isSelected()) {
					// driver.findElement(
					// By.xpath("//*[@for='edit-modules-nucleus-nucleus-promotion-cashtourney-enable']")).click();
					promomodule.click();
					// Thread.sleep(1000);
					captureScreenWithFilename(getLabel(data) + "_Promo_Enable_" + Instant.now().getEpochSecond());
					System.out.println("Enabled the promotion on " + getLabel(data) + ", Saving configuration");
					ExcelUtils.setCellData("Enabled the promotion on " + getLabel(data) + ", Saving configuration",
							DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
					// driver.findElement(By.xpath("/html/body")).sendKeys(Keys.RETURN);
					// Thread.sleep(3000);
					driver.findElement(By.xpath("//*[@id='edit-actions']/child::*")).sendKeys(Keys.RETURN);
					captureScreenWithFilename(getLabel(data) + "_Promo_SaveConfig_" + Instant.now().getEpochSecond());
					// try {
					// System.out.println(promomodulecheckbox.isSelected());
					// } catch (StaleElementReferenceException e) {
					// // TODO Auto-generated catch block
					// System.out.println(promomodulecheckbox.isSelected());
					// }
					int attempts = 0;
					while (attempts < 2) {
						try {
							System.out.println(promomodulecheckbox.isSelected());
							break;
						} catch (StaleElementReferenceException e) {
						}
						attempts++;
					}
				} else
					System.out.println("Promotion is already enabled on " + getLabel(data) + ", Saving configuration");
			}
			driver.get(data + "/admin/config/development/performance");
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit")));
			driver.findElement(By.id("edit-submit")).click();
			captureScreenWithFilename(getLabel(data) + "_Promo_ClearCache_" + Instant.now().getEpochSecond());
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"console\"]/div[2]")));
			driver.findElement(By.id("edit-clear")).click();
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"console\"]/div[2]/h2")));
			System.out.println("Cleared cache");
			captureScreenWithFilename(getLabel(data) + "_Promo_CacheCleared_" + Instant.now().getEpochSecond());
			System.out.println(driver.findElement(By.xpath("//*[@id=\"console\"]/div[2]/h2")).getText());// *[@id="console"]/div[2]/h2
			Thread.sleep(1000);
			driver.get(data + "/admin/modules");
			WebElement promomodule = driver
					.findElement(By.xpath("//*[@for='edit-modules-nucleus-nucleus-promotion-crazy-days-enable']"));
			WebElement promomodulecheckbox = driver
					.findElement(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable"));
			System.out.println(promomodulecheckbox.isSelected());
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", promomodule);
			captureScreenWithFilename(getLabel(data) + "_Promo_CheckingCheckbox_" + Instant.now().getEpochSecond());
			// driver.findElement(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable")).;
			driver.close();
		} catch (Exception e1) {
			DriverScript.bResult = false;
			e1.printStackTrace();
			// https://www.pawprintbingo.com/
			// https://www.spinandbingo.com/
			// https://www.gumballbingo.com/
			// https://www.merrygobingo.com/
		}
	}

	@AfterSuite
	public static void arrangeFiles() throws IOException {
		System.out.println("Making Changes to files");
		File source = new File("C:\\Users\\vkolla\\.jenkins\\workspace\\Cozy-Daily-Regression\\DataEngine1.xlsx");
		File dest = new File("C:\\Users\\vkolla\\.jenkins\\workspace\\Cozy-Daily-Regression\\OutputReport.xlsx");
		File brandnew = new File("C:\\Users\\vkolla\\.jenkins\\workspace\\Cozy-Daily-Regression\\brandnew.xlsx");
		FileUtils.copyFile(source, dest);
		System.out.println("Output Report updated");
		FileUtils.copyFile(brandnew, source);
		System.out.println("Input Sheet updated");
	}

	public static void createEntity(String data) {
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("incognito");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			Map<String, Object> prefs = new HashMap<String, Object>();

			prefs.put("profile.default_content_settings.state.flash", 1); // # 1->allow
			prefs.put("profile.default_content_setting_values.plugins", 1);
			prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("PluginsAllowedForUrls", data);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			// fdriver.get(Constants.URL+"/user/"+Constants.accountID+"/edit?destination=admin/people");
			driver.get(data + "/admin");
			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("name")).sendKeys("admin");
			driver.findElement(By.name("pass")).clear();
			driver.findElement(By.name("pass")).sendKeys("C0zy123$");
			driver.findElement(By.name("op")).click();
			// driver.get(data + "/admin/entity-promotions");
			// defaultAdminLogin("C0zy123$");
			// String errormessage = driver.getPageSource();
			System.out.println(driver.getTitle());
			if (driver.getTitle().contains("Access") || driver.getTitle().contains("Error")
					|| driver.getTitle().contains("Denied")) {
				System.out.println("Trying with this weird password");
				int firstperiod = data.indexOf(".");
				int secondperiod = data.indexOf(".", firstperiod + 1);
				String labelname = data.substring(firstperiod + 1, secondperiod);
				System.out.println(labelname);
				System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
				int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
				String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
				// String adminpass1=Constants.admin_password_list[];
				// *[@data-game-id='internal_netentstarbursthtml']
				System.out.println(len);
				System.out.println(adminpass);
				driver.findElement(By.name("name")).clear();
				driver.findElement(By.name("name")).sendKeys("admin");
				driver.findElement(By.name("pass")).clear();
				driver.findElement(By.name("pass")).sendKeys(adminpass);
				driver.findElement(By.name("op")).click();
				// defaultAdminLogin(adminpass);
			}
			driver.get(data + "/admin/entity-promotions");
			int indexofsitecode = driver.getPageSource().indexOf("site_code");
			String sub = driver.getPageSource().substring(indexofsitecode, indexofsitecode + 23);
			String site_code = sub.substring(sub.indexOf(":") + 2, sub.indexOf(",") - 1);
			System.out.println(site_code);// 5112018
			if (driver.getTitle().contains("Error"))
				driver.get(data + "/admin/entity-promotions");
			if (site_code.contains("WBN"))
				driver.get(data + "/admin/entity-promotions/manage/64");
			if (site_code.contains("LBN"))
				driver.get(data + "/admin/entity-promotions/manage/68");
			if (site_code.contains("BBN"))
				driver.get(data + "/admin/entity-promotions/manage/57");
			if (site_code.contains("MCN"))
				driver.get(data + "/admin/entity-promotions/manage/25");
			System.out.println("Doing this for " + site_code + " on URL " + driver.getCurrentUrl());
			if (driver.getTitle().contains("Edit")) {
				try {
					// driver.findElement(
					// By.xpath("(//*[@href='/entity-promotions/68']/parent::*/following-sibling::*)[1]")).click();
					driver.findElement(By.id("edit-configuration")).sendKeys("admin");
					driver.findElement(By.id("edit-submit")).click();
					captureScreenWithFilename(getLabel(data) + Instant.now().getEpochSecond());
					dataEngineLog("The Promotion: £1k Slots Prize Draw has been saved.");
					driver.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// driver.get(data+"href=\"/admin/entity-promotions?page=1\"");
					driver.get(data + "/admin/entity-promotions?page=1");
					// driver.findElement(
					// By.xpath("(//*[@href='/entity-promotions/68']/parent::*/following-sibling::*)[1]")).click();
					driver.findElement(By.id("edit-configuration")).sendKeys("admin");
					driver.findElement(By.id("edit-submit")).click();
					captureScreenWithFilename(getLabel(data) + Instant.now().getEpochSecond());
					dataEngineLog("The Promotion: £1k Slots Prize Draw has been saved.");
					driver.close();
				}
			}
			//
			// new WebDriverWait(driver, 5).until(ExpectedConditions
			// .visibilityOfElementLocated(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable")));
			// WebElement promomodule = driver
			// .findElement(By.xpath("//*[@for='edit-modules-nucleus-nucleus-promotion-crazy-days-enable']"));
			// WebElement promomodulecheckbox = driver
			// .findElement(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable"));
			//
			// // System.out.println(driver
			// //
			// .findElement(By.id("edit-modules-nucleus-nucleus-promotion-cashtourney-enable")).isSelected());
			// System.out.println(promomodulecheckbox.isSelected());
			// //
			// System.out.println(driver.findElement(By.id("edit-modules-nucleus-nucleus-bbn-bridezilla-enable")).isSelected());
			// if (!promomodulecheckbox.isSelected()) {
			// // driver.findElement(
			// //
			// By.xpath("//*[@for='edit-modules-nucleus-nucleus-promotion-cashtourney-enable']")).click();
			// promomodule.click();
			// // Thread.sleep(1000);
			// captureScreenWithFilename(getLabel(data) + "_Promo_Enable_" +
			// Instant.now().getEpochSecond());
			// System.out.println("Enabled the promotion on " + getLabel(data) + ", Saving
			// configuration");
			// ExcelUtils.setCellData("Enabled the promotion on " + getLabel(data) + ",
			// Saving configuration",
			// DriverScript.iTestStep, Constants.Col_TestStepLog,
			// Constants.Sheet_TestSteps);
			// // driver.findElement(By.xpath("/html/body")).sendKeys(Keys.RETURN);
			// // Thread.sleep(3000);
			// driver.findElement(By.xpath("//*[@id='edit-actions']/child::*")).sendKeys(Keys.RETURN);
			// captureScreenWithFilename(getLabel(data) + "_Promo_SaveConfig_" +
			// Instant.now().getEpochSecond());
			// // try {
			// // System.out.println(promomodulecheckbox.isSelected());
			// // } catch (StaleElementReferenceException e) {
			// // // TODO Auto-generated catch block
			// // System.out.println(promomodulecheckbox.isSelected());
			// // }
			// int attempts = 0;
			// while (attempts < 2) {
			// try {
			// System.out.println(promomodulecheckbox.isSelected());
			// break;
			// } catch (StaleElementReferenceException e) {
			// }
			// attempts++;
			// }
			// } else
			// System.out.println("Promotion is already enabled on " + getLabel(data) + ",
			// Saving configuration");
			// }
			// driver.get(data + "/admin/config/development/performance");
			// new WebDriverWait(driver,
			// 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit")));
			// driver.findElement(By.id("edit-submit")).click();
			// captureScreenWithFilename(getLabel(data) + "_Promo_ClearCache_" +
			// Instant.now().getEpochSecond());
			// new WebDriverWait(driver, 5)
			// .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"console\"]/div[2]")));
			// driver.findElement(By.id("edit-clear")).click();
			// new WebDriverWait(driver, 5)
			// .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"console\"]/div[2]/h2")));
			// System.out.println("Cleared cache");
			// captureScreenWithFilename(getLabel(data) + "_Promo_CacheCleared_" +
			// Instant.now().getEpochSecond());
			// System.out.println(driver.findElement(By.xpath("//*[@id=\"console\"]/div[2]/h2")).getText());//
			// *[@id="console"]/div[2]/h2
			// Thread.sleep(1000);
			// driver.get(data + "/admin/modules");
			// WebElement promomodule = driver
			// .findElement(By.xpath("//*[@for='edit-modules-nucleus-nucleus-promotion-crazy-days-enable']"));
			// WebElement promomodulecheckbox = driver
			// .findElement(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable"));
			// System.out.println(promomodulecheckbox.isSelected());
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", promomodule);
			// captureScreenWithFilename(getLabel(data) + "_Promo_CheckingCheckbox_" +
			// Instant.now().getEpochSecond());
			// //
			// driver.findElement(By.id("edit-modules-nucleus-nucleus-promotion-crazy-days-enable"));
			// driver.close();
		} catch (Exception e1) {
			DriverScript.bResult = false;
			e1.printStackTrace();
			// https://www.pawprintbingo.com/
			// https://www.spinandbingo.com/
			// https://www.gumballbingo.com/
			// https://www.merrygobingo.com/
		}
	}

	public static void createEntity20(String data) {
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("incognito");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			Map<String, Object> prefs = new HashMap<String, Object>();

			prefs.put("profile.default_content_settings.state.flash", 1); // # 1->allow
			prefs.put("profile.default_content_setting_values.plugins", 1);
			prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("PluginsAllowedForUrls", data);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			// fdriver.get(Constants.URL+"/user/"+Constants.accountID+"/edit?destination=admin/people");
			driver.get(data + "/admin");
			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("name")).sendKeys("admin");
			driver.findElement(By.name("pass")).clear();
			driver.findElement(By.name("pass")).sendKeys("C0zy123$");
			driver.findElement(By.name("op")).click();
			// driver.get(data + "/admin/entity-promotions");
			// defaultAdminLogin("C0zy123$");
			// String errormessage = driver.getPageSource();
			System.out.println(driver.getTitle());
			if (driver.getTitle().contains("Access") || driver.getTitle().contains("Error")
					|| driver.getTitle().contains("Denied")) {
				System.out.println("Trying with this weird password");
				int firstperiod = data.indexOf(".");
				int secondperiod = data.indexOf(".", firstperiod + 1);
				String labelname = data.substring(firstperiod + 1, secondperiod);
				System.out.println(labelname);
				System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
				int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
				String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
				// String adminpass1=Constants.admin_password_list[];
				// *[@data-game-id='internal_netentstarbursthtml']
				System.out.println(len);
				System.out.println(adminpass);
				driver.findElement(By.name("name")).clear();
				driver.findElement(By.name("name")).sendKeys("admin");
				driver.findElement(By.name("pass")).clear();
				driver.findElement(By.name("pass")).sendKeys(adminpass);
				driver.findElement(By.name("op")).click();
				// defaultAdminLogin(adminpass);
			}

			if (driver.getTitle().contains("Error"))
				driver.get(data + "/admin");
			driver.get(data + "/admin/config/system/cron");

			if (driver.getTitle().contains("Cron")) {
				try {
					new WebDriverWait(driver, 10)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-cron-safe-threshold")));
					WebElement cron = driver.findElement((By.id("edit-cron-safe-threshold")));
					Select scron = new Select(cron);
					scron.selectByIndex(0);
					driver.findElement(By.id("edit-submit")).click();
					captureScreenWithFilename(getLabel(data) + "_SaveNever_" + Instant.now().getEpochSecond());
					// driver.findElement(By.id("edit-run")).click();
					// captureScreenWithFilename(getLabel(data) + "_CronRun_" +
					// Instant.now().getEpochSecond());
					System.out.println("Doing this for " + data + " on URL " + driver.getCurrentUrl());
					driver.close();
				} catch (Exception e) {
					// driver.close();
					DriverScript.bResult = false;
					e.printStackTrace();
					captureScreenWithFilename(getLabel(data) + "_ERROR_" + Instant.now().getEpochSecond());
				}
			}
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", promomodule);
			// driver.close();
		} catch (Exception e1) {
			DriverScript.bResult = false;
			e1.printStackTrace();
			// https://www.pawprintbingo.com/
			// https://www.spinandbingo.com/
			// https://www.gumballbingo.com/
			// https://www.merrygobingo.com/
		}
	}

	public static void dataEngineLog(String data) throws Exception {
		if (datalog != null)
			datalog = datalog + "\n" + data;
		ExcelUtils.setCellData(datalog, DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
		// System.out.println("Datalog is \n\"" + datalog + "\"");
		Log.info(datalog);
	}

	public static void GDPR(String data) throws InterruptedException {

		//////////////// REGISTRATION BEGIN ////////////////
		Constants.URL = data;
		String labelpassword = null;
		Constants.rand = (int) ((long) Math.floor(Math.random() * 9_0000L) + 1_0000);
		// Constants.rand = (long) Math.random(); //on a roll
		String username = "avenger" + Constants.rand;
		String email = username + "@yopmail.com";
		defaultBrowseClient(data);
		captureScreen();
		driver.get(data + "/user/register");
		String pagesource = driver.getPageSource();
		int sitecodestartindex = pagesource.indexOf("site_code");
		String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
		System.out.println(sitecode);
		// driver.findElement(By.id("edit-name")).clear();
		// driver.findElement(By.id("edit-name")).sendKeys(username);

		if (sitecode.equals("LBN") || sitecode.equals("WBN") || sitecode.equals("BBN") || sitecode.equals("GVN")
				|| sitecode.equals("FBN") || sitecode.equals("SBN")) {
			try {
				// System.out.println("ocha loop loki");
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-name")));
				try {
					driver.findElement(By.id("edit-name")).clear();
					driver.findElement(By.id("edit-name")).sendKeys(username);
				} catch (Exception e3) {

				}
				try {
					driver.findElement(By.id("edit-first-name")).clear();
					driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				} catch (Exception e2) {
					driver.findElement(By.id("edit-first-name--2")).clear();// FOR ALL LDN SITES
					driver.findElement(By.id("edit-first-name--2")).sendKeys(Constants.FirstName);
				}
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				driver.findElement(By.xpath("//*[@for='edit-gender-male']")).click();
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				System.out.println("pass2entered, clicking register");

				try {
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					// driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
				} catch (Exception e3) {
				}
				try {
					driver.findElement(By.className("register-login-button")).click();
					// driver.findElement(By.className("register-login-button")).click();
				} catch (Exception e3) {
				}
				try {
					driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
					// driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
				} catch (Exception e3) {
				}
				// driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
				// driver.findElement(By.xpath("//*[@class='register-login-button']")).click();

				System.out.println("clicked register");

				// Thread.sleep(5000);
				// System.out.println("ocha loop loki");
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-mobile")));
				System.out.println("waiting until");
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);

				///////// SWATHI'S TASK 5172018

				// driver.findElement(By.xpath("//*[@for='edit-opinion-no']")).click();
				// // driver.findElement(By.id("edit-opinion-no")).click();
				// captureScreenWithFilename(getLabel(data) + "_" + username + "_" + "Offers
				// No");
				// Thread.sleep(1000);
				// driver.findElement(By.xpath("//*[@for='edit-opinion-yes']")).click();

				// driver.findElement(By.id("edit-opinion-yes")).click();
				// driver.findElement(By.xpath("//[@for='edit-opinion-yes'")).click();
				String[] offers = { "call", "text", "telemarketing" };
				String[] emailyn = { "emailyes", "emailno" };
				int offersrand = new Random().nextInt(offers.length);
				int emailynrand = new Random().nextInt(emailyn.length);
				System.out.println("Offers random is: " + offersrand);
				System.out.println("Email random is: " + emailynrand);
				// driver.findElement(By.id("edit-receive-email")).click();
				// driver.findElement(By.id("edit-receive-call")).click();
				// driver.findElement(By.id("edit-receive-text")).click();
				// driver.findElement(By.id("edit-receive-telemarketing")).click();
				// captureScreenWithFilename(getLabel(data) + "_" + username + "_" + "All
				// Unchecked");
				if (emailynrand == 0) {
					driver.findElement(By.id("edit-receive-email")).click();
					ExcelUtils.setCellData("Email unchecked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
				} else {
					ExcelUtils.setCellData("Email checked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
				}
				// driver.findElement(By.id("edit-receive-call")).click();
				// driver.findElement(By.id("edit-receive-text")).click();
				// driver.findElement(By.id("edit-receive-telemarketing")).click();
				if (offersrand == 0) {
					driver.findElement(By.id("edit-receive-call")).click();
					ExcelUtils.setCellData("Call unchecked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
				} else if (offersrand == 1) {
					driver.findElement(By.id("edit-receive-text")).click();
					ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text unchecked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
				} else if (offersrand == 2) {
					driver.findElement(By.id("edit-receive-telemarketing")).click();
					ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post unchecked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);

				}
				// captureScreenWithFilename(getLabel(data) + "_" + username + "_" + "Offers
				// Yes");

				//////// SWATHI'S TASK 5172018

				driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
				driver.findElement(By.id("edit-lookup-address")).click();
				Thread.sleep(5000);
				WebElement getAddr = driver.findElement((By.xpath("//select[@id='edit-address-list']")));
				Select addr = new Select(getAddr);
				addr.selectByValue("1");
				// selectByVisibleText("53-54 Regency Square");
				// Thread.sleep(3000);
				ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
						Constants.Sheet_TestSteps);
				if (getLabel(data).equals("bootybingo") || getLabel(data).equals("readysetbingo")
						|| getLabel(data).equals("lmaobingo") || getLabel(data).equals("carbootbingo")
						|| getLabel(data).equals("newlookbingo") || getLabel(data).equals("mummiesbingo")
						|| getLabel(data).equals("bingolegacy") || getLabel(data).equals("luckysocksbingo")
						|| getLabel(data).equals("vampirebingo") || getLabel(data).equals("casinomagix")
						|| getLabel(data).equals("slotdiamond") || getLabel(data).equals("unitedcoloursofbingo")) {
					Thread.sleep(4000);
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]")).click();
					// WebElement e1 =
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]"));
					// boolean isTermsChecked = e1.isSelected();
					// System.out.println(Constants.URL);
					// System.out.println(isTermsChecked);
					// if (!isTermsChecked) {
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]")).click();
					// }
					// WebElement e2 =
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]"));
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).click();
					// boolean isPrivacyChecked = e2.isSelected();
					// System.out.println(Constants.URL);
					// System.out.println(isPrivacyChecked);
					// if (!isPrivacyChecked) {
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).click();
					// }
					// WebElement e3 =
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]"));
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]")).click();
					// boolean isAgeChecked = e3.isSelected();
					// System.out.println(Constants.URL);
					// System.out.println(isAgeChecked);
					// if (!isAgeChecked) {
					// driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]")).click();
					// }
				} else {
					try {

						try {
							driver.findElement(By.name("terms-of-use")).click();
						} catch (Exception e) {
							try {
								driver.findElement(By.xpath(
										"//*[@id=\"user-register-form\"]/div/div/div[5]/div[1]/div[2]/div[9]/div/label"))
										.click();
							} catch (Exception e1) {
								try {
									driver.findElement(By.xpath("//*[@id=\"edit-terms-of-use\"]")).click();
								} catch (Exception e2) {

									driver.findElement(By.xpath("//*[@for=\"edit-terms-of-use\"]")).click();
								}
							}
						}

					}

					catch (Exception e) {// FOR 8BG ACQUIRED SITES
						System.out.println("In catch loop");
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]")).click();
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).click();
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]")).click();
					}
				}

				driver.findElement(By.id("edit-submit")).click();
				ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
						Constants.Sheet_TestSteps);
				captureScreen();
				String successfulregistration;
				try {
					successfulregistration = driver.findElement(By.xpath("//*[@class='messages__list']/child::*[1]"))
							.getText();
					System.out.println(successfulregistration);
					if (successfulregistration.contains("has been successfully registered")) {
						ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
								Constants.Sheet_TestSteps);
					}
				} catch (Exception e) {

				}
				Thread.sleep(1000);
				driver.close();
			} catch (Exception e) {
				e.printStackTrace();
				DriverScript.bResult = false;
				System.out.println("LBN/BBN/WBN/FBN/SBN Registration failed");
				// driver.close();
			}

		} else if (sitecode.equals("MCN")) {

			try {
				try {
					labelpassword = getEnvLabelPassword(data);
					System.out.println(labelpassword);
				} catch (Exception e) {

				}
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-first-name")));
				driver.findElement(By.id("edit-first-name")).clear();
				driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click();// vijay to change the
																								// xpath for this and
																								// the one below

				///////// SWATHI'S TASK 5172018

				String[] offers = { "call", "text", "telemarketing" };
				String[] emailyn = { "emailyes", "emailno" };
				int offersrand = new Random().nextInt(offers.length);
				int emailynrand = new Random().nextInt(emailyn.length);
				System.out.println("Offers random is: " + offersrand);
				System.out.println("Email random is: " + emailynrand);
				driver.findElement(By.id("edit-receive-email")).click();
				if (emailynrand == 0) {
					driver.findElement(By.id("edit-receive-email")).click();
					ExcelUtils.setCellData("Email unchecked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
				} else {
					ExcelUtils.setCellData("Email checked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
				}
				// driver.findElement(By.id("edit-receive-call")).click();
				// driver.findElement(By.id("edit-receive-text")).click();
				// driver.findElement(By.id("edit-receive-telemarketing")).click();
				if (offersrand == 0) {
					driver.findElement(By.id("edit-receive-call")).click();
					ExcelUtils.setCellData("Call unchecked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
				} else if (offersrand == 1) {
					driver.findElement(By.id("edit-receive-text")).click();
					ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text unchecked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
				} else if (offersrand == 2) {
					driver.findElement(By.id("edit-receive-telemarketing")).click();
					ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post unchecked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
					captureScreen();
				}

				//////// SWATHI'S TASK 5172018

				WebElement country = driver.findElement((By.id("edit-country")));
				Select scountry = new Select(country);
				scountry.selectByIndex(1);
				WebElement nationality = driver.findElement((By.id("edit-nationality")));
				Select snationality = new Select(nationality);
				snationality.selectByIndex(1);
				driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
				System.out.println("got till city");
				driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
				System.out.println("got till address");
				driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
				System.out.println("got till checkbox");
				if (labelpassword.contains("slotdiamond") || labelpassword.contains("casinomagix")) {
					driver.findElement(By.id("edit-confirm-terms")).click();
					WebElement e1 = driver.findElement(By.id("edit-confirm-terms"));
					boolean isTermsChecked = e1.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isTermsChecked);
					if (!isTermsChecked) {
						driver.findElement(By.id("edit-confirm-terms")).click();
					}
					driver.findElement(By.id("edit-confirm-privacy")).click();
					WebElement e2 = driver.findElement(By.id("edit-confirm-privacy"));
					boolean isPrivacyChecked = e2.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isPrivacyChecked);
					if (!isPrivacyChecked) {
						driver.findElement(By.id("edit-confirm-privacy")).click();
					}
					driver.findElement(By.id("edit-confirm-age")).click();
					WebElement e3 = driver.findElement(By.id("edit-confirm-age"));
					boolean isAgeChecked = e3.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isAgeChecked);
					if (!isAgeChecked) {
						driver.findElement(By.id("edit-confirm-age")).click();
					}
				} else {
					driver.findElement(By.id("edit-terms-of-use")).click();
				}
				System.out.println("In The End");
				driver.findElement(By.id("edit-submit")).click();
				captureScreen();
				String successfulregistration = driver.findElement(By.xpath("//*[@class='messages__list']/child::*[1]"))
						.getText();
				System.out.println(successfulregistration);
				if (successfulregistration.contains("has been successfully registered")) {
					ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
							Constants.Sheet_TestSteps);
				}
			} catch (Exception e) {

				DriverScript.bResult = false;
				System.out.println("MCN Registration failed");
			}

		} else if (sitecode.equals("8BG")) {
			try {
				driver.findElement(By.id("edit-name")).clear();
				driver.findElement(By.id("edit-name")).sendKeys(username);
				driver.findElement(By.id("edit-first-name")).clear();
				driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
				try {
					driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click(); // put this in Try
																									// because only one
																									// of the 8BG sites
																									// has Gender as a
																									// required field
				} catch (Exception e) {
				}
				driver.findElement(By.id("edit-mail")).sendKeys(email);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				try {
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					Thread.sleep(1000);
					try {
						driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					driver.findElement(By.xpath("//*[@class='collapse-button']")).click();
				} catch (Exception e) {
					e.printStackTrace();
				}
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				WebElement country = driver.findElement((By.id("edit-country")));
				Select scountry = new Select(country);
				scountry.selectByIndex(1);
				WebElement nationality = driver.findElement((By.id("edit-nationality")));
				Select snationality = new Select(nationality);
				snationality.selectByIndex(1);
				driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
				driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
				try {
					driver.findElement(By.id("edit-city")).sendKeys(Constants.City);
				} catch (Exception e4) {
					driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
				}

				//////// SWATHI'S TASK 5172018

				String[] offers = { "call", "text", "telemarketing" };
				String[] emailyn = { "emailyes", "emailno" };
				int offersrand = new Random().nextInt(offers.length);
				int emailynrand = new Random().nextInt(emailyn.length);
				System.out.println("Offers random is: " + offersrand);
				System.out.println("Email random is: " + emailynrand);
				driver.findElement(By.id("edit-receive-email")).click();
				if (emailynrand == 0) {
					driver.findElement(By.id("edit-receive-email")).click();
					ExcelUtils.setCellData("Email unchecked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
				} else {
					ExcelUtils.setCellData("Email checked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
				}
				// driver.findElement(By.id("edit-receive-call")).click();
				// driver.findElement(By.id("edit-receive-text")).click();
				// driver.findElement(By.id("edit-receive-telemarketing")).click();
				if (offersrand == 0) {
					driver.findElement(By.id("edit-receive-call")).click();
					ExcelUtils.setCellData("Call unchecked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
				} else if (offersrand == 1) {
					driver.findElement(By.id("edit-receive-text")).click();
					ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text unchecked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
				} else if (offersrand == 2) {
					driver.findElement(By.id("edit-receive-telemarketing")).click();
					ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
					ExcelUtils.setCellData("Post unchecked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
					captureScreen();
				}

				//////// SWATHI'S TASK 5172018

				driver.findElement(By.id("edit-confirm-terms")).click();
				WebElement e1 = driver.findElement(By.id("edit-confirm-terms"));
				boolean isTermsChecked = e1.isSelected();
				System.out.println(Constants.URL);
				System.out.println(isTermsChecked);
				if (!isTermsChecked) {
					driver.findElement(By.id("edit-confirm-terms")).click();
				}
				driver.findElement(By.id("edit-confirm-privacy")).click();
				WebElement e2 = driver.findElement(By.id("edit-confirm-privacy"));
				boolean isPrivacyChecked = e2.isSelected();
				System.out.println(Constants.URL);
				System.out.println(isPrivacyChecked);
				if (!isPrivacyChecked) {
					driver.findElement(By.id("edit-confirm-privacy")).click();
				}
				driver.findElement(By.id("edit-confirm-age")).click();
				WebElement e3 = driver.findElement(By.id("edit-confirm-age"));
				boolean isAgeChecked = e3.isSelected();
				System.out.println(Constants.URL);
				System.out.println(isAgeChecked);
				if (!isAgeChecked) {
					driver.findElement(By.id("edit-confirm-age")).click();
				}
				ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
						Constants.Sheet_TestSteps);
				driver.findElement(By.id("edit-submit")).click();
				captureScreen();
				String successfulregistration = driver.findElement(By.xpath("//*[@class='messages__list']/child::*[1]"))
						.getText();
				System.out.println(successfulregistration);
				if (successfulregistration.contains("has been successfully registered")) {
					ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
							Constants.Sheet_TestSteps);
				}
				driver.close();// 5172018
			} catch (Exception e) {
				e.printStackTrace();
				DriverScript.bResult = false;
				System.out.println("8BG Registration failed");
			}
		}

		///////////////////////// REGISTRATION END //////////////////////////////

		//////////////////////// GET ACCOUNT ID BEGIN ////////////////////////////////
		// 5172018 SWATHI'S TASK
		//
		// try {
		// driver.get(Constants.URL + "/user");
		// String reflink =
		// driver.findElement(By.xpath("//*[@rel=\"shortlink\"]")).getAttribute("href");
		//
		// System.out.println(reflink);
		// Constants.drupalID = reflink.substring(reflink.lastIndexOf('/') + 1);
		// System.out.println(Constants.drupalID);
		// int drupalAcc = (Integer.parseInt(Constants.drupalID));
		// // boolean isNumeric = Constants.drupalID.chars().allMatch(
		// Character::isDigit
		// // );
		// // System.out.print(isNumeric);
		// if (Constants.drupalID.chars().allMatch(Character::isDigit)) {
		// // DriverScript.bResult = false;
		// }
		// Constants.sessionid = getSessionID();
		// Constants.serviceURL = getServiceURL();
		// } catch (Exception e) {
		// System.out.print("Could not click, so refreshing the page");
		// // DriverScript.bResult = false;
		// System.out.print(DriverScript.bResult);
		// }
		//
		// /////////////////////// GET ACCOUNT ID END //////////////////////
		//
		// //////////////////////// CONVERT BETA TESTER BEGIN
		// //////////////////////// //////////////////////////////
		//
		// try {
		// System.setProperty("webdriver.gecko.driver",
		// "C:\\Users\\vkolla\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		// WebDriver fdriver = new FirefoxDriver();
		// //
		// fdriver.get(Constants.URL+"/user/"+Constants.accountID+"/edit?destination=admin/people");
		// fdriver.get(Constants.URL + "/admin" + "/people");
		//
		// try {
		// fdriver.findElement(By.name("name")).clear();
		// fdriver.findElement(By.name("name")).sendKeys("admin");
		// fdriver.findElement(By.name("pass")).clear();
		// fdriver.findElement(By.name("pass")).sendKeys("C0zy123$");
		// fdriver.findElement(By.name("op")).click();
		// String errormessage = fdriver.getPageSource();
		// if (errormessage.contains("Oops! Sorry, you have entered an invalid username
		// or password.")) {
		// int firstperiod = Constants.URL.indexOf(".");
		// int secondperiod = Constants.URL.indexOf(".", firstperiod + 1);
		// String labelname = Constants.URL.substring(firstperiod + 1, secondperiod);
		// System.out.println(labelname);
		// System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
		// int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
		// String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
		// // String adminpass1=Constants.admin_password_list[];
		// System.out.println(len);
		// System.out.println(adminpass);
		// fdriver.findElement(By.name("name")).clear();
		// fdriver.findElement(By.name("name")).sendKeys("admin");
		// fdriver.findElement(By.name("pass")).clear();
		// fdriver.findElement(By.name("pass")).sendKeys(adminpass);
		// fdriver.findElement(By.name("op")).click();
		// }
		// } catch (Exception e1) {
		//
		// }
		// captureScreen();
		// try {
		//
		// fdriver.findElement(
		// By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
		// .click();
		// ///
		// html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a
		// fdriver.findElement(
		// By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
		// .click();
		// } catch (Exception e) {
		//
		// fdriver.findElement(
		// By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
		// .click();
		// fdriver.findElement(
		// By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
		// .click();
		// }
		// fdriver.findElement(By.name("accounts[" + Constants.drupalID + "]")).click();
		// WebElement role = fdriver.findElement((By.name("operation")));
		// role.click();
		// Select btester = new Select(role);
		// btester.selectByValue("add_role-4");
		// captureScreen();
		// fdriver.findElement(By.id("edit-submit--2")).click();
		// Thread.sleep(2000);
		// fdriver.quit();
		//
		// } catch (Exception e) {
		//
		// // DriverScript.bResult = false;
		// }
		//
		// //////////////////////// CONVERT BETA TESTER END
		// //////////////////////// /////////////////////////////////
		// ////////////////////// test deposit ////////////////////////////
		// int indexofsid = driver.getPageSource().indexOf("session_id");
		// String sessionid = driver.getPageSource().substring(indexofsid + 12,
		// indexofsid + 44);
		// Constants.sessionid = driver.getPageSource().substring(indexofsid + 12,
		// indexofsid + 44);
		// testDeposit("15");
		// 5172018 SWATHI'S TASK
	}

	public static void updateGDPR(String data) throws Exception {
		navDashboardMenu("Profile");
		navDashboardProfileSubMenu("Profile");
		new WebDriverWait(driver, 5).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='edit-profile-link']/child::*")));
		driver.findElement(By.xpath("//*[@class='edit-profile-link']/child::*")).click();
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("fieldset-legend")));
		Thread.sleep(2000);
		String[] offers = { "call", "text", "telemarketing" };
		String[] emailyn = { "emailyes", "emailno" };
		String[] notifyn = { "notify", "notifn" };
		int offersrand = new Random().nextInt(offers.length);
		int emailynrand = new Random().nextInt(emailyn.length);
		int notifynrand = new Random().nextInt(notifyn.length);
		System.out.println("Offers random is: " + offersrand);
		System.out.println("Email random is: " + emailynrand);
		if (emailynrand == 0) {
			driver.findElement(By.id("edit-receive-email")).click();
			captureScreenWithFilename(getLabel(Constants.URL) + "_editprofile_beforechanges");
			ExcelUtils.setCellData("Email unchecked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
		} else {
			captureScreenWithFilename(getLabel(Constants.URL) + "_editprofile_beforechanges");
			ExcelUtils.setCellData("Email checked", DriverScript.iTestStep, 8, Constants.Sheet_TestSteps);
		}
		// driver.findElement(By.id("edit-receive-call")).click();
		// driver.findElement(By.id("edit-receive-text")).click();
		// driver.findElement(By.id("edit-receive-telemarketing")).click();
		if (offersrand == 0) {
			captureScreenWithFilename(getLabel(Constants.URL) + "_editprofile_beforechanges");
			driver.findElement(By.id("edit-receive-call")).click();
			ExcelUtils.setCellData("Call unchecked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
			ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
			ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
		} else if (offersrand == 1) {
			captureScreenWithFilename(getLabel(Constants.URL) + "_editprofile_beforechanges");
			driver.findElement(By.id("edit-receive-text")).click();
			ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
			ExcelUtils.setCellData("Text unchecked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
			ExcelUtils.setCellData("Post checked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);
		} else if (offersrand == 2) {
			captureScreenWithFilename(getLabel(Constants.URL) + "_editprofile_beforechanges");
			driver.findElement(By.id("edit-receive-telemarketing")).click();
			ExcelUtils.setCellData("Call checked", DriverScript.iTestStep, 9, Constants.Sheet_TestSteps);
			ExcelUtils.setCellData("Text checked", DriverScript.iTestStep, 10, Constants.Sheet_TestSteps);
			ExcelUtils.setCellData("Post unchecked", DriverScript.iTestStep, 11, Constants.Sheet_TestSteps);

		}
		if (notifynrand == 0) {
			driver.findElement(By.id("edit-push-notification-permission")).click();
		}
		captureScreenWithFilename(getLabel(Constants.URL) + "_editprofile_afterchanges");
		try {
			driver.findElement(By.id("edit-submit--16")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				driver.findElement(By.id("edit-submit--15")).click();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				driver.findElement(By.id("edit-submit--17")).click();
				e.printStackTrace();

			}
		}
	}

	public static void SEAccountsRegistration(String data) throws Exception {
		int numberofaccounts = ExcelUtils.getRowCount(Constants.Sheet_GamstopAccounts);
		String labelpassword = null;
		System.out.println(numberofaccounts);
		for (int i = 0; i < numberofaccounts; i++) {
			String client = "";
			long mobile = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			final String Mobile = Long.toString(mobile);

			Constants.rand = (int) ((long) Math.floor(Math.random() * 9_000_0L) + 1_000_0);
			String username = "seaccount" + Constants.rand;
			// try has to start here
			Constants.gafirstname = ExcelUtils.getCellData(i, Constants.Col_gafirstname,
					Constants.Sheet_GamstopAccounts);
			client = ExcelUtils.getCellData(i, 5, Constants.Sheet_GamstopAccounts);
			System.out.println(client);
			Constants.galastname = ExcelUtils.getCellData(i, Constants.Col_galastname, Constants.Sheet_GamstopAccounts);
			Constants.gadob = ExcelUtils.getCellData(i, Constants.Col_gadob, Constants.Sheet_GamstopAccounts);
			Constants.gaemail = "se." + Constants.rand + "@yopmail.com";

			Constants.gapostcode = "IM11EU";
			int firstslash = Constants.gadob.indexOf("/");
			int secondslash = Constants.gadob.lastIndexOf("/");
			String month = Constants.gadob.substring(1, firstslash);
			String day = Constants.gadob.substring(firstslash + 1, secondslash);
			String year = Constants.gadob.substring(secondslash + 1, Constants.gadob.length() - 1);
			System.out.println(Constants.gafirstname + "    " + Constants.galastname + "    " + Constants.gadob
					+ "     " + month + "/" + day + "/" + year + "  " + Constants.gaemail + "       "
					+ Constants.gapostcode + "     " + firstslash + "      " + secondslash);
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("incognito");
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
			driver.get(client);
			captureScreen();
			driver.get(client + "/user/register");
			String pagesource = driver.getPageSource();
			int sitecodestartindex = pagesource.indexOf("site_code");
			String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
			String sitecodefull = pagesource.substring(sitecodestartindex + 12, sitecodestartindex + 17);

			System.out.println(sitecode);
			if (sitecode.equals("LBN") || sitecode.equals("WBN") || sitecode.equals("BBN") || sitecode.equals("GVN")
					|| sitecode.equals("FBN") || sitecode.equals("SBN")) {

				try {
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-name")));
					driver.findElement(By.id("edit-name")).clear();
					driver.findElement(By.id("edit-name")).sendKeys(username);

					try {
						driver.findElement(By.id("edit-first-name")).clear();
						driver.findElement(By.id("edit-first-name")).sendKeys(Constants.gafirstname);
					} catch (Exception e4) {
						// TODO Auto-generated catch block
						driver.findElement(By.id("edit-first-name--2")).clear();// FOR ALL LDN SITES
						driver.findElement(By.id("edit-first-name--2")).sendKeys(Constants.gafirstname);
					}

					driver.findElement(By.id("edit-last-name")).clear();
					driver.findElement(By.id("edit-last-name")).sendKeys(Constants.galastname);
					driver.findElement(By.xpath("//*[@for='edit-gender-male']")).click();
					driver.findElement(By.id("edit-mail")).sendKeys(Constants.gaemail);
					driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
					driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
					captureScreen();
					try {
						driver.findElement(By.xpath("//*[@class='nextsssep-arrow']")).click();
						System.out.println("tried");
					} catch (Exception e2) {
						try {
							driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
							System.out.println("tried again");
						} catch (Exception e3) {
							driver.findElement(By.xpath("//*[@class='nextstep-arrow'][2]")).click();
							System.out.println("caught");
						}
					}

					// new WebDriverWait(driver,
					// 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-mobile")));
					new WebDriverWait(driver, 5).until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='date_of_birth[day]']")));
					new WebDriverWait(driver, 5).until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@name='date_of_birth[month]']")));
					new WebDriverWait(driver, 5).until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@name='date_of_birth[year]']")));
					System.out.println("waiting until");
					WebElement month1 = driver.findElement((By.xpath("//*[@name='date_of_birth[month]']")));
					WebElement day1 = driver.findElement((By.xpath("//*[@name='date_of_birth[day]']")));
					WebElement year1 = driver.findElement((By.xpath("//*[@name='date_of_birth[year]']")));
					System.out.println(day);
					System.out.println(month);
					System.out.println(year);
					Select sday = new Select(day1);
					Select smonth = new Select(month1);
					Select syear = new Select(year1);
					sday.selectByValue(day);
					smonth.selectByValue(month);
					syear.selectByValue(year);
					driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
					WebElement country = driver.findElement((By.id("edit-country")));
					Select scountry = new Select(country);
					scountry.selectByIndex(1);
					WebElement nationality = driver.findElement((By.id("edit-nationality")));
					Select snationality = new Select(nationality);
					snationality.selectByIndex(1);
					driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.gapostcode);
					driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-city--2")));
					driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
					try {
						try {
							driver.findElement(By.name("terms-of-use")).click();
						} catch (Exception e) {
							try {
								driver.findElement(By.xpath(
										"//*[@id=\"user-register-form\"]/div/div/div[5]/div[1]/div[2]/div[9]/div/label"))
										.click();
							} catch (Exception e1) {
								driver.findElement(By.xpath("//*[@id='edit-terms-of-use']")).click();
							}
						}
					} catch (Exception e) {// FOR 8BG ACQUIRED SITES
						System.out.println("In catch loop");
						driver.findElement(By.name("terms_of_use")).click();

						driver.findElement(By.xpath("//*[@for=\"edit-confirm-terms\"]")).click();
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-privacy\"]")).click();
						driver.findElement(By.xpath("//*[@for=\"edit-confirm-age\"]")).click();
					}
					captureScreen();
					driver.findElement(By.id("edit-submit")).click();
					ExcelUtils.setCellData(username, i, Constants.Col_garesult, Constants.Sheet_GamstopAccounts);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					DriverScript.bResult = false;
					Log.info("Registration failed");
				}

			} // end of IF
			else if (sitecode.equals("MCN")) {
				try {
					try {
						labelpassword = getEnvLabelPassword(data);
						System.out.println(labelpassword);
					} catch (Exception e) {

					}
					driver.findElement(By.id("edit-mobile")).sendKeys(Mobile);
					System.out.println(sitecodefull);
					if (sitecodefull.equals("VCMCN")) {
						driver.findElement(By.id("edit-first-name")).clear();
						driver.findElement(By.id("edit-first-name")).sendKeys(Constants.gafirstname);
						driver.findElement(By.id("edit-last-name")).clear();
						driver.findElement(By.id("edit-last-name")).sendKeys(Constants.galastname);
						driver.findElement(By.xpath("//*[@for='edit-gender-male']")).click();
						driver.findElement(By.id("edit-mail")).sendKeys(Constants.gaemail);
						driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
						driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
						driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
						WebElement day1 = driver.findElement((By.name("date_of_birth[day]")));
						WebElement month1 = driver.findElement((By.name("date_of_birth[month]")));
						WebElement year1 = driver.findElement((By.name("date_of_birth[year]")));

						new WebDriverWait(driver, 5).until(
								ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
						new WebDriverWait(driver, 5)
								.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
						new WebDriverWait(driver, 5)
								.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
						Select sday = new Select(day1);
						Select smonth = new Select(month1);
						Select syear = new Select(year1);
						sday.selectByValue(day);
						smonth.selectByValue(month);
						syear.selectByValue(year);
						WebElement country = driver.findElement((By.id("edit-country")));
						Select scountry = new Select(country);
						scountry.selectByIndex(1);
						WebElement nationality = driver.findElement((By.id("edit-nationality")));
						Select snationality = new Select(nationality);
						snationality.selectByIndex(1);
						driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
						System.out.println("got till city");
						new WebDriverWait(driver, 5)
								.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-city--2")));
						driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
						System.out.println("got till address");
						driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
						System.out.println("got till checkbox");
						driver.findElement(By.xpath("//*[@id='edit-terms-of-use']")).click();
						driver.findElement(By.id("edit-submit")).click();
						captureScreen();

					} else if (!sitecodefull.equals("VCMCN")) {

						driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
						driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
						WebElement day1 = driver.findElement((By.name("date_of_birth[day]")));
						WebElement month1 = driver.findElement((By.name("date_of_birth[month]")));
						WebElement year1 = driver.findElement((By.name("date_of_birth[year]")));
						new WebDriverWait(driver, 5).until(
								ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
						new WebDriverWait(driver, 5)
								.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
						new WebDriverWait(driver, 5)
								.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
						Select sday = new Select(day1);
						Select smonth = new Select(month1);
						Select syear = new Select(year1);
						sday.selectByValue(day);
						smonth.selectByValue(month);
						syear.selectByValue(year);
						driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
						new WebDriverWait(driver, 5)
								.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-first-name")));
						driver.findElement(By.id("edit-first-name")).clear();
						driver.findElement(By.id("edit-first-name")).sendKeys(Constants.gafirstname);
						driver.findElement(By.id("edit-last-name")).clear();
						driver.findElement(By.id("edit-last-name")).sendKeys(Constants.galastname);
						driver.findElement(By.id("edit-mail")).sendKeys(Constants.gaemail);
						driver.findElement(By.xpath("//*[@for='edit-gender-male']")).click();
						WebElement country = driver.findElement((By.id("edit-country")));
						Select scountry = new Select(country);
						scountry.selectByIndex(1);
						WebElement nationality = driver.findElement((By.id("edit-nationality")));
						Select snationality = new Select(nationality);
						snationality.selectByIndex(1);
						driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
						System.out.println("got till city");
						driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
						System.out.println("got till address");
						driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
						System.out.println("got till checkbox");
						if (labelpassword.contains("slotdiamond") || labelpassword.contains("casinomagix")) {
							driver.findElement(By.id("edit-confirm-terms")).click();
							WebElement e1 = driver.findElement(By.id("edit-confirm-terms"));
							boolean isTermsChecked = e1.isSelected();
							System.out.println(Constants.URL);
							System.out.println(isTermsChecked);
							if (!isTermsChecked) {
								driver.findElement(By.id("edit-confirm-terms")).click();
							}
							driver.findElement(By.id("edit-confirm-privacy")).click();
							WebElement e2 = driver.findElement(By.id("edit-confirm-privacy"));
							boolean isPrivacyChecked = e2.isSelected();
							System.out.println(Constants.URL);
							System.out.println(isPrivacyChecked);
							if (!isPrivacyChecked) {
								driver.findElement(By.id("edit-confirm-privacy")).click();
							}
							driver.findElement(By.id("edit-confirm-age")).click();
							WebElement e3 = driver.findElement(By.id("edit-confirm-age"));
							boolean isAgeChecked = e3.isSelected();
							System.out.println(Constants.URL);
							System.out.println(isAgeChecked);
							if (!isAgeChecked) {
								driver.findElement(By.id("edit-confirm-age")).click();
							}
						} else {
							driver.findElement(By.id("edit-terms-of-use")).click();
						}
						System.out.println("In The End");
						driver.findElement(By.id("edit-submit")).click();
						captureScreen();

					}
					ExcelUtils.setCellData(username, i, Constants.Col_garesult, Constants.Sheet_GamstopAccounts);
				} catch (Exception e) {
					e.printStackTrace();
					DriverScript.bResult = false;
					System.out.println("MCN Registration failed");
				}

			} else if (sitecode.equals("8BG")) {
				try {
					driver.findElement(By.id("edit-name")).clear();
					driver.findElement(By.id("edit-name")).sendKeys(username);
					driver.findElement(By.id("edit-first-name")).clear();
					driver.findElement(By.id("edit-first-name")).sendKeys(Constants.FirstName);
					driver.findElement(By.id("edit-last-name")).clear();
					driver.findElement(By.id("edit-last-name")).sendKeys(Constants.LastName);
					try {
						driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click(); // put this in
																										// Try
																										// because only
																										// one
																										// of the 8BG
																										// sites
																										// has Gender as
																										// a
																										// required
																										// field
					} catch (Exception e) {
					}
					driver.findElement(By.id("edit-mail")).sendKeys(Constants.gaemail);
					driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
					driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
					driver.findElement(By.id("edit-password-pass1")).clear();
					driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
					Thread.sleep(3000);
					driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					WebElement day2 = driver.findElement((By.name("date_of_birth[day]")));
					WebElement month2 = driver.findElement((By.name("date_of_birth[month]")));
					WebElement year2 = driver.findElement((By.name("date_of_birth[year]")));
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
					Select sday = new Select(day2);
					Select smonth = new Select(month2);
					Select syear = new Select(year2);
					sday.selectByValue(day);
					smonth.selectByValue(month);
					syear.selectByValue(year);
					driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
					WebElement country = driver.findElement((By.id("edit-country")));
					Select scountry = new Select(country);
					scountry.selectByIndex(1);
					WebElement nationality = driver.findElement((By.id("edit-nationality")));
					Select snationality = new Select(nationality);
					snationality.selectByIndex(1);
					driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
					driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
					Thread.sleep(2000);
					driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
					driver.findElement(By.id("edit-confirm-terms")).click();
					WebElement e1 = driver.findElement(By.id("edit-confirm-terms"));
					boolean isTermsChecked = e1.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isTermsChecked);
					if (!isTermsChecked) {
						driver.findElement(By.id("edit-confirm-terms")).click();
					}
					driver.findElement(By.id("edit-confirm-privacy")).click();
					WebElement e2 = driver.findElement(By.id("edit-confirm-privacy"));
					boolean isPrivacyChecked = e2.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isPrivacyChecked);
					if (!isPrivacyChecked) {
						driver.findElement(By.id("edit-confirm-privacy")).click();
					}
					driver.findElement(By.id("edit-confirm-age")).click();
					WebElement e3 = driver.findElement(By.id("edit-confirm-age"));
					boolean isAgeChecked = e3.isSelected();
					System.out.println(Constants.URL);
					System.out.println(isAgeChecked);
					if (!isAgeChecked) {
						driver.findElement(By.id("edit-confirm-age")).click();
					}
					driver.findElement(By.id("edit-submit")).click();
					captureScreen();
					ExcelUtils.setCellData(username, i, Constants.Col_garesult, Constants.Sheet_GamstopAccounts);
				} catch (Exception e) {
					e.printStackTrace();
					DriverScript.bResult = false;
					System.out.println("8BG Registration failed");
				}
			}
		}
	}

	public static void GamstopAccountsRegistration(String data) throws Exception {

		int numberofaccounts = ExcelUtils.getRowCount(Constants.Sheet_GamstopAccounts);
		System.out.println(numberofaccounts);
		for (int i = 0; i < numberofaccounts; i++) {
			Constants.rand = (int) ((long) Math.floor(Math.random() * 9_000_0L) + 1_000_0);
			String username = "dcenter" + Constants.rand;
			try {
				Constants.gafirstname = ExcelUtils.getCellData(i, Constants.Col_gafirstname,
						Constants.Sheet_GamstopAccounts);
				Constants.galastname = ExcelUtils.getCellData(i, Constants.Col_galastname,
						Constants.Sheet_GamstopAccounts);
				Constants.gadob = ExcelUtils.getCellData(i, Constants.Col_gadob, Constants.Sheet_GamstopAccounts);
				Constants.gaemail = "gamstop" + Constants.rand + "."
						+ ExcelUtils.getCellData(i, Constants.Col_gaemail, Constants.Sheet_GamstopAccounts);
				Constants.gapostcode = ExcelUtils.getCellData(i, Constants.Col_gapostcode,
						Constants.Sheet_GamstopAccounts);
				int firstslash = Constants.gadob.indexOf("/");
				int secondslash = Constants.gadob.lastIndexOf("/");
				String month = Constants.gadob.substring(1, firstslash);
				String day = Constants.gadob.substring(firstslash + 1, secondslash);
				String year = Constants.gadob.substring(secondslash + 1, Constants.gadob.length() - 1);
				System.out.println(Constants.gafirstname + "    " + Constants.galastname + "    " + Constants.gadob
						+ "     " + month + "/" + day + "/" + year + "  " + Constants.gaemail + "       "
						+ Constants.gapostcode + "     " + firstslash + "      " + secondslash);
				///// Registration begin //////
				Constants.URL = data;
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("incognito");
				options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
				driver.get(data);
				captureScreen();
				driver.get(Constants.URL + "/user/register");
				driver.findElement(By.id("edit-name")).sendKeys(username);
				driver.findElement(By.id("edit-first-name")).clear();
				driver.findElement(By.id("edit-first-name")).sendKeys(Constants.gafirstname);
				driver.findElement(By.id("edit-last-name")).clear();
				driver.findElement(By.id("edit-last-name")).sendKeys(Constants.galastname);
				driver.findElement(By.xpath("//*[@for='edit-gender-male']")).click();
				driver.findElement(By.id("edit-mail")).sendKeys(Constants.gaemail);
				driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
				driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
				captureScreen();
				new WebDriverWait(driver, 5).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='nextstep-arrow'][2]")));
				try {
					driver.findElement(By.xpath("//*[@class='nextstep-arrow'][2]")).click();
				} catch (Exception e2) {
					try {
						driver.findElement(By.xpath("//*[@class='register-login-button']")).click();
					} catch (Exception e3) {
						driver.findElement(By.xpath("//*[@class='nextstep-arrow']")).click();
					}
				}
				// new WebDriverWait(driver,
				// 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-mobile")));
				new WebDriverWait(driver, 5).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='date_of_birth[day]']")));
				new WebDriverWait(driver, 5).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='date_of_birth[month]']")));
				new WebDriverWait(driver, 5).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='date_of_birth[year]']")));
				System.out.println("waiting until");
				WebElement month1 = driver.findElement((By.xpath("//*[@name='date_of_birth[month]']")));
				WebElement day1 = driver.findElement((By.xpath("//*[@name='date_of_birth[day]']")));
				WebElement year1 = driver.findElement((By.xpath("//*[@name='date_of_birth[year]']")));
				System.out.println(day);
				System.out.println(month);
				System.out.println(year);
				Select sday = new Select(day1);
				Select smonth = new Select(month1);
				Select syear = new Select(year1);
				sday.selectByValue(day);
				smonth.selectByValue(month);
				syear.selectByValue(year);
				driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				WebElement country = driver.findElement((By.id("edit-country")));
				Select scountry = new Select(country);
				scountry.selectByIndex(1);
				WebElement nationality = driver.findElement((By.id("edit-nationality")));
				Select snationality = new Select(nationality);
				snationality.selectByIndex(1);
				driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.gapostcode);
				driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-city--2")));
				driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
				try {
					try {
						driver.findElement(By.name("terms-of-use")).click();
					} catch (Exception e) {
						try {
							driver.findElement(By.xpath(
									"//*[@id=\"user-register-form\"]/div/div/div[5]/div[1]/div[2]/div[9]/div/label"))
									.click();
						} catch (Exception e1) {
							driver.findElement(By.xpath("//*[@id=\"edit-terms-of-use\"]")).click();
						}
					}
				} catch (Exception e) {// FOR 8BG ACQUIRED SITES
				}
				captureScreen();
				driver.findElement(By.id("edit-submit")).click();
				/////// REGISTRATION END //////////
				String successful_registration_message = null;
				try {
					WebElement succ = driver.findElement(By.xpath("(//*[@class='element-invisible'])[3]"));
					String succparent = succ.findElement(By.xpath("..")).getText();
					System.out.println(succparent);
					WebElement err = driver.findElement(By.xpath("(//*[@class='element-invisible'])[4]"));
					String errparent = err.findElement(By.xpath("..")).getText();
					System.out.println(errparent);
					successful_registration_message = driver
							.findElement(By.xpath("(//*[@class='element-invisible'])[3]")).getAttribute("innerHTML");
					String error_message = driver.findElement(By.xpath("(//*[@class='element-invisible'])[4]"))
							.getAttribute("innerHTML");
					ExcelUtils.setCellData(succparent + "\n" + errparent, i, Constants.Col_garesult,
							Constants.Sheet_GamstopAccounts);
					ExcelUtils.setCellData(username, i, Constants.Col_garesult + 1, Constants.Sheet_GamstopAccounts);
					ExcelUtils.setCellData(Constants.gaemail, i, Constants.Col_garesult + 2,
							Constants.Sheet_GamstopAccounts);
					// driver.close();
					captureScreen();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// driver.close();
					// captureScreen();
					WebElement succ = driver.findElement(By.xpath("(//*[@class='element-invisible'])[3]"));
					String succparent = succ.findElement(By.xpath("..")).getText();
					System.out.println(succparent);
					WebElement err = driver.findElement(By.xpath("(//*[@class='element-invisible'])[4]"));
					String errparent = err.findElement(By.xpath("..")).getText();
					System.out.println(errparent);
					successful_registration_message = driver
							.findElement(By.xpath("(//*[@class='element-invisible'])[3]")).getAttribute("innerHTML");
					String error_message = driver.findElement(By.xpath("(//*[@class='element-invisible'])[4]"))
							.getAttribute("innerHTML");
					ExcelUtils.setCellData(succparent + "\n" + errparent, i, Constants.Col_garesult,
							Constants.Sheet_GamstopAccounts);
					ExcelUtils.setCellData(username, i, Constants.Col_garesult + 1, Constants.Sheet_GamstopAccounts);
					// driver.close();
					captureScreen();
				}
				// if (successful_registration_message.contains("successfully")) {
				// ExcelUtils.setCellData(successful_registration_message, i,
				// Constants.Col_garesult,
				// Constants.Sheet_GamstopAccounts);
				// driver.close();
				// // ExcelUtils.setCellData("did this", i, Constants.Col_garesult,
				// Constants.Sheet_GamstopAccounts);
				// }
			} catch (Exception e) {
				captureScreen();
				e.printStackTrace();
				// driver.close();
				ExcelUtils.setCellData("Registration failed", i, Constants.Col_garesult,
						Constants.Sheet_GamstopAccounts);

				System.out.println("came till here");
			}
			driver.close();
		} /// end of for loop
	}

	public static void RegisterAndDeposit(String data) {
		Constants.URL = data;
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
			// System.out.print(DriverScript.bResult);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("incognito");
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
			System.out.print(data);
			driver.get(data);
			Constants.handle1 = driver.getWindowHandle();
			System.out.printf("\n\n" + Constants.handle1);
			captureScreen();
			driver.get(Constants.URL + "/user/register");
			Constants.rand = (int) ((long) Math.floor(Math.random() * 9_000L) + 1_000);
			String username = "dcenter" + Constants.rand;
			String email = username + "." + Constants.LastName + "@yopmail.com";
			try {
				driver.findElement(By.id("edit-name")).sendKeys(username);
			} catch (Exception e3) {

				driver.findElement(By.name("mobile")).sendKeys(Constants.Mobile);
			}
			try {
				driver.findElement(By.name("first_name")).sendKeys(Constants.FirstName);
			} catch (Exception e6) {
				System.out.println("Registration steps are crazy");
			}
			try {
				driver.findElement(By.name("last_name")).sendKeys(Constants.LastName);
			} catch (Exception e6) {

				System.out.println("Registration steps are crazy");
			}
			try {
				driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click();
			} catch (Exception e6) {

				System.out.println("Registration steps are crazy");
			}
			try {
				driver.findElement(By.id("edit-mail")).sendKeys(email);
			} catch (Exception e6) {
				System.out.println("Registration steps are crazy");
			}

			try {
				WebElement day = driver.findElement((By.name("date_of_birth[day]")));
				WebElement month = driver.findElement((By.name("date_of_birth[month]")));
				WebElement year = driver.findElement((By.name("date_of_birth[year]")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
				Select sday = new Select(day);
				Select smonth = new Select(month);
				Select syear = new Select(year);
				sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));
				smonth.selectByIndex(getRandomNumberInRange(1, 12));
				syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
			} catch (Exception e6) {

				System.out.println("Registration steps are crazy");
			}

			// *[@id="user-register-form"]/div/div/div[3]/div/div[3]/a
			driver.findElement(By.id("edit-password-pass1")).sendKeys(Constants.Password);
			driver.findElement(By.id("edit-password-pass2")).sendKeys(Constants.Password);
			captureScreen();

			try {
				driver.findElement(
						By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[2]/div[6]/a/span[1]")).click();
			} catch (Exception e3) {
				System.out.println("first try");

			}
			try {
				driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[3]/a")).click();
			} catch (Exception e3) {
				System.out.println("second try");
			}

			try {
				driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[3]/div/div/div[4]/a/span"))
						.click();
			} catch (Exception e3) {
				System.out.println("third try");
			}

			try {
				driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[3]/div[2]/a")).click();
			} catch (Exception e7) {
				System.out.println("fourth try");
			}

			try {
				driver.findElement(By.id("edit-submit")).click();
			} catch (Exception e7) {
				System.out.println("fifth try");
			}

			try {
				driver.findElement(By.xpath("//*[@id=\"user-register-form\"]/div/div/div[3]/div/div[3]/a")).click();
			} catch (Exception e7) {
				System.out.println("sixth try");
			}

			captureScreen();
			try {

				try {
					driver.findElement(By.name("first_name")).sendKeys(Constants.FirstName);
				} catch (Exception e6) {
					System.out.println("I'm on Step 2");
				}
				try {
					driver.findElement(By.name("last_name")).sendKeys(Constants.LastName);
				} catch (Exception e6) {

					System.out.println("I'm on Step 2");
				}
				try {
					driver.findElement(By.xpath("//*[@id=\"edit-gender\"]/div[2]/label")).click();
				} catch (Exception e6) {

					System.out.println("I'm on Step 2");
				}
				try {
					driver.findElement(By.id("edit-mail")).sendKeys(email);
				} catch (Exception e6) {

					System.out.println("I'm on Step 2");
				}

				try {
					WebElement day = driver.findElement((By.name("date_of_birth[day]")));
					WebElement month = driver.findElement((By.name("date_of_birth[month]")));
					WebElement year = driver.findElement((By.name("date_of_birth[year]")));
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-month")));
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-day")));
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-date-of-birth-year")));
					Select sday = new Select(day);
					Select smonth = new Select(month);
					Select syear = new Select(year);
					sday.selectByVisibleText(String.valueOf(getRandomNumberInRange(1, 10)));

					smonth.selectByIndex(getRandomNumberInRange(1, 12));
					syear.selectByVisibleText(String.valueOf(getRandomNumberInRange(1950, 2000)));
				} catch (Exception e3) {

					System.out.println("I'm on Steps");
				}
				try {
					driver.findElement(By.id("edit-mobile")).sendKeys(Constants.Mobile);
				} catch (Exception e2) {

					System.out.println("This is an MCN site");
				}
				try {
					WebElement country = driver.findElement((By.id("edit-country")));
					Select scountry = new Select(country);
					scountry.selectByIndex(2);
				} catch (Exception e2) {

					WebElement country = driver.findElement((By.id("edit-country")));
					Select scountry = new Select(country);
					scountry.selectByValue("Cayman Islands");
					;
				}
				try {
					WebElement nationality = driver.findElement((By.id("edit-nationality")));
					Select snationality = new Select(nationality);
					snationality.selectByIndex(1);
					driver.findElement(By.id("edit-postal-code")).sendKeys(Constants.PostCode);
					driver.findElement(By.id("edit-address")).sendKeys(Constants.Address);
					driver.findElement(By.id("edit-city--2")).sendKeys(Constants.City);
				} catch (Exception e2) {

					System.out.println("One of them fields is not present");
				}
				try {
					driver.findElement(By.name("terms-of-use")).click();
					System.out.println("Tried successfully");
				} catch (Exception e) {

					try {
						driver.findElement(By
								.xpath("//*[@id=\"user-register-form\"]/div/div/div[5]/div[1]/div[2]/div[9]/div/label"))
								.click();
						System.out.println("Tried again successfully");
					} catch (Exception e1) {

						driver.findElement(By.xpath("//*[@id=\"edit-terms-of-use\"]")).click();
						System.out.println("Caught successfully");
					}
				} // *[@id="user-register-form"]/div/div/div[4]/div/div[2]/div[9]/div/label
				try {
					System.out.println("Trying again");
					driver.findElement(
							By.xpath("//*[@id=\"user-register-form\"]/div/div/div[4]/div/div[2]/div[9]/div/label"))
							.click();
					System.out.println("Tried again successfully");
				} catch (Exception e1) {

					driver.findElement(By.xpath("//*[@id=\"edit-terms-of-use\"]")).click();
					System.out.println("Caught successfully");
				}

				try {
					driver.findElement(By.id("edit-confirm-privacy")).click();
				} catch (Exception e1) {

					driver.findElement(By.xpath("//*[@id=\"edit-confirm-privacy\"]")).click();
				}
				try {
					driver.findElement(By.id("edit-confirm-age")).click();
				} catch (Exception e1) {

					driver.findElement(By.xpath("//*[@id=\"edit-confirm-age\"]")).click();
				}
				try {
					driver.findElement(By.id("edit-submit")).click();
				} catch (Exception e) {

					driver.findElement(By.name("op")).click();
				}
				String hand = driver.getWindowHandle();
				System.out.println(hand);
				captureScreen();

			} catch (Exception e1) {

				DriverScript.bResult = false;
			}

			try {
				driver.get(Constants.URL + "/user");
				String reflink = driver.findElement(By.xpath("//*[@rel=\"shortlink\"]")).getAttribute("href");
				System.out.println(reflink);
				Constants.drupalID = reflink.substring(reflink.lastIndexOf('/') + 1);
				System.out.println(Constants.drupalID);
				int drupalAcc = (Integer.parseInt(Constants.drupalID));
				// boolean isNumeric = Constants.drupalID.chars().allMatch( Character::isDigit
				// );
				// System.out.print(isNumeric);
				if (Constants.drupalID.chars().allMatch(Character::isDigit)) {
					// DriverScript.bResult = false;
				}
			} catch (Exception e) {

				System.out.print("Could not click, so refreshing the page");
				DriverScript.bResult = false;
				System.out.print(DriverScript.bResult);
			}
			ExcelUtils.setCellData(username, DriverScript.iTestStep, Constants.Col_TestStepLog,
					Constants.Sheet_TestSteps);

			try {
				System.setProperty("webdriver.gecko.driver",
						"C:\\Users\\vkolla\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
				WebDriver fdriver = new FirefoxDriver();
				// fdriver.get(Constants.URL+"/user/"+Constants.accountID+"/edit?destination=admin/people");
				fdriver.get(Constants.URL + "/admin" + "/people");

				try {
					fdriver.findElement(By.name("name")).clear();
					fdriver.findElement(By.name("name")).sendKeys("admin");
					fdriver.findElement(By.name("pass")).clear();
					fdriver.findElement(By.name("pass")).sendKeys("C0zy123$");
					fdriver.findElement(By.name("op")).click();
					String errormessage = fdriver.getPageSource();
					String pagetitle = fdriver.getTitle();
					System.out.println("About to get into IF");
					if (pagetitle.contains("Access Denied")) {
						System.out.println("Page title does contain Access Denied");
						try {
							System.out.println("Just got into Try block");
							int firstperiod = Constants.URL.indexOf(".");
							System.out.println(firstperiod);
							int secondperiod = Constants.URL.indexOf(".", firstperiod + 1);
							System.out.println(secondperiod);
							String labelname = Constants.URL.substring(firstperiod + 1, secondperiod);
							System.out.println(labelname);
							System.out.println("I'm in the Try loop of If block");
							System.out.println(Arrays.asList(Constants.sitelist).indexOf(labelname));
							System.out.println("Got the label");
							int len = Arrays.asList(Constants.sitelist).indexOf(labelname);
							System.out.println("Got the label's index");
							String adminpass = Arrays.asList(Constants.admin_password_list).get(len);
							System.out.println("Got the admin password");
							System.out.println(len);
							System.out.println(adminpass);
							fdriver.findElement(By.name("name")).clear();
							System.out.println("Cleared admin field");
							fdriver.findElement(By.name("name")).sendKeys("admin");
							System.out.println("Entered admin");
							fdriver.findElement(By.name("pass")).clear();
							System.out.println("Cleared password field");
							fdriver.findElement(By.name("pass")).sendKeys(adminpass);
							System.out.println("entered admin password");
							fdriver.findElement(By.name("op")).click();
							System.out.println("trying to click Login");
						} catch (Exception e) {

							DriverScript.bResult = false;
						}
					}
				} catch (Exception e1) {

				}
				String peopletab = fdriver.getTitle();
				if (peopletab.contains("People")) {

				} else {
					fdriver.get(Constants.URL + "/admin" + "/people");
				}
				captureScreen();
				try {

					fdriver.findElement(
							By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
							.click();
					/// html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a
					fdriver.findElement(
							By.xpath("/html/body/div[3]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
							.click();
				} catch (Exception e) {

					fdriver.findElement(
							By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
							.click();
					fdriver.findElement(
							By.xpath("/html/body/div[4]/div[2]/div[3]/div/div/form[2]/div/table[2]/thead/tr/th[6]/a"))
							.click();
				}
				fdriver.findElement(By.name("accounts[" + Constants.drupalID + "]")).click();
				WebElement role = fdriver.findElement((By.name("operation")));
				role.click();
				Select btester = new Select(role);
				btester.selectByValue("add_role-4");
				captureScreen();
				fdriver.findElement(By.id("edit-submit--2")).click();

			} catch (Exception e) {

				DriverScript.bResult = false;

				try {
					captureScreen();
					System.out.print(data);
					driver.get(Constants.URL + "/banking/deposit/test");
					// edit-submit
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-submit")));
					driver.findElement(By.xpath("//*[@alt='Paysafe Card'][1]")).click();
					driver.findElement(By.name("other_amount")).clear();
					driver.findElement(By.name("other_amount")).sendKeys(data);
					driver.findElement(By.xpath("//*[@id=\"combo-coupons\"]/li[1]")).click();
					// driver.findElement(By.id("edit-environment")).click();
					WebElement env = driver.findElement(By.name("environment"));
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-environment")));
					Select senv = new Select(env);
					senv.selectByIndex(2);
					captureScreen();
					try {
						driver.findElement(By.xpath(
								"//*[@id=\"nucleus-cashier-deposit-form-new\"]/div/div/div[2]/div[2]/div[3]/div[6]/div[2]/label"))
								.click();
					} catch (Exception e4) {

						// e.printStackTrace();
					}
					driver.findElement(By.id("edit-submit")).click();
					captureScreen();
				} catch (Exception e5) {

					DriverScript.bResult = false;
				}
			}
		} catch (Exception e) {

			DriverScript.bResult = false;
		}
	}

	public static void gotoBingoLobby() {
		try {
			driver.switchTo().window(Constants.parentwindow);
			if (Constants.URL.contains("gamevillage") || Constants.URL.contains("beckysbingo")
					|| Constants.URL.contains("bingomagix")) {
				URL url = new URL(Constants.URL + "/bingo");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				System.out.println("Response code for " + Constants.URL + "/bingo" + " is" + responsecode);
				if (responsecode != 404)
					driver.get(Constants.URL + "/bingo");
				return;
			}
			if (!driver.getCurrentUrl().equals(Constants.URL + "/play-bingo-online")) {

				URL url = new URL(Constants.URL + "/play-bingo-online");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				System.out.println("Response code for " + Constants.URL + "/play-bingo-online" + " is" + responsecode);
				if (responsecode != 404)
					driver.get(Constants.URL + "/play-bingo-online");

				else {
					System.out.println("trying else...");
					URL url2 = new URL(Constants.URL + "/bingo-games");
					HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
					conn2.setRequestMethod("GET");
					conn2.connect();
					int responsecode2 = conn2.getResponseCode();
					System.out.println("Response code for " + Constants.URL + "/bingo-games" + " is" + responsecode2);
					if (responsecode2 != 404)
						driver.get(Constants.URL + "/bingo-games");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void gotoSlotsLobby() {
		try {

			driver.switchTo().window(Constants.parentwindow);
			if (!driver.getCurrentUrl().equals(Constants.URL + "/slots-game-lobby"))
				driver.get(Constants.URL + "/slots-game-lobby");
			String pagetitle = driver.getTitle();
			if (pagetitle.contains("404")) {
				driver.get(Constants.URL + "/slots-games");
			}
			System.out.println("Navigate to Slots Lobby");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Unable to navigate to Slots lobby");
		}
	}

	public static void gotoCasinoLobby() {
		try {

			driver.switchTo().window(Constants.parentwindow);
			if (!driver.getCurrentUrl().equals("Slots"))
				driver.get(Constants.URL + "/casino-game-lobby");
			String pagetitle = driver.getTitle();
			if (pagetitle.contains("404")) {
				driver.get(Constants.URL + "/casino-game-lobby");
			}
			System.out.println("Navigate to Casino Lobby");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Unable to navigate to Casino lobby");
		}
	}

	public static void gotoScratchLobby() {
		try {

			driver.switchTo().window(Constants.parentwindow);
			if (!driver.getCurrentUrl().equals(Constants.URL + "/scratchcards-game-lobby"))
				driver.get(Constants.URL + "/scratchcards-game-lobby");
			String pagetitle = driver.getTitle();
			if (pagetitle.contains("404")) {
				driver.get(Constants.URL + "/scratchcards-game-lobby");
			}
			System.out.println("Navigate to Scratch Lobby");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Unable to navigate to Scratch lobby");
		}
	}

	public static void exlaunchBingoRoom(String data) throws MalformedURLException, IOException, ParseException {

		profileAPI();
		walletAPI();
		// loginAPI();
		// profileAPI();
		System.out.println("Wallet Balance Cash_GBP value is: " + getWalletBalance("Cash_GBP"));
		Constants.bingoroomname = data;
		driver.switchTo().window(Constants.parentwindow);
		allCookies = driver.manage().getCookies();
		SessionId sessionbefore = ((ChromeDriver) driver).getSessionId();
		System.out.println("Session ID before launching is " + sessionbefore);
		try {
			gotoBingoLobby();
			System.out.println("Navigated to bingo lobby");
			// WebElement table_element = driver.findElement(By.id("bingo-tablesorter"));
			// WebElement masons = driver.findElement(By.className("masonry-item"));
			// List<WebElement> masonitems =
			// masons.findElements(By.className("masonry-item"));

			/////////////////// LUCKY LADIES BINGO////////////////
			//
			// List<WebElement> ui_collection =
			// driver.findElements(By.xpath("//div[@id='bingo-lobby-table']/child::*[3]/child::*"));
			// System.out.println("NUMBER OF ROWS IN THIS TABLE = " + ui_collection.size());
			// // // VK - commenting
			// int row_num, col_num, row = 0, col = 0;
			// row_num = 1;
			// for (WebElement uiElement : ui_collection) {
			// List<WebElement> li_collection = uiElement.findElements(By.xpath("li"));
			// System.out.println("NUMBER OF COLUMNS=" + li_collection.size()); // VK -
			// // commenting
			// col_num = 1;
			// for (WebElement liElement : li_collection) {
			// // VK - USE THE BELOW PRINT LINE TO SEE WHAT THE TABLE DATA CONTAINS - ROW
			// WISE
			// // AND COLUMN WISE
			// System.out.println("row # " + row_num + ", col # " + col_num + "text=" +
			// liElement.getText());
			// //col_num++;
			//
			// if (liElement.getText().contains(data)) {
			// System.out.println("found the game in bingo lobby");
			// row = row_num;
			// System.out.println(row);
			// // if(tdElement.getText().contains("PLAY NOW")) {
			// // ActionKeywords.generateXPATH(tdElement,"PLAY NOW");
			// // col=col_num;
			// System.out.println(col_num);
			// driver.findElement(
			// By.xpath("//div[@id='bingo-lobby-table']/child::*[3]/child::*[" + row_num +
			// "]/child::*[7]/child::*[1]"))
			// .click();
			// System.out.println("Launched " + data + " bingo room successfully.");
			//
			// // ashot();
			// // VK - NEED TO REMOVE THE ABOVE HARDCODING OF COLUMN VALUE = 9
			// // *[@id="bingo-tablesorter"]/tbody/tr[1]/td[9]/span[1]/a
			// }
			// col_num++;
			// }
			// row_num++;
			// //
			// driver.findElement(By.xpath("//*[@id=\"bingo-tablesorter\"]/tbody/tr["+row+"]/td["+col+"]/span[1]/a")).click();
			// }
			////////// LUCKY LADIES BINGO////////////

			List<WebElement> tr_collection = driver
					.findElements(By.xpath("//table[@id='bingo-tablesorter']/child::*[2]/child::*")); // FOR LANDMARK
																										// 572018 CHANGE
			System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
			// // VK - commenting
			int row_num, col_num, row = 0, col = 0;
			row_num = 1;
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
				System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); // VK -
				// commenting
				col_num = 1;
				for (WebElement tdElement : td_collection) {
					// VK - USE THE BELOW PRINT LINE TO SEE WHAT THE TABLE DATA CONTAINS - ROW WISE
					// AND COLUMN WISE
					System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
					col_num++;
					if (tdElement.getText().contains(data)) {
						System.out.println("found the game in bingo lobby");
						row = row_num;
						System.out.println(row);
						// if(tdElement.getText().contains("PLAY NOW")) {
						// ActionKeywords.generateXPATH(tdElement,"PLAY NOW");
						// col=col_num;
						System.out.println(col_num);
						driver.findElement(
								By.xpath("//*[@id=\"bingo-tablesorter\"]/tbody/tr[" + row_num + "]/td[9]/span[1]/a"))
								.click();
						System.out.println("Launched " + data + " bingo room successfully.");
						// ashot();
						// VK - NEED TO REMOVE THE ABOVE HARDCODING OF COLUMN VALUE = 9
						// *[@id="bingo-tablesorter"]/tbody/tr[1]/td[9]/span[1]/a
					}
				}
				row_num++;
				// driver.findElement(By.xpath("//*[@id=\"bingo-tablesorter\"]/tbody/tr["+row+"]/td["+col+"]/span[1]/a")).click();
			}

			// Cookie cook= driver.manage().getCookieNamed(((ChromeDriver)
			// driver).getSessionId()));
			// System.out.println("Cookie get value is: "+cook);

			for (String bingowindows : driver.getWindowHandles()) {
				if (!bingowindows.toString().equals(Constants.parentwindow)
						&& !bingowindows.toString().equals(Constants.slothandle)
						&& !bingowindows.toString().equals(Constants.casinohandle)
						&& !bingowindows.toString().equals(Constants.scratchhandle)) {
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					driver.switchTo().window(bingowindows);
					Constants.bingohandle = driver.getWindowHandle();
					// URL url = new URL(Constants.URL);
					driver.manage().window().setSize(new Dimension(1024, 786));
					System.out.println(Constants.secondlaunch);
					if (!Constants.secondlaunch) {
						// Thread.sleep(25000);
						System.out.println(Constants.secondlaunch);
						Screen s = new Screen();
						System.out.println("Captured screen");
						String envlabel = null;
						try {
							envlabel = getLabel(Constants.URL);
							System.out.println(envlabel);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Pattern login = new Pattern(Constants.bingorepo + envlabel + "_bingoroomlogin.PNG").exact();
						Pattern invalidsession = new Pattern(
								Constants.bingorepo + envlabel + "_invalidsessionerror.PNG").exact();
						Pattern loadinggraphics = new Pattern(
								Constants.bingorepo + envlabel + "landmarkbingo_loadinggraphics.PNG")
										.similar((float) 0.9);
						Pattern selectstrip = new Pattern(
								"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_BingoImageRepository\\gamemode_selectstrip.PNG");
						Pattern gameinprogress = new Pattern(
								"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_BingoImageRepository\\gamemode_gameinprogress.PNG");
						Pattern seeall = new Pattern(
								"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_BingoImageRepository\\gamemode_seeallballcall.PNG");
						try {
							boolean loginissue = false, servererror = false, loadcomplete = false;
							System.out.print("Checking for login issue or internal server error issue");
							for (int i = 0; !loginissue && !servererror && !loadcomplete && i < 60; i++) {
								System.out.print(".");
								if (s.exists(login) != null) {
									System.out.println("This is rid");
									driver.close();
									driver.switchTo().window(Constants.parentwindow);
									launchBingoRoom(data);
								}
								if (s.exists(invalidsession) != null) {
									System.out.println("This is stu");
									driver.close();
									driver.switchTo().window(Constants.parentwindow);
									launchBingoRoom(data);
								}
								if (s.exists(loadinggraphics) != null || s.exists(selectstrip) != null
										|| s.exists(gameinprogress) != null || s.exists(seeall) != null) {
									System.out.println("Crossed that 6% mark..");
									break;
								}

							}
							// s.wait(login, 10);
							// if (s.exists(login) != null) {
							// driver.close();
							// driver.switchTo().window(Constants.parentwindow);
							// Constants.secondlaunch = true;
							// launchBingoRoom(data);
							// }
						} catch (Exception e) {
						}
					}
					// for (int i = 0; i < 5; i++) {
					// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					// conn.setRequestMethod("GET");
					// conn.connect();
					// int responsecode = conn.getResponseCode();
					// System.out.println("Response code for " + Constants.URL + " is" +
					// responsecode);
					// Thread.sleep(20);
					// driver.navigate().refresh();
					// }
					// for (int i = 0; i < 20; i++) {
					// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					// conn.setRequestMethod("GET");
					// conn.connect();
					// int responsecode = conn.getResponseCode();
					// System.out.println("Response code for " + Constants.URL + " is" +
					// responsecode);
					// Thread.sleep(20);
					// }
					// driver.navigate().refresh();
					// SessionId sessionafter = ((ChromeDriver) driver).getSessionId();
					// SessionStorage sessionstorage = ((ChromeDriver) driver).getSessionStorage();
					// System.out.println("Session ID after launching is " + sessionafter);
					// System.out.println("\nSession storage is " + sessionstorage);
					// for (Cookie cookie : allCookies) {
					// driver.manage().addCookie(cookie);
					// ((ChromeDriver) driver).getSessionStorage();
					// System.out.println("Cookie name is " + cookie.getName() + "\nCookie is" +
					// cookie + "\n\n");
					// }
					// driver.manage().addCookie((Cookie) allCookies);

					// driver.navigate().refresh();
					String bingowindowid = driver.getWindowHandle();
					// Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					System.out.println("Bingo Game window ID is :" + bingowindowid);
					System.out.println("Bingo window title is :" + driver.getTitle());
					// Thread.sleep(10000);
					// System.out.println(driver.manage().window().getSize());
					// driver.manage().window().fullscreen();
					// System.out.println(driver.manage().window().getSize());
					// action.moveByOffset(500, 500).click().perform();
					// action.contextClick().perform();
					// driver.findElement(By.xpath("//*[@id=\"Game\"]")).click();
					// action.moveByOffset(15, 15).click().perform();
					// driver.findElement(By.xpath("//html")).click();
					// driver.manage().window().
					// enableFlash();
				}
				ExcelUtils.setCellData("Launched " + data + " bingo room successfully", DriverScript.iTestStep,
						Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DriverScript.bResult = false;
			System.out.println("Unable to launch Bingo Room");
		}
	}

	public static void launchBingoRoom(String data) throws MalformedURLException, IOException, ParseException {

		// profileAPI();
		// walletAPI();
		// loginAPI();
		// profileAPI();
		System.out.println("Wallet Balance Cash_GBP value is: " + getWalletBalance("Cash_GBP"));
		Constants.bingoroomname = data;
		driver.switchTo().window(Constants.parentwindow);
		try {
			gotoBingoLobby();
			System.out.println("Navigated to bingo lobby dinchak");
			if (Constants.URL.contains("harrysbingo") || Constants.URL.contains("gonebingo")
					|| Constants.URL.contains("swankybingo") || Constants.URL.contains("houseofbingo")
					|| Constants.URL.contains("wowbingo") || Constants.URL.contains("swagbingo")
					|| Constants.URL.contains("bobsbingo") || Constants.URL.contains("plushbingo")
					|| Constants.URL.contains("landmarkbingo")) {
				List<WebElement> tr_collection;
				System.out.println("playing sound");
				playSound();
				System.out.println("played sound");
				System.out.println("Is this a new registration? : " + Constants.newregistration);
				if (Constants.newregistration == true) {
					tr_collection = driver
							.findElements(By.xpath("//table[@id='bingo-tablesorter']/child::*[3]/child::*"));
				} else {
					tr_collection = driver
							.findElements(By.xpath("//table[@id='bingo-tablesorter']/child::*[2]/child::*"));
				}
				System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
				// // VK - commenting
				int row_num, col_num, row = 0, col = 0;
				row_num = 1;
				for (WebElement trElement : tr_collection) {
					List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
					System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); // VK -
					// commenting
					col_num = 1;
					for (WebElement tdElement : td_collection) {
						// VK - USE THE BELOW PRINT LINE TO SEE THE CONTENTS OF TABLE - ROW WISE
						// AND COLUMN WISE
						System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
						col_num++;
						if (tdElement.getText().toLowerCase().replace(" ", "")
								.contains(data.toLowerCase().replace(" ", ""))) {
							System.out.println("found the game in bingo lobby");
							row = row_num;
							System.out.println(row);
							// if(tdElement.getText().contains("PLAY NOW")) {
							// ActionKeywords.generateXPATH(tdElement,"PLAY NOW");
							// col=col_num;
							System.out.println(col_num);
							if (Constants.URL.contains("landmarkbingo"))
								try {
									driver.findElement(By.xpath(
											"//*[@id=\"bingo-tablesorter\"]/tbody/tr[" + row_num + "]/td[9]/span[1]/a"))
											.click();
									Constants.bingolaunchtime = Instant.now();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									driver.findElement(By.xpath("//*[@id=\"bingo-tablesorter\"]/tbody[2]/tr[" + row_num
											+ "]/td[9]/span[1]/a")).click();
									Constants.bingolaunchtime = Instant.now();
								}
							else if (Constants.URL.contains("gonebingo") || Constants.URL.contains("swagbingo")
									|| Constants.URL.contains("NIUhouseofbingoNIU"))
								try {
									new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(
											By.xpath("(//table[@id='bingo-tablesorter']/child::*[2]/child::*/child::*)["
													+ 6 * row_num + "]/child::*/child::*")))
											.click();
									Constants.bingolaunchtime = Instant.now();
									// driver.findElement(
									// By.xpath("(//table[@id='bingo-tablesorter']/child::*[2]/child::*/child::*)["
									// + 6 * row_num + "]/child::*/child::*"))
									// .click();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							else if (Constants.URL.contains("goneNIUbingo")) {
								try {
									try {
										System.out.println("In launching");
										driver.findElement(By.xpath(
												"((//table[@id='bingo-tablesorter']/child::*[2]/child::*/child::*)["
														+ 6 * row_num + "]/child::*/child::*)[1]"))
												.click();
										Constants.bingolaunchtime = Instant.now();
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println("Done launching");
							} else if (Constants.URL.contains("swankybingo") || Constants.URL.contains("plushbingo")) {
								driver.findElement(
										By.xpath("(//table[@id='bingo-tablesorter']/child::*[2]/child::*/child::*)["
												+ 7 * row_num + "]/child::*/child::*"))
										.click();
								Constants.bingolaunchtime = Instant.now();
							} else if (Constants.URL.contains("houseofbingo") || Constants.URL.contains("wowbingo")
									|| Constants.URL.contains("bobsbingo") || Constants.URL.contains("harrysbingo")) {
								try {
									driver.findElement(By
											.xpath("((//table[@id='bingo-tablesorter']/child::*[2]/child::*/child::*)["
													+ 6 * row_num + "]/child::*/child::*)[1]"))
											.click();
									Constants.bingolaunchtime = Instant.now();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}

							System.out.println("Launched " + data + " bingo room successfully.");
						}
					}
					row_num++;
				}
			} else if (Constants.URL.contains("luckyladiesdfdsbingo")
					|| Constants.URL.contains("luckyladiesdfdsbingo")) {
				///////////////// LUCKY LADIES BINGO////////////////
				List<WebElement> ui_collection = driver
						.findElements(By.xpath("//div[@id='bingo-lobby-table']/child::*[3]/child::*"));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = " + ui_collection.size());
				int row_num, col_num, row = 0, col = 0;
				row_num = 1;
				for (WebElement uiElement : ui_collection) {
					List<WebElement> li_collection = uiElement.findElements(By.xpath("li"));
					System.out.println("NUMBER OF COLUMNS=" + li_collection.size()); // VK -
					col_num = 1;
					for (WebElement liElement : li_collection) {
						System.out.println("row # " + row_num + ", col # " + col_num + "text=" + liElement.getText());
						if (liElement.getText().contains(data)) {
							System.out.println("found the game in bingo lobby");
							row = row_num;
							driver.findElement(By.xpath("//div[@id='bingo-lobby-table']/child::*[3]/child::*[" + row_num
									+ "]/child::*[7]/child::*[1]")).click();
							System.out.println("Launched " + data + " bingo room successfully.");
						}
						col_num++;
					}
					row_num++;
				}
			} else if (Constants.URL.contains("gamevillage") || Constants.URL.contains("beckysbingo")
					|| Constants.URL.contains("bingomagix")) {
				WebElement liElementsrc = null;
				List<WebElement> ui_collection = driver
						.findElements(By.xpath("//div[@id='inner-bingo-lobby-table']/child::*[2]/child::*"));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = " + ui_collection.size());
				int row_num, col_num, row = 0, col = 0;
				row_num = 1;
				for (WebElement uiElement : ui_collection) {
					List<WebElement> li_collection = uiElement.findElements(By.xpath("li"));
					System.out.println("NUMBER OF COLUMNS=" + li_collection.size()); // VK -
					col_num = 1;
					for (WebElement liElement : li_collection) {
						if (col_num == 1) {
							liElementsrc = liElement.findElement(By.xpath("./child::*/child::*"));
							System.out.println("row # " + row_num + ", col # " + col_num + "text="
									+ liElementsrc.getAttribute("alt"));
							// *[@id="inner-bingo-lobby-table"]/div[2]/ul[1]/li[8]/a
							// *[@id="inner-bingo-lobby-table"]/div[2]/ul[1]/li[1]/span/img
							// *[@id="inner-bingo-lobby-table"]/div[2]/ul[1]/li[1]/span
						} else {
							System.out
									.println("row # " + row_num + ", col # " + col_num + "text=" + liElement.getText());
						}
						if (liElement.getText().equalsIgnoreCase(data.replace(" ", ""))
								|| liElementsrc.getAttribute("alt").equalsIgnoreCase(data.replace(" ", ""))) {
							System.out.println("found the game in bingo lobby");
							row = row_num;
							if (driver.findElement(By.xpath(
									"(//div[@id='inner-bingo-lobby-table']/child::*[2]/child::*/child::*/child::*)["
											+ (row_num * 5) + "]"))
									.getText().equalsIgnoreCase("Play Now")) {
								driver.findElement(By.xpath(
										"(//div[@id='inner-bingo-lobby-table']/child::*[2]/child::*/child::*/child::*)["
												+ (row_num * 5) + "]"))
										.click();
								Constants.bingolaunchtime = Instant.now();
							} else if (driver.findElement(By.xpath(
									"(//div[@id='inner-bingo-lobby-table']/child::*[2]/child::*/child::*/child::*)["
											+ ((row_num * 5) - 1) + "]"))
									.getText().equalsIgnoreCase("Play Now")) {
								driver.findElement(By.xpath(
										"(//div[@id='inner-bingo-lobby-table']/child::*[2]/child::*/child::*/child::*)["
												+ ((row_num * 5) - 1) + "]"))
										.click();
								Constants.bingolaunchtime = Instant.now();
							}
						}
						col_num++;
					}
					row_num++;
				}
				System.out.println("Launched " + data + " bingo room successfully.");
			}

			else if (Constants.URL.contains("velvetbingo") || Constants.URL.contains("luckypuppybingo")) {
				WebElement liElementsrc = null;
				List<WebElement> ui_collection = driver
						.findElements(By.xpath("//div[@id='bingo-lobby-table']/child::*[2]/child::*"));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = " + ui_collection.size());
				int row_num, col_num, row = 0, col = 0;
				row_num = 1;
				for (WebElement uiElement : ui_collection) {
					List<WebElement> li_collection = uiElement.findElements(By.xpath("li"));
					System.out.println("NUMBER OF COLUMNS=" + li_collection.size()); // VK -
					col_num = 1;
					for (WebElement liElement : li_collection) {
						if (col_num == 1) {
							if (Constants.URL.contains("velvetbingo")) {
								liElementsrc = liElement.findElement(By.xpath("./child::*/child::*/child::*"));
								System.out.println("row # " + row_num + ", col # " + col_num + "text="
										+ liElementsrc.getAttribute("alt"));
								String gamename = liElementsrc.getAttribute("alt");
								System.out.println(gamename + " " + data.replace(" ", "").toLowerCase());
								if (gamename.replace(" ", "").toLowerCase()
										.contains(data.replace(" ", "").toLowerCase()))
									liElement.findElement(By.xpath("./child::a")).click();
								Constants.bingolaunchtime = Instant.now();
							} else if (Constants.URL.contains("luckypuppybingo")) {
								liElementsrc = liElement.findElement(By.xpath("./child::*/child::img"));
								System.out.println("row # " + row_num + ", col # " + col_num + "text="
										+ liElementsrc.getAttribute("src"));
								String gamename = liElementsrc.getAttribute("src");
								// System.out.println(gamename + " " + data.replace(" ", "").toLowerCase());
								if (gamename.replace(" ", "").toLowerCase()
										.contains(data.replace(" ", "").toLowerCase()))
									driver.findElement(By.xpath(
											"//div[@id='bingo-lobby-table']/child::*[2]/child::*/child::*/child::a"))
											.click();
								Constants.bingolaunchtime = Instant.now();
							}
						} else {
							System.out
									.println("row # " + row_num + ", col # " + col_num + "text=" + liElement.getText());
						}
						if (liElement.getText().equalsIgnoreCase(data.replace(" ", ""))
								|| liElementsrc.getAttribute("alt").equalsIgnoreCase(data.replace(" ", ""))) {
							System.out.println("found the game in bingo lobby");
							row = row_num;
							driver.findElement(
									By.xpath("(//div[@id='bingo-lobby-table']/child::*[2]/child::*/child::*/child::*)["
											+ (row_num * 5) + "]"))
									.click();
							Constants.bingolaunchtime = Instant.now();
						}
						col_num++;
					}
					row_num++;
				}
				System.out.println("Launched " + data + " bingo room successfully.");
			}

			else if (Constants.URL.contains("bootybingo") || Constants.URL.contains("luckyladiesbingo")
					|| Constants.URL.contains("gravytrainbingo") || Constants.URL.contains("mummiesbingo")
					|| Constants.URL.contains("littlemissbingo") || Constants.URL.contains("comfybingo")
					|| Constants.URL.contains("newlookbingo")) {
				WebElement liElementsrc = null;
				List<WebElement> ui_collection = driver
						.findElements(By.xpath("//div[@id='bingo-lobby-table']/child::*[3]/child::*"));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = " + ui_collection.size());
				int row_num, col_num, row = 0, col = 0;
				row_num = 1;
				for (WebElement uiElement : ui_collection) {
					List<WebElement> li_collection = uiElement.findElements(By.xpath("li"));
					System.out.println("NUMBER OF COLUMNS=" + li_collection.size()); // VK -
					col_num = 1;
					for (WebElement liElement : li_collection) {
						if (col_num == 1) {
							liElementsrc = liElement.findElement(By.xpath("./child::span"));
							String gamename = liElementsrc.getText();
							if (gamename.equalsIgnoreCase(data))
								liElement.findElement(By.xpath("./child::a")).click();
							Constants.bingolaunchtime = Instant.now();
							System.out.println(
									"row # " + row_num + ", col # " + col_num + "text=" + liElementsrc.getText());
							// *[@id="inner-bingo-lobby-table"]/div[2]/ul[1]/li[8]/a
							// *[@id="inner-bingo-lobby-table"]/div[2]/ul[1]/li[1]/span/img
							// *[@id="inner-bingo-lobby-table"]/div[2]/ul[1]/li[1]/span
						} else {
							System.out
									.println("row # " + row_num + ", col # " + col_num + "text=" + liElement.getText());
						}
						if (liElement.getText().equalsIgnoreCase(data.replace(" ", ""))
								|| liElementsrc.getText().equalsIgnoreCase(data.replace(" ", ""))) {
							System.out.println("found the game in bingo lobby");
							row = row_num;
							liElement.findElement(By.xpath("./child::a")).click();
							Constants.bingolaunchtime = Instant.now();
						}
						col_num++;
					}
					row_num++;
				}
				System.out.println("Launched " + data + " bingo room successfully.");
			}
			
			for (String bingowindows : driver.getWindowHandles()) {
				if (!bingowindows.toString().equals(Constants.parentwindow)
						&& !bingowindows.toString().equals(Constants.slothandle)
						&& !bingowindows.toString().equals(Constants.casinohandle)
						&& !bingowindows.toString().equals(Constants.scratchhandle)) {
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					driver.switchTo().window(bingowindows);
					Constants.bingohandle = driver.getWindowHandle();
					// URL url = new URL(Constants.URL);
					driver.manage().window().setSize(new Dimension(1024, 786));
					enableFlash();
					System.out.println(Constants.secondlaunch);
					if (!Constants.secondlaunch) {
						// Thread.sleep(25000);
						System.out.println(Constants.secondlaunch);
						Screen s = new Screen();
						System.out.println("Captured screen");
						String envlabel = null;
						try {
							envlabel = getLabel(Constants.URL);
							System.out.println(envlabel);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Search for pattern at"+Constants.bingorepo+" for white label "+envlabel);
						Pattern login = new Pattern(Constants.bingorepo + envlabel + "_bingoroomlogin.PNG");
						Pattern invalidsession = new Pattern(
								Constants.bingorepo + envlabel + "_invalidsessionerror.PNG").exact();
						// Pattern selectstrip = new Pattern(Constants.bingorepo + envlabel +
						// "_selectstrip.PNG");
						Pattern gameinprogress = new Pattern(Constants.bingorepo + envlabel + "_gameinprogress.PNG");
						// Pattern seeall = new Pattern(Constants.bingorepo + envlabel + "_seeall.PNG");
						Pattern realitycheck = new Pattern(Constants.bingorepo + envlabel + "_realitycheck.PNG");
						Pattern realitycheckcontinue = new Pattern(
								Constants.bingorepo + envlabel + "_realitycheckcontinue.PNG");
						Pattern zerozero = new Pattern(Constants.bingorepo + envlabel + "_zerozero.PNG");
						Pattern winnerwindow = new Pattern(Constants.bingorepo + envlabel + "_winnerwindow.PNG");
						try {
							boolean loginissue = false, servererror = false, loadcomplete = false;
							System.out.println("Starting for loop");
							System.out.println("Checking for login issue or internal server error issue");
							for (int i = 0; !loginissue && !servererror && !loadcomplete && i < 60; i++) {
								System.out.println("Over to you..."); // 6/4/2018 doing this for getting all screenshots
								Instant justnow = Instant.now();
								System.out.println("Just now " + justnow);
								if (s.exists(login) != null) {
									System.out.println("This is rid");
									driver.close();
									driver.switchTo().window(Constants.parentwindow);
									driver.get(driver.getCurrentUrl());
									launchBingoRoom(data);
								}
								System.out.println("Took " + Duration.between(Instant.now(), justnow).toMillis()
										+ " milli seconds to check for login");
								justnow = Instant.now();
								if (s.exists(invalidsession) != null) {
									System.out.println("This is stu");
									driver.close();
									driver.switchTo().window(Constants.parentwindow);
									launchBingoRoom(data);
								}
								System.out.println("Took " + Duration.between(Instant.now(), justnow).toMillis()
										+ " milli seconds to check for invalidsession");
								justnow = Instant.now();
								// if (s.exists(loadinggraphics) != null) {
								// System.out.println("Crossed loadinggraphics..");
								// break;
								// }
								// if (s.exists(selectstrip) != null) {
								// System.out.println("Crossed selectstrip..");
								// Constants.bingoloadcompletetime = Instant.now();
								// break;
								// }
								System.out.println("Checking game in progress message");
								if (s.exists(gameinprogress) != null) {
									System.out.println("Crossed gameinprogress..");
									Constants.bingoloadcompletetime = Instant.now();
									break;
								}
								System.out.println("Checking reality check");
								if (s.exists(realitycheck) != null) {
									s.click(realitycheckcontinue);
								}
								// if (s.exists(clientgameinprogress) != null) {
								// System.out.println("Crossed clientgameinprogress..");
								// break;
								// }
								// if (s.exists(seeall) != null) {
								// System.out.println("Crossed seeall..");
								// Constants.bingoloadcompletetime = Instant.now();
								// break;
								// }
								if (data.contains("Penny") || data.contains("PENNY")) {
									System.out.println("Checking these special rooms - " + data);
									Pattern penny = new Pattern(Constants.bingorepo + envlabel + "_penny.PNG");
									if (s.exists(penny) != null) {
										System.out.println("Crossed select strip in these weird rooms..");
										Constants.bingoloadcompletetime = Instant.now();
										break;
									}
								}
								if (data.contains("City") || data.contains("CITY")) {
									System.out.println("Checking these special rooms - " + data);
									Pattern city = new Pattern(Constants.bingorepo + envlabel + "_city.PNG");
									if (s.exists(city) != null) {
										System.out.println("Crossed select strip in these weird rooms..");
										Constants.bingoloadcompletetime = Instant.now();
										break;
									}
								}
								System.out.println("Checking zero zero");
								if (s.exists(zerozero) != null) {
									System.out.println("Crossed zerozero..");
									Constants.bingoloadcompletetime = Instant.now();
									// System.out.println("Clicking using offset location on zero zero at -
									// "+s.find(zerozero).x+" "+s.find(zerozero).y);
									// s.click(s.find(zerozero).x,s.find(zerozero).y);
									// System.out.println("Did the first click");
									// s.click(zerozero);
									// System.out.println("Clicked using offset location and select 2 tickets
									// location is ");
									break;
								}
								System.out.println("Took " + Duration.between(Instant.now(), justnow).toMillis()
										+ " milli seconds to check for all of them now");
							}
						} catch (Exception e) {
						}
					}
					String bingowindowid = driver.getWindowHandle();
					// Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					System.out.println("Bingo Game window ID is :" + bingowindowid);
					System.out.println("Bingo window title is :" + driver.getTitle());
				}
				captureScreenWithFilename(
						getLabel(Constants.URL) + "_bingoroomscreenshot" + Instant.now().getEpochSecond());
				ExcelUtils.setCellData("Launched " + data + " bingo room successfully", DriverScript.iTestStep,
						Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DriverScript.bResult = false;
			System.out.println("Unable to launch Bingo Room");
		}
	}

	public static void bingoGamePlay(String data) throws Exception {
		try {
			boolean buymode = false, prebuymode = false, gameplaycomplete = false, loadmode = false,
					winnerwindows = false, sidegames = false, ticketspurchased = false, realitycheckpopup = false,
					playmode = false;
			String envlabel = getLabel(Constants.URL);
			Screen s = new Screen();
			String gameid = s.text().substring(s.text().indexOf("ID: ") + 4, s.text().indexOf("ID: ") + 17);
			System.out.println("Game ID is " + gameid);
			Pattern selectstrip = new Pattern(Constants.bingorepo + envlabel + "_gamemode_selectstrip.PNG");
			Pattern gameinprogress = new Pattern(Constants.bingorepo + envlabel + "_gameinprogress.PNG");
			Pattern tryingtoload = new Pattern(Constants.bingorepo + envlabel + "_tryingtoload.PNG");
			Pattern seeall = new Pattern(Constants.bingorepo + envlabel + "_gamemode_seeallballcall.PNG");
			Pattern buymode_buy = new Pattern(Constants.bingorepo + envlabel + "_buymode_buy.PNG");
			Pattern prebuy_buy = new Pattern(Constants.bingorepo + envlabel + "_prebuymode_buy.PNG");
			Pattern select2tickets = new Pattern(Constants.bingorepo + envlabel + "_select2tickets.PNG");
			Pattern loading = new Pattern(Constants.bingorepo + envlabel + "_loading.PNG");
			Pattern currsymbol = new Pattern(Constants.bingorepo + envlabel + "_currencysymbol.PNG");
			Pattern cancelpurchase = new Pattern(Constants.bingorepo + envlabel + "_cancelpurchase.PNG");
			Pattern purchasealert = new Pattern(Constants.bingorepo + envlabel + "_purchased2ticketssuccessfully.PNG");
			Pattern purchasealertcontinue = new Pattern(
					Constants.bingorepo + envlabel + "_purchased2ticketssuccessfully_continue.PNG");
			Pattern prebuycountdown = new Pattern(Constants.bingorepo + envlabel + "_buymodecountdown.PNG");
			Pattern secondstogothree = new Pattern(Constants.bingorepo + envlabel + "_3secondstogo.PNG");
			Pattern winnerwindow = new Pattern(Constants.bingorepo + envlabel + "_winnerwindow.PNG");
			Pattern winnerwindowcontinue = new Pattern(Constants.bingorepo + envlabel + "_winnerwindowcontinue.PNG");
			Pattern zerozero = new Pattern(Constants.bingorepo + envlabel + "_zerozero.PNG").similar((float) 0.1);
			Pattern realitycheck = new Pattern(Constants.bingorepo + envlabel + "_realitycheck.PNG");
			Pattern realitycheckcontinue = new Pattern(Constants.bingorepo + envlabel + "_realitycheckcontinue.PNG");
			for (int a = 0; gameplaycomplete == false && a < 60; a++) {
				System.out.println("Game Play Iteration # " + a);
				if (s.exists(zerozero) != null) {
					System.out.println("Buy mode game play");
					buymode = true;
					System.out.println("Balances before purchasing tickets:\nCash Balance - " + getWalletBalance("Cash")
							+ "\nBingo Bonus - " + getWalletBalance("Bingo Bonus") + "\nGame Bonus - "
							+ getWalletBalance("Game Bonus"));
					Log.info("Balances before purchasing tickets:\nCash Balance - " + getWalletBalance("Cash")
							+ "\nBingo Bonus - " + getWalletBalance("Bingo Bonus") + "\nGame Bonus - "
							+ getWalletBalance("Game Bonus"));
					for (int b = 0; buymode == true && b < 30; b++) {
						System.out.println("Buy Mode Iteration # " + b);
						if (s.exists(select2tickets) != null) {
							captureScreenWithFilename("_" + Instant.now().getEpochSecond());
							s.click(select2tickets);
							ticketspurchased = true;
							Thread.sleep(2000);
							System.out.println("Balances after purchasing tickets:\nCash Balance - "
									+ getWalletBalance("Cash") + "\nBingo Bonus - " + getWalletBalance("Bingo Bonus")
									+ "\nGame Bonus - " + getWalletBalance("Game Bonus"));
							Log.info("Balances after purchasing tickets:\nCash Balance - " + getWalletBalance("Cash")
									+ "\nBingo Bonus - " + getWalletBalance("Bingo Bonus") + "\nGame Bonus - "
									+ getWalletBalance("Game Bonus"));
						}
						if (s.exists(purchasealert) != null)
							s.click(purchasealertcontinue);
						if (s.exists(realitycheck) != null) {
							s.click(realitycheckcontinue);
							realitycheckpopup = true;
						}
						if (s.exists(seeall) != null) {
							System.out.println("Buy mode is gone, Pre-buy/Play mode is in, exiting buy-mode loop");
							buymode = false;
							playmode = true;
							return;
						}
						if (s.exists(winnerwindow) != null)
							s.click(winnerwindowcontinue);
					}
				}
				if (s.exists(gameinprogress) != null || s.exists(seeall) != null) {
					System.out.println("Pre-Buy mode game play");
					prebuymode = true;
					for (int c = 0; prebuymode == false && c < 30; c++) {
						System.out.println("Prebuy Mode Iteration # " + c);
						if (s.exists(select2tickets) != null) {
							s.click(select2tickets);
							ticketspurchased = true;
						}
						if (s.exists(realitycheck) != null) {
							s.click(realitycheckcontinue);
							realitycheckpopup = true;
						}
						if (s.exists(winnerwindow) != null)
							s.click(winnerwindowcontinue);
					}
				}
				if (playmode == true) {
					for (int d = 0; gameplaycomplete == false && d < 30; d++) {
						System.out.println("Play Mode Iteration # " + d);
						if (s.exists(realitycheck) != null) {
							s.click(realitycheckcontinue);
							realitycheckpopup = true;
						}
						if (s.exists(winnerwindow) != null)
							s.click(winnerwindowcontinue);
						if (s.exists(zerozero) != null) {
							gameplaycomplete = true;
							ExcelUtils.setCellData(
									"Purchased 2 tickets, Bingo Game play complete - Game ID is " + gameid,
									DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
							return;
						}
					}
				}
				if (s.exists(selectstrip) != null) {
					System.out.println("Found selectstrip");
				}
				if (s.exists(seeall) != null) {
					System.out.println("Found seeall");

				}
				if (s.exists(select2tickets) != null) {
					System.out.println("Found select2tickets");

				}
				if (s.exists(winnerwindow) != null) {
					System.out.println("Found winnerwindow");

				}
				if (s.exists(realitycheck) != null) {
					System.out.println("Found realitycheck");
					s.click(realitycheckcontinue);

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelUtils.setCellData("Bingo Game Play interrupted", DriverScript.iTestStep, Constants.Col_TestStepLog,
					Constants.Sheet_TestSteps);
		}
	}

	public static void buyBingoTickets(String data) throws Exception {

		boolean buymode = false, prebuymode = false, gameplaycomplete = false, loadmode = false, winnerwindows = false,
				sidegames = false, ticketspurchased = false, realitycheckpopup = false, playmode = false;
		String envlabel = getLabel(Constants.URL);
		Screen s = new Screen();
		// String gameid = s.text().substring(s.text().indexOf("ID: ") + 4,
		// s.text().indexOf("ID: ") + 17);
		// System.out.println("Game ID is " + gameid);
		Pattern selectstrip = new Pattern(Constants.bingorepo + envlabel + "_gamemode_selectstrip.PNG");
		Pattern gameinprogress = new Pattern(Constants.bingorepo + envlabel + "_gameinprogress.PNG");
		Pattern tryingtoload = new Pattern(Constants.bingorepo + envlabel + "_tryingtoload.PNG");
		Pattern seeall = new Pattern(Constants.bingorepo + envlabel + "_gamemode_seeallballcall.PNG");
		Pattern buymode_buy = new Pattern(Constants.bingorepo + envlabel + "_buymode_buy.PNG");
		Pattern prebuy_buy = new Pattern(Constants.bingorepo + envlabel + "_prebuymode_buy.PNG");
		Pattern select2tickets = new Pattern(Constants.bingorepo + envlabel + "_select2tickets.PNG");
		Pattern loading = new Pattern(Constants.bingorepo + envlabel + "_loading.PNG");
		Pattern currsymbol = new Pattern(Constants.bingorepo + envlabel + "_currencysymbol.PNG");
		Pattern cancelpurchase = new Pattern(Constants.bingorepo + envlabel + "_cancelpurchase.PNG");
		Pattern purchasealert = new Pattern(Constants.bingorepo + envlabel + "_purchased2ticketssuccessfully.PNG");
		Pattern purchasealertcontinue = new Pattern(
				Constants.bingorepo + envlabel + "_purchased2ticketssuccessfully_continue.PNG");
		Pattern prebuycountdown = new Pattern(Constants.bingorepo + envlabel + "_buymodecountdown.PNG");
		Pattern secondstogothree = new Pattern(Constants.bingorepo + envlabel + "_3secondstogo.PNG");
		Pattern winnerwindow = new Pattern(Constants.bingorepo + "_winnerwindow.PNG");
		Pattern winnerwindowcontinue = new Pattern(Constants.bingorepo + "_winnerwindowcontinue.PNG");
		Pattern zerozero = new Pattern(Constants.bingorepo + envlabel + "_zerozero.PNG").similar((float) 0.5);
		Pattern realitycheck = new Pattern(Constants.bingorepo + envlabel + "_realitycheck.PNG");
		Pattern realitycheckcontinue = new Pattern(Constants.bingorepo + envlabel + "_realitycheckcontinue.PNG");
		Pattern ldnbuynow = new Pattern(Constants.bingorepo + envlabel + "_buynow.PNG");
		System.out.print("Waiting in line to buy tickets...");
		for (int i = 0; ticketspurchased == false && i < 60; i++) {
			System.out.print(".");
			if (s.exists(zerozero) != null) {
				System.out.println("Found zerozero, scoring now");
				System.out.println("Balances before purchasing tickets:\nCash Balance - " + getWalletBalance("Cash")
						+ "\nBingo Bonus - " + getWalletBalance("Bingo Bonus") + "\nGame Bonus - "
						+ getWalletBalance("Game Bonus"));
				Log.info("Balances before purchasing tickets:\nCash Balance - " + getWalletBalance("Cash")
						+ "\nBingo Bonus - " + getWalletBalance("Bingo Bonus") + "\nGame Bonus - "
						+ getWalletBalance("Game Bonus"));
				if (getLabel(Constants.URL).equals("gamevillage") || getLabel(Constants.URL).equals("beckysbingo"))
					s.click(ldnbuynow);
				s.click(select2tickets);
				captureScreenWithFilename("BingoRoom" + Constants.bingoroomname + Instant.EPOCH);
				Constants.bingopurchasetime = getTime();
				ticketspurchased = true;
				System.out.println("Balances after purchasing tickets:\nCash Balance - " + getWalletBalance("Cash")
						+ "\nBingo Bonus - " + getWalletBalance("Bingo Bonus") + "\nGame Bonus - "
						+ getWalletBalance("Game Bonus"));
				Log.info("Balances after purchasing tickets:\nCash Balance - " + getWalletBalance("Cash")
						+ "\nBingo Bonus - " + getWalletBalance("Bingo Bonus") + "\nGame Bonus - "
						+ getWalletBalance("Game Bonus"));
			}
			if (s.exists(realitycheck) != null) {
				s.click(realitycheckcontinue);
				realitycheckpopup = true;
			}
			if (s.exists(winnerwindow) != null) {
				s.click(winnerwindowcontinue);
				winnerwindows = true;
			}
			if (i == 60) {
				System.out.println("Done waiting");
			}
		}
		// validateBingoPurchaseReconcileReport(Constants.bingopurchasetime);

	}

	public static void getBingoRoomScreenshot(String data) throws Exception {
		launchBingoRoom(data);
		buyBingoTickets(data);
	}

	public static void purchaseBingoTickets(String data)

			throws FindFailed, MalformedURLException, IOException, ParseException {
		String envlabel = getLabel(Constants.URL);
		double cash_before_bingo_wager = getWalletBalance("Cash"), cash_after_bingo_wager, bingo_wagered_cash;
		double bingobonus_before_bingo_wager = getWalletBalance("Bingo Bonus"), bingobonus_after_bingo_wager = 0,
				bingo_wagered_bonus;
		String gamemode = null;
//		 try {
		Screen s = new Screen();
//		String text = s.text().substring(s.text().indexOf("ID: ") + 4, s.text().indexOf("ID: ") + 17);
//		System.out.println("Game ID is " + text);
		Pattern selectstrip = new Pattern(Constants.bingorepo + envlabel + "_gamemode_selectstrip.PNG");
		Pattern gameinprogress = new Pattern(Constants.bingorepo + envlabel + "_gamemode_gameinprogress.PNG");
		Pattern seeall = new Pattern(Constants.bingorepo + envlabel + "_gamemode_seeallballcall.PNG");
		Pattern buymode_buy = new Pattern(Constants.bingorepo + envlabel + "_buymode_buy.PNG");
		Pattern prebuy_buy = new Pattern(Constants.bingorepo + envlabel + "_prebuymode_buy.PNG");
		Pattern select2tickets = new Pattern(Constants.bingorepo + envlabel + "_select2tickets.PNG");
		Pattern loading = new Pattern(Constants.bingorepo + envlabel + "_loading.PNG");
		Pattern currsymbol = new Pattern(Constants.bingorepo + envlabel + "_currencysymbol.PNG");
		Pattern cancelpurchase = new Pattern(Constants.bingorepo + envlabel + "_cancelpurchase.PNG");
		Pattern purchasealert = new Pattern(Constants.bingorepo + envlabel + "_purchased2ticketssuccessfully.PNG");
		Pattern purchasealertcontinue = new Pattern(
				Constants.bingorepo + envlabel + "_purchased2ticketssuccessfully_continue.PNG");
		Pattern prebuycountdown = new Pattern(Constants.bingorepo + envlabel + "_buymodecountdown.PNG");
		Pattern secondstogothree = new Pattern(Constants.bingorepo + envlabel + "_3secondstogo.PNG");
		Pattern winnerwindow = new Pattern("");
		Pattern realitycheck = new Pattern(Constants.bingorepo + envlabel + "_realitycheck.PNG");
		Pattern zerozero = new Pattern(Constants.bingorepo + envlabel + "_zerozero.PNG");
		// s.wait(loading, 30);
		// s.waitVanish(loading, 15);
		try {
			boolean buymode = false, prebuymode = false;
			for (int i = 0; !buymode && !prebuymode && i <= 30; i++) {
				System.out.println("selectstrip" + s.exists(selectstrip));
				if (s.exists(zerozero) != null) {
					gamemode = "Buy";
					buymode = true;
					System.out.println("Game is in " + gamemode + " Mode");
					System.out.println("Cash and Bingo Bonus balances of the player before tickets purchase :\nCash = "
							+ getWalletBalance("Cash") + "\nBingo Bonus = " + getWalletBalance("Bingo Bonus"));
					captureScreenWithFilename("Before Tickets Purchase" + rand(4));
					s.click(select2tickets);
					s.onAppear(realitycheck);
					Constants.bingopurchasetime = getTime();
					// s.click(buymode_buy);
					Thread.sleep(1000);
					captureScreenWithFilename("After Tickets Purchase" + rand(4));
					Thread.sleep(1000);
					System.out.println("Cash and Bingo Bonus balances of the player after tickets purchase :\nCash = "
							+ getWalletBalance("Cash") + "\nBingo Bonus = " + getWalletBalance("Bingo Bonus"));
					// s.wait(prebuycountdown, 60);
					s.wait(secondstogothree, 60);
					System.out.println("Count down's almost over, 3 seconds to go");
					s.wait(seeall, 60);
					cash_after_bingo_wager = getWalletBalance("Cash");
					bingobonus_after_bingo_wager = getWalletBalance("Bingo Bonus");
					// getWalletBalance("Game Bonus");
					getWalletBalance("Bingo Bonus");
					bingo_wagered_cash = cash_before_bingo_wager - cash_after_bingo_wager;
					bingo_wagered_bonus = bingobonus_before_bingo_wager - bingobonus_after_bingo_wager;
					System.out.println("Bingo Cash wagering for current session: "
							+ new DecimalFormat("##.##").format(bingo_wagered_cash));
					System.out.println("Bingo Bonus wagering for current session: "
							+ new DecimalFormat("##.##").format(bingo_wagered_bonus));
					// break;
				}
				// break;
				System.out.println("seeall" + s.exists(seeall));
				System.out.println("gameinprogress" + s.exists(gameinprogress));
				if (s.exists(seeall) != null || s.exists(gameinprogress) != null) {
					gamemode = "Pre-Buy";
					prebuymode = true;
					System.out.println("Game is in " + gamemode + " Mode");
					System.out.println("Cash and Bingo Bonus balances of the player before tickets purchase :\nCash = "
							+ getWalletBalance("Cash") + "\nBingo Bonus = " + getWalletBalance("Bingo Bonus"));
					captureScreenWithFilename("Before Tickets Purchase" + rand(4));
					s.click(prebuy_buy);
					captureScreenWithFilename("After Tickets Purchase" + rand(4));
					System.out.println("Cash and Bingo Bonus balances of the player after tickets purchase :\nCash = "
							+ getWalletBalance("Cash") + "\nBingo Bonus = " + getWalletBalance("Bingo Bonus"));
					System.out.println("Purchased tickets, waiting for game to be in Buy mode");
					s.waitVanish(cancelpurchase, 300);
					System.out.println("Cancel purchase vanished");
					Constants.bingopurchasetime = getTime();
					s.wait(purchasealert, 300);
					System.out.println("Purchase alert is shown");
					s.click(purchasealertcontinue);
					System.out.println("Clicked on purchase alert continue");
					cash_after_bingo_wager = getWalletBalance("Cash");
					bingobonus_after_bingo_wager = getWalletBalance("Bingo Bonus");
					// getWalletBalance("Game Bonus");
					getWalletBalance("Bingo Bonus");
					bingo_wagered_cash = cash_before_bingo_wager - cash_after_bingo_wager;
					bingo_wagered_bonus = bingobonus_before_bingo_wager - bingobonus_after_bingo_wager;
					System.out.println("Bingo Cash wagering for current session: "
							+ new DecimalFormat("##.##").format(bingo_wagered_cash));
					System.out.println("Bingo Bonus wagering for current session: "
							+ new DecimalFormat("##.##").format(bingo_wagered_bonus));
					// break;
				}
				// break;
				System.out.println("Been waiting for " + i + " seconds");
				Thread.sleep(1000);
				// break;
			}
			System.out.println("Validating reconcile reports and game logs");
			// validateBingoPurchase(bingopurchasetime);
			// s.wait(selectstrip, 30);
			// if (s.exists(selectstrip) != null) {
			// gamemode = "Buy";
			// System.out.println("Game is in " + gamemode + " Mode");
			// s.click(select2tickets);
			// s.click(buymode_buy);
			// }
			// } catch (Exception e) {
			// System.out.println("Game is not in Buy mode");
			// }
			// try {
			// // s.wait(seeall, 30);
			// // s.wait(gameinprogress, 30);
			// if (s.exists(seeall) != null || s.exists(gameinprogress) != null) {
			// gamemode = "Pre-Buy";
			// System.out.println("Game is in " + gamemode + " Mode");
			// captureScreenWithFilename("Buying Bingo Tickets");
			// s.click(prebuy_buy);
			// }
			// } catch (Exception e) {
			// System.out.println("Game is not in Pre-Buy mode");
			// }
			// String envlabel = getLabel(Constants.URL);
			// Pattern buybutton = new Pattern(
			// "C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_BingoImageRepository\\BingoBuyButton.PNG");
			// Pattern loading = new Pattern(
			// "C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_BingoImageRepository\\landmarkbingo_loadingconfiguration.PNG");
			// Pattern loginpopup = new
			// Pattern(Constants.bingorepo
			// + envlabel + "_bingoroomlogin.PNG");
			// s.wait(buybutton, 30);
			// s.click(buybutton);
			// // s.click(buybutton);
			ExcelUtils.setCellData("Purchased 2 tickets successfully", DriverScript.iTestStep,
					Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DriverScript.bResult = false;
			System.out.println("outer catch");
		}

	}

	public static void validateBingoPurchaseReconcileReport(String data) throws Exception {
		String[][] reconcile = new String[20][7];
		String[] bingoreconcile = new String[7];
		String wageramount = null;
		try {
			driver.switchTo().window(Constants.parentwindow);
			driver.get(Constants.URL + "/dashboard");
			navDashboardMenu("Reports");
			navDashboardReportsSubMenu("Reconcile Report");
			System.out.println("Clicking submit");
			driver.findElement(By.id("edit-submit--12")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//table[@id='wallet-reconcile-table']/child::*)[2]/child::*")));
			System.out.println("Done waiting for the table to appear");
			List<WebElement> tr_collection = driver
					.findElements(By.xpath("(//table[@id='wallet-reconcile-table']/child::*)[2]/child::*")); // FOR
																												// LANDMARK
			// 572018 CHANGE
			System.out.println("Got the table collection");
			System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
			// // VK - commenting
			int row_num, col_num, row = 0, col = 0, bingorow = 0;
			System.out.println("Reading rows");
			row_num = 1;
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
				System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); // VK -
				// commenting
				col_num = 1;
				for (WebElement tdElement : td_collection) {
					// VK - USE THE BELOW PRINT LINE TO SEE WHAT THE TABLE DATA CONTAINS - ROW WISE
					// AND COLUMN WISE
					System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
					reconcile[row_num - 1][col_num - 1] = tdElement.getText();
					// System.out.println("Reconcile's "+reconcile[row_num][col_num]);
					col_num++;
					if (tdElement.getText().contains(Constants.bingopurchasetime)) {
						System.out.println("Reconcile report has the transaction");
						row = row_num;
						bingorow = row;
						System.out.println("It's at row " + row + " and column " + col_num);

					} else {
						// DriverScript.bResult = false;
						// ExcelUtils.setCellData(
						// "Verification of Bingo tickets purchase in Reconcile report unsuccessful",
						// DriverScript.iTestStep, Constants.Col_TestStepLog,
						// Constants.Sheet_TestSteps);

					}
				}
				row_num++;
			}
			for (int n = 0; n <= 5; n++) {
				bingoreconcile[n] = reconcile[bingorow][n];
			}
			System.out.println("According to Reconcile Report\nWagered amount is " + bingoreconcile[2]);
			System.out.println("Balance amount is " + bingoreconcile[3]);
			System.out.println("Date and time of purchase is " + bingoreconcile[4]);
			captureScreenWithFilename("Bingo Reconcile Report_" + rand(4));
			reconcile = Arrays.stream(reconcile).filter(s -> (s != null && s.length > 0)).toArray(String[][]::new);
			System.out.println("Stored this in our line :\n" + bingoreconcile);
			System.out.println("Matrix had this :\n" + Arrays.deepToString(reconcile).replaceAll("], ", "]\n"));
			// ExcelUtils.setCellData("Successfully verified Bingo tickets purchase in
			// Reconcile report",
			// DriverScript.iTestStep, Constants.Col_TestStepLog,
			// Constants.Sheet_TestSteps);
			System.out.println("Successfully verified Bingo tickets purchase in Reconcile report");
		} catch (Exception e) {
			e.printStackTrace();
			ExcelUtils.setCellData("Verification of Bingo tickets purchase in Reconcile report unsuccessful",
					DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
		}

	}

	public static void validateBingoGameLogs() {

	}

	public static void bingoHandler() {

	}

	public static void enableFlash() throws InterruptedException, FindFailed {

//		Actions action = new Actions(driver);
//		// action.contextClick().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build()
//		// .perform();
//		Thread.sleep(10000);
//		new WebDriverWait(driver, 10)
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='game_iframe']")));
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='game_iframe']")));
//		System.out.println(driver.getWindowHandle());
//		String gameURL = driver.getCurrentUrl();
//		driver.get(gameURL);
		// flashApp.callFlashObject("Play");
		// Thread.sleep(5000);
		// new WebDriverWait(driver,
		// 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='Game']")));
		// driver.findElement(By.xpath("//*[@name='Game']")).click();
		// driver.findElement(By.id("game_iframe")).click();
		// prefs.put("PluginsAllowedForUrls", "https://arlo.netgear.com");
		// options.setExperimentalOption("prefs", prefs);
//		 driver.manage().window().setSize(new Dimension(1024, 768));
		// action.click().perform();
		 Screen s = new Screen();
		 Pattern puzzle = new
		 Pattern("C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\enableFlash.PNG");
		 Pattern allow = new
		 Pattern("C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\enableFlashAllow.PNG");
		 s.wait(puzzle,60);
		 s.click(puzzle);
		 s.wait(allow,10);
		 s.click(allow);
		// try {
		// WebDriverWait wait = new WebDriverWait(driver, 5);
		// wait.until(ExpectedConditions.alertIsPresent());
		// Alert alert = driver.switchTo().alert();
		// System.out.println(alert.getText());
		// alert.accept();
		// Assert.assertTrue(alert.getText().contains("Thanks."));
		// } catch (Exception e) {
		// e.printStackTrace();
		//
		// //exception handling
		// }
		// driver.manage().window().fullscreen();
		// try {
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		// wait.until(ExpectedConditions.alertIsPresent());
		// Alert alert = driver.switchTo().alert();
		// alert.accept();
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println("Alert's not here");
		// }
	}

	public static void prebuyBingo(String data) throws InterruptedException {
		Instant start = Instant.now();
		String instant = start.toString();
		System.out.println(instant);
		String eff_date = instant.substring(8, 10) + "-"
				+ WordUtils.capitalizeFully(
						Month.of(Integer.parseInt((instant.substring(5, 7)))).toString().substring(0, 3))
				+ "-" + instant.substring(0, 4);
		System.out.println(eff_date);
		if (!driver.getCurrentUrl().equals(Constants.URL + "/bingo/prebuy"))
			driver.get(Constants.URL + "/bingo/prebuy");
		driver.findElement(By.id("edit-from-date-datepicker-popup-0")).clear();
		driver.findElement(By.id("edit-from-date-datepicker-popup-0")).sendKeys(eff_date);
		Select eff_from_time = new Select(driver.findElement(By.id("edit-from-time")));
		int efffromtime = (Integer.parseInt(instant.substring(11, 13)) + 1);
		String efffromtimestr = Integer.toString(efffromtime);
		System.out.println(efffromtimestr);
		eff_from_time.selectByValue(efffromtimestr);
		driver.findElement(By.id("edit-to-date-datepicker-popup-0")).clear();
		driver.findElement(By.id("edit-to-date-datepicker-popup-0")).sendKeys(eff_date);
		Select eff_to_time = new Select(driver.findElement(By.id("edit-to-time")));
		int efftotime = (Integer.parseInt(instant.substring(11, 13)) + 2);
		String efftotimestr = Integer.toString(efftotime);
		System.out.println(efftotimestr);
		eff_to_time.selectByValue(efftotimestr);
		Select gametype = new Select(driver.findElement(By.id("edit-game-type")));
		eff_to_time.selectByValue(efftotimestr);
		if (data.equalsIgnoreCase("BIG BEN") || data.equalsIgnoreCase("VICTORIA 90")) {
			gametype.selectByVisibleText("90 Ball");
			Thread.sleep(5000);
			Select stream = new Select(driver.findElement(By.xpath("//*[@name='game']")));
			if (data.equalsIgnoreCase("BIG BEN"))
				stream.selectByVisibleText("Big Ben");
			Thread.sleep(3000);
			Select category = new Select(driver.findElement(By.xpath("//*[@name='category']")));
			category.selectByVisibleText("All Games");
			if (data.equalsIgnoreCase("VICTORIA 90"))
				stream.selectByVisibleText("Victoria 90");
		} else if (data.equalsIgnoreCase("DOWN TOWN")) {
			gametype.selectByVisibleText("80 Ball");
			Thread.sleep(5000);
			Select stream = new Select(driver.findElement(By.xpath("//*[@name='game']")));
			stream.selectByVisibleText("Down Town");
			Select category = new Select(driver.findElement(By.xpath("//*[@name='category']")));
			category.selectByVisibleText("All Games");
		} else if (data.equalsIgnoreCase("CITY HALL")) {
			gametype.selectByVisibleText("30 Ball");
			Thread.sleep(5000);
			Select stream = new Select(driver.findElement(By.xpath("//*[@name='game']")));
			stream.selectByVisibleText("City Hall");
			Select category = new Select(driver.findElement(By.xpath("//*[@name='category']")));
			category.selectByVisibleText("All Games");
		} else if (data.equalsIgnoreCase("PENNY BINGO")) {
			gametype.selectByVisibleText("75 Ball");
			Thread.sleep(5000);
			Select stream = new Select(driver.findElement(By.xpath("//*[@name='game']")));
			stream.selectByVisibleText("Penny Bingo");
			Select category = new Select(driver.findElement(By.xpath("//*[@name='category']")));

			category.selectByVisibleText("All Games");
		}
		driver.findElement(By.id("edit-number-of-games")).sendKeys("2");
		driver.findElement(By.id("edit-number-of-tickets")).sendKeys("2");
		captureScreen();
		driver.findElement(By.id("edit-submit")).click();
		System.out.println(driver.findElement(By.xpath("(//*[@id='main']/child::*)[3]")).getText());
		Log.info(driver.findElement(By.xpath("(//*[@id='main']/child::*)[3]")).getText());
		// Read more:
		// http://www.java67.com/2012/12/how-to-display-date-in-multiple-timezone-java.html#ixzz5CYB3del1
		// refer
		// https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
		// for more info
		// driver.close();
	}

	public static void launchSlotsGame(String data) throws Exception {
		Constants.slotdude = data;
		boolean loginissue = false;
		try {
			// Thread.sleep(1000);
			driver.switchTo().window(Constants.parentwindow);
			allCookies = driver.manage().getCookies();
			gotoSlotsLobby();
			getWalletBalance("Cash");
			driver.findElement(By.xpath("//*[@id='edit-title']")).clear();
			driver.findElement(By.xpath("//*[@id='edit-title']")).sendKeys(data);
			// Thread.sleep(5000);
			int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
			String gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
			System.out.println(gameinternalid);
			try {
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]")));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				dataEngineLog("Search for the game " + data + " has not returned any results");
			}
			WebElement gameid = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"));
			String gameidtext = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"))
					.getAttribute("innerHTML").trim();
			System.out.println("GameID Text is :" + gameidtext);
			WebElement gameid2 = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
			String gameid2text = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
					.getAttribute("innerHTML").trim();
			System.out.println("GameID2 Test is: " + gameid2text);
			Actions action = new Actions(driver);
			if (gameidtext.contains("Play")) {
				Thread.sleep(5000);
				try {
					action.moveToElement(gameid).click(gameid).build().perform();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					gameid = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
					gameidtext = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
							.getAttribute("innerHTML").trim();
					System.out.println("GameID2 Test is: " + gameidtext);
					action = new Actions(driver);
					action.moveToElement(gameid).click(gameid).build().perform();
				}
				Constants.slotslaunchtime = Instant.now();
				// captureScreen();562018
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> slotgamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + slotgamewindow);
				System.out.println("Clicked first Play");
			} else if (gameid2text.contains("Play")) {
				Thread.sleep(3000);
				try {
					action.moveToElement(gameid2).click(gameid2).build().perform();
				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					gameid2 = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
					gameid2text = driver
							.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
							.getAttribute("innerHTML").trim();
					System.out.println("GameID2 Test is: " + gameid2text);
					action = new Actions(driver);
					action.moveToElement(gameid2).click(gameid2).build().perform();
					System.out.println("Clicked second Play");
				}
				Constants.slotslaunchtime = Instant.now();
				// captureScreen();562018
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> slotgamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + slotgamewindow);
			}

			for (String slotswindows : driver.getWindowHandles()) {
				if (!slotswindows.toString().equals(Constants.parentwindow)
						&& !slotswindows.toString().equals(Constants.bingohandle)
						&& !slotswindows.toString().equals(Constants.casinohandle)
						&& !slotswindows.toString().equals(Constants.scratchhandle)) {
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					driver.switchTo().window(slotswindows);
					driver.manage().window().maximize();
					Constants.slothandle = driver.getWindowHandle();
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("After switching to Slotswindow, driver has :" + Constants.driverhandle);
					System.out.println("Slot Game window ID is :" + Constants.slothandle);
					System.out.println("Slot window title is :" + driver.getTitle());
					// driver.navigate().refresh();
					// System.out.println("Driver currently has " + Constants.driverhandle + " with
					// title "
					// + driver.getTitle() + " and is resizing it");
					driver.manage().window().setSize(new Dimension(1024, 786));
					ExcelUtils.setCellData("Launched " + data + " Slots Game successfully", DriverScript.iTestStep,
							Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
					// action.contextClick().perform();
					// Thread.sleep(45000);
					// if (!Constants.secondlaunch) { //// removed ! for 562018 //7192018
					// // Thread.sleep(25000);
					// System.out.println("inside second launch loop");
					// Screen s = new Screen();
					// String envlabel = getLabel(Constants.URL);
					// Pattern login = new Pattern(Constants.slotsrepo + data +
					// "_cozyslotslogin.PNG");
					// Pattern spin = new Pattern(Constants.slotsrepo + data + "_spin.PNG");
					// Pattern eyeconbackground = new Pattern(Constants.slotsrepo + data +
					// "_confirmsound.PNG");
					// Pattern cozybackground = new Pattern(Constants.slotsrepo + data +
					// "_cozybackground");
					// try {
					//
					// for (int i = 0; !loginissue && i < 40; i++) {
					// System.out.println("Probably worth the wait..");
					// if (gameinternalid.contains("netent")) {
					// Pattern netentcontinue = new Pattern(Constants.slotsrepo + data +
					// "_continue.PNG");
					// if (s.exists(netentcontinue) != null) {
					// System.out.println("NetEnt is fine cause I found what's hidden");
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					// if (s.exists(login) != null) {
					// System.out.println("NetEnt is fine cause login issue isn't here");
					// captureScreenWithFilename("SlotsLoginIssue" + rand(4));
					// driver.close();
					// driver.switchTo().window(Constants.parentwindow);
					// loginissue = true;
					// launchSlotsGame(data);
					// break;
					// }
					//
					// } else if (gameinternalid.equals("internal_nyxspectra")
					// || gameinternalid.equals("internal_nyxflux")
					// || gameinternalid.equals("internal_nyxdjwild")) {
					// // Pattern nyxcontinue = new Pattern (Constants.slotsrepo+data+"_continue");
					// // Pattern nyxskip = new Pattern (Constants.slotsrepo+data+"_skip");
					// Pattern nyxloadcomplete = new Pattern(
					// Constants.slotsrepo + data + "_nyxloadcomplete");
					// System.out.println("This is NYX");
					// if (s.exists(nyxloadcomplete) != null) {
					// s.click(nyxloadcomplete);
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					// } else if (gameinternalid.equals("internal_winaztecgold")
					// || gameinternalid.equals("internal_wincashcruise")
					// || gameinternalid.equals("internal_winpalladium")
					// || gameinternalid.equals("internal_winlootenkhamun")) {
					// Pattern wincomplete = new Pattern(Constants.slotsrepo + data +
					// "_wincomplete");
					// System.out.println("This is Win");
					// if (s.exists(wincomplete) != null) {
					// s.click(wincomplete);
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					// }
					//
					// else if (gameinternalid.equals("internal_png5xmagic")
					// || gameinternalid.equals("internal_png7sins")
					// || gameinternalid.equals("internal_pngcloudquest")) {
					// Pattern pngcomplete = new Pattern(Constants.slotsrepo + data +
					// "_pngcomplete");
					// System.out.println("This is Play n Go");
					// if (s.exists(pngcomplete) != null) {
					// s.click(pngcomplete);
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					// }
					//
					// else if (gameinternalid.equals("internal_blueprintted")
					// || gameinternalid.equals("internal_blueprintwildantics")
					// || gameinternalid.equals("internal_blueprintwishupon")
					// || gameinternalid.equals("internal_blueprintcaspersmm")
					// || gameinternalid.equals("internal_blueprintkingkongcash")) {
					// Pattern blueprintcomplete = new Pattern(
					// Constants.slotsrepo + data + "_blueprintcomplete");
					// System.out.println("This is Blueprint");
					// if (s.exists(blueprintcomplete) != null) {
					// s.click(blueprintcomplete);
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					// }
					//
					// else if (gameinternalid.equals("internal_yggvikingsgoberzerk")
					// || gameinternalid.equals("internal_yggjunglebooks")
					// || gameinternalid.equals("internal_yggpumpkinsmash")
					// || gameinternalid.equals("internal_yggorientexpress")
					// || gameinternalid.equals("internal_yggrainbowryan")) {
					// Pattern yggcomplete = new Pattern(Constants.slotsrepo + data +
					// "_yggcomplete");
					// System.out.println("This is YGG");
					// if (s.exists(yggcomplete) != null) {
					// s.click(yggcomplete);
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					// }
					//
					// else if (false) {
					// if (s.exists(login) != null) {
					// System.out.println("login issue's here, closing and relaunching game");
					// captureScreenWithFilename("SlotsLoginIssue" + rand(4));
					// driver.close();
					// driver.switchTo().window(Constants.parentwindow);
					// loginissue = true;
					// launchSlotsGame(data);
					// }
					// if (s.exists(cozybackground) != null) {
					// System.out.println("Breaking out of the loop cause I found what's hidden");
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					// if (s.exists(spin) != null) {
					// System.out.println("Breaking out of the loop since login issue isn't here");
					// Constants.slotsloadcompletetime = Instant.now();
					// break;
					// }
					//
					// if (s.exists(eyeconbackground) != null) {
					// System.out.println(
					// "Breaking out of the loop since login issue isn't here for the beautiful
					// Eyecon game");
					// break;
					// }
					// break;// 6202018
					// }
					// }
					// System.out.println("Out of For loop");
					//
					// // s.wait(login, 120);
					// // if (s.exists(login) != null) {
					// // System.out.println("login issue's here, closing and relaunching game");
					// // captureScreenWithFilename("SlotsLoginIssue");
					// // driver.close();
					// // driver.switchTo().window(Constants.parentwindow);
					// // Constants.secondlaunch = true;
					// // launchSlotsGame(data);
					// // }
					// } catch (Exception e) {
					// System.out.println(
					// "Waited for login issue window to appear, but it didn't and that's a good
					// thing "
					// + Instant.now());
					// Log.info("Waited for login issue window to appear, but it didn't and that's a
					// good thing "
					// + Instant.now());
					// }
					// } //7192018
					// /////// delete this block of code on 652018////
					//
					// Screen s = new Screen();
					// Pattern almostspin = new Pattern(
					// "C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\Brittonaire_spin.PNG");
					// try {
					// s.wait(almostspin, 120);
					// } catch (Exception e) {
					// }
					// if (s.exists(almostspin) != null) {
					// captureScreenWithFilename(data);
					// captureScreen();
					// System.out.println("General template game is here so taking the screenshot
					// and exiting");
					// Log.info("General template game " + data + " is here so taking the screenshot
					// and exiting");
					// driver.close();
					// } else {
					// System.out.println(
					// "Some weirdo template game " + data + " it is, so waiting to capture
					// screenshot");
					// Log.info("Some weirdo template game " + data + " it is, so waiting to capture
					// screenshot");
					// Thread.sleep(45000);
					// captureScreen();
					// captureScreenWithFilename(data);
					// // captureScreen();
					// driver.close();// 562018
					// }
					//
					// /////// delete this block of code on 652018////

					// driver.quit();
					// Screen s=new Screen();
					// Pattern cont = new
					// Pattern("C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\SBC.PNG");
					// Pattern contbig = new
					// Pattern("C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\SBC_BigScreen.PNG");
					// s.wait(cont,60);
					// s.click(cont);
					// Pattern spinbutton = new
					// Pattern("C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\StarBurstSpinButton.PNG");
					// s.wait(spinbutton,10);
					// s.click(spinbutton);
					//// System.out.println("it is clickable");

					// validateSlotGameElements(data);
				}
			}

		} catch (Exception e) {
			// driver.close();
			e.printStackTrace();
			DriverScript.bResult = false;
			ExcelUtils.setCellData("Slots game " + data + " is not available", DriverScript.iTestStep,
					Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			System.out.println("Unable to launch Slots");
		}
	}

	public static void slotsRegularPlay(String data) throws Exception {
		for (int j = 0; j <= Integer.parseInt(data); j++) {
			try {
				double cash_before_slots_wager = getWalletBalance("Cash"), cash_after_slots_wager = 0,
						slots_wagered_cash;
				double gamebonus_before_slots_wager = getWalletBalance("Bingo Bonus"), gamebonus_after_slots_wager = 0,
						slots_wagered_bonus = 0;
				int gameindex = Arrays.asList(Constants.gamesnames).indexOf(Constants.slotdude);
				System.out.println("Game index is " + gameindex);
				String gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
				if (gameinternalid.contains("netent")) {
					driver.manage().window().setSize(new Dimension(1024, 786));
					System.out.println("this is that NetEnt game");
					Log.info("we launched a NetEnt game");
					Screen s = new Screen();
					Pattern netentloading = new Pattern(
							"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\NetEnt_loadingicon.PNG");
					Pattern netentcontinue = new Pattern(Constants.slotsrepo + data + "_continue.PNG");
					Pattern netentspin = new Pattern(Constants.slotsrepo + data + "_spin.PNG");
					Pattern coinvalueminus = new Pattern(Constants.slotsrepo + data + "_coinvalueminus.PNG");
					start = Instant.now();
					s.wait(netentloading, 10);
					end = Instant.now();
					System.out.println("NetEnt loading icon has appeared in "
							+ (Duration.between(start, end).toMillis()) / 1000 + " seconds");
					Log.info("NetEnt loading icon has appeared in " + (Duration.between(start, end).toMillis()) / 1000
							+ " seconds");
					captureScreen();
					start = Instant.now();
					s.wait(netentcontinue, 30);
					end = Instant.now();
					System.out.println("NetEnt continue button has appeared in "
							+ (Duration.between(start, end).toMillis()) / 1000 + " seconds");
					Log.info("NetEnt continue button has appeared in "
							+ (Duration.between(start, end).toMillis()) / 1000 + " seconds");
					captureScreen();
					s.click(netentcontinue);
					start = Instant.now();
					s.wait(coinvalueminus, 10);
					end = Instant.now();
					System.out.println("coinvalueminus has appeared in "
							+ (Duration.between(start, end).toMillis()) / 1000 + " seconds");
					s.click(coinvalueminus);
					start = Instant.now();
					s.wait(netentspin, 10);
					end = Instant.now();
					captureScreen();
					start = Instant.now();
					s.click(netentspin);
					end = Instant.now();
					System.out.println("netentspin has appeared in " + (Duration.between(start, end).toMillis()) / 1000
							+ " seconds");
					s.click(netentspin);

				}

				else if (gameinternalid.equals("kittypayout") || gameinternalid.equals("puggypayout")) {
					driver.manage().window().setSize(new Dimension(1024, 786));
					boolean soundpopup = false, rcp = false, spinbutton = false, gameplaycomplete = false;
					Screen s = new Screen();
					Pattern spin = new Pattern(Constants.slotsrepo + data + "_spin");
					Pattern confirmsound = new Pattern(Constants.slotsrepo + data + "_confirmsound");
					Pattern confirmsoundyes = new Pattern(Constants.slotsrepo + data + "_confirmsoundyes");
					Pattern realitycheck = new Pattern(Constants.slotsrepo + data + "_realitycheck");
					Pattern realitycheckcontinue = new Pattern(Constants.slotsrepo + data + "_realitycheckcontinue");
					Pattern sessionexpired = new Pattern(Constants.slotsrepo + data + "_sessionexpired");
					Pattern sessionexpiredreload = new Pattern(Constants.slotsrepo + data + "_sessionexpiredreload");
					s.wait(confirmsound, 10);
					for (int i = 0; !soundpopup && !rcp && !spinbutton && !gameplaycomplete && i < 60; i++) {
						System.out.println("Checking for things in here...");
						if (s.exists(confirmsound) != null) {
							Thread.sleep(500);
							System.out.println("Found confirm sound pop-up");
							captureScreenWithFilename(data + " Sound pop-up" + rand(4));
							s.click(confirmsoundyes);
							Thread.sleep(500);
						}
						if (s.exists(realitycheck) != null) {
							Thread.sleep(500);
							captureScreenWithFilename(data + " Reality Check Popup" + rand(4));
							s.click(realitycheckcontinue);
							System.out.println("Found reality check pop-up");
							Thread.sleep(500);
						}
						if (s.exists(spin) != null) {
							Thread.sleep(500);
							System.out.println("Spin button's here");
							captureScreenWithFilename(data + " BeforeSpin_" + rand(3));
							s.click(spin);
							Thread.sleep(500);
							cash_after_slots_wager = getWalletBalance("Cash");
							slots_wagered_cash = cash_before_slots_wager - cash_after_slots_wager;
							System.out.println("Cash wagering for current Slots session: "
									+ new DecimalFormat("##.##").format(slots_wagered_cash));
							System.out.println("Bingo Bonus wagering for current session: "
									+ new DecimalFormat("##.##").format(slots_wagered_bonus));
							gameplaycomplete = true;
						}
						if (s.exists(sessionexpiredreload) != null && s.exists(sessionexpired) != null) {
							System.out.println("Session issue is here again. Closing the game and relaunching");
							driver.close();
							launchSlotsGame(data);
							slotsRegularPlay(data);
						}
						System.out.println("I have been playing this slots game for more than " + i + " seconds");
					}
					System.out.println(data + " Slots game play complete");
				}

				else {
					System.out.println("I'm cozy");
					driver.manage().window().setSize(new Dimension(1024, 786));
					Screen s = new Screen();
					Pattern spin = new Pattern(Constants.slotsrepo + Constants.slotdude + "_spin.PNG");

					s.wait(spin, 60);
					if (null != s.exists(spin)) {
						System.out.println("Spin button is here");
						cash_before_slots_wager = getWalletBalance("Cash");
						System.out.println("Cash balance before spin is: " + getWalletBalance("Cash"));
						captureScreenWithFilename(data + "BeforeSpin" + rand(4));
						s.click(spin);
						Thread.sleep(3000);
						slots_wagered_cash = cash_before_slots_wager - cash_after_slots_wager;
						slots_wagered_bonus = gamebonus_before_slots_wager - gamebonus_after_slots_wager;
						System.out.println("Cash wagering for current Slots session: "
								+ new DecimalFormat("##.##").format(slots_wagered_cash));
						System.out.println("Game Bonus wagering for current session: "
								+ new DecimalFormat("##.##").format(slots_wagered_bonus));
						captureScreenWithFilename(data + "AfterSpin");
						System.out.println("Cash balance after spin is: " + getWalletBalance("Cash"));
						// String reconcileformat = DateTimeFormatter.ofPattern("EEE,
						// dd-MMM-YYYY").format(LocalDateTime.now());
						// String reconciletime = Instant.now().toString().substring(11, 16);
						// String spintime = Instant.now().toString();
						// System.out.println("reconcile format is :" + reconcileformat + " " +
						// reconciletime);
						Constants.slotspintime = getTime();

					} else {
						System.out.println("spinbtn probably not here");
						// s.click(spin);
					}
					// driver.close();
				}
				ExcelUtils.setCellData("Slots game " + data + " Game play complete", DriverScript.iTestStep,
						Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ExcelUtils.setCellData("Game play for " + data + " incomplete", DriverScript.iTestStep,
						Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
				DriverScript.bResult = false;
				e.printStackTrace();
			}
		}
		validateSlotsGameLogs(Constants.slotspintime);
	}

	public static void validateSlotGameReconcileReport(String data) throws Exception {
		String[][] reconcile = new String[20][7];
		String[] slotsreconcile = new String[7];
		try {
			System.out.println("Validating slot game reconcile report");
			driver.switchTo().window(Constants.parentwindow);
			driver.get(Constants.URL + "/dashboard");
			navDashboardMenu("Reports");
			navDashboardReportsSubMenu("Reconcile Report");
			System.out.println("Clicking submit");
			driver.findElement(By.id("edit-submit--12")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//table[@id='wallet-reconcile-table']/child::*)[2]/child::*")));
			System.out.println("Done waiting for the table to appear");
			List<WebElement> tr_collection = driver
					.findElements(By.xpath("(//table[@id='wallet-reconcile-table']/child::*)[2]/child::*")); // FOR
																												// LANDMARK
			// 572018 CHANGE
			System.out.println("Got the table collection");
			System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
			// // VK - commenting
			int row_num, col_num, row = 0, col = 0, slotsrow = 0;
			System.out.println("Reading rows");
			row_num = 1;
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
				System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); // VK -
				// commenting
				col_num = 1;
				for (WebElement tdElement : td_collection) {
					// VK - USE THE BELOW PRINT LINE TO SEE WHAT THE TABLE DATA CONTAINS - ROW WISE
					// AND COLUMN WISE
					System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
					reconcile[row_num - 1][col_num - 1] = tdElement.getText();
					// System.out.println("Reconcile's "+reconcile[row_num][col_num]);
					col_num++;
					if (tdElement.getText().contains(Constants.slotspintime)) {
						System.out.println("Reconcile report has the transaction");
						row = row_num;
						slotsrow = row;
						System.out.println("It's at row " + row + " and column " + col_num);
					} else {
						DriverScript.bResult = false;
						ExcelUtils.setCellData("Verification of Slots spin in Reconcile report unsuccessful",
								DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
					}
				}
				row_num++;
			}
			for (int n = 0; n <= 5; n++) {
				slotsreconcile[n] = reconcile[slotsrow][n];
			}
			System.out.println("According to Reconcile Report\nWagered amount is " + slotsreconcile[2]);
			System.out.println("Balance amount is " + slotsreconcile[3]);
			System.out.println("Date and time of Slots spin is " + slotsreconcile[4]);
			captureScreenWithFilename("Slots Reconcile Report_" + rand(4));
			System.out.println(Arrays.deepToString(reconcile).replaceAll("], ", "]\n"));
			ExcelUtils.setCellData("Successfully verified Slots spin in Reconcile report", DriverScript.iTestStep,
					Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
		} catch (Exception e) {
			e.printStackTrace();
			ExcelUtils.setCellData("Verification of Slots spin in Reconcile report unsuccessful",
					DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
		}
	}

	public static void validateSlotsGameLogs(String data) {
		driver.switchTo().window(Constants.parentwindow);
		driver.get(Constants.URL + "/dashboard");
		navDashboardMenu("Reports");
		navDashboardReportsSubMenu("Reconcile Report");
		driver.findElement(By.id("edit-submit--12")).click();
		captureScreen();

	}

	public static void validateSlotGameElements(String data) throws FindFailed {
		driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1024, 786));
		Screen s = new Screen();
		Pattern cont = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\SBC.PNG");
		Pattern contbig = new Pattern(
				"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\SBC_BigScreen.PNG");
		s.wait(cont, 60);
		s.click(cont);
		Pattern spinbutton = new Pattern(
				"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\StarBurstSpinButton.PNG");
		s.wait(spinbutton, 10);
		s.click(spinbutton);

	}

	public static void slotsAutoPlay(String data) {

	}

	public static void slotsFeaturePlay(String data) {

	}

	public static void slotsWagerLimit(String data) {

	}

	public static void slotsLossLimit(String data) {

	}

	public static void slotsRCP(String data) {

	}

	public static void launchCasinoGame(String data) {

		boolean loginissue = false;
		try {
			driver.switchTo().window(Constants.parentwindow);
			gotoCasinoLobby();
			getWalletBalance("Cash");
			driver.findElement(By.xpath("//*[@id='edit-title']")).clear();
			driver.findElement(By.xpath("//*[@id='edit-title']")).sendKeys(data);
			int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
			String gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
			System.out.println(gameinternalid);
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]")));
			WebElement gameid = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"));
			String gameidtext = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"))
					.getAttribute("innerHTML").trim();
			System.out.println(gameidtext);
			WebElement gameid2 = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
			String gameid2text = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
					.getAttribute("innerHTML").trim();
			System.out.println(gameid2text);
			Actions action = new Actions(driver);
			if (gameidtext.contains("Play")) {
				action.moveToElement(gameid).click(gameid).build().perform();
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> casinogamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + casinogamewindow);
				System.out.println("Clicked first Play");
			} else if (gameid2text.contains("Play")) {
				action.moveToElement(gameid2).click(gameid2).build().perform();
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> casinogamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + casinogamewindow);
				System.out.println("Clicked second Play");
			}
			for (String casinowindows : driver.getWindowHandles()) {
				if (!casinowindows.toString().equals(Constants.parentwindow)
						&& !casinowindows.toString().equals(Constants.bingohandle)
						&& !casinowindows.toString().equals(Constants.slothandle)
						&& !casinowindows.toString().equals(Constants.scratchhandle)) {
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					driver.switchTo().window(casinowindows);
					driver.manage().window().setSize(new Dimension(1024, 786));
					Constants.casinohandle = driver.getWindowHandle();
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					System.out.println("Casino Game window ID is :" + casinowindows);
					System.out.println("Casino window title is :" + driver.getTitle());
					Thread.sleep(1000);
					if (!Constants.secondlaunch) { //// removed ! for 562018
						// Thread.sleep(25000);
						System.out.println("inside second launch loop");
						Screen s = new Screen();
						String envlabel = getLabel(Constants.URL);
						Pattern login = new Pattern(
								"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_SlotImageRepository\\cozyslotslogin.PNG");
						Pattern loginbackground = new Pattern(
								"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_CasinoImageRepository\\" + data
										+ "_loginbackground.PNG");
						try {
							for (int i = 0; !loginissue && i < 60; i++) {
								if (s.exists(login) != null) {
									System.out.println("login issue's here, closing and relaunching game");
									captureScreenWithFilename("CasinoLoginIssue");
									driver.close();
									driver.switchTo().window(Constants.parentwindow);
									loginissue = true;
									launchCasinoGame(data);
								}
								if (s.exists(loginbackground) != null) {
									System.out.println(
											"Breaking out of the loop since login issue isn't here and Sikuli can see the login background");
									break;
								}
								Thread.sleep(1000);
							}
							System.out.println("Out of For loop");
							// s.wait(login, 120);
							// if (s.exists(login) != null) {
							// System.out.println("login issue's here, closing and relaunching game");
							// captureScreenWithFilename("SlotsLoginIssue");
							// driver.close();
							// driver.switchTo().window(Constants.parentwindow);
							// Constants.secondlaunch = true;
							// launchSlotsGame(data);
							// }
						} catch (Exception e) {
							System.out.println(
									"Waited for login issue window to appear, but it didn't and that's a good thing  "
											+ Instant.now());
							Log.info("Waited for login issue window to appear, but it didn't and that's a good thing  "
									+ Instant.now());
						}
					}
				}
			}
			System.out.println("it is clickable");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to launch Casino");
		}
	}

	public static void getGameScreenshot() {

	}

	public static void casinoRegularPlay(String data) throws InterruptedException {
		try {
			double cash_before_casino_wager = getWalletBalance("Cash"), cash_after_casino_wager = 0,
					casino_wagered_cash;

			int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
			String gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
			Screen s = new Screen();
			Pattern deal = new Pattern(
					"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_CasinoImageRepository\\" + data + "_deal.PNG");
			Pattern draw = new Pattern(
					"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_CasinoImageRepository\\" + data + "_draw.PNG");
			getWalletBalance("Cash");
			s.wait(deal, 30);
			s.click(deal);
			s.wait(draw, 5);
			s.click(draw);
			Thread.sleep(2000);
			getWalletBalance("Cash");
			cash_after_casino_wager = getWalletBalance("Cash");
			casino_wagered_cash = cash_before_casino_wager - cash_after_casino_wager;
			System.out.println("Bingo Cash wagering for current session: "
					+ new DecimalFormat("##.##").format(casino_wagered_cash));

		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void validateCasinoReconcileReport(String data) throws Exception {
		String[][] reconcile = new String[20][7];
		String[] slotsreconcile = new String[7];
		try {
			System.out.println("Validating slot game reconcile report");
			driver.switchTo().window(Constants.parentwindow);
			driver.get(Constants.URL + "/dashboard");
			navDashboardMenu("Reports");
			navDashboardReportsSubMenu("Reconcile Report");
			System.out.println("Clicking submit");
			driver.findElement(By.id("edit-submit--12")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//table[@id='wallet-reconcile-table']/child::*)[2]/child::*")));
			System.out.println("Done waiting for the table to appear");
			List<WebElement> tr_collection = driver
					.findElements(By.xpath("(//table[@id='wallet-reconcile-table']/child::*)[2]/child::*")); // FOR
																												// LANDMARK
			// 572018 CHANGE
			System.out.println("Got the table collection");
			System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
			// // VK - commenting
			int row_num, col_num, row = 0, col = 0, slotsrow = 0;
			System.out.println("Reading rows");
			row_num = 1;
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
				System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); // VK -
				// commenting
				col_num = 1;
				for (WebElement tdElement : td_collection) {
					// VK - USE THE BELOW PRINT LINE TO SEE WHAT THE TABLE DATA CONTAINS - ROW WISE
					// AND COLUMN WISE
					System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
					reconcile[row_num - 1][col_num - 1] = tdElement.getText();
					// System.out.println("Reconcile's "+reconcile[row_num][col_num]);
					col_num++;
					if (tdElement.getText().contains(Constants.slotspintime)) {
						System.out.println("Reconcile report has the transaction");
						row = row_num;
						slotsrow = row;
						System.out.println("It's at row " + row + " and column " + col_num);
					} else {
						DriverScript.bResult = false;
						ExcelUtils.setCellData("Verification of Slots spin in Reconcile report unsuccessful",
								DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
					}
				}
				row_num++;
			}
			for (int n = 0; n <= 5; n++) {
				slotsreconcile[n] = reconcile[slotsrow][n];
			}
			System.out.println("According to Reconcile Report\nWagered amount is " + slotsreconcile[2]);
			System.out.println("Balance amount is " + slotsreconcile[3]);
			System.out.println("Date and time of Slots spin is " + slotsreconcile[4]);
			captureScreenWithFilename("Slots Reconcile Report_" + rand(4));
			System.out.println(Arrays.deepToString(reconcile).replaceAll("], ", "]\n"));
			ExcelUtils.setCellData("Successfully verified Slots spin in Reconcile report", DriverScript.iTestStep,
					Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
		} catch (Exception e) {
			e.printStackTrace();
			ExcelUtils.setCellData("Verification of Slots spin in Reconcile report unsuccessful",
					DriverScript.iTestStep, Constants.Col_TestStepLog, Constants.Sheet_TestSteps);
		}
	}

	public static void gmailLogin(String data) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver();
		driver.get("https://www.gmail.com");
		driver.findElement(By.id("identifierId")).sendKeys("keviv8");
		driver.findElement(By.id("identifierNext")).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		driver.findElement(By.name("password")).sendKeys("aditiramkolla");
		driver.findElement(By.id("passwordNext")).click();
		Thread.sleep(2000);
		allCookies = driver.manage().getCookies();
		driver = new ChromeDriver();
		driver.get("https://www.gmail.com");
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
			((ChromeDriver) driver).getSessionStorage();
			System.out.println("Cookie name is " + cookie.getName() + "\nCookie is" + cookie + "\n\n");
		}
		driver.navigate().refresh();
	}

	public static void launchScratchGame(String data) {

		boolean loginissue = false;
		try {
			driver.switchTo().window(Constants.parentwindow);
			gotoScratchLobby();
			getWalletBalance("Cash");
			driver.findElement(By.xpath("//*[@id='edit-title']")).clear();
			driver.findElement(By.xpath("//*[@id='edit-title']")).sendKeys(data);
			int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
			String gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
			System.out.println(gameinternalid);
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]")));
			WebElement gameid = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"));
			String gameidtext = driver.findElement(By.xpath("//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "]"))
					.getAttribute("innerHTML").trim();
			System.out.println(gameidtext);
			WebElement gameid2 = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"));
			String gameid2text = driver
					.findElement(By.xpath("(//*[@data-game-id=" + "\"" + gameinternalid + "\"" + "])[2]"))
					.getAttribute("innerHTML").trim();
			System.out.println(gameid2text);
			Actions action = new Actions(driver);
			if (gameidtext.contains("Play")) {
				action.moveToElement(gameid).click(gameid).build().perform();
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> casinogamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + casinogamewindow);
				System.out.println("Clicked first Play");
			} else if (gameid2text.contains("Play")) {
				action.moveToElement(gameid2).click(gameid2).build().perform();
				System.out.println("Parent window ID is :" + Constants.parentwindow);
				Set<String> casinogamewindow = driver.getWindowHandles();
				System.out.println("All window IDs are :" + casinogamewindow);
				System.out.println("Clicked second Play");
			}
			for (String scratchwindows : driver.getWindowHandles()) {
				if (!scratchwindows.toString().equals(Constants.parentwindow)
						&& !scratchwindows.toString().equals(Constants.bingohandle)
						&& !scratchwindows.toString().equals(Constants.slothandle)
						&& !scratchwindows.toString().equals(Constants.scratchhandle)) {
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					driver.switchTo().window(scratchwindows);
					driver.manage().window().setSize(new Dimension(1024, 786));
					Constants.casinohandle = driver.getWindowHandle();
					Constants.driverhandle = driver.getWindowHandle();
					System.out.println("Driver currently has :" + Constants.driverhandle);
					System.out.println("Scratch Game window ID is :" + scratchwindows);
					System.out.println("Scratch window title is :" + driver.getTitle());
					Thread.sleep(1000);
					if (!Constants.secondlaunch) { //// removed ! for 562018
						// Thread.sleep(25000);
						System.out.println("inside second launch loop");
						Screen s = new Screen();
						String envlabel = getLabel(Constants.URL);
						Pattern login = new Pattern(
								"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_ScratchImageRepository\\cozyscratchlogin.PNG");
						Pattern loginbackground = new Pattern(
								"C:\\Users\\vkolla\\eclipse-workspace\\Sikuli_ScratchImageRepository\\" + data
										+ "_cozyscratchlogin.PNG");
						try {
							for (int i = 0; !loginissue && i < 60; i++) {
								if (s.exists(login) != null) {
									System.out.println("login issue's here, closing and relaunching game");
									captureScreenWithFilename("CasinoLoginIssue");
									driver.close();
									driver.switchTo().window(Constants.parentwindow);
									loginissue = true;
									launchCasinoGame(data);
								}
								if (s.exists(loginbackground) != null) {
									System.out.println(
											"Breaking out of the loop since login issue isn't here and Sikuli can see the login background");
									break;
								}
								Thread.sleep(1000);
							}
							System.out.println("Out of For loop");
							// s.wait(login, 120);
							// if (s.exists(login) != null) {
							// System.out.println("login issue's here, closing and relaunching game");
							// captureScreenWithFilename("SlotsLoginIssue");
							// driver.close();
							// driver.switchTo().window(Constants.parentwindow);
							// Constants.secondlaunch = true;
							// launchSlotsGame(data);
							// }
						} catch (Exception e) {
							System.out.println(
									"Waited for login issue window to appear, but it didn't and that's a good thing  "
											+ Instant.now());
							Log.info("Waited for login issue window to appear, but it didn't and that's a good thing  "
									+ Instant.now());
						}
					}
				}
			}
			System.out.println("it is clickable");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to launch Casino");
		}
	}

	public static void navDashboardMenu(String data) {
		try {
			URL url = new URL(Constants.URL + "/dashboard");
			// System.out.println(Constants.URL+"/api/get_games/desktop");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			System.out.println("Response code is");
			System.out.println(responsecode);
			if (responsecode != 404) {
				if (!driver.getCurrentUrl().equals(Constants.URL + "/dashboard"))
					driver.get(Constants.URL + "/dashboard");
				String[] dashboardtabnames = new String[20];
				String[] dashboardtabids = new String[20];
				for (int i = 1; i <= 5; i++) {
					dashboardtabnames[i] = driver.findElement(By.xpath(
							"(//div[@id='quicktabs-dashboard_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
							.getText();
					dashboardtabids[i] = driver.findElement(By.xpath(
							"(//div[@id='quicktabs-dashboard_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
							.getAttribute("id");
					// System.out.println(dashboardtabnames[i]+"'s ID is "+dashboardtabids[i]);
					if (data.equalsIgnoreCase(dashboardtabnames[i])) {
						driver.findElement(By.id(dashboardtabids[i])).click();
						System.out.println("we did it");
					}
				}

			} else {
				if (data.contains("Profile")) {
					driver.get(Constants.URL + "/user");
				} else if (data.contains("Redeem Coupon")) {
					driver.get(Constants.URL + "/content/redeem-bonus-coupon");
				} else if (data.contains("Deposit")) {
					driver.get(Constants.URL + "/banking/deposit");
				} else if (data.contains("Withdrawal")) {
					driver.get(Constants.URL + "/banking/withdraw/dashboard");
				} else if (data.contains("Deposit History")) {
					driver.get(Constants.URL + "/banking/deposit/history");
				} else if (data.contains("Deposit Limit")) {
					driver.get(Constants.URL + "/velocity/deposit-limit");
				} else if (data.contains("Loss Limits")) {
					driver.get(Constants.URL + "/velocity/loss-limits");
				} else if (data.contains("Wager Limits")) {
					driver.get(Constants.URL + "/velocity/wager-limits");
				} else if (data.contains("Game Time Reminder")) {
					driver.get(Constants.URL + "/velocity/reality-checks");
				} else if (data.contains("Withdrawal History")) {
					driver.get(Constants.URL + "/banking/withdraw/history");
				} else if (data.contains("Reconcile Report")) {
					driver.get(Constants.URL + "/wallet/account-reconcile");
				}

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean isDashboardSite(String data) throws Exception {
		heuristicLogin(data);
		// URL url = new URL(data + "/dashboard");
		// // System.out.println(Constants.URL+"/api/get_games/desktop");
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("GET");
		// conn.connect();
		// int responsecode = conn.getResponseCode();
		// System.out.println("Response code is "+responsecode);
		// dataEngineLog(String.valueOf(responsecode));
		String myaccounturl = null;

		try {
			myaccounturl = driver.findElement(By.xpath("(//*[@class='logout-sec'])[1]/child::*")).getAttribute("href");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			myaccounturl = driver.findElement(By.xpath("(((//*[@class='user-info-sec'])[1]/child::*)[3]/child::*)[1]"))
					.getAttribute("href");
		}

		// user-info-sec
		dataEngineLog("My Account link is " + myaccounturl);
		System.out.println("My Account link is " + myaccounturl);
		driver.close();
		if (myaccounturl.contains("dashboard"))
			return true;
		else if (myaccounturl.contains("user"))
			return false;
		else
			return false;

	}

	public static void navDashboardResponsibleGamingSubMenu(String data) {
		String[] dashboardsubtabnames = new String[20];
		String[] dashboardsubtabids = new String[20];
		for (int i = 1; i < 7; i++) {
			dashboardsubtabnames[i] = driver.findElement(By
					.xpath("(//div[@id='quicktabs-dashboard_responsible_gaming']/child::*/child::*/child::*/child::*)["
							+ i + "]"))
					.getText();
			dashboardsubtabids[i] = driver.findElement(By
					.xpath("(//div[@id='quicktabs-dashboard_responsible_gaming']/child::*/child::*/child::*/child::*)["
							+ i + "]"))
					.getAttribute("id");
			System.out.println(dashboardsubtabnames[i] + "'s ID is " + dashboardsubtabids[i]);
			if (data.equalsIgnoreCase(dashboardsubtabnames[i])) {
				driver.findElement(By.id(dashboardsubtabids[i])).click();
				System.out.println("we did it again");
			}
		}
	}

	public static void navDashboardWithdrawSubMenu(String data) {
		String[] dashboardsubtabnames = new String[20];
		String[] dashboardsubtabids = new String[20];
		for (int i = 1; i < 5; i++) {
			dashboardsubtabnames[i] = driver
					.findElement(By.xpath(
							"(//div[@id='quicktabs-withdraw_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
					.getText();
			dashboardsubtabids[i] = driver
					.findElement(By.xpath(
							"(//div[@id='quicktabs-withdraw_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
					.getAttribute("id");
			System.out.println(dashboardsubtabnames[i] + "'s ID is " + dashboardsubtabids[i]);
			if (data.equalsIgnoreCase(dashboardsubtabnames[i])) {
				driver.findElement(By.id(dashboardsubtabids[i])).click();
				System.out.println("we did it again");
			}

		}

	}

	public static void navDashboardReportsSubMenu(String data) {
		String[] dashboardsubtabnames = new String[20];
		String[] dashboardsubtabids = new String[20];
		for (int i = 1; i < 5; i++) {
			dashboardsubtabnames[i] = driver
					.findElement(By.xpath(
							"(//div[@id='quicktabs-reports_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
					.getText();
			dashboardsubtabids[i] = driver
					.findElement(By.xpath(
							"(//div[@id='quicktabs-reports_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
					.getAttribute("id");
			System.out.println(dashboardsubtabnames[i] + "'s ID is " + dashboardsubtabids[i]);
			if (data.equalsIgnoreCase(dashboardsubtabnames[i])) {
				driver.findElement(By.id(dashboardsubtabids[i])).click();
				System.out.println("we did it again");
			}

		}

	}

	public static void clickThatSubmit(String menu, String submenu) {

	}

	public static void navDashboardDepositSubMenu(String data) {

		String[] dashboardsubtabnames = new String[20];
		String[] dashboardsubtabids = new String[20];
		for (int i = 1; i < 5; i++) {
			dashboardsubtabnames[i] = driver
					.findElement(By.xpath(
							"(//div[@id='quicktabs-deposit_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
					.getText();
			dashboardsubtabids[i] = driver
					.findElement(By.xpath(
							"(//div[@id='quicktabs-deposit_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
					.getAttribute("id");
			System.out.println(dashboardsubtabnames[i] + "'s ID is " + dashboardsubtabids[i]);
			if (data.equalsIgnoreCase(dashboardsubtabnames[i])) {
				driver.findElement(By.id(dashboardsubtabids[i])).click();
				System.out.println("we did it again");
			}

		}

	}

	public static void navDashboardProfileSubMenu(String data) throws IOException {
		URL url = new URL(Constants.URL + "/dashboard");
		// System.out.println(Constants.URL+"/api/get_games/desktop");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();
		System.out.println("Response code is");
		System.out.println(responsecode);
		if (responsecode != 404) {
			String[] dashboardsubtabnames = new String[20];
			String[] dashboardsubtabids = new String[20];
			for (int i = 1; i < 5; i++) {
				dashboardsubtabnames[i] = driver
						.findElement(By.xpath(
								"(//div[@id='quicktabs-profile_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
						.getText();
				dashboardsubtabids[i] = driver
						.findElement(By.xpath(
								"(//div[@id='quicktabs-profile_tabs']/child::*/child::*/child::*/child::*)[" + i + "]"))
						.getAttribute("id");
				System.out.println(dashboardsubtabnames[i] + "'s ID is " + dashboardsubtabids[i]);
				if (data.equalsIgnoreCase(dashboardsubtabnames[i])) {
					driver.findElement(By.id(dashboardsubtabids[i])).click();
					System.out.println("we did it again");
				}

			}
		} else {
			if (data.equalsIgnoreCase("Profile")) {
				driver.get(Constants.URL + "/user");
			}
			if (data.equalsIgnoreCase("Profile")) {
				driver.get(Constants.URL + "/user");
			}

		}

	}

	public static void setDepositLimit(String data) {

		try {

			/////////// DEMO TO VIJAY //////////////

			// navDashboardMenu("My Profile");
			// navDashboardProfileSubMenu("Profile");
			// navDashboardProfileSubMenu("Change Password");
			// navDashboardMenu("Deposit");
			// navDashboardDepositSubMenu("Make a Deposit");
			// navDashboardDepositSubMenu("Deposit History");
			// navDashboardDepositSubMenu("Redeem Vouchers");
			// navDashboardMenu("Responsible Gaming");
			// navDashboardResponsibleGamingSubMenu("Deposit Limit");
			// navDashboardResponsibleGamingSubMenu("Wager Limit");
			// navDashboardResponsibleGamingSubMenu("Loss Limits");
			// navDashboardResponsibleGamingSubMenu("Close Account");
			// navDashboardResponsibleGamingSubMenu("Game Time Reminder");
			// navDashboardResponsibleGamingSubMenu("Self Exclusion/Time-Out");
			// navDashboardMenu("Withdraw");
			// navDashboardWithdrawSubMenu("Request Withdrawal");
			// navDashboardWithdrawSubMenu("Flowback");
			// navDashboardWithdrawSubMenu("History");
			// navDashboardWithdrawSubMenu("Bonus Details");
			// navDashboardMenu("Reports");
			// navDashboardReportsSubMenu("Reconcile Report");
			// navDashboardReportsSubMenu("Game Logs");
			// navDashboardReportsSubMenu("Prebuy History");
			// navDashboardReportsSubMenu("Net Deposits");

			/////////// DEMO TO VIJAY //////////////

			navDashboardMenu("Responsible Gaming");
			navDashboardResponsibleGamingSubMenu("Deposit Limit");
			int comma_index = data.indexOf(",");
			String limit_type = data.substring(0, comma_index);
			String deposit_limit_amount = data.substring(comma_index + 1);
			System.out.println(limit_type);
			System.out.println(deposit_limit_amount);

			if (limit_type.equalsIgnoreCase("daily")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@for='edit-deposit-limit-period-0--2']")).click();
				driver.findElement(By.id("edit-deposit-amount--2")).sendKeys(deposit_limit_amount);
			} else if (limit_type.equalsIgnoreCase("weekly")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@for='edit-deposit-limit-period-1--2']")).click();
				driver.findElement(By.id("edit-deposit-amount--2")).sendKeys(deposit_limit_amount);
			} else if (limit_type.equalsIgnoreCase("monthly")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@for='edit-deposit-limit-period-2--2']")).click();
				driver.findElement(By.id("edit-deposit-amount--2")).sendKeys(deposit_limit_amount);
			}
			// String submitid = driver.findElement(By.xpath(
			// "//*[@class='nucleus-velocity-deposit-limits-form']/child::*/child::*/child::*/child::*/child::*"))
			// .getAttribute("id");
			// driver.findElement(By.id(submitid)).click();
			// Thread.sleep(1000);
			driver.findElement(By.id("edit-submit--6")).click();
			// driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/button[1]/span")).click();
			// driver.switchTo().alert().accept();
			// NEED TO WRITE CODE FOR CONFIRMING THE DEPOSIT LIMIT BY SELECTING "YES" ON
			// POP-UP
			driver.findElement(By.xpath("(//span[@class='ui-button-text'])[2]")).click();
			// driver.findElement(By.className("ui-button-text"));
			// String current_limits =
			// driver.findElement(By.className("placeholder")).getText();
			// WebElement wagobj =
			// driver.findElement(By.xpath("//*[@aria-labelledby='ui-id-3']"));
			// List<WebElement> buttons =
			// driver.findElements(By.xpath("//*[@class=\"ui-button-text\"]"));
			// System.out.println(buttons.size());
			// System.out.println(buttons);
			// Iterator<WebElement> itr = buttons.iterator();
			// while (itr.hasNext()) {
			// System.out.println(itr.next().getText());
			// if (itr.next().getText().equals("YES")) {
			// buttons.indexOf("YES");
			// }
			// }

			// wagobj.findElement(By.xpath("//*[@class='ui-button ui-widget ui-state-default
			// ui-corner-all ui-button-text-only accept']/child::*")).click();
			// System.out.println(current_limits);
			// driver.findElement(By.xpath("//*[@class='ui-button ui-widget ui-state-default
			// ui-corner-all ui-button-text-only accept']/child::*")).click();

			// WOULD BE NICE TO HAVE CODE TO VERIFY IF A LIMIT ALREADY EXISTS
		} catch (Exception e) {

			e.printStackTrace();
			DriverScript.bResult = false;
			System.out.println("Error setting deposit limit");
		}
	}

	public static void setWagerLimit(String data) {
		try {
			try {

				// driver.findElement(By.xpath("//*[@id='quicktabs-tab-dashboard_tabs-2']")).click();
				// driver.findElement(By.id("quicktabs-tab-dashboard_responsible_gaming-1")).click();
				navDashboardMenu("Responsible Gaming");
				navDashboardResponsibleGamingSubMenu("Wager Limit");
			} catch (Exception e) {

				driver.get(
						Constants.URL + "/dashboard?qt-dashboard_responsible_gaming=1#qt-dashboard_responsible_gaming");
			}
			int comma_index = data.indexOf(",");
			String limit_type = data.substring(0, comma_index);
			String wager_limit_amount = data.substring(comma_index + 1);
			System.out.println(limit_type);
			System.out.println(wager_limit_amount);
			if (limit_type.equalsIgnoreCase("session")) {
				driver.findElement(By.xpath("//*[@for='edit-wager-limit-period-0']")).click();
				driver.findElement(By.id("edit-wager-amount")).sendKeys(wager_limit_amount);
			} // *[@id="edit-wager-limit-period"]/div[1]/label
			else if (limit_type.equalsIgnoreCase("daily")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@for='edit-wager-limit-period-1']")).click();
				driver.findElement(By.id("edit-wager-amount")).sendKeys(wager_limit_amount);
			} else if (limit_type.equalsIgnoreCase("weekly")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@for='edit-wager-limit-period-2']")).click();
				driver.findElement(By.id("edit-wager-amount")).sendKeys(wager_limit_amount);
			} else if (limit_type.equalsIgnoreCase("monthly")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@for='edit-wager-limit-period-3']")).click();
				driver.findElement(By.id("edit-wager-amount")).sendKeys(wager_limit_amount);
			}
			driver.findElement(By.id("edit-submit--7")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//span[@class='ui-button-text'])[2]")).click();
			// driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/button[1]/span")).click();
			// NEED TO WRITE CODE FOR CONFIRMING THE WAGER LIMIT BY SELECTING "YES" ON
			// POP-UP
			String current_limits = driver.findElement(By.className("placeholder")).getText();
			System.out.println(current_limits);
			// WOULD BE NICE TO HAVE CODE TO VERIFY IF A LIMIT ALREADY EXISTS
		} catch (Exception e) {
			DriverScript.bResult = false;
			System.out.println("Error setting wager limit");
		}
	}

	public static void setLossLimit(String data) {
		try {
			try {

				// driver.findElement(By.xpath("//*[@id='quicktabs-tab-dashboard_tabs-2']")).click();
				// driver.findElement(By.id("quicktabs-tab-dashboard_responsible_gaming-2")).click();
				navDashboardMenu("Responsible Gaming");
				navDashboardResponsibleGamingSubMenu("Loss Limits");
			} catch (Exception e) {
				driver.get(
						Constants.URL + "/dashboard?qt-dashboard_responsible_gaming=2#qt-dashboard_responsible_gaming");
			}
			int comma_index = data.indexOf(",");
			String limit_type = data.substring(0, comma_index);
			String loss_limit_amount = data.substring(comma_index + 1);
			System.out.println(limit_type);
			System.out.println(loss_limit_amount);
			if (limit_type.equalsIgnoreCase("daily")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"edit-loss-limit-period\"]/div[1]/label")).click();
				driver.findElement(By.id("edit-loss-amount")).sendKeys(loss_limit_amount);
			} else if (limit_type.equalsIgnoreCase("weekly")) {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"edit-loss-limit-period\"]/div[2]/label")).click();
				driver.findElement(By.id("edit-loss-amount")).sendKeys(loss_limit_amount);
			} else if (limit_type.equalsIgnoreCase("monthly")) {
				driver.findElement(By.xpath("//*[@id=\"edit-loss-limit-period\"]/div[3]/label")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("edit-loss-amount")).sendKeys(loss_limit_amount);
			}
			driver.findElement(By.id("edit-submit--8")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//span[@class='ui-button-text'])[2]")).click();

			// NEED TO WRITE CODE FOR CONFIRMING THE LOSS LIMIT BY SELECTING "YES" ON
			// POP-UP
			String current_limits = driver.findElement(By.className("placeholder")).getText();
			System.out.println(current_limits);
			// WOULD BE NICE TO HAVE CODE TO VERIFY IF A LIMIT ALREADY EXISTS
		} catch (Exception e) {

			DriverScript.bResult = false;
			System.out.println("Error setting loss limit");
		}
	}

	public static void setGameTimeReminder(String data) {

		try {
			try {

				driver.findElement(By.xpath("//*[@id='quicktabs-tab-dashboard_tabs-2']")).click();
				driver.findElement(By.id("quicktabs-tab-dashboard_responsible_gaming-4")).click();
			} catch (Exception e) {

				driver.get(
						Constants.URL + "/dashboard?qt-dashboard_responsible_gaming=4#qt-dashboard_responsible_gaming");
			}
			driver.findElement(By.xpath("//*[@for=\"edit-time-interval-custom\"]")).click();
			if (!data.isEmpty()) {
				driver.findElement(By.xpath("//*[@id=\"edit-reality-check-interval\"]")).sendKeys(data);
				driver.findElement(By.id("edit-submit--10")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//span[@class='ui-button-text'])[2]")).click();

			} else {
				driver.findElement(By.xpath("//*[@id=\"edit-reality-check-interval\"]")).sendKeys("1");
				driver.findElement(By.id("edit-submit--10")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//span[@class='ui-button-text'])[2]")).click();

			}
		} catch (Exception e) {

			DriverScript.bResult = false;
		}

	}

	public static void clickPromotionFloatingIconLive(String data) {
		try {
			defaultBrowseClient(data);
			// heuristicLogin(data);
			// openBrowser("Firefox");
			// browseClient(data);
			// try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[@id='block-nucleus-promotion-sep-monthly-sep-monthly-float-icon']/child::*/child::*")));
			driver.findElement(
					By.xpath("//*[@id='block-nucleus-promotion-sep-monthly-sep-monthly-float-icon']/child::*/child::*"))
					.click();
			// *[@id="page-content"]/div[1]/div[4]/div/div/div[7]
			// } catch (Exception e) {
			// TODO Auto-generated catch block
			// driver.get(data+"/sep-monthly");
			// }
			captureScreenWithFilename(getLabel(data) + "_PromoPage11th_" + Instant.now().toEpochMilli());
			WebElement element = driver.findElement(By.className("calendar-bg"));
			// WebElement tablesection =
			// driver.findElement(By.xpath("//*[@class='table-section']"));
			// WebElement tnc = driver.findElement(By.xpath("//*[@class='terms-section']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			captureScreenWithFilename(getLabel(data) + "_Calendar11th_" + Instant.now().toEpochMilli());
			// Thread.sleep(500);
			// driver.findElement(By.className("calendar-bg"));
			driver.findElement(By.xpath("(//*[@class='calendar-days']/child::*)[17]")).click();
			captureScreenWithFilename(getLabel(data) + "_PopUp11th_" + Instant.now().toEpochMilli());
			driver.close();
			// ashot();
		} catch (Exception e) {
			captureScreenWithFilename(getLabel(data) + "_PromotionError_" + Instant.now().toEpochMilli());
			e.printStackTrace();
			DriverScript.bResult = false;
		}
	}

	public static void clickPromotionFloatingIcon(String data) {
		try {
			// defaultBrowseClient(data);
			heuristicLogin(data);
			// heuristicLogin(data);
			// openBrowser("Firefox");
			// browseClient(data);h
			// try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='block-nucleus-promotion-oct-bonusback-oct-bonusback-float-icon']/child::*/child::*")));
			driver.findElement(By.xpath(
					"//*[@id='block-nucleus-promotion-oct-bonusback-oct-bonusback-float-icon']/child::*/child::*"))
					.click();
			// *[@id="page-content"]/div[1]/div[4]/div/div/div[7]
			// } catch (Exception e) {
			// TODO Auto-generated catch block
			// driver.get(data+"/sep-monthly");
			// }
			captureScreenWithFilename(getLabel(data) + "_PromoPage_" + Instant.now().toEpochMilli());
			// WebElement element = driver.findElement(By.className("calendar-bg"));
			////////////////////////////// leaderboard//////////////////////
			// WebElement tablesection =
			// driver.findElement(By.xpath("//*[@class='table-section']"));
			WebElement tnc = driver.findElement(By.xpath("//*[@class='terms-section']"));
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", tablesection);
			// captureScreenWithFilename(getLabel(data) + "_PromoOptIn_" +
			// Instant.now().toEpochMilli());
			// Thread.sleep(500);
			// driver.findElement(By.className("calendar-bg"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tnc);
			// driver.findElement(By.xpath("(//*[@class='calendar-days']/child::*)[9]")).click();
			captureScreenWithFilename(getLabel(data) + "_PromoTNC_" + Instant.now().toEpochMilli());
			WebElement optin = driver.findElement(By.cssSelector(".oct-bonusback-opt-button.postlogin.dept-user"));
			optin.findElement(By.xpath(".//child::*")).click();
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='post-opt-in']//child::p")));
			dataEngineLog(driver.findElement((By.xpath("//*[@class='post-opt-in']//child::p"))).getText());
			System.out.println("Opt In successful");
			captureScreenWithFilename(getLabel(data) + "OptInSuccess");
			driver.close();
		} catch (Exception e) {
			captureScreenWithFilename(getLabel(data) + "_PromotionError_");
			e.printStackTrace();
			DriverScript.bResult = false;
		}
	}

	public static void postLoginOptInPromotionFlow(String data) throws Exception {
		heuristicLogin(data);
		clickPromotionFloatingIcon("");
		optIn("");
		// closeBrowser("");
	}

	public static void validatePromotionPage(String data) {

	}

	public static void preloginFlowPromotion(String data) {

		try {
			defaultBrowseClient(data);
			// driver.get(data);
			captureScreen();
			clickPromotionFloatingIcon("");
			// String prizetable = driver
			// .findElement(By.xpath("//*[@class='table-section1']/child::*/child::*/child::*/child::*"))
			// .getText();
			// System.out.println(prizetable);
			try {
				driver.findElement(By.xpath("//*[@class='opt-in-box']//child::*/child::*/child::*")).click();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);",
			// driver.findElement(By.xpath("//*[@class='opt-in-box']//child::*/child::*/child::*")));
			// optin_3k_slot_showdown
			captureScreen();
			// ashot();
			// driver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DriverScript.bResult = false;
			driver.close();
		}

	}

	public static void heuristicLogin(String data) throws Exception {
		try {

			int firstperiod = data.indexOf(".");
			int secondperiod = data.indexOf(".", firstperiod + 1);
			String labelname = data.substring(firstperiod + 1, secondperiod);
			defaultBrowseClient(data);
			String pagesource = driver.getPageSource();
			int sitecodestartindex = pagesource.indexOf("site_code");
			String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
			System.out.println(sitecode);
			System.out.println(driver.findElement(By.xpath("/html/body")).getAttribute("Class"));
			Boolean not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
					.contains("not-logged-in");
			// driver.findElement(By.xpath("/html/body")).getAttribute("Class").contains("not-logged-in");
			// System.out.println(driver.findElement(By.xpath("/html/body")).getAttribute("Class").contains("not-logged-in"));
			for (int i = 1, j = 0; not_logged_in; i++) {
				// System.out.println(i);
				if (!ExcelUtils.getCellData(i, 3, "LivePlayerData").isEmpty()) {
					String[] usernames = new String[100];
					String label_checker = ExcelUtils.getCellData(i, 3, "LivePlayerData");
					String player_type_checker = ExcelUtils.getCellData(i, 4, "LivePlayerData");
					String balance_checker = (ExcelUtils.getCellData(i, 1, "LivePlayerData"));
					String total_deposits = ExcelUtils.getCellData(i, 2, "LivePlayerData");
					// System.out.print(label_checker + "|" + player_type_checker + "|" +
					// balance_checker + "|" + total_deposits);
					if (label_checker.equalsIgnoreCase(labelname) & !label_checker.isEmpty()) {
						System.out.println("came into IF for " + i);
						// System.out.println(i);
						System.out.println(ExcelUtils.getCellData(i, 0, "LivePlayerData"));
						usernames[j] = ExcelUtils.getCellData(i, 0, "LivePlayerData");
						// System.out.println("usernamesj is " + usernames[j]);
						not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
								.contains("not-logged-in");
						// System.out.println("Not Logged In is : " + not_logged_in);
						if (player_type_checker.equalsIgnoreCase("Active")) {
							// System.out.println(ExcelUtils.getCellData(i, 2, "LivePlayerData"));
							// System.out.println("came into 2nd IF for " + i);
							// System.out.println(label_checker + player_type_checker + balance_checker +
							// total_deposits);
							if (Float.parseFloat(balance_checker) >= 7) {
								System.out.println(ExcelUtils.getCellData(i, 2, "LivePlayerData"));
								Constants.heuristicloginusername = usernames[j];
								// if(not_logged_in)
								defaultLogin(usernames[j]);
								not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
										.contains("not-logged-in");
								j++;
								// System.out.println("came into 3rd IF for " + i);
							}
						}
					}
				}
			}
			// loginAPI();
			Constants.sessionid = getSessionID();
			Constants.serviceURL = getServiceURL();
			System.out.println(Constants.serviceURL);
			System.out.println(getSiteCode());
			// profileAPI();
			// walletAPI();
			// casinoAPI();
			// getProviderGames("PLAYNGO");
			System.out.println("Cash balance is :" + getWalletBalance("Cash"));
			// System.out.println("Game Bonus balance is :" + getWalletBalance("Game
			// Bonus"));
			// System.out.println("Bingo Bonus balance is :" + getWalletBalance("Bingo
			// Bonus"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void heuristicStagingLogin(String data) throws Exception {
		try {

			int hyphen = data.indexOf("-");
			int firstperiod = data.indexOf(".");
			String labelname = data.substring(hyphen + 1, firstperiod);
			defaultBrowseClient(data);
			String pagesource = driver.getPageSource();
			int sitecodestartindex = pagesource.indexOf("site_code");
			String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
			System.out.println(sitecode);
			System.out.println(driver.findElement(By.xpath("/html/body")).getAttribute("Class"));
			Boolean not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
					.contains("not-logged-in");
			// driver.findElement(By.xpath("/html/body")).getAttribute("Class").contains("not-logged-in");
			// System.out.println(driver.findElement(By.xpath("/html/body")).getAttribute("Class").contains("not-logged-in"));
			for (int i = 1, j = 0; not_logged_in; i++) {
				System.out.println(i);
				if (!ExcelUtils.getCellData(i, 3, "StagingPlayerData").isEmpty()) {
					String[] usernames = new String[100];
					String label_checker = ExcelUtils.getCellData(i, 3, "StagingPlayerData");
					String player_type_checker = ExcelUtils.getCellData(i, 4, "StagingPlayerData");
					String balance_checker = (ExcelUtils.getCellData(i, 1, "StagingPlayerData"));
					String total_deposits = ExcelUtils.getCellData(i, 2, "StagingPlayerData");
					System.out.println(label_checker + " " + player_type_checker + " " + balance_checker + " "
							+ total_deposits + " " + labelname);
					// System.out.print(label_checker + "|" + player_type_checker + "|" +
					// balance_checker + "|" + total_deposits);
					if (label_checker.equalsIgnoreCase(labelname) & !label_checker.isEmpty()) {
						System.out.println("came into IF for " + i);
						// System.exit(1);
						// System.out.println(i);
						System.out.println(ExcelUtils.getCellData(i, 0, "StagingPlayerData"));
						usernames[j] = ExcelUtils.getCellData(i, 0, "StagingPlayerData");
						// System.out.println("usernamesj is " + usernames[j]);
						not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
								.contains("not-logged-in");
						// System.out.println("Not Logged In is : " + not_logged_in);
						if (player_type_checker.equalsIgnoreCase("Active")) {
							// System.out.println(ExcelUtils.getCellData(i, 2, "LivePlayerData"));
							// System.out.println("came into 2nd IF for " + i);
							// System.out.println(label_checker + player_type_checker + balance_checker +
							// total_deposits);
							if (Float.parseFloat(balance_checker) >= 0) {
								System.out.println(ExcelUtils.getCellData(i, 2, "StagingPlayerData"));
								Constants.heuristicloginusername = usernames[j];
								// if(not_logged_in)
								defaultLogin(usernames[j]);
								not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
										.contains("not-logged-in");
								j++;
								// System.out.println("came into 3rd IF for " + i);
							}
						}
					}
				}
			}
			// loginAPI();
			Constants.sessionid = getSessionID();
			Constants.serviceURL = getServiceURL();
			System.out.println(Constants.serviceURL);
			System.out.println(getSiteCode());
			// profileAPI();
			// walletAPI();
			// casinoAPI();
			// getProviderGames("PLAYNGO");
			System.out.println("Cash balance is :" + getWalletBalance("Cash"));
			// System.out.println("Game Bonus balance is :" + getWalletBalance("Game
			// Bonus"));
			// System.out.println("Bingo Bonus balance is :" + getWalletBalance("Bingo
			// Bonus"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void noSleep() {

	}

	public static void heuristicSpecificLogin(String data) throws Exception {
		int end_of_url = data.indexOf(",");
		int end_of_playertype = data.lastIndexOf(",");
		String clienturl = data.substring(0, end_of_url);
		String playertype = data.substring(end_of_url + 1, end_of_playertype);
		Float balance_greater_than = Float.parseFloat(data.substring(end_of_playertype + 1));
		System.out.println(clienturl);
		System.out.println(playertype);
		System.out.println(balance_greater_than);
		System.out.println(getEnvLabelPassword(clienturl));
		int firstperiod = clienturl.indexOf(".");
		int secondperiod = clienturl.indexOf(".", firstperiod + 1);
		String labelname = clienturl.substring(firstperiod + 1, secondperiod);
		defaultBrowseClient(clienturl);
		String pagesource = driver.getPageSource();
		int sitecodestartindex = pagesource.indexOf("site_code");
		String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
		System.out.println(sitecode);
		System.out.println(driver.findElement(By.xpath("/html/body")).getAttribute("Class"));
		Boolean not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
				.contains("not-logged-in");
		// inputUsername("hyd123");
		// inputPassword("123456");
		// clickLogin("");
		// System.out.println("logged in");
		// not_logged_in =
		// driver.findElement(By.xpath("/html/body")).getAttribute("Class").contains("not-logged-in");
		// System.out.println(driver.findElement(By.xpath("/html/body")).getAttribute("Class").contains("not-logged-in"));
		for (int i = 0, j = 0; !ExcelUtils.getCellData(i, 9, "LivePlayerData").isEmpty() & not_logged_in; i++) {
			String[] usernames = new String[100];
			// System.out.println("i'm in IF"+labelname);
			// System.out.println(not_logged_in);
			// System.out.println(i);
			// System.out.println(ExcelUtils.getCellData(i, 9, "LivePlayerData"));
			String label_checker = ExcelUtils.getCellData(i, 9, "LivePlayerData");
			String player_type_checker = ExcelUtils.getCellData(i, 10, "LivePlayerData");
			String balance_checker = (ExcelUtils.getCellData(i, 5, "LivePlayerData"));
			String total_deposits = ExcelUtils.getCellData(i, 7, "LivePlayerData");

			if (label_checker.equalsIgnoreCase(labelname) & !label_checker.isEmpty()) {
				System.out.println("came into IF for " + i);
				System.out.println(i);
				System.out.println(ExcelUtils.getCellData(i, 2, "LivePlayerData"));
				usernames[j] = ExcelUtils.getCellData(i, 2, "LivePlayerData");
				System.out.println("usernamesj is " + usernames[j]);
				not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
						.contains("not-logged-in");
				System.out.println("Not Logged In is : " + not_logged_in);
				not_logged_in = driver.findElement(By.xpath("/html/body")).getAttribute("Class")
						.contains("not-logged-in");
				System.out.println("Not Logged In is : " + not_logged_in);
				if (playertype.equalsIgnoreCase("depositor") & player_type_checker.equalsIgnoreCase("Active")) {
					System.out.println(ExcelUtils.getCellData(i, 2, "LivePlayerData"));
					System.out.println("came into 2nd IF for " + i);
					System.out.println(label_checker + player_type_checker + balance_checker + total_deposits);
					if (Float.parseFloat(balance_checker) > balance_greater_than) {
						System.out.println(ExcelUtils.getCellData(i, 2, "LivePlayerData"));
						defaultLogin(usernames[j]);
						j++;
						System.out.println("came into 3rd IF for " + i);
					}
				}
			}
		}
	}

	public static void optIn(String data) {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@class='pre-opt-in']/child::*/child::*")));
			driver.findElement(By.xpath("//*[@class='pre-opt-in']/child::*/child::*")).click();
			captureScreen();
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@class='post-opt-in']/child::*/child::*")));
			String post_opt_in_message = driver.findElement(By.xpath("//*[@class='post-opt-in']/child::*/child::*"))
					.getText();
			Log.info(post_opt_in_message);
			if (post_opt_in_message.contains("Congratulations")) {
				System.out.println("Successfully opted-in and Congratulations message is displayed");
				captureScreen();
			} else {
				DriverScript.bResult = false;
				System.out.println("Congratulations message is not displayed");
			}
			driver.close();
		} catch (Exception e) {
			DriverScript.bResult = false;
		}
	}

	public static void checkGameAvailability(String data) throws InterruptedException {

		try {
			try {
				String pagesource = driver.getPageSource();
				int sitecodestartindex = pagesource.indexOf("site_code");
				String sitecode = pagesource.substring(sitecodestartindex + 14, sitecodestartindex + 17);
				if (!driver.getCurrentUrl().contains("slots") & !sitecode.equals("MCN")) {
					gotoSlotsLobby();
				} else if (sitecode.equals("MCN")) {
					driver.findElement(By.xpath("//*[@data-type='slots']")).click();
				}
				driver.findElement(By.xpath("//*[@id='edit-title']")).clear();
				driver.findElement(By.xpath("//*[@id='edit-title']")).sendKeys(data);
				int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
				String gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
				System.out.println(gameinternalid);
				Thread.sleep(2000);
				captureScreen();
				// new WebDriverWait(driver, 10)
				// .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-game-id="
				// + "'" + gameinternalid + "'" + "]")));
				String text = driver.findElement(By.xpath("//*[@data-game-id=" + "'" + gameinternalid + "'" + "]"))
						.getText();
				System.out.println(text);

				// WebElement we = driver.findElement(By.xpath("//*[@data-game-id=" + "'" +
				// gameinternalid + "'" + "]"));
				String source = driver.findElement(By.xpath("//img[@title='" + data + "']")).getAttribute("src");
				System.out.println(source);
				URL url = new URL(source);
				if (source.isEmpty()) {
					DriverScript.bResult = false;
				}
				// System.out.println(Constants.URL+"/api/get_games/desktop");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				System.out.println("Response code is");
				System.out.println(responsecode);
				if (responsecode != 200) {
					// DriverScript.bResult = false;

					throw new RuntimeException("HttpResponseCode: " + responsecode);
				}

				// action.build().perform();
				captureScreen();
			} catch (Exception e) { // code for templates which don't have search button like bingoanywhere

				// List<WebElement> deps = driver
				// .findElements(By.xpath("//*[@class='game-details
				// slots']/child::*/child::*"));
				// // List<WebElement> currencykey =
				// // driver.findElements(By.className("currency-key"));
				// System.out.println(deps.size());
				//
				// for (int i = 1; i < 10; i++)
				//
				// {
				// String[] gamenames = new String[1500];
				// gamenames[i] = driver.findElement(By.xpath(
				// "(//*[@class='game-details
				// slots']/child::*/child::*/child::*/child::*/child::*/child::*)["
				// + i + "]"))
				// .getAttribute("onclick");
				//
				// // System.out.println("printing what's in the getText\n\n");
				// System.out.println(gamenames[i]);
				// // System.out.println("/nthat's it.");
				// //// *[@id="combo-coupons"]/li[13]/div/span[1]
				// // if (att.contains("CB")) {
				// // WebElement bb = driver.findElement(By.className("coupon-code"));
				// // driver.findElement(By.className("coupon-code")).getAttribute("");
				// // String xpath = generateXPATH(bb,"");
				// // System.out.print(xpath);
				// // driver.findElement(By.xpath(xpath)).click();
				// // String attval = deps.get(i).getAttribute("value");
				//
				// // System.out.printf(attval);
				// // if (attval.equals(20)) {
				// // driver.findElement(By.name("accounts[" + Constants.drupalID +
				// "]")).click();
				// // break;
				// // }
				// // }
				// }
				//
				// int gameindex = Arrays.asList(Constants.gamesnames).indexOf(data);
				// String gameinternalid = Arrays.asList(Constants.gamesid).get(gameindex);
				// System.out.println(gameinternalid);
				// String text = driver.findElement(By.xpath("//*[@data-game-id=" + "'" +
				// gameinternalid + "'" + "]"))
				// .getText();
				// System.out.println(text);
				e.printStackTrace();
				System.out.println("NoSuchElementException");
				DriverScript.bResult = false;
			}

		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			System.out.println("NoSuchElementException");
			DriverScript.bResult = false;
		}

	}

	public static void liveHelp(String data) {
		try {

			defaultBrowseClient("https://www.comfybingo.com/");

			System.out.println(driver.getWindowHandles());
			driver.findElement(By.id("_lpChatBtn")).click();
			driver.findElement(By.className("live_help_js")).click();
			Actions actions = new Actions(driver);
			WebElement lpChatBtn = driver.findElement(By.xpath("//*[@id='_lpChatBtn']"));
			WebElement lpChatBtnc = driver.findElement(By.xpath("//*[@class='live_help_js']"));
			Thread.sleep(4000);
			actions.moveToElement(lpChatBtn).perform();
			// actions.
			actions.click(lpChatBtn);
			// actions.click(lpChatBtnc).build().perform();
			Log.info("Clicked Live Help button on " + driver.getCurrentUrl() + " at" + Instant.now());
			System.out.println(driver.getWindowHandles());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DriverScript.bResult = false;
		}
	}

	public static void closeBrowser(String data) {
		try {
			driver.quit();
		} catch (Exception e) {

			DriverScript.bResult = false;
		}
	}

	public static void validateSiteDownMessage(String data) {

		// Constants.handle1 = driver.getWindowHandle();
		// System.out.printf("\n\n" + Constants.handle1);

		try {
			// Constants.handle1 = driver.getWindowHandle();
			// Constants.URL = data;
			ArrayList<String> multipleTabs = new ArrayList<String>(driver.getWindowHandles());
			do {
				System.out.println(multipleTabs.size());
				System.out.println("opening from Do While loop");
				((JavascriptExecutor) driver).executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(multipleTabs.size()));
				driver.get(data);
				// System.out.printf("\n\n" + Constants.handle1);
				ArrayList<String> multipleTabs1 = new ArrayList<String>(driver.getWindowHandles());
				System.out.println("multiple tabs is");
				System.out.println(multipleTabs);
				captureScreen();
				String pagetitle = driver.getTitle();
				String pagesource = driver.getPageSource();
				System.out.println(pagetitle);
				captureScreen();
				if (pagetitle.contains("Bingo") == true && pagesource.contains(Constants.sitedownmessage1) == true) {
					System.out.println("Site Down message is present");
					break;
					// driver.close();
				} else {
					DriverScript.bResult = false;
					break;
				}
			} while (multipleTabs.size() == 3);
		} catch (Exception e) {

			try {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\vkolla\\eclipse-workspace\\BrowserDrivers\\chromedriver.exe");
				// System.out.print(DriverScript.bResult);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("incognito");
				options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
				Constants.URL = data;
				System.out.println("opening from Catch");
				driver.get(data);
				Constants.handle1 = driver.getWindowHandle();
				System.out.printf("\n\n" + Constants.handle1);
				ArrayList<String> multipleTabs = new ArrayList<String>(driver.getWindowHandles());
				System.out.println("multiple tabs is");
				System.out.println(multipleTabs);
				captureScreen();
				String pagetitle = driver.getTitle();
				String pagesource = driver.getPageSource();
				System.out.println(pagetitle);
				captureScreen();
				if (pagetitle.contains("Bingo") == true && pagesource.contains(Constants.sitedownmessage1) == true) {
					System.out.println("Site Down message is present");
					// driver.close();
				} else {
					DriverScript.bResult = false;
				}
			} catch (Exception e1) {

				DriverScript.bResult = false;
			}
		}

	}

	public static void thinkBingoLogin(String data) {
		String[][] bingoroomdata = new String[20][7];
		defaultBrowseClient(data);
		try {
			driver.findElement(By.xpath("(//*[@class='navbar-wrapper-right']//child::span)[1]")).click();
			driver.findElement(By.xpath("//*[@name='username']")).sendKeys("gbp66");
			driver.findElement(By.xpath("//*[@name='password']")).sendKeys("123123");
			driver.findElement(By.xpath("(//form[@method='POST']/child::*/child::*/child::div)[2]/child::*")).click();
		} catch (Exception e1) {
			System.out.println("Log in failed");
			e1.printStackTrace();
		}
		try {
			new WebDriverWait(driver, 25)
					.until(ExpectedConditions.visibilityOfElementLocated(By.className("dlg-responsive-content")));
			driver.findElement(By.xpath("(//*[@class='button-bottom-margin']//child::*)[2]")).click();
			System.out.println("Identity verification pop-up vanished");
		} catch (Exception e) {
			System.out.println("Getting rid of that pop-up failed");
			e.printStackTrace();
		}

		try {
			new WebDriverWait(driver, 25)
					.until(ExpectedConditions.visibilityOfElementLocated(By.className("bingorooms-data")));
			System.out.println("Found bingo rooms data");
			System.out.println("\nPrinting room data");
			new WebDriverWait(driver, 25).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//*[@class='bingorooms-data']/child::*)[1]")));
			List<WebElement> roomlist = driver.findElements(By.xpath("(//*[@class='bingorooms-data']/child::*)"));
			for (int i = 1; i <= roomlist.size(); i++) {
				// System.out.println("Class name for " + i + " is - "
				// + driver.findElement(By.xpath("(//*[@class='bingorooms-data']/child::*)[" + i
				// + "]"))
				// .getAttribute("class"));
				// System.out.println("Data inside bingo room " + i + " is - " +
				// driver.findElement( // this gets the ball
				// type of the bingo
				// room
				// By.xpath("(//*[@class='bingorooms-data']/child::*)[" + i +
				// "]/child::*/child::*/child::span"))
				// .getText());
				// List<WebElement> roomdata = driver.findElements(
				// By.xpath("(//*[@class='bingorooms-data']/child::*)[" + i +
				// "]/child::*/child::*/child::span"));
				// for (WebElement rooms : roomdata) {
				// System.out.println("Data is \t" + rooms.getText());
				// }
				List<WebElement> roomdata2 = driver.findElements(
						By.xpath("(//*[@class='bingorooms-data']/child::*)[" + i + "]/child::*/child::div"));
				int i1 = 0, j = 0;
				System.out.print("\nRoom " + i + " data is\t||");
				for (WebElement rooms2 : roomdata2) {
					System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
					if (rooms2.getText().startsWith("75")) {
						// System.out.println("First IF true");
						driver.findElement(By
								.xpath("((//*[@class='bingorooms-data']/child::*)[" + i + "]/child::*/child::div)[6]"))
								.click();
						// if(rooms2.getText().equals("PLAY NOW")) {
						// System.out.println("Second IF true");
						// rooms2.click();
						// }
					}
				}
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("Bingo room launch failed");
			e.printStackTrace();
		}

		for (String BingoGame : driver.getWindowHandles()) {
			if (!BingoGame.equals(Constants.parentwindow)) {
				driver.switchTo().window(BingoGame);
				driver.manage().window().maximize();
			}
		}

		try {
			// driver.switchTo().frame(0);
			driver.findElement(By.xpath("//*[@id='game-container-1']/child::*/child::iframe")).click();
			System.out.println("\nSwitched to parent iFrame\n\n");
			// driver.switchTo().frame("bingo_68");
			driver.findElement(By.cssSelector("#bingo_68")).click();
			// driver.findElement(By.xpath("//*[@id='bingo_68']")).click();
			// *[@id="bingo_68"]
			System.out.println("\nSwitched to bingo_68 iFrame\n\nWaiting for BingoGame canvas");
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='BingoGame']")));
			WebElement bingogame = driver.findElement(By.xpath("//*[@id='BingoGame']"));
			Actions action = new Actions(driver);
			action.moveToElement(bingogame);
			System.out.println("Move successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static float foxyRegistration(String data) throws Exception {
		float timetaken = 0;
		try {
			System.out.println(
					"Beginning execution in " + Constants.cashcadeenv + " environment on " + Constants.cclabel);
			dataEngineLog("Beginning execution in " + Constants.cashcadeenv + " environment");
			if (driver.getWindowHandles().isEmpty())
				defaultBrowseClient(Constants.cchome);
			Actions actions = new Actions(driver);
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOfElementLocated((By.cssSelector(".menu-item-link.header-btn.btn.btn-primary"))));
			// dataEngineLog("Waiting for register button complete at " + Instant.now());
			driver.findElement(By.cssSelector(".menu-item-link.header-btn.btn.btn-primary")).click();
			Instant registerbuttonhit = Instant.now();
			// dataEngineLog("Clicking register button complete at " + Instant.now());
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id='register']"))));
			Instant registeridloadcomplete = Instant.now();
			dataEngineLog("Registration section loading time is "
					+ (float) ((float) (Duration.between(registerbuttonhit, registeridloadcomplete).toMillis()))
							/ (float) 1000
					+ " seconds");
			// driver.findElement(By.xpath("//*[@id='register']")).click();
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated((By.name("addresscountrycode"))));
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated((By.name("currencycode"))));
			WebElement country = driver.findElement(By.name("addresscountrycode"));
			WebElement currency = driver.findElement(By.name("currencycode"));
			Select scountry = new Select(country);
			Select scurrency = new Select(currency);
			try {
				// scountry.selectByVisibleText("China");
				scountry.selectByVisibleText("United Kingdom");
				// scurrency.selectByVisibleText("EUR");
				scurrency.selectByVisibleText("GBP");
			} catch (StaleElementReferenceException e) {
				// ExpectedCondition expectation = new ExpectedCondition() {
				// public Boolean apply(WebDriver driver) {
				// return ((JavascriptExecutor) driver).executeScript("return
				// document.readyState")
				// .equals("complete");
				// }
				//
				// @Override
				// public Object apply(Object arg0) {
				// return null;
				// }
				// };
				// Wait<WebDriver> wait = new WebDriverWait(driver, 10);
				// try {
				// wait.until(expectation);
				// } catch (Throwable error) {
				// }

			}
			driver.findElement(By.name("emailaddress")).sendKeys(Constants.ccuser + "@discard.email");
			driver.findElement(By.xpath("//*[@id='register']")).click();
			driver.findElement(By.name("username")).sendKeys(Constants.ccuser);
			driver.findElement(By.xpath("//*[@id='register']")).click();
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("(//*[@name='password'])[2]"))));
			driver.findElement(By.xpath("(//*[@name='password'])[2]")).sendKeys("Foxy@12345");
			driver.findElement(By.xpath("//*[@id='register']")).click();
			WebElement continue1 = driver.findElement(By.xpath("(//*[@id='register']//child::button)[1]"));
			captureScreenWithFilename("Foxy Registration step 1");
			driver.findElement(By.xpath("//*[@id='register']")).click();
			actions.moveToElement(continue1).build().perform();
			actions.click().perform();
			// WebElement
			// continue3=driver.findElement(By.xpath("(//*[@id='register']//child::button)[3]"));
			// driver.findElement(By.xpath("(//*[@id='register']//child::button)[1]")).click();
			// driver.findElement(By.xpath("(//*[@id='register']//child::button)[1]")).click();
			System.out.println("Clicked continue, moving on to step 2...");
			try {
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated((By.name("firstname"))));
			} catch (Exception e) {
				WebElement continue11 = driver.findElement(By.xpath("(//*[@id='register']//child::button)[1]"));
				driver.findElement(By.xpath("(//*[@id='register']//child::button)[1]")).click();
				actions.moveToElement(continue11).perform();
				actions.click().perform();
				try {
					new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated((By.name("firstname"))));
				} catch (Exception e1) {
					WebElement continue111 = driver.findElement(By.xpath("(//*[@id='register']//child::button)[1]"));
					driver.findElement(By.xpath("//*[@id=\"register\"]/div[1]/div/button")).click();
					actions.moveToElement(continue111).perform();
					actions.click().perform();
				}
			}
			driver.findElement(By.name("firstname")).sendKeys("Autofoxy");
			driver.findElement(By.name("lastname")).sendKeys("testcozy");
			WebElement day = driver.findElement(By.xpath("//*[@name='day']"));
			WebElement month = driver.findElement(By.xpath("//*[@name='month']"));
			WebElement year = driver.findElement(By.xpath("//*[@name='year']"));
			Select sday = new Select(day);
			Select smonth = new Select(month);
			sday.selectByIndex(2);
			smonth.selectByIndex(2);
			year.sendKeys("1990");
			WebElement securityquestion = driver.findElement(By.xpath("//*[@name='securityquestion']"));
			WebElement securityanswer = driver.findElement(By.xpath("//*[@name='securityanswer']"));
			Select ssecurityquestion = new Select(securityquestion);
			ssecurityquestion.selectByIndex(4);
			securityanswer.sendKeys("The Man From Earth");
			WebElement continue2 = driver.findElement(By.xpath("(//*[@id='register']//child::button)[2]"));
			captureScreenWithFilename("Foxy Registration step 2");
			driver.findElement(By.xpath("//*[@id='register']")).click();
			actions.moveToElement(continue2).build().perform();
			actions.click().perform();
			try {
				driver.findElement(By.name("addressline1")).sendKeys("601-701 Europort, Gibraltar");
				new WebDriverWait(driver, 5)
						.until(ExpectedConditions.visibilityOfElementLocated((By.name("addressline1"))));
			} catch (Exception e) {
				WebElement continue22 = driver.findElement(By.xpath("(//*[@id='register']//child::button)[2]"));
				// driver.findElement(By.xpath("//*[@id=\"register\"]/div[2]/div/button")).click();
				actions.moveToElement(continue22).perform();
				actions.click().perform();
			}
			try {
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
						(By.cssSelector(".form-element.promotions.ng-pristine.ng-invalid.ng-touched"))));
				WebElement entermanually = driver
						.findElement(By.cssSelector(".form-element.promotions.ng-pristine.ng-invalid.ng-touched"));
				entermanually.findElement(By.xpath(".//child::a")).click();
				System.out.println("Just want to complete registraion");

			} catch (Exception e) {
				e.printStackTrace();
			}
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOfElementLocated((By.name("addressline1"))));
			driver.findElement(By.name("addressline1")).sendKeys("601-701 Europort, Gibraltar");
			driver.findElement(By.name("addresscity")).sendKeys("Gibraltar");
			driver.findElement(By.name("addresszip")).sendKeys("GX11 1AA");
			driver.findElement(By.name("mobilenumber")).sendKeys("7" + Constants.FoxyMobile);
			// driver.findElement(By.name("")).sendKeys("");
			driver.findElement(By.xpath("//*[@for='No']")).click();
			driver.findElement(By.id("submit")).click();
			captureScreenWithFilename("Foxy Registration Submit");
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@name='nickname']"))));
			driver.findElement(By.xpath("//*[@name='nickname']")).sendKeys(Constants.ccuser + "nick");
			captureScreenWithFilename("Foxy Registration Nickname2");
			driver.findElement(By.cssSelector(".btn.btn-primary.login.w-100")).click();
			captureScreenWithFilename("Foxy Registration Nickname2");
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@class='cms-container']"))));
			WebElement finalcontinue = driver
					.findElement(By.xpath("//*[@class='dlg-responsive-content']//child::button"));
			actions.moveToElement(finalcontinue).perform();
			actions.click(finalcontinue).perform();
			Instant registrationcomplete = Instant.now();
			dataEngineLog("Registration process completed in "
					+ (float) ((float) (Duration.between(registeridloadcomplete, registrationcomplete).toMillis()))
							/ (float) 1000
					+ " seconds. Username = " + Constants.ccuser);
			Constants.ccnewregistration = true;
			timetaken = (float) ((float) (Duration.between(registeridloadcomplete, registrationcomplete).toMillis()))
					/ (float) 1000;
			driver.close();
			// if (driver.getWindowHandles().isEmpty())
			// defaultBrowseClient(Constants.cchome);
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
			DriverScript.bResult = false;
		}
		return timetaken;
	}

	public static void foxyRegression(String data) throws Exception {
		foxyLogin(data);
		checkAllTabsNavigation();
		markBingoRoomAsFavourite("AUG TEST");
		markBingoRoomAsFavourite("AUGSTREAM");
		readBingoRoomData();
		gotoBingoTab("Rooms");
		gotoBingoTab("My Favourites");
		gotoBingoTab("Schedule");
		gotoBingoTab("Rooms");
		gotoBingoTab("My Favourites");
		gotoBingoTab("Schedule");
		gotoBingoTab("Rooms");
		gotoBingoTab("My Favourites");
		gotoBingoTab("Schedule");
		gotoBingoTab("Rooms");
		gotoBingoTab("My Favourites");
		gotoBingoTab("Schedule");
		gotoBingoTab("Rooms");
		filterBingoRoomsBy("75 Ball");
		checkAllTabsNavigation();
		foxyCasinoGlobalSearch("ted");
		foxyCasinoGlobalSearch("egg");
		foxyCasinoGlobalSearch("cash");
		foxyCasinoGlobalSearch("sweet");
		foxyCasinoGlobalSearch("going");
		checkAllHyperlinks("");
		LaunchFoxyBingoGame("Big Ben");

	}

	public static float foxyLogin(String data) throws Exception {

		Instant logindone = null, clickedlogin;
		float timetaken = 0;
		try {
			// System.out.println("Beginning execution in " + Constants.foxyenv + "
			// environment");
			// dataEngineLog("Beginning execution in " + Constants.foxyenv + "
			// environment");
			if (iopenattheclose == false)
				defaultBrowseClient(Constants.cchome);

			new WebDriverWait(driver, 15).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//*[@class='navbar-wrapper-right']//child::a)[1]")));
			driver.findElement(By.xpath("(//*[@class='navbar-wrapper-right']//child::a)[1]")).click();
			driver.findElement(By.xpath("//*[@name='username']")).clear();
			if (Constants.ccnewregistration == false && Constants.cashcadeenv.equals("QA2"))
				Constants.ccuser = "qacad7";
			if (Constants.ccnewregistration == false && Constants.cashcadeenv.equals("QA1"))
				Constants.ccuser = "testcad7";
			if (Constants.ccnewregistration == false && Constants.cashcadeenv.equals("E7"))
				Constants.ccuser = "e7" + Constants.cclabel.replace(" Bingo", "").toLowerCase();
			if (Constants.ccnewregistration == false && Constants.cashcadeenv.equalsIgnoreCase("Beta"))
				Constants.ccuser = "FBTESTGVCFOXY3".toLowerCase();
			if (Constants.ccnewregistration == false && Constants.cashcadeenv.equalsIgnoreCase("Live")
					&& Constants.ccbrand.equalsIgnoreCase("foxy"))
				Constants.ccuser = "FBTESTGVCEU3".toLowerCase();
			if (Constants.ccnewregistration == false && Constants.cashcadeenv.equalsIgnoreCase("Live")
					&& Constants.ccbrand.equalsIgnoreCase("cheeky"))
				Constants.ccuser = "CHTESTGVCEU3".toLowerCase();
			driver.findElement(By.xpath("//*[@name='username']")).sendKeys(Constants.ccuser);
			driver.findElement(By.xpath("//*[@name='password']")).clear();
			if (Constants.ccnewregistration == true)
				driver.findElement(By.xpath("//*[@name='password']")).sendKeys("test@123");
			else
				driver.findElement(By.xpath("//*[@name='password']")).sendKeys("test@123");
			driver.findElement(By.xpath("(//form[@method='POST']/child::*/child::*/child::div)[2]/child::*")).click();
			clickedlogin = Instant.now();
			// wait for successful login and capture screenshot
			for (int i = 0; i < 100; i++) {
				System.out.println(i);
				// Thread.sleep(500);
				try {
					// new
					// WebDriverWait(driver,3).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".theme-error-i.ng-star-inserted")));
					WebElement errorguy = driver.findElement(By.cssSelector(".theme-error-i.ng-star-inserted"));
					System.out.println(errorguy.findElement(By.xpath(".//child::p")).getText());
					break;
					// driver.findElement(By.xpath("//*[@name='password']"))
					// .sendKeys(Constants.cclabel.replace(" Bingo", "") + "@123");
					// driver.findElement(By.xpath("(//form[@method='POST']/child::*/child::*/child::div)[2]/child::*"))
					// .click();
				} catch (Exception e) {
					Thread.sleep(500);
				}
				if (isFoxyLoggedIn()) {
					captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_LoginSuccessful");
					System.out.println("Log in successful, captured screenshot");
					logindone = Instant.now();
					break;
				}
			}
			timetaken = timediff(clickedlogin, logindone);
			System.out.println("Time taken for login : " + timediff(clickedlogin, logindone));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");

		}
		return timetaken;
	}

	public static boolean isFoxyLoggedIn() {
		if (driver
				.findElement(
						By.xpath("//*[@data-label='" + Constants.cclabel.replace(" ", "").toLowerCase() + ".com']"))
				.getAttribute("class").contains("unauthenticated"))
			return false;
		else
			return true;
	}

	public static void readBingoRoomData() throws InterruptedException {

		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.attributeContains(
					By.xpath("//*[@data-label='" + Constants.cclabel.replace(" ", "").toLowerCase() + ".com']"),
					"class", "authenticated"));
			System.out.println("Player logged in, found bingo rooms data");
			System.out.println("\nPrinting room data");
			new WebDriverWait(driver, 25).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])[1]")));
			// driver.findElement(By.xpath("((//*[@class='bingogames-group'])[1]//child::div)[6]//child::i"))
			// .click();
			// System.out.println("clicked i");
			// Thread.sleep(5000);
			// driver.findElement(By.xpath("((//*[@class='bingogames-group'])[1]//child::div)[6]//child::bg-favourite"))
			// .click();
			// System.out.println("clicked bg-favourite");
			List<WebElement> roomlist = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
			for (int i = 1; i <= roomlist.size(); i++) {

				List<WebElement> roomdata2 = driver
						.findElements(By.xpath("(//*[@class='bingogames-group'])[" + i + "]//child::div"));
				int i1 = 0, j = 0;
				System.out.print("\nRoom " + i + " data is\t||");
				for (WebElement rooms2 : roomdata2) {
					System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
				}
				System.out.println("");
			}
		}

		catch (StaleElementReferenceException e1) {
			System.out.println("Caugh stale elements. Restarting reading bingo room data");
			new WebDriverWait(driver, 25).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])[1]")));
			// driver.findElement(By.xpath("((//*[@class='bingogames-group'])[1]//child::div)[6]//child::i"))
			// .click();
			// System.out.println("clicked i");
			// Thread.sleep(5000);
			// driver.findElement(By.xpath("((//*[@class='bingogames-group'])[1]//child::div)[6]//child::bg-favourite"))
			// .click();
			// System.out.println("clicked bg-favourite");
			List<WebElement> roomlist = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
			for (int i = 1; i <= roomlist.size(); i++) {

				List<WebElement> roomdata2 = driver
						.findElements(By.xpath("(//*[@class='bingogames-group'])[" + i + "]//child::div"));
				int i1 = 0, j = 0;
				System.out.print("\nRoom " + i + " data is\t||");
				for (WebElement rooms2 : roomdata2) {
					System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
				}
				System.out.println("");
			}
		}

		catch (Exception e) {
			System.out.println("Bingo room data read failed");
			e.printStackTrace();
		}
	}

	public static float LaunchFoxyBingoGame(String data) throws Exception {
		boolean launched = false;
		float timetaken = 0;
		Instant bingolaunch = null, bingoloadcomplete;
		if (!driver.getCurrentUrl().contains("bingo.foxybingo")) {
			driver.get(Constants.cchome);
			gotoBingoTab("Schedule");
		}
		try {
			try {
				new WebDriverWait(driver, 20).until(ExpectedConditions
						.attributeContains(By.xpath("//*[@data-label='foxybingo.com']"), "class", "authenticated"));
			} catch (Exception e) {
				dataEngineLog("Player is not logged in");
			}
			System.out.println("Found bingo rooms data");
			System.out.println("\nPrinting room data");
			new WebDriverWait(driver, 25).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])[1]")));
			List<WebElement> roomlist = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
			for (int i = 1; i <= roomlist.size(); i++) {
				List<WebElement> roomdata2 = driver
						.findElements(By.xpath("(//*[@class='bingogames-group'])[" + i + "]//child::div"));
				int i1 = 0, j = 0;
				System.out.print("\nRoom " + i + " data is\t||");
				for (WebElement rooms2 : roomdata2) {
					System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
					if (rooms2.getText().replace("\n", " ").trim().toUpperCase().contains(data.toUpperCase())) {
						// driver.findElement(By.xpath("((//*[@class='bingogames-group'])[" + i
						// +"]//child::div)[6]//child::i"))
						// .click(); //favourite icon
						bingolaunch = Instant.now();
						driver.findElement(
								By.xpath("((//*[@class='bingogames-group'])[" + i + "]//child::div)[8]//child::*"))
								.click(); // favourite iconsssss
						launched = true;
						break;
					}
				}
				if (launched == true) {
					System.out.println("Done launching");
					break;
				}
			}

		}

		catch (StaleElementReferenceException e1) {
			try {
				new WebDriverWait(driver, 20).until(ExpectedConditions
						.attributeContains(By.xpath("//*[@data-label='foxybingo.com']"), "class", "authenticated"));
			} catch (Exception e) {
				dataEngineLog("Player is not logged in");
			}
			System.out.println("Found bingo rooms data");
			System.out.println("\nPrinting room data");
			new WebDriverWait(driver, 25).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])[1]")));
			List<WebElement> roomlist = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
			for (int i = 1; i <= roomlist.size(); i++) {
				List<WebElement> roomdata2 = driver
						.findElements(By.xpath("(//*[@class='bingogames-group'])[" + i + "]//child::div"));
				int i1 = 0, j = 0;
				System.out.print("\nRoom " + i + " data is\t||");
				for (WebElement rooms2 : roomdata2) {
					System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
					if (rooms2.getText().replace("\n", " ").trim().toUpperCase().contains(data.toUpperCase())) {
						// driver.findElement(By.xpath("((//*[@class='bingogames-group'])[" + i
						// +"]//child::div)[6]//child::i"))
						// .click(); //favourite icon
						bingolaunch = Instant.now();
						driver.findElement(
								By.xpath("((//*[@class='bingogames-group'])[" + i + "]//child::div)[8]//child::*"))
								.click(); // favourite iconsssss
						launched = true;
						break;
					}
				}
				if (launched == true) {
					System.out.println("Done launching");
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Bingo room launch failed");
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
			DriverScript.bResult = false;
			e.printStackTrace();
		}

		try {
			Screen s = new Screen();
			Pattern tutorial = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_bingotutorial.PNG");
			Pattern tutorial1 = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_bingotutorial1.PNG");
			Pattern tutorialclose = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_tutorialclose.PNG");
			Pattern prebuy_buytickets = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_prebuy_buytickets.PNG");
			Pattern settingsection = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_settingsection.PNG");
			Pattern prebuy = new Pattern(
					"C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\" + Constants.ccbrand + "_prebuy.PNG");
			Pattern prebuydata = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_prebuydataheaders.PNG");
			Pattern next = new Pattern(
					"C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\" + Constants.ccbrand + "_next.PNG");
			Pattern tutorialfinalclose = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_tutorial_finalclose.PNG");
			Pattern tutorialfinalpop = new Pattern("C:\\Users\\vkolla\\eclipse-workspace\\FoxyBingoScreenshots\\"
					+ Constants.ccbrand + "_tutorial_finalclose.PNG");
			s.wait(tutorial, 45);
			System.out.println("Tutorial is here");
			bingoloadcomplete = Instant.now();

			for (int j = 0; j < 10; j++) {
				try {
					if (j > 7 && s.find(tutorialfinalpop) != null) {
						s.click(tutorialfinalclose);
						break;
					}

				} catch (Exception e) {
				}
				s.wait(next, 5);
				s.click(next);
				System.out.println("Clicked on next " + j + " time(s)");
			}
			// Match tut=s.find(tutorial);
			// Region tutreg = new Region(s.find(tutorial).getX(), s.find(tutorial).getY(),
			// s.find(tutorial).getW(),
			// s.find(tutorial).getH());
			// tutreg.find(next);
			// tutreg.click(next);
			// s.wait(tutorial, 45);
			// System.out.println("Tutorial 1 is here");
			// Region tutreg1 = new Region(s.find(tutorial1).getX(),
			// s.find(tutorial1).getY(), s.find(tutorial1).getW(),
			// s.find(tutorial1).getH());
			// tutreg1.find(next);
			// tutreg1.click(next);
			// s.click(tutorialclose);
			s.wait(prebuy_buytickets, 45);
			timetaken = timediff(bingolaunch, bingoloadcomplete);
			System.out.println(data.toUpperCase() + " Bingo Game load time " + timetaken + " in float seconds");
			s.click(prebuy);
			Instant prebuyclick = Instant.now();
			s.wait(prebuydata, 45);
			Instant prebuypop = Instant.now();
			timetaken = timediff(prebuyclick, prebuypop);
			System.out.println(data.toUpperCase() + " Pre-Buy pop up load time " + timetaken + " in float seconds");
			dataEngineLog(data.toUpperCase() + " game load time " + timetaken + " seconds");
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_BingoLoadComplete");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timetaken;
	}

	public static void lobbyPreBuy(String data) throws Exception {

		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.attributeContains(By.xpath("//*[@data-label='foxybingo.com']"), "class", "authenticated"));
		} catch (Exception e) {
			dataEngineLog("Player is not logged in");
		}
		System.out.println("Found bingo rooms data");
		System.out.println("\nPrinting room data");
		new WebDriverWait(driver, 25)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])[1]")));
		List<WebElement> roomlist = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
		for (int i = 1; i <= roomlist.size(); i++) {
			List<WebElement> roomdata2 = driver
					.findElements(By.xpath("(//*[@class='bingogames-group'])[" + i + "]//child::div"));
			List<WebElement> prebuybuttons = driver
					.findElements(By.cssSelector(".btn.btn-outline-secondary.btn-block.prebuy-btn"));
			int i1 = 0, j = 0;
			System.out.print("\nRoom " + i + " data is\t||");
			for (WebElement rooms2 : roomdata2) {
				System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
				if (rooms2.getText().replace("\n", " ").trim().toUpperCase().contains(data.toUpperCase())) {
					prebuybuttons.get(i).click();
					// driver.findElement(
					// By.xpath("((//*[@class='bingogames-group'])[" + i +
					// "]//child::div)[7]")).click(); // favourite
					// iconsssss
					break;
				}
			}
		}
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='tickets-component']")));
		List<WebElement> prebuydd = driver.findElements(By.xpath("//*[@class='dd-display-item']//child::*"));
		int i = 1;
		for (WebElement prebuydds : prebuydd) {
			System.out.println(i + " : " + prebuydds.getText());
			i++;
		}

	}

	public static void filterBingoRoomsBy(String data) throws InterruptedException {
		String filtername = null;
		try {
			driver.findElement(By.xpath("//*[@class='Filter-Sorting-Menu']")).click();
			Thread.sleep(3000);
			List<WebElement> filterbylist = driver.findElements(By.xpath("(//*[@class='filter-by-list']//child::*)"));
			for (int i = 1; i <= filterbylist.size(); i++) {
				filtername = driver.findElement(By.xpath("(//*[@class='filter-by-list']//child::*)[" + i + "]"))
						.getText();
				System.out.println(filtername);
				if (filtername.toUpperCase().equals(data.toUpperCase()))
					driver.findElement(By.xpath("(//*[@class='filter-by-list']//child::*)[" + i + "]")).click();
			}
			driver.findElement(By.xpath("//*[@class='button-result']")).click();
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
		}
	}

	public static float gotoBingoTab(String data) throws Exception {
		float timetaken = 0;
		WebElement mainnav;
		if (!driver.getCurrentUrl().contains(Constants.cchome))
			driver.get(Constants.cchome);
		try {
			try {
				new WebDriverWait(driver, 15).until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector(".main-navigation.ng-star-inserted")));
				mainnav = driver.findElement(By.cssSelector(".main-navigation.ng-star-inserted"));
			} catch (Exception e) {
				new WebDriverWait(driver, 15)
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".main-navigation")));
				mainnav = driver.findElement(By.cssSelector(".main-navigation"));
			}

			List<WebElement> bingomainnav = mainnav.findElements(By.xpath(".//child::*/child::li/child::*"));
			for (WebElement bmn : bingomainnav) {
				System.out.println(bmn.getText());

				if (data.equalsIgnoreCase(bmn.getText()) && data.equalsIgnoreCase("SCHEDULE")) {
					Instant beforeclick = Instant.now();
					// mainnav.findElement(By.xpath(".//child::*/child::li/child::*)[1]")).click();
					bmn.click();
					List<WebElement> allbingorooms = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
					if (allbingorooms.size() > 0)
						new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("(//*[@class='bingogames-group'])[" + allbingorooms.size() + "]")));
					else
						new WebDriverWait(driver, 15).until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])")));
					timetaken = (float) ((float) (Duration.between(beforeclick, Instant.now()).toMillis())
							/ (float) 1000);
					System.out.println(data.toUpperCase() + " tab load time " + timetaken + " in float seconds");
					dataEngineLog(data.toUpperCase() + " tab load time " + timetaken + " in float seconds");
				}
				if (data.equalsIgnoreCase(bmn.getText()) && data.equalsIgnoreCase("ROOMS")) {
					Instant beforeclick = Instant.now();
					// driver.findElement(By.xpath("(//*[@class='main-navigation']//child::*/child::li/child::*)[2]"))
					// .click();
					bmn.click();
					List<WebElement> allbingocards = driver.findElements(By.xpath("//*[@class='card']"));
					System.out.println("Number of cards are " + allbingocards.size());
					if (allbingocards.size() > 0)
						new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("(//*[@class='card'])[" + allbingocards.size() + "]")));
					else
						new WebDriverWait(driver, 15)
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='card'])")));
					timetaken = (float) ((float) (Duration.between(beforeclick, Instant.now()).toMillis())
							/ (float) 1000);
					System.out.println(data.toUpperCase() + " tab load time " + timetaken + " in float seconds");
					dataEngineLog(data.toUpperCase() + " tab load time " + timetaken + " in float seconds");
				}
				if (data.equalsIgnoreCase(bmn.getText()) && data.equalsIgnoreCase("My Favourites")) {
					Instant beforeclick = Instant.now();
					// driver.findElement(By.xpath("(//*[@class='main-navigation']//child::*/child::li/child::*)[3]"))
					// .click();
					bmn.click();
					new WebDriverWait(driver, 15).until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='bingogames-group']")));
					timetaken = (float) ((float) (Duration.between(beforeclick, Instant.now()).toMillis())
							/ (float) 1000);
					System.out.println(data.toUpperCase() + " tab load time " + timetaken + " in float seconds");
					dataEngineLog(data.toUpperCase() + " tab load time " + timetaken + " in float seconds");
				}
			}
		} catch (Exception e) {
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
			e.printStackTrace();
		}
		return timetaken;
	}

	public static float foxyCasinoGlobalSearch(String data) {
		Instant sentkeys, searchresultsappeared;
		float timetaken = 0;
		try {
			if (!driver.getCurrentUrl().equals(Constants.cccasino))
				driver.get(Constants.cccasino);

			driver.findElement(By.xpath("//*[@id='desktop-searchnav']//child::input")).clear();
			driver.findElement(By.xpath("//*[@id='desktop-searchnav']//child::input")).sendKeys(data);
			// try {
			// new WebDriverWait(driver, 10).until(ExpectedConditions
			// .visibilityOfElementLocated(By.cssSelector(".search-input.ng-untouched.ng-pristine.ng-valid")));
			// } catch (Exception e1) {
			// // TODO Auto-generated catch block
			// new WebDriverWait(driver, 10).until(ExpectedConditions
			// .visibilityOfElementLocated(By.cssSelector(".search-input.ng-valid.ng-dirty.ng-touched")));
			// }
			// try {
			// driver.findElement(By.cssSelector(".search-input.ng-untouched.ng-pristine.ng-valid")).clear();
			// driver.findElement(By.cssSelector(".search-input.ng-untouched.ng-pristine.ng-valid")).sendKeys(data);
			// } catch (Exception e) {
			// driver.findElement(By.cssSelector(".search-input.ng-valid.ng-dirty.ng-touched")).clear();
			// driver.findElement(By.cssSelector(".search-input.ng-valid.ng-dirty.ng-touched")).sendKeys(data);
			// }

			sentkeys = Instant.now();
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='desktop-search-results']")));
			List<WebElement> searchresults = driver
					.findElements(By.xpath("//*[@class='desktop-search-results']//child::li"));
			System.out.println("'" + data + "' search result is" + searchresults.size());
			if (searchresults.size() > 0)
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							searchresults.get(searchresults.size()));
				} catch (Exception e) {
				}
			searchresultsappeared = Instant.now();
			captureScreenWithFilename("FoxyBingoGlobalSearchResults_" + data);
			System.out.println("Search results for " + data + " appeared in "
					+ timediff(sentkeys, searchresultsappeared) + " seconds");
			timetaken = timediff(sentkeys, searchresultsappeared);
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", promomodule);
		} catch (Exception e) {
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
			e.printStackTrace();
		}
		return timetaken;
	}

	public static float foxyMyAccount(String data) {
		if (isFoxyLoggedIn()) {
			driver.findElement(By.xpath("//*[@vnelementkey='HEADER_ACCOUNT_BADGE']")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(".menu-item-link.list-nav-link.ng-star-inserted")));
			List<WebElement> myaccountmenu = driver
					.findElements(By.cssSelector(".menu-item-link.list-nav-link.ng-star-inserted"));
			List<WebElement> myaccountmenutext = driver.findElements(By.cssSelector(".list-nav-txt.ng-star-inserted"));

		}

		return 0;

	}

	public static float foxyDeposit(String data) {
		if (isFoxyLoggedIn()) {
			System.out.println("User is logged in, searching for My Account");
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@vnelementkey='HEADER_DEPOSIT_BUTTON']")));
			Instant depclick = Instant.now();
			driver.findElement(By.xpath("//*[@vnelementkey='HEADER_DEPOSIT_BUTTON']")).click();
			System.out.println("Clicked on Deposit button");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='options-list']")));
			System.out.println("Deposit options list is displayed");
			driver.findElement(By.cssSelector(".option-center.payment-method-neteller")).click();
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("netellerDepositForm")));
			driver.findElement(By.id("navamount")).sendKeys("34");
			driver.findElement(By.id("netellerid")).sendKeys("123412341234");
			driver.findElement(By.id("netsecureid")).sendKeys("123123");
			driver.findElement(By.id("submitButton")).click();
			System.out.println("");
			System.out.println("");
			System.out.println("");

		} else
			System.out.println("User is not logged in");

		return 0;

	}

	public static void foxyPerformance(String data) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now));
		int free;
		for (free = 0; !ExcelUtils.getCellData(0, free, Constants.Sheet_foxyPerformance).isEmpty(); free++) {
		}
		ExcelUtils.setCellData(dtf.format(now), 0, free, Constants.Sheet_foxyPerformance);
		String step1 = defaultBrowseClient(Constants.cchome) + " seconds";
		ExcelUtils.setCellData(step1, 1, free, Constants.Sheet_foxyPerformance);
		// String step2=foxyRegistration(data)+" seconds";
		// ExcelUtils.setCellData(step2, 2, free, Constants.Sheet_foxyPerformance);
		String step3 = foxyLogin("FBTESTGVCEU3") + " seconds";
		ExcelUtils.setCellData(step3, 3, free, Constants.Sheet_foxyPerformance);
		// String step3half = foxyDeposit(data) + " seconds";
		String step4 = gotoBingoTab("Schedule") + " seconds";
		ExcelUtils.setCellData(step4, 4, free, Constants.Sheet_foxyPerformance);
		String step5 = gotoBingoTab("Rooms") + " seconds";
		ExcelUtils.setCellData(step5, 5, free, Constants.Sheet_foxyPerformance);
		String step6 = gotoBingoTab("My Favourites") + " seconds";
		ExcelUtils.setCellData(step6, 6, free, Constants.Sheet_foxyPerformance);
		// System.out.println(checkAllTabsNavigation().toString().replaceAll(", ",
		// "\n"));
		// ArrayList<String> casinooffers = checkAllTabsNavigation();
		// int cocount;
		// for (cocount = 0; cocount < casinooffers.size(); cocount++) {
		//
		// if (!casinooffers.get(cocount).equals("offers")) {
		// System.out.println(casinooffers.get(cocount).substring(0,
		// casinooffers.get(cocount).indexOf(",")));
		// ExcelUtils.setCellData(casinooffers.get(cocount).substring(0,
		// casinooffers.get(cocount).indexOf(",")),
		// cocount + 7, 1, Constants.Sheet_foxyPerformance);
		// System.out.println(casinooffers.get(cocount).substring(casinooffers.get(cocount).indexOf(",")
		// + 1,
		// casinooffers.get(cocount).length()));
		// ExcelUtils.setCellData(
		// casinooffers.get(cocount).substring(casinooffers.get(cocount).indexOf(",") +
		// 1,
		// casinooffers.get(cocount).length()),
		// cocount + 7, free, Constants.Sheet_foxyPerformance);
		// }
		// if (casinooffers.get(cocount).equals("offers")) {
		// cocount++;
		// break;
		// }
		// }
		// for (int i = 0; cocount < casinooffers.size(); cocount++, i++) {
		// System.out.println(casinooffers.get(cocount).substring(0,
		// casinooffers.get(cocount).indexOf(",")));
		// ExcelUtils.setCellData(casinooffers.get(cocount).substring(0,
		// casinooffers.get(cocount).indexOf(",")),
		// i + 16, 1, Constants.Sheet_foxyPerformance);
		// System.out.println(casinooffers.get(cocount).substring(casinooffers.get(cocount).indexOf(",")
		// + 1,
		// casinooffers.get(cocount).length()));
		// ExcelUtils.setCellData(casinooffers.get(cocount).substring(casinooffers.get(cocount).indexOf(",")
		// + 1,
		// casinooffers.get(cocount).length()), i + 16, free,
		// Constants.Sheet_foxyPerformance);
		// }

		// String step20 = foxyCasinoGlobalSearch("luck") + " seconds";
		// ExcelUtils.setCellData(step20, 20, free, Constants.Sheet_foxyPerformance);
		// gotoFoxyPage("bingo.foxybingo");
		// String step21 = gotoFoxyPage("casino.foxybingo") + " seconds";
		// ExcelUtils.setCellData("HOME - CNV", 21, free,
		// Constants.Sheet_foxyPerformance);
		// gotoFoxyPage("bingo.foxybingo");
		// String step22 = gotoFoxyPage("casino.foxybingo") + " seconds";
		// ExcelUtils.setCellData(step22, 22, free, Constants.Sheet_foxyPerformance);
		// gotoFoxyPage("promo.foxybingo");
		// String step23 = gotoFoxyPage("casino.foxybingo") + " seconds";
		// ExcelUtils.setCellData(step23, 23, free, Constants.Sheet_foxyPerformance);
		// String step24 = gotoFoxyPage("bingo.foxybingo") + " seconds";
		// ExcelUtils.setCellData(step24, 24, free, Constants.Sheet_foxyPerformance);
		// gotoFoxyPage("promo.foxybingo");
		// String step25 = gotoFoxyPage("bingo.foxybingo") + " seconds";
		// ExcelUtils.setCellData(step25, 25, free, Constants.Sheet_foxyPerformance);
		// // gotoFoxyPage("promo.foxybingo");
		// String step26 = gotoFoxyPage("bingo.foxybingo") + " seconds";
		// ExcelUtils.setCellData("HOME - CNV", 26, free,
		// Constants.Sheet_foxyPerformance);
		// String step27 = gotoFoxyPage("promo.foxybingo") + " seconds";
		// ExcelUtils.setCellData(step27, 27, free, Constants.Sheet_foxyPerformance);

		LaunchFoxyBingoGame("30");
	}

	public static float gotoFoxyPage(String data) throws Exception {
		float timetaken = 0;
		ArrayList<String> maintabs = new ArrayList<String>();

		List<WebElement> tabnavlinks = driver.findElements(By.xpath("//*[@class='tab-nav-link']"));
		System.out.println("Navigation tab has " + tabnavlinks.size() + " tabs each with URLs");
		for (int i = 0; i < tabnavlinks.size(); i++) {
			System.out.println(tabnavlinks.get(i).getAttribute("href"));
			maintabs.add(tabnavlinks.get(i).getAttribute("href"));
		}
		for (int i = 0; i < tabnavlinks.size(); i++) {
			if (maintabs.get(i) != null && maintabs.get(i).contains(data)) {
				Instant pagehit = Instant.now();
				driver.get(maintabs.get(i));
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-content']")));
				Instant pageloadcomplete = Instant.now();
				System.out.println("Navigation to page '" + maintabs.get(i) + "' took "
						+ +(float) ((float) (Duration.between(pagehit, pageloadcomplete).toMillis())) / (float) 1000
						+ " seconds");
				dataEngineLog("Navigation to page '" + maintabs.get(i) + "' took " + timediff(pagehit, pageloadcomplete)
						+ " seconds");
				timetaken = timediff(pagehit, pageloadcomplete);
			}
		}
		return timetaken;
	}

	public static ArrayList<String> checkAllTabsNavigation() {
		if (!driver.getCurrentUrl().equals(Constants.cchome))
			driver.get(Constants.cchome);
		System.out.println("Method 'checkAllTabsNavigation' begin");
		boolean tabloadcomplete = false;
		List<WebElement> casinotabs, bingotabs, offerstabs;
		ArrayList<String> maintabs = new ArrayList<String>();
		ArrayList<String> casinotabloadtimes = new ArrayList<String>();
		ArrayList<String> casinotabnames = new ArrayList<String>();
		ArrayList<String> casinotabnameandloadtimes = new ArrayList<String>();
		try {
			/////////////// keep this///////////////////
			//
			List<WebElement> tabnavlinks = driver.findElements(By.xpath("//*[@class='tab-nav-link']"));
			System.out.println("Navigation tab has " + tabnavlinks.size() + " tabs each with URLs");
			for (int i = 0; i < tabnavlinks.size(); i++) {
				System.out.println(tabnavlinks.get(i).getAttribute("href"));
				maintabs.add(tabnavlinks.get(i).getAttribute("href"));
			}
			for (int i = 0; i < tabnavlinks.size(); i++) {
				// System.out.println("Navigating to " + maintabs.get(i));
				Instant pagehit = Instant.now();
				driver.get(maintabs.get(i));
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-content']")));
				// if (maintabs.get(i).contains("casino")) {
				// new
				// WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-content']")));
				// }
				// if (maintabs.get(i).contains("promo")) {
				// new
				// WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-content']")));
				// }
				// if (maintabs.get(i).contains("bingo")) {
				// new
				// WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-content']")));
				// }
				Instant pageloadcomplete = Instant.now();
				System.out.println("Navigation to page '" + maintabs.get(i) + "' took "
						+ +(float) ((float) (Duration.between(pagehit, pageloadcomplete).toMillis())) / (float) 1000
						+ " seconds");
				dataEngineLog("Navigation to page '" + maintabs.get(i) + "' took "
						+ +(float) ((float) (Duration.between(pagehit, pageloadcomplete).toMillis())) / (float) 1000
						+ " seconds");
				if (maintabs.get(i).contains("bingo.foxy")) {
					// bingotabs =
					// driver.findElements(By.xpath("//*[@class='header-tabs']//child::li"));
					// for(WebElement bingotab : bingotabs) {
					// if(bingotab.findElement(By.xpath(".//child::a")).getText().toUpperCase().equals("SCHEDULE"))
					// bingotab.findElement(By.xpath(".//child::li")).click();
					// }
					System.out.println(gotoBingoTab("Schedule"));
					System.out.println(gotoBingoTab("Rooms"));
					System.out.println(gotoBingoTab("My Favourites"));
				}
				if (maintabs.get(i).contains("casino.foxy")) {
					casinotabs = driver.findElements(By.xpath("//*[@class='nontouchNav']//child::li"));
					for (int i1 = 1; i1 < casinotabs.size(); i1++) {
						System.out.println(i1 + ". Tapping tab "
								+ driver.findElement(By.xpath(
										"((//*[@class='nontouchNav']//child::li)[" + i1 + "]//child::*//child::*)[2]"))
										.getText().toUpperCase()
								+ " out of " + casinotabs.size() + " in total");
						WebElement casinotab = casinotabs.get(i1);/// made a change here at 8:17 PM to fix
						// System.out.println("Trying for tab " + driver
						// .findElement(By.xpath(
						// "((//*[@class='nontouchNav']//child::li)[" + i1 +
						// "]//child::*//child::*)[2]"))
						// .getText() + " that has class value " + casinotab.getAttribute("class"));
						if (casinotab.getAttribute("class").equals("item")) {

							// if (i1 < (casinotabs.size() - 1))
							try {
								casinotab.click();
							} catch (Exception e) {
							}
							tabloadcomplete = false;
							Instant tabtap = Instant.now();
							for (int k = 1; tabloadcomplete == false && k < 1000; k++) {
								// System.out.println("k value is : " + k);
								if (casinotab.getAttribute("class").equals("item active")) {
									tabloadcomplete = true;
									// System.out.println("class active");
									Instant tabloaddone = Instant.now();
									casinotabloadtimes.add(timediff(tabtap, tabloaddone) + " seconds");
									casinotabnames
											.add(driver.findElement(By.xpath("((//*[@class='nontouchNav']//child::li)["
													+ i1 + "]//child::*//child::*)[2]")).getText());
									casinotabnameandloadtimes.add(driver
											.findElement(By.xpath("((//*[@class='nontouchNav']//child::li)[" + i1
													+ "]//child::*//child::*)[2]"))
											.getText() + "," + timediff(tabtap, tabloaddone) + " seconds");
									System.out.println("Tab " + i1 + " "
											+ driver.findElement(By.xpath("((//*[@class='nontouchNav']//child::li)["
													+ i1 + "]//child::*//child::*)[2]")).getText()
											+ " in Casino Lobby took "
											+ +(float) ((float) (Duration.between(tabtap, tabloaddone).toMillis()))
													/ (float) 1000
											+ " seconds");
									dataEngineLog("Tab " + i1 + " "
											+ driver.findElement(By.xpath("((//*[@class='nontouchNav']//child::li)["
													+ i1 + "]//child::*//child::*)[2]")).getText()
											+ " in Casino Lobby took "
											+ +(float) ((float) (Duration.between(tabtap, tabloaddone).toMillis()))
													/ (float) 1000
											+ " seconds");
									continue;
								}
								// else
								// System.out.println("class not active");
							}
						}
					}
					casinotabnameandloadtimes.add("offers");
				}
				if (maintabs.get(i).contains("promo.foxy")) {

					WebElement promotab = driver
							.findElement(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper"));
					List<WebElement> promotabs = promotab.findElements(By.xpath(".//child::*//child::li"));
					System.out.print(promotabs.get(1).getText());
					String one = promotabs.get(1).getText();
					Instant hit1 = Instant.now();
					promotabs.get(1).click();
					new WebDriverWait(driver, 15).until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper")));
					Instant load1 = Instant.now();
					System.out.println(" took " + timediff(hit1, load1) + " seconds");
					one = one + "," + timediff(hit1, load1) + " seconds";
					casinotabnameandloadtimes.add(one);

					WebElement promotab1 = driver
							.findElement(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper"));
					List<WebElement> promotabs1 = promotab1.findElements(By.xpath(".//child::*//child::li"));
					System.out.print(promotabs1.get(3).getText());
					String three = promotabs1.get(3).getText();
					Instant hit2 = Instant.now();
					promotabs1.get(3).click();
					new WebDriverWait(driver, 15).until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper")));
					Instant load2 = Instant.now();
					System.out.println(" took " + timediff(hit2, load2) + " seconds");
					three = three + "," + timediff(hit2, hit2) + " seconds";
					casinotabnameandloadtimes.add(three);

					WebElement promotab3 = driver
							.findElement(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper"));
					List<WebElement> promotabs3 = promotab3.findElements(By.xpath(".//child::*//child::li"));
					System.out.print(promotabs3.get(0).getText());
					String zero = promotabs3.get(0).getText();
					Instant hit4 = Instant.now();
					promotabs3.get(0).click();
					new WebDriverWait(driver, 15).until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper")));
					Instant load4 = Instant.now();
					System.out.println(" took " + timediff(hit4, load4) + " seconds");
					zero = zero + "," + timediff(hit4, load4) + " seconds";
					casinotabnameandloadtimes.add(zero);

					WebElement promotab2 = driver
							.findElement(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper"));
					List<WebElement> promotabs2 = promotab2.findElements(By.xpath(".//child::*//child::li"));
					System.out.print(promotabs2.get(2).getText());
					String two = promotabs2.get(2).getText();
					Instant hit3 = Instant.now();
					promotabs2.get(2).click();
					new WebDriverWait(driver, 15).until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper")));
					Instant load3 = Instant.now();
					System.out.println(" took " + timediff(hit3, load3) + " seconds");
					two = two + "," + timediff(hit3, load3) + " seconds";
					casinotabnameandloadtimes.add(two);

					//////////////////// this is getting tricky - jump back to this later
					//////////////////// ///////////////
					// for (WebElement eachtab : promotabs) {
					// System.out.println(eachtab.getText() + " " + eachtab.getAttribute("class"));
					// if (eachtab.getAttribute("class").contains("active"))
					// continue;
					// Instant tabhit = Instant.now();
					// eachtab.click();
					// new WebDriverWait(driver,
					// 15).until(ExpectedConditions.visibilityOfElementLocated(
					// By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper")));
					// promotab =
					// driver.findElement(By.cssSelector(".navbar.navbar-expand-sm.sub-nav-wrapper"));
					// for (int j = 0; j < 1000; j++) {
					// if (eachtab.getAttribute("class").contains("active")) {
					// Instant tabdone = Instant.now();
					// continue;
					// }
					// }
					//
					// }
				}

			}
			// System.exit(0);

			/////////////// keep this///////////////////
			// System.out.println("Starting the url");

			// if (!driver.getCurrentUrl().equals(Constants.foxycasino))
			// driver.get(Constants.foxycasino);
			// // casinotabs =
			// // driver.findElements(By.xpath("//*[@class='nontouchNav']//child::li"));
			//
			// // for (WebElement casinotab : casinotabs) {
			// // Instant tabtap = Instant.now();
			// // if (casinotab.getAttribute("class").equals("item")) {
			// // casinotab.click();
			// // System.out.println(
			// // "Trying tab " +
			// // casinotab.findElement(By.xpath(".//child::*//child::*")).getText());
			// // for (int i = 0; tabloadcomplete == false && i < 1000; i++) {
			// // if (casinotab.getAttribute("class").equals("item active")) {
			// // tabloadcomplete = true;
			// // System.out.println("class active");
			// // Instant tabloaddone = Instant.now();
			// // System.out.println("Tab load took "
			// // + +(float) ((float) (Duration.between(tabtap, tabloaddone).toMillis()))
			// // / (float) 1000
			// // + " seconds");
			// // continue;
			// // } else
			// // System.out.println("class not active");
			// // }
			// // }
			// // System.out.println(casinotab.getAttribute("class"));
			// // }
			// casinotabs =
			// driver.findElements(By.xpath("//*[@class='nontouchNav']//child::li"));
			// // System.out.println("got the elements "+casinotabs.size());
			//
			// for (int i = 1; i < casinotabs.size(); i++) {
			// // System.out.println("in for");
			//
			// WebElement casinotab = casinotabs.get(i);
			// System.out.println("Trying for tab " + driver
			// .findElement(
			// By.xpath("((//*[@class='nontouchNav']//child::li)[" + i +
			// "]//child::*//child::*)[2]"))
			// .getText() + " that has class value " + casinotab.getAttribute("class"));
			// if (casinotab.getAttribute("class").equals("item")) {
			// casinotab.click();
			// tabloadcomplete = false;
			// Instant tabtap = Instant.now();
			// for (int i1 = 0; tabloadcomplete == false && i1 < 1000; i1++) {
			// if (casinotab.getAttribute("class").equals("item active")) {
			// tabloadcomplete = true;
			// // System.out.println("class active");
			// Instant tabloaddone = Instant.now();
			// System.out.println("Tab "
			// + driver.findElement(By.xpath("((//*[@class='nontouchNav']//child::li)[" + i1
			// + "]//child::*//child::*)[2]")).getText()
			// + " in Casino Lobby took "
			// + +(float) ((float) (Duration.between(tabtap, tabloaddone).toMillis()))
			// / (float) 1000
			// + " seconds");
			// dataEngineLog("Tab load "
			// + driver.findElement(By.xpath("((//*[@class='nontouchNav']//child::li)[" + i1
			// + "]//child::*//child::*)[2]")).getText()
			// + " took " + +(float) ((float) (Duration.between(tabtap,
			// tabloaddone).toMillis()))
			// / (float) 1000
			// + " seconds");
			// continue;
			// }
			// // else
			// // System.out.println("class not active");
			// }
			// }
			// }

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
		}
		return casinotabnameandloadtimes;

	}

	public static void checkAllHyperlinks(String data) {
		if (!driver.getCurrentUrl().equals(Constants.cchome))
			driver.get(Constants.cchome);
		try {
			List<WebElement> hrefs = driver.findElements(By.xpath("//a"));
			String homepage = driver.getCurrentUrl();
			ArrayList<String> linker = new ArrayList<String>();
			int i = 1;
			for (WebElement href : hrefs) {
				if (href.getAttribute("href") != null) {
					System.out.println("Link " + i + " is : " + href.getAttribute("href"));
					linker.add(href.getAttribute("href"));
					i++;
				}
			}
			System.out.println("Linker size :" + linker.size());
			for (int j = 0; j < linker.size(); j++) {
				try {
					if (linker.get(j).contains("foxybingo")) {
						Instant pageclicktime = Instant.now();
						driver.get(linker.get(j));
						Instant pageloadcomplete = Instant.now();
						System.out.println("LINK " + j + " [" + linker.get(j) + "] page load took "
								+ (float) ((float) (Duration.between(pageclicktime, pageloadcomplete).toMillis()))
										/ (float) 1000
								+ " seconds to load completely");
						dataEngineLog("LINK " + j + " [" + linker.get(j) + "] page load took "
								+ (float) ((float) (Duration.between(pageclicktime, pageloadcomplete).toMillis()))
										/ (float) 1000
								+ " seconds to load completely");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Navigation of all hyperlinks on " + homepage.replace("https://", "")
					+ "complete, returning to home page");
			driver.get(homepage);
		} catch (Exception e) {
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
			e.printStackTrace();
		}
	}

	public static void markBingoRoomAsFavourite(String data) throws InterruptedException {
		if (!driver.getCurrentUrl().contains(Constants.cchome))
			driver.get(Constants.cchome);
		try {
			List<WebElement> bingotabs = driver.findElements(By.xpath("//*[@class='header-tabs']//child::li"));
			for (WebElement btab : bingotabs) {
				System.out.println(btab.getText());
				if (btab.getText().toUpperCase().trim().equals("SCHEDULE"))
					btab.click();
			}
			new WebDriverWait(driver, 25).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])[1]")));
			List<WebElement> roomlist = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
			for (int i = 1; i <= roomlist.size(); i++) {

				List<WebElement> roomdata2 = driver
						.findElements(By.xpath("(//*[@class='bingogames-group'])[" + i + "]//child::div"));
				int i1 = 0, j = 0;
				// System.out.print("\nRoom " + i + " data is\t||");
				for (WebElement rooms2 : roomdata2) {
					// System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
					if (rooms2.getText().replace("\n", " ").trim().contains(data)
							|| rooms2.getText().replace("\n", " ").trim().contains(data.toUpperCase())) {
						System.out.println(driver
								.findElement(By.xpath(
										"((//*[@class='bingogames-group'])[" + i + "]//child::div)[6]//child::i"))
								.getAttribute("class"));
						if (driver
								.findElement(By.xpath(
										"((//*[@class='bingogames-group'])[" + i + "]//child::div)[6]//child::i"))
								.getAttribute("class").contains("removed")) {
							driver.findElement(
									By.xpath("((//*[@class='bingogames-group'])[" + i + "]//child::div)[6]//child::i"))
									.click();
							new WebDriverWait(driver, 10).until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//*[@class='bg-toast-text']")));
							captureScreenWithFilename("FoxyRoomFavourite_" + data.toUpperCase() + "Sucess");
							System.out.println("Room " + data.toUpperCase() + " favourited");
						} else if (driver
								.findElement(By.xpath(
										"((//*[@class='bingogames-group'])[" + i + "]//child::div)[6]//child::i"))
								.getAttribute("class").contains("added"))
							System.out.println("I think the room is already favourited");
					}
				}
			}
		}

		catch (StaleElementReferenceException e1) {
			new WebDriverWait(driver, 25)
					.until(ExpectedConditions.visibilityOfElementLocated(By.className("bingogames-group")));
			new WebDriverWait(driver, 25).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='bingogames-group'])[1]")));
			List<WebElement> roomlist = driver.findElements(By.xpath("//*[@class='bingogames-group']"));
			for (int i = 1; i <= roomlist.size(); i++) {
				List<WebElement> roomdata2 = driver
						.findElements(By.xpath("(//*[@class='bingogames-group'])[" + i + "]//child::div"));
				int i1 = 0, j = 0;
				// System.out.print("\nRoom " + i + " data is\t||");
				for (WebElement rooms2 : roomdata2) {
					// System.out.print(rooms2.getText().replace("\n", " ").trim() + "||\t\t\t||");
					// if (rooms2.getText().replace("\n", " ").trim().contains("DOWN TOWN")) {
					if (rooms2.getText().replace("\n", " ").trim().contains(data)
							|| rooms2.getText().replace("\n", " ").trim().contains(data.toUpperCase())) {
						driver.findElement(
								By.xpath("((//*[@class='bingogames-group'])[" + i + "]//child::div)[6]//child::i"))
								.click();
						System.out.println("Room " + data.toUpperCase() + " favourited");
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println("markBingoRoomAsFavourite failed");
			e.printStackTrace();
			captureScreenWithFilename("FoxyBingo_" + DriverScript.sActionKeyword + "_Error");
		}
	}

}