package org.wallstreetWolf.orderservice.service.serviceImple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallstreetWolf.common.mapper.orderMapper;
import org.wallstreetWolf.common.pojo.order;
import org.wallstreetWolf.orderservice.service.*;

import java.util.Collections;
import java.util.List;

@Service
public class orderServiceImple extends ServiceImpl<orderMapper, order> implements orderService{

}
