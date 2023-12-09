package ${groupId}.common.model.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import ${groupId}.common.enums.BizCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据统一返回
 *
 * @author Alex Meng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PageResult<T extends Serializable> extends BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private PageResultRecord<T> data;

    @Data
    public static class PageResultRecord<T extends Serializable> implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long current;

        private Long pageSize;

        private Long total;

        private List<T> records;
    }

    public static <T extends Serializable> PageResult<T> success(IPage<T> result) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(BizCodeEnum.SUCCESS.getCode());
        pageResult.setMessage(BizCodeEnum.SUCCESS.getMessage());
        PageResultRecord<T> data = new PageResultRecord<>();
        data.setCurrent(result.getCurrent());
        data.setPageSize(result.getSize());
        data.setTotal(ObjectUtils.isNotEmpty(result.getTotal()) ? result.getTotal() : 0L);
        data.setRecords(result.getRecords());
        pageResult.setData(data);
        return pageResult;
    }

    public static <T extends Serializable> PageResult<T> success(Long total, List<T> records) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(BizCodeEnum.SUCCESS.getCode());
        pageResult.setMessage(BizCodeEnum.SUCCESS.getMessage());
        PageResultRecord<T> data = new PageResultRecord<>();
        data.setTotal(total);
        data.setRecords(records);
        pageResult.setData(data);
        return pageResult;
    }

}

