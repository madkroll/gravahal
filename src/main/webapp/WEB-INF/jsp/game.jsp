<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Grava Hal. By Maxim Nikitin</title>
</head>
<body>
<table>
    <tr>
        <td class="gravahal">${gameSession.topPlayer.pits[6]}</td>
        <c:forEach var="i" begin="0" end="5" step="1">
            <c:set var="decr" value="${5-i}"/>
            <td>
                <c:choose>
                    <c:when test="${gameSession.currentTopPlayer && gameSession.topPlayer.pits[decr] gt 0}">
                        <a href="http://localhost:8080/gravahal/turn?pitIndex=${decr}">${gameSession.topPlayer.pits[decr]}</a>
                    </c:when>
                    <c:otherwise>
                        ${gameSession.topPlayer.pits[decr]}
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>
        <td></td>
        <td>
            <c:if test="${gameSession.currentTopPlayer}">
                &lt;--
            </c:if>
        </td>
        <td>
            <c:if test="${gameSession.gameOver}">
                <c:if test="${gameSession.topPlayer.pits[6] gt gameSession.bottomPlayer.pits[6]}">
                    WINNER
                </c:if>
                <c:if test="${gameSession.topPlayer.pits[6] eq gameSession.bottomPlayer.pits[6]}">
                    FRIENDSHIP
                </c:if>
            </c:if>
        </td>
    </tr>
    <tr>
        <td></td>
        <c:forEach var="i" begin="0" end="5" step="1">
            <td>
                <c:choose>
                    <c:when test="${!gameSession.currentTopPlayer && gameSession.bottomPlayer.pits[i] gt 0}">
                        <a href="http://localhost:8080/gravahal/turn?pitIndex=${i}">${gameSession.bottomPlayer.pits[i]}</a>
                    </c:when>
                    <c:otherwise>
                        ${gameSession.bottomPlayer.pits[i]}
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>
        <td class="gravahal">${gameSession.bottomPlayer.pits[6]}</td>
        <td>
            <c:if test="${!gameSession.currentTopPlayer}">
                &lt;--
            </c:if>
        </td>
        <td>
            <c:if test="${gameSession.gameOver}">
                <c:if test="${gameSession.bottomPlayer.pits[6] gt gameSession.topPlayer.pits[6]}">
                    WINNER
                </c:if>
                <c:if test="${gameSession.topPlayer.pits[6] eq gameSession.bottomPlayer.pits[6]}">
                    FRIENDSHIP
                </c:if>
            </c:if>
        </td>
    </tr>
</table>
${error}
</body>
</html>
