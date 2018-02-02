package cn.aldd.vape.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class CarouselFigureVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "存储路径")
	private String url;
	@ApiModelProperty(value = "排序")
	private Integer sort;
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date createTime;

// setter and getter
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	public Integer getSort(){
		return sort;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public java.util.Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	
}