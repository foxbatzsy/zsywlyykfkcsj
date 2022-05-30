package order_861385.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import order_861385.entity.User;
import order_861385.dao.UserMapper;
import order_861385.service.IUserService;
import order_861385.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<DateUtil> num() {
        return baseMapper.num();
    }
}
