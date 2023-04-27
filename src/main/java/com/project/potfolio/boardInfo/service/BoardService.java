package com.project.potfolio.boardInfo.service;

import com.project.potfolio.boardInfo.dao.request.BoardInsertDAO;
import com.project.potfolio.boardInfo.dao.response.BoardResponseDAO;
import com.project.potfolio.memberInfo.mapper.MemberMapper;
import com.project.potfolio.memberInfo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemberMapper memberMapper;
    public BoardResponseDAO insertBoardInfo(BoardInsertDAO data)throws Exception{
//        if(!memberMapper.isExistMemberId(id)){
//            return BoardResponseDAO.builder()
//                    .status(false)
//                    .message("잘못된 회원 아이디 입니다.")
//                    .code(HttpStatus.BAD_REQUEST)
//                    .build();
//        }
        if(data.getSubCate() == null){  // 이 부분 카테고리 정보 먼저 만들고 수정 해야할 듯
            return BoardResponseDAO.builder()
                    .status(false)
                    .message("존재하지 않는 카테고리 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        return BoardResponseDAO.builder()
                .status(true)
                .message("게시글 등록 완료")
                .code(HttpStatus.OK)
                .build();
    }
}
