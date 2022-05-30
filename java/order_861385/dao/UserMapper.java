package order_861385.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import order_861385.entity.User;
import order_861385.utils.DateUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT DATE_FORMAT(o.c_time, '%Y-%m-%d %H:%i') AS x, count(*) AS y\n" +
            "FROM user o\n" +
            "GROUP BY x\n" +
            "ORDER BY x;")
    @Results(value = {
            @Result(property = "x" ,column = "x"),
            @Result(property = "y",column = "y")
    })
    List<DateUtil> num();
}
