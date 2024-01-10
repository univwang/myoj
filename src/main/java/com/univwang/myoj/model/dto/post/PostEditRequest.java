package com.univwang.myoj.model.dto.post;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 编辑请求
 *
 *  
 * @from <a href="https://univwang.top">  </a>
 */
@Data
public class PostEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}