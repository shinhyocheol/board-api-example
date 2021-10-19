package kr.co.platform.util.validation;

import kr.co.platform.exception.custom.ApiOtherException;
import kr.co.platform.exception.custom.UserNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Assert {

    private Assert() {}

    public static void memberNotNull(Object object, String msg) {
        if (validation(object))
            throw new UserNotFoundException(msg);
    }

    public static void resultNotNull(Object object, String msg) {
        if (validation(object))
            throw new ApiOtherException(msg);
    }

    private static boolean validation(Object obj) {

        if(Objects.isNull(obj)) {
            return true;
        }

        if("null".equals(obj)) {
            return true;
        }

        if(obj instanceof Long && (Long)obj == 0) {
            return true;
        }

        if(obj instanceof Integer && (Integer)obj == 0) {
            return true;
        }

        if((obj instanceof String) && (((String)obj).trim().length() == 0)) {
            return true;
        }

        if(obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if(obj instanceof Map) {
            return ((Map<?, ?>)obj).isEmpty();
        }

        if(obj instanceof List) {
            return ((List<?>)obj).isEmpty();
        }

        if(obj instanceof Object[]) {
            return (((Object[])obj).length == 0);
        }

        return false;
    }
}
