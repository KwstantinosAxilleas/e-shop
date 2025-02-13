<h1>E-commerce REST API README</h1>

<h2>Overview</h2>
<p>This project is a <strong>CRUD-based REST API</strong> designed for an e-commerce platform. It provides essential functionalities such as <strong>product management, user authentication, and shopping cart operations</strong>. The API follows <strong>RESTful principles</strong> and returns JSON responses for seamless frontend integration.</p>

<h2>API Documentation</h2>
<p>The API is documented and tested using <strong>Postman & Swagger UI</strong>, allowing developers to explore available endpoints, send requests, and review responses effortlessly.</p>

<h2>Preview of Swagger UI</h2>
<p>Below are previews of the <strong>Swagger UI</strong>, showcasing the well-structured and interactive API documentation.</p>

<h3>Swagger UI View:</h3>
<img src="./assets/swaggerUI.png" alt="Swagger UI Screenshot"/>

<h2>Key Features</h2>

<h3>User Features:</h3>
<ul>
    <li><strong>Account Management:</strong> Secure registration, login, and authentication using <strong>JWT tokens</strong>.</li>
    <li><strong>Product Browsing:</strong> Retrieve product details, including categories, prices, and stock availability.</li>
    <li><strong>Shopping Cart:</strong> Add, update, and remove products from the cart.</li>
    <li><strong>Order Management:</strong> Place orders and view order history.</li>
</ul>

<h3>Admin Features:</h3>
<ul>
    <li><strong>Product Management:</strong> Create, update, and delete product listings.</li>
    <li><strong>User Management:</strong> View registered users and manage user accounts.</li>
    <li><strong>Order Processing:</strong> View and update order statuses.</li>
</ul>

<h2>Technology Stack</h2>

<h3>Back-End</h3>
<ul>
    <li><strong>Java & Spring Boot:</strong> A robust backend framework for handling API requests and business logic.</li>
    <li><strong>H2 Database:</strong> A lightweight, in-memory database for temporary data storage.</li>
    <li><strong>JWT Authentication:</strong> Custom <strong>JSON Web Token (JWT)</strong> implementation for secure authentication.</li>
</ul>

<h3>API Documentation & Testing</h3>
<ul>
    <li><strong>Swagger UI:</strong> Interactive API documentation for testing and debugging.</li>
</ul>

<h2>Getting Started</h2>

<h3>Run the Application</h3>
<ol>
    <li><strong>Clone the repository</strong></li>
    <pre><code>git clone https://github.com/your-repo/ecommerce-api.git
cd ecommerce-api
    </code></pre>

    <li><strong>Build and run the application</strong></li>
    <pre><code>mvn spring-boot:run</code></pre>

    <li><strong>Access Swagger UI</strong></li>
    <p>Once the application is running, open your browser and visit:</p>
    <pre><code>http://localhost:8080/swagger-ui.html</code></pre>
</ol>

<p>This <strong>REST API</strong> offers a powerful backend solution for e-commerce platforms, ensuring efficient product and user management with an intuitive <strong>Swagger UI</strong> for easy testing and integration.</p>
