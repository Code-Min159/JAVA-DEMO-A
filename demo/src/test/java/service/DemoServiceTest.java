package service;

import com.lesson.demo.service.DemoService;
import org.junit.jupiter.api.Test;

public class DemoServiceTest {

    DemoService demoService = new DemoService();


    @Test
    void testTwoSumSuccess(){
        int a =1;
        int b =2;
        int result = demoService.twoSum(a,b);
        assert result == 3;
    }

    @Test
    void testTwoSumFailed(){
        int a =1;
        int b =2;
        int result = demoService.twoSum(a,b);
        assert result != 3;
    }

}
