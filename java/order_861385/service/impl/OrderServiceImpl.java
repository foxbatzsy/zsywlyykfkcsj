package order_861385.service.impl;

import order_861385.entity.Order;
import order_861385.dao.OrderMapper;
import order_861385.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import order_861385.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    public List<DateUtil> num(){
        return baseMapper.num();
    };
}
