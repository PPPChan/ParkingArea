

# API



<h2 style="color:red">1 用户模块</h4>


<h4 style="color:red">1-1 查看用户列表</h4>

```
GET /parkingarea/userinfo/list
```

参数

```
page: 0 //从第0页开始
size: 10
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "openid": "sdadasaksfhkjsah23djsal",
            "userName": "陈俊鹏",
            "userPhone": "18868822111",
            "userType": 0,
            "createTime": 1490177352,
            "updateTime": 1490177352
        },
        {
            "openid": "sdadasak321sfsah23djsal",
            "userName": "哒哒哒",
            "userPhone": "18868822111"
            "userType": 0,
            "createTime": 1490177352,
            "updateTime": 1490177352
        }
        
    ]
}
```

<h4 style="color:red">1-2 创建用户</h4>

```
POST /parkingarea/userinfo/create
```

参数

```
openid: "widkhdkjakjd",//用户的微信openid
userName: "张三",
phone: "18868822111",
```
返回
```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "openid": "widkhdkjakjd" 
  }
}
```





<h4 style="color:red">1-3 用户查看个人信息</h4>

```
GET /parkingarea/userinfo/personal
```
参数
```
openid: "dddddddddasdaklj"
```
返回
```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "openid": "dddddddddasdaklj",
      "userName": "张三",
      "phone": "132658888888",
      "userType": 0
  }
}
```



<h4 style="color:red">1-4 更新用户信息</h4>

```
POST /parkingarea/userinfo/update
```

参数
```
openid: "widkhdkjakjd",//用户的微信openid
userName: "张三",
phone: "18868822111",
```
返回
```json
{
  "code": 0,
  "msg": "成功",
  "data": {
  	  "openid": "widkhdkjakjd",
      "userName": "张三",
      "phone": "18868822111",
  }
}
```



<h4 style="color:red">1-5 开通月卡</h4>

```
POST /parkingarea/userinfo/openMonthlyCard
```

参数

```
openid: "widkhdkjakjd",//用户的微信openid
```

返回

```json
{
  "code": 0,
  "msg": "成功",
  "data": 1
}
```



<h4 style="color:red">1-6 查看月卡到期时间</h4>

```
GET /parkingarea/userinfo/getExpire
```

参数

```
openid: "widkhdkjakjd",//用户的微信openid
```

返回

```json
{
  "code": 0,
  "msg": "成功",
  "data": "2021-12-5 12:32:47"
}
```




<h2 style="color:red">2 员工模块</h4>

<h4 style="color:red">2-1 添加员工</h4>

```
POST /parkingarea/employee/create
```

参数

```
employeeName: "路人甲",
employeePassword: "123456789",
employeePhone: "13268889999"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": 5
}
```



<h4 style="color:red">2-2 员工查看个人信息</h4>

```
GET /parkingarea/employee/personal
```

参数

```
employeeId: 3
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
  	  "employeeId": "1",
  	  "employeeName": "路人甲",
  	  "employeePhone": "13268889999"
  }
}
```

<h4 style="color:red">2-3 修改员工个人信息</h4>

```
POST /parkingarea/employee/update
```

参数

```
employeeId: 1
employeeName: "路人甲",
employeePassword: "123456789",
employeePhone: "13268889999"
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "employeeName": 1,
  	  "employeeName": "路人甲",
  	  "employeePhone": "13268889999"
  }
}
```

<h4 style="color:red">2-4 查看员工列表</h4>

```
GET /parkingarea/employee/list
```

参数

```
page: 0 //从第0页开始
size: 10
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "employeeId": 1,
            "employeePassword": "123123",
            "employeeName": "员工甲",
            "employeePhone": "",
            "createTime": 1618650499,
            "updateTime": 1618651607
        },
        {
            "employeeId": 2,
            "employeePassword": "8888",
            "employeeName": "员工乙",
            "employeePhone": "13655555555",
            "createTime": 1618650548,
            "updateTime": 1618654341
        },
        {
            "employeeId": 3,
            "employeePassword": "12345646789",
            "employeeName": "迪迦",
            "employeePhone": "13267716728",
            "createTime": 1618653155,
            "updateTime": 1618653155
        }
    ]
}
```
<h4 style="color:red">2-5 删除员工</h4>

```
GET /parkingarea/employee/delete
```

参数

```
employeeId: 1
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": "删除成功"
}
```
<h2 style="color:red">3 订单模块</h4>

<h4 style="color:red">3-1 创建订单</h4>

```
POST /parkingarea/order/create
```

参数

```
licensePlateNumber: "粤A66666" //车牌号
parkingId: 1
```
返回
```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "orderId": "1618935107790281496"
    }
}
```

<h4 style="color:red">3-2 查看订单详情</h4>

```
GET /parkingarea/order/detail
```

参数

```
orderId:147283992738221
```
返回
```json
{
    "code": 0,
    "msg": "成功",
    "data": {
        "orderId": "1618935107790281496",
        "parkingName": "海利停车场",
        "hourPrice": 1.00,
        "licensePlateNumber": "粤C88888",
        "cost": 1308.98,
        "discount": 0.00,
        "orderAmount": 1308.98,
        "payStatus": 0,
        "createTime": 1623687107,
        "endTime": 1623761801
    }
}
```

<h4 style="color:red">3-3 查看个人订单列表</h4>

```
GET /parkingarea/order/personal
```

参数

```
openid:123123
```
返回
```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "orderId": "1618921223197709717",
            "parkingId": 4,
            "parkingName": "吉珠停车场",
            "hourPrice": 0.00,
            "userOpenid": "123123",
            "licensePlateNumber": "粤C88888",
            "orderAmount": 0.00,
            "payStatus": 0,
            "createTime": 1618921223,
            "endTime": 1618921941
        },
        {
            "orderId": "1618935107790281496",
            "parkingId": 3,
            "parkingName": "海利停车场",
            "hourPrice": 1.00,
            "userOpenid": "123123",
            "licensePlateNumber": "粤C88888",
            "orderAmount": 0.00,
            "payStatus": 0,
            "createTime": 1618935107,
            "endTime": 1618935107
        }
    ]
}
```



<h4 style="color:red">3-4 查询订单列表</h4>

```
GET /parkingarea/order/list
```

参数

```
page: 2 //从第0页开始
size: 2
```
返回
```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "data": [
            {
                "orderId": "1618935107790281496",
                "parkingId": 3,
                "parkingName": "海利停车场",
                "hourPrice": 1.00,
                "userOpenid": "123123",
                "licensePlateNumber": "粤C88888",
                "orderAmount": 0.00,
                "payStatus": 0,
                "createTime": 1618935107,
                "endTime": 1618935107
            }
        ],
        "attr": {
            "totalPage": 3,
            "currentPage": 3
        }
    }
}
```



<h2 style="color:red">4 车牌模块</h4>
<h4 style="color:red">4-1 绑定车牌</h4>


```
POST /parkingarea/licenseplate/bind
```

参数

```
openid: "123hkj12h3k"
licensePlateNumber: "粤A66666" //车牌号
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": "粤C88888"
}
```

<h4 style="color:red">4-2 取消绑定车牌</h4>

```
POST /parkingarea/licenseplate/unbind
```

参数

```
openid: "123hkj12h3k"
licensePlateNumber: "粤A66666" //车牌号
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": "粤A66666"
}
```

<h4 style="color:red">4-3 查看个人车牌列表</h4>

```
GET /parkingarea/licenseplate/personal
```

参数

```
openid: "11111111"
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "licensePlateNumber": "粤A66666",
            "openid": "11111111"
        },
        {
            "licensePlateNumber": "粤A66688",
            "openid": "11111111"
        },
        {
            "licensePlateNumber": "粤A88886",
            "openid": "11111111"
        },
        {
            "licensePlateNumber": "粤B67777",
            "openid": "11111111"
        },
        {
            "licensePlateNumber": "粤RNG666",
            "openid": "11111111"
        }
    ]
}
```

<h2 style="color:red">5 停车场模块</h4>
<h4 style="color:red">5-1 新建停车场</h4>

```
POST /parkingarea/parking/create
```

参数

```
parkingName: "西丽停车场"
parkingAddress: "深圳市南山区西丽街道"
hourPrice: 10
parkingTotal: 50
longitude: 116.23
latitude: 40.48
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
    	"parkingId": 1
    }
}
```
<h4 style="color:red">5-2 更新停车场信息</h4>



```
POST /parkingarea/parking/update
```

参数

```
parkingId: 1
parkingName: 西丽停车场
parkingAddress: 深圳市南山区西丽街道
hourPrice:11
parkingTotal: 50
longitude: 116.23
latitude: 40.48
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "parkingId": 1,
        "parkingName": " 西丽停车场",
        "parkingAddress": " 深圳市南山区西丽街道",
        "hourPrice": 11,
        "parkingTotal": 50,
        "parkingUsed": 50,
        "parkingAvailable": 0,
        "parkingStatus": 1,
        "createTime": 1618838597,
        "updateTime": 1618921960,
        
    }
}
```
<h4 style="color:red">5-3 关闭停车场</h4>

```
POST /parkingarea/parking/close
```

参数

```
parkingId: 3
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "parkingId": 3,
        "parkingName": "海利停车场",
        "parkingAddress": "海利花园",
        "hourPrice": 0.00,
        "parkingTotal": 90,
        "parkingUsed": 2,
        "parkingAvailable": 88,
        "parkingStatus": 0,
        "createTime": 1618903158,
        "updateTime": 1618924705
    }
}
```

<h4 style="color:red">5-4 加已用车位</h4>

```
POST /parkingarea/parking/increaseUsed
```
参数
```
parkingId: 3
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "parkingId": 3,
        "parkingName": "海利停车场",
        "parkingAddress": "海利花园",
        "hourPrice": 0.00,
        "parkingTotal": 90,
        "parkingUsed": 3,
        "parkingAvailable": 87,
        "parkingStatus": 0,
        "createTime": 1618903158,
        "updateTime": 1618924705
    }
}
```
<h4 style="color:red">5-5 扣已用车位</h4>

```
POST /parkingarea/parking/decrease
```
参数
```
parkingId: 2
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "parkingId": 2,
        "parkingName": "海利停车场",
        "parkingAddress": "海利花园",
        "hourPrice": 0.00,
        "parkingTotal": 90,
        "parkingUsed": 2,
        "parkingAvailable": 88,
        "parkingStatus": 0,
        "createTime": 1618903158,
        "updateTime": 1618924705
    }
}
```
<h4 style="color:red">5-6 查询单个停车场</h4>

```
GET /parkingarea/parking/detail
```
参数
```
parkingId: 4
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "parkingId": 4,
        "parkingName": "吉珠停车场",
        "parkingAddress": "珠海市金湾区吉林大学珠海学院",
        "hourPrice": 0.00,
        "parkingTotal": 99,
        "parkingUsed": 1,
        "parkingAvailable": 98,
        "parkingStatus": 1,
        "longitude": 116.23,
		"latitude": 40.48,
        "createTime": 1618905511,
        "updateTime": 1618921223
    }
}
```
<h4 style="color:red">5-7 查询所有停车场</h4>

```
POST /parkingarea/parking/list
```
参数
```
page: 0 //从第0页开始
size: 10
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "parkingId": 1,
            "parkingName": " 西丽停车场",
            "parkingAddress": " 深圳市南山区西丽街道",
            "hourPrice": 11.11,
            "parkingTotal": 50,
            "parkingUsed": 50,
            "parkingAvailable": 0,
            "parkingStatus": 1,
            "createTime": 1618838597,
            "updateTime": 1618927169
        },
        {
            "parkingId": 2,
            "parkingName": "海丰停车场",
            "parkingAddress": "",
            "hourPrice": 0.00,
            "parkingTotal": 90,
            "parkingUsed": 2,
            "parkingAvailable": 88,
            "parkingStatus": 0,
            "createTime": 1618826724,
            "updateTime": 1618922092
        },
        {
            "parkingId": 3,
            "parkingName": "海利停车场",
            "parkingAddress": "海利花园",
            "hourPrice": 1.00,
            "parkingTotal": 90,
            "parkingUsed": 4,
            "parkingAvailable": 86,
            "parkingStatus": 0,
            "createTime": 1618903158,
            "updateTime": 1618927086
        },
        {
            "parkingId": 4,
            "parkingName": "吉珠停车场",
            "parkingAddress": "珠海市金湾区吉林大学珠海学院",
            "hourPrice": 0.00,
            "parkingTotal": 99,
            "parkingUsed": 1,
            "parkingAvailable": 98,
            "parkingStatus": 1,
            "createTime": 1618905511,
            "updateTime": 1618921223
        },
        {
            "parkingId": 5,
            "parkingName": "官龙山停车场",
            "parkingAddress": " 深圳市南山区西丽街道",
            "hourPrice": 10.00,
            "parkingTotal": 50,
            "parkingUsed": 0,
            "parkingAvailable": 50,
            "parkingStatus": 1,
            "createTime": 1618926770,
            "updateTime": 1618926770
        }
    ]
}
```

<h4 style="color:red">5-8 开启停车场</h4>

```
POST /parkingarea/parking/open
```

参数

```
parkingId: 3
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
        "parkingId": 3,
        "parkingName": "海利停车场",
        "parkingAddress": "海利花园",
        "hourPrice": 0.00,
        "parkingTotal": 90,
        "parkingUsed": 2,
        "parkingAvailable": 88,
        "parkingStatus": 1,
        "createTime": 1618903158,
        "updateTime": 1618924705
    }
}
```



<h4 style="color:red">5-9 查询附近停车场（500km）</h4>

```
GET /parkingarea/parking/radius
```

参数

```
longitude: 110
latitude: 33
```

返回

```json
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "parkingName": " 西丽停车场",
            "parkingAddress": " 深圳市南山区西丽街道",
            "distance": 0.01,
            "parkingAvailable": 0
        },
        {
            "parkingName": "海丰停车场",
            "parkingAddress": "广东省汕尾市海丰县二环南路",
            "distance": 297.46,
            "parkingAvailable": 88
        },
        {
            "parkingName": "海利停车场",
            "parkingAddress": "海利花园",
            "distance": 476.74,
            "parkingAvailable": 85
        }
    ]
}
```

<h4 style="color:red">5-10 修改停车场地理位置</h4>

```
POST /parkingarea/parking/updateGeo
```

参数

```
longitude: 110
latitude: 33
```

返回

```json
{
    "code": 0,
    "msg": "成功",
    "data": "更新地理位置成功！"
}
```