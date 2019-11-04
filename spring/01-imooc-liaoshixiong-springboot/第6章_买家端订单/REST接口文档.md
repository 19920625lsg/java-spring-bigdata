# API

## 商品列表

### URL

```shell
GET /sell/buyer/product/list
```

### 请求参数

```shell
无
```

### 返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "name": "热榜",
            "type": 1,
            "foods": [
                {
                    "id": "123456",
                    "name": "皮蛋粥",
                    "price": 1.2,
                    "description": "好吃的皮蛋粥",
                    "icon": "http://xxx.com",
                }
            ]
        },
        {
            "name": "好吃的",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "慕斯蛋糕",
                    "price": 10.9,
                    "description": "美味爽口",
                    "icon": "http://xxx.com",
                }
            ]
        }
    ]
}
```

## 创建订单

### URL

```shell
POST /sell/buyer/order/create
```

### 请求参数

```json
name: "张三"
phone: "18868822111"
address: "慕课网总部"
openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
items: [{
    productId: "1423113435324",
    productQuantity: 2 //购买数量
}]

```

### 返回值

```json
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

## 订单列表

### URL

```shell
GET /sell/buyer/order/list
```

### 请求参数

```json
openid: 18eu2jwk2kse3r42e2e
page: 0 //从第0页开始
size: 10
```

### 返回值

```json
{
  "code": 0,
  "msg": "成功",
  "data": [
    {
      "orderId": "161873371171128075",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "慕课网总部",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    },
    {
      "orderId": "161873371171128076",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "慕课网总部",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    }]
}
```

## 查询订单详情

### URL

```shell
GET /sell/buyer/order/detail
```

### 请求参数

```json
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

### 返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
          "orderId": "161899085773669363",
          "buyerName": "李四",
          "buyerPhone": "18868877111",
          "buyerAddress": "慕课网总部",
          "buyerOpenid": "18eu2jwk2kse3r42e2e",
          "orderAmount": 18,
          "orderStatus": 0,
          "payStatus": 0,
          "createTime": 1490177352,
          "updateTime": 1490177352,
          "orderDetailList": [
            {
                "detailId": "161899085974995851",
                "orderId": "161899085773669363",
                "productId": "157875196362360019",
                "productName": "招牌奶茶",
                "productPrice": 9,
                "productQuantity": 2,
                "productIcon": "http://xxx.com",
                "productImage": "http://xxx.com"
            }
        ]
    }
}
```

## 取消订单

### URL

```shell
POST /sell/buyer/order/cancel
```

### 请求参数

```json
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

### 返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

## 获取openid

### URL

```shell
重定向到 /sell/wechat/authorize
```

### 请求参数

```json
returnUrl: http://xxx.com/abc  //【必填】
```

### 返回值

```json
http://xxx.com/abc?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg
```

## 支付订单

### URL

```shell
重定向 /sell/pay/create
```

### 请求参数

```json
orderId: 161899085773669363
returnUrl: http://xxx.com/abc/order/161899085773669363
```

### 返回值

```json
http://xxx.com/abc/order/161899085773669363
```