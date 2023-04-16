package com.project.potfolio.memberInfo.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginInfoResponseDAO {
    private Integer no;
    private String id;
    private String name;
    private String nickname;
    private Integer status;
}
