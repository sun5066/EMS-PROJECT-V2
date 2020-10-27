package com.biz.ems.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    private long f_seq;
    private long f_b_seq;
    private String f_org_name;
    private String f_file_name;
    private long f_down;
}
