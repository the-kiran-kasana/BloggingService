//  // Sample data - replace this with actual data from your backend
//  const tagsData = {
//     "Science": ["Physics", "Chemistry"],
//     "Chemistry": ["Organic", "Inorganic", "Physical"],
//     "Physics": ["Statics", "Dynamics"],
//     // Add more tags and their hierarchy as needed
// };


// function search() {
//     const searchInput = document.getElementById('searchInput').value.trim();
//     const resultDiv = document.getElementById('result');
//     resultDiv.innerHTML = ""; // Clear previous results

//     // Check if the search input is a valid tag
//     if (tagsData.hasOwnProperty(searchInput)) {
//         // Fetch blogs for the searched tag and its child tags
//         const blogs = getBlogsForTagAndChildren(searchInput);
//         // Display the results
//         displayResults(blogs);
//     } else {
//         resultDiv.innerHTML = "<p>No results found.</p>";
//     }
// }

// function getBlogsForTagAndChildren(tag) {
//     let blogs = [];
//     if (tagsData.hasOwnProperty(tag)) {
//         // Include blogs for the current tag
//         blogs = blogs.concat(getBlogsForTag(tag));

//         // Include blogs for child tags recursively
//         for (const childTag of tagsData[tag]) {
//             blogs = blogs.concat(getBlogsForTagAndChildren(childTag));
//         }
//     }
//     return blogs;
// }

// // Dummy function to simulate fetching blogs for a tag from the backend
// function getBlogsForTag(tag) {
//     // Replace this with actual backend API call to fetch blogs
//     // For now, return a dummy array of blogs
//     return ["Blog 1 for " + tag, "Blog 2 for " + tag, "Blog 3 for " + tag];
// }

// function displayResults(blogs) {
//     const resultDiv = document.getElementById('result');
//     if (blogs.length > 0) {
//         const ul = document.createElement('ul');
//         for (const blog of blogs) {
//             const li = document.createElement('li');
//             li.textContent = blog;
//             ul.appendChild(li);
//         }
//         resultDiv.appendChild(ul);
//     } else {
//         resultDiv.innerHTML = "<p>No blogs found for the selected tag.</p>";
//     }
// }














        function fetchBlogs() {
     
                var xhr = new XMLHttpRequest();
                var search=document.getElementById("searchInput").value;// Replace with the actual value you want to pass
                xhr.open('GET', 'http://localhost:8080/byTag?tag_name=' + search, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        console.log(xhr.responseText);
                    }
                };
                xhr.send();
        }


        function displayBlogs(blogs) {
            const blogContainer = document.getElementById('blogContainer');
            blogContainer.innerHTML = '';

            blogs.forEach(blog => {
                const blogElement = document.createElement('div');
                blogElement.innerHTML = `<h3>${blog.title}</h3><p>${blog.body}</p>`;
                blogContainer.appendChild(blogElement);
            });
        }