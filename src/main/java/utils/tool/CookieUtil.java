package utils.tool;

import com.google.common.base.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.google.common.base.Strings.isNullOrEmpty;

public final class CookieUtil {
    private String domain;
    private String path;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieUtil(HttpServletRequest request, HttpServletResponse response, String domain, String path) {
        this.request = request;
        this.response = response;
        this.domain = domain;
        this.path = path;
    }

    public void setCookie(final String key, String value, final int expiry) {
        try {
            value = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(key, value);
        if (!isNullOrEmpty(domain)) {
            cookie.setDomain(domain);
        }
        if (!isNullOrEmpty(path)) {
            cookie.setPath(path);
        }
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }

    public String getCookie(final String key) {
        Optional<Cookie[]> cookies = Optional.fromNullable(request.getCookies());
        if (cookies.isPresent()) {
            for (int i = 0, length = cookies.get().length; i < length; i++) {
                Cookie cookie = cookies.get()[i];
                if (key.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public void deleteCookie(final String key) {
        Optional<Cookie[]> cookies = Optional.fromNullable(request.getCookies());
        if (cookies.isPresent()) {
            for (int i = 0, length = cookies.get().length; i < length; i++) {
                Cookie cookie = cookies.get()[i];
                if (key.equals(cookie.getName())) {
                    Cookie newCookie = new Cookie(key, null);
                    if (!isNullOrEmpty(domain)) {
                        newCookie.setDomain(domain);
                    }
                    if (!isNullOrEmpty(path)) {
                        newCookie.setPath(path);
                    }
                    newCookie.setMaxAge(-1);
                    response.addCookie(newCookie);
                }
            }
        }
    }
}
