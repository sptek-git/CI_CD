package org.keumann.wisestudy.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RestfulConfigDto {

    private int readTimeout;

    private int connectTimeout;

    private int maxConnTotal;

    private int maxConnPerRoute;

}
