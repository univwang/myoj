package com.univwang.myoj.model.dto.postthumb;

import java.io.Serializable;
import lombok.Data;

/**
 * 帖子点赞请求
 *
 *  
 * @from <a href="https://univwang.top">  </a>
 */
@Data
public class PostThumbAddRequest implements Serializable {

    /**
     * 帖子 id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}