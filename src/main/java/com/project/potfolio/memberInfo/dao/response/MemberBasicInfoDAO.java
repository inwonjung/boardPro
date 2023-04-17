package com.project.potfolio.memberInfo.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberBasicInfoDAO {
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private Integer report;
    private Integer grade;
    private Integer recommend;
}
