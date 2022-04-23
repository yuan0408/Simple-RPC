package service;

import service.common.Blog;

public interface BlogService {

    Blog selectBlogById(Integer id);

    Integer insertBlog(Blog blog);
}
