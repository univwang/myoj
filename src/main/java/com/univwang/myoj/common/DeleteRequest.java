package com.univwang.myoj.common;

import java.io.Serializable;
import lombok.Data;

/**
 * 删除请求
 *
 *  
 * @from <a href="https://univwang.top">  </a>
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}