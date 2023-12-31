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
            "<h3 style=\"color:blue;\">" + title + "</h3>" +
            "<h4>" + body + "</h4>" +
            "</div>";
    }



        