package com.project.potfolio.memberInfo.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDetailResponseDAO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    private MemberBasicInfoDAO info;
}
