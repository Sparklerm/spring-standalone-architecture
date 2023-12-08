package ${groupId}.common.utils.captcha.generator;

import cn.hutool.captcha.generator.AbstractGenerator;
import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 混合字符串验证码生成器
 *
 * @author Alex Meng
 * @createDate 2023-10-08 12:37
 */
public class MixStrGenerator extends AbstractGenerator {

    private static final long serialVersionUID = 1L;

    public MixStrGenerator(int count) {
        super(count);
    }

    @Override
    public String generate() {
        return RandomUtil.randomString(this.length);
    }

    @Override
    public boolean verify(String code, String userInputCode) {
        if (StringUtils.isNotBlank(userInputCode)) {
            return StringUtils.equalsIgnoreCase(code, userInputCode);
        }
        return false;
    }
}
