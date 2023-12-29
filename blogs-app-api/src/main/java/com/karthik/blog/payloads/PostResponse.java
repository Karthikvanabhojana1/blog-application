package com.karthik.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
private List<PostDTO> content;
private int pageNumber;
private int pagesize;
private int totalPages;
private long totalElements;
private boolean lastpage;
}
