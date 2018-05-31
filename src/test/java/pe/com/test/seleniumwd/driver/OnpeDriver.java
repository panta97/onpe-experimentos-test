package pe.com.test.seleniumwd.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public final class OnpeDriver {

	public final static WebDriver inicializarDriver(String navegador, boolean remoto) {
		WebDriver webDriver = null;
		System.out.println("Ejecución Remota: " + (remoto ? "SI" : "NO"));
		switch (navegador) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"C:\\ProgramasInstalados\\geckodriver.exe");
			webDriver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"C:\\ProgramasInstalados\\chromedriver.exe");
			webDriver = new ChromeDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver",
					"C:\\ProgramasInstalados\\IEDriverServer.exe");
			webDriver = new InternetExplorerDriver();
			break;
		}
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return webDriver;
	}

	public final static void cerrarPagina(WebDriver webDriver) {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
