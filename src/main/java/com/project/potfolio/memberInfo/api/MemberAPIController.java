package com.project.potfolio.memberInfo.api;

import com.project.potfolio.memberInfo.service.MemberService;
import com.project.potfolio.memberInfo.dao.request.MemberInsertDAO;
import com.project.potfolio.memberInfo.dao.response.MemberResponseDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Api(tags = "회원정보 관리")
public class MemberAPIController {
    private final MemberService memberService;

    @ApiOperation(value = "회원가입", notes = "회원가입 API")
    @PutMapping("")
    public ResponseEntity<MemberResponseDAO> insertMemberInfo(@RequestBody MemberInsertDAO data) throws Exception{
        MemberResponseDAO response = memberService.insertMemberInfo(data);
        return new ResponseEntity<>(response, response.getCode());
    }
}
