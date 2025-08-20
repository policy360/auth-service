package com.policy360.auth.aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.policy360.auth.audit.AuditLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuditLoggingAspect {

    private final com.policy360.auth.repository.AuditLogRepository auditLogRepository;
    private final ObjectMapper objectMapper;

    @Around("execution(* com.policy360.auth.service.*.*(..))")
    public Object logAudit(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String username = "USER";
        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                username = authentication.getName();
            }
        } catch (Exception ignored) {}

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
        String params = maskSensitiveData(objectMapper.writeValueAsString(joinPoint.getArgs()));

        log.info("[AOP] User: {} | Action: {} | Params: {}", username, methodName, params);

        Object result = joinPoint.proceed(); // execute target method

        long executionTime = System.currentTimeMillis() - startTime;

        // Save to DB
        auditLogRepository.save(AuditLog.builder()
                .username(username)
                .action(methodName)
                .method(methodName)
                .parameters(params)
                .timestamp(LocalDateTime.now())
                .executionTimeMs(executionTime)
                .build()
        );

        log.info("[AOP] Executed {} in {} ms", methodName, executionTime);

        return result;
    }

    private String maskSensitiveData(String json) {
        return json
                .replaceAll("(?i)\"password\":\".*?\"", "\"password\":\"****\"")
                .replaceAll("(?i)\"secret\":\".*?\"", "\"secret\":\"****\"");
    }
}
