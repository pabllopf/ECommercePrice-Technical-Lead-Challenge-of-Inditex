package dev.pabllopf.ecommerceprice.application.filters;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.security.Keys;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.*;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

    import java.io.IOException;
    import java.security.Key;
    import java.util.Base64;
    import java.util.Collections;

    import static org.junit.jupiter.api.Assertions.assertNull;
    import static org.mockito.Mockito.*;

    public class JWTAuthenticationFilterTest {

        @InjectMocks
        private JWTAuthenticationFilter jwtAuthenticationFilter;

        @Mock
        private HttpServletRequest request;

        @Mock
        private HttpServletResponse response;

        private FilterChain filterChain;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            filterChain = mock(FilterChain.class);
        }


        @Test
        public void testDoFilterInternalWithoutAuthHeader() throws ServletException, IOException {
            when(request.getHeader("Authorization")).thenReturn(null);

            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

            verify(filterChain).doFilter(request, response);
            assertNull(SecurityContextHolder.getContext().getAuthentication());
        }
    }