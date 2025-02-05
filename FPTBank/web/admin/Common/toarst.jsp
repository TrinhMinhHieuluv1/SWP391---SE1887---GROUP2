<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:if test="${not empty sessionScope.message}">
    <script type="text/javascript">
        $(document).ready(function () {
            toastr.success("${sessionScope.message}");
        });
    </script>
    <c:remove var="message" scope="session" />
</c:if>
    
    
<c:if test="${not empty sessionScope.error}">
    <script type="text/javascript">
        $(document).ready(function () {
            toastr.error("${sessionScope.error}");
        });
    </script>
    <c:remove var="error" scope="session" />
</c:if>