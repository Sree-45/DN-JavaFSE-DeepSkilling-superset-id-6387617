import React from 'react';

class Post {
  constructor(id, title, body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }
}

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false,
      errorMessage: ''
    };
  }

  loadPosts = () => {
    const hardcodedData = [
      {
        id: 1,
        title: "Introduction to React",
        body: "React is a JavaScript library for building user interfaces. It allows you to create reusable UI components and manage application state efficiently."
      },
      {
        id: 2,
        title: "Understanding JSX",
        body: "JSX is a syntax extension for JavaScript that allows you to write HTML-like code in your React components. It makes your components more readable and easier to write."
      },
      {
        id: 3,
        title: "State and Props in React",
        body: "State and props are fundamental concepts in React. Props are used to pass data from parent to child components, while state is used to manage data within a component."
      },
      {
        id: 4,
        title: "React Component Lifecycle",
        body: "React components have a lifecycle that includes mounting, updating, and unmounting phases. Understanding these phases helps you optimize your application performance."
      },
      {
        id: 5,
        title: "Error Boundaries in React",
        body: "Error boundaries are React components that catch JavaScript errors anywhere in their child component tree and display a fallback UI instead of crashing."
      }
    ];

    try {
      const fetchedPosts = hardcodedData.map(
        item => new Post(item.id, item.title, item.body)
      );
      this.setState({ posts: fetchedPosts });
    } catch (error) {
      this.setState({ hasError: true, errorMessage: error.message });
    }
  };

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    this.setState({ hasError: true, errorMessage: error.toString() });
  }

  render() {
    if (this.state.hasError) {
      return (
        <div style={{ color: 'red' }}>
          <h2>Something went wrong.</h2>
          <p>{this.state.errorMessage}</p>
        </div>
      );
    }

    return (
      <div>
        <h1>Blog Posts</h1>
        {this.state.posts.length === 0 ? (
          <p>Loading...</p>
        ) : (
          this.state.posts.map(post => (
            <div key={post.id}>
              <h2>{post.title}</h2>
              <p>{post.body}</p>
            </div>
          ))
        )}
      </div>
    );
  }
}

export { Posts };
