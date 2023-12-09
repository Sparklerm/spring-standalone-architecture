package ${groupId}.controller;

import ${groupId}.common.model.result.Result;
import ${groupId}.model.dto.ResourceCategoryDTO;
import ${groupId}.service.IResourceCategoryService;
import ${groupId}.model.request.CreateResourceCategoryRequest;
import ${groupId}.model.request.UpdateResourceCategoryRequest;
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
 * @createDate 2023-11-21 04:52
 */
@RestController
@RequestMapping("/resourceCategory")
@Api(tags = "后台资源分类API")
public class ResourceCategoryController {

    @Resource
    private IResourceCategoryService resourceCategoryService;

    @GetMapping("/listAll")
    @ApiOperation("查询所有后台资源分类")
    public Result<List<ResourceCategoryDTO>> listAll() {
        return Result.success(resourceCategoryService.listAll());
    }

    @PostMapping("/create")
    @ApiOperation("添加后台资源分类")
    public Result<String> create(@Valid @RequestBody CreateResourceCategoryRequest request) {
        Integer i = resourceCategoryService.create(request.getName());
        return Result.createSuccess(i);
    }

    @PostMapping("/update")
    @ApiOperation("修改后台资源分类")
    public Result<String> update(@Valid @RequestBody UpdateResourceCategoryRequest request) {
        Integer update = resourceCategoryService.update(request.getId(), request.getName());
        return Result.updateSuccess(update);
    }

    @GetMapping("/delete/{id}")
    @ApiOperation("根据ID删除后台资源分类")
    public Result<String> delete(@PathVariable("id") Long id) {
        Integer delete = resourceCategoryService.deleteById(id);
        return Result.deleteSuccess(delete);
    }

}
