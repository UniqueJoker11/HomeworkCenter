package colin.web.homework.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/10/9.
 */
public class BeanOperateUtils {
    /**
     *
     * 方法描述：对象转换工具类
     * 注意事项：
     * @param sourceList
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @Exception 异常对象
     */
    public static <S,T> List<T> transferObj(List<S> sourceList,Class<T> target) throws NoSuchFieldException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<T> targetList=new ArrayList<T>();
        Method[] setMethods=target.getDeclaredMethods();
        for(S source:sourceList){
            T t=target.newInstance();
            for(Method setMethod:setMethods){
                setMethod.setAccessible(true);
                if(setMethod.getName().startsWith("set")){
                    String fieldName=setMethod.getName().substring(3);
                    Field filed=target.getDeclaredField(fieldName.toLowerCase());
                    Method getMethod=source.getClass().getDeclaredMethod("get"+fieldName)==null?source.getClass().getDeclaredMethod("is"+fieldName):source.getClass().getDeclaredMethod("get"+fieldName);
                    setMethod.invoke(t,getMethod.invoke(source));
                }
            }
            targetList.add(t);
        }
        return targetList;
    }
}
