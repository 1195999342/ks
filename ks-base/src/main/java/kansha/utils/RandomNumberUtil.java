package kansha.utils;

/**
 * @program: ks-parent
 * @description:
 * @author: pzy
 * @create: 2019-08-02 15:58
 **/

public class RandomNumberUtil {

    //随机生成4为验证码
    public static String getRandomNumber4(){
        String code = "";
        for(int i=0;i<4;i++){
            int random = (int)(Math.random()*10);
            code += String.valueOf(random);
        }
        return code;
    }
    //随机生成6为验证码
    public static String getRandomNumber6(){
        String code = "";
        for(int i=0;i<6;i++){
            int random = (int)(Math.random()*10);
            code += String.valueOf(random);
        }
        return code;
    }
}