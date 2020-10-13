package com.lipeng.common.aop;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.SqlUtil;
import com.lipeng.common.mybatis.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PageAspect {

    @Around("@annotation(com.lipeng.common.aop.PageAble) && @annotation(pageAble)")
    public Object doPage(ProceedingJoinPoint pjp, PageAble pageAble) throws Throwable {
        Object[] args = pjp.getArgs();
        PageParam pageParam = null;
        for (Object arg : args) {
            if (arg.getClass().getSimpleName().equals(pageAble.clazzName())) {
                pageParam = (PageParam) arg;
                break;
            }
        }
        if (null == pageParam || !pageParam.getIsCut()) {
            log.error("PageAspect.doPage，分页请求参数类型为非PageParam,或isCut=false！");
            return pjp.proceed();
        }

        Page page = PageHelper.startPage(pageParam.getCurPage(), pageParam.getPageSize());
        Object rtnObj = pjp.proceed();
        pageParam.setTotalCount((int) page.getTotal());
        pageParam.setTotalPage(page.getPages());
        SqlUtil.clearLocalPage();
        return rtnObj;
    }

}