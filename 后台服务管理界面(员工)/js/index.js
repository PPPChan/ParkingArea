
$(window).load(loadParkingList());

//获取数据
function loadParkingList() {
    // alert($.session.get('employeeId'));
    // alert($.session.get('employeeName'));

    if ($.session.get('employeeId') == null) {
        // alert($.session.get('employeeId'));
        window.location.href = 'login.html';
    };


    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/employee/personal",
        type: "GET",
        //处理跨域问题
        data: {
            "employeeId": $.session.get('employeeId')
        },
        success: function (data) {
            sessionStorage.setItem("employeeName", data.data.employeeName);          //将数据存入sessionStorage
            // alert("111"+$.session.get('employeeName'));
            $("#employeeName").text($.session.get('employeeName'));
            // alert("111"+$.session.get('employeeName'));
        }
    })



    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/parking/list",
        type: "GET",
        //处理跨域问题

        data: {
            "parkingId": 1
        },

        success: function (data) {
            console.log(data.data.parkingName);
            $("#s1").text(data.data.parkingName);


            $("#parkingList tr:not(#title)").remove();
            $.each(data.data, function (index, item) {

                parkingStatus = item.parkingStatus == 0 ? "关闭" : "营业中";

                if (item.parkingStatus == 0) {
                    operator = "<button class='btn btn-success btn-sm' type='button' onclick=\"op(" + item.parkingId + ")\">开启</button>";

                } else {
                    operator = "<button class='btn btn-danger btn-sm' type='button'onclick=\"cls(" + item.parkingId + ")\">关闭</button>";
                }
                $("#parkingList").append(
                    "<tr><td id='parkingId'>" + item.parkingId + "</td>"
                    + "<td id='parkingName'>" + item.parkingName + "</td>"
                    + "<td id='parkingAddress'>" + item.parkingAddress + "</td>"
                    + "<td id='hourPrice'>" + item.hourPrice + "</td>"
                    + "<td id='parkingTotal'>" + item.parkingTotal + "</td>"
                    + "<td id='parkingUsed'>" + item.parkingUsed + "</td>"
                    + "<td id='parkingAvailable'>" + item.parkingAvailable + "</td>"
                    + "<td id='parkingStatus'>" + parkingStatus + "</td>"
                    + "<td id='createTime'>" + getLocalTime(item.createTime) + "</td>"
                    + "<td id='updateTime'>" + getLocalTime(item.updateTime) + "</td>"
                    + "<td id='operator'><button type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#updateParking' data-whatever='@fat' onclick='detail(" + item.parkingId + ")'>修改</button>&nbsp" + operator + "</td>"
                    + "</tr>");


            });
        }
    })
}



//获取员工数据
function loadEmployeeList() {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/employee/list",
        type: "GET",
        //处理跨域问题

        data: {

        },

        success: function (data) {
            $("#employeeList tr:not(#title)").remove();
            $.each(data.data, function (index, item) {


                $("#employeeList").append(
                    "<tr><td>" + item.employeeId + "</td>"
                    + "<td>" + item.employeePassword + "</td>"
                    + "<td>" + item.employeeName + "</td>"
                    + "<td>" + item.employeePhone + "</td>"
                    + "<td>" + getLocalTime(item.createTime) + "</td>"
                    + "<td>" + getLocalTime(item.updateTime) + "</td>"
                    + "<td id='operator'><button class='btn btn-danger btn-sm' type='button' onclick='deleteEmployee(" + item.employeeId + ")'>删除</button></td>"
                    + "</tr>");

            });
        }
    })
}


//获取订单数据
function loadOrderList(page) {
    

    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/order/list",
        type: "GET",
        //处理跨域问题

        data: {
            page: page,
            size: 10
        },

        success: function (data) {
            console.log(data.data.parkingName);
            $("#s1").text(data.data.parkingName);
            $("#orderList tr:not(#title)").remove();
            $.each(data.data.data, function (index, item) {
                payStatus = item.payStatus == 0 ? "<img src='img/weizhifu.png'/>未支付" : "<img src='img/yizhifu.png'/>已支付";
                operator = item.payStatus == 0? "<td id='operator'><button class='btn btn-danger btn-sm' type='button' onclick='pullFinish(" + item.orderId + ")'>补单</button></td>":""
                $("#orderList").append(
                    "<tr><td id='orderId'>" + item.orderId + "</td>"
                    + "<td id='parkingName'>" + item.parkingName + "</td>"
                    + "<td id='hourPrice'>" + item.hourPrice + "</td>"
                    + "<td id='userOpenid'>" + item.userOpenid + "</td>"
                    + "<td id='licensePlateNumber'>" + item.licensePlateNumber + "</td>"
                    + "<td id='createTime'>" + getLocalTime(item.createTime) + "</td>"
                    + "<td id='endTime'>" + getLocalTime(item.endTime) + "</td>"
                    + "<td id='orderAmount'>" + item.orderAmount + "</td>"
                    + "<td id='payStatus'>" + payStatus + "</td>"
                    + operator
                    + "</tr>"
                    );
            });

            //分页功能
            var currentPage = data.data.attr.currentPage;
            $("#orderPage").children().remove();
            
            //上一页
            if(currentPage>1){
                $("#orderPage").append(
                    "<ul class=\"pagination pull-right\"><li><a onclick=\"loadOrderList("+(currentPage-1)+")\" href=\"#\">上一页</a></li>"
                    +"</ul>"
                )
            }else{
                $("#orderPage").append(
                    "<ul class=\"pagination pull-right\"><li class=\"disabled\"><a href=\"#\">上一页</a></li>"
                    +"</ul>"
                )
            }

            //中间
            for(var i = 1;i<=data.data.attr.totalPage;i++){
                if(currentPage == i){    
                    $("#orderPage").children().append(
                        "<li class=\"disabled\"><a onclick=\"loadOrderList("+i+")\" href=\"#\">"+i+"</a></li>"
                    )
                }else{    
                    $("#orderPage").children().append(
                        "<li><a onclick=\"loadOrderList("+i+")\" href=\"#\">"+i+"</a></li>"
                    ) 
                }
            }

            //下一页
            if(currentPage<data.data.attr.totalPage){
                $("#orderPage").children().append(
                    "<li><a onclick=\"loadOrderList("+(currentPage+1)+")\" href=\"#\">下一页</a></li>"
                )
            }else{
                $("#orderPage").children().append(
                    "<li class=\"disabled\"><a href=\"#\">下一页</a></li>"
                )
            }
            
        }
    })
}


//获取用户数据
function loadUserList() {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/userinfo/list",
        type: "GET",
        //处理跨域问题

        data: {

        },
        success: function (data) {
            $("#userList tr:not(#title)").remove();
            $.each(data.data, function (index, item) {
                userType = item.userType == 0 ? "普通用户" : "月卡用户";
                $("#userList").append(
                    "<tr><td>" + item.openid + "</td>"
                    + "<td>" + item.userName + "</td>"
                    + "<td>" + item.userPhone + "</td>"
                    + "<td>" + userType + "</td>"
                    + "<td>" + getLocalTime(item.createTime) + "</td>"
                    + "<td>" + getLocalTime(item.updateTime) + "</td>"
                    + "</tr>");

            });
        }
    })
}



//获取员工个人信息
function loadPersonal() {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/employee/personal",
        type: "GET",
        //处理跨域问题
        data: {
            "employeeId": $.session.get('employeeId')
        },
        success: function (data) {
            $("#modifySubmit").hide();
            $("#employeeNameP").attr('placeholder', data.data.employeeName);
            $("#employeePhone").attr('placeholder', data.data.employeePhone);
            $('#employeeNameP').val(data.data.employeeName);
            $('#employeePhone').val(data.data.employeePhone);
        }
    })
}





//新建停车场
function createParking() {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/parking/create",
        type: "POST",
        //处理跨域问题
        data: {
            "parkingName": $("input[name='parkingName']").val(),
            "parkingAddress": $("input[name='parkingAddress']").val(),
            "hourPrice": $("input[name='hourPrice']").val(),
            "parkingTotal": $("input[name='parkingTotal']").val()
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert("新建停车场成功！停车场编号为:" + data.data.parkingId);
            loadParkingList();
        }
    })
};

//开启停车场
function op(parkingId) {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/parking/open",
        type: "POST",
        //处理跨域问题
        data: {
            "parkingId": parkingId
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert(data.data.parkingName + "开启成功");
            loadParkingList();
        }
    })
};


//关闭停车场
function cls(parkingId) {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/parking/close",
        type: "POST",
        //处理跨域问题
        data: {
            "parkingId": parkingId
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert(data.data.parkingName + "已关闭");
            loadParkingList();
        }
    })
};

//获取停车场详情
function detail(parkingId) {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/parking/detail",
        type: "GET",
        //处理跨域问题
        data: {
            "parkingId": parkingId
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            $("#disabledInput").val(data.data.parkingId);
            $("#updateName").val(data.data.parkingName);
            $("#updateAddress").val(data.data.parkingAddress);
            $("#updatePrice").val(data.data.hourPrice);
            $("#updateTotal").val(data.data.parkingTotal);
        }
    })
};

//更新停车场
function updateParking() {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/parking/update",
        type: "POST",
        //处理跨域问题
        data: {
            "parkingId": $("input[name='parkingId']").val(),
            "parkingName": $("input[name='updateName']").val(),
            "parkingAddress": $("input[name='updateAddress']").val(),
            "hourPrice": $("input[name='updatePrice']").val(),
            "parkingTotal": $("input[name='updateTotal']").val()
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert("修改成功");
            loadParkingList();
        }
    })
};

//添加员工
function createEmployee() {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/employee/create",
        type: "POST",
        //处理跨域问题
        data: {
            "employeeName": $("input[name='employeeName']").val(),
            "employeePassword": $("input[name='employeePassword']").val(),
            "employeePhone": $("input[name='employeePhone']").val()
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert("添加员工成功！员工号为:" + data.data);
            loadEmployeeList();
        }
    })

};


//删除员工
function deleteEmployee(employeeId) {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/employee/delete",
        type: "POST",
        //处理跨域问题
        data: {
            "employeeId": employeeId
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert(data.data);
            loadEmployeeList();

        }
    })
};

//拉出补单窗口


//员工管理页
$("#employeeMaster").click(function () {
    $(this).parent().addClass("active").siblings().removeClass('active');
    $("#employeeListDiv").siblings().hide();
    loadEmployeeList();
    $("#employeeListDiv").show();

});

//停车场管理页
$("#parkingMaster").click(function () {
    $(this).parent().addClass("active").siblings().removeClass('active');
    $("#parkingListDiv").siblings().hide();
    loadEmployeeList();
    $("#parkingListDiv").show();

});


//订单管理页
$("#orderMaster").click(function () {
    $(this).parent().addClass("active").siblings().removeClass('active');
    $("#orderListDiv").siblings().hide();
    loadOrderList(1);
    $("#orderListDiv").show();
});

//首页
$("#overview").click(function () {
    $(this).parent().addClass("active").siblings().removeClass('active');
    $("#overviewDiv").siblings().hide();
    loadOrderList();
    $("#overviewDiv").show();
});

//用户管理页
$("#userMaster").click(function () {
    $(this).parent().addClass("active").siblings().removeClass('active');
    // $("#userListDiv");
    loadUserList();
    $("#userListDiv").show().siblings().hide();
});


//个人信息页
$("#employeeName").click(function () {
    $("#userMaster").parent().siblings().removeClass('active');
    $("#personal").show().siblings().hide();
    loadPersonal();
});

//点击修改按钮
$("#modify").click(function () {
    $("#modify").hide();
    $("#modifySubmit").show();
    $('#employeePhone').removeAttr("disabled");
    $('#employeeNameP').removeAttr("disabled");
});


//点击提交修改按钮
$("#modifySubmit").click(function () {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/employee/update",
        type: "POST",
        //处理跨域问题
        data: {
            "employeeId": $.session.get('employeeId'),
            "employeeName": $("#employeeNameP").val(),
            "employeePhone": $("#employeePhone").val()
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert(data.msg);
            sessionStorage.setItem("employeeName", data.data.employeeName);
            // loadParkingList();  
            window.location.replace("index.html");
        }
    })
});


//修改密码页
$("#modifyPasswordBtn").click(function () {
    $("#modifyPassword").show().siblings().hide();
});



//验证俩次密码是否一致
function validate() {
    var pwd1 = $("#pwd1").val();
    var pwd2 = $("#pwd2").val();
    if(pwd2 == ""){
        $('#changePassword').attr("disabled",true);
    }
    if (pwd1 == pwd2) {
        $("#tishi").html("两次密码相同");
        $("#tishi").css("color", "green");
        $("#xiugai").removeAttr("disabled");
        $('#changePassword').removeAttr("disabled");
    }
    else {
        $("#tishi").html("两次密码不相同");
        $("#tishi").css("color", "red");
        $("button").attr("disabled", "disabled");
        $('#changePassword').attr("disabled",true);
    }
}

//确认修改密码
$("#changePassword").click(function () {
    $.ajax({
        url: "http://193.112.203.154:8080/parkingarea/employee/update",
        type: "POST",
        //处理跨域问题
        data: {
            "employeeId": $.session.get('employeeId'),
            "employeePassword": $("#pwd1").val()
        },
        success: function (data) {
            // console.log(data.data.parkingName);
            alert(data.msg);
            window.location.replace("index.html");
        }
    })
});

//注销
$("#logout").click(function () {
    $.session.clear();
    window.location.reload();
});

//时间戳转换
function getLocalTime(nS) {
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}