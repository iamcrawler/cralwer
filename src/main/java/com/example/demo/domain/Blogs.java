package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by 刘亮 on 2018/5/2.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("blog")
public class Blogs {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private String summary;

    private Date createTime;

    private Integer viewTimes;

    private String tag;

    @TableField(exist = false)
    private List<String> tags;

    private String category;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    private String markdownContent;

}
