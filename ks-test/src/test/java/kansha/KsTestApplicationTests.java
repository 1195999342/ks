package kansha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class KsTestApplicationTests {

    @Test
    public void contextLoads() {

        WebDriver driver = new ChromeDriver(); // 新建一个WebDriver 的对象，但是new 的是谷歌的驱动
        String url = "http://www.baidu.com";
        driver.get(url); // 打开指定的网站














        int total = 47623;
        int dayNum = 28;
        int max = new Double((total/dayNum)*1.3).intValue();
        int min = new Double((total/dayNum)*0.7).intValue();

        int newtotal = total;
        int num = 0;
        int arr[] = new int[dayNum];
        for (int i = 0; i < dayNum; i++) {
            if(newtotal < 0) {
                arr = new int[dayNum];
                newtotal = total;
                i = -1;
                continue;
            }
            if( i == dayNum-1){
                if(newtotal > max || newtotal < min){
                    arr = new int[dayNum];
                    newtotal = total;
                    i = -1;
                    continue;
                }
                arr[i] = newtotal;
                break;
            }
            num = new Random().nextInt(max - min) + min;
            arr[i] = num;
            newtotal = newtotal - num;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(i + ":::"+arr[i] );
            sum = sum+arr[i];
        }
        System.out.println(sum);

    }

}
