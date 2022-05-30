package order_861385.service.impl;

import order_861385.entity.Log;
import order_861385.dao.LogMapper;
import order_861385.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import order_861385.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 浏览日志 服务实现类
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Override
    public List<DateUtil> num() {
        return baseMapper.num();
    }
}
