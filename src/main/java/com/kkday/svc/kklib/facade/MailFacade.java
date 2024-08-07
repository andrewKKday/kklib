package com.kkday.svc.kklib.facade;

import com.kkday.sdk.api.KKApiFactory;
import com.kkday.svc.kklib.api.MailFakeApi;
import com.kkday.svc.kklib.controller.data.Mail;
import com.kkday.svc.kklib.controller.data.MailResp;
import com.kkday.svc.kklib.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class MailFacade {
    @Autowired
    BookService bookService;

    public void generateMail(String outputPath, String userName, Integer bookOid) {
        try {
            MailFakeApi api = KKApiFactory.getApi(MailFakeApi.class);
            MailResp resp = api.getMail();
            Mail mail = resp.getData();

            VelocityEngine velocityEngine = new VelocityEngine();

            VelocityContext context = new VelocityContext();
            context.put("bookTitle", bookService.findById(bookOid).getBookTitle());
            context.put("userName", userName);

            StringWriter writer = new StringWriter();
            velocityEngine.evaluate(context, writer, mail.toString(), mail.getMailContent());

            writeFile(outputPath, writer.toString());
        } catch (Exception e) {
            log.error("Error generating mail: \n{}", e.getMessage(), e);
        }
    }

    private void writeFile(String outputPath, String content) {
        try (PrintWriter writer = new PrintWriter(outputPath, StandardCharsets.UTF_8)) {
            writer.println(content);
        } catch (Exception e) {
            log.error("Error writing file: \n{}", e.getMessage(), e);
        }
    }
}
