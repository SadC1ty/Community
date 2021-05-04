package com.nowcoder.community;

import com.nowcoder.community.util.SensitveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {
    @Autowired
    private SensitveFilter sensitveFilter;

    @Test
    public void testSensitiveFilter(){
        String text= "这里可以赌博，可以嫖娼，可以吸毒，可以开票";
        text = sensitveFilter.filter(text);
        System.out.println(text);
    }
}
