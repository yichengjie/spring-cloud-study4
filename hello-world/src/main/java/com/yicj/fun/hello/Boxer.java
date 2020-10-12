package com.yicj.fun.hello;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boxer{
    // 分组
    private String group ;
    // 重量级
    private Integer weight ;
}
