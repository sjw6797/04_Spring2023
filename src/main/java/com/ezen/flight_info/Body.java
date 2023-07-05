
package com.ezen.flight_info;

import javax.annotation.Generated;

import lombok.Data;

@Generated("jsonschema2pojo")
@Data
public class Body {
	//json파일의 <body></body> 부분
    private Items items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;	// result 레코드 갯수 

}
