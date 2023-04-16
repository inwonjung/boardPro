package com.project.potfolio.memberInfo.dao.response;

import com.project.potfolio.memberInfo.dao.request.MemberLoginDAO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResponseDAO {
    private Boolean status;
    private String message;
    @ApiModelProperty(name = "login", notes = "로그인 학생 정보", required = true)
    private MemberLoginInfoResponseDAO login;
    private HttpStatus code;
}
