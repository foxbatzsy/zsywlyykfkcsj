package order_861385.service;

import order_861385.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;
import order_861385.utils.DateUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 浏览日志 服务类
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
public interface ILogService extends IService<Log> {

    List<DateUtil> num();
}
