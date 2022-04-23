package transport.server.impl;

import service.BlogService;
import service.common.Blog;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog selectBlogById(Integer id) {
        return Blog.builder()
                .id(id)
                .title("title")
                .author("author")
                .content("conten")
                .build();
    }

    @Override
    public Integer insertBlog(Blog blog) {
        return 1;
    }
}
