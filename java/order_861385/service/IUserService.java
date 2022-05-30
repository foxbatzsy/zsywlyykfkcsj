package order_861385.service;

import com.baomidou.mybatisplus.extension.service.IService;
import order_861385.entity.User;
import order_861385.utils.DateUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
public interface IUserService extends IService<User> {

    List<DateUtil> num();
}
