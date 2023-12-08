package ${groupId}.common.utils.encrypt;

/**
 * @author Alex Meng
 * @createDate 2023/1/4
 */
public class EncryptConstant {
    /**
     * 密钥长度不足补充字符
     */
    public static final char FILL_CHAR = '0';

    /**
     * AES 密钥长度
     */
    public static final int ASE_SECRET_LENGTH = 16;

    /**
     * DES 密钥长度
     */
    public static final int DES_SECRET_LENGTH = 8;
}
