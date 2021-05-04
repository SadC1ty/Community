package com.nowcoder.community;

import com.nowcoder.community.util.MailCilent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MaiTests {

    @Autowired
    private MailCilent mailCilent;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail(){
        mailCilent.sendMail("875046105@qq.com","Test","Welcome");
    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","sunday");
        String content = templateEngine.process("/mail/demo",context);
        System.out.println(content);
        mailCilent.sendMail("875046105@qq.com","Html",content);
    }


}
