package com.project.potfolio.memberInfo.mapper;

import com.project.potfolio.memberInfo.dao.request.MemberInsertDAO;
import com.project.potfolio.memberInfo.dao.request.MemberLoginDAO;
import com.project.potfolio.memberInfo.dao.response.MemberLoginInfoResponseDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insertMemberInfo(MemberInsertDAO data);
    Boolean isExistMemberId(@Param("id") String id);
    MemberLoginInfoResponseDAO loginMember(MemberLoginDAO login);
    void updateMemberInfo(@Param("id") String id, @Param("data") MemberInsertDAO data);
    void deleteMemberInfo(@Param("id") String id);
}
