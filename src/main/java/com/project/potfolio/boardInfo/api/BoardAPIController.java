package com.project.potfolio.boardInfo.api;

import com.project.potfolio.boardInfo.dao.request.BoardInsertDAO;
import com.project.potfolio.boardInfo.dao.response.BoardResponseDAO;
import com.project.potfolio.boardInfo.mapper.BoardMapper;
import com.project.potfolio.boardInfo.service.BoardService;
import com.project.potfolio.memberInfo.dao.response.MemberResponseDAO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardAPIController {
    private final BoardService boardService;
    @ApiOperation(value = "게시글 등록", notes = "게시글 등록 API")
    @PutMapping("/")
    public ResponseEntity<BoardResponseDAO>insertBoardInfo(@RequestBody BoardInsertDAO data) throws Exception{
        BoardResponseDAO response = boardService.insertBoardInfo(data);
        return new ResponseEntity<>(response, response.getCode());
    }
}
