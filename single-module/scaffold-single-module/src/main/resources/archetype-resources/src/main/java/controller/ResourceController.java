package ${groupId}.controller;

import ${groupId}.common.exception.BizAssert;
import ${groupId}.common.model.result.Result;
import ${groupId}.common.utils.BeanCopierUtils;
import ${groupId}.config.security.component.DynamicSecurityMetadataSource;
import ${groupId}.model.dto.ResourceDTO;
import ${groupId}.service.IResourceService;
import ${groupId}.model.request.CreateResourceRequest;
import ${groupId}.model.request.ResourcePageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 03:11
 */
@RestController
@RequestMapping("/resource")
@Api(tags = "后端接口权限API")
public class ResourceController {

    @Resource
    private IResourceService resourceService;
    @Resource
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @PostMapping("/create")
    @ApiOperation(value = "创建资源", notes = "创建资源")
    public Result<String> create(@Valid @RequestBody CreateResourceRequest request) {
        resourceService.create(BeanCopierUtils.copyProperties(request, ResourceDTO.class));
        dynamicSecurityMetadataSource.clearDataSource();
        return Result.success("资源创建成功");
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新资源信息", notes = "更新资源信息")
    public Result<String> update(@RequestBody ResourceDTO request) {
        BizAssert.notNull(request.getId(), "资源Id不能为空");
        resourceService.update(request);
        dynamicSecurityMetadataSource.clearDataSource();
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("根据Id删除资源")
    public Result<String> delete(@PathVariable("id") Long id) {
        resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据Id获取资源详情")
    public Result<ResourceDTO> detail(@PathVariable("id") Long id) {
        return Result.success(resourceService.getItem(id));
    }

    @GetMapping("/list")
    @ApiOperation("分页查询资源")
    public Result<List<ResourceDTO>> list(ResourcePageRequest request) {
        return Result.success(resourceService.list(request.getCategoryId(), request.getName(), request.getUrl(), request.getPageSize(), request.getPageNum()));
    }

    @PostMapping("/listAll")
    @ApiOperation("查询全部资源")
    public Result<List<ResourceDTO>> listAll() {
        return Result.success(resourceService.listAll());
    }
}
