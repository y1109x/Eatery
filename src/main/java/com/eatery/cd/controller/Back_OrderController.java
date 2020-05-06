package com.eatery.cd.controller;

import com.eatery.cd.pojo.Evaluate;
import com.eatery.cd.pojo.Order;
import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.User;
import com.eatery.cd.service.EvaService;
import com.eatery.cd.service.OrderService;
import com.eatery.cd.service.UserService;
import com.eatery.cd.tools.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 后台订单列表
 */
@Controller
public class Back_OrderController {

    @Autowired()
    private OrderService orderService;
    @Autowired()
    private UserService userService;
    @Autowired
    private EvaService evaService;

    /**
     * 跳转到订单详情页面--并展示订单详情
     * @return
     */
    @RequestMapping("/orderForm")
    public String orderForm(Model model) {
        List<Order> orderList = orderService.findOrderAll();

        for (int i = 0; i < orderList.size(); i++) {
            int count = 0 ;
            double price = 0 ;
            Order order = orderList.get(i);
            List<Product> product = order.getProduct();
            for (Product p:
            product) {
                order.setProductName(order.getProductName()+p.getProductName()+",");
                count += p.getBuynum();
                price += p.getPrice() * p.getBuynum();
            }
            order.setPrice(price);
            order.setCount(count);
            order.setProductName(order.getProductName().substring(0,order.getProductName().length()-1));
        }
        model.addAttribute("orderList",orderList);

        return "/admin/Orderform";
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder(String orderId){
        orderService.deleteOrder(orderId);
        return "redirect:/orderForm";
    }
    /**
     * 修改后台订单状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    @RequestMapping("/updateAdminOrderStatus")
    public String updateAdminOrderStatus(String orderId,String orderStatus){
        orderService.updateOrderStatus(orderStatus,orderId);
        return "redirect:/orderForm";
    }

    /**
     * 跳转到回复评论界面
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("/toAnsEva")
    public String toAnsEva(String orderId,Model model){
        Evaluate evaluate = evaService.findEvaListByOrderId(orderId);
        model.addAttribute("evaluate",evaluate);
        return "/admin/adminAnsEva";
    }
    @RequestMapping("/ansEva")
    public String ansEva(String orderId,String ansContent,String orderStatus){
        evaService.saveAnsEva(orderId,ansContent);
        orderService.updateOrderStatus(orderStatus,orderId);
        return "redirect:/orderForm";
    }
    //跳转到订单详情页面
    @RequestMapping("/orderDetailed")
    public String orderDetailed(String orderId,Model model){
        Order order =  orderService.findListByOrderId(orderId);
        User user = userService.findUserInfo(order.getUserId());

        order.setUser(user);

        model.addAttribute("order",order);

        return "/admin/order_detailed";
    }



    //根据订单id查询对应的数据
    @RequestMapping("/searchOrderList")
    public String searchOrderList(String orderId,Model model){
        Order order =  orderService.findListByOrderId(orderId);
        if (order == null) {
            return "redirect:/orderForm" ;
        }
        List<Order> orderList = new ArrayList<Order>();
        //将order封装进一个集合，方便页面中遍历
        orderList.add(order);
        model.addAttribute("orderList",orderList);
        return "/admin/Orderform";
    }


    //poi
    @RequestMapping("/stamp")
    public String toStamp(){
        List<Order> orderList = orderService.findOrderAll();
        //int count = orderService.findOrderTotal();
        try {
            VerifyCode.writeToDisk(orderList,4,"订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/orderForm";
    }



}
