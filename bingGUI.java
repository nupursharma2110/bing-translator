package bing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class bingGUI {

	public static void main(String[] args) throws InterruptedException, IOException {
	
		WebDriver driver =new FirefoxDriver();
		driver.get("https://www.bing.com/translator");
		File file = new File("/home/nupursharma/Desktop/bing3.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;

		while( (line = br.readLine())!= null ){
			WebElement my= driver.findElement(By.className("LanguageSelector"));
			my.click();

			String [] tokens = line.split(",");
			String var_1 = tokens[0];
			driver.findElement(By.xpath(".//*[@class='col translationContainer sourceText']/div[1]/div[1]/div[1]/table/tbody/tr[*]/td[text() = '"+var_1+"']")).click();

			String var_3 = tokens[2].toString();
			System.out.println(var_3);
			driver.findElement(By.cssSelector("#srcText")).sendKeys(var_3);
			Thread.sleep(5000);
			String var_2 = tokens[1];
			driver.findElement(By.xpath(".//*[@class='col translationContainer destinationText']/div[1]/div[1]")).click();
			driver.findElement(By.xpath(".//*[@class='col translationContainer destinationText']/div[1]/div[1]/div[1]/table/tbody/tr[*]/td[text() = '"+var_2+"']")).click();


			String a =driver.findElement(By.xpath(".//*[@id='destText']")).getText();
			System.out.println("result"+a);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/nupursharma/Desktop/gui.txt", true)));
			out.println(a);
			driver.findElement(By.cssSelector("#srcText")).clear();



		}
		
}

}
