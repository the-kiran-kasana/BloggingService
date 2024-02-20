/**
 * Fetch the all childs of blog
 */
function fetchBlogs() {
    document.getElementById("blogdetails").innerHTML = "";

    var search = document.getElementById("searchInput").value;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/blogs/tag?tag_name=' + encodeURIComponent(search), true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    const data = JSON.parse(xhr.responseText);
                    if (data && data.length > 0) {
                        displayBlogs(data);
                    } else {
                        document.getElementById("blogdetails").innerHTML = "<p>No blogs found.</p>";
                    }
                } catch (error) {
                    console.error("Error parsing JSON: ", error);
                }
            } else {
                console.error("Error fetching blogs. Status code: ", xhr.status);
            }
        }
    };

    xhr.send();
}

function displayBlogs(data) {
    var blogslist = "";
    for (let i = 0; i < data.length; i++) {
        blogslist += getDetails(data[i].title, data[i].body, data[i].blogTags);
    }
    document.getElementById("blogdetails").innerHTML = blogslist;
}

function getDetails(title, body, tagsList) {
    return `<div class="blog-card">
                <h1 class="blog-title">${title}</h1>
                <p class="blog-body">${body}</p>
                <div class="blog-tags">${getTagsCode(tagsList)}</div>
            </div>`;
}

function getTagsCode(tagsList) {
    tags = "";
    tagsList.forEach(tag => {
        tags += `<div class="tag">${tag.tag_name}</div>`;
    })
    return tags;
}













    // function AddBlogs()
    // {
    //     location.replace("http://localhost:8080/add_API");  
    // }

    /**
     * add new blogs from UI
     */
    function SubmitbBlogsDetails()
    {
      var title=document.getElementById("Title").value;
      var body=document.getElementById("bodyofblog").value;
      var tag_name=document.getElementById("tag").value;
      var parent=document.getElementById("parenttag").value;

      if(title==="" || body==="" || tag_name==="")
      {
        alert("please fill the details");
      }else
      {

      const blogs = { title,body};
      const tags={tag_name,parent};

      //insert details in blog table

      const xhr = new XMLHttpRequest();
      xhr.open("POST", "http://localhost:8080/add_blogs_details", true);
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.onreadystatechange = function () {
        var Blogsresponse = this.responseText;
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            alert(Blogsresponse);
      }};
      blogbody = JSON.stringify(blogs);
      xhr.send(blogbody);



      //insert details in tag table

      const xhhr = new XMLHttpRequest();
      xhhr.open("POST", "http://localhost:8080/add_tags_details", true);
      xhhr.setRequestHeader("Content-Type", "application/json");
      xhhr.onreadystatechange = function () {
        var Tagsresponse = this.responseText;
        if (xhhr.readyState === XMLHttpRequest.DONE && xhhr.status === 200) {
            alert(Tagsresponse);
      }};
      tagbody = JSON.stringify(tags);
      xhhr.send(tagbody);
    }




}