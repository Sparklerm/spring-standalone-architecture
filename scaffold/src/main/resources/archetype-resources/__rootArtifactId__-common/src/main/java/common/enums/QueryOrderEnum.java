package ${groupId}.common.enums;

import lombok.Getter;

/**
 * 排序枚举
 *
 * @author Alex Meng
 * @createDate 2023-10-15 0015 下午 01:32
 */
@Getter
public enum QueryOrderEnum {
    ASC("ASC"),
    DESC("DESC");
    private final String value;

    QueryOrderEnum(String value) {
        this.value = value;
    }

}
