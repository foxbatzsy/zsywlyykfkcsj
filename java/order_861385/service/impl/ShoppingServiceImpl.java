package order_861385.service.impl;

import order_861385.entity.Shopping;
import order_861385.dao.ShoppingMapper;
import order_861385.service.IShoppingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Service
public class ShoppingServiceImpl extends ServiceImpl<ShoppingMapper, Shopping> implements IShoppingService {

}
