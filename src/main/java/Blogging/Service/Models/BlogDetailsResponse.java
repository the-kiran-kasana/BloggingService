package Blogging.Service.Models;

import Blogging.Service.databaseLayer.model.TagsDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDetailsResponse {
    private int blog_id;
    private String title;
    private String body;
    private List<TagsDetails> blogTags;
}
