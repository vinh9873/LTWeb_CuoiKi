package vn.ute.dto;

import lombok.Data;

@Data
public class ProductReviewDto {
    private Integer id;
    private Integer rate;
    private String comment;
}
