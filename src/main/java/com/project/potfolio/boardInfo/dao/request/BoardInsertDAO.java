package com.project.potfolio.boardInfo.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardInsertDAO {
    private Integer member;
    private String title;
    private String content;
    private Integer subCate;
}
