package com.project.potfolio.memberInfo.service;

import com.project.potfolio.memberInfo.mapper.MemberMapper;
import com.project.potfolio.memberInfo.dao.request.MemberInsertDAO;
import com.project.potfolio.memberInfo.dao.response.MemberResponseDAO;
import com.project.potfolio.utils.AESAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    @Transactional
    public MemberResponseDAO insertMemberInfo(MemberInsertDAO data) throws Exception{
        if(memberMapper.isExistMemberId(data.getId())){
            return MemberResponseDAO.builder()
                    .status(false)
                    .message("이미 등록된 아이디 입니다.")
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
}
