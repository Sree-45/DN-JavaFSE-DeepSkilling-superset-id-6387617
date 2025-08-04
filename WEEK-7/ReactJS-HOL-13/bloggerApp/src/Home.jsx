import React from "react";
import "./Home.css";

function Home() {
    const books = [
        {id:  101, name:"Master React", price:670},
        {id:  102, name:"Master spring", price:800},
        {id:  103, name:"Master sql", price:900},
    ]
    const course = [
        {id:11, name:"Angular", date:"31/07/25"},
        {id:12, name:"React", date:"1/08/25"},
    ]
    const blog = [
        {id: 1, title: "React Learning", author: "steaphen", content:"welcome to learning react"},
        {id: 2, title: "Installation", author: "fleming", content:"react installation"},

    ]
    return (
        <div className="container">
            <div className="section">
                <h2>Course Details</h2>
                <ul>
                    {course.map((course) => (
                        <div key={course.id} className="item">
                            <h3>{course.name}</h3>
                            <h4>{course.date}</h4>
                        </div>
                    ))}
                </ul>
            </div>

            <div className="section">
                <h2>Book Details</h2>
                <ul>
                    {books.map((book) => (
                        <div key={book.id} className="item">
                            <h3>{book.name}</h3>
                            <h4>{book.price}</h4>
                        </div>
                    ))}
                </ul>
            </div>

            <div className="section">
                <h2>Blog Details</h2>
                <ul>
                    {blog.map((blog) => (
                        <div key={blog.id} className="item">
                            <h3>{blog.title}</h3>
                            <h4>{blog.author}</h4>
                            <h4>{blog.content}</h4>
                        </div>
                    ))}
                </ul>
            </div>
        </div>
    )
}

export default Home;