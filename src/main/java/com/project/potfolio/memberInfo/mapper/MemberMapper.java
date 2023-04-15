package com.project.potfolio.memberInfo.mapper;

import com.project.potfolio.memberInfo.dao.request.MemberInsertDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insertMemberInfo(MemberInsertDAO data);
    Boolean isExistMemberId(@Param("id") String id);
}
