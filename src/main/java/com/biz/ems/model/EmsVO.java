package com.biz.ems.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmsVO {
    private long id;
    private String from_email;
    private String to_email;
    private String s_date;
    private String s_time;
    private String s_subject;
    private String s_content;
    private String s_file1;
    private String s_file2;

    private List<FileVO> fileVOList;
}
