<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="not hasRole('USER')">
  <%@ include file="publicBook.jsp"%>
</sec:authorize>
<sec:authorize access="hasRole('USER')">
  <%@ include file="internalBook.jsp"%>
</sec:authorize>
