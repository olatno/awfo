package com.ecommerce.awf.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtils {

    private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);
    private static final int AGE_ONE_DAY = 24 * 60 * 60;
    private static final int AGE_THREE_DAYS = 3 * AGE_ONE_DAY;

    public static final int DEFAULT_COOKIE_LIFETIME = AGE_THREE_DAYS;
    public static final String DEFAULT_PATH = "/";

    private CookieUtils() {
    }

    /**
     * Create a cookie with a name, value, path and age. This will just create the cookie object and return it.
     * It will not be added to the response, if that's required, use the "addCookie" methods.
     *
     * @param name   name of the cookie
     * @param value  cookie value
     * @param path   path
     * @param maxAge maximum age to keep the cookie (auto-expire)
     * @return Created cookie object
     */
    public static Cookie createCookie(String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    /**
     * Add a cookie (based on a name, value, path and maxAge) to the response object.
     *
     * @param response Response object
     * @param name     name of the cookie
     * @param value    cookie value
     * @param path     path
     * @param maxAge   maximum age to keep the cookie (auto-expire)
     * @param secure   secure boolean value
     * @param httpOnly httpOnly boolean value
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String path, int maxAge, boolean secure, boolean httpOnly) {
        Cookie cookie = createCookie(name, value, path, maxAge);
        if (secure) {
            cookie.setSecure(true);
        }
        if(httpOnly) {
            cookie.setHttpOnly(httpOnly);
        }
        response.addCookie(cookie);
    }

    /**
     * Retrieves the string value of the cookie. If no cookie with the given name was found null will be returned.
     *
     * @param request Request object
     * @param name    name of the cookie
     * @return The value of the cookie with the given name
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), name)) {
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }

    /**
     * This will reset the cookie age, path and content to the default.
     * When age is '0', this means the cookie will be 'end of life'
     *
     * @param request  Request object
     * @param response Response object
     * @param names    names of the Cookies to be cleared
     */
    public static void clearCookies(HttpServletRequest request, HttpServletResponse response, String... names) {
        if (names != null) {
            for (String name : names) {
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie : cookies){
                    if(StringUtils.equals(cookie.getName(), name)){
                        cookie.setPath(DEFAULT_PATH);
                        cookie.setMaxAge(0);
                        cookie.setValue(null);
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }
}
