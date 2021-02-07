package com.iquantex.email.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author myli
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CS_MAIL_FILE")
public class CsMailFile implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "FILE_ID", type = IdType.INPUT)
    private String fileId;

    @TableField("MAIL_ID")
    private String mailId;

    @TableField("FILE_NAME")
    private String fileName;

    @TableField("CONTENT_TYPE")
    private String contentType;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("FILE_CONTENT")
    private byte[] fileContent;


    public static final String FILE_ID = "FILE_ID";

    public static final String MAIL_ID = "MAIL_ID";

    public static final String FILE_NAME = "FILE_NAME";

    public static final String CONTENT_TYPE = "CONTENT_TYPE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String FILE_CONTENT = "FILE_CONTENT";

}
