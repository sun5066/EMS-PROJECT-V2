package com.biz.ems.exec;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class NaverSecMake {

    public static void main(String[] args) {
        // 암호화된 네이버 Email 정보를 저장할 파일
        String propsFile = "./src/main/webapp/WEB-INF/spring/naver.sec.properties";

        // 암호화를 수행할때 사용할 salt 추출
        Map<String, String> envList = System.getenv();
        String saltPass = envList.get("NAVER");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Salt Password : " + saltPass);

        if (saltPass == null) {
            System.out.println("NAVER 환경변수를 설정한 후 실행해주세요!");
            return;
        }

        System.out.print("Email 주소 : ");
        String email = scanner.nextLine();
        System.out.print("비밀번호 : ");
        String password = scanner.nextLine();

        StandardPBEStringEncryptor pbeStringEncryptor = new StandardPBEStringEncryptor();
        pbeStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        pbeStringEncryptor.setPassword(saltPass);

        String encEmail = pbeStringEncryptor.encrypt(email);
        String endPass = pbeStringEncryptor.encrypt(password);

        System.out.println("Email : " + encEmail);
        System.out.println("Pass : " + endPass);

        String props_email = String.format("naver.email=ENC(%s)", encEmail);
        String props_password = String.format("naver.password=ENC(%s)", endPass);

        try {
            PrintWriter out = new PrintWriter(propsFile);
            out.println(props_email);
            out.println(props_password);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
