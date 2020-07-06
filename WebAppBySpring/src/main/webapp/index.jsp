<html>
<body>
<h2>index.jsp Page</h2>
<ul>
    <li><a href="${pageContext.request.contextPath}/hello-world">Hello World Page from Hello Controller</a></li>
    <li><a href="${pageContext.request.contextPath}/first/hello">hello.html page from First Controller</a></li>
    <li><a href="${pageContext.request.contextPath}/first/goodbye">goodbye.html page from First Controller</a></li>
    <li><a href="${pageContext.request.contextPath}/exit">exit.html page from Second Controller</a></li>
    <li><a href="${pageContext.request.contextPath}/getParamByServletReq">getParamByServletReq.html page from Third..
        Controller</a></li>
    <li><a href="${pageContext.request.contextPath}/getParamByAnnotationReq">getParamByAnnotationReq.html page from
        Third.. Controller</a></li>
    <li><a href="${pageContext.request.contextPath}/dataFromModelToView">dataFromModelToView.html page from Forth
        Controller</a></li>
    <li><a href="${pageContext.request.contextPath}/calculator/calculate">calculate.html page from Second Controller</a></li>
</ul>
</body>
</html>