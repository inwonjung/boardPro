package com.project.potfolio.boardInfo.mapper;

import com.project.potfolio.boardInfo.dao.request.BoardInsertDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void insertBoardInfo(BoardInsertDAO data);
}
