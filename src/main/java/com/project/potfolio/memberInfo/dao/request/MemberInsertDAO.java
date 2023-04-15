package com.project.potfolio.memberInfo.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberInsertDAO {
    private String id;
    private String pwd;
    private String name;
    private String nickname;
    private String phone;
    private String email;
}
