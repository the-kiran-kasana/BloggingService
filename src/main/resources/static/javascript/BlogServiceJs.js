/**
 * Fetch the all childs of blog
 */



    function fetchBlogs() {
        var xhr = new XMLHttpRequest();
        var search = document.getElementById("searchInput").value;
        xhr.open('GET', 'http://localhost:8080/byTag?tag_name=' + search, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                console.log(xhr.responseText);
                const data = JSON.parse(xhr.responseText);
                if (data) {
                    var blogslist = "";
                    for (let i in data) {
                        blogslist += getDetails(data[i].title, data[i].body);
                    
                    }
                    document.getElementById("blogdetails").innerHTML = blogslist;
                }
            }
        };
        xhr.send();
    }

    function getDetails(title, body) {
        return "<div style=\"height:120px;border: 2px aliceblue solid;\">" +
            "<h3 style=\"color:blue;\">Title : " + title + "</h3>" +
            "<h4>Body : " + body + "</h4>" +
            "</div>";
    }



    function AddBlogs()
    {
        location.replace("http://localhost:8080/add_API");  
    }
      
    


    /**
     * add new blogs from UI
     */
    function SubmitbBlogsDetails()
    {
      var title=document.getElementById("Title").value;
      var body=document.getElementById("bodyofblog").value;
      var tag=document.getElementById("tag").value;
      var parent=document.getElementById("parenttag").value;

      if(title==="" || body==="" || tag==="")
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