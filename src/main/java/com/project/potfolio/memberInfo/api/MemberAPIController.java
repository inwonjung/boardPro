package com.project.potfolio.memberInfo.api;

import com.project.potfolio.memberInfo.dao.request.MemberLoginDAO;
import com.project.potfolio.memberInfo.dao.response.MemberLoginResponseDAO;
import com.project.potfolio.memberInfo.service.MemberService;
import com.project.potfolio.memberInfo.dao.request.MemberInsertDAO;
import com.project.potfolio.memberInfo.dao.response.MemberResponseDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "회원 로그인", notes = "회원 로그인 API")
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDAO> postMemberLogin(@RequestBody MemberLoginDAO login) throws Exception{
        MemberLoginResponseDAO response = memberService.memberLogin(login);
        return new ResponseEntity<>(response, response.getCode());
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정 API")
    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDAO> updateMemberInfo(@PathVariable String id, @ApiParam(value = "회원 정보 객체", required = true) @RequestBody MemberInsertDAO data) throws Exception{
        MemberResponseDAO response = memberService.updateMemberInfo(id, data);
        return new ResponseEntity<>(response, response.getCode());
    }

    @ApiOperation(value = "회원 정보 삭제", notes = "회원 정보 삭제 API")
    @DeleteMapping("/{id}")
    public ResponseEntity<MemberResponseDAO> deleteMemberInfo(@PathVariable String id) throws Exception{
        MemberResponseDAO response = memberService.deleteMemberInfo(id);
        return new ResponseEntity<>(response, response.getCode());
    }
}
