package ${groupId}.security.component;

import com.alibaba.ttl.TransmittableThreadLocal;
import ${groupId}.service.auth.model.AuthUserDetails;
import lombok.Getter;

/**
 * 当前会话用户信息保存
 */
public class AuthContextHolder {
    private final TransmittableThreadLocal<AuthUserDetails> threadLocal = new TransmittableThreadLocal<>();

    @Getter
    private static final AuthContextHolder instance = new AuthContextHolder();

    private AuthContextHolder() {
    }

    public void setContext(AuthUserDetails t) {
        this.threadLocal.set(t);
    }

    public AuthUserDetails getContext() {
        return this.threadLocal.get();
    }

    public void clear() {
        this.threadLocal.remove();
    }
}