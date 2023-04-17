package com.project.potfolio.memberInfo.service;

import com.project.potfolio.memberInfo.dao.request.MemberLoginDAO;
import com.project.potfolio.memberInfo.dao.response.*;
import com.project.potfolio.memberInfo.mapper.MemberMapper;
import com.project.potfolio.memberInfo.dao.request.MemberInsertDAO;
import com.project.potfolio.utils.AESAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    // 회원가입 기능
    @Transactional
    public MemberResponseDAO insertMemberInfo(MemberInsertDAO data) throws Exception{
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String phonePattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
        String pwdPattern = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$";
        if(memberMapper.isExistMemberId(data.getId())){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("이미 등록된 아이디 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(!Pattern.matches(pwdPattern, data.getPwd())){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("비밀번호는 공백없이 6자리 이상 입력해 주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(!Pattern.matches(emailPattern, data.getEmail())){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("이메일 형식이 올바르지 않습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(!Pattern.matches(phonePattern, data.getPhone())){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("전화번호 형식이 올바르지 않습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if(data.getId() == null){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("아이디가 입력되었는지 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(data.getPwd() == null){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("비밀번호가 입력되었는지 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(data.getName() == null){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("이름이 입력되었는지 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(data.getNickname() == null){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("닉네임이 입력되었는지 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(data.getPhone() == null){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("전화번호가 입력되었는지 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        else if(data.getEmail() == null){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("이메일이 입력되었는지 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        data.setPwd(AESAlgorithm.Encrypt(data.getPwd()));
        memberMapper.insertMemberInfo(data);
        return MemberResponseDAO.builder()
                .status(true)
                .message("회원 등록이 완료되었습니다.")
                .code(HttpStatus.ACCEPTED)
                .build();
    }

    // 회원 로그인 기능
    public MemberLoginResponseDAO memberLogin(MemberLoginDAO login) throws Exception{
        login.setPwd(AESAlgorithm.Encrypt(login.getPwd()));
        MemberLoginInfoResponseDAO member = memberMapper.loginMember(login);
        if(member == null){
            return MemberLoginResponseDAO.builder()
                    .status(false)
                    .message("아이디 또는 비밀번호 오류입니다.")
                    .code(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        else if(member.getStatus() == 1){
            return MemberLoginResponseDAO.builder()
                    .status(false)
                    .message("사용 정지된 회원입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        return MemberLoginResponseDAO.builder()
                .status(true)
                .message("로그인 되었습니다.")
                .code(HttpStatus.OK)
                .login(member)
                .build();
    }

    // 회원 정보 수정 기능
    public MemberResponseDAO updateMemberInfo(@PathVariable String id, MemberInsertDAO data) throws Exception{
        if(!memberMapper.isExistMemberId(id)){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("잘못된 회원 아이디 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if(data.getPwd() != null){
            data.setPwd(AESAlgorithm.Encrypt(data.getPwd()));
        }
        memberMapper.updateMemberInfo(id, data);
        return MemberResponseDAO.builder()
                .status(true)
                .message("회원 정보를 수정했습니다.")
                .code(HttpStatus.OK)
                .build();
    }

    // 회원 탈퇴 기능
    public MemberResponseDAO deleteMemberInfo(@PathVariable String id) throws Exception{
        if(!memberMapper.isExistMemberId(id)){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("잘못된 회원 아이디 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        memberMapper.deleteMemberInfo(id);
        return MemberResponseDAO.builder()
                .status(true)
                .message("회원 정보 삭제 완료")
                .code(HttpStatus.OK)
                .build();
    }

    // 회원 상세 정보 조회
    public MemberDetailResponseDAO getMemberDetailInfo(@PathVariable String id){
        if(!memberMapper.isExistMemberId(id)){
            return MemberDetailResponseDAO.builder()
                    .status(false)
                    .message("회원 정보를 찾을 수 없습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        return MemberDetailResponseDAO.builder()
                .status(true)
                .message("회원 상세 정보 조회!")
                .code(HttpStatus.OK)
                .info(memberMapper.getMemberDetailInfo(id))
                .build();
    }
}
