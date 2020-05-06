package com.eatery.cd.controller;

import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.User;
import com.eatery.cd.service.OrderService;
import com.eatery.cd.service.ProductService;
import com.eatery.cd.service.UserService;
import com.eatery.cd.tools.PaymentUtil;
import com.eatery.cd.tools.PropUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    //跳转到cart.jsp页面
    @RequestMapping("/toCart")
    public String toDetail(HttpSession session, @RequestParam("productId") String[] productsId, String buyNum, Model model) {
        System.out.println("111111111111111:" + productsId[0]);
        Integer buyCount = Integer.parseInt(buyNum);
        List<Product> productList = productService.findProductsById(productsId);
        Map<Product, Integer> cartMap = (Map<Product, Integer>) session.getAttribute("cart");
        for (Product product : productList) {
            cartMap.put(product, buyCount);
        }
        session.setAttribute("cart", cartMap);
        session.setAttribute("buyCount", buyCount);
        return "/pages/order/cart";
    }

    @RequestMapping("/tocart")
    public String tocart() {
        return "/pages/order/cart";
    }


    @RequestMapping("/toDelete")
    public String toDelete(String productId, HttpSession session, Model model) {
        Product product = productService.findProductById(productId);
        System.out.println(product);
        ((Map<Product, Integer>) session.getAttribute("cart")).remove(product);
        System.out.println("size:" + ((Map<Product, Integer>) session.getAttribute("cart")).size());
        return "/pages/order/cart";
    }

    @RequestMapping("/toOrder")
    public String toOrder(HttpSession session, String[] buyNum, Model model) {
        Integer[] buyCount = new Integer[buyNum.length];
        for (int i = 0; i < buyNum.length; i++) {
            for (int j = 0; j < buyNum.length; j++) {
                buyCount[j] = Integer.parseInt(buyNum[i]);
            }
        }
        Map<Product, Integer> cartMap = (HashMap<Product, Integer>) session.getAttribute("cart");
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            for (int i = 0; i < buyCount.length; i++) {
                entry.getKey().setBuyCount(entry.getValue());
            }
            productList.add(entry.getKey());
        }
        model.addAttribute("productList", productList);
        return "pages/order/addorder";
    }

    @RequestMapping("/toConfirmOrder")
    public String toConfirmOrder(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        User userInfos = userService.findUserInfo(user.getUserId());
        Map<Product, Integer> cartMap = (HashMap<Product, Integer>) session.getAttribute("cart");
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            productList.add(entry.getKey());
        }
        model.addAttribute("productList", productList);
        model.addAttribute("user", userInfos);
        return "pages/order/confirm_order1";
    }

    /* @RequestMapping("/order")
     public String  order(HttpServletRequest request, String addresinfo, @RequestParam("productId") String[] productIds, HttpSession session){
         Map<Product,Integer> cartMap =(Map<Product, Integer>) session.getAttribute("cart");
         User user = (User) session.getAttribute("user");

         orderService.saveOrder(user,productIds,addresinfo,cartMapentry.getKey().getProductId(),entry.getValue());

         List<Product> list = productService.findProductsById(productIds);
         for (Product product:list) {
             cartMap.remove(product);//清空购物车
         }
         System.out.println("size:"+cartMap.size());
         return "pages/order/respond";
     }*/
    @RequestMapping("/order")
    public String order(HttpServletRequest request, HttpServletResponse response, String addresinfo, @RequestParam("productId") String[] productIds, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        //1接收订单id
        String oid = UUID.randomUUID().toString();
        Map<Product, Integer> cartMap = (Map<Product, Integer>) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        orderService.saveOrder(user, productIds, addresinfo, cartMap, oid);
        List<Product> list = productService.findProductsById(productIds);
        //2、准备第三方支付平台需要的参数
        String p0_Cmd = "Buy";//业务类型
        String p1_MerId = PropUtils.getProperty("p1_MerId");
        String p2_Order = oid;//商户的订单号
        //测试时使用
        String p3_Amt = "0.01";//订单金额
        //“正式发布”的时候使用
        /*OrderService os = BasicFactory.getFactory().getInstance(OrderService.class);
        Order order = os.findOrderByOid(oid);
		String p3_Amt = ""+order.getMoney();*/
        String p4_Cur = "CNY";//交易币种
        String p5_Pid = "";//商品名称
        String p6_Pcat = "";//商品分类
        String p7_Pdesc = "";//商品描述
        String p8_Url = PropUtils.getProperty("responseURL");

        String p9_SAF = "";//送货地址
        String pa_MP = "";//商户的扩展信息
        //支付通道编码
        String pd_FrpId = request.getParameter("pd_FrpId");
        //应答机制
        String pr_NeedResponse = "1";
        //调用工具类生产数据签名
        String result = PropUtils.getProperty("keyValue");
        String hmac = PaymentUtil.buildHmac(p0_Cmd,
                p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid,
                p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, PropUtils.getProperty("keyValue"));
        //3、将以上产生保存request
        request.setAttribute("list", list);
        request.setAttribute("pd_FrpId", pd_FrpId);
        request.setAttribute("p0_Cmd", p0_Cmd);
        request.setAttribute("p1_MerId", p1_MerId);
        request.setAttribute("p2_Order", p2_Order);
        request.setAttribute("p3_Amt", p3_Amt);
        request.setAttribute("p4_Cur", p4_Cur);
        request.setAttribute("p5_Pid", p5_Pid);
        request.setAttribute("p6_Pcat", p6_Pcat);
        request.setAttribute("p7_Pdesc", p7_Pdesc);
        request.setAttribute("p8_Url", p8_Url);
        request.setAttribute("p9_SAF", p9_SAF);
        request.setAttribute("pa_MP", pa_MP);
        request.setAttribute("pr_NeedResponse", pr_NeedResponse);
        request.setAttribute("hmac", hmac);
       /* List<Product> list = productService.findProductsById(productIds);
        for (Product product:list) {
            cartMap.remove(product);//清空购物车
        }
        System.out.println("size:"+cartMap.size());*/
        return "pages/order/confirm";
    }

    @RequestMapping("/callBack")
    public String callBack(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        Map<Product, Integer> cartMap = (Map<Product, Integer>) session.getAttribute("cart");
        String[] productIds = request.getParameterValues("list");
        //1、接收参数
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        //商品订单号
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        //交易结果的返回类型:1表示重定向  2点对点通讯
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        //银行交易的流水号
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");
        //在第三方支付平台上完成支付的时间
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime = request.getParameter("ru_Trxtime");
        //签名数据
        String hmac = request.getParameter("hmac");

        // response.getWriter().write("支付操作已执行，支付结果需要等待进一步的通知");
        //测试使用，正式发布前要删除以下两行代码
        //修改订单的支付状态
        orderService.updateOrderStatus("0", r6_Order);

        //响应给第三方支付平台 success
        response.getWriter().write("success");
//                List<Product> list = productService.findProductsById(r6_Order);
        List<Product> list = productService.findProductsById();
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            int buyCount = entry.getKey().getBuyCount();
            String pId = entry.getKey().getProductId();
            orderService.updateProductNum(pId, buyCount);
        }
        for (Product product : list) {
            cartMap.remove(product);//清空购物车
        }
        return "pages/order/respond";
    }


}
