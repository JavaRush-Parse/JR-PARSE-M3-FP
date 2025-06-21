package com.java.servelets;

import com.java.classes.GameSession;
import com.java.classes.QuestNode;
import com.java.classes.QuestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServletTest {
    GameServlet servlet = new GameServlet();
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpServletResponse resp = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    GameSession gameSession = mock(GameSession.class);
    QuestRepository questRepository = mock(QuestRepository.class);
    QuestNode questNode = mock(QuestNode.class);





    @Test
    void testQuestNodeNotFound_ShowsError() throws Exception {
        when(req.getSession(true)).thenReturn(session);
        when(session.getAttribute("gameSession")).thenReturn(new GameSession("Alex", 999, 3));
        when(questRepository.getNode(999)).thenReturn(null);
        when(resp.getWriter()).thenReturn(new PrintWriter(System.out));
        servlet.questRepository = questRepository;
        servlet.doGet(req, resp);
        verify(resp).getWriter();
    }

    @Test
    void testIsLosingNode_ShouldReturnFalse() throws Exception {
        when(questNode.isLosingNode()).thenReturn(false);
        when(req.getSession(true)).thenReturn(session);
        when(session.getAttribute("gameSession")).thenReturn(new GameSession("Alex", 1, 3));
        when(questRepository.getNode(1)).thenReturn(questNode);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/views/game.jsp")).thenReturn(requestDispatcher);
        servlet.questRepository = questRepository;
        servlet.doGet(req, resp);
        verify(requestDispatcher).forward(req, resp);
    }



    @Test
    void testHelloServerDoPost() throws IOException {
        when(req.getSession()).thenReturn(session);
        when(req.getSession().getAttribute("gameSession")).thenReturn(gameSession);
        when(req.getParameter("choiceId")).thenReturn("5");
        when(req.getContextPath()).thenReturn("/Web");

        servlet.doPost(req,resp);
        verify(gameSession).setCurrentNodeId(5);
        verify(resp).sendRedirect("/Web/game");
    }

    @Test
    void testCheckOrNoEmpty() throws IOException{
        when(req.getParameter("choiceId")).thenReturn("abc");
        when(req.getContextPath()).thenReturn("/Web");
        servlet.doPost(req,resp);
        verify(resp).sendRedirect("/Web/game");
    }

    @Test
    void testCheckOrNull() throws IOException{
        when(req.getParameter("choiceId")).thenReturn(null);
        when(req.getContextPath()).thenReturn("/Web");
        servlet.doPost(req,resp);
        verify(resp).sendRedirect("/Web/game");
    }
}