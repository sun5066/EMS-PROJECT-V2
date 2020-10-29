package com.biz.ems.service;

import com.biz.ems.model.EmsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class NaverMailSendServiceV1 {

    @Autowired
    private JavaMailSender xMail;

    @Autowired
    private String winPath;

    public void sendMail(EmsVO emsVO) {
        String uploadFolder = winPath;

        String from_email = emsVO.getFrom_email();
        String to_email = emsVO.getTo_email();

        String s_file1 = emsVO.getS_file1();
        String s_file2 = emsVO.getS_file2();

        /**
         * MineType
         * 인터넷 TCP/IP 를 통해서 주고받는 문서(파일)등은
         * 고유의 형식을 갖는다
         * 그러한 형식을 마임타입이라고 한다.
         *
         * Hyper Text markup tag 로 만들어진 문서를 HTML DOC type 이라고 하며
         * 이 문서는 Web Browser 를 통해서 내용을 확인 할 수 있다.
         * 이 처럼 Mime Type 을 지정해둔 문서는 인터넷으로 부터 받았을때
         * 즉시 내용을 확인할 수 있다.
         *
         * xMail 을 통해서 지금 만드는 Mime type 은 email viewer 에서 확인 할 수 있는 문서 형식이다
         * 이 문서는 메모장이나, 웹브라우저 로 보면 그 내용을 정확히 확인이 어렵다.
         * 대표적으로 xMail Mime type 문서를 볼 수 있는 어플리케이션에 아웃룩이 있고
         * 각 스마트 기기에 고유 Email App 이 이런 종류에 해당한다.
         * */
        MimeMessage mimeMessage = xMail.createMimeMessage();
        
        // Mime Type 으로 만들어진 문서를 인터넷으로 전송하는데
        // 여러가지 기능을 수행해주는 도구
        MimeMessageHelper mimeMessageHelper = null;

        try {
            // 두번째 값을 true : HTML 적용을 한 메시지를 보낼수 있다. + 메시지에 이미지 파일을 적용하여 보낼 수 있다.
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(from_email);
            mimeMessageHelper.setTo(to_email);
            mimeMessageHelper.setSubject(emsVO.getS_subject());

            // 두번째값을 true : HTML 적용을 한 메시지를 보낼수 있다.
            mimeMessageHelper.setText(emsVO.getS_content(), true);

            FileSystemResource fileSystemResource = null;
            if (!s_file1.isEmpty()) {
                fileSystemResource = new FileSystemResource(new File(uploadFolder, s_file1));
                mimeMessageHelper.addAttachment(s_file1, fileSystemResource);
            }
            if (!s_file2.isEmpty()) {
                fileSystemResource = new FileSystemResource(new File(uploadFolder, s_file2));
                mimeMessageHelper.addAttachment(s_file2, fileSystemResource);
            }

            xMail.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
